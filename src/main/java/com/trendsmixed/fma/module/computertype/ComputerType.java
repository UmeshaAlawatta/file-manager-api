package com.trendsmixed.fma.module.computertype;

import com.fasterxml.jackson.annotation.JsonView;
import com.trendsmixed.fma.module.computer.Computer;
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
@Table(name = "computer_type")
public class ComputerType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @JsonView(ComputerTypeView.Id.class)
    @Column(name = "id")
    private Integer id;
    @JsonView(ComputerTypeView.Code.class)
    @Column(name = "code", unique = true)
    private String code;
    @JsonView(ComputerTypeView.Name.class)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "computerType")
    private List<Computer> computerList;

    @JsonView(ComputerTypeView.All.class)
    public String getDisplay() {
        return code + " : " + name;
    }
}
