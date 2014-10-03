package ch.bfh.swos.camerareview.service;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.bfh.swos.camerareview.model.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/serviceContext.xml")
public class CameraDoaTest {

	@Inject
	private ICameraDao cameraDao;
	
	@Test
	public void testStoreNewCamera() {
		Camera camera = cameraDao.create();
		camera.setDescription("Beginner model of Nikon.");
		camera.setModel("Nikon D40");
		camera.setProducer("Nikon");
		camera.setProductionYear(2005);
		try{
			
		
		camera = cameraDao.update(camera);
		
		Camera camera2 = cameraDao.read(camera.getId());
		
		Assert.assertEquals(camera.getId(), camera2.getId());
		}finally{
			cameraDao.delete(camera);
		}
	}

}
