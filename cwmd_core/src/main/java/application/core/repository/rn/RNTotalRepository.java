package application.core.repository.rn;

import application.core.model.rn.RNTotal;
import application.core.repository.CWMDRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RNTotalRepository  extends CWMDRepository<Integer, RNTotal>{
}
