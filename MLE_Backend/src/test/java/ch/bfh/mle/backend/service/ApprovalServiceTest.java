package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.model.Approval;
import ch.bfh.mle.backend.model.Patient;
import ch.bfh.mle.backend.model.Role;
import ch.bfh.mle.backend.model.Supplier;
import ch.bfh.mle.backend.model.TreatmentCase;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Stefan Walle
 */
@RunWith(MockitoJUnitRunner.class)
public class ApprovalServiceTest {
    
    private static final String APPROVAL_SELECT_BY_TREATMENT_ID_AND_EMPLOYEE_ID = "Approval.SelectByTreatmentIdAndEmployeeId";
    private static final String SUPPLIER_FIND_BY_EMPLOYEE_ID = "Supplier.FindByEmployeeId";
    private static final String TRATEMENT_FIND_BY_TREATMENT_NUMBER = "TreatmentCase.FindByTreatmentNumber";
    private static final Long EMPLOYEE_ID = 1000L;
    private static final Long TREATMENT_NUMER = 2000L;

    ApprovalService cut;
    SupplierService supplierService;
    TreatmentCaseService treatmentService;
    
    @Mock
    EntityManager em;
    
    @Before
    public void init() {
        cut = new ApprovalService();
        supplierService = new SupplierService();
        treatmentService = new TreatmentCaseService();
        supplierService.setEntityManager(em);
        treatmentService.setEntityManager(em);
        cut.setEntityManager(em);
        cut.setSupplierService(supplierService);
        cut.setTreatmentService(treatmentService);
    }

    // Mock Typed Query
    void mockNamedQuery(String name, Class c, List<Approval> results) {
        TypedQuery<Approval> mockedQuery = mock(TypedQuery.class);
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

    @Test
    public void readByEmpolyeeIdAndTreatmentNumber(){
        List<Approval> list = new ArrayList();
        mockNamedQuery(APPROVAL_SELECT_BY_TREATMENT_ID_AND_EMPLOYEE_ID, Approval.class, list);
        List<Supplier> suppliers = new ArrayList();
        mockNamedQuery1(SUPPLIER_FIND_BY_EMPLOYEE_ID, Supplier.class, suppliers);
        List<TreatmentCase> cases = new ArrayList();
        mockNamedQuery2(TRATEMENT_FIND_BY_TREATMENT_NUMBER, TreatmentCase.class, cases);
        Approval result;
        // Test null-parameter
        try {
            cut.readByEmpolyeeIdAndTreatmentNumber(EMPLOYEE_ID, null);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("TreatmentNumber cannot be null"));
        }
        try {
            cut.readByEmpolyeeIdAndTreatmentNumber(null, TREATMENT_NUMER);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("EmployeeId cannot be null"));
        }
        // Test no Supplier
        try {
            cut.readByEmpolyeeIdAndTreatmentNumber(EMPLOYEE_ID, TREATMENT_NUMER);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("No Supplier found with employeeId 1000"));
        }
        // Test no TreatmentCase
        suppliers.add(createSupplier(EMPLOYEE_ID, "Anne", "Meier"));
        try {
            cut.readByEmpolyeeIdAndTreatmentNumber(EMPLOYEE_ID, TREATMENT_NUMER);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("No Treatmentcase found with treatmentNumber 2000"));
        }
        // Test empty result
        cases.add(createTreatmentCase(TREATMENT_NUMER));
        result = cut.readByEmpolyeeIdAndTreatmentNumber(EMPLOYEE_ID, TREATMENT_NUMER);
        assertSame(null, result);
        // Test one Approval found
        Approval a = new Approval();
        list.add(a);
        result = cut.readByEmpolyeeIdAndTreatmentNumber(EMPLOYEE_ID, TREATMENT_NUMER);
        assertSame(a, result);
        // Test more than one Approval
        list.add(new Approval());
        try {
            cut.readByEmpolyeeIdAndTreatmentNumber(EMPLOYEE_ID, TREATMENT_NUMER);
        } catch (IllegalStateException e) {
            assertThat(e.getMessage(), is("More than one approval found"));
        }
        // Test more than one Supplier
        list.remove(1);
        suppliers.add(createSupplier(EMPLOYEE_ID, "Hans", "Muster"));
        try {
            cut.readByEmpolyeeIdAndTreatmentNumber(EMPLOYEE_ID, TREATMENT_NUMER);
        } catch (IllegalStateException e) {
            assertThat(e.getMessage(), is("More than one Supplier found"));
        }
        // Test more than one TreatmentCase
        suppliers.remove(1);
        cases.add(createTreatmentCase(TREATMENT_NUMER));
        try {
            cut.readByEmpolyeeIdAndTreatmentNumber(EMPLOYEE_ID, TREATMENT_NUMER);
        } catch (IllegalStateException e) {
            assertThat(e.getMessage(), is("More than one Treatmentcase found"));
        }
    }
    
    @Test
    public void approve(){
        List<Approval> list = new ArrayList();
        mockNamedQuery(APPROVAL_SELECT_BY_TREATMENT_ID_AND_EMPLOYEE_ID, Approval.class, list);
        List<Supplier> suppliers = new ArrayList();
        mockNamedQuery1(SUPPLIER_FIND_BY_EMPLOYEE_ID, Supplier.class, suppliers);
        List<TreatmentCase> cases = new ArrayList();
        mockNamedQuery2(TRATEMENT_FIND_BY_TREATMENT_NUMBER, TreatmentCase.class, cases);
        // Test null-parameter
        try {
            cut.approve(EMPLOYEE_ID, null);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("TreatmentNumber cannot be null"));
        }
        try {
            cut.approve(null, TREATMENT_NUMER);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("EmployeeId cannot be null"));
        }
        // Test no Supplier
        TreatmentCase c = createTreatmentCase(TREATMENT_NUMER);
        cases.add(c);
        suppliers.clear();
        try {
            cut.approve(EMPLOYEE_ID, TREATMENT_NUMER);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("No Supplier found with employeeId 1000"));
        }
        // Test no TreatmentCase
        Supplier s = createSupplier(EMPLOYEE_ID, "Anne", "Meier");
        suppliers.add(s);
        cases.clear();
        try {
            cut.approve(EMPLOYEE_ID, TREATMENT_NUMER);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("No Treatmentcase found with treatmentNumber 2000"));
        }
        // Test TreatementCase allready approved by Employee
        cases.clear();
        suppliers.clear();
        cases.add(c);
        suppliers.add(s);
        Approval a = new Approval();
        list.add(a);
        try {
            cut.approve(EMPLOYEE_ID, TREATMENT_NUMER);
        } catch (IllegalStateException e) {
            assertThat(e.getMessage(), is("Approval already exists"));
        }
        // Test TreatmentCase released
        list.clear();
        c.setReleased(Boolean.TRUE);
       try {
            cut.approve(EMPLOYEE_ID, TREATMENT_NUMER);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Treatmentcase must not be released"));
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
