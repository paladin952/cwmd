package application.core.repository.dr;

import application.core.model.dr.DRPersonInfo;
import application.core.repository.CWMDRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository for {@link DRPersonInfo}
 */
@Transactional
public interface DRPersonInforRepository extends CWMDRepository<Integer, DRPersonInfo> {
}
