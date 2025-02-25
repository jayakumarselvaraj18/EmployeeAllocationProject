package com.altimetrik.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.model.Employee;
import com.altimetrik.model.Project;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {

	Optional<Project> findByProjectName(String projectName);


	
}
