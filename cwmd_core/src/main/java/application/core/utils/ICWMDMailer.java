package application.core.utils;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Map;

public interface ICWMDMailer {
    ICWMDMailer setMailSender(JavaMailSender sender);
    ICWMDMailer setVelocityEngine(VelocityEngine engine);
    ICWMDMailer sendMail(Object obj);
    String getVelocityTemplateContent(Map<String, Object> model);
    String getVelocityTemplateLocation();
    ICWMDMailer setVelocityTemplateLocation(String location);
}
