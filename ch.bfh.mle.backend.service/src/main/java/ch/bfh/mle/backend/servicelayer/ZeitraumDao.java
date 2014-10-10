package ch.bfh.mle.backend.servicelayer;

import ch.bfh.mle.backend.modellayer.Zeitraum;
import javax.inject.Named;

/**
 * Erweiterung des generischen DAO (Data Access Objects) {@link GenericJpaDao} fuer Zeitraum.
 * Erweitert {@link GenericJpaDao} und implementiert {@link IZeitraumDao}.
 * @author Stefan Walle
 */
@Named
public class ZeitraumDao extends GenericJpaDao<Zeitraum> implements IZeitraumDao{

    public ZeitraumDao() {
        super(Zeitraum.class);
    }

}
