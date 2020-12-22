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

Example of using library:
```java
package com.example.smsapi_java_client_example;

import pl.smsapi.OAuthClient;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.action.sms.SMSSend;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.api.response.StatusResponse;
import pl.smsapi.exception.ClientException;
import pl.smsapi.exception.SmsapiException;

public class Main {
    final static String urlForPlSmsapi = "http://api.smsapi.pl/";
    final static String urlForComSmsapi = "http://api.smsapi.com/";
    
    public static void main(String args[]) {
        try {
            String oauthToken = "00000000000000000000000000000000";
            OAuthClient client = new OAuthClient(oauthToken);
	    ProxyNative proxyToPlOrComSmsapi = new ProxyNative(urlForPlSmsapi);

            SmsFactory smsApi = new SmsFactory(client, proxyToPlOrComSmsapi);
            String phoneNumber = "000000000";
            SMSSend action = smsApi.actionSend()
                    .setText("test")
                    .setTo(phoneNumber);

            StatusResponse result = action.execute();

            for (MessageResponse status : result.getList() ) {
                System.out.println(status.getNumber() + " " + status.getStatus());
            }
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (SmsapiException e) {
            e.printStackTrace();
        }
    }
}
```

### How to use *SMSAPI.PL* client
```java
	ProxyNative proxyToPlOrComSmsapi = new ProxyNative(urlForPlSmsapi);
```
### How to use *SMSAPI.COM* client
```java
	ProxyNative proxyToPlOrComSmsapi = new ProxyNative(urlForComSmsapi);
```

## JAVADOC
[2.3](http://labs.smsapi.com/docs/javadoc/pl/smsapi/smsapi-lib/2.3/)

[2.2](http://labs.smsapi.com/docs/javadoc/pl/smsapi/smsapi-lib/2.2/)

[2.1](http://labs.smsapi.com/docs/javadoc/pl/smsapi/smsapi-lib/2.1/)

[2.0](http://labs.smsapi.com/docs/javadoc/pl/smsapi/smsapi-lib/2.0/)

## LICENSE
[Apache 2.0 License](https://github.com/smsapi/smsapi-java-client/blob/master/LICENSE)
