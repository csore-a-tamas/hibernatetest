/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csat.hibernatetest.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Csore_A_Tamas
 */
@Entity
@Table(name = "`test`.`c`")
public class C implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "`id`")
    private Integer id;
    
    @Column(name = "`some_field`")
    private String someField;
    
    @OneToOne
    @JoinColumn(name = "`D_id`")
    private D d;

    public C() {
    }

    public C(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSomeField() {
        return someField;
    }

    public void setSomeField(String someField) {
        this.someField = someField;
    }

    public D getD() {
        return d;
    }

    public void setD(D d) {
        this.d = d;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof C)) {
            return false;
        }
        C other = (C) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "csat.hibernatetest.jpa.C[ id=" + id + " ]";
    }
    
}
