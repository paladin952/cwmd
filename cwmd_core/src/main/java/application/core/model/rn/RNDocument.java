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
    @OneToOne(cascade = CascadeType.ALL)
    private RNOthers rnOthers;

    @OneToOne(cascade = CascadeType.ALL)
    private RNResearch rnResearch;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "rnDocument")
    private List<RNProduct> rnProducts;
}
