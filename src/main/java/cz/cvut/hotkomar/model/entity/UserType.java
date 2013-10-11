/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import org.hibernate.annotations.Type;

/**
 *
 * @Marie Hoťková
 */
@Entity
@Table(name="USERTYPE")
public class UserType implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id_userType")
    private Long id;
    private String name;
    @Version
    private Integer version;
    @Type(type = "true_false")
    private Boolean visible;

    /**
     * get Version
     *
     * @return
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * set Version
     *
     * @param version
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * get id
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * set id
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * get name
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * set name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get visble
     *
     * @return
     */
    public Boolean getVisible() {
        return visible;
    }

    /**
     * set visible
     *
     * @param visible
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
