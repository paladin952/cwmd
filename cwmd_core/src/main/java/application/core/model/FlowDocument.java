package application.core.model;

import application.core.model.PKs.FlowDocumentPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "flow_document")
@IdClass(FlowDocumentPK.class)
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FlowDocument implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "FlowID", nullable = false)
    @JsonIgnore
    private Flow flow;

    @Id
    @OneToOne
    @JoinColumn(name = "DocumentID", unique = true, nullable = false)
    private Document document;

    @Override
    public String toString() {
        return "FlowDocument = { flow: " + flow.getId() + ", document: " + document.getId() + " }";
    }
}
