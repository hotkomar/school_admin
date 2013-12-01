/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.manager;

import cz.cvut.hotkomar.model.entity.SubjectOfClass;
import cz.cvut.hotkomar.model.entity.UserType;
import java.util.List;
import javax.annotation.PostConstruct;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marie Hotkova
 */
@Service
public class UserTypeMan extends GeneralManager<UserType> {
    @PostConstruct
    public void init(){
        super.setEntityClass(UserType.class);
    }
    
    public UserType findByType(String userType) {
        Session session = getSession();
        

        try {

            Query query = session.createQuery("SELECT u FROM UserType AS u WHERE u.name = :name AND u.visible=true");
            query.setParameter("name", userType);
            List<UserType> list = query.list();
            


            if (list.isEmpty()) {
                return null;
            } else {
                return  list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            if (tx != null) {
//                tx.rollback();
//            }
            return null;
        }

    }
}