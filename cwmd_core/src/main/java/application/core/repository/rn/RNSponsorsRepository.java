package application.core.repository.rn;

import application.core.model.rn.RNSponsors;
import application.core.repository.CWMDRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RNSponsorsRepository extends CWMDRepository<Integer, RNSponsors> {
}
