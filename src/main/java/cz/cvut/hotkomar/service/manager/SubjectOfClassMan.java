/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.manager;

import cz.cvut.hotkomar.model.entity.StudentClass;
import cz.cvut.hotkomar.model.entity.Subject;
import cz.cvut.hotkomar.model.entity.SubjectOfClass;
import cz.cvut.hotkomar.model.entity.Teacher;
import java.util.ArrayList;
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
public class SubjectOfClassMan extends GeneralManager<SubjectOfClass>{
    @PostConstruct
    public void init(){
        super.setEntityClass(SubjectOfClass.class);
    }
    public List<SubjectOfClass> findByIdClass(Long id) {
        
        System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
      List<SubjectOfClass> list = new ArrayList<SubjectOfClass>();
      try {
        Query query = session.createQuery("SELECT u FROM SubjectOfClass AS u WHERE u.id_class.id = :id_class AND u.visible = true");
            query.setParameter("id_class", id);
           list = query.list();
         //  System.out.println("vracím : "+list.size());
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
      }
      return list;
        
    } 
     public List<SubjectOfClass> findByIdSubject(Long id) {
        
        System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
      List<SubjectOfClass> list = new ArrayList<SubjectOfClass>();
      try {
        Query query = session.createQuery("SELECT u FROM SubjectOfClass AS u WHERE u.id_subject.id = :id_subject AND u.visible = true");
            query.setParameter("id_subject", id);
           list = query.list();
         //  System.out.println("vracím : "+list.size());
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
      }
      return list;
        
    } 
   public List<SubjectOfClass> findByTeacher( Teacher t) {
        
        System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
      List<SubjectOfClass> list = new ArrayList<SubjectOfClass>();
      try {
        Query query = session.createQuery("SELECT u FROM SubjectOfClass AS u WHERE  u.id_teacher= :id_teacher AND u.visible = true");
            
            query.setParameter("id_teacher", t);
           list = query.list();
         //  System.out.println("vracím : "+list.size());
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
      }
      return list;
        
        
       

    }    
    public List<SubjectOfClass> findSubjectByTeacher( Teacher t) {
        
        System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
      List<SubjectOfClass> list = new ArrayList<SubjectOfClass>();
      try {
        Query query = session.createQuery("SELECT u FROM SubjectOfClass AS u WHERE  u.id_teacher= :id_teacher AND u.visible = true GROUP BY u.id_subject");
            
            query.setParameter("id_teacher", t);
           list = query.list();
         //  System.out.println("vracím : "+list.size());
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
      }
      return list;
        
        
       

    }    
public List<SubjectOfClass> findByClassSubjectTeacher(StudentClass c, Subject s, Teacher t) {
        
        System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
      List<SubjectOfClass> list = new ArrayList<SubjectOfClass>();
      try {
        Query query = session.createQuery("SELECT u FROM SubjectOfClass AS u WHERE u.id_class = :id_class AND u.id_subject= :id_subject AND u.id_teacher= :id_teacher AND u.visible = true");
            query.setParameter("id_class", c);
            query.setParameter("id_subject", s);
            query.setParameter("id_teacher", t);
           list = query.list();
         //  System.out.println("vracím : "+list.size());
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
      }
      return list;
        
        
       

    }

public List<SubjectOfClass> findByClassSubjectTeacherVisible(StudentClass c, Subject s, Teacher t) {
        
        System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
      List<SubjectOfClass> list = new ArrayList<SubjectOfClass>();
      try {
        Query query = session.createQuery("SELECT u FROM SubjectOfClass AS u WHERE u.id_class = :id_class AND u.id_subject= :id_subject AND u.id_teacher= :id_teacher AND u.visible = false");
            query.setParameter("id_class", c);
            query.setParameter("id_subject", s);
            query.setParameter("id_teacher", t);
           list = query.list();
         //  System.out.println("vracím : "+list.size());
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
      }
      return list;
        
        
       

    }
public List<SubjectOfClass> findByClassSubjectTeacherVisibleID(StudentClass c, Subject s, Teacher t) {
        
       
      Session session= getSession();
//      Transaction tx=null;
      List<SubjectOfClass> list = new ArrayList<SubjectOfClass>();
      try {
        Query query = session.createQuery("SELECT u FROM SubjectOfClass AS u WHERE u.id_class = :id_class AND u.id_subject= :id_subject AND u.id_teacher= :id_teacher AND u.visible = false");
            query.setParameter("id_class", c);
            query.setParameter("id_subject", s);
            query.setParameter("id_teacher", t);
           list = query.list();
         //  System.out.println("vracím : "+list.size());
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
      }
      return list;
        
        
       

    }
public SubjectOfClass findByClassSubjectTeacherVisibleID(Long studentClass,Long subject, Long teacher) {
        
       
      Session session= getSession();
//      Transaction tx=null;
      List<SubjectOfClass> list = new ArrayList<SubjectOfClass>();
      try {
        Query query = session.createQuery("SELECT u FROM SubjectOfClass AS u WHERE u.id_class.id = :id_class AND u.id_subject.id = :id_subject AND u.id_teacher.id = :id_teacher AND u.visible = true");
            query.setParameter("id_class", studentClass);
            query.setParameter("id_subject", subject);
            query.setParameter("id_teacher", teacher);
           list = query.list();
         //  System.out.println("vracím : "+list.size());
//          tx.commit();
           if(!list.isEmpty())
           {
              return (SubjectOfClass) list.get(0);
           }
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
      }
      return null;
        
        
       

    }
public List<SubjectOfClass> findBySubjectTeacher( Subject s, Teacher t) {
        
        System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
      List<SubjectOfClass> list = new ArrayList<SubjectOfClass>();
      try {
        Query query = session.createQuery("SELECT u FROM SubjectOfClass AS u WHERE  u.id_subject= :id_subject AND u.id_teacher= :id_teacher AND u.visible = true");
            
            query.setParameter("id_subject", s);
            query.setParameter("id_teacher", t);
           list = query.list();
         //  System.out.println("vracím : "+list.size());
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
      }
      return list;
        
        
       

    }
public List<SubjectOfClass> findBySubjectTeacherID( Long s, Teacher t) {
        
        System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
      List<SubjectOfClass> list = new ArrayList<SubjectOfClass>();
      try {
        Query query = session.createQuery("SELECT u FROM SubjectOfClass AS u WHERE  u.id_subject.id= :id_subject AND u.id_teacher= :id_teacher AND u.visible = true");
            
            query.setParameter("id_subject", s);
            query.setParameter("id_teacher", t);
           list = query.list();
         //  System.out.println("vracím : "+list.size());
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
      }
      return list;
        
        
       

    }
   public List<SubjectOfClass> findBySubjectTeacherVisible( Subject s, Teacher t) {
        
        System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
      List<SubjectOfClass> list = new ArrayList<SubjectOfClass>();
      try {
        Query query = session.createQuery("SELECT u FROM SubjectOfClass AS u WHERE  u.id_subject= :id_subject AND u.id_teacher= :id_teacher u.visible= false");
            
            query.setParameter("id_subject", s);
            query.setParameter("id_teacher", t);
           list = query.list();
         //  System.out.println("vracím : "+list.size());
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
      }
      return list;
        
        
       

    } 
      
}
