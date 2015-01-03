package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.model.Patient;
import ch.bfh.mle.backend.model.Supplier;
import ch.bfh.mle.backend.model.TreatmentCase;
import ch.bfh.mle.backend.service.dto.ActivityDto;
import java.util.ArrayList;
import java.util.Date;
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
public class TreatmentCaseServiceTest {
   
    private static final String FIND_BY_TREATMENT_NUMBER = "TreatmentCase.FindByTreatmentNumber";
    private static final Long TREATMENT_NUMER = 2000L;
    
    TreatmentCaseService cut;
    
    @Mock
    EntityManager em;
    
    @Mock
    TypedQuery<TreatmentCase> typedQuery;

    @Before
    public void init() {
        cut = new TreatmentCaseService();
        cut.setEntityManager(em);
    }

    // Mock Typed Query
    void mockNamedQuery(String name, Class c, List<TreatmentCase> results) {
        TypedQuery<TreatmentCase> mockedQuery = mock(TypedQuery.class);
        when(mockedQuery.getResultList()).thenReturn(results);
        when(mockedQuery.setParameter(Matchers.anyString(), Matchers.anyObject())).thenReturn(mockedQuery);
        when(em.createNamedQuery(name, c)).thenReturn(mockedQuery);
    }
    
    @Test
    public void readByTreatmentNumber(){
        List<TreatmentCase> list = new ArrayList();
        mockNamedQuery(FIND_BY_TREATMENT_NUMBER, TreatmentCase.class, list);
        TreatmentCase result;
        // Test null-parameter
        try {
            cut.readByTreatmentNumber(null);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("TreatementNumer cannot be null"));
        }
        // Test empty return result
        result = cut.readByTreatmentNumber(TREATMENT_NUMER);
        assertSame(null, result);
        // Test sorting
        TreatmentCase c = createTreatmentCase(TREATMENT_NUMER);
        list.add(c);
        result = cut.readByTreatmentNumber(TREATMENT_NUMER);
        assertNotNull(result);
        assertEquals(list.size(), 1);
        assertSame(list.get(0), result);
        assertEquals(list.get(0).getTreatmentNumber(), TREATMENT_NUMER);
        // Test more than one result
        list.add(createTreatmentCase(TREATMENT_NUMER));
        try {
            cut.readByTreatmentNumber(TREATMENT_NUMER);
        } catch (IllegalStateException e) {
            assertThat(e.getMessage(), is("More than one Treatmentcase found"));
        }
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
