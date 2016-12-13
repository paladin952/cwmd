package application.core.model.rn;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "rnProduct")
public class RNProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rnProductId")
    private Integer rnProductId;

    @Column(name = "nrCrt")
    private Integer nrCrt;

    @Column(name = "name")
    private String name;

    @Column(name = "codCPV")
    private String codCPV;

    @Column(name = "um")
    private String um;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "pricePerUnit")
    private Float pricePerUnit;

    @Column(name = "totalPrice")
    private Float totalPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rnDocumentId")
    @JsonIgnore
    private RNDocument rnDocument;
}
