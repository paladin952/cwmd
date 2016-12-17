package application.core.model.rn;

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
@Table(name = "rnSponsors")
public class RNSponsors implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rnSponsorsId")
    private Integer rnSponsorsId;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "sponsorName")
    private String sponsorName;

}
