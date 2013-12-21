/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.test;

import cz.cvut.hotkomar.model.entity.StudentParent;
import cz.cvut.hotkomar.service.manager.StudentParentMan;
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
public class TestStudentParent extends GenericTest<StudentParent> {
    @Autowired
    private StudentParentMan studentParentMan;
    
    @PostConstruct
    public void init(){
        super.setManager(studentParentMan);
        super.setClazz(StudentParent.class);
    }

    @Override
    public void addEntity() {
           List<StudentParent> findAll1 = manager.findAll();
            StudentParent o = new StudentParent();
            o.setVisible(Boolean.TRUE);
            manager.add(o, false);
            List<StudentParent> findAll2 = manager.findAll();
             assertEquals("mistake addEntity TestQuestion",findAll1.size()+1, findAll2.size());
    }
    
    
    
    public void editEntity() {
        List<StudentParent> findAll1 = manager.findAll();
            if(!findAll1.isEmpty()){
                StudentParent o = findAll1.get(0);
                o.setLogin("rodic");
                o.setPassword("heslo");
                
                manager.edit(o, false);
                StudentParent e = manager.findById(o.getId());
               assertEquals("mistake edit login",e.getLogin(),o.getLogin());
               assertEquals("mistake edit password",e.getPassword(),o.getPassword());
               
               
            } else {
                assertEquals(true, false);
            }
    }
    
}
