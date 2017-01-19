package application.core.repository;

import application.core.model.Flow;
import application.core.model.User;

import java.util.List;

public interface FlowRepositoryCustom {
    Flow getOneSQL(Integer flowId);
    List<Flow> getAllSQL();
}
