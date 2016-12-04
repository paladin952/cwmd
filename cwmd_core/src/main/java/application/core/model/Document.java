package application.core.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "document")
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Document implements Serializable {
    @Id
    @TableGenerator(name = "TABLE_GENERATOR", initialValue = 0, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GENERATOR")
    @Column(name = "DocumentID")
    private Integer id;

    @Column(name = "Name", length = 150, nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "DateAdded", nullable = false)
    private Date dateAdded;

    @ManyToOne
    @JoinColumn(name = "Owner", nullable = false)
    private User user;

    @Column(name = "Version", nullable = false)
    private Float version;

    @Column(name = "Path", nullable = false)
    private String path;
}
