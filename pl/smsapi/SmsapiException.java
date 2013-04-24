package pl.smsapi;

public final class SmsapiException extends RuntimeException {

	private int code;
	
	private static String errorDefult = "999";

	public int getCode() {
		return code;
	}

	public SmsapiException(final String message) {
		super(message);
	}

	public SmsapiException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public SmsapiException(final String message, final int code) {
		super(message);
		this.code = code;
	}
	
	public static Error createError(String number) {
		return new Error(number);
	}

	public static Error createError(String number, String message) {
		return new Error(number, message);
	}
	
	public static Error createError(String[] infoMsg)
	{
		if(infoMsg.length == 2){
			return new Error(infoMsg[1]);
		}
		
		return new Error(errorDefult);
	}

	public static class Error {

		private int number;
		private String message;

		public Error(String number) {
			
			this.number = Integer.parseInt(number.trim());
			this.message = new CodeResponse(this.number).getMessage();
		}

		public Error(String number, String message) {
			this(number);
			this.message = message;
		}

		public int getNumber() {
			return number;
		}

		public String getMessage() {
			return message;
		}
		
		@Override
		public String toString() {
			return Integer.toString(number) + ":" + message;
		}
		
}

static class CodeResponse {
	
	private Integer code;
	
	public CodeResponse(Integer code)
	{
		this.code = code;
	}
	
