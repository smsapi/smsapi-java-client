java-client
===========

Klient napisany w języku Java, pozwalający na wysyłanie wiadomości SMS, MMS, VMS oraz zarządzanie kontem w serwisie SMSAPI.pl

wymagane dołaczenie bibloteki:
https://github.com/douglascrockford/JSON-java

Przykład wysyłki:
```java
package com.example.smsapi_java_client_example;

import pl.smsapi.BasicAuthClient;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.action.sms.SMSSend;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.api.response.StatusResponse;
import pl.smsapi.exception.ClientException;
import pl.smsapi.exception.SmsapiException;

public class Main {
    public static void main(String args[]) {
        try {
            String passwordHash = "00000000000000000000000000000000";
            BasicAuthClient client = new BasicAuthClient("username", passwordHash);

            SmsFactory smsApi = new SmsFactory(client);
            String phoneNumber = "000000000";
            SMSSend action = smsApi.actionSend()
                    .setText("test")
                    .setTo(phoneNumber);

            StatusResponse result = action.execute();

            for (MessageResponse status : result.getList() ) {
                System.out.println(status.getNumber() + " " + status.getStatus());
            }
        } catch (ClientException e) {
	/**
     	* 101 Niepoprawne lub brak danych autoryzacji.
     	* 102 Nieprawidłowy login lub hasło
     	* 103 Brak punków dla tego użytkownika
     	* 105 Błędny adres IP
     	* 110 Usługa nie jest dostępna na danym koncie
     	* 1000 Akcja dostępna tylko dla użytkownika głównego
     	* 1001 Nieprawidłowa akcja
     	*/
            e.printStackTrace();
        } catch (SmsapiException e) {
            e.printStackTrace();
        }
    }
}
```


## JAVADOC
[2.3](http://labs.smsapi.com/docs/javadoc/pl/smsapi/smsapi-lib/2.3/)

[2.2](http://labs.smsapi.com/docs/javadoc/pl/smsapi/smsapi-lib/2.2/)

[2.1](http://labs.smsapi.com/docs/javadoc/pl/smsapi/smsapi-lib/2.1/)

[2.0](http://labs.smsapi.com/docs/javadoc/pl/smsapi/smsapi-lib/2.0/)

## MAVEN

W pliku **pom.xml** należy dopisać:

```xml
    <repositories>
        <repository>
            <releases>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
                <checksumPolicy>fail</checksumPolicy>
            </releases>
            <id>smsapi</id>
            <name>smsapi</name>
            <url>http://labs.smsapi.com/maven/</url>
            <layout>default</layout>
        </repository>
    </repositories>
    <dependencies>
        <dependency>
            <groupId>pl.smsapi</groupId>
            <artifactId>smsapi-lib</artifactId>
            <version>2.4</version>
        </dependency>
    </dependencies>
</project>
```

## LICENSE
[Apache 2.0 License](https://github.com/smsapi/smsapi-java-client/blob/master/LICENSE)
