package application.web.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FlowStartDTO {
    private List<Integer> documentIds;
    private List<Integer> departmentIds;
}
