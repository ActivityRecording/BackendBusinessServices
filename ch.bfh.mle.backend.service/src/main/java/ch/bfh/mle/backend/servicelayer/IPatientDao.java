package ch.bfh.mle.backend.servicelayer;

import ch.bfh.mle.backend.modellayer.Patient;

/**
 * DAO(Data Acces Object) Interface für {@link Patient} <br>
 * Erweitert das generelle DAO-Interface {@link IDao}
 * @author Boris Haueter
 */
public interface IPatientDao extends IDao<Patient> {
	
}