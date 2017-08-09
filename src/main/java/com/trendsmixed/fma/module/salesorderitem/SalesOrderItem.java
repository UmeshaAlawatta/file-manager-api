/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trendsmixed.fma.module.salesorderitem;

import com.trendsmixed.fma.module.salesorder.SalesOrder;
import com.trendsmixed.fma.module.job.Job;
import com.trendsmixed.fma.module.item.Item;
import com.trendsmixed.fma.module.delivery.Delivery;
import com.fasterxml.jackson.annotation.JsonView;
import java.io.Serializable;
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
import javax.persistence.UniqueConstraint;
import lombok.Data;

/**
 *
 * @author Thilina
 */
@Entity
@Data
@Table(name = "sales_order_item", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"item_id", "sales_order_id"})})
@NamedQueries({
    @NamedQuery(name = "SalesOrderItem.findAll", query = "SELECT s FROM SalesOrderItem s")})
public class SalesOrderItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @JsonView(SalesOrderItemView.Id.class)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @JsonView(SalesOrderItemView.Quantity.class)
    @Column(name = "quantity")
    private Double quantity;
    @JsonView(SalesOrderItemView.Price.class)
    @Column(name = "price")
    private Double price;
    @JsonView(SalesOrderItemView.Item.class)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Item item;
    @JsonView(SalesOrderItemView.SalesOrder.class)
    @JoinColumn(name = "sales_order_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SalesOrder salesOrder;
    @OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, mappedBy = "salesOrderItem")
    private List<Delivery> deliveryList;
    @JsonView(SalesOrderItemView.Job.class)
    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, mappedBy = "salesOrderItem")
    private Job job;

    public SalesOrderItem() {
    }

    public SalesOrderItem(Integer id) {
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
        if (!(object instanceof SalesOrderItem)) {
            return false;
        }
        SalesOrderItem other = (SalesOrderItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.trendsmixed.fma.entity.SalesOrderItem[ id=" + id + " ]";
    }

}
