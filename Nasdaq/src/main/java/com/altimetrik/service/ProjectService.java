package com.altimetrik.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.model.Project;
import com.altimetrik.repo.ProjectRepo;

@Service
public class ProjectService {

	@Autowired
	ProjectRepo projectRepo;

	 public Project saveProject(Project project) {
	        return projectRepo.save(project);
	    }

}
