package application.core.repository.dr;

import application.core.model.dr.DRDocument;
import application.core.repository.CWMDRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface DRDocumentRepository extends CWMDRepository<Integer, DRDocument> {

    List<DRDocument> findByUser_Username(String user_Username);
}
