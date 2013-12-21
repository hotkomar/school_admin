/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.test;

import cz.cvut.hotkomar.service.manager.GeneralManager;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
/**
 *
 * @author maru
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/java/cz/cvut/hotkomar/test/application-context.xml")
@Transactional
@TestExecutionListeners({ 
        WebContextTestExecutionListener.class, 
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class })
public abstract class GenericTest<O> implements IDataTest {
    
    protected GeneralManager<O> manager;
    private Class<O> clazz;

    public void setManager(GeneralManager<O> manager) {
        this.manager = manager;
    }

    public void setClazz(Class<O> clazz) {
        this.clazz = clazz;
    }
    
    public void addEntity(){
        try {
            
            List<O> findAll1 = manager.findAll();
            O o = clazz.newInstance();
            manager.add(o, false);
            o.getClass().getField("visible").set(o, Boolean.TRUE);
            List<O> findAll2 = manager.findAll();
            assertEquals("kravo",findAll1.size()+1, findAll2.size());
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(GenericTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(GenericTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(GenericTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GenericTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteEntity(){
            List<O> findAll1 = manager.findAll();
            if(!findAll1.isEmpty()){
                O o = findAll1.get(0);
                manager.delete(o);
                List<O> findAll2 = manager.findAll();
                assertEquals(findAll1.size() - 1, findAll2.size());           
            } else {
                assertEquals(true, false);
            }
    }
    
    @Test
    public void doTest(){
        addEntity();
        editEntity();
        deleteEntity();
    }
    
}
