package com.altimetrik.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.model.Employee;
import com.altimetrik.model.Project;
import com.altimetrik.repo.EmployeeRepo;
import com.altimetrik.repo.ProjectRepo;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepo employeeRepo;

	@Autowired
	ProjectRepo projectRepo;

	@Autowired
	EmailService emailService;

	public Employee saveEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}

	public void allocateProjectToEmployee(Long employeeId, String projectName, float allocation) {

		if (projectName == null || projectName.isEmpty()) {
			throw new RuntimeException("Project name cannot be null or empty");
		}
		Employee employee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new RuntimeException("Employee not found"));

		Project project = projectRepo.findByProjectName(projectName)
				.orElseThrow(() -> new RuntimeException("Project not found"));

		
		employee.setProject(project);
		employee.setProjectAllocation(allocation); 

		employeeRepo.save(employee);

		String subject = "Project Allocation Notification";
		String message = "You have been successfully allocated to the project:"; 
		emailService.sendEmail(employee.getEmail(), subject, message);
	}

	public Employee updateProjectAllocation(Long employeeId, String projectName, float newAllocation) {
		Employee employee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new RuntimeException("Employee not found"));

		if (newAllocation < 0.1f || newAllocation > 1.0f) {
			throw new IllegalArgumentException("Allocation must be between 0.1 and 1.0");
		}

		Project project = projectRepo.findByProjectName(projectName)
				.orElseThrow(() -> new RuntimeException("Project not found"));

		if (employee.getProject() != null && !employee.getProject().getProjectName().equals(projectName)) {
			throw new RuntimeException("This employee is not assigned to the specified project");
		}

		employee.setProject(project);
		employee.setProjectAllocation(newAllocation);

		return employeeRepo.save(employee);
	}

	public List<Employee> getEmployeesBySkills(String primarySkill, String secondarySkill) {
		return employeeRepo.findEmployeesBySkills(primarySkill, secondarySkill);
	}

	public Employee getSecondMostExperiencedEmployee(Long projectId) {
		List<Employee> employees = employeeRepo.findEmployeesByProjectId(projectId);

		if (employees == null || employees.size() < 2) {
			throw new RuntimeException("Not enough employees in the project to determine the 2nd most experienced.");
		}
		return employees.get(1);
	}

	public List<Employee> getEmployeesWithoutPrimarySkill(String primarySkill) {
		return employeeRepo.findEmployeesWithoutPrimarySkill(primarySkill);
	}
}
