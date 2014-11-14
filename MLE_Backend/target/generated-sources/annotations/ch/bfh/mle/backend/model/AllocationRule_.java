package ch.bfh.mle.backend.model;

import ch.bfh.mle.backend.model.TarmedActivity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-14T12:21:15")
@StaticMetamodel(AllocationRule.class)
public class AllocationRule_ { 

    public static volatile SingularAttribute<AllocationRule, Boolean> notSelectable;
    public static volatile SingularAttribute<AllocationRule, Boolean> requiresTime;
    public static volatile SingularAttribute<AllocationRule, Integer> groupId;
    public static volatile SingularAttribute<AllocationRule, Long> id;
    public static volatile SingularAttribute<AllocationRule, Boolean> noPeriodAllocation;
    public static volatile SingularAttribute<AllocationRule, TarmedActivity> tarmedActivity;
    public static volatile SingularAttribute<AllocationRule, Integer> cardinality;

}