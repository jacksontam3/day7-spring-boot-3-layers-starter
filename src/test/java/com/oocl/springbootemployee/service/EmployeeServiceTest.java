package com.oocl.springbootemployee.service;

import com.oocl.springbootemployee.exception.EmployeeAgeNotValidException;
import com.oocl.springbootemployee.exception.EmployeeNotActiveException;
import com.oocl.springbootemployee.exception.EmployeeSalaryNotValidException;
import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import com.oocl.springbootemployee.repository.IEmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class EmployeeServiceTest {

    @Mock
    IEmployeeRepository mockedEmployeeRepository;

    @InjectMocks
    EmployeeService employeeService;

    @Test
    void should_return_the_given_employees_when_getAllEmployees() {
        //given
        IEmployeeRepository mockedEmployeeRepository = mock(IEmployeeRepository.class);
        when(mockedEmployeeRepository.getAll()).thenReturn(List.of(new Employee(1, "Lucy", 18, Gender.FEMALE, 8000.0)));
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //when
        List<Employee> allEmployees = employeeService.getAllEmployees();

        //then
        assertEquals(1, allEmployees.size());
        assertEquals("Lucy", allEmployees.get(0).getName());
    }

    @Test
    void should_return_the_created_employee_when_create_given_a_employee() {
        //given
        IEmployeeRepository mockedEmployeeRepository = mock(IEmployeeRepository.class);
        Employee lucy = new Employee(1, "Lucy", 18, Gender.FEMALE, 8000.0);
        when(mockedEmployeeRepository.addEmployee(any())).thenReturn(lucy);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //when
        Employee createdEmployee = employeeService.create(lucy);

        //then
        assertEquals("Lucy", createdEmployee.getName());
        verify(mockedEmployeeRepository).addEmployee(argThat(Employee::isActive));
    }

    @Test
    void should_throw_EmployeeAgeNotValidException_when_create_given_an_employee() {
        //given
        Employee lucy = new Employee(1, "Lucy", 17, Gender.FEMALE, 8000.0);
        //when

        //Then
        assertThrows(EmployeeAgeNotValidException.class, () -> employeeService.create(lucy));
        verify(mockedEmployeeRepository, never()).addEmployee(any());
    }

    @Test
    void should_throw_EmployeeAgeNotValidException_when_employee_salary_less_than_30000_given_an_employee(){
        //given
        Employee lucy = new Employee(1, "Lucy", 30, Gender.FEMALE, 8000.0);
        //when

        //Then
        assertThrows(EmployeeSalaryNotValidException.class, () -> employeeService.create(lucy));
        verify(mockedEmployeeRepository, never()).addEmployee(any());

    }

    @Test
    void should_throw_EmployeeNotActiveException_when_create_given_an_inactive_employee() {
        //given
        Employee lucy = new Employee(1, "Lucy", 18, Gender.FEMALE, 8000.0);
        lucy.setActive(false);
        when(mockedEmployeeRepository.getEmployeeById(1)).thenReturn(lucy);
        //when

        //then
        assertThrows(EmployeeNotActiveException.class, () -> employeeService.update(1, lucy));
        verify(mockedEmployeeRepository, never()).updateEmployee(any(),any());
    }
}
