# SMSAPI JAVA Client

## How to install

### How to install using Maven Central Repository

Add following in your project **pom.xml** file:

 * in `<dependencies>` section add SMSAPI dependency:

```xml
<dependency>
    <groupId>pl.smsapi</groupId>
    <artifactId>smsapi-lib</artifactId>
    <version>3.0.0-RC15</version>
</dependency>
```

## How to use

To use *SMSAPI.PL* set proxy URL to **https://api.smsapi.pl/**. \
To use *SMSAPI.COM* set proxy URL to **https://api.smsapi.com/**. \
To use *SMSAPI.SE* or *SMSAPI.BG* set proxy URL to **https://smsapi.io/**.

### How to send SMS

```java
import pl.smsapi.OAuthClient;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.action.sms.SMSSend;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.api.response.StatusResponse;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.proxy.ProxyNative;

import java.util.Optional;

/**
 * Example output:
 *
 * Phone number: 48000000000
 * Shipment id: 6124CC3C3463351AD569127F
 * Shipment status: QUEUE
 */
public class Example {
    public static void main(String[] args) {
        try {
            String oauthToken = "00000000000000000000000000000000";
            OAuthClient client = new OAuthClient(oauthToken);
            ProxyNative proxy = new ProxyNative("https://api.smsapi.pl/");

            SmsFactory smsApi = new SmsFactory(client, proxy);

            SMSSend action = smsApi.actionSend("000000000", "test message");

            StatusResponse result = action.execute();

            MessageResponse status = result.getList().get(0);

            System.out.println("Phone number: " + status.getNumber());
            System.out.println("Shipment id: " + status.getId());
            System.out.println("Shipment status: " + status.getStatus());

        } catch (SmsapiException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
```

### How to send SMS to many recipients

```java
import pl.smsapi.OAuthClient;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.action.sms.SMSSend;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.api.response.StatusResponse;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.proxy.ProxyNative;

/**
 * Example output:
 *
 * Phone number: 48000000000
 * Shipment id: 6124CFBF3463350568CC428E
 * Shipment status: QUEUE
 * Phone number: 48000000001
 * Shipment id: 6124CFBF3463350568CC428F
 * Shipment status: QUEUE
 */
public class Example {
    public static void main(String[] args) {
        try {
            String oauthToken = "00000000000000000000000000000000";
            OAuthClient client = new OAuthClient(oauthToken);
            ProxyNative proxy = new ProxyNative("https://api.smsapi.pl/");

            SmsFactory smsApi = new SmsFactory(client, proxy);

            String[] to = {"000000000", "000000001"};

            SMSSend action = smsApi.actionSend(to, "test message");

            StatusResponse result = action.execute();

            for (MessageResponse status : result.getList() ) {
                System.out.println("Phone number: " + status.getNumber());
                System.out.println("Shipment id: " + status.getId());
                System.out.println("Shipment status: " + status.getStatus());
            }
        } catch (SmsapiException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}

```

### How to get shipment status

```java
import pl.smsapi.OAuthClient;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.action.sms.SMSGet;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.api.response.StatusResponse;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.proxy.ProxyNative;

import java.util.Optional;

/**
 * Example output:
 *
 * Phone number: 48500000000
 * Shipment id: 6124D61434633525780D4F3B
 * Shipment status: SENT
 */
public class Example2ci {
    public static void main(String[] args) {
        try {
            String oauthToken = "00000000000000000000000000000000";
            OAuthClient client = new OAuthClient(oauthToken);
            ProxyNative proxy = new ProxyNative("https://api.smsapi.pl/");

            SmsFactory smsApi = new SmsFactory(client, proxy);

            String shipmentId = "6124D61434633525780D4F3B";
            SMSGet getAction = smsApi.actionGet(shipmentId);

            StatusResponse shipmentStatus = getAction.execute();
            MessageResponse statusAfterGet = shipmentStatus.getList().get(0);

            System.out.println("Phone number: " + statusAfterGet.getNumber());
            System.out.println("Shipment id: " + statusAfterGet.getId());
            System.out.println("Shipment status: " + statusAfterGet.getStatus());
        } catch (SmsapiException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
```
