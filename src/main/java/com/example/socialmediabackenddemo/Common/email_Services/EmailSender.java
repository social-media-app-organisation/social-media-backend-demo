package com.example.socialmediabackenddemo.Common.email_Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class EmailSender {
    @Autowired
    private EmailConfig mailConfig;

    @Autowired
    Environment env;

    private static final String  SENDEREMAIL ="${udeesa.email.sender.user}";

    @Async
    public void SendVerificationEmail(String token, String userMail) {
        String url = env.getProperty("verifyemailcontext") + "?token=" + token;
        String message = " Click on this link to verify your email";
        send( userMail, "Verify Email", message + " \r\n" + url);
    }

    private void send(String to, String subject, String body) {
        try {
            JavaMailSender mailSender = mailConfig.getJavaMailSender();
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage,true, "utf-8");
            helper.setFrom(EmailSender.SENDEREMAIL);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body,true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            Logger logger=Logger.getLogger(EmailSender.class.getName());
            logger.log(Level.WARNING, "failed to send mail",e);
        }
    }
}
