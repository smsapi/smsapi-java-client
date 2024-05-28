package pl.smsapi.proxy;

import pl.smsapi.api.authenticationStrategy.AuthenticationStrategy;
import pl.smsapi.exception.ProxyException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProxyNative implements Proxy {

    private String baseUrl;

    public ProxyNative(String url) {

        this.baseUrl = url;
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
    public String execute(String endpoint, Map<String, String> data, Map<String, InputStream> files, String httpMethod, AuthenticationStrategy authenticationStrategy) throws Exception {
        URL url = createUrl(httpMethod, endpoint, data);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("User-Agent", "smsapi-lib/java " + System.getProperty("os.name"));
        connection.setRequestProperty("Accept", "*");
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.setRequestMethod(httpMethod);

        String authenticationHeader = authenticationStrategy.getAuthenticationHeader();

        if (authenticationHeader != null) {
            connection.setRequestProperty("Authorization", authenticationHeader);
        }

        if (httpMethod.equals("GET")) {
            data.clear();
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


        String response;

        try {
            response = readResponsePayload(connection.getInputStream());
        } catch (FileNotFoundException notFound) {
            response = readResponsePayload(connection.getErrorStream());
        } catch (IOException clientErrorOrServerError) {
            response = readResponsePayload(connection.getErrorStream());
        }

        return response;
    }

    private String readResponsePayload(InputStream inputStream) throws ProxyException {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder response = new StringBuilder();
        String line;

        try {
            while ((line = inputReader.readLine()) != null) {
                response.append(line);
            }
            inputReader.close();
        } catch (IOException readerException) {
            throw new ProxyException("Cannot read response input stream", readerException);
        }

        return response.toString();
    }

    private String generateBoundary() {
        Random generator = new Random();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        return "SMSAPI-" + format.format(new Date()) + generator.nextInt() + "-boundary";
    }

    protected URL createUrl(String httpMethod, String endpoint, Map<String, ?> data) throws UnsupportedEncodingException, MalformedURLException {
        String urlString = baseUrl + endpoint;

        if (httpMethod.equals("GET") && !data.isEmpty()) {
            String queryString = createQueryString(data);

            if (urlString.contains("?")) {
                urlString = urlString + '&' + queryString;
            } else {
                urlString = urlString + '?' + queryString;
            }
        }

        return new URL(urlString);
    }

    protected String createQueryString(Map<String, ?> data) throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder();

        Iterator<? extends Map.Entry<String, ?>> entryIterator = data.entrySet().iterator();

        while (entryIterator.hasNext()) {

            Map.Entry<String, ?> entry = entryIterator.next();

            String record = encodeUrlParam(entry.getKey()) + "=" + encodeUrlParam(entry.getValue().toString());
            stringBuilder.append(record);

            if (entryIterator.hasNext()) {
                stringBuilder.append('&');
            }
        }

        return stringBuilder.toString();
    }

    protected byte[] createDataStream(Map<String, ?> data) throws IOException {
        String queryString = createQueryString(data);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        stream.write(queryString.getBytes());

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
