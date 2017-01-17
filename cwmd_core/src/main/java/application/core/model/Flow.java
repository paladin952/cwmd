package application.core.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "flow")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Flow implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FlowID")
    private Integer id;

    @OneToMany(mappedBy = "flow", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<FlowDocument> flowDocuments = new ArrayList<>();

    @OneToMany(mappedBy = "flow", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FlowPath> flowPath = new ArrayList<>();

    @Column(name = "CurrentDepartment")
    private Integer crtDepartment;

    @Column(name = "Remarks")
    private String remarks;

    public void addFlowDocument(FlowDocument flowDoc) {
        flowDocuments.add(flowDoc);
    }

    public void addFlowDepartment(FlowPath flowDept) {
        flowPath.add(flowDept);
    }
}
