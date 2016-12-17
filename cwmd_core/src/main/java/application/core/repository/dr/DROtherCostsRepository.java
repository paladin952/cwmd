package application.core.repository.dr;

import application.core.model.dr.DROtherCosts;
import application.core.repository.CWMDRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface DROtherCostsRepository extends CWMDRepository<Integer, DROtherCosts> {
}
