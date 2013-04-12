package pl.smsapi;

import pl.smsapi.message.Mms;
import pl.smsapi.message.Result;
import pl.smsapi.message.Sms;
import pl.smsapi.message.Account;
import pl.smsapi.message.Message;
import pl.smsapi.message.MessageInterface;
import pl.smsapi.message.Vms;
import pl.smsapi.sender.SenderHttp;

public final class MessageClient {
	
	private static String username;
	private static String password;
	private static boolean auth = true;
	
	public static boolean getAuth(){
		return auth;
	}
	
	public static void setAuth(boolean val){
		auth = val;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		MessageClient.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		MessageClient.password = password;
	}
	
	public static void setPassword(String password, boolean encodeMd5) {
		if(encodeMd5 == true){
			MessageClient.password = Message.MD5Digest(password);
		}else{
			MessageClient.password = password;
		}	
	}
	
	
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
	
	private static void setDataAuth(MessageInterface message){
		if(auth == true && !username.isEmpty() && !password.isEmpty()){
			message.setUsername(username);
			message.setPassword(password);
		}		
	}

	public static Sms getSmsInstance() {

		SenderHttp sender = new SenderHttp();
		Sms sms = new Sms(sender);
		setDataAuth(sms);
		return sms;

	}

	public static Mms getMmsInstance() {
		SenderHttp sender = new SenderHttp();
		Mms mms = new Mms(sender);
		setDataAuth(mms);
		return mms;
	}

	public static Vms getVmsInstance() {
		SenderHttp sender = new SenderHttp();
		Vms vms = new Vms(sender);
		setDataAuth(vms);
		return vms;
	}
	
	public static Account getAccountInstance() {
		SenderHttp sender = new SenderHttp();
		Account account = new Account(sender);
		setDataAuth(account);
		return account;
	}

	public static SenderHttp getSenderHttp() {
		SenderHttp sender = new SenderHttp();
		return sender;
	}
	
	
}
