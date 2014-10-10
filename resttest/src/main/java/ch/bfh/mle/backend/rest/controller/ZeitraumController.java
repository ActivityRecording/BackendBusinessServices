package ch.bfh.mle.backend.rest.controller;

import ch.bfh.mle.backend.modellayer.Zeitraum;
import ch.bfh.mle.backend.servicelayer.IZeitraumDao;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * REST-Service fuer die Ressource Zeitraum
 * @author Stefan Walle
 */
@Controller
@RequestMapping("/zeitraum")
public class ZeitraumController extends GenericController<Zeitraum> {

    @Inject
    public ZeitraumController(IZeitraumDao zeitraumDao) {
            dao = zeitraumDao;
    }
    
}
