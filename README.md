java-client
===========

Klient napisany w języku Java, pozwalający na wysyłanie wiadomości SMS, MMS, VMS oraz zarządzanie kontem w serwisie SMSAPI.pl

wymagane dołaczenie bibloteki:
https://github.com/douglascrockford/JSON-java

Przykład wysyłki:
```java
try {

    Client client = new Client("login");
    client.setPasswordHash("passwordHash");

    SmsFactory smsApi = new SmsFactory(client);
    Send action = smsApi.actionSend()
            .setText("test message")
            .setTo("xxxyyyzzz")
			.setSender("Info"); //Pole nadawcy lub typ wiadomość 'ECO', '2Way'

    StatusResponse result = action.execute();

    for(MessageResponse status : result.getList() ) {

        System.out.println( status.getNumber() + " " + status.getStatus() );
    }

} catch( ActionException e) {
    /**
     * Błędy związane z akcją (z wyłączeniem błędów 101,102,103,105,110,1000,1001 i 8,666,999,201)
     * http://www.smsapi.pl/sms-api/kody-bledow
     */
    System.out.println(e.getMessage());
} catch( ClientException e) {
    /**
     * 101 Niepoprawne lub brak danych autoryzacji.
     * 102 Nieprawidłowy login lub hasło
     * 103 Brak punków dla tego użytkownika
     * 105 Błędny adres IP
     * 110 Usługa nie jest dostępna na danym koncie
     * 1000 Akcja dostępna tylko dla użytkownika głównego
     * 1001 Nieprawidłowa akcja
     */
    System.out.println(e.getMessage());
} catch( HostException e) {
    /* błąd po stronie servera lub problem z parsowaniem danych
     *
     * 8 - Błąd w odwołaniu
     * 666 - Wewnętrzny błąd systemu
     * 999 - Wewnętrzny błąd systemu
     * 201 - Wewnętrzny błąd systemu
     */
    System.out.println(e.getMessage());
} catch( SmsapiException e ) {
    System.out.println(e.getMessage());
}
```

## LICENSE
[Apache 2.0 License](https://github.com/smsapi/smsapi-java-client/blob/master/LICENSE)