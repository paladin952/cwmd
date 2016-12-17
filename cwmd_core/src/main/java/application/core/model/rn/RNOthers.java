package application.core.model.rn;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "rnOthers")
public class RNOthers implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rnOthersId")
    private Integer rnOthersId;

    @Column(name = "structuralFunds")
    private Float structuralFunds;

    @Column(name = "structuralFundsIdentification")
    private String structuralFundsIdentification;

    @Column(name = "externalFunding")
    private Float externalFunding;

    @Column(name = "externalFundingIdentification")
    private String externalFundingIdentification;

    @Column(name = "other")
    private Float other;

    @Column(name = "otherIdentification")
    private String otherIdentification;

    @Column(name = "advancePayment")
    private Float advancePayment;
}
