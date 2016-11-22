package application.core.model.validators;


import application.core.model.Document;
import application.core.model.validators.exceptions.ValidatorException;

public class DocumentValidatorImpl implements IValidator<Document> {
    @Override
    public IValidator<Document> validate(Document entity) throws ValidatorException {
        if (entity.getName().equals("")) throw new ValidatorException("Name is null");
        if (entity.getDateAdded() == null) throw new ValidatorException("Date added is null");
        return this;
    }
}
