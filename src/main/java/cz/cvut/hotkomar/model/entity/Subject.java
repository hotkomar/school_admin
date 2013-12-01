/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.model.entity;

import cz.cvut.hotkomar.service.checkAndMake.DateFunction;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

/**
 *
 * @author Marie Hotkova
 */
@Entity
@Table (name="SUBJECT")
@Indexed
public class Subject implements Serializable {
    //subject registration identification number
     @DocumentId(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subject")
    private Long id;
     @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String name;
    // date, when subject was created
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar validFrom;
    //date, when subject was canceled
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar expiration;
    //when admin delete subject, visible will be false
    @OneToMany(mappedBy = "id_class",targetEntity = SubjectOfClass.class)
    private List<SubjectOfClass> subjects;
    
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
    public Calendar getValidFrom() {
        return validFrom;
    }

    /**
     *
     * @param validFrom
     */
    public void setValidFrom(Calendar validFrom) {
        this.validFrom = validFrom;
    }

    /**
     *
     * @return
     */
    public Calendar getExpiration() {
        return expiration;
    }

    /**
     *
     * @param expiration
     */
    public void setExpiration(Calendar expiration) {
        this.expiration = expiration;
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

    public List<SubjectOfClass> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectOfClass> subjects) {
        this.subjects = subjects;
    }
    
    
    @Transient
    public String getDateExpiration()
    {
        if(expiration!=null)
        {
            DateFunction dateFunction = new DateFunction();
            return dateFunction.getDateString(expiration);
        }
        return"";
    }
    
    @Transient
    public String getDateValidFrom()
    {
        if(validFrom!=null)
        {
            DateFunction dateFunction = new DateFunction();
            return dateFunction.getDateString(validFrom);
        }
        return"";
    }

    
    
}
