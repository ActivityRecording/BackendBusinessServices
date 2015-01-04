package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.model.TarmedActivity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import static org.hamcrest.CoreMatchers.is;
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
public class TarmedActivityServiceTest {

    private static final String AUTOMATIC_ACTIVITIES = "TarmedActivity.AutomaticActivities";
    
    TarmedActivityService cut;
    
    @Mock
    EntityManager em;
    
    @Before
    public void init() {
        cut = new TarmedActivityService();
        cut.setEntityManager(em);
    }

    // Mock Typed Query
    void mockNamedQuery(String name, Class c, List<TarmedActivity> results) {
        TypedQuery<TarmedActivity> mockedQuery = mock(TypedQuery.class);
        when(mockedQuery.getResultList()).thenReturn(results);
        when(mockedQuery.setParameter(Matchers.anyString(), Matchers.anyObject())).thenReturn(mockedQuery);
        when(em.createNamedQuery(name, c)).thenReturn(mockedQuery);
    }
    
    @Test
    public void readByEmployeeId(){
        List<TarmedActivity> list = new ArrayList();
        mockNamedQuery(AUTOMATIC_ACTIVITIES, TarmedActivity.class, list);
        List<TarmedActivity> result;
        // Test null-parameter
        try {
            cut.readIdList(null);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("ID-List cannot be null"));
        }
        // Test empty return result
        List<String> param = new ArrayList();
        result = cut.readIdList(param);
        assertNotNull(result);
        assertSame(list, result);
        // Test more than one result
        param.add("00.0010");
        param.add("00.0020");
        list.add(new TarmedActivity());
        list.add(new TarmedActivity());
        result = cut.readIdList(param);
        assertNotNull(result);
        assertSame(list, result);
    }
    
}
