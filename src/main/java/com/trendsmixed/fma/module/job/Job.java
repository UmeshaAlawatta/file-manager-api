/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trendsmixed.fma.module.job;

import com.trendsmixed.fma.module.item.Item;
import com.fasterxml.jackson.annotation.JsonView;
import com.trendsmixed.fma.module.jobdispatch.JobDispatch;
import com.trendsmixed.fma.module.jobtype.JobType;
import com.trendsmixed.fma.module.operation.Operation;
import com.trendsmixed.fma.module.salesorderitem.SalesOrderItem;
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
import javax.persistence.OneToOne;
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
@Table(name = "job")
@NamedQueries({
    @NamedQuery(name = "Job.findAll", query = "SELECT j FROM Job j")})
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @JsonView(JobView.Id.class)
    @Column(name = "id")
    private Integer id;
    @JsonView(JobView.ActualShippedDate.class)
    @Column(name = "actual_sipped_date")
    @Temporal(TemporalType.DATE)
    private Date actualSippedDate;
    @JsonView(JobView.Comment.class)
    @Column(name = "comment")
    private String comment;
    @JsonView(JobView.ConfirmShippedDate.class)
    @Column(name = "confirm_shipped_date")
    @Temporal(TemporalType.DATE)
    private Date confirmShippedDate;
    @JsonView(JobView.JobDate.class)
    @Column(name = "job_date")
    @Temporal(TemporalType.DATE)
    private Date jobDate;
    @JsonView(JobView.JobNo.class)
    @Column(name = "job_no")
    private String jobNo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @JsonView(JobView.Quantity.class)
    @Column(name = "quantity")
    private Double quantity;
    @JsonView(JobView.RemainingQuantity.class)
    @Column(name = "remaining_quantity")
    private Double remainingQuantity;
    @OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, mappedBy = "job")
    private List<JobDispatch> jobDispatchList;
    @JsonView(JobView.Item.class)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Item item;
    @JsonView(JobView.JobType.class)
    @JoinColumn(name = "job_type_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private JobType jobType;
    @JsonView(JobView.SalesOrderItem.class)
    @JoinColumn(name = "sales_order_item_id", referencedColumnName = "id", nullable = true)
    @OneToOne()
    private SalesOrderItem salesOrderItem;
    @OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, mappedBy = "job")
    private List<Operation> operationList;

    public Job() {
    }

    public Job(Integer id) {
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
        if (!(object instanceof Job)) {
            return false;
        }
        Job other = (Job) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.trendsmixed.fma.entity.Job[ id=" + id + " ]";
    }

}
