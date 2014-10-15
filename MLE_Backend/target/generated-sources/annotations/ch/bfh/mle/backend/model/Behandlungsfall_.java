package ch.bfh.mle.backend.model;

import ch.bfh.mle.backend.model.Leistung;
import ch.bfh.mle.backend.model.Patient;
import ch.bfh.mle.backend.model.Zeitraum;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-15T17:02:50")
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