package application.core.repository;

import application.core.model.Flow;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface FlowRepository extends CWMDRepository<Integer, Flow>, FlowRepositoryCustom {

    List<Flow> findByUser_Username(String user_Username);
}
