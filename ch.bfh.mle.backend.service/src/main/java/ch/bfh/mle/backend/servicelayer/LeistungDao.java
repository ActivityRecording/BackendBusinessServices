package ch.bfh.mle.backend.servicelayer;

import ch.bfh.mle.backend.modellayer.Leistung;
import javax.inject.Named;

/**
 * Erweiterung des generischen DAO (Data Access Objects) {@link GenericJpaDao} fuer Leistung.
 * Erweitert {@link GenericJpaDao} und implementiert {@link ILeistungDao}.
 * @author Stefan Walle
 */
@Named
public class LeistungDao extends GenericJpaDao<Leistung> implements ILeistungDao{
    
    public LeistungDao() {
        super(Leistung.class);
    }
    
    
}
