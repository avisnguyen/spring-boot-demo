package xyz.nhatbao.springdemo.service.impl;

import org.springframework.stereotype.Service;
import xyz.nhatbao.springdemo.service.MailService;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {
    @Override
    public void sendMail(String receiveMail) throws AddressException, MessagingException, IOException {
        // Set up mail server
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable", "true");
        //  TURN OFF TWO STEP AUTH https://myaccount.google.com/lesssecureapps
        //  TURN ON LESS SECURE APPS https://myaccount.google.com/lesssecureapps

//        get mail session
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
//                type username and password email account
                return new PasswordAuthentication("<example>@gmail.com", "<your password>");
            }
        });

//        Set content for message
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("<from>@gmail.com", false));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiveMail));
        msg.setSubject("Test email");
        msg.setContent("Test email", "text/html");
        msg.setSentDate(new Date());

//        multipart body to send attachments
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Test email", "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

//        add attachment file
        MimeBodyPart attachPart = new MimeBodyPart();
        attachPart.attachFile("/var/tmp/TestUploadFile.txt");
        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
//        send mail
        Transport.send(msg);
    }
}
