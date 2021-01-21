package xyz.nhatbao.springdemo.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;

public interface MailService {
    public void sendMail(String receiveMail) throws AddressException, MessagingException, IOException;
}
