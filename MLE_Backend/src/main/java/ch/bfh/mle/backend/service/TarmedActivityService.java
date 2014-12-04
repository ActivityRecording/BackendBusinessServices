package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.model.TarmedActivity;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;

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

    public TarmedActivity read(String id) {
        return entityManager.find(TarmedActivity.class, id);
    }
    
    public List<TarmedActivity> readIdList(List<String> idList){
        TypedQuery<TarmedActivity> query = entityManager.createNamedQuery("TarmedActivity.AutomaticActivities", TarmedActivity.class);
        query.setParameter("idList", idList);
        List<TarmedActivity> result;
        result = query.getResultList();
        return result;
    }
    
}
