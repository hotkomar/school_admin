/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.form;

/**
 *
 * @author Marie Hoťková
 */
public class PaginationForm {
    
    private Integer actualPage;
    private Integer countOfPage;

    public int getActualPage() {
        return actualPage.intValue();
    }

    public void setActualPage(Integer actualPage) {
        this.actualPage = actualPage;
    }

    public int getCountOfPage() {
        return countOfPage.intValue();
    }

    public void setCountOfPage(Integer countOfPage) {
        this.countOfPage = countOfPage;
    }
    
    
    
}
