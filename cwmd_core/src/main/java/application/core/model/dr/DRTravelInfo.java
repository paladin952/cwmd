package application.core.model.dr;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "drTravelInfo")
public class DRTravelInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drTravelInfoId")
    private Integer drTravelInfoId;

    @Column(name = "travelLocation")
    @NotNull
    private String travelLocation;

    @Column(name = "route")
    @NotNull
    private String route;

    @Column(name = "eventTime")
    @NotNull
    private String eventTime;

    @Column(name = "displacementPeriod")
    @NotNull
    private String displacementPeriod;

    @Column(name = "meansOfTransportation")
    @NotNull
    private String meansOfTransportation;

    @Column(name = "travelScope")
    @NotNull
    private String travelScope;

    @Column(name = "costBearer")
    @NotNull
    private String costBearer;

}
