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
@Table(name = "rnTotal")
public class RNTotal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rnTotalId")
    private Integer rnTotalId;

    @Column(name = "totalQuantity")
    private Integer totalQuantity;

    @Column(name = "totalPricePerUnit")
    private Float totalPricePerUnit;

    @Column(name = "totalPrice")
    private Float totalPrice;
}
