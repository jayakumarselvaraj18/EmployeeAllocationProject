package com.altimetrik.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.model.Employee;
import com.altimetrik.model.Project;
import com.altimetrik.repo.EmployeeRepo;
import com.altimetrik.repo.ProjectRepo;
import com.altimetrik.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping
	public Employee saveEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}

	@PostMapping("/allocate-project")
	public ResponseEntity<String> allocateProjectToEmployee(@RequestParam Long employeeId, 
	                                                        @RequestParam String projectName, 
	                                                        @RequestParam float allocation) {
	    if (projectName == null || projectName.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Project name is required");
	    }
	    try {
	        employeeService.allocateProjectToEmployee(employeeId, projectName, allocation);
	        return ResponseEntity.ok("Project allocation successful");
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}


	@PutMapping("/modify-allocation")
	public Employee modifyProjectAllocation(@RequestParam Long employeeId, @RequestParam String projectName,
			@RequestParam float newAllocation) {
		return employeeService.updateProjectAllocation(employeeId, projectName, newAllocation);
	}

	@GetMapping("/second-most-experienced/{projectId}")
	public Employee getSecondMostExperiencedEmployee(@PathVariable Long projectId) {
		return employeeService.getSecondMostExperiencedEmployee(projectId);
	}

	@GetMapping("/skills")
	public List<Employee> getEmployeesBySkills(@RequestParam String primarySkill, @RequestParam String secondarySkill) {
		return employeeService.getEmployeesBySkills(primarySkill, secondarySkill);
	}

	@GetMapping("/without-primary-skill")
	public List<Employee> getEmployeesWithoutPrimarySkill(@RequestParam String primarySkill) {
		return employeeService.getEmployeesWithoutPrimarySkill(primarySkill);
	}

}
