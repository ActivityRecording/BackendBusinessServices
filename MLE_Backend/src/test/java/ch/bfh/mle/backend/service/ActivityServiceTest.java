package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.model.Approval;
import ch.bfh.mle.backend.model.Patient;
import ch.bfh.mle.backend.model.Role;
import ch.bfh.mle.backend.model.Supplier;
import ch.bfh.mle.backend.model.TreatmentCase;
import ch.bfh.mle.backend.service.dto.ActivityDto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Stefan Walle
 */
@RunWith(MockitoJUnitRunner.class)
public class ActivityServiceTest {
    
    private static final String FIND_ALL_ACTIVITIES_BY_TREATMENT_NUMBER = "Activity.FindAllActivitiesByTreatmentNumber";
    private static final String FIND_ALL_ACTIVITIES_BY_TREATMENT_NUMBER_AND_EMPLOYEE = "Activity.FindAllActivitiesByTreatmentNumberAndEmployee";
    private static final String SUPPLIER_FIND_BY_EMPLOYEE_ID = "Supplier.FindByEmployeeId";
    private static final String TRATEMENT_FIND_BY_TREATMENT_NUMBER = "TreatmentCase.FindByTreatmentNumber";
    private static final Long TREATMENT_NUMER = 2000L;
    private static final Long EMPLOYEE_ID = 1000L;
    private static final Long ID = 1L;
    
    ActivityService cut;
    SupplierService supplierService;
    TarmedActivityService tarmedService;
    TreatmentCaseService treatmentService;
    TimePeriodService timePeriodService;
    
    @Mock
    EntityManager em;
    
    @Before
    public void init() {
        cut = new ActivityService();
        supplierService = new SupplierService();
        tarmedService = new TarmedActivityService();
        treatmentService = new TreatmentCaseService();
        timePeriodService = new  TimePeriodService();
        supplierService.setEntityManager(em);
        tarmedService.setEntityManager(em);
        treatmentService.setEntityManager(em);
        supplierService.setEntityManager(em);
        treatmentService.setEntityManager(em);
        cut.setEntityManager(em);
        cut.setSupplierService(supplierService);
        cut.setTarmedService(tarmedService);
        cut.setTimePeriodService(timePeriodService);
        cut.setTreatmentService(treatmentService);
    }

    // Mock Typed Query
    void mockNamedQuery(String name, Class c, List<ActivityDto> results) {
        TypedQuery<ActivityDto> mockedQuery = mock(TypedQuery.class);
        when(mockedQuery.getResultList()).thenReturn(results);
        when(mockedQuery.setParameter(Matchers.anyString(), Matchers.anyObject())).thenReturn(mockedQuery);
        when(em.createNamedQuery(name, c)).thenReturn(mockedQuery);
    }

    void mockNamedQuery1(String name, Class c, List<Supplier> results) {
        TypedQuery<Supplier> mockedQuery = mock(TypedQuery.class);
        when(mockedQuery.getResultList()).thenReturn(results);
        when(mockedQuery.setParameter(Matchers.anyString(), Matchers.anyObject())).thenReturn(mockedQuery);
        when(em.createNamedQuery(name, c)).thenReturn(mockedQuery);
    }

    void mockNamedQuery2(String name, Class c, List<TreatmentCase> results) {
        TypedQuery<TreatmentCase> mockedQuery = mock(TypedQuery.class);
        when(mockedQuery.getResultList()).thenReturn(results);
        when(mockedQuery.setParameter(Matchers.anyString(), Matchers.anyObject())).thenReturn(mockedQuery);
        when(em.createNamedQuery(name, c)).thenReturn(mockedQuery);
    }
    
