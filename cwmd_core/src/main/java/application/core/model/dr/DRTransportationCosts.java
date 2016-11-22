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
@Table(name = "drTransportationCosts")
public class DRTransportationCosts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drTransportationCostsId")
    private Integer drTransportationCostsId;

    @Column(name = "transport1_1Amount")
    private String transport1_1Amount;

    @Column(name = "transport1_1Currency")
    private String transport1_1Currency;

    @Column(name = "transport1_1Funding")
    private String transport1_1Funding;

    @Column(name = "transport1_2_1Amount")
    private String transport1_2_1Amount;

    @Column(name = "transport1_2_1Funding")
    private String transport1_2_1Funding;

    @Column(name = "transport1_2_2Amount")
    private String transport1_2_2Amount;

    @Column(name = "transport1_2_2Currency")
    private String transport1_2_2Currency;

    @Column(name = "transport1_2_2Funding")
    private String transport1_2_2Funding;

    @Column(name = "transport1_3Amount")
    private String transport1_3Amount;

    @Column(name = "transport1_3Funding")
    private String transport1_3Funding;

    @Column(name = "transport1_4Amount")
    private String transport1_4Amount;

    @Column(name = "transport1_4Currency")
    private String transport1_4Currency;

    @Column(name = "transport1_4Funding")
    private String transport1_4Funding;
}
