package com.trendsmixed.fma.module.treatment;

import com.fasterxml.jackson.annotation.JsonView;
import com.trendsmixed.fma.module.accident.Accident;
import com.trendsmixed.fma.module.treatmenttype.TreatmentType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Thilina
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Table(name = "treatment")
public class Treatment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @JsonView(TreatmentView.Id.class)
    @Column(name = "id")
    private Integer id;
    @JsonView(TreatmentView.Code.class)
    @Column(name = "code", unique = true)
    private String code;
    @JsonView(TreatmentView.TreatmentCost.class)
    @Column(name = "treatment_cost")
    private Double treatmentCost;
    @JsonView(TreatmentView.Description.class)
    @Column(name = "description", unique = true)
    private String description;
    @JsonView(TreatmentView.LossManHours.class)
    @Column(name = "lossManHours")
    private Double lossManHours;
    @JsonView(TreatmentView.StartTime.class)
    @Column(name = "start_time")
    private Date startTime;
    @JsonView(TreatmentView.EndTime.class)
    @Column(name = "end_time")
    private Date endTime;
    @JsonView(TreatmentView.TreatmentType.class)
    @JoinColumn(name = "treatment_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TreatmentType treatmentType;
    @JsonView(TreatmentView.Accident.class)
    @JoinColumn(name = "accident_id", referencedColumnName = "id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Accident accident;

}
