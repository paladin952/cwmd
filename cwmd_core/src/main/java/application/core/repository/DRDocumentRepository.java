package application.core.repository;

import application.core.model.dr.DRDocument;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface DRDocumentRepository extends CWMDRepository<Integer, DRDocument>  {
}
