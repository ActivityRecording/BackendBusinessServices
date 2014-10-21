package ch.bfh.mle.backend.model;

import ch.bfh.mle.backend.model.Supplier;
import ch.bfh.mle.backend.model.TimePeriodType;
import ch.bfh.mle.backend.model.TreatmentCase;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-21T20:17:19")
@StaticMetamodel(TimePeriod.class)
public class TimePeriod_ { 

    public static volatile SingularAttribute<TimePeriod, TreatmentCase> treatmentCase;
    public static volatile SingularAttribute<TimePeriod, Supplier> supplier;
    public static volatile SingularAttribute<TimePeriod, Date> startTime;
    public static volatile SingularAttribute<TimePeriod, Long> id;
    public static volatile SingularAttribute<TimePeriod, Date> endTime;
    public static volatile SingularAttribute<TimePeriod, TimePeriodType> type;

}