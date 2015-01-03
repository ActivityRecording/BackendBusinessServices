package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.service.dto.ActivityDto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
//@RunWith(MockitoJUnitRunner.class)
public class ApprovalServiceTest {
    
    private static final String APPROVAL_SELECT_BY_TREATMENT_ID_AND_EMPLOYEE_ID = "Approval.SelectByTreatmentIdAndEmployeeId";

//    ApprovalService cut;
//    SupplierService supplierService;
//    TreatmentCaseService treatmentService;
//    
//    @Mock
//    EntityManager em;
//    
//    @Mock
//    TypedQuery<ActivityDto> typedQuery;
//
//    @Before
//    public void init() {
//        cut = new ApprovalService();
//        supplierService = new SupplierService();
//        treatmentService = new TreatmentCaseService();
//        cut.setEntityManager(em);
//        supplierService.setEntityManager(em);
//        treatmentService.setEntityManager(em);
//        cut.setSupplierService(supplierService);
//        cut.setTreatmentService(treatmentService);
//    }
//
//    // Mock Typed Query
//    void mockNamedQuery(String name, Class c, List<ActivityDto> results) {
//        TypedQuery<ActivityDto> mockedQuery = mock(TypedQuery.class);
//        when(mockedQuery.getResultList()).thenReturn(results);
//        when(mockedQuery.setParameter(Matchers.anyString(), Matchers.anyObject())).thenReturn(mockedQuery);
//        when(em.createNamedQuery(name, c)).thenReturn(mockedQuery);
//    }
//    
//    @Test
//    public void readByEmpolyeeIdAndTreatmentNumber(){
//        ArrayList<ActivityDto> list = new ArrayList();
//        mockNamedQuery(FIND_ALL_ACTIVITIES_BY_TREATMENT_NUMBER, ActivityDto.class, list);
//        List<ActivityDto> result;
//        
//    }

    
}
