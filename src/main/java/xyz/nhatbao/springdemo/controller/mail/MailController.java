package xyz.nhatbao.springdemo.controller.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.nhatbao.springdemo.service.MailService;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/to/{receiveMail}")
    public String sendMail(@PathVariable("receiveMail") String receiveMail) throws IOException, MessagingException {
        mailService.sendMail(receiveMail);
        return "Email sent successfully";
    }
}
