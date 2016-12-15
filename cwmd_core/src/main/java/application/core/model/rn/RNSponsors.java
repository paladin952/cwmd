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
@Table(name = "rnSponsors")
public class RNSponsors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rnSponsorsId")
    private Integer rnSponsorsId;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "sponsorName")
    private String sponsorName;

}
