/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.form.teacher;

import cz.cvut.hotkomar.form.student.NewMarkStudentForm;
import cz.cvut.hotkomar.service.valid.BornMatch;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Maru
 */
@BornMatch(born = "date",message = "Datum je špatně zadané nebo neexistuje")
public class NewClassMarksForm {
    @NotEmpty(message = "Jméno testu musí být vyplněno.")
    private String name;
     @NotEmpty(message = "Datum testu musí být vyplněno.")
    private String date;
    private Long idSubject;
    private Long idTeacher;
    private Long idClass;
    private List <NewMarkStudentForm> listOfStudents = new ArrayList();
    Map<Short,Short> map = new HashMap<Short, Short>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<NewMarkStudentForm> getListOfStudents() {
        return listOfStudents;
    }

    public void setListOfStudents(List<NewMarkStudentForm> listOfStudents) {
        this.listOfStudents = listOfStudents;
    }

    public Long getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(Long idSubject) {
        this.idSubject = idSubject;
    }

    public Long getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(Long idTeacher) {
        this.idTeacher = idTeacher;
    }

    public Long getIdClass() {
        return idClass;
    }

    public void setIdClass(Long idClass) {
        this.idClass = idClass;
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
