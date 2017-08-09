/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trendsmixed.fma.module.manpowertype;

import com.trendsmixed.fma.module.manpower.Manpower;
import com.fasterxml.jackson.annotation.JsonView;
import com.trendsmixed.fma.module.manpowertype.ManpowerTypeView;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Thilina
 */
@Entity
@Data
@Table(name = "manpower_type")
@NamedQueries({
    @NamedQuery(name = "ManpowerType.findAll", query = "SELECT m FROM ManpowerType m")})
public class ManpowerType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @JsonView(ManpowerTypeView.Id.class)
    @Column(name = "id")
    private Integer id;
    @JsonView(ManpowerTypeView.Code.class)
    @Column(name = "code")
    private String code;
    @JsonView(ManpowerTypeView.Name.class)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, mappedBy = "manpowerType")
    private List<Manpower> manpowerList;

    public ManpowerType() {
    }

    public ManpowerType(Integer id) {
        this.id = id;
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
        if (!(object instanceof ManpowerType)) {
            return false;
        }
        ManpowerType other = (ManpowerType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.trendsmixed.fma.entity.ManpowerType[ id=" + id + " ]";
    }

}
