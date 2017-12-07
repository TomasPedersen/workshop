
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Message {
	// Send besked
	public void sendEmail(){
		String to = "tomas@patina.one";
		String from = "tomas@patina.one";
		String host = "send.one.com";
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.auth", "true");
		Authenticator auth = new Message.SMTPAuthenticator();
		Session session = Session.getDefaultInstance(properties, auth);
		
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Besked fra workshop");
			message.setText("Dette er beskeden.");
			
			Transport.send(message);
			System.out.println("Besked afsendt");
		}
		catch(AuthenticationFailedException afe){
			System.out.println("Forkert brugernavn eller adgangskode");
		}
		catch (MessagingException me){
			
			me.printStackTrace();
		}
	}
	private class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("tomas@patina.one", "xyz");
		}
	}
}
