/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.form.teacher;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Maru
 */
public class ChangeMarkForm {
   private Long id;
   private Short mark;
  Map<Short,Short> map = new HashMap<Short, Short>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getMark() {
        return mark;
    }

    public void setMark(Short mark) {
        this.mark = mark;
    }

    public Map<Short, Short> getMap() {
        return map;
    }

    public void setMap() {
       map.put(Short.valueOf("1"),Short.valueOf("1"));
       map.put(Short.valueOf("2"),Short.valueOf("2"));
       map.put(Short.valueOf("3"),Short.valueOf("3"));
       map.put(Short.valueOf("4"),Short.valueOf("4"));
       map.put(Short.valueOf("5"),Short.valueOf("5"));
    }
   
}
