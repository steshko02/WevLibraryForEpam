package by.steshko.LIb.api;

import org.springframework.mail.SimpleMailMessage;

public interface MailSender {
     void send(String emailTo, String subject, String message);
}
