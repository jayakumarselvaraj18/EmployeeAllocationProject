package com.altimetrik.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Date;
import java.util.List;

import com.altimetrik.enumclass.AccountName;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Project {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @Enumerated(EnumType.STRING)  
    private AccountName accountName;

    private String projectName;  

    @Column(nullable = false)
    private float allocation;

    @Temporal(TemporalType.DATE)  
    private Date projectStartDate;

    @Temporal(TemporalType.DATE)
    private Date projectEndDate;

    private String remarks;  
    
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Employee> employees;  

    

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public AccountName getAccountName() {
		return accountName;
	}

	public void setAccountName(AccountName accountName) {
		this.accountName = accountName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public float getAllocation() {
		return allocation;
	}

	public void setAllocation(float allocation) {
		this.allocation = allocation;
	}

	public Date getProjectStartDate() {
		return projectStartDate;
	}

	public void setProjectStartDate(Date projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public Date getProjectEndDate() {
		return projectEndDate;
	}

	public void setProjectEndDate(Date projectEndDate) {
		this.projectEndDate = projectEndDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

    
    
	 

    
	

}
