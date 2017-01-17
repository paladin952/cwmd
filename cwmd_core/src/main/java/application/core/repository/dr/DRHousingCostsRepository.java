package application.core.repository.dr;

import application.core.model.dr.DRHousingCosts;
import application.core.repository.CWMDRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository for {@link DRHousingCosts}
 */
@Transactional
public interface DRHousingCostsRepository extends CWMDRepository<Integer, DRHousingCosts> {
}
