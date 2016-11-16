package application.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "flow")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Flow implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EntryID")
    private Integer id;

    //TODO: UNDO THE COMMENT AND FIX THE HIBERNATE RELATIONSHIPS
//    // Could do: change to FetchType.LAZY
//    @JsonIgnore
//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "EntryID")
//    private List<Document> userDocument;
//
//    // Could do: change to FetchType.LAZY
//    @JsonIgnore
//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "EntryID")
//    private List<Department> userDepartment;
}
