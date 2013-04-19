package pl.smsapi.message;

import pl.smsapi.SmsapiException;
import pl.smsapi.sender.Sender;
import pl.smsapi.sender.SenderHttp;
import pl.smsapi.sender.SenderHttp.RequestMethod;

public final class Sms extends Message {

	private Template tpl;

	public Sms() {
		setPath("sms.do");
	}

	/**
	 * Wstawia object który będzie obsługiwał wysyłkę wiadomości
	 *
	 * @param sender Sender
	 */
	public Sms(Sender sender) {
		this();
		setSender(sender);
	}

	@Override
	public String getObjMessage() {
		return params.get("message");
	}

	/**
	 * Uruchamia wysyłkę wiadomości w smsapi.pl
	 *
	 * @return bool
	 * @param requestMethod enum SenderHttp.RequestMethod
	 */
	public boolean send(RequestMethod requestMethod) {
		
		if (getSender() == null) {
			throw new SmsapiException("No exists sender");
		}

		getSender().setMethod(requestMethod);

		boolean result = super.send();

		return result;
	}

	/**
	 * Uruchamia wysyłkę wiadomości w smsapi.pl
	 *
	 * @return bool
	 * @param requestMethod String "POST" lub "GET"
	 */
	public boolean send(String requestMethod) {

		if (requestMethod.equals("post") || requestMethod.equals("POST")) {
			getSender().setMethod(SenderHttp.RequestMethod.POST);
		} else if (requestMethod.equals("get") || requestMethod.equals("GET")) {
			getSender().setMethod(SenderHttp.RequestMethod.GET);
		}

		boolean result = super.send();

		return result;
	}

	/**
	 * Zwraca parametr message
	 *
	 * @return String
	 */
	public String getMessage() {
		return params.get("message");
	}

	/**
	 * ---Obowiazkowy--- Maksymalna długość wiadomości wynosi 918 znaków (lub 402 ze znakami specjalnymi) i jest wysłana
	 * jako 6 połączonych SMS-ów
	 *
	 * @param message String
	 */
	public void setMessage(String message) {
		params.put("message", message);
	}

	/**
	 * Zwraca parametr from
	 *
	 * @return String
	 */
	public String getFrom() {
		return params.get("from");
	}

	/**
	 * Nazwa nadawcy wiadomości. Pozostawienie pola pustego powoduje wysłanie wiadomości od „SMSAPI”. -tylko dla
	 * wiadomoćsi pro
	 *
	 * @param from
	 * @see setEco
	 */
	public void setFrom(String from) {
		params.put("from", from);
	}

	/**
	 * Zwraca parametr encoding
	 *
	 * @return String
	 */
	public String getEncoding() {
		return params.get("encoding");
	}

	/**
	 * Parametr określa kodowanie polskich znaków w SMS-ie. Domyślne kodowanie jest windows-1250. Jeżeli występuje
	 * konieczność zmiany kodowania, należy użyć parametru encoding z danymi: - dla iso-8859-2 (latin2) – należy podać
	 * wartość „iso-8859-2” - dla utf-8 – należy podać wartość „utf-8”.
	 *
	 * @param encoding String
	 */
	public void setEncoding(String encoding) {
		if (!encoding.equals("iso-8859-2") && !encoding.equals("utf-8")) {
			throw new IllegalArgumentException("Incorrect encoding format");
		}
		params.put("encoding", encoding);
	}

	/**
	 * Zwraca parametr flash
	 *
	 * @return String
	 */
	public String getFlash() {
		return params.get("flash");
	}

	/**
	 * Wysyłanie wiadomości trybem „flash”,
	 *
	 * @param flash bool
	 */
	public void setFlash(boolean flash) {
		if (flash == true) {
			params.put("flash", "1");
		} else if (flash == false) {
			params.remove("flash");
		}
	}

	/**
	 * Zwraca parametr date_vaidate
	 *
	 * @return String
	 */
	public String getDateVaildate() {
		return params.get("date_vaidate");
	}

