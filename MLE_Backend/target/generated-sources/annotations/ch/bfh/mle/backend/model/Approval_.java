package ch.bfh.mle.backend.model;

import ch.bfh.mle.backend.model.Supplier;
import ch.bfh.mle.backend.model.TreatmentCase;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-01-04T22:01:50")
@StaticMetamodel(Approval.class)
public class Approval_ { 

    public static volatile SingularAttribute<Approval, TreatmentCase> treatmentCase;
    public static volatile SingularAttribute<Approval, Supplier> supplier;
    public static volatile SingularAttribute<Approval, Long> id;
    public static volatile SingularAttribute<Approval, Date> approvedAt;

}