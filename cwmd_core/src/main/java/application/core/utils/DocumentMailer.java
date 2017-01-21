package application.core.utils;

import application.core.model.Document;
import application.core.service.exceptions.ServiceException;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Component
@PropertySources({@PropertySource(value = "classpath:local/email.properties")})
public class DocumentMailer implements ICWMDMailer {
    private final static String PLACEHOLDER_DESTINATION = "senekrum@gmail.com";
    private final static String DOCUMENT_TOKEN = "document";

    @Value("${email.address}")
    private String from;

    private String velocityTemplateLocation = "/velocity/doc_30_days_template.vm";

    private JavaMailSender mailSender;

    private VelocityEngine velocityEngine;

    @Autowired
    public DocumentMailer(JavaMailSender mailSender, VelocityEngine velocityEngine) {
        this.mailSender = mailSender;
        this.velocityEngine = velocityEngine;
    }

    @Override
    public ICWMDMailer setMailSender(JavaMailSender sender) {
        mailSender = sender;

        return this;
    }

    @Override
    public ICWMDMailer setVelocityEngine(VelocityEngine engine) {
        velocityEngine = engine;

        return this;
    }

    @Override
    public String getVelocityTemplateLocation() {
        return velocityTemplateLocation;
    }

    @Override
    public ICWMDMailer setVelocityTemplateLocation(String velocityTemplateLocation) {
        this.velocityTemplateLocation = velocityTemplateLocation;

        return this;
    }

    @Override
    public ICWMDMailer sendMail(Object obj) {
        Document doc = (Document) obj;

        MimeMessagePreparator preparator = getMessagePreparator(doc);
        try {
            mailSender.send(preparator);
        } catch (MailException e) {
            throw new ServiceException("Error occurred while sending mail", e);
        }

        return this;
    }

    private MimeMessagePreparator getMessagePreparator(Document doc) {
        return mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setSubject("[CWMD] Concerning document " + doc.getName());
            helper.setFrom(from);
            helper.setTo(doc.getUser().getUserInfo().getEmail());

            Map<String, Object> model = new HashMap<>();
            model.put(DOCUMENT_TOKEN, doc);

            String msg = getVelocityTemplateContent(model);

            helper.setText(msg, true);
        };
    }

    @Override
    public String getVelocityTemplateContent(Map<String, Object> model) {
        try {
            Document doc = (Document) model.get(DOCUMENT_TOKEN);
            VelocityContext velocityContext = new VelocityContext();
            velocityContext.put("name", doc.getName());
            velocityContext.put("date", doc.getDateAdded());
            velocityContext.put("owner", doc.getUser().getUsername());

            StringWriter content = new StringWriter();
            velocityEngine.mergeTemplate(velocityTemplateLocation, "UTF-8", velocityContext, content);
            return content.toString();
        } catch (Exception e) {
            throw new ServiceException("Exception occured while processing velocity template", e);
        }
    }
}