	/**
	 * Ustawienie sprawdza poprawność formatu podanej daty. W przypadku wystąpienia błędnej daty zwrócony zostanie błąd
	 * ERROR:54
	 *
	 * @param date_validate bool
	 */
	public void setDateVaidate(boolean date_validate) {
		if (date_validate == true) {
			params.put("date_validate", "1");
		} else if (date_validate == false) {
			params.remove("date_validate");
		}
	}

	/**
	 * Zwaraca parametr eco
	 *
	 * @return String
	 */
	public String getEco() {
		return params.get("eco");
	}

	/**
	 * Ustawienie parametru spowoduje wysłanie wiadomości Eco -brak możliwości wyboru pola nadawcy, wiadomość wysyłana z
	 * losowego numeru
	 *
	 * @param eco bool
	 */
	public void setEco(boolean eco) {
		if (eco == true) {
			params.put("eco", "1");
		} else if (eco == false) {
			params.put("eco", "0");
		}
	}

	/**
	 * Zwraca parametr nounicode
	 *
	 * @return String
	 */
	public String getNounicode() {
		return params.get("nounicode");
	}

	/**
	 * Ustawienie zabezpiecza przed wysłaniem wiadomości ze znakami specjalnymi -w tym polskimi (ERROR:11)
	 *
	 * @param nounicode bool
	 */
	public void setNounicode(boolean nounicode) {
		if (nounicode == true) {
			params.put("nounicode", "1");
		} else if (nounicode == false) {
			params.remove("nounicode");
		}
	}

	/**
	 * Zwaraca parametr normalize
	 *
	 * @return String
	 */
	public String getNormalize() {
		return params.get("normalize");
	}

	/**
	 * Ustawienie powoduje zamianę znaków diakrytycznych takich jak „ą”, „ś”, „ć” na ich odpowiedniki „a”, „s”, „c”.
	 *
	 * @param normalize bool
	 */
	public void setNormalize(boolean normalize) {
		if (normalize == true) {
			params.put("normalize", "1");
		} else if (normalize == false) {
			params.remove("normalize");
		}
	}

	/**
	 * Zwraca parametr fast
	 *
	 * @return String
	 */
	public String getFast() {
		return params.get("fast");
	}

	/**
	 * Ustawienie spowoduje wysłanie wiadomości przy wykorzystaniu osobnego kanału zapewniającego szybkie doręczenie
	 * wiadomości Fast. Z parametru korzystać można podczas wysyłania wiadomości Pro oraz Eco Ilość punktów za wysyłkę
	 * pomnożona będzie przez 1.5
	 *
	 * @param fast bool
	 */
	public void setFast(boolean fast) {
		if (fast == true) {
			params.put("fast", "1");
		} else if (fast == false) {
			params.remove("fast");
		}
	}

	/**
	 * Zwraca parametr partner_id
	 *
	 * @return String
	 */
	public String getPartnerId() {
		return params.get("partner_id");
	}

	/**
	 * Kod partnerski, który otrzymać można po podpisaniu umowy partnerskiej
	 *
	 * @param partner_id String
	 */
	public void setPartnerId(String partner_id) {
		params.put("partner_id", partner_id);
	}

	/**
	 * Zwraca objekt template który pozwala ustawić do 4 parametry wykorzystywane w szablonie
	 *
	 * @param name String -nazwa szablonu zdefiniowana w panelu klienta
	 * @return Template
	 */
	public Template template(String name) {
		if (tpl == null) {
			tpl = new Template(params, name);
		}

		return tpl;
	}

	/**
	 * Usuwa object template -to pozwala na utworzenie nowego
	 */
	public void templateClean() {
		if (tpl != null) {
			tpl = null;
			params.remove("template");
			params.remove("param1");
			params.remove("param2");
			params.remove("param3");
			params.remove("param4");
		}
	}
}
