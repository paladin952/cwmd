package application.core.repository.dr;

import application.core.model.dr.DRBankInfo;
import application.core.repository.CWMDRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository for {@link DRBankInfo}
 */
@Transactional
public interface DRBankInfoRepository extends CWMDRepository<Integer, DRBankInfo> {
}