    //@Test
    public void readAllByTreatmentNumber(){
        ArrayList<ActivityDto> list = new ArrayList();
        mockNamedQuery(FIND_ALL_ACTIVITIES_BY_TREATMENT_NUMBER, ActivityDto.class, list);
        List<ActivityDto> result;
        // Test null-parameter
        try {
            cut.readAllByTreatmentNumber(null);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("TreatmentNumber cannot be null"));
        }
        // Test empty return result
        result = cut.readAllByTreatmentNumber(TREATMENT_NUMER);
        assertSame(list, result);
        // Test sorting
        list.add(new ActivityDto(ID + 1, 1, EMPLOYEE_ID, "Caaaa", "Caaaa", "00.0030", TREATMENT_NUMER, "Desciption", 5));
        list.add(new ActivityDto(ID, 1, EMPLOYEE_ID, "Aaaaa", "Aaaaa", "00.0010", TREATMENT_NUMER, "Desciption", 5));
        list.add(new ActivityDto(ID + 2, 1, EMPLOYEE_ID, "Baaaa", "Baaaa", "00.0020", TREATMENT_NUMER, "Desciption", 5));
        result = cut.readAllByTreatmentNumber(TREATMENT_NUMER);
        assertSame(list, result);
        assertEquals("00.0010", result.get(0).getTarmedActivityId());
        assertEquals("00.0020", result.get(1).getTarmedActivityId());
        assertEquals("00.0030", result.get(2).getTarmedActivityId());
    }
    
    //@Test
    public void readAllByTreatmentAndEmployee(){
        ArrayList<ActivityDto> list = new ArrayList();
        mockNamedQuery(FIND_ALL_ACTIVITIES_BY_TREATMENT_NUMBER_AND_EMPLOYEE, ActivityDto.class, list);
        List<ActivityDto> result;
        // Test null-parameter
        try {
            cut.readAllByTreatmentAndEmployee(null, EMPLOYEE_ID);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("TreatmentNumber cannot be null"));
        }
        try {
            cut.readAllByTreatmentAndEmployee(TREATMENT_NUMER, null);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("EmployeeId cannot be null"));
        }
        // Test empty return result
        result = cut.readAllByTreatmentAndEmployee(TREATMENT_NUMER, EMPLOYEE_ID);
        assertSame(list, result);
        // Test sorting
        list.add(new ActivityDto(ID + 1, 1, EMPLOYEE_ID, "Caaaa", "Caaaa", "00.0030", TREATMENT_NUMER, "Desciption", 5));
        list.add(new ActivityDto(ID, 1, EMPLOYEE_ID, "Aaaaa", "Aaaaa", "00.0010", TREATMENT_NUMER, "Desciption", 5));
        list.add(new ActivityDto(ID + 2, 1, EMPLOYEE_ID, "Baaaa", "Baaaa", "00.0020", TREATMENT_NUMER, "Desciption", 5));
        result = cut.readAllByTreatmentAndEmployee(TREATMENT_NUMER, EMPLOYEE_ID);
        assertSame(list, result);
        assertEquals("00.0010", result.get(0).getTarmedActivityId());
        assertEquals("00.0020", result.get(1).getTarmedActivityId());
        assertEquals("00.0030", result.get(2).getTarmedActivityId());
    }
    
    @Test
    public void create(){
        List<Supplier> suppliers = new ArrayList();
        mockNamedQuery1(SUPPLIER_FIND_BY_EMPLOYEE_ID, Supplier.class, suppliers);
        List<TreatmentCase> cases = new ArrayList();
        mockNamedQuery2(TRATEMENT_FIND_BY_TREATMENT_NUMBER, TreatmentCase.class, cases);
        // Test Employeeid and/or TreatmentNumber null
        ActivityDto dto = new ActivityDto(null, 1, null, "Hans", "Meier", "00.0010", null, null, null);
        try {
            cut.create(dto);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("EmployeeId and Treatmentnumber must not be null"));
        }
        dto = new ActivityDto(null, 1, EMPLOYEE_ID, "Hans", "Meier", "00.0010", null, null, null); 
        try {
            cut.create(dto);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("EmployeeId and Treatmentnumber must not be null"));
        }
        dto = new ActivityDto(null, 1, null, "Hans", "Meier", "00.0010", TREATMENT_NUMER, null, null); 
        try {
            cut.create(dto);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("EmployeeId and Treatmentnumber must not be null"));
        }
        // Test no Supplier
        dto = new ActivityDto(null, 1, EMPLOYEE_ID, "Hans", "Meier", "00.0010", TREATMENT_NUMER, null, null); 
        TreatmentCase c = createTreatmentCase(TREATMENT_NUMER);
        cases.add(c);
        suppliers.clear();
        try {
            cut.create(dto);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("No Supplier found with employeeId 1000"));
        }
        // Test no TreatmentCase
        Supplier s = createSupplier(EMPLOYEE_ID, "Anne", "Meier");
        suppliers.add(s);
        cases.clear();
        try {
            cut.create(dto);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("No Treatmentcase found with treatmentNumber 2000"));
        }
        // Test TreatmentCase released
        cases.clear();
        suppliers.clear();
        cases.add(c);
        suppliers.add(s);
        c.setReleased(Boolean.TRUE);
        try {
            cut.create(dto);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Treatmentcase must not be released"));
        }
        c.setReleased(Boolean.FALSE);
        // Test invalid Tarmed number
        dto = new ActivityDto(null, 1, EMPLOYEE_ID, "Hans", "Meier", null, TREATMENT_NUMER, null, null); 
        try {
            cut.create(dto);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Tarmedid must not be null"));
        }
        // Test invalid number of activities
        dto = new ActivityDto(null, 0, EMPLOYEE_ID, "Hans", "Meier", "00.0010", TREATMENT_NUMER, null, null); 
        try {
            cut.create(dto);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Number of activities must be greater than 0"));
        }
        dto = new ActivityDto(null, null, EMPLOYEE_ID, "Hans", "Meier", "00.0010", TREATMENT_NUMER, null, null); 
        try {
            cut.create(dto);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Number of activities must be greater than 0"));
        }
    }
    
    private Supplier createSupplier(Long employeeId, String firstName, String lastName){
        Supplier supplier = new Supplier();
        supplier.setEmployeeID(employeeId);
        supplier.setFirstname(firstName);
        supplier.setLastname(lastName);
        supplier.setRole(new Role());
        return supplier;
    }
    
    private TreatmentCase createTreatmentCase(Long treatmentNumber){
        Patient patient = new Patient();
        TreatmentCase c = new TreatmentCase(patient);
        c.setEndTime(new Date());
        c.setStartTime(new Date());
        c.setReleased(Boolean.FALSE);
        c.setTreatmentNumber(treatmentNumber);
        return c;
    }
}
