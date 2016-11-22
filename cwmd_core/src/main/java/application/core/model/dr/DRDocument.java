package application.core.model.dr;

import application.core.model.Document;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "drDocument")
public class DRDocument extends Document {
    @OneToOne(cascade = CascadeType.ALL)
    private DRPersonInfo drPersonInfo;

    @OneToOne(cascade = CascadeType.ALL)
    private DRTravelInfo drTravelInfo;

    @OneToOne(cascade = CascadeType.ALL)
    private DRTransportationCosts drTransportationCosts;

    @OneToOne(cascade = CascadeType.ALL)
    private DRDailyCosts drDailyCosts;

    @OneToOne(cascade = CascadeType.ALL)
    private DRHousingCosts drHousingCosts;

    @OneToOne(cascade = CascadeType.ALL)
    private DROtherCosts drOtherCosts;

    @OneToOne(cascade = CascadeType.ALL)
    private DRTotalCosts drTotalCosts;

    @OneToOne(cascade = CascadeType.ALL)
    private DRBankInfo drBankInfo;
}
