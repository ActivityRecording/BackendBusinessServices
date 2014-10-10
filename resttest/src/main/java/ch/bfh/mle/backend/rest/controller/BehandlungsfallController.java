package ch.bfh.mle.backend.rest.controller;

import ch.bfh.mle.backend.modellayer.Behandlungsfall;
import ch.bfh.mle.backend.servicelayer.IBehandlungsfallDao;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * REST-Service fuer die Ressource Behandlungsfall
 * @author Stefan Walle
 */
@Controller
@RequestMapping("/behandlungsfall")
public class BehandlungsfallController extends GenericController<Behandlungsfall> {

    @Inject
    public BehandlungsfallController(IBehandlungsfallDao behandlungsfallDao) {
            dao = behandlungsfallDao;
    }

}
