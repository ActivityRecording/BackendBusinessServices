package ch.bfh.mle.backend.model;

import ch.bfh.mle.backend.model.Supplier;
import ch.bfh.mle.backend.model.TarmedActivity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-01-13T10:59:10")
@StaticMetamodel(Favorite.class)
public class Favorite_ { 

    public static volatile SingularAttribute<Favorite, Supplier> supplier;
    public static volatile SingularAttribute<Favorite, Long> id;
    public static volatile SingularAttribute<Favorite, TarmedActivity> tarmedActivity;

}