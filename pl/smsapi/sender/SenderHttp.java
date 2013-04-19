package pl.smsapi.sender;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import pl.smsapi.SmsapiException;
import pl.smsapi.message.Account;
import pl.smsapi.message.Message;
import pl.smsapi.message.MessageInterface;
import pl.smsapi.message.Mms;
import pl.smsapi.message.Phonebook;
import pl.smsapi.message.Result;
import pl.smsapi.message.Sms;
import pl.smsapi.message.Vms;

public final class SenderHttp extends Sender implements SenderInterface {

	protected URI uri;
	protected File file;
	protected RequestMethod requestMethod;

	public static enum RequestMethod {

		POST("POST"),
		GET("GET");
		private final String method;

		RequestMethod(String method) {
			this.method = method;
		}

		public String method() {
			return method;
		}
	};

	public SenderHttp() {
		requestMethod = RequestMethod.POST;
	}

	public SenderHttp(Message message) {
		this();
		setMessage(message);
	}
	
	public SenderHttp(Account user) {
		this();
		setMessage(user);
	}

	@Override
	public void setMethod(Object method) {
		setRequestMethod((RequestMethod) method);
	}
	
	public void setRequestMethod(RequestMethod method) {
		this.requestMethod = method;
	}

	private class SenderConnection<T extends HttpURLConnection> {

		protected T conn;
		protected File file;
		protected String params;

		public SenderConnection(T conn, File file, String params) {
			this.conn = conn;
			this.file = file;
			this.params = params;

			checkConn();

		}

		protected void checkConn() {
			if (conn == null) {
				throw new SmsapiException("http/https connection not exists");
			}
		}
		
