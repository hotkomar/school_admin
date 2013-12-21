/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.manager;

import cz.cvut.hotkomar.model.entity.Answer;
import cz.cvut.hotkomar.model.entity.Question;
import cz.cvut.hotkomar.model.entity.Student;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maru
 */
@Service
public class AnswerMan extends GeneralManager<Answer>{
    @PostConstruct
    public void init(){
        super.setEntityClass(Answer.class);
    }
     public List<Answer> findByQuestion (Question question)
  {
      System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
      List<Answer> list = new ArrayList<Answer>();
      try {
//          tx=session.beginTransaction();
          Query query =session.createQuery("SELECT u FROM Answer AS u WHERE u.question = :id_question AND u.visible = true ");
          //query.setParameter("visible",true);
          query.setParameter("id_question",question);
           list = query.list();
           System.out.println("vracím : "+list.size());
//          tx.commit();
      } catch (Exception e) {
          System.out.println("findByQuestion has exception");
      }
      return list;
  }
    
}
