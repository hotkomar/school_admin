/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.test;

import cz.cvut.hotkomar.model.entity.Subject;
import cz.cvut.hotkomar.service.manager.SubjectMan;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author maru
 */

@Transactional
public class TestSubject extends GenericTest<Subject> {
    @Autowired
    private SubjectMan subjectMan;
    
    @PostConstruct
    public void init(){
        super.setManager(subjectMan);
        super.setClazz(Subject.class);
    }

    @Override
    public void addEntity() {
           List<Subject> findAll1 = manager.findAll();
            Subject o = new Subject();
            o.setVisible(Boolean.TRUE);
            manager.add(o, false);
            List<Subject> findAll2 = manager.findAll();
             assertEquals("mistake addEntity TestSubject",findAll1.size()+1, findAll2.size());
    }
    
    
    
    public void editEntity() {
        List<Subject> findAll1 = manager.findAll();
            if(!findAll1.isEmpty()){
                Subject o = findAll1.get(0);
                o.setName("matematika");
                
                
                manager.edit(o, false);
                Subject e = manager.findById(o.getId());
               assertEquals("mistake edit name",e.getName(),o.getName());
               
            } else {
                assertEquals(true, false);
            }
    }
    
}
