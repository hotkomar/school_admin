/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.manager;

import cz.cvut.hotkomar.model.entity.Student;
import cz.cvut.hotkomar.model.entity.StudentClass;
import cz.cvut.hotkomar.model.entity.StudentParent;
import cz.cvut.hotkomar.model.entity.Subject;
import java.util.ArrayList;
import java.util.Calendar;
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

public class SubjectMan extends GeneralManager<Subject>{
   @PostConstruct
    public void init() throws InterruptedException{
       Session session = sessionFactory.openSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        fullTextSession.createIndexer(Subject.class).startAndWait();
        fullTextSession.close();
        super.setEntityClass(Subject.class);
    }
   public List<Subject> findFullText( String keywords)
    {
        
        Session s = sessionFactory.getCurrentSession();
        Transaction tx =null;
        try{
            FullTextSession fullTextSession =Search.getFullTextSession(s);
            tx = fullTextSession.beginTransaction();
            QueryBuilder builder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Subject.class).get();
            org.apache.lucene.search.Query query =builder.keyword().onFields("name").matching(keywords).createQuery();
            Query q = fullTextSession.createFullTextQuery(query, Subject.class);         
            List<Subject> list = q.list();
            List<Subject> list1 = new ArrayList<Subject>();
            for (Subject subject:list) {
                
                if(subject.getVisible())
                {
                    list1.add(subject);
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
   
   public List<Subject> findByExpiration ()
  {
      System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
     List<Subject> list =null;
      Calendar calendar = Calendar.getInstance();
      try {
//          tx=session.beginTransaction();
          Query query =session.createQuery("SELECT u FROM Subject AS u WHERE u.expiration > :actualDate OR u.expiration IS NULL AND u.visible=true");
          query.setParameter("actualDate",calendar);
          list =query.list();
           
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
          //return new ArrayList<Student>();
          
      }
      return list;
  }
   
   
}
