package ch.bfh.mle.backend.model;

import ch.bfh.mle.backend.model.Behandlungsfall;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-15T17:02:50")
@StaticMetamodel(Patient.class)
public class Patient_ { 

    public static volatile SingularAttribute<Patient, String> lastName;
    public static volatile SingularAttribute<Patient, String> firstName;
    public static volatile SingularAttribute<Patient, Long> patientID;
    public static volatile ListAttribute<Patient, Behandlungsfall> behandlungsfaelle;
    public static volatile SingularAttribute<Patient, Long> id;

}