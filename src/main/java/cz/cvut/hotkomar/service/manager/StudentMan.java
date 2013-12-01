/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.manager;

import cz.cvut.hotkomar.model.entity.FileEntity;
import cz.cvut.hotkomar.model.entity.Student;
import cz.cvut.hotkomar.model.entity.StudentClass;
import cz.cvut.hotkomar.model.entity.UserType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marie Hotkova
 */
@Service
public class StudentMan extends GeneralManager<Student> {
    
    @PostConstruct
    public void init() throws InterruptedException{
        Session session = sessionFactory.openSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        fullTextSession.createIndexer(Student.class).startAndWait();
        fullTextSession.close();
        super.setEntityClass(Student.class);
    }
    public List<Student> findFullText( String keywords)
    {
        Session s = sessionFactory.getCurrentSession();
        Transaction tx =null;
        try{
            FullTextSession fullTextSession =Search.getFullTextSession(s);
            tx = fullTextSession.beginTransaction();
            QueryBuilder builder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Student.class).get();
            org.apache.lucene.search.Query query =builder.keyword().onFields("login","name","surname").matching(keywords).createQuery();
            Query q = fullTextSession.createFullTextQuery(query, Student.class);         
            List<Student> list = q.list();
            List<Student> list1 = new ArrayList<Student>();
            for (Student student:list) {
                
                if(student.getVisible())
                {
                    list1.add(student);
                }
            }
            tx.commit();
            return  list1;
            
            
        }
        catch(Exception e)
        {
            if(tx !=null)
            {
                tx.rollback();
            }
        }
        return null;
    }
    
    /**
     * find List<Student> by chosen class(StudentClass)
     * @param clazz
     * @return
     */
    public List<Student> findByClass_id (StudentClass clazz)
  {
      System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
      List<Student> list = new ArrayList<Student>();
      try {
//          tx=session.beginTransaction();
          Query query =session.createQuery("SELECT u FROM Student AS u WHERE u.id_class = :id_class AND u.visible = true ");
          //query.setParameter("visible",true);
          query.setParameter("id_class",clazz);
           list = query.list();
           System.out.println("vracím : "+list.size());
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
      }
      return list;
  }
   
    public List<Student> findByLogin (String login)
  {
      System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
     List<Student> list =null;
      try {
//          tx=session.beginTransaction();
          Query query =session.createQuery("SELECT u FROM Student AS u WHERE u.login = :login and u.visible=true");
          //query.setParameter("visible",true);
          query.setParameter("login",login);
          list =query.list();
           
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
          return new ArrayList<Student>();
          
      }
      return list;
  }
}
