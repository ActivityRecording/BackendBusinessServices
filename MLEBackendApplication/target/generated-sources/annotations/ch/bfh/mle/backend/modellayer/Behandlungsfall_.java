package ch.bfh.mle.backend.modellayer;

import ch.bfh.mle.backend.modellayer.Leistung;
import ch.bfh.mle.backend.modellayer.Patient;
import ch.bfh.mle.backend.modellayer.Zeitraum;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-12T15:42:20")
@StaticMetamodel(Behandlungsfall.class)
public class Behandlungsfall_ { 

    public static volatile ListAttribute<Behandlungsfall, Zeitraum> zeitraeume;
    public static volatile SingularAttribute<Behandlungsfall, Long> fallId;
    public static volatile SingularAttribute<Behandlungsfall, Date> ende;
    public static volatile SingularAttribute<Behandlungsfall, Patient> patient;
    public static volatile SingularAttribute<Behandlungsfall, Long> id;
    public static volatile ListAttribute<Behandlungsfall, Leistung> leistungen;
    public static volatile SingularAttribute<Behandlungsfall, Date> beginn;

}