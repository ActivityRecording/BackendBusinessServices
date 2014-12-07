package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.service.dto.ActivityDto;
import java.util.ArrayList;
import java.util.List;
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
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Stefan
 */
@RunWith(MockitoJUnitRunner.class)
public class ActivityServiceTest {
    
    private static final String FIND_ALL_ACTIVITIES_BY_TREATMENT_NUMBER = "Activity.FindAllActivitiesByTreatmentNumber";
    private static final String FIND_ALL_ACTIVITIES_BY_TREATMENT_NUMBER_AND_EMPLOYEE = "Activity.FindAllActivitiesByTreatmentNumberAndEmployee";
    private static final Long TREATMENT_NUMER = 2000L;
    private static final Long EMPLOYEE_ID = 1000L;
    private static final Long ID = 1L;
    
    ActivityService cut;
    
    @Mock
    EntityManager em;
    
    @Mock
    TypedQuery<ActivityDto> typedQuery;

    @Before
    public void init() {
        cut = new ActivityService();
        cut.setEntityManager(em);
    }

    // Mock Typed Query
    void mockNamedQuery(String name, Class c, List<ActivityDto> results) {
        TypedQuery<ActivityDto> mockedQuery = mock(TypedQuery.class);
        when(mockedQuery.getResultList()).thenReturn(results);
        when(mockedQuery.setParameter(Matchers.anyString(), Matchers.anyObject())).thenReturn(mockedQuery);
        when(em.createNamedQuery(name, c)).thenReturn(mockedQuery);
    }
    
    @Test
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
        assertEquals(list.get(0).getTarmedActivityId(), "00.0010");
        assertEquals(list.get(1).getTarmedActivityId(), "00.0020");
        assertEquals(list.get(2).getTarmedActivityId(), "00.0030");
    }
    
    @Test
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
        assertEquals(list.get(0).getTarmedActivityId(), "00.0010");
        assertEquals(list.get(1).getTarmedActivityId(), "00.0020");
        assertEquals(list.get(2).getTarmedActivityId(), "00.0030");
    }
}
