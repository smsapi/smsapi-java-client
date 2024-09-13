package pl.smsapi.test;

import org.json.JSONObject;
import org.junit.Before;
import pl.smsapi.Client;
import pl.smsapi.OAuthClient;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.proxy.Proxy;
import pl.smsapi.proxy.ProxyNative;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestSmsapi {
    protected String fileToIds = "_ids_test.txt";

    protected Proxy proxy;
    protected Client client;

    @Before
    public void setUp() {
        String config;
        Path configPath = Paths.get("src", "test", "config", "config.json");
        File configFile = configPath.toFile();

        try {
            FileInputStream fis = new FileInputStream(configFile);
            byte[] data = new byte[(int) configFile.length()];
            fis.read(data);
            fis.close();
            config = new String(data, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Cannot read test config file", e);
        }

        JSONObject configJson = new JSONObject(config);

        proxy = new ProxyNative(configJson.getJSONObject("api").getString("uri"));
        client = new OAuthClient(configJson.getJSONObject("api").getString("token"));
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
}
