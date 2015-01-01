package ch.bfh.mle.backend.rest;

import ch.bfh.mle.backend.model.Patient;
import ch.bfh.mle.backend.service.PatientService;
import ch.bfh.mle.backend.service.dto.PatientListItemDto;
import ch.bfh.mle.backend.service.dto.PatientWithTreatementCaseDto;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service fuer die Ressource Patient
 *
 * @author Stefan Walle - Boris Haueter
 */
@Stateless
@Path("patients")
public class PatientResource {

    /**
     * Erstellt eine neue Instanz von PatientResource
     */
    public PatientResource() {}

    @Context
    private UriInfo context;

    @Inject
    private PatientService srv;

    /**
     * Speichert einen neuen Patienten in die Datenbank.
     * Die id des Patienten wird automatisch vergeben und muss null sein.
     * Die Patientennummer ist unique und darf nicht auf der DB vorhanden sein.
     * @param entity 
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Patient entity) {
        srv.create(entity);
    }

    /**
     * Mutiert einen Patienten. Der Patient muss auf der DB vorhanden sein.
     * @param entity
     * @return Patient mit den gemachten Aenderungen
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Patient update(Patient entity) {
        return srv.update(entity);
    }

    /**
     * Gibt einen Patienten aufgrund der Datenbank-ID zurueck.
     * @param id
     * @return Patient
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Patient get(@PathParam("id") @NotNull Long id) {
        return srv.read(id);
    }

    /**
     * Gibt Patient- und Behandlungsfallinformationen zurueck zu allen nicht 
     * freigegebenen Behandlungsfaellen. Mit Hilfe des Status koennen die 
     * Behandlungsfaelle eingeschraenkt werden.
     * Status 0 = Alle
     * Status 1 = Behandlungsfaelle ohne erfasste Leistungen
     * Status 2 = Behandlungsfaelle mit erfassten Leistungen
     * @param state Status zur Einschraenkung der Behandlungsfaelle
     * @return Liste von Patienten- und Behandlungsfallinformationen
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(
        @DefaultValue("0") @QueryParam("state") @NotNull Integer state) {
        if(state < 0 || state > 2) {
            //Status ungueltig -> return HTTP Status 400
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        List<PatientListItemDto> dtos;
        dtos = srv.readAll(state);
        GenericEntity entity = new GenericEntity<List<PatientListItemDto>>(dtos) {};
        return Response.ok(entity).build();
    }

    /**
     * Gibt einen Patienten aufgrund der Behandlungsfallnummer zurueck.
     * @param patientNr Fachliche Patientennummer
     * @return Patient
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/patient/{patientNr}")
    public Patient getByPatientNumber(@PathParam("patientNr") @NotNull Long patientNr ){
        return srv.readByPatientNumber(patientNr);
    }

    /**
     * Gibt Informationen zu einem Patienten und Behandlungsfall mit
     * Behandlungsfallnummer treatmentNr zurueck.
     * @param treatementNr
     * @return PatientWithTreatementCaseDto
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/treatment/{treatmentNr}")
    public PatientWithTreatementCaseDto getByTreatment(@PathParam("treatmentNr") @NotNull Long treatementNr ) {
        return srv.readByTreatmentNumber(treatementNr);
    }

    /**
     * Gibt Informationen zu einem Patienten und Behandlungsfall mit
     * Behandlungsfallnummer treatmentNr und Leistungserbringer employeeId zurueck.
     * @param treatementNr
     * @param employeeId
     * @return PatientWithTreatementCaseDto
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/treatment/{treatmentNr}/{employeeId}")
    public PatientWithTreatementCaseDto getByTreatment(@PathParam("treatmentNr") @NotNull Long treatementNr, @PathParam("employeeId") @NotNull Long employeeId) {
        return srv.readByTreatmentNumberAndEmployeeId(treatementNr, employeeId);
    }
    
    /**
     * Gibt Patient- und Behandlungsfallinformationen zurueck zu allen nicht 
     * freigegebenen Behandlungsfaellen, zu denen ein Leistungserbringer mit der 
     * Mitarbeiternummer employeeId Zeitraeume gemessen hat. Mit Hilfe des  
     * Status koennen die Behandlungsfaelle eingeschraenkt werden.
     * Status 0 = Alle
     * Status 1 = Behandlungsfaelle ohne erfasste Leistungen
     * Status 2 = Behandlungsfaelle mit erfassten Leistungen
     * @param employeeId Mitarbeiternummer des Leistungserbringers
     * @param state Status zur Einschraenkung der Behandlungsfaelle
     * @return Liste von Patienten- und Behandlungsfallinformationen
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/supplier/{employeeId}")
    public Response getBySupplier(
        @PathParam("employeeId") @NotNull Long employeeId,
        @DefaultValue("0") @QueryParam("state") @NotNull Integer state) {
        if(state < 0 || state > 2) {
            //Status ungueltig -> return HTTP Status 400
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        List<PatientListItemDto> dtos = srv.readByEmployeeId(employeeId, state);
        GenericEntity entity = new GenericEntity<List<PatientListItemDto>>(dtos) {};
        return Response.ok(entity).build();
    }
    
    /**
     * Loescht einen Patienten aus der Datenbank.
     * Der Patient muss auf der Datenbank vorhanden sein.
     * @param id 
     */
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") @NotNull Long id) {
        srv.delete(id);
    }
}
