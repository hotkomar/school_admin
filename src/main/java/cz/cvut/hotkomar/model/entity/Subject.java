/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.model.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Version;
import org.hibernate.annotations.Type;

/**
 *
 * @author Marie Hotkova
 */
@Entity
@Table (name="SUBJECT")
public class Subject implements Serializable {
    //subject registration identification number

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subject")
    private Long id;
     
    private String name;
    // date, when subject was created
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar validFrom;
    //date, when subject was canceled
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar expiration;
    //when admin delete subject, visible will be false
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
    
    
}
