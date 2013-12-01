/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.pagination;

import cz.cvut.hotkomar.form.PaginationForm;
import java.awt.print.PageFormat;
import java.io.Serializable;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marie Hotkova
 */
@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Pagination implements Serializable{
  //number of actual page
  private int page;
  //count of pages
  private int countOfPages;
  //list of all items
  private List list;
  //number of items, which view on page
  private int itemsOnPage=20;
  // items, which view on page
  private List actualPage= null;
  //number of intems, which will be view on last page, when count of items isn't divisible completely
  private int mod;
  //information for pagination view
  private PaginationForm pageForm;
  private int firstItem=0;

    /**
     * Constructor
     */
    public Pagination() {
        this.pageForm = new PaginationForm();
    }
  
  

    /**
     * return number of actual page
     * @return
     */
    public int getPage() {
        return page;
    }

    /**
     * set number of actual page
     * @param page
     */
    public void setPage(int page) {
        this.page = page;
        System.out.println("stárnka je "+page);
    }

    /**
     * return count of pages
     * @return
     */
    public int getCountOfPages() {
        return countOfPages;
    }
/**
 *  count of pages
 * @param items
 * @param listSize 
 */
    private void setCountOfPages(int items, int listSize) {
       setMod(items, listSize);
        if(mod==0)
        {
            //number of items is divisible completely
            this.countOfPages=listSize/items;
        }
        else{
            //number of items isn't divisible completely
        countOfPages=(listSize/items)+1;
        }
                
        System.out.println("count of pages is "+countOfPages);
    }

    /**
     * return list of all items
     * @return
     */
    public List getList() {
        return list;
    }

    /**
     * set list of all items
     * @param list
     */
    public void setList(List list) {
        this.list = list;
        
        setCountOfPages(itemsOnPage,list.size());
        System.out.println("délka listu je "+list.size());
    }

    /**
     * return number of items, which view on 1 page
     * @return
     */
    public int getItemsOnPage() {
        return itemsOnPage;
    }

    /**
     * set number of items, which view on 1 page
     * @param itemsOnPage
     */
    public void setItemsOnPage(int itemsOnPage) {
        this.itemsOnPage = itemsOnPage;
       
    }

    /**
     * return list with items, which view on actual page
     * @return
     */
    public List getActualPage() {
        setActualPage();
        System.out.println("actual page size"+actualPage.size());
        return actualPage;
    }
/**
 * pripare number of items, which view on actual page
 */
    private void setActualPage() {
      setFirstItem();
     
        //   list.subList(firstItem, firstItem+itemsOnPage);
        if(page==1){
         actualPage   = list.subList(0,itemsOnPage);
}
        else{
            if(page==countOfPages)
            {
                System.out.println("modulo je"+mod);
                if(mod==1)
                {
                    actualPage.clear();
                    System.out.println("actual list"+actualPage.size());
                    actualPage.add(list.get(list.size()-1));
                }
                else{
                
                System.out.println("poslední stránka");
                actualPage =list.subList(firstItem,list.size());
                }
            }
            else{
            actualPage = list.subList(firstItem,firstItem+itemsOnPage);
            }
}
        
        System.out.println("list"+actualPage.size());  
    }
   /**
    * set frst item index to suibList function
    * @return 
    */
    private void setFirstItem()
    {
//        if(page==1){
//        firstItem=0;}
//        else{
//           firstItem+=20; 
//            System.out.println("firtItem"+firstItem);
//        }

      firstItem=(itemsOnPage*(page-1)); 
    }
/**
 * return first item index to subList function
 * @return 
 */
    private int getMod() {
        return mod;
    }
/**
 *  set modulo
 * @param items
 * @param listSize 
 */
    private void setMod(int items, int listSize) {
         int mod = listSize%items;
        this.mod = mod;
    }
  
    /**
     * treated exeption that can occur and set information for view
     * @param list
     * @param page
     * @return
     */
    public List paginationList (List list, Integer page)
   {
       //data, which is view on pages
       setList(list);
          // if list of students have more items then max items, which are view on page
        if(list.size()>getItemsOnPage()){
      
       
        //if number of page is bigger than count of page, set first page
        if(getCountOfPages()<page || page<0)
        {
            //when param page has wrong value, will be set to first page
            page=1;
        }
        setPage(page);
       List pageList = getActualPage();
            
            pageForm.setActualPage(page);
            pageForm.setCountOfPage(Integer.valueOf(getCountOfPages()));
            
       return pageList;
       
        }
        else
        {
            // if list of students have less items then max items, which are view on page
          
            pageForm.setActualPage(0);
            return list;
        }
   }

    /**
     * rezturn information about pagination for view
     * @return
     */
    public PaginationForm getPageForm() {
        return pageForm;
    }
/**
 * set information for pagination
 */
    private void setPageForm(PaginationForm pageForm) {
        this.pageForm = pageForm;
    }
   
}
