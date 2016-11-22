package application.core.model.validators;

import application.core.model.Department;
import application.core.model.validators.exceptions.ValidatorException;

public class DepartmentValidatorImpl implements IValidator<Department> {
    @Override
    public IValidator<Department> validate(Department entity) throws ValidatorException {
        if (entity.getName().equals("")) throw new ValidatorException("Name is null");

        //if (entity.getUserList() == null) throw new ValidatorException("Department has no users");

        return this;
    }
}
