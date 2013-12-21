/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.checkAndMake;

import java.util.Locale;
import java.util.Properties;
import javax.annotation.PostConstruct;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maru
 */
@Service
public class MailPost extends JavaMailSenderImpl{

    public MailPost() {
    }
    
    

    @PostConstruct
    private void init() {
        setHost("smtp.gmail.com");
       // setPort(25);
        setUsername("hotkoborec@gmail.com");
        setPassword("Pomeranc");
        java.util.Properties p = new Properties();
        p.put("mail.transport.protocol", "smtp");
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable","true");
        p.put("mail.debug"," true");
        setJavaMailProperties(p);
    }
}
