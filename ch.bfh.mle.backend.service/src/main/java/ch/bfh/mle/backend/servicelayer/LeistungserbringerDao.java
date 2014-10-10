package ch.bfh.mle.backend.servicelayer;

import ch.bfh.mle.backend.modellayer.Leistungserbringer;
import javax.inject.Named;

/**
 * Erweiterung des generischen DAO (Data Access Objects) {@link GenericJpaDao} fuer Leistungserbringer.
 * Erweitert {@link GenericJpaDao} und implementiert {@link ILeistungserbringerDao}.
 * @author Stefan Walle
 */
@Named
public class LeistungserbringerDao extends GenericJpaDao<Leistungserbringer> implements ILeistungserbringerDao {

    public LeistungserbringerDao() {
        super(Leistungserbringer.class);
    }
    
}
