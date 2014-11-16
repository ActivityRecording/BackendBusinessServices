package ch.bfh.mle.backend.model;

import ch.bfh.mle.backend.model.Favorite;
import ch.bfh.mle.backend.model.Role;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-14T17:47:50")
@StaticMetamodel(Supplier.class)
public class Supplier_ { 

    public static volatile ListAttribute<Supplier, Favorite> favorites;
    public static volatile SingularAttribute<Supplier, String> firstname;
    public static volatile SingularAttribute<Supplier, Role> role;
    public static volatile SingularAttribute<Supplier, Long> employeeID;
    public static volatile SingularAttribute<Supplier, Long> id;
    public static volatile SingularAttribute<Supplier, String> lastname;

}