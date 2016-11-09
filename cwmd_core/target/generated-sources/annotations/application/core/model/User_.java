package application.core.model;

import application.core.utils.enums.RoleType;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, UserDetails> userInfo;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, RoleType> role;
	public static volatile ListAttribute<User, Document> documents;
	public static volatile SingularAttribute<User, String> username;

}

