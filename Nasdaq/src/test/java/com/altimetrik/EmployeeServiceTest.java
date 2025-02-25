package com.altimetrik;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.altimetrik.model.Employee;
import com.altimetrik.model.Project;
import com.altimetrik.repo.EmployeeRepo;
import com.altimetrik.repo.ProjectRepo;
import com.altimetrik.service.EmployeeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepo employeeRepo;

    @Mock
    private ProjectRepo projectRepo;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAllocateProjectToEmployee_Success() {
        // Arrange
        Long employeeId = 1L;
        String projectName = "Experian";
        float allocation = 0.5f;

        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setEmployeeName("John Doe");

        Project project = new Project();
        project.setProjectName(projectName);
        
        when(employeeRepo.findById(employeeId)).thenReturn(Optional.of(employee));
        when(projectRepo.findByProjectName(projectName)).thenReturn(Optional.of(project));

        // Act
        employeeService.allocateProjectToEmployee(employeeId, projectName, allocation);

        assertEquals(allocation, project.getAllocation());
        assertEquals(projectName, employee.getProject().getProjectName());
        verify(employeeRepo, times(1)).save(employee);  // Verify if save method was called
    }

    @Test
    public void testAllocateProjectToEmployee_ProjectNotFound() {
        Long employeeId = 1L;
        String projectName = "Experian";
        float allocation = 0.5f;

        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setEmployeeName("John Doe");

        when(employeeRepo.findById(employeeId)).thenReturn(Optional.of(employee));
        when(projectRepo.findByProjectName(projectName)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            employeeService.allocateProjectToEmployee(employeeId, projectName, allocation);
        });

        assertEquals("Project not found", exception.getMessage());
    }

    @Test
    public void testAllocateProjectToEmployee_EmployeeNotFound() {
        Long employeeId = 1L;
        String projectName = "Experian";
        float allocation = 0.5f;

        when(employeeRepo.findById(employeeId)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            employeeService.allocateProjectToEmployee(employeeId, projectName, allocation);
        });

        assertEquals("Employee not found", exception.getMessage());
    }
}
