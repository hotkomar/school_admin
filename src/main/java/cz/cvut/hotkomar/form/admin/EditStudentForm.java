/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.form.admin;

import cz.cvut.hotkomar.service.valid.BornMatch;
import cz.cvut.hotkomar.service.valid.FieldMatch;
import java.util.Calendar;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author Maru
 */
@FieldMatch(first = "password", second = "password2", message = "Hesla musí být stejná.")
@BornMatch(born = "dateOfBorn",message = "Datum je špatně zadané nebo neexistuje")
public class EditStudentForm {
    private Long id;
    private Long id_class;
    //student's mother name
    @NotEmpty( message = "Jméno nesmí být prázdné.")
    private String motherName;
    //student's mother surname
    @NotEmpty( message = "Příjmení nesmí být prázdné.")
    private String motherSurname;
    // student's father name
    private String fatherName;
    //student's father surname
    private String fatherSurname;
    //student's mother's phone number
    @NotEmpty( message = "Telefonní číslo nesmí být prázdné.")
    private String motherPhone;
    // student's father's pohone number
    private String fatherPhone;
 
    // password for log in
    @Pattern(regexp = "^^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(.){5,255}$", message = "Heslo musí mít minimálně 5 znaků a musí obsahovat minimálně jedno číslo, jedno velké a jedno malé písmeno.")
    private String password;
    @Pattern(regexp = "^^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(.){5,255}$", message = "Heslo musí mít minimálně 5 znaků a musí obsahovat minimálně jedno číslo, jedno velké a jedno malé písmeno.")
    private String password2;
    //user's mail address
     @Email(message = "E-mail není ve správném formátu.")
    private String mail;
    //user name
     @NotEmpty( message = "Jméno nesmí být prázdné.")
    private String name;
    //user surname
      @NotEmpty(message = "Příjmení nesmí být prázdné")
    private String surname;
    //user identification number
       @Pattern(regexp = "^[0-9]{6}/[0-9]{3,4}$|^{0}$", message = "Rodné číslo musí být ve tvaru xxxxxx/xxxx, kde x je číslice.")
    private String identificationNumber;
    //user date of born
     @Pattern(regexp = "^[0-3][0-9].[0-1][0-9].[1-2][0-9][0-9][0-9]$|^{0}$", message = "Datum narození musí být ve tvaru den.měsíc.rok .")
     @NotEmpty(message = "Datum narození musí být vyplněno.")
    private String dateOfBorn;
    //place, where user was borned
    private String placeOfBorn;
    // number of mobile phone
    private String mobilePhone;
    // number of phone number
    private String phoneNumber;
    // number of fax
    private String fax;
     @Email(message = "E-mail není ve správném formátu.")
    private String motherMail;
      @Email(message = "E-mail není ve správném formátu.")
    private String fatherMail;
    private CommonsMultipartFile file;
    private MultipartFile photo;
    private Calendar born;
//    private CheckForm checkForm;
//
//    @Autowired
//    public void setCheckForm(CheckForm checkForm) {
//        this.checkForm = checkForm;
//    }
    
    @AssertTrue(message = "Soubor není obrázek nebo je příliš velký.")
   public boolean isValidFile () 
   {
       if(!file.isEmpty()){
            String[] split = file.getContentType().split("/");
        if(file.getSize()<=204800 && split[0].equals("image") )
        {
            return true;
        } else {
        return false;
        }
       }
       return true;
   }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_class() {
        return id_class;
    }

    public void setId_class(Long id_class) {
        this.id_class = id_class;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherSurname() {
        return motherSurname;
    }

    public void setMotherSurname(String motherSurname) {
        this.motherSurname = motherSurname;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherSurname() {
        return fatherSurname;
    }

    public void setFatherSurname(String fatherSurname) {
        this.fatherSurname = fatherSurname;
    }

    public String getMotherPhone() {
        return motherPhone;
    }

    public void setMotherPhone(String motherPhone) {
        this.motherPhone = motherPhone;
    }

    public String getFatherPhone() {
        return fatherPhone;
    }

    public void setFatherPhone(String fatherPhone) {
        this.fatherPhone = fatherPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getDateOfBorn() {
        return dateOfBorn;
    }

    public void setDateOfBorn(String dateOfBorn) {
        this.dateOfBorn = dateOfBorn;
    }

    public String getPlaceOfBorn() {
        return placeOfBorn;
    }

    public void setPlaceOfBorn(String placeOfBorn) {
        this.placeOfBorn = placeOfBorn;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMotherMail() {
        return motherMail;
    }

    public void setMotherMail(String motherMail) {
        this.motherMail = motherMail;
    }

    public String getFatherMail() {
        return fatherMail;
    }

    public void setFatherMail(String fatherMail) {
        this.fatherMail = fatherMail;
    }

    public CommonsMultipartFile getFile() {
        return file;
    }

    public void setFile(CommonsMultipartFile file) {
        this.file = file;
    }

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }

    public Calendar getBorn() {
        return born;
    }

    public void setBorn(Calendar born) {
        this.born = born;
    }
    
}
