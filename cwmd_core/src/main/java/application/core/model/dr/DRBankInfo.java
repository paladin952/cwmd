package application.core.model.dr;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "drBankInfo")
public class DRBankInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drBankInfoId")
    private Integer drBankInfoId;

    @Column(name = "accountOwner")
    private String accountOwner;

    @Column(name = "cnp")
    private String cnp;

    @Column(name = "homeAddress")
    private String homeAddress;

    @Column(name = "bankName")
    private String bankName;

    @Column(name = "iban")
    private String iban;
}
