package ch.bfh.mle.backend.modellayer;

import ch.bfh.mle.backend.modellayer.Rolle;
import ch.bfh.mle.backend.modellayer.Tarmedleistung;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-12T15:42:20")
@StaticMetamodel(Standardleistung.class)
public class Standardleistung_ { 

    public static volatile SingularAttribute<Standardleistung, Rolle> rolle;
    public static volatile SingularAttribute<Standardleistung, String> bezeichnung;
    public static volatile SingularAttribute<Standardleistung, Tarmedleistung> tarmedLeistung;
    public static volatile SingularAttribute<Standardleistung, Long> id;

}