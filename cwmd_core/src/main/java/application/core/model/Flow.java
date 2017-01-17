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

    /**
     * Add a flow documment to this flow
     * @param flowDoc The flow document to be added
     */
    public void addFlowDocument(FlowDocument flowDoc) {
        flowDocuments.add(flowDoc);
    }

    /**
     * Add a flow department to this flow
     * @param flowDept The flow department
     */
    public void addFlowDepartment(FlowPath flowDept) {
        flowPath.add(flowDept);
    }
}
