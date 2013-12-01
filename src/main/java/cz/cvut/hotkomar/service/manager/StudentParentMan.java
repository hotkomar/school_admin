/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.manager;

import cz.cvut.hotkomar.model.entity.StudentParent;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marie Hoťková
 */
@Service
public class StudentParentMan extends GeneralManager<StudentParent>{
    @PostConstruct
    public void init(){
        super.setEntityClass(StudentParent.class);
    }
}
