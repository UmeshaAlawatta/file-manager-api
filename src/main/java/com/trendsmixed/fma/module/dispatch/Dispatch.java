/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trendsmixed.fma.module.dispatch;

import com.trendsmixed.fma.module.customer.Customer;
import com.fasterxml.jackson.annotation.JsonView;
import com.trendsmixed.fma.module.jobdispatch.JobDispatch;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

/**
 *
 * @author Thilina
 */
@Entity
@Data
@Table(name = "dispatch")
@NamedQueries({
    @NamedQuery(name = "Dispatch.findAll", query = "SELECT d FROM Dispatch d")})
public class Dispatch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @JsonView(DispatchView.Id.class)
    private Integer id;
    @Column(name = "dispatch_date")
    @Temporal(TemporalType.DATE)
    @JsonView(DispatchView.DispatchDate.class)
    private Date dispatchDate;
    @JsonView(DispatchView.Customer.class)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Customer customer;
    @JsonView(DispatchView.JobDispatch.class)
    @OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, mappedBy = "dispatch")
    private List<JobDispatch> jobDispatchList;

    public Dispatch() {
    }

    public Dispatch(Integer id) {
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
        if (!(object instanceof Dispatch)) {
            return false;
        }
        Dispatch other = (Dispatch) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.trendsmixed.fma.entity.Dispatch[ id=" + id + " ]";
    }

}
