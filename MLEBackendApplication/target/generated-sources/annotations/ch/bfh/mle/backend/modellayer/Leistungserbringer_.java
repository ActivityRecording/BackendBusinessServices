package ch.bfh.mle.backend.modellayer;

import ch.bfh.mle.backend.modellayer.Favorit;
import ch.bfh.mle.backend.modellayer.Rolle;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-12T15:42:20")
@StaticMetamodel(Leistungserbringer.class)
public class Leistungserbringer_ { 

    public static volatile SingularAttribute<Leistungserbringer, Rolle> rolle;
    public static volatile SingularAttribute<Leistungserbringer, Long> mitarbeiterID;
    public static volatile ListAttribute<Leistungserbringer, Favorit> favoriten;
    public static volatile SingularAttribute<Leistungserbringer, String> vorname;
    public static volatile SingularAttribute<Leistungserbringer, String> nachname;
    public static volatile SingularAttribute<Leistungserbringer, Long> id;

}