		private void isHttps()
		{
			if (conn instanceof HttpsURLConnection) {
				HttpsURLConnection httpsType = (HttpsURLConnection) conn;

				httpsType.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {
					public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
						return true;
					}
				});
				conn = (T) httpsType;
			}
		}

		public SenderConnection<T> initGET() throws ProtocolException {
			checkConn();

			conn.setRequestMethod(RequestMethod.GET.method());
			
			isHttps();
			
			conn.setDoInput(true);

			return this;
		}

		public SenderConnection<T> initPOST() throws ProtocolException, FileNotFoundException, IOException {
			checkConn();

			conn.setRequestMethod(RequestMethod.POST.method());

			isHttps();

			if (this.file != null) {
				addFile();
			} else {
				addPost(this.params);
			}

			return this;
		}

		protected void addFile() throws ProtocolException, FileNotFoundException, IOException {

			checkConn();

			DataOutputStream dos = null;
			String lineEnd = "\r\n";
			String twoHyphens = "--";
			String boundary = "***232404jkg4220957934FW**";
			int bytesRead, bytesAvailable, bufferSize;
			byte[] buffer;
			int maxBufferSize = 1 * 1024 * 1024;

			FileInputStream fileInputStream = new FileInputStream(file);

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type",
					"multipart/form-data;boundary=" + boundary);

			dos = new DataOutputStream(conn.getOutputStream());

			dos.writeBytes(twoHyphens + boundary + lineEnd);
			dos.writeBytes("Content-Disposition: form-data; name=\"file\";"
					+ " filename=\"" + this.file.getName() + "\"" + lineEnd);
			dos.writeBytes(lineEnd);

			bytesAvailable = fileInputStream.available();
			bufferSize = Math.min(bytesAvailable, maxBufferSize);
			buffer = new byte[bufferSize];

			bytesRead = fileInputStream.read(buffer, 0, bufferSize);

			while (bytesRead > 0) {
				dos.write(buffer, 0, bufferSize);
				bytesAvailable = fileInputStream.available();
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				bytesRead = fileInputStream.read(buffer, 0, bufferSize);
			}

			dos.writeBytes(lineEnd);
			dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

			fileInputStream.close();
			dos.flush();
			dos.close();
		}

		protected void addPost(String params) throws IOException {
			checkConn();

			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			conn.setRequestProperty("Content-Length", ""
					+ Integer.toString(params.getBytes().length));
			conn.setRequestProperty("Content-Language", "pl-PL");

			conn.setUseCaches(false);
			conn.setDoOutput(true);
			conn.setDoInput(true);

			DataOutputStream wr = new DataOutputStream(
					conn.getOutputStream());
			wr.writeBytes(params);
			wr.flush();
			wr.close();
		}

		public StringBuffer outputResult() throws IOException {
			checkConn();

			InputStream is = conn.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();

			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}

			rd.close();
			is.close();

			return response;
		}

		public String getHeadItem() {
			checkConn();

			List<String> it = (List<String>) conn.getHeaderFields().values().toArray()[0];
			String head = it.get(0);

			if (head == null) {
				throw new SmsapiException("Problem with net - no exists head");
			}

			return head;
		}

		public String getHeadItem(int indexList) {
			checkConn();

			String head = "";
			Collection<List<String>> list = conn.getHeaderFields().values();

			if (indexList >= 0 && indexList <= list.size()) {
				List<String> it = (List<String>) list.toArray()[indexList];
				head = it.get(0);
			}

			if (head == null) {
				throw new SmsapiException("Problem with net - no exists head");
			}

			return head;
		}

		public void disconnect() {
			checkConn();

			conn.disconnect();
		}
	}

	@Override
	public boolean send() throws SmsapiException {
		String query = "";
		String responseHead = "";
		StringBuffer response = null;
		SenderConnection senderConnection = null;

		try {
			if (message == null) {
				throw new SmsapiException("No exists message");
			}

			String messageName = message.getClass().getSimpleName();

			if (messageName.equals(Sms.class.getSimpleName())) {
				uri = new sms().excute();
			} else if (messageName.equals(Mms.class.getSimpleName())) {
				uri = new mms().excute();
			} else if (messageName.equals(Vms.class.getSimpleName())) {
				uri = new vms().excute();
			} else if (messageName.equals(Account.class.getSimpleName())) {
				uri = new account().excute();
			} else if (messageName.equals(Phonebook.class.getSimpleName())) {
				uri = new phonebook().excute();
			} 


			if (uri == null) {
				throw new SmsapiException("Invalid URI");
			}

			switch (requestMethod) {
				case POST:
					if (file != null) {
						query = uri.toASCIIString();
					} else {
						query = uri.getScheme() + "://" + uri.getHost() + uri.getPath();
					}

					if (getProtocol().equals("http")) {
						senderConnection = new SenderConnection((HttpURLConnection) new URL(query).openConnection(), file, uri.getQuery()).initPOST();
						response = senderConnection.outputResult();
						responseHead = senderConnection.getHeadItem();
					} else {
						senderConnection = new SenderConnection((HttpsURLConnection) new URL(query).openConnection(), file, uri.getQuery()).initPOST();
						response = senderConnection.outputResult();
						responseHead = senderConnection.getHeadItem();
					}

					break;

				case GET:
					query = uri.toASCIIString();
					if (getProtocol().equals("http")) {
					  senderConnection = new SenderConnection((HttpURLConnection) new URL(query).openConnection(), null, null).initGET();
					  response = senderConnection.outputResult();
					  responseHead = senderConnection.getHeadItem();
					} else {
					  senderConnection = new SenderConnection((HttpsURLConnection) new URL(query).openConnection(), null, null).initGET();
					  response = senderConnection.outputResult();
					  responseHead = senderConnection.getHeadItem();	
					}
					break;
			}
             
			if(message instanceof Message){
				Result.renderResultMessage(results, response.toString(), responseHead, query);
			}else{
				Result.renderResulMessageSimple(results, response.toString(), responseHead, query);
			}
			
			return true;

		} catch (Exception ex) {
			Logger.getLogger(SenderHttp.class.getName()).log(Level.SEVERE, null, ex);
			throw new SmsapiException(ex.getMessage(), ex.getCause());
		} finally {
			if (senderConnection != null) {
				senderConnection.disconnect();
			}
		}
	}

	protected int getPort() {
		int port = (this.getProtocol().equals("https")) == true ? 443 : 80;

		return port;
	}

	protected String renderMessageParams(HashMap params, String skip) {

		String query = "";
		Set set = params.entrySet();
		Iterator it = set.iterator();

		while (it.hasNext()) {
			Map.Entry me = (Map.Entry) it.next();

			if (!skip.equals(me.getKey())) {
				if(me.getValue() != null){
					query += "&" + me.getKey() + "=" + me.getValue();
				}		
			}
		}

		return query;
	}

	protected String renderMessageParams(HashMap params) {
		return renderMessageParams(params, "");
	}

	protected String renderListTo(Message msg) {

		class ListToAndIdx {

			ArrayList<String> to;
			ArrayList<String> idx;
			int sizeTo;
			int sizeIdx;
			String queryTo;
			String queryIdx;
			String error;

			ListToAndIdx(ArrayList<String> to, ArrayList<String> idx) {

				this.to = to;
				this.idx = idx;
			}

			private String renderList(ArrayList<String> list, String delimiter) {
				String query = "";
				int loop = 1;
				int size = list.size();

				for (String item : list) {
					query += item;
					if (loop < size) {
						query += delimiter;
					}
					loop++;
				}

				return query;
			}

			private String renderListTo() {
				return renderList(to, ",");
			}

			private String renderListIdx() {
				return renderList(idx, "|");
			}

			public String getError() {
				return error;
			}

			@Override
			public String toString() {
				int sizeTo = to.size();
				int sizeIdx = idx.size();

				if (sizeIdx > 0) {
					if ((sizeTo != sizeIdx)) {
						error = "size idx is not equals to";
						throw new IllegalArgumentException(error);
					} else {
						return renderListTo() + "&idx=" + renderListIdx();
					}
				}

				return renderListTo();
			}
		}


		return new ListToAndIdx(msg.getTo(), msg.getIdx()).toString();
	}

	protected String renderBasicParamsToQuery(Message msg) {

		String query = "username=" + msg.getUsername() + "&password=" + msg.getPassword();

		query += (msg.getGroup() != null) ? "&group=" + msg.getGroup() : "&to=" + renderListTo(msg);

		query += (msg.getDate() != null) ? "&date=" + msg.getDate() : "";

		return query;
	}
	
	protected String renderBasicParamsToQuery(MessageInterface msg) {

		String query = "username=" + msg.getUsername() + "&password=" + msg.getPassword();

		return query;
	}

	private class sms {

		public URI excute() throws URISyntaxException, IOException {

			Sms msg = (Sms) message;
			String text = msg.getObjMessage();
			String query;
			path = "/" + msg.getPath();

			if (text instanceof String) {
				query = renderBasicParamsToQuery(msg);

				query += renderMessageParams(msg.getParams(), "message");

				query += "&message=" + text;

				return new URI(getProtocol(), null, getHost(), getPort(), path, query, null);
			}

			return null;
		}
	}

	private class mms {

		public URI excute() throws URISyntaxException {

			Mms msg = (Mms) message;
			Object text = msg.getObjMessage();
			String query;
			path = "/" + msg.getPath();

			if (text instanceof String) {

				query = renderBasicParamsToQuery(msg);

				query += renderMessageParams(msg.getParams());

				query += "&smil=" + text;

				setRequestMethod(RequestMethod.POST);

				return new URI(getProtocol(), null, getHost(), getPort(), path, query, null);
			}

			return null;
		}
	}

	private class vms {

		public URI excute() throws URISyntaxException, FileNotFoundException, IOException {

			Vms msg = (Vms) message;
			Object text = msg.getObjMessage();
			String query;
			path = "/" + msg.getPath();

			if (text instanceof File) {
				query = renderBasicParamsToQuery(msg);

				query += renderMessageParams(msg.getParams());

				file = (File) text;

				return new URI(getProtocol(), null, getHost(), getPort(), path, query, null);
			} else if (text instanceof String) {

				query = renderBasicParamsToQuery(msg);

				query += renderMessageParams(msg.getParams());

				query += "&tts=" + text;

				return new URI(getProtocol(), null, getHost(), getPort(), path, query, null);
			}

			return null;
		}
	}
	
	private class account {

		public URI excute() throws URISyntaxException, IOException {

			Account user = (Account) message;
			String query;
			path = "/" + user.getPath();

			if (user != null){
				query = renderBasicParamsToQuery(user);

				query += renderMessageParams(user.getParams());

				return new URI(getProtocol(), null, getHost(), getPort(), path, query, null);
			}

			return null;
		}
	}
	
	private class phonebook {

		public URI excute() throws URISyntaxException, IOException {

			Phonebook phonebook = (Phonebook) message;
			String query;
			path = "/" + phonebook.getPath();

			if (phonebook != null){
				query = renderBasicParamsToQuery(phonebook);

				query += renderMessageParams(phonebook.getParams());

				return new URI(getProtocol(), null, getHost(), getPort(), path, query, null);
			}

			return null;
		}
	}
}
