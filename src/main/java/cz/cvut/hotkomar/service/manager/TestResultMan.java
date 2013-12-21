/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.manager;

import cz.cvut.hotkomar.model.entity.Answer;
import cz.cvut.hotkomar.model.entity.Student;
import cz.cvut.hotkomar.model.entity.StudentClass;
import cz.cvut.hotkomar.model.entity.Subject;
import cz.cvut.hotkomar.model.entity.Teacher;
import cz.cvut.hotkomar.model.entity.Test;
import cz.cvut.hotkomar.model.entity.TestResult;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maru
 */
@Service
public class TestResultMan extends GeneralManager<TestResult>{
   @PostConstruct
    public void init(){
        super.setEntityClass(TestResult.class);
    } 
   
    public TestResult findByStudentTestActualMark( Student student, Test test) {
        
        System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
     
      try {
        Query query = session.createQuery("SELECT u FROM TestResult AS u WHERE u.student= :id_student AND u.test= :id_test AND u.visible= true AND u.actualMark= true");
            
            query.setParameter("id_student", student);
            query.setParameter("id_test", test);
            List list = query.list();
            if(!list.isEmpty())
            {
                return (TestResult)list.get(0);
            }
           
         //  System.out.println("vracím : "+list.size());
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
      }
      return null;
        
        
       

    }    
    public List<TestResult> findByTest(  Test test) {
        
        System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
     List<TestResult> list = new ArrayList<TestResult>();
      try {
        Query query = session.createQuery("SELECT u FROM TestResult AS u WHERE   u.test= :id_test AND u.visible = true");
            
           
            query.setParameter("id_test", test);
             list = query.list();
            
           
         //  System.out.println("vracím : "+list.size());
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
      }
      return list;
        
        
       

    }    
    public List<TestResult> findByClass(  StudentClass clazz) {
        
        System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
     List<TestResult> list = new ArrayList<TestResult>();
      try {
        Query query = session.createQuery("SELECT u FROM TestResult AS u WHERE   u.student.id_class = :id_clazz AND u.visible = true");
            
           
            query.setParameter("id_clazz", clazz);
             list = query.list();
            
           
         //  System.out.println("vracím : "+list.size());
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
      }
      return list;
        
        
       

    }  
    
