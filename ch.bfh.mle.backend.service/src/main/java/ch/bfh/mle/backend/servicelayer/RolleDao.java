package ch.bfh.mle.backend.servicelayer;

import ch.bfh.mle.backend.modellayer.Rolle;
import javax.inject.Named;

/**
 * Erweiterung des generischen DAO (Data Access Objects) {@link GenericJpaDao} fuer Rolle.
 * Erweitert {@link GenericJpaDao} und implementiert {@link IRolleDao}.
 * @author Stefan Walle
 */
@Named
public class RolleDao extends GenericJpaDao<Rolle> implements IRolleDao{
    
    public RolleDao() {
        super(Rolle.class);
    }
    
}
