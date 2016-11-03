/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trendsmixed.fma.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.trendsmixed.fma.jsonView.ControlPointRunLossView;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Thilina
 */
@Entity
@Table(name = "control_point_run_loss")
@NamedQueries({
    @NamedQuery(name = "ControlPointRunLoss.findAll", query = "SELECT c FROM ControlPointRunLoss c")})
public class ControlPointRunLoss implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @JsonView(ControlPointRunLossView.Id.class)
    @Column(name = "id")
    private Integer id;
    @JsonView(ControlPointRunLossView.ControlPointRun.class)
    @JoinColumn(name = "control_point_run_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ControlPointRun controlPointRun;
    @JsonView(ControlPointRunLossView.LossReason.class)
    @JoinColumn(name = "loss_reason_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private LossReason lossReason;

    public ControlPointRunLoss() {
    }

    public ControlPointRunLoss(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ControlPointRun getControlPointRun() {
        return controlPointRun;
    }

    public void setControlPointRun(ControlPointRun controlPointRun) {
        this.controlPointRun = controlPointRun;
    }

    public LossReason getLossReason() {
        return lossReason;
    }

    public void setLossReason(LossReason lossReason) {
        this.lossReason = lossReason;
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
        if (!(object instanceof ControlPointRunLoss)) {
            return false;
        }
        ControlPointRunLoss other = (ControlPointRunLoss) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.trendsmixed.fma.entity.ControlPointRunLoss[ id=" + id + " ]";
    }
    
}
