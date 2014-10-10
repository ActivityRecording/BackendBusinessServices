package ch.bfh.mle.backend.servicelayer;

import ch.bfh.mle.backend.modellayer.Behandlungsfall;
import javax.inject.Named;

/**
 * Erweiterung des generischen DAO (Data Access Objects) {@link GenericJpaDao} fuer Patient.
 * Erweitert {@link GenericJpaDao} und implementiert {@link IBehandlungsfallDao}.
 * @author Stefan Walle
 */
@Named
public class BehandlungsfallDao extends GenericJpaDao<Behandlungsfall> implements IBehandlungsfallDao {

    public BehandlungsfallDao() {
        super(Behandlungsfall.class);
    }
    
}
