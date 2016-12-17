package application.core.model.dr;

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
@Table(name = "drDailyCosts")
public class DRDailyCosts implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drDailyCostsId")
    private Integer drDailyCostsId;

    @Column(name = "dailyPayment2_1Amount")
    private String dailyPayment2_1Amount;

    @Column(name = "dailyPayment2_1Days")
    private String dailyPayment2_1Days;

    @Column(name = "dailyPayment2_1Total")
    private String dailyPayment2_1Total;

    @Column(name = "dailyPayment2_1Currency")
    private String dailyPayment2_1Currency;

    @Column(name = "dailyPayment2_1Funding")
    private String dailyPayment2_1Funding;

    @Column(name = "dailyPayment2_2Amount")
    private String dailyPayment2_2Amount;

    @Column(name = "dailyPayment2_2Days")
    private String dailyPayment2_2Days;

    @Column(name = "dailyPayment2_2Funding")
    private String dailyPayment2_2Funding;

    @Column(name = "dailyPayment2_3Amount")
    private String dailyPayment2_3Amount;

    @Column(name = "dailyPayment2_3Months")
    private String dailyPayment2_3Months;

    @Column(name = "dailyPayment2_3Total")
    private String dailyPayment2_3Total;

    @Column(name = "dailyPayment2_3Currency")
    private String dailyPayment2_3Currency;

    @Column(name = "dailyPayment2_3Funding")
    private String dailyPayment2_3Funding;
}
