package ch.bfh.mle.backend.model;

import ch.bfh.mle.backend.model.Role;
import ch.bfh.mle.backend.model.TarmedActivity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-07T12:50:35")
@StaticMetamodel(StandardActivity.class)
public class StandardActivity_ { 

    public static volatile SingularAttribute<StandardActivity, Role> role;
    public static volatile SingularAttribute<StandardActivity, Integer> sortKey;
    public static volatile SingularAttribute<StandardActivity, String> description;
    public static volatile SingularAttribute<StandardActivity, Long> id;
    public static volatile SingularAttribute<StandardActivity, Integer> category;
    public static volatile SingularAttribute<StandardActivity, TarmedActivity> tarmedActivity;

}