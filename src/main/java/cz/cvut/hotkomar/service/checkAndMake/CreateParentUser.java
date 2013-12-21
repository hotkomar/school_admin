/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.checkAndMake;

import java.security.SecureRandom;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maru
 */
@Service
public class CreateParentUser {
    
    private CheckForm checkForm;
@Autowired
    public void setCheckForm(CheckForm checkForm) {
        this.checkForm = checkForm;
    }
    
    
    public String createLogin (String name,String surname, Long id)
    {
        String login="";
        login+= getChar(name);
        login+= getChar(surname);
        int lengtId= id.toString().length();
        switch(lengtId)
        {
            case(1):
                login+="00000"+id.toString();
                break;
           case(2):
               login+="0000"+id.toString();
                break;
               case(3):
               login+="000"+id.toString();
                break;
              case(4):
               login+="00"+id.toString();
                break;
                  case(5):
               login+="0"+id.toString();
                break;
             default:
                 
               login+=id.toString();
               
             
        }
        boolean uniqueLogin = checkForm.uniqueLogin(login);
        while(!uniqueLogin)
        {
            int length = login.length()-1;
           String lastChar= login.substring(length);
            int valueOf = Integer.parseInt(lastChar);
            valueOf++;
            login+=valueOf;
            uniqueLogin=checkForm.uniqueLogin(login);
        }
        return login;
    }
    
    private char getChar(String s)
    {
        s= s.toLowerCase();
         for (int i = 0; i < s.length(); i++) {
             char b=s.charAt(i) ;
        System.out.println("char "+b);
         int c= Integer.valueOf(b);
         System.out.println("int "+c);
            if(c>66 && c<=122)
            {
                return b;
            }
            
        }
         return'a';
        
    }
    
     public String getPassword() {
        Random rand = new SecureRandom();
        String password = "";
        for (int i = 0; i < 6; i++) {
            double d = rand.nextDouble();
            if (d < 0.3 && d >= 0.0) {
                int number = rand.nextInt(10);
                password += number;
            } else if (d >= 0.3 && d < 0.6) {
                int r = rand.nextInt(26);
                String upperCase = String.valueOf((char) (r + 65));
                password += upperCase;
            } else {
                int r = rand.nextInt(26);
                String lowerCase = String.valueOf((char) (r + 97));
                password += lowerCase;
            }
        }
        return password;
    }
     
     
}
