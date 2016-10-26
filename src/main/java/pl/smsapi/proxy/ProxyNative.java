package pl.smsapi.proxy;

import pl.smsapi.api.authenticationStrategy.AuthenticationStrategy;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class ProxyNative implements Proxy {

    private String baseUrl;
    private AuthenticationStrategy authenticationStrategy;

    public ProxyNative(String url) {

        this.baseUrl = url;
        this.authenticationStrategy = null;
    }

    public ProxyNative(String url, AuthenticationStrategy authenticationStrategy) {
        this.baseUrl = url;
        this.authenticationStrategy = authenticationStrategy;

    }

    public String execute(String endpoint, Map<String, ?> data, Map<String, InputStream> files) throws Exception {
        return execute(endpoint, data, files, "GET");
    }

    /**
     * Execute
     * <p/>
     * Disable ssl hostname verification
     * <p/>
     * <code>
     * HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {
     * public boolean verify(StringUtils hostname, javax.net.ssl.SSLSession sslSession) {
     * return true;
     * }
     * });
     * </code>
     */
    public String execute(String endpoint, Map<String, ?> data, Map<String, InputStream> files, String httpMethod) throws Exception {

        URL url = new URL(baseUrl + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("User-Agent", "smsapi-lib/java " + System.getProperty("os.name"));
        connection.setRequestProperty("Accept", "*");
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.setRequestMethod(httpMethod);

        if (authenticationStrategy != null) {
            authenticationStrategy.applyAuthentication(connection, data);
        }

        byte[] dataBytes;

        if (files == null || files.isEmpty()) {

            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            dataBytes = createDataStream(data);
        } else {

            String boundary = generateBoundary();
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            dataBytes = createMultipartDataStream(boundary, data, files);
        }

        connection.setRequestProperty("Content-Length", Integer.toString(dataBytes.length));

        if (dataBytes.length != 0) {
            connection.getOutputStream().write(dataBytes);
            connection.getOutputStream().flush();
            connection.getOutputStream().close();
        }

        StringBuilder response = new StringBuilder();
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;
        while ((line = inputReader.readLine()) != null) {
            response.append(line);
        }

        inputReader.close();

        return response.toString();
    }

    private String generateBoundary() {
        Random generator = new Random();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        return "SMSAPI-" + format.format(new Date()) + generator.nextInt() + "-boundary";
    }

    protected byte[] createDataStream(Map<String, ?> data) throws IOException {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        Iterator<? extends Map.Entry<String, ?>> entryIterator = data.entrySet().iterator();

        while (entryIterator.hasNext()) {

            Map.Entry<String, ?> entry = entryIterator.next();

            String record = encodeUrlParam(entry.getKey()) + "=" + encodeUrlParam(entry.getValue().toString());
            stream.write(record.getBytes());

            if (entryIterator.hasNext()) {
                stream.write("&".getBytes());
            }
        }

        return stream.toByteArray();
    }

    protected byte[] createMultipartDataStream(String boundary, Map<String, ?> data, Map<String, InputStream> files) throws IOException {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        for (Map.Entry<String, ?> entry : data.entrySet()) {

            String paramHeader = "\r\n--" + boundary + "\r\nContent-Disposition: form-data; name=\"" + entry.getKey() + "\";\r\n\r\n";
            stream.write(paramHeader.getBytes());
            stream.write(entry.getValue().toString().getBytes());
        }

        for (Map.Entry<String, InputStream> entry : files.entrySet()) {

            String fileHeader =
                    "\r\n--" + boundary +
                            "\r\nContent-Disposition: form-data; name=\"" + entry.getKey() + "\"; filename=\"" + entry.getKey() + "\"" +
                            "\r\nContent-Type: application/octet-stream\r\n\r\n";

            stream.write(fileHeader.getBytes());

            InputStream inputStream = entry.getValue();
            byte[] buffer = new byte[4096];
            int n;
            while ((n = inputStream.read(buffer)) > 0) {
                stream.write(buffer, 0, n);
            }

            inputStream.close();
        }

        byte[] footBytes = ("\r\n--" + boundary + "--").getBytes();
        stream.write(footBytes, 0, footBytes.length);

        return stream.toByteArray();
    }

    protected String encodeUrlParam(String s) throws UnsupportedEncodingException {
        return URLEncoder.encode(s, "UTF-8");
    }
}
