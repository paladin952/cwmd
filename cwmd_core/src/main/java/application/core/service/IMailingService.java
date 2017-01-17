package application.core.service;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Map;

/**
 * Interface for Mailing Service
 */
public interface IMailingService {

    /**
     * Sets the JavaMailSender
     * @param sender The JavaMailSender
     */
    IMailingService SetMailSender(JavaMailSender sender);

    /**
     * Sets the Velocity Engine
     * @param engine The Velocity Engine
     */
    IMailingService SetVelocityEngine(VelocityEngine engine);

    /**
     * Sends mail
     */
    IMailingService SendMail(Object obj);

    /**
     * Returns the Velocity Template Content
     */
    String getVelocityTemplateContent(Map<String, Object> model);
}
