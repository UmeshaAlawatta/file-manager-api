/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trendsmixed.fma.module.section;

import com.trendsmixed.fma.module.costcenter.CostCenter;
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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.trendsmixed.fma.module.sectiontype.SectionType;
import com.trendsmixed.fma.module.section.SectionView;

/**
 *
 * @author Thilina
 */
@Entity
@Table(name = "section")
@NamedQueries({ @NamedQuery(name = "Section.findAll", query = "SELECT s FROM Section s") })
public class Section implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@JsonView(SectionView.Id.class)
	@Column(name = "id")
	private Integer id;
	@JsonView(SectionView.Code.class)
	@Column(name = "code")
	private String code;
	@JsonView(SectionView.Name.class)
	@Column(name = "name")
	private String name;
	@JsonView(SectionView.MTBFTarget.class)
	@Column(name = "mtbf_target")
	private Double mtbfTarget;
	@JsonView(SectionView.MTTRTarget.class)
	@Column(name = "mttr_target")
	private Double mttrTarget;
	@JsonView(SectionView.MDTTarget.class)
	@Column(name = "mdt_target")
	private Double mdtTarget;
	@JsonView(SectionView.SectionType.class)
	@JoinColumn(name = "section_type_id", referencedColumnName = "id")
	@ManyToOne(optional = true)
	private SectionType sectionType;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "section")
	private List<CostCenter> costCenterList;

	public Section() {
	}

	public Section(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SectionType getSectionType() {
		return sectionType;
	}

	public void setSectionType(SectionType sectionType) {
		this.sectionType = sectionType;
	}

	public List<CostCenter> getCostCenterList() {
		return costCenterList;
	}

	public void setCostCenterList(List<CostCenter> costCenterList) {
		this.costCenterList = costCenterList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Section)) {
			return false;
		}
		Section other = (Section) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.trendsmixed.fma.entity.Section[ id=" + id + " ]";
	}

}