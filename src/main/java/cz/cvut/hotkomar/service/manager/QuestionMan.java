/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.manager;

import cz.cvut.hotkomar.model.entity.Question;
import cz.cvut.hotkomar.model.entity.SubjectOfClass;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maru
 */
@Service
public class QuestionMan extends GeneralManager<Question>{
    
    @PostConstruct
    public void init(){
        super.setEntityClass(Question.class);
    }
    
}
