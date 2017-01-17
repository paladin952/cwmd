package application.core.repository;

import application.core.model.Document;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repo for {@link Document}
 */
@Transactional
public interface DocumentRepository extends CWMDRepository<Integer, Document>  {
}
