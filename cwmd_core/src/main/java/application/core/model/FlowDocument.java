package application.core.model;

import application.core.model.PKs.FlowDocumentPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "flow_document")
@IdClass(FlowDocumentPK.class)
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FlowDocument implements Serializable {
    @JsonIgnore
    @Id
    @ManyToOne
    @JoinColumn(name = "FlowID", nullable = false)
    private Flow flow;

    @Id
    @ManyToOne
    @JoinColumn(name = "DocumentID", unique = true, nullable = false)
    private Document document;
}
