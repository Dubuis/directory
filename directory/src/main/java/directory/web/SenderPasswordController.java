package directory.web;

import java.util.Collection;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import directory.business.dao.IDao;
import directory.model.Person;

@Controller
public class SenderPasswordController {
	private static String SENDER = "directoryapplication.noreply@gmail.com";
	private static String PWD = "forgetPassword";
	private static String HOST = "smtp.gmail.com";
	private static String PORT = "587";
	
	@Autowired
	IDao dao;
	
	@RequestMapping("sendPassword")
	public String sendPassword(
			@RequestParam(required=false) Boolean modified,
			@RequestParam(required=false) String mail,
			@RequestParam(required=false) Long id
			) throws Exception {
		if(modified == null || !modified) {
			return "sendPassword";
		}
		if(mail == null && id == null ||
				mail.isEmpty() && id <= 0L) {
			return "sendPassword";
		}
		if(mail.isEmpty() && id >= 0L) {
			// Find user by id
			Person p = dao.findPerson(id);
			if(p == null || p.getEmail().isEmpty()) {
				return "sendPassword";
			} else {
				sendMail(p);
			}
		} else if(!mail.isEmpty()) {
			Collection<Person> list = dao.findAllPersons();
			Person person = null;
			for(Person p : list) {
				if(p.getEmail().toLowerCase().equals(mail.toLowerCase())) {
					person = p;
					break;
				}
			}
			if(person != null) {
				sendMail(person);
			} else {
				return "sendPassword";
			}
		}
		return "redirect:";
	}

	private void sendMail(Person p) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", HOST);
		props.put("mail.smtp.port", PORT);
		
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(SENDER, PWD);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(SENDER));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(p.getEmail()));
			message.setSubject("Your Password");
			message.setText("Dear " + p.getFamilyName()
				+ " " + p.getFirstName()
				+ "\n\nYour id is : " + p.getId()
				+ "\nYour password is : " + p.getPassword());

			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
