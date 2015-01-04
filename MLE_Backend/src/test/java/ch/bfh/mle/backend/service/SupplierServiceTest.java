package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.model.Role;
import ch.bfh.mle.backend.model.Supplier;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
public class SupplierServiceTest {

    private static final String FIND_BY_EMPLOYEE_ID = "Supplier.FindByEmployeeId";
    private static final String FIND_BY_ROLE_TYPE = "Supplier.FindByRoletype";
    private static final Long EMPLOYEE_ID = 1000L;
    
    SupplierService cut;
    
    @Mock
    EntityManager em;
    
    @Before
    public void init() {
        cut = new SupplierService();
        cut.setEntityManager(em);
    }

    // Mock Typed Query
    void mockNamedQuery(String name, Class c, List<Supplier> results) {
        TypedQuery<Supplier> mockedQuery = mock(TypedQuery.class);
        when(mockedQuery.getResultList()).thenReturn(results);
        when(mockedQuery.setParameter(Matchers.anyString(), Matchers.anyObject())).thenReturn(mockedQuery);
        when(em.createNamedQuery(name, c)).thenReturn(mockedQuery);
    }
    
    @Test
    public void readByEmployeeId(){
        List<Supplier> list = new ArrayList();
        mockNamedQuery(FIND_BY_EMPLOYEE_ID, Supplier.class, list);
        Supplier result;
        // Test null-parameter
        try {
            cut.readByEmployeeId(null);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("EmployeeId cannot be null"));
        }
        // Test empty return result
        result = cut.readByEmployeeId(EMPLOYEE_ID);
        assertSame(null, result);
        // Test sorting
        list.add(createSupplier(EMPLOYEE_ID, "Hans", "Muster"));
        result = cut.readByEmployeeId(EMPLOYEE_ID);
        assertNotNull(result);
        assertSame(list.get(0), result);
        assertEquals("Hans", result.getFirstname());
        assertEquals("Muster", result.getLastname());
        assertEquals(EMPLOYEE_ID, result.getEmployeeID());
        // Test more than one result
        list.add(createSupplier(EMPLOYEE_ID, "Anne", "Meier"));
        list.add(createSupplier(EMPLOYEE_ID, "Fritz", "König"));
        try {
            cut.readByEmployeeId(EMPLOYEE_ID);
        } catch (IllegalStateException e) {
            assertThat(e.getMessage(), is("More than one Supplier found"));
        }
    }
    
    @Test
    public void getTechnicalSupplier(){
        List<Supplier> list = new ArrayList();
        mockNamedQuery(FIND_BY_ROLE_TYPE, Supplier.class, list);
        Supplier result;
        // Test not technical user found
        try {
            cut.getTechnicalSupplier();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage(), is("Technical Supplier not found"));
        }
        // Test one technical user found
        list.add(createSupplier(EMPLOYEE_ID, "Tech", "User"));
        result = cut.getTechnicalSupplier();
        assertNotNull(result);
        assertSame(list.get(0), result);
        assertEquals("Tech", result.getFirstname());
        assertEquals("User", result.getLastname());
        assertEquals(EMPLOYEE_ID, list.get(0).getEmployeeID());
        // Test more then one supplier
        list.add(createSupplier(EMPLOYEE_ID, "Tech", "User"));
        try {
            cut.getTechnicalSupplier();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage(), is("More than one technical Supplier found"));
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
    
}
