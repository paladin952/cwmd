package application.core.repository;


import application.core.model.rn.RNDocument;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RNDocumentRepository extends CWMDRepository<Integer, RNDocument>{
}
