package application.web.dto;

import lombok.*;

import java.util.Date;

/**
 * DTO for {@link application.core.model.dr.DRDocument}
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DRDocumentDto {
    private Integer id;

    private String name;

    private Date dateAdded;

    private String username;

    private Integer drPersonInfoId;

    private Integer drTravelInfoId;

    private Integer drTransportationCostsId;

    private Integer drDailyCostsId;

    private Integer drHousingCostsId;

    private Integer drOtherCostsId;

    private Integer drTotalCostsId;

    private Integer drBankInfoId;
}