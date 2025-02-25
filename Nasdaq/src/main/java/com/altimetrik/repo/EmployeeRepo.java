package com.altimetrik.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.altimetrik.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{

    @Query("SELECT e FROM Employee e WHERE e.primarySkill = :primarySkill AND e.secondarySkill = :secondarySkill")
    List<Employee> findEmployeesBySkills(@Param("primarySkill") String primarySkill, 
                                         @Param("secondarySkill") String secondarySkill);
    
    @Query("SELECT e FROM Employee e WHERE e.project.projectId = :projectId ORDER BY e.overallExperience DESC")
    List<Employee> findEmployeesByProjectId(@Param("projectId") Long projectId);
    
    @Query("SELECT e FROM Employee e WHERE e.primarySkill <> :primarySkill")
    List<Employee> findEmployeesWithoutPrimarySkill(@Param("primarySkill") String primarySkill);
}
