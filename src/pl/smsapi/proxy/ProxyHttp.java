package pl.smsapi.proxy;

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
import pl.smsapi.api.action.BaseAction;
import java.net.URI;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import pl.smsapi.exception.ProxyException;

public class ProxyHttp implements Proxy {

	private String protocol;
	private String host;
	private int port;
	private URI uri;
	private File file;
	private String query = "";
	private String responseHead = null;
	private RequestMethod requestMethod;

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

	public void setRequestMethod(RequestMethod method) {
		requestMethod = method;
	}

	public RequestMethod getRequestMethod(RequestMethod method) {
		return requestMethod;
	}

	public ProxyHttp(String baseUrl) {

		final int tmpPos = baseUrl.lastIndexOf("://");

		protocol = baseUrl.substring(0, tmpPos);
		host = baseUrl.substring(tmpPos + 3, baseUrl.length());
		port = protocol.equals("https") ? 443 : 80;
		setRequestMethod(RequestMethod.POST);
	}

	public ProxyHttp(String protocol, String host, int port) {
		this.protocol = protocol;
		this.host = host;
		this.port = port;
		setRequestMethod(RequestMethod.POST);
	}

	@Override
	public String execute(BaseAction action) throws ProxyException {

		StringBuffer response = null;
		ProxyHttpConnection pHC = null;

		try {

			uri = action.uri();
			file = action.file();

			if (uri == null) {
				throw new ProxyException("Invalid URI");
			}


			switch (requestMethod) {
				case POST:
					if (file != null) {
						query = uri.toASCIIString();
					} else {
						query = uri.getScheme() + "://" + uri.getHost() + uri.getPath();
					}


					if (getProtocol().equals("http")) {
						pHC = new ProxyHttpConnection((HttpURLConnection) new URL(query).openConnection(), file, uri.getQuery()).initPOST();
						response = pHC.outputResult();
						responseHead = pHC.getHeadItem();
					} else {
						pHC = new ProxyHttpConnection((HttpsURLConnection) new URL(query).openConnection(), file, uri.getQuery()).initPOST();
						response = pHC.outputResult();
						responseHead = pHC.getHeadItem();
					}
					break;

				case GET:
					query = uri.toASCIIString();
					if (getProtocol().equals("http")) {
						pHC = new ProxyHttpConnection((HttpURLConnection) new URL(query).openConnection(), null, null).initGET();
						response = pHC.outputResult();
						responseHead = pHC.getHeadItem();
					} else {
						pHC = new ProxyHttpConnection((HttpsURLConnection) new URL(query).openConnection(), null, null).initGET();
						response = pHC.outputResult();
						responseHead = pHC.getHeadItem();
					}
					break;
			}

			return response.toString();

		} catch (Exception ex) {
			Logger.getLogger(ProxyHttp.class.getName()).log(Level.SEVERE, null, ex);
			throw new ProxyException(ex.getMessage());
		} finally {
			if (pHC != null) {
				pHC.disconnect();
			}

		}
	}

	@Override
	public String getProtocol() {
		return protocol;
	}

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public int getPort() {
		return port;
	}

	public String getQuery() {
		return query;
	}

	public String getResponseHead() {
		return responseHead;
	}

	private class ProxyHttpConnection<T extends HttpURLConnection> {

		protected T conn;
		protected File file;
		protected String params;

		public ProxyHttpConnection(T conn, File file, String params) throws ProxyException {
			this.conn = conn;
			this.file = file;
			this.params = params;

			checkConn();

		}

		protected void checkConn() throws ProxyException {
			if (conn == null) {
				throw new ProxyException("http/https connection not exists");
			}

		}

		private void isHttps() {
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

		public ProxyHttpConnection<T> initGET() throws ProtocolException, ProxyException {
			checkConn();

			conn.setRequestMethod(RequestMethod.GET.method());

			isHttps();

			conn.setDoInput(true);

			return this;
		}

		public ProxyHttpConnection<T> initPOST() throws ProtocolException, FileNotFoundException, IOException, ProxyException {
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

		protected void addFile() throws ProtocolException, FileNotFoundException, IOException, ProxyException {

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

		protected void addPost(String params) throws IOException, ProxyException {
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

		public StringBuffer outputResult() throws IOException, ProxyException {
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

		public String getHeadItem() throws ProxyException {
			checkConn();

			List<String> it = (List<String>) conn.getHeaderFields().values().toArray()[0];
			String head = it.get(0);

			if (head == null) {
				throw new ProxyException("Problem with net - no exists head");
			}


			return head;
		}

		public String getHeadItem(int indexList) throws ProxyException {
			checkConn();

			String head = "";
			Collection<List<String>> list = conn.getHeaderFields().values();

			if (indexList >= 0 && indexList <= list.size()) {
				List<String> it = (List<String>) list.toArray()[indexList];
				head = it.get(0);
			}

			if (head == null) {
				throw new ProxyException("Problem with net - no exists head");
			}


			return head;
		}

		public void disconnect() throws ProxyException {
			checkConn();

			conn.disconnect();
		}
	}
}
