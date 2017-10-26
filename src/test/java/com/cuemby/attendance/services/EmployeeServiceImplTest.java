package com.cuemby.attendance.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cuemby.attendance.domain.Employee;
import com.cuemby.attendance.model.v1.EmployeeDTO;
import com.cuemby.attendance.model.v1.mappers.IEmployeeMapper;
import com.cuemby.attendance.repositories.IEmployeeRepository;
import com.cuemby.attendance.services.exception.ResourceAlreadyExistsException;
import com.cuemby.attendance.services.impl.EmployeeServiceImpl;

public class EmployeeServiceImplTest {

	@Mock
	IEmployeeRepository employeeRepository;

	EmployeeServiceImpl employeeServiceImpl;

	DateTimeFormatter formatter;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.employeeServiceImpl = new EmployeeServiceImpl(employeeRepository, IEmployeeMapper.INSTANCE);
	}

	@Test
	public void testListAll() {
		// Given
		List<Employee> employees = new ArrayList<>(3);

		Employee employee1 = new Employee();
		employee1.setFirstName("Employee 1");
		employee1.setIdentification("1234567");
		employees.add(employee1);

		Employee employee2 = new Employee();
		employee2.setFirstName("Employee 1");
		employee2.setIdentification("1234567");
		employees.add(employee2);

		Employee employee3 = new Employee();
		employee3.setFirstName("Employee 1");
		employee3.setIdentification("1234567");
		employees.add(employee3);

		when(employeeRepository.findAllByStatus(anyString())).thenReturn(employees);

		// When
		List<EmployeeDTO> employeeList = employeeServiceImpl.listAll();

		assertThat(employeeList).isNotNull();
		assertThat(employeeList).isNotEmpty();
		assertThat(employeeList.size()).isEqualTo(3);

	}

	@Test
	public void createNewEmployee() {
		// Given
		Employee employeeResult = new Employee();
		employeeResult.setId("1");
		employeeResult.setFirstName("Employee 1");
		employeeResult.setIdentification("1234567");

		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setFirstName("Employee 1");
		employeeDTO.setIdentification("1234567");

		when(employeeRepository.save(any(Employee.class))).thenReturn(employeeResult);

		// When
		EmployeeDTO result = employeeServiceImpl.create(employeeDTO);

		// Them
		assertThat(result).isNotNull();
		assertThat(result.getId()).isNotNull();
		assertThat(result.getIdentification()).isEqualTo("1234567");

	}

	@Test(expected = ResourceAlreadyExistsException.class)
	public void testValidateExceptionWhenUserExist() {
		// Given
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setFirstName("Employee 1");
		employeeDTO.setIdentification("1234567");

		when(employeeRepository.save(any(Employee.class))).thenThrow(new ResourceAlreadyExistsException());

		// When
		employeeServiceImpl.create(employeeDTO);

		// Them
		verify(employeeRepository, times(1)).save(any(Employee.class));

	}

	@Test
	public void updateEstatusEmployeeInactive() {
		// Given
		Employee employee = new Employee();
		employee.setFirstName("Employee 1");
		employee.setIdentification("1234567");
		String id = "1234567";
		employee.setId(id);
		
		
		Employee employeeDTO = new Employee();
		employeeDTO.setFirstName("Employee 1");
		employeeDTO.setIdentification("1234567");
		employeeDTO.setId(id);

		when(employeeRepository.getOne(anyString())).thenReturn(Optional.of(employeeDTO));

		when(employeeRepository.update(any(Employee.class))).thenReturn(employeeDTO);
				
				
		employeeServiceImpl.updateEstatusEmployeeInactive(id);

		verify(employeeRepository, times(1)).update(any(Employee.class));
	}

}
