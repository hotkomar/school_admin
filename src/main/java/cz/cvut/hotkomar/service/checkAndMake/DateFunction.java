/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.checkAndMake;

import cz.cvut.hotkomar.service.message.FormMessage;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author Marie Hotkova
 */
@Service
public class DateFunction {
   final static String DATEFORM = "dd.MM.yyyy";
   
   
   private FormMessage message;
   
   /**
     *
     * @return
     */
    @ModelAttribute("message")
    public FormMessage getMessage() {
        return message;
    }

    /**
     *
     * @param message
     */
    @Autowired
    public void setMessage(FormMessage message) {
        this.message = message;
    }
   
   /**
     * set date by chosen day, month and year
     *
     * @param day
     * @param month
     * @param year
     * @return  
     */
    public Calendar setDate(int day, int month, int year) {
       Calendar calendar = Calendar.getInstance();
        month--;
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        return calendar;
    }
    
    
    /**
     *
     * @param s
     * @return
     */
    public String[] splitDataString(String s) {
       // System.out.println("STRING" + s);
        String[] split = s.split("\\.");
//        System.out.println("DÉLKA POLE"+split.length);
        if (split.length == 3) {
            return split;

        }
        return null;
    }
     /**
     *
     * @param calendar
     * @return
     */
    public String getDateString(Calendar calendar) {
        DateFormat df = new SimpleDateFormat(DATEFORM);
        return df.format(calendar.getTime());
    }
     /**
     *
     * @param calendar
     * @return
     */
    public String getYear(Calendar calendar) {
        DateFormat df = new SimpleDateFormat("yyyy");
        return df.format(calendar.getTime());
    }
     /**
     *
     * @param string
     * @return
     */
    public boolean isValidDate(String string) {


        try {
            SimpleDateFormat format = new SimpleDateFormat(DATEFORM);
            format.setLenient(false);
            format.parse(string);
        } catch (ParseException e) {
          //  message.setNegativeMes("Toto datum(" + string + ") neexistuje.");
            return false;
        } catch (IllegalArgumentException e) {
           // message.setNegativeMes("špatně zadané datum.");
            return false;
        }

       return true;
    }
     
     /**
     *
     * @param d
     * @return
     */
    public boolean isDateInPast(String d) {
         String[] splitDataString = splitDataString(d);
       Calendar calendar = setDate(Integer.parseInt(splitDataString[0]),Integer.parseInt(splitDataString[1]),Integer.parseInt(splitDataString[2]));
        Calendar c = Calendar.getInstance();
        long timeInMillis = c.getTimeInMillis();
        long timeInMillis1 = calendar.getTimeInMillis();
        if (timeInMillis1 <= timeInMillis) {
            return true;
        }
       // message.setNegativeMes("Den(" + getDateString(calendar) + ") s tímto datem ještě nenastal.");
        return false;
    }
     
    /**
     * return true if date isn't valid
     * @param d
     * @return
     */
    public boolean validDate(String d)
{
   if( isValidDate(d) && isDateInPast(d))
   {
       return false;
   }
   return true;
    
}
}
