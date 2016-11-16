package application.core.utils;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Map;

public interface ICWMDMailer {
    ICWMDMailer SetMailSender(JavaMailSender sender);
    ICWMDMailer SetVelocityEngine(VelocityEngine engine);
    ICWMDMailer SendMail(Object obj);
    String getVelocityTemplateContent(Map<String, Object> model);
    String GetVelocityTemplateLocation();
    ICWMDMailer SetVelocityTemplateLocation(String location);
}
