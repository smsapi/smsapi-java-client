package pl.smsapi.message;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.smsapi.sender.Sender;

public abstract class Message implements MessageInterface{

	protected String username;
	protected String password;
	protected final ArrayList<String> to = new ArrayList<String>();
	protected final ArrayList<String> idx = new ArrayList<String>();
	protected String group;
	protected String date;
	protected final HashMap<String, String> params = new HashMap<String, String>();
	protected Sender sender;

	//---end--abstract method---------
	@Override
	public final HashMap getParams() {
		return params;
	}

	@Override
	public final String getUsername() {
		return username;
	}

	/**
	 * Ustawia login potrzebny do autoryzacji w smsapi.pl
	 *
	 * @param username
	 */
	@Override
	public final void setUsername(String username) {
		this.username = username;
	}

	@Override
	public final String getPassword() {
		return password;
	}

	/**
	 * Ustawia hasło potrzebne do autoryzacji w smsapi.pl -wartość podać jako string zakodowany przez MD5
	 *
	 * @param password String
	 */
	public final void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Ustawia hasło potrzebne do autoryzacji w smsapi.pl -hasło można podać jako otwarte wtedy trzeba ustawić codeMD5
	 * na true
	 *
	 * @param password String
	 * @param codeMD5 bool
	 */
	public final void setPassword(String password, boolean encodeMD5) {

		this.password = (encodeMD5 == true) ? MD5Digest(password) : password;
	}

	/**
	 * Zwraca listę numerów telefonów
	 *
	 * @return ArrayList
	 */
	public final ArrayList getTo() {
		return to;
	}

	/**
	 * Zwraca listę indetyfikatorów
	 *
	 * @return ArrayList
	 */
	public final ArrayList getIdx() {
		return idx;
	}

	/**
	 * Numer odbiorcy wiadomości w formacie 48xxxxxxxxx lub xxxxxxxxx
	 *
	 * @param to String
	 */
	public final void addTo(String to) {
		this.to.add(to);
	}

	/**
	 * Numer odbiorcy wiadomości w formacie 48xxxxxxxxx lub xxxxxxxxx
	 *
	 * @param to long
	 */
	public final void addTo(long to) {
		String tmp = Long.toString(to);
		addTo(tmp);
	}

	/**
	 * Numery odbiorców wiadomości w formacie tablicy
	 *
	 * @param to String[]
	 */
	public final void addTo(String[] to) {
		for (String item : to) {
			addTo(item);
		}
	}

	/**
	 * Opcjonalny parametr użytkownika wysyłany z wiadomością a następnie zwracany przy wywołaniu zwrotnym CALLBACK.
	 * Parametr idx może mieć maksymalnie 36 znaków dopuszczalne są cyfry 0 - 9 oraz litery a – z -wielkość liter nie
	 * jest rozróżniana
	 *
	 * @param to
	 * @param idx
	 */
	public final void addTo(String to, String idx) {
		this.to.add(to);
		this.idx.add(idx);
	}

	/**
	 * Zwraca parametr group
	 *
	 * @return String
	 */
	public final String getGroup() {
		return group;
	}

	/**
	 * Nazwa grupy kontaktów z książki telefonicznej, do których ma zostać wysłana wiadomość -ustawienie tego parametru
	 * powoduje ze parametr "to" nie będzie brany pod uwagę
	 *
	 * @param group String
	 */
	public final void setGroup(String group) {
		this.group = group;
	}

	/**
	 * Zwraca paramert date
	 *
	 * @return String
	 */
	public final String getDate() {
		return date;
	}

	/**
	 * Określa kiedy wiadomość ma być wysłana. W przypadku wstawienia daty przeszłej wiadomość zostanie wysłana od razu.
	 * Wiadomość można zaplanować na maksymalnie 3 miesiące do przodu Data w formacie unixtime przekazana jako string
	 *
	 * @param date String
	 */
	public final void setDate(String date) {
		this.date = date;
	}

	/**
	 * Data w formacie unixtime przekazana jako long
	 *
	 * @param date long
	 */
	public final void setDate(long date) {
		Long time = date;
		setDate(time.toString());
	}

	/**
	 * Data w formacie unixtime przekazana jako objekt typu Calendar
	 *
	 * @param cal GregorianCalendar
	 */
	public final void setDate(Calendar cal) {
		long time = cal.getTimeInMillis() / 1000;
		setDate(time);
	}

	/**
	 * Zwraca parametr test
	 *
	 * @return String
	 */
	public final String getTest() {
		return params.get("test");
	}

	/**
	 * Wiadomość nie jest wysyłana, wyświetlana jest jedynie odpowiedź
	 *
	 * @param test
	 */
	public final void setTest(boolean test) {
		if (test == true) {
			params.put("test", "1");
		} else if (test == false) {
			params.remove("test");
		}
	}

	@Override
	public final Sender getSender() {
		return sender;
	}

	@Override
	public final void setSender(Sender sender) {
		this.sender = sender;
	}

	public static String MD5Digest(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");

			md.update(str.getBytes());

			byte byteData[] = md.digest();

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}

			return sb.toString();

		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
		}

		return null;
	}

	/**
	 * Uruchamia wysyłkę wiadomości w smsapi.pl
	 *
	 * @return bool
	 */
	@Override
	public boolean send() {

		if (sender == null) {
			throw new RuntimeException("No exists sender");
		}

		sender.setMessage(this);

		return sender.send();
	}

	/**
	 * Zwraca liste odpowiedzi po wywolaniu send();
	 *
	 * @return ArrayList Result
	 * @see Result
	 */
	public ArrayList<Result> getResults() {

		if (sender == null) {
			throw new RuntimeException("No exists sender");
		}

		return sender.getResults();
	}

	/**
	 * Zwraca parametr check_idx
	 *
	 * @return String
	 */
	public final String getCheckIdx() {
		return params.get("check_idx");
	}

	/**
	 * Pozwala zabezpieczyć przed wysłanie dwóch wiadomości z identyczną wartością parametru idx. W przypadku ustawienia
	 * parametru (&check_idx=1) system sprawdza czy wiadomość z takim idx już została przyjęta jeśli tak zwracany jest
	 * błąd 53.
	 *
	 * @param checkIdx bool
	 */
	public final void setCheckIdx(boolean checkIdx) {
		if (checkIdx == true) {
			params.put("check_idx", "1");
		} else if (checkIdx == false) {
			params.remove("check_idx");
		}
	}

	public final void setHttps(boolean https) {
		if (sender == null) {
			throw new RuntimeException("No exists sender");
		}

		if (https == true) {
			sender.setProtocol("https");
		} else {
			sender.setProtocol("http");
		}
	}

	private void cleanAllParams() {
		group = null;
		date = null;
		to.clear();
		idx.clear();
		params.clear();
	}

	public final boolean deleteScheduled(String idMessage) {
		if (idMessage.isEmpty()) {
			throw new IllegalArgumentException("ID message not have to empty");
		}

		params.put("sch_del", idMessage);
		return send();
	}
}
