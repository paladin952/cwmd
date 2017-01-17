package application.core.repository.rn;

import application.core.model.rn.RNResearch;
import application.core.repository.CWMDRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository for {@link RNResearch}
 */
@Transactional
public interface RNResearchRepository extends CWMDRepository<Integer, RNResearch> {
}
