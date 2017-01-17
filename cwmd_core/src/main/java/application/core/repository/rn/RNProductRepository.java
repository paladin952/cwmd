package application.core.repository.rn;

import application.core.model.rn.RNProduct;
import application.core.repository.CWMDRepository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Repository for {@link RNProduct}
 */
@Transactional
public interface RNProductRepository extends CWMDRepository<Integer, RNProduct> {
}
