/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.test;

import cz.cvut.hotkomar.model.entity.Question;
import cz.cvut.hotkomar.service.manager.QuestionMan;
import java.util.List;
import javax.annotation.PostConstruct;
import static org.junit.Assert.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Marie
 */
@Transactional
public class TestQuestion extends GenericTest<Question> {
    
     @Autowired
    private QuestionMan questionMan;
    
    @PostConstruct
    public void init(){
        super.setManager(questionMan);
        super.setClazz(Question.class);
    }

    @Override
    public void addEntity() {
           List<Question> findAll1 = manager.findAll();
            Question o = new Question();
            o.setVisible(Boolean.TRUE);
            manager.add(o, false);
            List<Question> findAll2 = manager.findAll();
             assertEquals("mistake addEntity TestQuestion",findAll1.size()+1, findAll2.size());
    }
    
    
    
    public void editEntity() {
        List<Question> findAll1 = manager.findAll();
            if(!findAll1.isEmpty()){
                Question o = findAll1.get(0);
                o.setPoints(10);
                o.setQuestion("1+1=?");
                
                manager.edit(o, false);
                Question e = manager.findById(o.getId());
                assertEquals("misstake edit points",e.getPoints(),o.getPoints());
                assertEquals("mistake edit question",e.getQuestion(),o.getQuestion());
                
            } else {
                assertEquals(true, false);
            }
    }
}
