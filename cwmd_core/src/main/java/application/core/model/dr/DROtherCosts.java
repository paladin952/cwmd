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
@Table(name = "drOtherCosts")
public class DROtherCosts implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drOtherCostsId")
    private Integer drOtherCostsId;

    @Column(name = "otherCosts4_1Amount")
    private String otherCosts4_1Amount;

    @Column(name = "otherCosts4_1Currency")
    private String otherCosts4_1Currency;

    @Column(name = "otherCosts4_1Funding")
    private String otherCosts4_1Funding;

    @Column(name = "otherCosts4_2Amount")
    private String otherCosts4_2Amount;

    @Column(name = "otherCosts4_2Currency")
    private String otherCosts4_2Currency;

    @Column(name = "otherCosts4_2Funding")
    private String otherCosts4_2Funding;

    @Column(name = "otherCosts4_3Amount")
    private String otherCosts4_3Amount;

    @Column(name = "otherCosts4_3Currency")
    private String otherCosts4_3Currency;

    @Column(name = "otherCosts4_3Funding")
    private String otherCosts4_3Funding;

    @Column(name = "otherCosts4_4Amount")
    private String otherCosts4_4Amount;

    @Column(name = "otherCosts4_4Currency")
    private String otherCosts4_4Currency;

    @Column(name = "otherCosts4_4Funding")
    private String otherCosts4_4Funding;

    @Column(name = "otherCosts4_5Amount")
    private String otherCosts4_5Amount;

    @Column(name = "otherCosts4_5Currency")
    private String otherCosts4_5Currency;

    @Column(name = "otherCosts4_5Funding")
    private String otherCosts4_5Funding;

    @Column(name = "otherCosts4_6Amount")
    private String otherCosts4_6Amount;

    @Column(name = "otherCosts4_6Funding")
    private String otherCosts4_6Funding;

    @Column(name = "otherCosts4_7Amount")
    private String otherCosts4_7Amount;

    @Column(name = "otherCosts4_7Currency")
    private String otherCosts4_7Currency;

    @Column(name = "otherCosts4_7Funding")
    private String otherCosts4_7Funding;

    @Column(name = "otherCosts4_8Amount")
    private String otherCosts4_8Amount;

    @Column(name = "otherCosts4_8Funding")
    private String otherCosts4_8Funding;
}
