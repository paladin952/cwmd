package application.core.model.PKs;

import application.core.model.Document;
import application.core.model.Flow;
import lombok.*;

import java.io.Serializable;

/**
 * MySql primary key for {@link application.core.model.FlowDocument}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class FlowDocumentPK implements Serializable {
    private Flow flow;
    private Document document;
}
