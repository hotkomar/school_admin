/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.manager;

import cz.cvut.hotkomar.model.entity.Student;
import cz.cvut.hotkomar.model.entity.StudentClass;
import cz.cvut.hotkomar.service.message.FormMessage;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marie Hotkova
 */
@Service
public class StudentClassMan extends GeneralManager<StudentClass>{
    
   
   @PostConstruct
    public void init(){
        super.setEntityClass(StudentClass.class);
    }
   public void upYear ()
  {
      System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
      
      try {
//          tx=session.beginTransaction();
          Query query =session.createQuery("UPDATE StudentClass u SET u.nameNumber=u.nameNumber+1 WHERE u.nameNumber < u.numberOfYears AND u.visible=TRUE ");
          query.executeUpdate();
          //query.setParameter("visible",true);
          message.setPositiveMes("Posunuto.");
          
//          tx.commit();
      } catch (Exception e) {
          message.setNegativeMes("Akce se nepovedla");
//          if(tx != null){ tx.rollback();}
      }
     
  }
   
   public List<StudentClass> getClassVisbleFalseSubjectOfClass(Long idSubject, Long idTeacher){
      
        Session session= getSession();
       try{
       Query q = session.createQuery("Select tr.student.id_class From SubjectOfClass as soc JOIN soc.id_class.students stud, TestResult as tr WHERE soc.visible = false AND soc.id_subject.id = :idSubject AND soc.id_teacher.id = :idTeacher AND soc.id_class.visible = true AND tr.student.id = stud.id AND tr.visible = true ");
       q.setParameter("idSubject",idSubject);
            q.setParameter("idTeacher", idTeacher);
            return (List<StudentClass>) q.list();
       }
        catch (Exception e) {
          message.setNegativeMes("Akce se nepovedla");
//          if(tx != null){ tx.rollback();}
      }
       return null;
   }
   public List<StudentClass> getClassVisbleTrueSubjectOfClass(Long idSubject, Long idTeacher){
      
        Session session= getSession();
       try{
       Query q = session.createQuery("Select tr.student.id_class From SubjectOfClass as soc JOIN soc.id_class.students stud, TestResult as tr WHERE soc.visible = true AND soc.id_subject.id = :idSubject AND soc.id_teacher.id = :idTeacher AND soc.id_class.visible = true AND tr.student.id = stud.id AND tr.visible = true ");
       q.setParameter("idSubject",idSubject);
            q.setParameter("idTeacher", idTeacher);
            return (List<StudentClass>) q.list();
       }
        catch (Exception e) {
          message.setNegativeMes("Akce se nepovedla");
//          if(tx != null){ tx.rollback();}
      }
       return null;
   }
}
