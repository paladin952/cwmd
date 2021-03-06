package application.core.model.PKs;

import application.core.model.Department;
import application.core.model.Flow;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class FlowPathPK implements Serializable {
    private Flow flow;
    private Department department;
}
