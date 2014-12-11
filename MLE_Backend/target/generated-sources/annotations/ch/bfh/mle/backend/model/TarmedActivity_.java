package ch.bfh.mle.backend.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-11T19:53:35")
@StaticMetamodel(TarmedActivity.class)
public class TarmedActivity_ { 

    public static volatile SingularAttribute<TarmedActivity, Integer> duration;
    public static volatile SingularAttribute<TarmedActivity, String> description;
    public static volatile SingularAttribute<TarmedActivity, String> id;
    public static volatile SingularAttribute<TarmedActivity, Date> validFrom;
    public static volatile SingularAttribute<TarmedActivity, String> medicalInformation;
    public static volatile SingularAttribute<TarmedActivity, Date> validTo;

}