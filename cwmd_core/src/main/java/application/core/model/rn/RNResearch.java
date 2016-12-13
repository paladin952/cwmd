package application.core.model.rn;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "rnResearch")
public class RNResearch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rnResearchId")
    private Integer rnResearchId;

    @Column(name = "nationalFunds1")
    private Float nationalFunds1;

    @Column(name = "nationalFunds1Identification")
    private String nationalFunds1Identification;

    @Column(name = "nationalFunds2")
    private Float nationalFunds2;

    @Column(name = "nationalFunds2Identification")
    private String nationalFunds2Identification;

    @Column(name = "ternaryContracts")
    private Float ternaryContracts;

    @Column(name = "ternaryContractsIdentification")
    private String ternaryContractsIdentification;
}
