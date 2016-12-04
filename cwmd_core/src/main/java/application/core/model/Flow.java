package application.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Builder
public class Flow implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FlowID")
    private Integer id;

    @OneToMany(mappedBy = "flow", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<FlowDocument> flowDocuments = new ArrayList<>();

    @OneToMany(mappedBy = "flow", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<FlowPath> flowPath = new ArrayList<>();

    @Column(name = "CurrentDepartment")
    private Integer crtDepartment;

    @Column(name = "Remarks")
    private String remarks;
}
