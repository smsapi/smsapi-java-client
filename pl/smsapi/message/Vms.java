package pl.smsapi.message;

import java.io.File;
import java.io.IOException;
import pl.smsapi.sender.Sender;

public final class Vms extends Message {

	protected String tts;
	protected File wav;

	public static enum Lector {

		AGNIESZKA("agnieszka"),
		EWA("ewa"),
		JACEK("jacek"),
		JAN("jan"),
		MAJA("maja");
		private final String name;

		Lector(String name) {
			this.name = name;
		}
	};

	public Vms() {
	}

	/**
	 * Wstawia object który będzie obsługiwał wysyłkę wiadomości
	 *
	 * @param sender Sender
	 */
	public Vms(Sender sender) {
		this.sender = sender;
	}

	@Override
	public Object getObjMessage() {


		if (tts != null) {
			return getTts();
		} else if (wav != null) {
			return getWav();
		}

		return null;
	}

	/**
	 * Pobiera parametr tts
	 *
	 * @return String
	 * @exception IllegalArgumentException -gdy parametr tts jest null
	 */
	public String getTts() {
		if (tts == null) {
			throw new IllegalArgumentException("No exists tts");
		}

		return tts;
	}

	/**
	 * Wstawia tekst do parametru tts - jest obowiązkowy jeśli wcześniej nie został ustawiony parametr wav
	 *
	 * @param tts String
	 * @exception IllegalArgumentException -gdy string is empty
	 */
	public void setTts(String tts) {

		if (tts.isEmpty()) {
			throw new IllegalArgumentException("String is empty");
		}

		this.tts = tts;
	}

	/**
	 * deprecated
	 *
	 * public void setTts(String tts, String encoding) {
	 *
	 * if(tts.isEmpty()){ throw new IllegalArgumentException("String is empty"); }
	 *
	 * this.tts = tts; params.put("encoding", encoding); }
	 */
	/**
	 * Pobiera plik wav
	 *
	 * @return File
	 * @exception IllegalArgumentException -gdy nie wav jest null
	 */
	public File getWav() {
		if (wav == null) {
			throw new IllegalArgumentException("No exists wav");
		}

		return wav;
	}

	/**
	 * Na podstawie scieżki do pliku tworzy objekt File i przypisuje do zmienej wav
	 *
	 * @param pathFile String
	 * @throws IOException
	 * @exception IllegalArgumentException -gdy plik nie istnieje lub nie jest typu wav
	 * @see checkFileWav
	 */
	public void setWav(String pathFile) {
		File file = new File(pathFile);

		if (!checkFileWav(file)) {
			throw new IllegalArgumentException("File not exists or not read");
		}

		wav = file;
	}

	/**
	 * Wstawia objekt File do zmienej wav
	 *
	 * @param fileWav File
	 * @throws IOException
	 * @exception IllegalArgumentException -gdy pliku nie istnieje lub nie jest typu wav
	 */
	public void setWav(File fileWav) {

		if (!checkFileWav(fileWav)) {
			throw new IllegalArgumentException("File not exists or not read");
		}

		wav = fileWav;
	}

	private boolean checkFileWav(File file) {
		try {
			if (file.exists() && file.canRead() && file.getCanonicalPath().endsWith(".wav")) {
				return true;
			}
		} catch (IOException ex) {
			throw new RuntimeException(ex.getMessage());
		}

		return false;
	}

	/**
	 * Ilość prób połączenia
	 *
	 * @return String
	 */
	public String getTry() {
		return params.get("try");
	}

	/**
	 * Ilość prób połączenia (dopuszczalne wartości od 1 do 6)
	 *
	 * @param val int
	 */
	public void setTry(int val) {

		if (val < 1 || val > 6) {
			throw new IllegalArgumentException("Incorrect value - accept 1 to 6");
		}

		String tmp = Integer.toString(val);

		params.put("try", tmp);

	}

	/**
	 * Pobiera parametr interval
	 *
	 * @return String
	 */
	public String getInterval() {
		return params.get("interval");
	}

	public void setInterval(int val) {

		if (val < 1800 || val > 7200) {
			throw new IllegalArgumentException("Incorrect value - accept 1800 to 7200");
		}

		String tmp = Integer.toString(val);

		params.put("interval", tmp);

	}

	/**
	 * Pobjera parametr skip_gsm
	 *
	 * @return String
	 */
	public String getSkipGsm() {
		return params.get("skip_gsm");
	}

	/**
	 * Ustawienie tego parametru spowoduje pominięcie telefonów komórkowych podczas wysyłki i wysłanie wiadomości tylko
	 * do numerów stacjonarnych
	 *
	 * @param skipGsm bool
	 */
	public void setSkipGsm(boolean skipGsm) {

		if (skipGsm == true) {
			params.put("skip_gsm", "1");
		} else if (skipGsm == false) {
			params.remove("skip_gsm");
		}

	}

	/**
	 * Pobiera parametr tts_lector
	 *
	 * @return String
	 */
	public String getTtsLector() {
		return params.get("tts_lector");
	}

	/**
	 * Określa nazwę lektora czytającego wpisany tekst wiadomości VMS Domyslna wartość to JAN.
	 *
	 * @param lector enum Lector
	 */
	public void setTtsLector(Lector lector) {
		params.put("tts_lector", lector.name());
	}

	public String getFrom() {
		return params.get("from");
	}

	public void setFrom(String from) {
		params.put("from", from);
	}
}
