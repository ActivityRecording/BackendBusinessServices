package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.model.TarmedActivity;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author Stefan Walle
 */
@Stateless
@Named
public class TarmedActivityService extends GenericService{

    public TarmedActivityService() {
        super(TarmedActivity.class);
    }
    
}
