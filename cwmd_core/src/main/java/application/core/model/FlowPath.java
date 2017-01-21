package application.core.model;

import application.core.model.PKs.FlowPathPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "flow_path")
@IdClass(FlowPathPK.class)
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FlowPath implements Serializable {

    @Column(name = "FlowPathID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Id
    @ManyToOne
    @JoinColumn(name = "FlowID", nullable = false)
    @JsonIgnore
    private Flow flow;

    @Id
    @ManyToOne
    @JoinColumn(name = "DepartmentID", nullable = false)
    private Department department;

    public FlowPath(Flow flow, Department department) {
        this.flow = flow;
        this.department = department;
    }

    @Override
    public String toString() {
        return "FlowPath = { flow: " + flow.getId() + ", department: " + department.getId() + " }";
    }
}
