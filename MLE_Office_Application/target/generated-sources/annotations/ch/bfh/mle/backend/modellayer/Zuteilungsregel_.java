package ch.bfh.mle.backend.modellayer;

import ch.bfh.mle.backend.modellayer.Tarmedleistung;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-12T15:42:20")
@StaticMetamodel(Zuteilungsregel.class)
public class Zuteilungsregel_ { 

    public static volatile SingularAttribute<Zuteilungsregel, Boolean> nichtauswaehlbar;
    public static volatile SingularAttribute<Zuteilungsregel, Integer> gruppierungsID;
    public static volatile SingularAttribute<Zuteilungsregel, Boolean> ohneZeitmessung;
    public static volatile SingularAttribute<Zuteilungsregel, Tarmedleistung> tarmedLeistung;
    public static volatile SingularAttribute<Zuteilungsregel, Boolean> erfordertZeitangabe;
    public static volatile SingularAttribute<Zuteilungsregel, Long> id;
    public static volatile SingularAttribute<Zuteilungsregel, Integer> kardinalitaet;

}