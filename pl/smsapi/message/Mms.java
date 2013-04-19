package pl.smsapi.message;

import pl.smsapi.SmsapiException;
import pl.smsapi.sender.Sender;

public final class Mms extends Message {

	protected SmilInterface smil;

	public Mms() {
		setPath("mms.do");
	}

	public Mms(Sender sender) {
		this();
		setSender(sender);
	}

	@Override
	public String getObjMessage() {

		if (smil == null) {
			throw new SmsapiException("Smil not exists");
		}

		return smil.render();
	}

	public String getSubject() {
		return params.get("subject");
	}

	public void setSubject(String subject) {
		params.put("subject", subject);
	}

	public SmilInterface smil() {

		if (smil == null) {
			smil = new Smil();
		}

		return smil;
	}

	public SmilInterface smil(String content) {

		if (smil == null) {
			smil = new Smil(content);
		}

		return smil;
	}

	public SmilInterface smil(SmilInterface smil) {
		this.smil = smil;
		return this.smil;
	}
}