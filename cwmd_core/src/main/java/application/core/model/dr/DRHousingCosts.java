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
@Table(name = "drHousingCosts")
public class DRHousingCosts implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drHousingCostsId")
    private Integer drHousingCostsId;

    @Column(name = "housing3_1Amount")
    private String  housing3_1Amount;

    @Column(name = "housing3_1Days")
    private String  housing3_1Days;

    @Column(name = "housing3_1Total")
    private String  housing3_1Total;

    @Column(name = "housing3_1Currency")
    private String  housing3_1Currency;

    @Column(name = "housing3_1Funding")
    private String  housing3_1Funding;

    @Column(name = "housing3_2Amount")
    private String housing3_2Amount;

    @Column(name = "housing3_2Days")
    private String housing3_2Days;

    @Column(name = "housing3_2Total")
    private String housing3_2Total;

    @Column(name = "housing3_2Currency")
    private String housing3_2Currency;

    @Column(name = "housing3_2Funding")
    private String housing3_2Funding;
}
