/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.model.entity;

import cz.cvut.hotkomar.service.checkAndMake.DateFunction;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;



/**
 *
 * @author Marie Hotkova
 */
@MappedSuperclass
public abstract class AbstractUser implements Serializable{
    //user registration identification number
    @DocumentId(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;
    @ManyToMany//(fetch = FetchType.EAGER)
    private Set<UserType> idType = new HashSet<UserType>();
    // name for log in
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String login;
    // password for log in
    private String password;
    //user's mail address
    private String mail;
    //user name
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String name;
    //user surname
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String surname;
    //user identification number
    private String identificationNumber;
    //user date of born
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dateOfBorn;
    //place, where user was borned
    private String placeOfBorn;
    // number of mobile phone
    private String mobilePhone;
    // number of phone number
    private String phoneNumber;
    // number of fax
    private String fax;
    // user's photo
    @IndexedEmbedded
    @OneToOne//(fetch = FetchType.EAGER)
    private FileEntity photo;
    // visible is false, when admin delete user
    @Type(type = "true_false")
    private Boolean visible;
    @Version
    private Integer version;
    

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public Set<UserType> getIdType() {
        return idType;
    }

    /**
     *
     * @param idType
     */
    public void setIdType(Set<UserType> idType) {
        this.idType = idType;
    }

    /**
     *
     * @return
     */
    public String getLogin() {
        return login;
    }

    /**
     *
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public String getMail() {
        return mail;
    }

    /**
     *
     * @param mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getSurname() {
        return surname;
    }

    /**
     *
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     *
     * @return
     */
    public String getIdentificationNumber() {
        return identificationNumber;
    }

    /**
     *
     * @param identificationNumber
     */
    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    /**
     *
     * @return
     */
    public Calendar getDateOfBorn() {
        return dateOfBorn;
    }

    /**
     *
     * @param dateOfBorn
     */
    public void setDateOfBorn(Calendar dateOfBorn) {
        this.dateOfBorn = dateOfBorn;
       
    }

    /**
     *
     * @return
     */
    public String getPlaceOfBorn() {
        return placeOfBorn;
    }

    /**
     *
     * @param palceOfBorn
     */
    public void setPlaceOfBorn(String palceOfBorn) {
        this.placeOfBorn = palceOfBorn;
    }

    /**
     *
     * @return
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     *
     * @param mobilePhone
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     *
     * @return
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * @return
     */
    public String getFax() {
        return fax;
    }

    /**
     *
     * @param fax
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     *
     * @return
     */
    public Boolean getVisible() {
        return visible;
    }

    /**
     *
     * @param visible
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    /**
     *
     * @return
     */
    public Integer getVersion() {
        return version;
    }

    /**
     *
     * @param version
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    public FileEntity getPhoto() {
        return photo;
    }

    public void setPhoto(FileEntity photo) {
        this.photo = photo;
    }
@Transient
    public String getBorn() {
        if(dateOfBorn!=null)
        {
            DateFunction dateFunction = new DateFunction();
          return  dateFunction.getDateString(dateOfBorn);
        }
        return "";
    }

    
    
    
}
