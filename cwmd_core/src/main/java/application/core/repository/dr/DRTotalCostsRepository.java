package application.core.repository.dr;

import application.core.model.dr.DRTotalCosts;
import application.core.repository.CWMDRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface DRTotalCostsRepository extends CWMDRepository<Integer, DRTotalCosts> {
}
