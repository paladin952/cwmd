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
@Table(name = "drTotalCosts")
public class DRTotalCosts implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drTotalCostsId")
    private Integer drTotalCostsId;

    @Column(name = "totalSpending")
    private String totalSpending;

    @Column(name = "advancePayment")
    private String advancePayment;
}
