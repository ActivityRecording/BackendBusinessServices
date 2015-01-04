package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.service.dto.ActivityCountDto;
import ch.bfh.mle.backend.service.dto.StandardActivitiyListItemDto;
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
public class StandardActivityServiceTest {

    private static final String FIND_BY_EMPLOYEE_ID = "StandardActivity.FindByEmployeeId";
    private static final String ACTIVITY_WITH_COUNT = "Activity.ActivitiesWithCountForTratmendCaseAndEmployee";
    private static final Long EMPLOYEE_ID = 1000L;
    private static final Long TREATMENT_NUMER = 2000L;
    
    StandardActivityService cut;
    
    @Mock
    EntityManager em;
    
    @Before
    public void init() {
        cut = new StandardActivityService();
        cut.setEntityManager(em);
    }

    // Mock Typed Query
    void mockNamedQuery(String name, Class c, List<StandardActivitiyListItemDto> results) {
        TypedQuery<StandardActivitiyListItemDto> mockedQuery = mock(TypedQuery.class);
        when(mockedQuery.getResultList()).thenReturn(results);
        when(mockedQuery.setParameter(Matchers.anyString(), Matchers.anyObject())).thenReturn(mockedQuery);
        when(em.createNamedQuery(name, c)).thenReturn(mockedQuery);
    }

    // Mock Typed Query
    void mockNamedQuery2(String name, Class c, List<ActivityCountDto> results) {
        TypedQuery<ActivityCountDto> mockedQuery = mock(TypedQuery.class);
        when(mockedQuery.getResultList()).thenReturn(results);
        when(mockedQuery.setParameter(Matchers.anyString(), Matchers.anyObject())).thenReturn(mockedQuery);
        when(em.createNamedQuery(name, c)).thenReturn(mockedQuery);
    }
    
    @Test
    public void readByEmployeeId(){
        List<StandardActivitiyListItemDto> list = new ArrayList();
        mockNamedQuery(FIND_BY_EMPLOYEE_ID, StandardActivitiyListItemDto.class, list);
        List<StandardActivitiyListItemDto> result;
        // Test null-parameter
        try {
            cut.readByEmployeeId(null);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("EmployeeId cannot be null"));
        }
        // Test empty return result
        result = cut.readByEmployeeId(EMPLOYEE_ID);
        assertSame(list, result);
        assertEquals(0, result.size());
       // Test more than one result
        list.add(new StandardActivitiyListItemDto(null, "Description1", 1, 1, "00.0010", 5, null, 1, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, 0L));
        list.add(new StandardActivitiyListItemDto(null, "Description2", 1, 1, "00.0020", 5, null, 1, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, 0L));
        result = cut.readByEmployeeId(EMPLOYEE_ID);
        assertNotNull(result);
        assertEquals(2, result.size());
    }
    
    @Test
    public void readByEmployeeAndTreatment(){
        List<StandardActivitiyListItemDto> list = new ArrayList();
        mockNamedQuery(FIND_BY_EMPLOYEE_ID, StandardActivitiyListItemDto.class, list);
        List<ActivityCountDto> list2 = new ArrayList();
        mockNamedQuery2(ACTIVITY_WITH_COUNT, ActivityCountDto.class, list2);
        List<StandardActivitiyListItemDto> result;
        // Test null-parameter
        try {
            cut.readByEmployeeAndTreatment(null, TREATMENT_NUMER);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("EmployeeId cannot be null"));
        }
        try {
            cut.readByEmployeeAndTreatment(EMPLOYEE_ID, null);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("TreatmentNumber cannot be null"));
        }
        // Test empty return result
        result = cut.readByEmployeeAndTreatment(EMPLOYEE_ID, TREATMENT_NUMER);
        assertNotNull(result);
        assertSame(list, result);
        assertEquals(0, result.size());
       // Test more than one result
        list.add(new StandardActivitiyListItemDto(null, "Description1", 1, 1, "00.0010", 5, null, 1, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, 0L));
        list.add(new StandardActivitiyListItemDto(null, "Description2", 1, 1, "00.0020", 5, null, 1, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, 0L));
        result = cut.readByEmployeeAndTreatment(EMPLOYEE_ID, TREATMENT_NUMER);
        assertNotNull(result);
        assertSame(list, result);
        assertEquals(2, result.size());
        // Test results with captured count
        list2.add(new ActivityCountDto("00.0010", 3L));
        list2.add(new ActivityCountDto("00.0020", 12L));
        result = cut.readByEmployeeAndTreatment(EMPLOYEE_ID, TREATMENT_NUMER);
        assertNotNull(result);
        assertSame(list, result);
        assertEquals(2, result.size());
        StandardActivitiyListItemDto dto = result.get(0);
        assertEquals(3, dto.getCapturedCount().intValue());
        dto = result.get(1);
        assertEquals(12, dto.getCapturedCount().intValue());
     }
}
