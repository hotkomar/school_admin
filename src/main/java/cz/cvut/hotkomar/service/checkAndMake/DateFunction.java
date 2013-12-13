/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.checkAndMake;

import cz.cvut.hotkomar.service.message.FormMessage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
//        Calendar calendar = Calendar.getInstance();
//        month--;
//        calendar.set(Calendar.DAY_OF_MONTH, day);
//        calendar.set(Calendar.MONTH, month);
//        calendar.set(Calendar.YEAR, year);
//        return calendar;
        System.out.println("měsíc v DB "+month);
        // month--;
        String string_date = day+"."+month+"."+year;
        System.out.println("datum do db "+string_date);
        SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance();
        Date d;
        try {
            d = (Date) f.parse(string_date);
            long milliseconds = d.getTime();
        
        
        
        calendar.setTimeInMillis(milliseconds);
        } catch (ParseException ex) {
            Logger.getLogger(DateFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return calendar;
        
    }

    public Calendar setDBDate(int day, int month, int year) {

        String string_date = day+"."+month+"."+year;
        System.out.println("datum do db "+string_date);
        SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance();
        Date d;
        try {
            d = (Date) f.parse(string_date);
            long milliseconds = d.getTime();
        
        
        
        calendar.setTimeInMillis(milliseconds);
        } catch (ParseException ex) {
            Logger.getLogger(DateFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
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
        Calendar calendar = setDate(Integer.parseInt(splitDataString[0]), Integer.parseInt(splitDataString[1]), Integer.parseInt(splitDataString[2]));
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
     *
     * @param d
     * @return
     */
    public boolean validDate(String d) {
        if (isValidDate(d) && isDateInPast(d)) {
            return false;
        }
        return true;

    }

    public int semester(Calendar c) {
        Integer month = getMonth(c);
        if(month>=9)
        {
            return 1;
        }
        else{
            if(month>=2 && month<9)
            {
                return 2;
            }
            else{
                return 3;
            }
        }

    }

    public List<String> listOfSemester(Calendar c, Calendar yearOfEnd) {
        List<String> list = new ArrayList<String>();
        int semester = semester(c);
        int year = Integer.valueOf(getYear(c));
        int actualYear = Integer.valueOf(getYear(Calendar.getInstance()));
        //rok zakončení třídy
        int lastYear = Integer.valueOf(getYear(yearOfEnd));
        int semesterActualYear = semester(Calendar.getInstance());
        //  int semesterActualYear =2;

        String actualSchoolYear = getSchoolYear(semesterActualYear, actualYear);
        String lastSchoolYear = (lastYear - 1) + "/" + lastYear;
        System.out.println("last school year " + lastSchoolYear);
        String schoolYear;
        int count = 0;
            System.out.println("************************************");
            System.out.println("************************************");
            System.out.println("************************************");
            System.out.println("************************************");
            System.out.println("semester "+semester);
            System.out.println("year "+year);
            System.out.println("actual Year "+actualYear);
            System.out.println("lastYear "+lastYear);
            System.out.println("semesterActualYear "+semesterActualYear);
            System.out.println("actual School year "+actualSchoolYear);
            System.out.println("lastSchoolYear "+lastSchoolYear);
            System.out.println("************************************");
            System.out.println("************************************");
            System.out.println("************************************");
            
            
            
        do {
            schoolYear = getSchoolYear(semester, year + count);
            System.out.println("school Year "+schoolYear);
            list.add(schoolYear);
            count++;
        } while ((!schoolYear.equals(actualSchoolYear)) && (!schoolYear.equals(lastSchoolYear)));
        if (semesterActualYear == 2 && (!schoolYear.equals(lastSchoolYear))) {
            System.out.println("vrazil jsem tam if");
            schoolYear = getSchoolYear(semester, year + count);
            list.add(schoolYear);
        }
        return list;
    }

    private Integer getMonth(Calendar c) {
        DateFormat df = new SimpleDateFormat("MM");
        Integer valueOf = Integer.valueOf(df.format(c.getTime()));
        return valueOf;
    }

    private String getSchoolYear(int semester, int year) {
        System.out.println("rok" + year);
        String string = "";
        if (semester == 1) {
            string = ((year) + "/" + (year + 1));

        } else {
            if(semester==2 ||semester==3){
            string = ((year - 1) + "/" + (year));
            }
        }

        return string;
    }
}
