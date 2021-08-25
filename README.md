# SMSAPI JAVA Client

## How to install

JARs, POMs and docs available are at https://labs.smsapi.com/maven.

Library https://github.com/douglascrockford/JSON-java is required.

### How to install using Maven

Add following in your project **pom.xml** file:

 * in `<repositories>` section add SMSAPI repository:

```xml
<repository>
    <releases>
        <enabled>true</enabled>
        <updatePolicy>always</updatePolicy>
        <checksumPolicy>fail</checksumPolicy>
    </releases>
    <id>smsapi</id>
    <name>smsapi</name>
    <url>https://labs.smsapi.com/maven/</url>
    <layout>default</layout>
</repository>
```

 * in `<dependencies>` section add SMSAPI dependency:

```xml
<dependency>
    <groupId>pl.smsapi</groupId>
    <artifactId>smsapi-lib</artifactId>
    <version>2.4</version>
</dependency>
```

### How to install using Gradle

Add following in your project **build.gradle** file:

 * in `plugins` section:

```
id 'java'
id 'java-library'
```

 * in `repositories` section:

```
mavenCentral()
maven {
    url 'https://labs.smsapi.com/maven/'
}
```

 * in `dependencies` section:

```
api 'pl.smsapi:smsapi-lib:2.5'
```

## How to use

To use SMSAPI.PL set proxy URL to **https//api.smsapi.pl/**.
To use SMSAPI.COM set proxy URL to **https://api.smsapi.com/**.

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

            SMSSend action = smsApi.actionSend()
                    .setTo("000000000")
                    .setText("test");

            StatusResponse result = action.execute();

            Optional<MessageResponse> status = result.getList().stream().findFirst();
            if (status.isEmpty()) {
                throw new RuntimeException();
            }

            System.out.println("Phone number: " + status.get().getNumber());
            System.out.println("Shipment id: " + status.get().getId());
            System.out.println("Shipment status: " + status.get().getStatus());

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

            SMSSend action = smsApi.actionSend()
                    .setTo(to)
                    .setText("test");

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
            Optional<MessageResponse> statusAfterGet = shipmentStatus.getList().stream().findFirst();
            if (statusAfterGet.isEmpty()) {
                throw new RuntimeException();
            }

            System.out.println("Phone number: " + statusAfterGet.get().getNumber());
            System.out.println("Shipment id: " + statusAfterGet.get().getId());
            System.out.println("Shipment status: " + statusAfterGet.get().getStatus());
        } catch (SmsapiException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
```

## JAVADOC
[2.3](http://labs.smsapi.com/docs/javadoc/pl/smsapi/smsapi-lib/2.3/)

[2.2](http://labs.smsapi.com/docs/javadoc/pl/smsapi/smsapi-lib/2.2/)

[2.1](http://labs.smsapi.com/docs/javadoc/pl/smsapi/smsapi-lib/2.1/)

[2.0](http://labs.smsapi.com/docs/javadoc/pl/smsapi/smsapi-lib/2.0/)

## LICENSE
[Apache 2.0 License](https://github.com/smsapi/smsapi-java-client/blob/master/LICENSE)
