package ch.bfh.mle.backend.model;

import ch.bfh.mle.backend.model.RoleType;
import ch.bfh.mle.backend.model.StandardActivity;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-01-13T10:59:10")
@StaticMetamodel(Role.class)
public class Role_ { 

    public static volatile SingularAttribute<Role, String> description;
    public static volatile SingularAttribute<Role, Long> id;
    public static volatile SingularAttribute<Role, RoleType> type;
    public static volatile ListAttribute<Role, StandardActivity> standardActivities;

}