/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.manager;

//import cz.cvut.hotkomar.service.message.FormMessage;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @param <E>
 * @Marie Hoťková
 */
public abstract class GeneralManager<E> implements GeneralManImp<E> {

    /**
     * SessionFactory
     */
    protected SessionFactory sessionFactory;
    /**
     * Information message about action for user
     */
   // protected FormMessage message;
    private Class<E> entityClass; //reflexe

    /**
     *
     * @param message
     */
//    @Autowired
//    public void setMessage(FormMessage message) {
//        this.message = message;
//    }

    GeneralManager() {
        //return Class name
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
    }
    //comunication with spring

    /**
     *
     * @param sessionFactory
     */
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }

    /**
     * create session
     *
     * @return
     */
    protected Session getSession() {
        Session s = sessionFactory.getCurrentSession();
        if (s == null) {
            s = sessionFactory.openSession();
        }

        return s;

    }

    /**
     * add entity to database
     *
     * @param entity
     * @param b
     */
    public void add(E entity, boolean b) {
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            if (b) {
//                message.setPositiveMes("uloženo");
                System.out.println("ulozena "+entity.getClass().getName());
            }

        } catch (HibernateException e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            if (b) {
//                message.setNegativeMes("uložení se nepovedlo :(");
            }


        }
    }

    /**
     * edit entity boolean b == true : return info message for user
     *
     * @param entity
     * @param b
     */
    public void edit(E entity, boolean b) {
        Session session = sessionFactory.openSession();//getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.update(entity);

            tx.commit();
            if (b) {
//                message.setPositiveMes("změněno");
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
//                message.setNegativeMes("editace se nepovedla :(");
            }
        } finally {
            session.close();

        }
    }

    /**
     * delete entity
     *
     * @param entity
     */
    public void delete(E entity) {
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(entity);

            tx.commit();
//            message.setPositiveMes("smazáno");

        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
//                message.setNegativeMes("nepovedlo se smazání :(");
            }
        }
    }

    /**
     * set visible to false boolean b == true : return information message for
     * user
     *
     * @param id
     * @param b
     */
    public void visible(Long id, boolean b) {
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "UPDATE " + entityClass.getName() + " set visible = :visible WHERE id = :entity_id";
            Query query = session.createQuery(hql);
            query.setParameter("visible", false);
            query.setParameter("entity_id", id);
            int result = query.executeUpdate();
//            System.out.println("Rows affected: " + result);
            tx.commit();
            if (b) {
//                message.setPositiveMes("smazáno");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
                if (!b) {
//                    message.setNegativeMes("smazání se nepovedlo :( ");
                }
            }
        }
    }

    /**
     * find entity by chosen id
     *
     * @param id
     * @return
     */
    public E findById(Long id) {
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT e FROM " + entityClass.getName() + " as e where e.id= :id AND e.visible=true");
            query.setParameter("id", id);

            List<E> list = query.list();

            tx.commit();

            if (list.isEmpty()) {
                return null;
            } else {
                return list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            return null;
        }

    }

    /**
     * find List<E> which is all entity in table
     *
     * @return
     */
    public List<E> findAll() {
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT e FROM " + entityClass.getName() + " as e where e.visible = true ");
            List<E> list = query.list();

            tx.commit();

            return list;

        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            return new LinkedList<E>();
        }
    }
}