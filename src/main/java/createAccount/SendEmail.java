package createAccount;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	// Gmail username
	final String sender = "ezfitjava012@gmail.com";

	// Gmail password: Do!ng123
	final String password = "rozbeehygetdexaa";

	public void sendEmail(String email, String name, String code) {
		// 發送email
		// Receiver's email ID
		String receiver = email;

		// Sending email from gmail
		String host = "smtp.gmail.com";

		// Port of SMTP
		String port = "587";

		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);

		// Create session object passing properties and authenticator instance
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password);
			}
		});

		try {
			// Create MimeMessage object
			MimeMessage message = new MimeMessage(session);

			// Set the Senders mail to From
			message.setFrom(new InternetAddress(sender));

			// Set the recipients email address
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));

			// Subject of the email
			message.setSubject("歡迎加入EZFit");

			// Body of the email

			message.setText("Hi " + name + "\n" + "您的驗證碼為: " + code + " , 請注意有區分大小寫");
			// Send email.
			Transport.send(message);

		} catch (MessagingException me) {
			me.printStackTrace();
		}

	}

}
