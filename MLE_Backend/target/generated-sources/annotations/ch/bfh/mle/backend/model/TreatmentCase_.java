package ch.bfh.mle.backend.model;

import ch.bfh.mle.backend.model.Activity;
import ch.bfh.mle.backend.model.Patient;
import ch.bfh.mle.backend.model.TimePeriod;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-14T17:47:50")
@StaticMetamodel(TreatmentCase.class)
public class TreatmentCase_ { 

    public static volatile SingularAttribute<TreatmentCase, Patient> patient;
    public static volatile ListAttribute<TreatmentCase, Activity> activities;
    public static volatile SingularAttribute<TreatmentCase, Long> treatmentNumber;
    public static volatile SingularAttribute<TreatmentCase, Date> startTime;
    public static volatile SingularAttribute<TreatmentCase, Long> id;
    public static volatile SingularAttribute<TreatmentCase, Date> endTime;
    public static volatile ListAttribute<TreatmentCase, TimePeriod> timePeriods;
    public static volatile SingularAttribute<TreatmentCase, Boolean> released;

}