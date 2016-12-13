package application.core.model;

import application.core.utils.enums.RoleType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User implements Serializable {
    private static final String DEFAULT_STRING = "N/A";

    @Id
    @Column(name = "Username", length = 60, unique = true, nullable = false)
    private String username;

    @Column(name = "Password", length = 30, nullable = false)
    private String password;

    @Column(name = "Role", nullable = false)
    private RoleType role;

    //TODO fix this. JPA repo crashes when find.all()
    // Could do: change to FetchType.LAZY
//    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private UserDetails userInfo;

//     TODO fix this. userRepo.finAll() java.sql.SQLSyntaxErrorException: Table 'cwmd_db.drdocument' doesn't exist
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
//    private List<Document> documents;
}
