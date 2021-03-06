package com.trendsmixed.fma.module.treatmenttype;

import com.fasterxml.jackson.annotation.JsonView;
import com.trendsmixed.fma.module.treatment.Treatment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Thilina
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Table(name = "treatment_type")
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

    @JsonView(TreatmentTypeView.All.class)
    public String getDisplay() {
        return code + " : " + name;
    }
}
