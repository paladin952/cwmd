package application.web.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class RNDocumentDto {
    private Integer id;

    private String name;

    private Date dateAdded;

    private String username;

    private Float budget;

    private Float personalFunds;

    private Integer rnResearchId;

    private Integer rnSponsorsId;

    private Integer rnOthersId;

    private List<Integer> rnProductIds;

    private Integer rnTotalId;
}
