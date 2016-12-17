package application.core.repository.dr;

import application.core.model.dr.DRPersonInfo;
import application.core.repository.CWMDRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface DRPersonInforRepository extends CWMDRepository<Integer, DRPersonInfo> {
}
