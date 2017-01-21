package application.core.utils;

import application.core.model.*;
import application.core.service.exceptions.ServiceException;
import application.core.utils.exceptions.FlowMailerException;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
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
import java.net.URL;
import java.net.URLClassLoader;
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

    private String velocityTemplateLocation = "/velocity/flow_at_dept_template.vm";

    private JavaMailSender mailSender;

    private VelocityEngine velocityEngine;

    @Autowired
    public FlowMailer(JavaMailSender mailSender, VelocityEngine velocityEngine) {
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
        Flow flow = (Flow) obj;

        MimeMessagePreparator preparator = getMessagePreparator(flow);
        try {
            mailSender.send(preparator);
        } catch (MailException e) {
            throw new ServiceException("Error occurred while sending e-mail", e);
        }

        return this;
    }

    public FlowMailer sendMailToInitiator(Flow flow) {
        MimeMessagePreparator preparator = getUserMessagePreparator(flow);
        try {
            mailSender.send(preparator);
        } catch (MailException e) {
            throw new ServiceException("Error occurred while sending e-mail", e);
        }

        return this;
    }

    private MimeMessagePreparator getUserMessagePreparator(Flow flow) {
        return mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setSubject("[CWMD] Flow status has been updated");
            helper.setFrom(from);
                User user = flow.getUser();
                    helper.setTo(user.getUserInfo().getEmail());

            Map<String, Object> model = new HashMap<>();
            model.put(FLOW_TOKEN, flow);

            String msg = getVelocityTemplateContent(model);

            helper.setText(msg, true);
        };
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
                        .filter(departmentUser -> departmentUser.getUser().getUserInfo().getIsDepartmentChief())
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
            velocityContext.put("documents", flowDocs);
            velocityContext.put("remarks", (flow.getRemarks() == null || flow.getRemarks().equals("")) ? "N/A" : flow.getRemarks());

            StringWriter content = new StringWriter();
            velocityEngine.mergeTemplate(velocityTemplateLocation, "UTF-8", velocityContext, content);
            return content.toString();
        } catch (Exception e) {
            throw new ServiceException("Exception occured while processing velocity template", e);
        }
    }
}
