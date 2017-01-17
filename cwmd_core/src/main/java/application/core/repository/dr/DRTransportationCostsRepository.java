package application.core.repository.dr;

import application.core.model.dr.DRTransportationCosts;
import application.core.repository.CWMDRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository for {@link DRTransportationCosts}
 */
@Transactional
public interface DRTransportationCostsRepository extends CWMDRepository<Integer, DRTransportationCosts> {
}
