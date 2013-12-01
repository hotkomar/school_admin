/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.manager;

import cz.cvut.hotkomar.model.entity.Subject;
import cz.cvut.hotkomar.model.entity.SubjectOfClass;
import cz.cvut.hotkomar.model.entity.Teacher;
import cz.cvut.hotkomar.model.entity.Test;
import java.util.ArrayList;
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
 * @author Maru
 */
@Service
public class TestMan extends GeneralManager<Test>{
    @PostConstruct
    public void init() throws InterruptedException{
        Session session = sessionFactory.openSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        fullTextSession.createIndexer(Test.class).startAndWait();
        fullTextSession.close();
        super.setEntityClass(Test.class);
    }
     public List<Test> findFullText( String keywords)
    {
        
        Session s = sessionFactory.getCurrentSession();
        Transaction tx =null;
        try{
            FullTextSession fullTextSession =Search.getFullTextSession(s);
            tx = fullTextSession.beginTransaction();
            QueryBuilder builder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Test.class).get();
            org.apache.lucene.search.Query query =builder.keyword().onFields("name").matching(keywords).createQuery();
            Query q = fullTextSession.createFullTextQuery(query, Test.class);         
            List<Test> list = q.list();
            List<Test> list1 = new ArrayList<Test>();
            for (Test test:list) {
                
                if(test.getVisible())
                {
                    list1.add(test);
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
    
    
    
    
    
    public List<Test> findByTeacher( Teacher t) {
        
        System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
      List<Test> list = new ArrayList<Test>();
      try {
        Query query = session.createQuery("SELECT u FROM Test AS u WHERE  u.id_teacher= :id_teacher AND u.visible = true");
            
            query.setParameter("id_teacher", t);
           list = query.list();
         //  System.out.println("vracím : "+list.size());
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
      }
      return list;
        
        
       

    }    
    
    public Test findByIDTeacher( Long id,Teacher t) {
        
        System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
      
      try {
        Query query = session.createQuery("SELECT u FROM Test AS u WHERE  u.id_teacher= :id_teacher AND u.id= :id AND u.visible = true");
            
            query.setParameter("id_teacher", t);
            query.setParameter("id",id);
            List list = query.list();
            
            //  System.out.println("vracím : "+list.size());
            //          tx.commit();
            if(!list.isEmpty())
            {
                return (Test)list.get(0);
            }
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
      }
      return null;
        
        
       

    }    
    
    
     public Test findBypass( String pass) {
        
        System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
      
      try {
        Query query = session.createQuery("SELECT u FROM Test AS u WHERE  u.password = :password AND u.visible = true");
            
            query.setParameter("password", pass);
           
            List list = query.list();
            
            //  System.out.println("vracím : "+list.size());
            //          tx.commit();
            if(!list.isEmpty())
            {
                return (Test)list.get(0);
            }
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
      }
      return null;
        
        
       

    }    
    
    
    
    
    
    
    
    
    
}
