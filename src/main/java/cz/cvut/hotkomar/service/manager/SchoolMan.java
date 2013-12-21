/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.manager;

import cz.cvut.hotkomar.model.entity.School;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maru
 */
@Service
public class SchoolMan extends GeneralManager<School>{
   @PostConstruct
    public void init(){
        super.setEntityClass(School.class);
    }
}
