/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.manager;

import cz.cvut.hotkomar.model.entity.FileEntity;
import javax.annotation.PostConstruct;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marie Hotkova
 */
@Service
public class FileMan extends GeneralManager<FileEntity>{
    @PostConstruct
    public void init() throws InterruptedException{
        Session session = sessionFactory.openSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        fullTextSession.createIndexer(FileEntity.class).startAndWait();
        fullTextSession.close();
        super.setEntityClass(FileEntity.class);
    }
}
