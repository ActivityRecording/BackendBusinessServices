package ch.bfh.mle.backend.modellayer;

import ch.bfh.mle.backend.modellayer.Rollenart;
import ch.bfh.mle.backend.modellayer.Standardleistung;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-12T15:42:20")
@StaticMetamodel(Rolle.class)
public class Rolle_ { 

    public static volatile ListAttribute<Rolle, Standardleistung> standardkatalog;
    public static volatile SingularAttribute<Rolle, Rollenart> art;
    public static volatile SingularAttribute<Rolle, String> bezeichnung;
    public static volatile SingularAttribute<Rolle, Long> id;

}