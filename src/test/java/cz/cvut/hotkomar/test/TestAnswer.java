/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.test;


import cz.cvut.hotkomar.model.entity.Answer;
import cz.cvut.hotkomar.service.manager.AnswerMan;
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
public class TestAnswer extends GenericTest<Answer> {
     @Autowired
    private AnswerMan answerMan;
    
    @PostConstruct
    public void init(){
        super.setManager(answerMan);
        super.setClazz(Answer.class);
    }

    @Override
    public void addEntity() {
           List<Answer> findAll1 = manager.findAll();
            Answer o = new Answer();
            o.setVisible(Boolean.TRUE);
            o.setAnswer("Answer");
            manager.add(o, false);
            List<Answer> findAll2 = manager.findAll();
            assertEquals("mistake addEntity TestStudent",findAll1.size()+1, findAll2.size());
    }
    
    
    
    public void editEntity() {
        List<Answer> findAll1 = manager.findAll();
            if(!findAll1.isEmpty()){
                Answer o = findAll1.get(0);
                o.setAnswer("1+1=");
                o.setCorrect(Boolean.TRUE);
                
                manager.edit(o, false);
                Answer e = manager.findById(o.getId());
                assertEquals("mistake edit answer",e.getAnswer() ,o.getAnswer());
                assertEquals("mistake edit correst",e.getCorrect(),o.getCorrect());
                
            } else {
                assertEquals(true, false);
            }
    }
}
