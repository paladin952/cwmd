package application.core.model.rn;

import application.core.model.Document;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "rnDocument")
public class RNDocument extends Document{

    @Column(name = "budget")
    private Float budget;

    @Column(name = "personalFunds")
    private Float personalFunds;

    @OneToOne(cascade = CascadeType.ALL)
    private RNResearch rnResearch;

    @OneToOne(cascade = CascadeType.ALL)
    private RNSponsors rnSponsors;

    @OneToOne(cascade = CascadeType.ALL)
    private RNOthers rnOthers;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "rnDocument")
    private List<RNProduct> rnProducts;

    @OneToOne(cascade = CascadeType.ALL)
    private RNTotal rnTotal;
}
