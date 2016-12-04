package application.core.model;

import application.core.model.PKs.FlowPathPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "flow_path")
@IdClass(FlowPathPK.class)
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FlowPath implements Serializable {
    @JsonIgnore
    @Id
    @ManyToOne
    @JoinColumn(name = "FlowID", nullable = false)
    private Flow flow;

    @Id
    @ManyToOne
    @JoinColumn(name = "DepartmentID", nullable = false)
    private Department department;
}
