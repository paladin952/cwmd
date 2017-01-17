package application.core.repository.dr;

import application.core.model.dr.DRTravelInfo;
import application.core.repository.CWMDRepository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Repository for {@link DRTravelInfo}
 */
@Transactional
public interface DRTravelInfoRepository extends CWMDRepository<Integer, DRTravelInfo> {
}
