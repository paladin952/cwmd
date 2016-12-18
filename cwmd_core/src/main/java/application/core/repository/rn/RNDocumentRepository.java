package application.core.repository.rn;


import application.core.model.rn.RNDocument;
import application.core.repository.CWMDRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RNDocumentRepository extends CWMDRepository<Integer, RNDocument> {

    List<RNDocument> findByUser_Username(String user_Username);
}
