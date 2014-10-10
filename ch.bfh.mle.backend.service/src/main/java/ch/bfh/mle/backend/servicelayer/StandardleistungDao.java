package ch.bfh.mle.backend.servicelayer;

import ch.bfh.mle.backend.modellayer.Standardleistung;
import javax.inject.Named;

/**
 * Erweiterung des generischen DAO (Data Access Objects) {@link GenericJpaDao} fuer Standardleistung.
 * Erweitert {@link GenericJpaDao} und implementiert {@link IStandardleistungDao}.
 * @author Stefan Walle
 */
@Named
public class StandardleistungDao extends GenericJpaDao<Standardleistung> implements IStandardleistungDao{
    
    public StandardleistungDao() {
        super(Standardleistung.class);
    }
   
}
