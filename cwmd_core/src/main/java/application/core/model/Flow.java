package application.core.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

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

    // Could do: change to FetchType.LAZY
    // FIXME: 15.11.2016 this column does not have a mapping in the Document entity. when fixed uncomment following lines
//    @JsonIgnore
//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "EntryID")
//    private List<Document> userDocument;

    // Could do: change to FetchType.LAZY
//    @JsonIgnore
//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "EntryID")
//    private List<Department> userDepartment;
}
