package application.core.utils;

import application.core.model.*;
import application.core.service.exceptions.ServiceException;
import application.core.utils.exceptions.FlowMailerException;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@PropertySources({@PropertySource(value = "classpath:local/email.properties")})
public class FlowMailer implements ICWMDMailer {
    private final static String PLACEHOLDER_DESTINATION = "senekrum@gmail.com";
    private final static String FLOW_TOKEN = "flow";

    @Value("${email.address}")
    private String from;

    private String velocityTemplateLocation = "/resources/velocity/flow_notification_template.vm";

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    @Override
    public ICWMDMailer SetMailSender(JavaMailSender sender) {
        mailSender = sender;

        return this;
    }

    @Override
    public ICWMDMailer SetVelocityEngine(VelocityEngine engine) {
        velocityEngine = engine;

        return this;
    }

    @Override
    public String GetVelocityTemplateLocation() {
        return velocityTemplateLocation;
    }

    @Override
    public ICWMDMailer SetVelocityTemplateLocation(String velocityTemplateLocation) {
        this.velocityTemplateLocation = velocityTemplateLocation;

        return this;
    }

    @Override
    public ICWMDMailer SendMail(Object obj) {
        Flow flow = (Flow) obj;

        MimeMessagePreparator preparator = getMessagePreparator(flow);
        try {
            mailSender.send(preparator);
        } catch (MailException e) {
            throw new ServiceException("Error occurred while sending e-mail", e);
        }

        return this;
    }

    private MimeMessagePreparator getMessagePreparator(Flow flow) {
        return mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            Department dept = flow.getFlowPath().get(flow.getCrtDepartment()).getDepartment();
            helper.setSubject("[CWMD] Document flow has reached department " + dept.getName());
            helper.setFrom(from);
            if (dept.getIsUserGroup()) {
                List<String> emails = dept.getUserList().stream()
                        .map(departmentUser -> departmentUser.getUser().getUserInfo().getEmail())
                        .collect(Collectors.toList());
                helper.setTo(emails.toArray(new String[0]));
            }
            else {
                Optional<DepartmentUser> chief = dept.getUserList().stream()
                        .filter(departmentUser -> !departmentUser.getUser().getUserInfo().getIsDepartmentChief())
                        .findFirst();
                if (chief.isPresent()) {
                    User user = chief.get().getUser();
                    helper.setTo(user.getUserInfo().getEmail());
                }
                else throw new FlowMailerException("Could not determine a sender; current department of flow having ID " + flow.getId().toString() + " has no department chief, and is not a user group");
            }

            Map<String, Object> model = new HashMap<>();
            model.put(FLOW_TOKEN, flow);

            String msg = getVelocityTemplateContent(model);

            helper.setText(msg, true);
        };
    }

    @Override
    public String getVelocityTemplateContent(Map<String, Object> model) {
        try {
            Flow flow = (Flow) model.get(FLOW_TOKEN);

            String flowDocs = "";
            for (FlowDocument flowDoc : flow.getFlowDocuments()) {
                flowDocs += flowDoc.getDocument().getName();
                flowDocs += ", ";
            }
            if (flowDocs.length() > 0)
                flowDocs = flowDocs.substring(0, flowDocs.length() - 2);

            VelocityContext velocityContext = new VelocityContext();
            velocityContext.put(FLOW_TOKEN + ".documents", flowDocs);
            velocityContext.put(FLOW_TOKEN + ".remarks", flow.getRemarks());

            StringWriter content = new StringWriter();
            velocityEngine.mergeTemplate(velocityTemplateLocation, "UTF-8", velocityContext, content);
            return content.toString();
        } catch (Exception e) {
            throw new ServiceException("Exception occured while processing velocity template", e);
        }
    }
}
