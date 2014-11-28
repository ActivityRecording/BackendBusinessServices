package ch.bfh.mle.backend.model;

import ch.bfh.mle.backend.model.Supplier;
import ch.bfh.mle.backend.model.TarmedActivity;
import ch.bfh.mle.backend.model.TreatmentCase;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-28T17:26:24")
@StaticMetamodel(Activity.class)
public class Activity_ { 

    public static volatile SingularAttribute<Activity, TreatmentCase> treatmentCase;
    public static volatile SingularAttribute<Activity, Integer> number;
    public static volatile SingularAttribute<Activity, Supplier> supplier;
    public static volatile SingularAttribute<Activity, Long> id;
    public static volatile SingularAttribute<Activity, TarmedActivity> tarmedActivity;

}