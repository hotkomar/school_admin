/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.test;

import cz.cvut.hotkomar.model.entity.School;
import cz.cvut.hotkomar.service.manager.SchoolMan;
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
public class TestSchool extends GenericTest<School> {
         @Autowired
    private SchoolMan schoolMan;
    
    @PostConstruct
    public void init(){
        super.setManager(schoolMan);
        super.setClazz(School.class);
    }

    @Override
    public void addEntity() {
           List<School> findAll1 = manager.findAll();
            School o = new School();
            o.setVisible(Boolean.TRUE);
            manager.add(o, false);
            List<School> findAll2 = manager.findAll();
             assertEquals("mistake addEntity TestQuestion",findAll1.size()+1, findAll2.size());
    }
    
    
    
    public void editEntity() {
        List<School> findAll1 = manager.findAll();
            if(!findAll1.isEmpty()){
                School o = findAll1.get(0);
                o.setCity("Karlovy Vary");
                
                o.setMobilePhone("111 111 111");
               
                o.setPhoneNumber("111 111 111");
                o.setPsc("36005");
                o.setSchoolName("ZS Konecna");
                o.setStreetName("Konecna 25");
                
                
                manager.edit(o, false);
                School e = manager.findById(o.getId());
               assertEquals("mistake edit city",e.getCity(),o.getCity());
               
               assertEquals("mistake edit mobilePhone",e.getMobilePhone(),o.getMobilePhone());
              
               assertEquals("mistake adit phoneNumber",e.getPhoneNumber(),e.getPhoneNumber());
               assertEquals("mistake edit PSC",e.getPsc(),o.getPsc());
               assertEquals("mistake edit schoolName",e.getSchoolName(),o.getSchoolName());
               assertEquals("mistake edit streetName",e.getStreetName(),o.getStreetName());
               
            } else {
                assertEquals(true, false);
            }
    }
    
}
