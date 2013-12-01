/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.manager;

import cz.cvut.hotkomar.model.entity.Student;
import cz.cvut.hotkomar.model.entity.SubjectOfClass;
import cz.cvut.hotkomar.model.entity.Teacher;
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
 * @author Marie Hoťková
 */
@Service
public class TeacherMan extends GeneralManager<Teacher>{
    @PostConstruct
    public void init() throws InterruptedException{
       Session session = sessionFactory.openSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        fullTextSession.createIndexer(Teacher.class).startAndWait();
        fullTextSession.close();
        super.setEntityClass(Teacher.class);
    }
    
    public List<Teacher> findFullText( String keywords)
    {
        
        Session s = sessionFactory.getCurrentSession();
        Transaction tx =null;
        try{
            FullTextSession fullTextSession =Search.getFullTextSession(s);
            tx = fullTextSession.beginTransaction();
            QueryBuilder builder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Teacher.class).get();
            org.apache.lucene.search.Query query =builder.keyword().onFields("login","name","surname").matching(keywords).createQuery();
            Query q = fullTextSession.createFullTextQuery(query, Teacher.class);         
            List<Teacher> list = q.list();
            List<Teacher> list1 = new ArrayList<Teacher>();
            for (Teacher teacher:list) {
                
                if(teacher.getVisible())
                {
                    list1.add(teacher);
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
    
    public List<Teacher> teacherWithoutClass() {
        Session session = getSession();
        List <Teacher> list = null;

        try {

            Query query = session.createQuery("SELECT e FROM Teacher as e WHERE e.visible=TRUE AND e.id not in (SELECT c.id_teacher.id FROM StudentClass AS c WHERE c.visible=true)");
            
            

           list = query.list();



            if (list.isEmpty()) {
                return null;
            } else {
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
//            if (tx != null) {
//                tx.rollback();
//            }
            return null;
        }

    }
    
    public List<Teacher> findByLogin (String login)
  {
      System.out.println("jsem ve třídě findByClass_id");
      Session session= getSession();
//      Transaction tx=null;
     List<Teacher> list =null;
      try {
//          tx=session.beginTransaction();
          Query query =session.createQuery("SELECT u FROM Teacher AS u WHERE u.login = :login and u.visible=true");
          //query.setParameter("visible",true);
          query.setParameter("login",login);
          list =query.list();
           
//          tx.commit();
      } catch (Exception e) {
//          if(tx != null){ tx.rollback();}
          return new ArrayList<Teacher>();
      }
      return list;
  }
}
