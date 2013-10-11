/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.manager;

import java.io.Serializable;
import java.util.List;



/**
 *
 * @author Marie Hoťková
 */
public interface  GeneralManImp<E>  extends Serializable{
   public void add(E entity, boolean b);
   public void edit(E entity, boolean b);
    public void delete(E entity);
    public void visible(Long id, boolean b);
    public E findById(Long id);
     public List<E> findAll();
   
    
}
