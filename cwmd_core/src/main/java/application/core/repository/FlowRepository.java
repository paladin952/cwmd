package application.core.repository;

import application.core.model.Flow;

import javax.transaction.Transactional;

/**
 * Repo for {@link Flow}
 */
@Transactional
public interface FlowRepository extends CWMDRepository<Integer, Flow> {}
