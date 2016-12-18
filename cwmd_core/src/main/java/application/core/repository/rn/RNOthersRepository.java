package application.core.repository.rn;

import application.core.model.rn.RNOthers;
import application.core.repository.CWMDRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RNOthersRepository extends CWMDRepository<Integer, RNOthers> {
}
