package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.model.TarmedActivity;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * Die Klasse TarmedActivityService stellt Applikationsfunktionalitaeten 
 * fuer den Tarmed-Leistungskatalog zur Verfuegung,
 * @author Stefan Walle
 */
@Stateless
@Named
public class TarmedActivityService extends GenericService{

    /**
     * Kontruktor zum Erstellen eines TarmedActivityService
     */
    public TarmedActivityService() {
        super(TarmedActivity.class);
    }
    
}
