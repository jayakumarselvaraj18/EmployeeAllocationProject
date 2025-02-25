package com.altimetrik.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

import com.altimetrik.enumclass.CapabilityCentre;
import com.altimetrik.enumclass.Designation;

@Entity
public class Employee {

	@Id
	private Long employeeId; 

	private String employeeName; 
	
	@Enumerated(EnumType.STRING) 
	private CapabilityCentre capabilityCentre;

	@Temporal(TemporalType.DATE) 
	private Date dateOfJoining;

	@Enumerated(EnumType.STRING) 
	private Designation designation;

	private String primarySkill; 

	private String secondarySkill; 

	private int overallExperience;

    private String email;

    
	@ManyToOne
	@JoinColumn(name = "projectId")
	private Project project;

	private float projectAllocation;

	public float getProjectAllocation() {
		return projectAllocation;
	}

	public void setProjectAllocation(float projectAllocation) {
		this.projectAllocation = projectAllocation;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public CapabilityCentre getCapabilityCentre() {
		return capabilityCentre;
	}

	public void setCapabilityCentre(CapabilityCentre capabilityCentre) {
		this.capabilityCentre = capabilityCentre;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public String getPrimarySkill() {
		return primarySkill;
	}

	public void setPrimarySkill(String primarySkill) {
		this.primarySkill = primarySkill;
	}

	public String getSecondarySkill() {
		return secondarySkill;
	}

	public void setSecondarySkill(String secondarySkill) {
		this.secondarySkill = secondarySkill;
	}

	public int getOverallExperience() {
		return overallExperience;
	}

	public void setOverallExperience(int overallExperience) {
		this.overallExperience = overallExperience;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

	
}
