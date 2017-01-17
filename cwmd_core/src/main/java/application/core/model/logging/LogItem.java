package application.core.model.logging;

import application.core.utils.enums.LogLevel;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "log")
public class LogItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EntryID")
    private Long id;
    @Column(name = "Level", nullable = false)
    private Integer level;
    @Column(name = "Timestamp", nullable = false)
    private Timestamp timestamp;
    @Column(name = "Tag", nullable = false)
    private String tag;
    @Column(name = "User")
    private String user;
    @Column(name = "Department")
    private String department;
    @Column(name = "DocumentType")
    private String documentType;
    @Column(name = "Message", nullable = false)
    private String msg;
    @Column(name = "Exception")
    private String exception;

    /**
     * A nice representation of this model
     * @return a string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(LogLevel.toString(LogLevel.fromValue(level)))
            .append(" | ")
            .append(timestamp.toString())
            .append(" | [ ")
            .append(tag)
            .append(" ] ");

        if (user != null || department != null || documentType != null) {
            sb.append("[ ");
            if (user != null)
                sb.append(user);
            if (department != null) {
                if (user != null)
                    sb.append(", ");
                sb.append(department);
            }
            if (documentType != null)
            {
                if (user != null || department != null)
                    sb.append(", ");
                sb.append(documentType);
            }
            sb.append(" ]: ");
        }

        sb.append(msg);

        if (exception != null)
            sb.append("[ EXCEPTION ]: ")
                .append(exception);

        return sb.toString();
    }
}
