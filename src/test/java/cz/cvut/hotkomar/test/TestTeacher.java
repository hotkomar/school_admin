/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.test;

import cz.cvut.hotkomar.model.entity.Teacher;
import cz.cvut.hotkomar.service.manager.TeacherMan;
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
public class TestTeacher extends GenericTest<Teacher> {

      @Autowired
    private TeacherMan teacherMan;
    
    @PostConstruct
    public void init(){
        super.setManager(teacherMan);
        super.setClazz(Teacher.class);
    }

    @Override
    public void addEntity() {
           List<Teacher> findAll1 = manager.findAll();
            Teacher o = new Teacher();
            o.setVisible(Boolean.TRUE);
            manager.add(o, false);
            List<Teacher> findAll2 = manager.findAll();
            assertEquals("mistake addEntity TestTeacher",findAll1.size()+1, findAll2.size());
    }
    public void editEntity() {
    List<Teacher> findAll1 = manager.findAll();
            if(!findAll1.isEmpty()){
                Teacher o = findAll1.get(0);
                o.setLogin("hotkomarT");
                o.setPassword("hesloT");
                o.setFax("222 222 222");
                o.setIdentificationNumber("905105/2222");
                o.setMail("hotkomarT@fel.cvut.cz");
                o.setMobilePhone("222 222 222");
                o.setName("MarieT");
                o.setSurname("HotkovaT");
                o.setPlaceOfBorn("Praha");
                o.setPhoneNumber("222 222 222");
                o.setVisible(Boolean.TRUE);
                o.setDegree("Mgr.");
                o.setEducation("UK");
                o.setEducation_consultant(Boolean.TRUE);
                o.setWagePerHour((short)10);
                
                manager.edit(o, false);
                Teacher e = manager.findById(o.getId());
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
                assertEquals("mistake edit degree",e.getDegree(),o.getDegree());
                assertEquals("mistake edit education",e.getEducation(),o.getEducation());
                assertEquals("mistake edit education_consultant",e.getEducation_consultant(), o.getEducation_consultant());
                assertEquals("mistake edit wagePerHour",e.getWagePerHour(),o.getWagePerHour());
                
                
            } else {
                assertEquals(true, false);
            }

    }
}
