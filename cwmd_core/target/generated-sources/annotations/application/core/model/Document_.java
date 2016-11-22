package application.core.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Document.class)
public abstract class Document_ {

	public static volatile SingularAttribute<Document, String> name;
	public static volatile SingularAttribute<Document, Integer> id;
	public static volatile SingularAttribute<Document, Date> dateAdded;

}

