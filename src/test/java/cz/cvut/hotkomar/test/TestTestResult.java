/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.test;

import cz.cvut.hotkomar.model.entity.TestResult;
import cz.cvut.hotkomar.service.manager.TestResultMan;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Marie Hoťková
 */
@Transactional
public class TestTestResult extends GenericTest<TestResult> {
    @Autowired
    private TestResultMan testResultMan;
    
    @PostConstruct
    public void init(){
        super.setManager(testResultMan);
        super.setClazz(TestResult.class);
    }

    @Override
    public void addEntity() {
           List<TestResult> findAll1 = manager.findAll();
            TestResult o = new TestResult();
            o.setVisible(Boolean.TRUE);
            manager.add(o, false);
            List<TestResult> findAll2 = manager.findAll();
             assertEquals("mistake addEntity TestSubject",findAll1.size()+1, findAll2.size());
    }
    
    
    
    public void editEntity() {
        List<TestResult> findAll1 = manager.findAll();
            if(!findAll1.isEmpty()){
                TestResult o = findAll1.get(0);
                o.setMark((short)1);
                o.setPercent(1.0);
                
                
                
                manager.edit(o, false);
                TestResult e = manager.findById(o.getId());
              assertEquals("mistake edit mark",e.getMark(),o.getMark());
              assertEquals("mistake edit percent",e.getPercent(),o.getPercent());
               
            } else {
                assertEquals(true, false);
            }
    }
}
