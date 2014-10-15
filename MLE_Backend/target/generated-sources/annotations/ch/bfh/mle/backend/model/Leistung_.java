package ch.bfh.mle.backend.model;

import ch.bfh.mle.backend.model.Behandlungsfall;
import ch.bfh.mle.backend.model.Leistungserbringer;
import ch.bfh.mle.backend.model.Tarmedleistung;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-15T17:02:50")
@StaticMetamodel(Leistung.class)
public class Leistung_ { 

    public static volatile SingularAttribute<Leistung, Behandlungsfall> behandlungsfall;
    public static volatile SingularAttribute<Leistung, Leistungserbringer> leistungserbringer;
    public static volatile SingularAttribute<Leistung, Integer> anzahl;
    public static volatile SingularAttribute<Leistung, Tarmedleistung> tarmedleistung;
    public static volatile SingularAttribute<Leistung, Long> id;

}