package application.core.repository;

import application.core.model.FlowPath;
import application.core.model.PKs.FlowPathPK;

import javax.transaction.Transactional;

@Transactional
public interface FlowPathRepository extends CWMDRepository<FlowPathPK, FlowPath> {}
