/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.form.teacher;

import cz.cvut.hotkomar.service.valid.BornMatch;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Maru
 */
@BornMatch(born = "markDate",message = "Datum je špatně zadané nebo neexistuje")
public class ChangeMarkForm {

   private Long id;
   private Short mark;
   @NotEmpty(message = "Datum testu musí být vyplněno.")
   private String markDate;
   private Short classified;
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

    public Short getClassified() {
        return classified;
    }

    public void setClassified(Short classified) {
        this.classified = classified;
    }

    public String getMarkDate() {
        return markDate;
    }

    public void setMarkDate(String markDate) {
        this.markDate = markDate;
    }


   
      
}
