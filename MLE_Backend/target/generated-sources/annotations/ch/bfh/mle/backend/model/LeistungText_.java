package ch.bfh.mle.backend.model;

import ch.bfh.mle.backend.model.LeistungTextPK;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-27T19:49:00")
@StaticMetamodel(LeistungText.class)
public class LeistungText_ { 

    public static volatile SingularAttribute<LeistungText, String> bez255;
    public static volatile SingularAttribute<LeistungText, String> medInterpret;
    public static volatile SingularAttribute<LeistungText, Date> mutDat;
    public static volatile SingularAttribute<LeistungText, LeistungTextPK> leistungTextPK;
    public static volatile SingularAttribute<LeistungText, String> techInterpret;

}