/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.form.admin;

import cz.cvut.hotkomar.service.valid.BornMatch;
import cz.cvut.hotkomar.service.valid.FieldMatch;
import cz.cvut.hotkomar.service.valid.WorkMatch;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author Maru
 */
@FieldMatch(first = "password", second = "password2", message = "Hesla musí být stejná.")
@BornMatch(born = "dateOfBorn",message = "Datum je špatně zadané nebo neexistuje")
@WorkMatch(work = "dateOfEmploy",message = "Datum nástupu není platné datum.")
public class EditTeacherForm {
    private Long id;
    private String id_class;
    
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
    @NotEmpty(  message = "Příjmení nesmí být prázdné.")
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
    private CommonsMultipartFile file;
    private Long photo;
    @Pattern(regexp = "^[0-3][0-9].[0-1][0-9].[1-2][0-9][0-9][0-9]$|^{0}$", message = "Datum nástupu musí být ve tvaru den.měsíc.rok .")
    private String dateOfEmploy;
    private String degree;
    private String education;
    private Boolean educationConsultant;
    @Min(value = 0, message = "minimální mzda je 0 Kč")
    @Max(value = 32767, message = "maximální mzda může být 32 767 Kč")
    //@NotNull(message = "Nesmí zůstat prázdné.")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private String wagePerHour;
    private Boolean admin;
    private Long actualPage;

    
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

    public String getId_class() {
        return id_class;
    }

    public void setId_class(String id_class) {
        this.id_class = id_class;
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

    public CommonsMultipartFile getFile() {
        return file;
    }

    public void setFile(CommonsMultipartFile file) {
        this.file = file;
    }

    public Long getPhoto() {
        return photo;
    }

    public void setPhoto(Long photo) {
        this.photo = photo;
    }

    public String getDateOfEmploy() {
        return dateOfEmploy;
    }

    public void setDateOfEmploy(String dateOfEmploy) {
        this.dateOfEmploy = dateOfEmploy;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Boolean getEducationConsultant() {
        return educationConsultant;
    }

    public void setEducationConsultant(Boolean educationConsultant) {
        this.educationConsultant = educationConsultant;
    }

    public String getWagePerHour() {
        return wagePerHour;
    }

    public void setWagePerHour(String wagePerHour) {
        this.wagePerHour = wagePerHour;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Long getActualPage() {
        return actualPage;
    }

    public void setActualPage(Long actualPage) {
        this.actualPage = actualPage;
    }
    
}
