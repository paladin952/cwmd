package application.core.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Flow.class)
public abstract class Flow_ {

	public static volatile ListAttribute<Flow, Document> documents;
	public static volatile SingularAttribute<Flow, Integer> id;
	public static volatile ListAttribute<Flow, Department> departments;

}

