package application.core.repository;

import application.core.model.Flow;

import javax.transaction.Transactional;

@Transactional
public interface FlowRepository extends CWMDRepository<Integer, Flow>, FlowRepositoryCustom {}
