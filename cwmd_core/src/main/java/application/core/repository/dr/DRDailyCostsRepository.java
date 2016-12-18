package application.core.repository.dr;

import application.core.model.dr.DRDailyCosts;
import application.core.repository.CWMDRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface DRDailyCostsRepository extends CWMDRepository<Integer, DRDailyCosts> {
}