     public List<TestResult> findByStudent(  Student student) {
        
        System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
     List<TestResult> list = new ArrayList<TestResult>();
      try {
        Query query = session.createQuery("SELECT u FROM TestResult AS u WHERE   u.student = :id_student AND u.visible = true");
            
           
            query.setParameter("id_student", student);
             list = query.list();
            
           
         //  System.out.println("vracím : "+list.size());
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
      }
      return list;
        
        
       

    }  
     public List<TestResult> findByStudentTest(  Student student,Test test) {
        
        System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
     List<TestResult> list = new ArrayList<TestResult>();
      try {
        Query query = session.createQuery("SELECT u FROM TestResult AS u WHERE   u.student = :id_student AND u.test= :id_test AND u.visible = true");
            
           
            query.setParameter("id_student", student);
            query.setParameter("id_test",test);
             list = query.list();
            
           
         //  System.out.println("vracím : "+list.size());
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
      }
      return list;
        
        
       

    }  
   public TestResult findByStudentActualMark(  Student student) {
        
        System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
     
      try {
        Query query = session.createQuery("SELECT u FROM TestResult AS u WHERE   u.student = :id_student AND u.visible = true AND u.actualMark = true");
            
           
            query.setParameter("id_student", student);
             List list = query.list();
            if(!list.isEmpty())
            {
                return (TestResult)list.get(0);
            }
            
           
         //  System.out.println("vracím : "+list.size());
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
      }
      return null;
        
        
       

    }  
   public Calendar findByStudentMaxDate(  Student student, Subject subject) {
        
        System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
     
      try {
        Query query = session.createQuery("SELECT MAX(u.testDate) FROM TestResult AS u WHERE u.test.id_subject = :id_subject AND  u.student = :id_student AND u.visible = true AND u.actualMark = true");
            
           
            query.setParameter("id_student", student);
            query.setParameter("id_subject", subject);
             List list = query.list();
           // TestResult t= ((TestResult)list.get(0));
             System.out.println("DÉLKA LISTKU "+list.size());
             System.out.println("list "+list.get(0).toString());
             System.out.println("is empty "+list.isEmpty());
            if(!list.isEmpty())
            {
               
                return (Calendar)list.get(0);
            }
            
           
         //  System.out.println("vracím : "+list.size());
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
      }
      return null;      
           }  
   public Calendar findByStudentMINDate(  Student student, Subject subject) {
        
        System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
     
      try {
        Query query = session.createQuery("SELECT Min(u.testDate) FROM TestResult AS u WHERE u.test.id_subject = :id_subject AND  u.student = :id_student AND u.visible = true AND u.actualMark = true AND u.mark IS NOT null");
            
           
            query.setParameter("id_student", student);
            query.setParameter("id_subject", subject);
             List list = query.list();
           // TestResult t= ((TestResult)list.get(0));
             System.out.println("DÉLKA LISTKU "+list.size());
             System.out.println("list "+list.get(0).toString());
             System.out.println("is empty "+list.isEmpty());
            if(!list.isEmpty())
            {
               
                return (Calendar)list.get(0);
            }
            
           
         //  System.out.println("vracím : "+list.size());
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
      }
      return null;
        
        
       

    }  
   public List<TestResult> findByStudentBetween(  Student student, Subject subject, Calendar first, Calendar last) {
        
        System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
      List<TestResult> list = new ArrayList<TestResult>();
//      Transaction tx=null;
     
      try {
        Query query = session.createQuery("SELECT u FROM TestResult AS u WHERE (u.testDate BETWEEN :first AND :last) AND u.test.id_subject = :id_subject AND  u.student = :id_student AND u.visible = true AND u.actualMark = true AND u.mark IS NOT null");
            
            /*u.test.id_subject = :id_subject AND  u.student = :id_student AND u.visible = true AND u.actualMark = true AND u.testDate*/
            query.setParameter("id_student", student);
            query.setParameter("id_subject", subject);
            query.setParameter("first", first);
            query.setParameter("last",last);
              list = query.list();
           // TestResult t= ((TestResult)list.get(0));
             System.out.println("DÉLKA LISTKU "+list.size());
            
             System.out.println("is empty "+list.isEmpty());
            
            
           
         //  System.out.println("vracím : "+list.size());
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
          e.printStackTrace();
          System.out.println("bléééééééééééééééééééééééééééééééééééé");
      }
      return list;
        
        
       

    }  
   
   public List<TestResult> findByStudentBetween(  Student student, Subject subject, Date first, Date last) {
        
        System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
      List<TestResult> list = new ArrayList<TestResult>();
//      Transaction tx=null;
     
      try {
        Query query = session.createQuery("SELECT u FROM TestResult AS u WHERE (u.testDate BETWEEN :first AND :last) AND u.test.id_subject = :id_subject AND  u.student = :id_student AND u.visible = true AND u.actualMark = true");
            
            /*u.test.id_subject = :id_subject AND  u.student = :id_student AND u.visible = true AND u.actualMark = true AND u.testDate*/
            query.setParameter("id_student", student);
            query.setParameter("id_subject", subject);
            query.setParameter("first", first);
            query.setParameter("last",last);
              list = query.list();
           // TestResult t= ((TestResult)list.get(0));
             System.out.println("DÉLKA LISTKU "+list.size());
            
             System.out.println("is empty "+list.isEmpty());
            
            
           
         //  System.out.println("vracím : "+list.size());
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
          e.printStackTrace();
          System.out.println("bléééééééééééééééééééééééééééééééééééé");
      }
      return list;
        
        
       

    }  
   
