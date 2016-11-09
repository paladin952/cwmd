package application.core.service;

import application.core.model.Document;
import application.core.service.exceptions.ServiceException;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.util.HashMap;
import java.util.Map;

@Service
// TODO: ADD YOUR OWN email.properties FILE, UNDER CWMD_CORE.SRC.MAIN.RESOURCES
// properties file format:
// address=YOUREMAIL@gmail.com
// password=YOURPASSWORD
// ! might need to set up an application password
// if two-step authentication is enabled on your account
@PropertySources({@PropertySource(value="classpath:local/email.properties")})
public class DocumentMailingServiceImpl implements IMailingService {
    private final static String PLACEHOLDER_DESTINATION = "senekrum@gmail.com";
    private final static String DOCUMENT_TOKEN          = "document";

    @Value("${email.address}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    @Override
    public IMailingService SetMailSender(JavaMailSender sender) {
        mailSender = sender;

        return this;
    }

    @Override
    public IMailingService SetVelocityEngine(VelocityEngine engine) {
        velocityEngine = engine;

        return this;
    }

    @Override
    public IMailingService SendMail(Object obj) {
        Document doc = (Document) obj;

        MimeMessagePreparator preparator = getMessagePreparator(doc);
        try {
            mailSender.send(preparator);
        } catch (MailException e) {
            throw new ServiceException("Mailing error occurred while sending mail", e);
        }

        return this;
    }

    private MimeMessagePreparator getMessagePreparator(Document doc) {
        return mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setSubject("[CWMD] Concerning document " + doc.getName());
            helper.setFrom(from);
            helper.setTo(PLACEHOLDER_DESTINATION);

            Map<String, Object> model = new HashMap<>();
            model.put(DOCUMENT_TOKEN, doc);

            String msg = getVelocityTemplateContent(model);

            helper.setText(msg, true);
        };
    }

    @Override
    public String getVelocityTemplateContent(Map<String, Object> model) {
        StringBuilder content = new StringBuilder();
        try{
            content.append(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/resources/velocity/document_template.vm", model));
            return content.toString();
        }catch(Exception e){
            throw new ServiceException("Exception occured while processing velocity template", e);
        }
    }
}
