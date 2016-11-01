package cwmd.core.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserDetails.class)
public abstract class UserDetails_ {

	public static volatile SingularAttribute<UserDetails, String> firstName;
	public static volatile SingularAttribute<UserDetails, String> lastName;
	public static volatile SingularAttribute<UserDetails, String> address;
	public static volatile SingularAttribute<UserDetails, Long> phoneNumber;
	public static volatile SingularAttribute<UserDetails, Integer> id;
	public static volatile SingularAttribute<UserDetails, String> email;
	public static volatile SingularAttribute<UserDetails, Boolean> isDepartmentChief;

}

