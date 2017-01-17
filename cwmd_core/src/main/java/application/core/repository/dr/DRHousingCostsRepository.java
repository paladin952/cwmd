package application.core.repository.dr;

import application.core.model.dr.DRHousingCosts;
import application.core.repository.CWMDRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface DRHousingCostsRepository extends CWMDRepository<Integer, DRHousingCosts> {
}
