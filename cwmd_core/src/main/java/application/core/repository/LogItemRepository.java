package application.core.repository;

import application.core.model.logging.LogItem;

import javax.transaction.Transactional;

@Transactional
public interface LogItemRepository extends CWMDRepository<Long, LogItem>, LogItemRepositoryCustom {}
