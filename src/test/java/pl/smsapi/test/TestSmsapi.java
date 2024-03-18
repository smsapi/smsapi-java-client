package pl.smsapi.test;

import org.junit.Ignore;
import pl.smsapi.Client;
import pl.smsapi.OAuthClient;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.exception.ClientException;
import pl.smsapi.proxy.Proxy;
import pl.smsapi.proxy.ProxyNative;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Ignore
public class TestSmsapi {
    protected String fileToIds = "_ids_test.txt";

    protected Client getAuthorizationClient() throws ClientException {
        return new OAuthClient("<token>");
    }

    protected String[] readIds() {
        BufferedReader br = null;
        StringBuilder result = new StringBuilder();

        try {

            String sCurrentLine;
            br = new BufferedReader(new FileReader(fileToIds));

            while ((sCurrentLine = br.readLine()) != null) {
                result.append(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return result.toString().split(",");
    }

    protected void writeIds(String[] ids) {
        StringBuilder content = new StringBuilder();

        try {

            int i = 1;
            for (String item : ids) {
                content.append(item);
                if (i < ids.length) {
                    content.append(",");
                }
                i++;
            }

            File file = new File(fileToIds);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content.toString());
            bw.close();

            System.out.println("Wrote ids");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void renderMessageItem(MessageResponse item) {
        if (item != null) {

            System.out.println("ID: " + item.getId()
                    + " Number: " + item.getNumber()
                    + " Points:" + item.getPoints()
                    + " Status:" + item.getStatus()
                    + " IDx: " + item.getIdx());

        } else {
            System.out.println("Item is null");
        }

    }

    protected Proxy getProxy() {
        return new ProxyNative("http://api.smsapi.pl/");
    }

    public static String MD5Digest(String str) throws ClientException {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(str.getBytes());

            byte[] byteData = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : byteData) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException ex) {
            throw new ClientException(ex);
        }
    }
}