   public List<TestResult> findByStudentBetweenStringDate(  Student student, Subject subject, String first, String last) {
        
        System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
      List<TestResult> list = new ArrayList<TestResult>();
//      Transaction tx=null;
     
      try {
        Query query = session.createQuery("SELECT u FROM TestResult AS u WHERE u.testDate= 2013-01-01 AND u.test.id_subject = :id_subject AND  u.student = :id_student AND u.visible = true AND u.actualMark = true");
            
            /*u.test.id_subject = :id_subject AND  u.student = :id_student AND u.visible = true AND u.actualMark = true AND u.testDate*/
            query.setParameter("id_student", student);
            query.setParameter("id_subject", subject);
           // query.setParameter("first", first);
          //  query.setParameter("last",last);
              list = query.list();
           // TestResult t= ((TestResult)list.get(0));
             System.out.println("DÉLKA LISTKU "+list.size());
            
             System.out.println("is empty "+list.isEmpty());
            
            
           
         //  System.out.println("vracím : "+list.size());
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
          e.printStackTrace();
          System.out.println("bléééééééééééééééééééééééééééééééééééé");
      }
      return list;
        
        
       

    }  
   public Integer findByAvarageActualMark(Test test, Short mark) {
        
        System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
     
      try {
        Query query = session.createQuery("SELECT COUNT(u.id) FROM TestResult AS u WHERE   u.test = :id_test AND u.mark= :id_mark  AND u.visible = true AND u.actualMark = true");
            
           query.setParameter("id_mark", mark);
            query.setParameter("id_test", test);
             List list = query.list();
            if(!list.isEmpty())
            {
                return Integer.valueOf(list.get(0).toString());
            }
            
           
         //  System.out.println("vracím : "+list.size());
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
      }
      return Integer.parseInt("0");
        
        
       

    }
   public Double findByAvarageActualMarkClass(Test test, StudentClass clazz) {
        
        System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
     
      try {
        Query query = session.createQuery("SELECT AVG(u.mark) FROM TestResult AS u WHERE u.test = :id_test AND u.student.id_class = :id_clazz  AND u.visible = true AND u.actualMark = true");
            
           query.setParameter("id_clazz", clazz);
            query.setParameter("id_test", test);
             List list = query.list();
            if(!list.isEmpty())
            {
                return Double.valueOf(list.get(0).toString());
            }
            
           
         //  System.out.println("vracím : "+list.size());
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
      }
      return Double.valueOf("0.0");
        
        
       

    }
     public Map<StudentClass, Double> findByAvarageActualMarkClass(Test test) {
        
        System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
     
      try {
        Query query = session.createQuery("SELECT sc, AVG(u.mark) FROM TestResult AS u, StudentClass AS sc WHERE u.student.id_class = sc AND u.test = :id_test  AND u.visible = true AND u.actualMark = true GROUP BY sc");
            
            query.setParameter("id_test", test);
            List<Object[]> result = query.list();
            System.out.println("SIZE="+result.size());
            Map <StudentClass, Double> m = new LinkedHashMap<StudentClass, Double>(); 
            for(Object[] d: result){
//                System.out.print(((StudentClass)d[0]).getName());
//                System.out.print(" ");
//                System.out.println((Double) d[1]);
                if(d[1]==null)
                {
                    d[1]=0.0;
                }
                else {
                d[1]= Double.valueOf(String.format("%.2f", d[1]).replace(',','.'));
                }
                m.put((StudentClass)d[0],(Double) d[1]);
                
            }
            return m;
//            for(List<Object> o: result){
//                System.out.println("SC="+((StudentClass)o.get(0)).getName()+" AVG="+(Double)o.get(1));
//            }
           
         //  System.out.println("vracím : "+list.size());
//          tx.commit();
      } catch (Exception e) {
          e.printStackTrace();
//          if(tx != null){ tx.rollback();}
      }
      return new HashMap<StudentClass, Double>();
        
        
       

    }
   public Integer findByAvarageActualMark(Test test) {
        
        System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
     
      try {
        Query query = session.createQuery("SELECT COUNT(u.id) FROM TestResult AS u WHERE   u.test = :id_test   AND u.visible = true AND u.actualMark = true AND u.mark IS NOT null");
            
          
            query.setParameter("id_test", test);
             List list = query.list();
            if(!list.isEmpty())
            {
                return Integer.valueOf(list.get(0).toString());
            }
            
           
         //  System.out.println("vracím : "+list.size());
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
      }
      return Integer.parseInt("0");
        
        
       

    }  
   
     
   
//     public List<TestResult> join(Test test) {
//        
//        System.out.println("jsem ve třídě findByClass_id");
//      Session session= getSession();
////      Transaction tx=null;
//     List<TestResult> list = new ArrayList<TestResult>();
//      try {
//        Query query = session.createQuery("SELECT u FROM TestResult AS u JOIN SubjectOfClass AS z  WHERE u.student.id_class = z.id_class AND u.test= :idtest");
//            
//           
//            query.setParameter("id_test", test);
//             list = query.list();
//            
//           
//         //  System.out.println("vracím : "+list.size());
////          tx.commit();
//      } catch (Exception e) {
////          if(tx != null){ tx.rollback();}
//      }
//      return list;
//        
//        
//       
//
//    }    
}
