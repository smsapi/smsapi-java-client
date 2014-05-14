package pl.smsapi.proxy;

import pl.smsapi.api.action.BaseAction;
import pl.smsapi.exception.ProxyException;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ProxyNative implements Proxy {

    private String baseUrl;

    public ProxyNative(String url) {

        baseUrl = url;
    }

    public String execute(String endpoint, Map<String, Object> data)
    {
        Map<String, InputStream> files = new HashMap<String, InputStream>();

        try {
            return execute(endpoint, data, files);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String execute(String endpoint, Map<String,? extends Object> data, Map<String, InputStream> files) throws Exception {

        URL url = new URL(baseUrl + endpoint);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "smsapi-lib/java "+System.getProperty("os.name"));
        connection.setUseCaches(false);
        connection.setDoOutput(true);

        /*
        if (url.getProtocol().equals("https")) {
            HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {
                public boolean verify(StringUtils hostname, javax.net.ssl.SSLSession sslSession) {
                    return true;
                }
            });
        }
        */

        if( files == null || files.isEmpty() ) {

            byte[] dataBytes = createDataStream(data);

            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", Integer.toString(dataBytes.length));
            connection.getOutputStream().write(dataBytes);
        } else {
            throw new RuntimeException("");
        }


        connection.getOutputStream().flush();
        connection.getOutputStream().close();

        StringBuilder response = new StringBuilder();
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;
        while ((line = inputReader.readLine()) != null) {
            response.append(line);
        }

        inputReader.close();

        return response.toString();
    }

    protected byte[] createDataStream(Map<String,? extends Object> data) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        Iterator<? extends Map.Entry<String, ?>> entryIterator = data.entrySet().iterator();

        while (entryIterator.hasNext()) {

            Map.Entry<String, ?> entry = entryIterator.next();

            String record = encodeUrlParam(entry.getKey()) + "=" + encodeUrlParam( entry.getValue().toString() );
            stream.write(record.getBytes());

            if (entryIterator.hasNext()) {
                stream.write("&".getBytes());
            }
        }

        return stream.toByteArray();
    }

    protected String encodeUrlParam(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }


/* temporary */

    @Override
    public String execute(BaseAction action) throws ProxyException {

        return null;
    }

    @Override
    public String getProtocol() {
        return null;
    }

    @Override
    public String getHost() {
        return null;
    }

    @Override
    public int getPort() {
        return 0;
    }

    @Override
    public String getPath() {
        return null;
    }
}
