package application.core.repository.dr;

import application.core.model.dr.DROtherCosts;
import application.core.repository.CWMDRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository for {@link DROtherCosts}
 */
@Transactional
public interface DROtherCostsRepository extends CWMDRepository<Integer, DROtherCosts> {
}