	public String getMessage()
	{
			switch (code) {
				case 8:
					return "Błąd w odwołaniu (Prosimy zgłośić)";
				case 11:
					return "Zbyt długa lub brak wiadomości lub ustawiono parametr nounicode i pojawiły się znaki specjalne w wiadomości. Dla wysyłki VMS błąd oznacz brak pliku WAV lub treści TTS.";
				case 12:
					return "Wiadomość składa się z większej ilości części niż określono w parametrze &max_parts";
				case 13:
					return "Nieprawidłowy numer odbiorcy";
				case 14:
					return "Nieprawidłowe pole nadawcy";
				case 17:
					return "Nie można wysłać FLASH ze znakami specjalnymi";
				case 18:
					return "Nieprawidłowa liczba parametrów";
				case 19:
					return "Za dużo wiadomości w jednym odwołaniu";
				case 20:
					return "Nieprawidłowa liczba parametrów IDX";
				case 21:
					return "Wiadomość MMS ma za duży rozmiar (maksymalnie 300kB)";
				case 22:
					return "Błędny format SMIL";
				case 23:
					return "Błąd pobierania pliku dla wiadomości MMS lub VMS";
				case 24:
					return "Błędny format pobieranego pliku";
				case 25:
					return "Parametry &normalize oraz &datacoding nie mogą być używane jednocześnie";
				case 26:
					return "Za długi temat wiadomości. Temat może zawierać makstymalnie 30 znaków";
				case 30:
					return "Brak parametru UDH jak podany jest datacoding=bin";
				case 31:
					return "Błąd konwersji TTS";
				case 32:
					return "Nie można wysyłać wiadomości Eco, MMS i VMS na zagraniczne numery lub wysyłka wiadomości na numery zagraniczne została zablokowana (w przypadku wiadomości Pro";
				case 33:
					return "Brak poprawnych numerów";
				case 36:
					return "Nie można wysyłać wiadomości binarnych z ustawioną stopką";
				case 40:
					return "Brak grupy o podanej nazwie";
				case 41:
					return "Wybrana grupa jest pusta (brak kontaktów w grupie)";
				case 50:
					return "Nie można zaplanować wysyłki na więcej niż 3 miesiące w przyszłość";
				case 51:
					return "Ustawiono błędną godzinę wysyłki, wiadomość VMS mogą być wysyłane tylko pomiędzy godzinami 8 a 22 ub ustawiono kombinację parametrów try i interval powodującą możliwość próby połączenia po godzinie 22";
				case 52:
					return "Za dużo prób wysyłki wiadomości do jednego numeru (maksymalnie 10 prób w przeciągu 60sek do jednego numeru";
				case 53:
					return "Nieunikalny parametr idx. Została już przyjęta wiadomość z identyczną wartością parametru idx przy wykorzystaniu parametru &check_idx=1";
				case 54:
					return "Błędny format daty. Ustawiono sprawdzanie poprawności daty &date_validate=1";
				case 55:
					return "Brak numerów stacjonarnych w wysyłce i ustawiony parametr skip_gsm";
				case 56:
					return "Różnica pomiędzy datą wysyłki a datą wygaśnięcia nie może być mniejsza niż 1 godzina i większa niż 12 godzin";
				case 101:
					return "Niepoprawne lub brak danych autoryzacji. UWAGA! Hasło do API może być inne niż hasło do Panelu Klienta";
				case 102:
					return "Nieprawidłowy login lub hasło";
				case 103:
					return "Brak punków dla tego użytkownika";
				case 104:
					return "Brak szablonu";
				case 105:
					return "Błędny adres IP (włączony filtr IP dla interfejsu API)";
				case 110:
					return "Usługa (SMS, MMS, VMS lub HLR) nie jest dostępna na danym koncie";
				case 200:
					return "Nieudana próba wysłania wiadomości, prosimy ponowić odwołanie";
				case 201:
					return "Wewnętrzny błąd systemu (prosimy zgłosić)";
				case 202:
					return "Zbyt duża ilość jednoczesnych odwołań do serwisu, wiadomość nie została wysłana (prosimy odwołać się ponownie)";
				case 300:
					return "Nieprawidłowa wartość pola points (przy użyciu pola points jest wymagana wartość 1)";
				case 301:
					return "ID wiadomości nie istnieje";
				case 400:
					return "Nieprawidłowy ID statusu wiadomości";
				case 401:
					return "Błędny numer ID lub raport wygasł";
				case 402:
					return "Wiadomość niedostarczona z powodu zbyt długiego czasu niedostępność numeru ";
				case 403:
					return "Wiadomość została wysłana ale operator nie zwrócił jeszcze raportu doręczenia";
				case 404:
					return "Wiadomość dotarła do odbiorcy";
				case 405:
					return "Wiadomość niedostarczona (np.: błędny numer, numer niedostępny)";
				case 406:
					return "Błąd podczas wysyłki wiadomości - prosimy zgłosić";
				case 408:
					return "Brak raportu doręczenia dla wiadomości (wiadomość doręczona lub brak możliwości doręczenia)";
				case 409:
					return "Wiadomość czeka w kolejce na wysyłkę";
				case 410:
					return "Wiadomość przyjęta przez operatora";
				case 411:
					return "Wykonana była próba połączenia która nie została odebrana, połączenie zostanie ponowione.";
				case 999:
					return "Wewnętrzny błąd systemu (prosimy zgłosić)";
				case 1000:
					return "Akcja dostępna tylko dla użytkownika głównego";
				case 1001:
					return "Nieprawidłowa akcja (oczekiwane jedna z add_user, set_user, get_user,credits)";
				case 1010:
					return "Błąd dodawania podużytkownika";
				case 1020:
					return "Błąd edycji konta podużytkownika";
				case 1021:
					return "Brak danych do edycji, przynajmniej jeden parametr musi być edytowany";
				case 1030:
					return "Błąd pobierania danych użytkownika";
				case 1032:
					return "Nie istnieje podużytkownik o podanej nazwie dla danego użytkownika głównego";
				case 1100:
					return "Błąd danych podużytkownika";
				case 1110:
					return "Błędna nazwa tworzonego podużytkownika";
				case 1111:
					return "Nie podano nazwy tworzonego konta podużytkownika";
				case 1112:
					return "Nazwa konta podużytkownika za krótka (minimum 3 znaki";
				case 1113:
					return "Nazwa konta podużytkownika za długa, łączna długość nazwy podużytkownika wraz z prefiksem użytkownika głównego może mieć maksymalnie 32 znaki";
				case 1114:
					return "W nazwie podużytkownika pojawiły się nidozwolone znaki, dozwolone są litery [A – Z], cyfry [0 – 9] oraz znaki @, -, _ i";
				case 1115:
					return "Istnieje już podużytkownik o podanej nazwie";
				case 1120:
					return "Błąd hasła dla tworzonego konta podużytkownika";
				case 1121:
					return "Hasło dla tworzonego konta podużytkownika za krótkie";
				case 1122:
					return "Hasło dla tworzonego konta podużytkownika za długie";
				case 1123:
					return "Hasło powinno być zakodowane w MD5";
				case 1130:
					return "Błąd limitu punktów przydzielanego podużytkownikowi";
				case 1131:
					return "Parametr limit powinno zawierać wartość numeryczną";
				case 1140:
					return "Błąd limitu miesięcznego punktów przydzielanego podużytkownikowi";
				case 1141:
					return "Parametr month_limit powinno zawierać wartość numeryczną";
				case 1150:
					return "Błędna wartość parametru senders, dopuszczalne wartości dla tego parametru to 0 lub 1";
				case 1160:
					return "Błędna wartość parametru phonebook, dopuszczalne wartości dla tego parametru to 0 lub 1";
				case 1170:
					return "Błędna wartość parametru active, dopuszczalne wartości dla tego parametru to 0 lub 1";
				case 1180:
					return "Błąd parametru info";
				case 1183:
					return "Zawartość parametru info jest za długa";
				case 1190:
					return "Błąd hasła do interfejsu API dla konta podużytkownika";
				case 1192:
					return "Błędna długość hasła do interfejsu API dla konta podużytkownika (hasło zakodowane w md5 powinno mieć 32 znaki)";
				case 1193:
					return "Hasło do interfejsu powinno zostać podane w formie zakodowanej w md5";
				case 2001:
					return "Nieprawidłowa akcja";
				case 2010:
					return "Błąd dodawania pola nadawcy";
				case 2030:
					return "Błąd sprawdzania statusu pola nadawcy";
				case 2031:
					return "Nie istnieje pole nadawcy o podanej nazwie";
				case 2060:
					return "Błąd dodawania domyślnego pola nadawcy";
				case 2061:
					return "Pole nadawcy musi być aktywne, żeby ustawić je jako domyślne";
				case 2062:
					return "Pole nadawcy już jest ustawione jako domyślne";
				case 2100:
					return "Błąd przesyłanych danych";
				case 2110:
					return "Błąd nazwy pola nadawcy";
				case 2111:
					return "Brak nazwy dodawanego pola nadawcy (parametr &add jest pusty)";
				case 2112:
					return "Niepoprawna nazwa pola nadawcy (np. numer telefonu, zawierająca polskie i/lub specjalne znaki lub za długie), pole nadawcy może mieć maksymalnie 11 znaków, dopuszczalne znaki: a-z A-Z 0-9 - . [spacja]";
				case 2115:
					return "Pole o podanej nazwie już istnieje";
			}

			return "";
		}
	}
}
