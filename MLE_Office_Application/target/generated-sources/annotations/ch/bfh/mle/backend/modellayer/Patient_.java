package ch.bfh.mle.backend.modellayer;

import ch.bfh.mle.backend.modellayer.Behandlungsfall;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-12T15:42:20")
@StaticMetamodel(Patient.class)
public class Patient_ { 

    public static volatile SingularAttribute<Patient, String> lastName;
    public static volatile SingularAttribute<Patient, String> firstName;
    public static volatile SingularAttribute<Patient, Long> patientID;
    public static volatile ListAttribute<Patient, Behandlungsfall> behandlungsfaelle;
    public static volatile SingularAttribute<Patient, Long> id;

}