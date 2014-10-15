package ch.bfh.mle.backend.modellayer;

import ch.bfh.mle.backend.modellayer.Behandlungsfall;
import ch.bfh.mle.backend.modellayer.Leistungserbringer;
import ch.bfh.mle.backend.modellayer.Zeitraumart;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-12T15:42:20")
@StaticMetamodel(Zeitraum.class)
public class Zeitraum_ { 

    public static volatile SingularAttribute<Zeitraum, Behandlungsfall> behandlungsfall;
    public static volatile SingularAttribute<Zeitraum, Zeitraumart> art;
    public static volatile SingularAttribute<Zeitraum, Leistungserbringer> leistungserbringer;
    public static volatile SingularAttribute<Zeitraum, Date> ende;
    public static volatile SingularAttribute<Zeitraum, Long> id;
    public static volatile SingularAttribute<Zeitraum, Date> beginn;

}