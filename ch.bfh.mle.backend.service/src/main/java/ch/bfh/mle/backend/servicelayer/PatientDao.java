package ch.bfh.mle.backend.servicelayer;

import javax.inject.Named;
import ch.bfh.mle.backend.modellayer.Patient;

/**
 * Erweiterung des generischen DAO (Data Access Objects) {@link GenericJpaDao} fuer Patient.
 * Erweitert {@link GenericJpaDao} und implementiert {@link IPatientDao}.
 * @author Boris Haueter
 */
@Named
public class PatientDao extends GenericJpaDao<Patient> implements IPatientDao {
   
    public PatientDao() {
        super(Patient.class);
    }
    
}
