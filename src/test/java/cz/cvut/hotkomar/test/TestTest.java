/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.test;

import cz.cvut.hotkomar.model.entity.Test;
import cz.cvut.hotkomar.service.manager.TestMan;
import java.util.List;
import javax.annotation.PostConstruct;
import static org.junit.Assert.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author maru
 */
@Transactional
public class TestTest extends GenericTest<Test> {
    @Autowired
    private TestMan testMan;
    
    @PostConstruct
    public void init(){
        super.setManager(testMan);
        super.setClazz(Test.class);
    }

    @Override
    public void addEntity() {
           List<Test> findAll1 = manager.findAll();
            Test o = new Test();
            o.setVisible(Boolean.TRUE);
            manager.add(o, false);
            List<Test> findAll2 = manager.findAll();
             assertEquals("mistake addEntity TestTest",findAll1.size()+1, findAll2.size());
    }
    
    
    
    public void editEntity() {
        List<Test> findAll1 = manager.findAll();
            if(!findAll1.isEmpty()){
                Test o = findAll1.get(0);
                o.setFive((short)5);
                o.setFour((short)4);
                o.setThree((short)3);
                o.setTwo((short)2);
                o.setName("pocty");
                o.setPassword("heslo");
                o.setTaught(Boolean.TRUE);
                
                
                
                manager.edit(o, false);
                Test e = manager.findById(o.getId());
               assertEquals("mistake edit five",e.getFive(),o.getFive());
               assertEquals("mistake edit four",e.getThree(),o.getThree());
               assertEquals("mistake edit three",e.getThree(),o.getThree());
               assertEquals("mistake edit two",e.getTwo(),o.getTwo());
               assertEquals("mistake edit name",e.getName(),o.getName());
               assertEquals("mistake edit password",e.getPassword(),o.getPassword());
               assertEquals("mistake edit taught",e.getTaught(),o.getTaught());
               
            } else {
                assertEquals(true, false);
            }
    }
    
}
