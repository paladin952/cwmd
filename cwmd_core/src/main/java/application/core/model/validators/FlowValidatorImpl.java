package application.core.model.validators;


import application.core.model.Flow;
import application.core.model.validators.exceptions.ValidatorException;

public class FlowValidatorImpl implements IValidator<Flow> {

    @Override
    public IValidator<Flow> validate(Flow entity) throws ValidatorException {
        if (entity.getDocuments() == null) throw new ValidatorException("Flow has no documents");
        if (entity.getDepartments() == null) throw new ValidatorException("Flow has no departments");

        return this;
    }
}
