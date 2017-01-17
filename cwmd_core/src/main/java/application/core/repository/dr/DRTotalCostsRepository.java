package application.core.repository.dr;

import application.core.model.dr.DRTotalCosts;
import application.core.repository.CWMDRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository for {@link DRTotalCosts}
 */
@Transactional
public interface DRTotalCostsRepository extends CWMDRepository<Integer, DRTotalCosts> {
}
