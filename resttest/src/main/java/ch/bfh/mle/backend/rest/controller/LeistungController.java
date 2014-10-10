package ch.bfh.mle.backend.rest.controller;

import ch.bfh.mle.backend.modellayer.Leistung;
import ch.bfh.mle.backend.servicelayer.ILeistungDao;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * REST-Service fuer die Ressource Leistung
 * @author Stefan Walle
 */
@Controller
@RequestMapping("/leistung")
public class LeistungController extends GenericController<Leistung> {

    @Inject
    public LeistungController(ILeistungDao leistungDao) {
            dao = leistungDao;
    }
}
