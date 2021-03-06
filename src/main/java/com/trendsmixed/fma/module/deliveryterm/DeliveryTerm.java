package com.trendsmixed.fma.module.deliveryterm;

import com.fasterxml.jackson.annotation.JsonView;
import com.trendsmixed.fma.module.supplier.Supplier;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Thilina
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Table(name = "delivery_term")
public class DeliveryTerm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @JsonView(DeliveryTermView.Id.class)
    @Column(name = "id")
    private Integer id;
    @JsonView(DeliveryTermView.Code.class)
    @Column(name = "code", unique = true)
    private String code;
    @JsonView(DeliveryTermView.Name.class)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "deliveryTerm")
    private List<Supplier> supplierList;

    @JsonView(DeliveryTermView.All.class)
    public String getDisplay() {
        return code + " : " + name;
    }
}
