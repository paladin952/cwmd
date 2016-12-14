package application.core.model.dr;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "drSignatureInfo")
public class DRSignatureInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer drSignatureInfoId;

    private String signedBy;

    private Date signedOn;
}
