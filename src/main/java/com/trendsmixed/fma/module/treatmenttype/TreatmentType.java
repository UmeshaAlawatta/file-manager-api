/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trendsmixed.fma.module.treatmenttype;

import com.fasterxml.jackson.annotation.JsonView;
import com.trendsmixed.fma.module.treatment.Treatment;
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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author Thilina
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Table(name = "treatment_type")
@NamedQueries({
    @NamedQuery(name = "TreatmentType.findAll", query = "SELECT i FROM TreatmentType i")})
public class TreatmentType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @JsonView(TreatmentTypeView.Id.class)
    @Column(name = "id")
    private Integer id;
    @JsonView(TreatmentTypeView.Code.class)
    @Column(name = "code", unique = true)
    private String code;
    @JsonView(TreatmentTypeView.Name.class)
    @Column(name = "name")
    private String name;
    @JsonView(TreatmentTypeView.Treatment.class)
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "treatmentType")
    private List<Treatment> treatmentList;

}
