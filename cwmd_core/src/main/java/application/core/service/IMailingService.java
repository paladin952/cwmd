package application.core.service;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Map;

public interface IMailingService {
    IMailingService SetMailSender(JavaMailSender sender);
    IMailingService SetVelocityEngine(VelocityEngine engine);
    IMailingService SendMail(Object obj);
    String getVelocityTemplateContent(Map<String, Object> model);
}
