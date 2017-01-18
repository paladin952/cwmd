package application.core.repository;

import application.core.model.Flow;

public interface FlowRepositoryCustom {
    Flow getOneSQL(Integer flowId);
}
