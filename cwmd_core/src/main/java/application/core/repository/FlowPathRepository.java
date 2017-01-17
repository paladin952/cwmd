package application.core.repository;

import application.core.model.FlowPath;
import application.core.model.PKs.FlowPathPK;

import javax.transaction.Transactional;

/**
 * Repo for {@link FlowPath}
 */
@Transactional
public interface FlowPathRepository extends CWMDRepository<FlowPathPK, FlowPath>, FlowPathRepositoryCustom {}
