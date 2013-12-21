/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.test;

import cz.cvut.hotkomar.model.entity.Student;
import cz.cvut.hotkomar.service.manager.StudentMan;
import java.util.List;
import javax.annotation.PostConstruct;
import static org.junit.Assert.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.OSEnvironment;

/**
 *
 * @author Marie Hotkova
 */
@Transactional
public class TestStudent extends GenericTest<Student> {
    
    @Autowired
    private StudentMan studentMan;
    
    @PostConstruct
    public void init(){
        super.setManager(studentMan);
        super.setClazz(Student.class);
    }

    @Override
    public void addEntity() {
           List<Student> findAll1 = manager.findAll();
            Student o = new Student();
            o.setVisible(Boolean.TRUE);
            manager.add(o, false);
            List<Student> findAll2 = manager.findAll();
            assertEquals("mistake addEntity TestStudent",findAll1.size()+1, findAll2.size());
    }
    
    
    
    public void editEntity() {
        List<Student> findAll1 = manager.findAll();
            if(!findAll1.isEmpty()){
                Student o = findAll1.get(0);
                o.setLogin("hotkomar");
                o.setPassword("heslo");
                o.setFax("111 111 111");
                o.setIdentificationNumber("905105/1111");
                o.setMail("hotkomar@fel.cvut.cz");
                o.setMobilePhone("111 111 111");
                o.setName("Marie");
                o.setSurname("Hotkova");
                o.setPlaceOfBorn("Karlovy Vary");
                o.setPhoneNumber("111 111 111");
                o.setVisible(Boolean.TRUE);
                o.setFatherName("Jan");
                o.setFatherSurname("Hotek");
                o.setFatherPhone("111 111 111");
                o.setMotherName("Marie");
                o.setMotherSurname("Hotkova");
                o.setMotherPhone("111 111 111");
                manager.edit(o, false);
                Student e = manager.findById(o.getId());
                assertEquals("mistake edit login",e.getLogin(), o.getLogin());
                assertEquals("mistake edit password",e.getPassword(), o.getPassword());
                assertEquals("mistake edit fax",e.getFax(),o.getFax());
                assertEquals("mistake edit IdentificationNumber",e.getIdentificationNumber(), o.getIdentificationNumber());
                assertEquals("mistake edit mail",e.getMail(),o.getMail());
                assertEquals("mistake edit mobilPhone",e.getMobilePhone(),o.getMobilePhone());
                assertEquals("mistake edit name",e.getName(), o.getName());
                assertEquals("mistake edit surname",e.getSurname(),o.getSurname());
                assertEquals("mistake edit placeOfBorn",e.getPlaceOfBorn(),o.getPlaceOfBorn());
                assertEquals("mistake edit phoneNumber",e.getPhoneNumber(),o.getPhoneNumber());
                assertEquals("mistake edit visible",e.getVisible(),o.getVisible());
                assertEquals("mistake edit fatherName",e.getFatherName(), o.getFatherName()); 
                assertEquals("mistake edit FatherSurname",e.getFatherSurname(), o.getFatherSurname());
                assertEquals("mistake edit FatherPhone",e.getFatherPhone(),o.getFatherPhone());
                assertEquals("mistake edit MotherName",e.getMotherName(),o.getMotherName());
                assertEquals("mistake edit MotherSurname",e.getMotherSurname(),o.getMotherSurname());
                assertEquals("mistake edit MotherPhone",e.getMotherPhone(),o.getMotherPhone());
            } else {
                assertEquals(true, false);
            }
    }

    
    
    
}
