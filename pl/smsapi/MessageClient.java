package pl.smsapi;

import pl.smsapi.message.Mms;
import pl.smsapi.message.Result;
import pl.smsapi.message.Sms;
import pl.smsapi.message.Vms;
import pl.smsapi.sender.SenderHttp;

public final class MessageClient {

	public static void main(String[] args) {

		try {

			if (args.length < 5) {
				throw new RuntimeException("No exist args");
			}


			Sms sms = MessageClient.getSmsInstance();

			sms.setUsername(args[0]);
			sms.setPassword(args[1]);

			String[] partsTo = args[2].split(",");

			for (String phone : partsTo) {
				sms.addTo(phone);
			}

			if (args[3].equals("eco")) {
				sms.setEco(true);
			}

			sms.setMessage(args[4]);

			sms.send();


			for (Result result : sms.getResults()) {

				System.out.println("start---------------------------");
				System.out.println("Response http: " + result.getResponse());
				System.out.println("Status: " + result.getStatus());
				System.out.println("Error: " + result.getError());
				System.out.println("Id: " + result.getId());
				System.out.println("Points: " + result.getPoints());
				System.out.println("Phone: " + result.getPhone());
				System.out.println("end-----------------------------");

			}


		} catch (IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		} catch (RuntimeException ex) {
			System.out.println(ex.getMessage());
		}

	}

	public static Sms getSmsInstance() {

		SenderHttp sender = new SenderHttp();
		Sms sms = new Sms(sender);

		return sms;

	}

	public static Mms getMmsInstance() {
		SenderHttp sender = new SenderHttp();
		Mms mms = new Mms(sender);

		return mms;
	}

	public static Vms getVmsInstance() {
		SenderHttp sender = new SenderHttp();
		Vms vms = new Vms(sender);

		return vms;
	}

	public static SenderHttp getSenderHttp() {
		SenderHttp sender = new SenderHttp();
		return sender;
	}
}
