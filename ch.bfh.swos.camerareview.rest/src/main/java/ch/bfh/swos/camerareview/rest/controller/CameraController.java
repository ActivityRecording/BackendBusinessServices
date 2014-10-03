package ch.bfh.swos.camerareview.rest.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.bfh.swos.camerareview.model.Camera;
import ch.bfh.swos.camerareview.service.ICameraDao;

@Controller
@RequestMapping("/cameras")
public class CameraController extends GenericController<Camera> {
	
	@Inject
	public CameraController(ICameraDao cameraDao) {
		dao = cameraDao;
	}
}
