/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.test;

import cz.cvut.hotkomar.model.entity.StudentClass;
import cz.cvut.hotkomar.service.manager.StudentClassMan;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Marie Hotkova
 */
@Transactional
public class TestStudentClass extends GenericTest<StudentClass> {
    
       @Autowired
    private StudentClassMan studentClassMan;
    
    @PostConstruct
    public void init(){
        super.setManager(studentClassMan);
        super.setClazz(StudentClass.class);
    }

    @Override
    public void addEntity() {
           List<StudentClass> findAll1 = manager.findAll();
            StudentClass o = new StudentClass();
            o.setVisible(Boolean.TRUE);
            manager.add(o, false);
            List<StudentClass> findAll2 = manager.findAll();
             assertEquals("mistake addEntity TestStudent",findAll1.size()+1, findAll2.size());
    }
    
    
    
    public void editEntity() {
        List<StudentClass> findAll1 = manager.findAll();
            if(!findAll1.isEmpty()){
                StudentClass o = findAll1.get(0);
                o.setName("A");
                o.setNameNumber((byte)5);
                o.setNumberOfYears((byte)8);
                
                manager.edit(o, false);
                StudentClass e = manager.findById(o.getId());
                
                assertEquals("mistake edit Name",e.getName(),o.getName());
                assertEquals("mistake edit ",e.getNameNumber(),o.getNameNumber());
                assertEquals("mistake edit numberOfYear",e.getNumberOfYears(),o.getNumberOfYears());
            } else {
                assertEquals(true, false);
                
            }
    } 
}
