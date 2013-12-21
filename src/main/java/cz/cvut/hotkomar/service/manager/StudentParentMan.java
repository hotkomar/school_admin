/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.manager;

import cz.cvut.hotkomar.model.entity.Student;
import cz.cvut.hotkomar.model.entity.StudentParent;
import java.util.List;
import javax.annotation.PostConstruct;
import org.hibernate.Query;
import org.hibernate.Session;
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
    
    public StudentParent findByLogin (String login)
  {
      System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
     List<StudentParent> list =null;
      try {
//          tx=session.beginTransaction();
          Query query =session.createQuery("SELECT u FROM StudentParent AS u WHERE u.login = :login and u.visible=true");
          //query.setParameter("visible",true);
          query.setParameter("login",login);
          list =query.list();
           if(!list.isEmpty())
           {
               return list.get(0);
           }
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
          //return new ArrayList<Student>();
          
      }
      return null;
  }
    
    public StudentParent findByStudent (Student student)
  {
      System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
     List<StudentParent> list =null;
      try {
//          tx=session.beginTransaction();
          Query query =session.createQuery("SELECT u FROM StudentParent AS u WHERE u.student = :student and u.visible=true");
          //query.setParameter("visible",true);
          query.setParameter("student",student);
          list =query.list();
           if(!list.isEmpty())
           {
               return list.get(0);
           }
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
          //return new ArrayList<Student>();
          
      }
      return null;
  }
}
