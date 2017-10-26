package com.cuemby.attendance.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import com.cuemby.attendance.domain.Employee;
import com.cuemby.attendance.enums.StatusEmployee;
import com.cuemby.attendance.repositories.impl.EmployeeRepositoryImpl;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class EmployeeRepositoryImplTest {

	// @Autowired
	EmployeeRepositoryImpl employeeRepositoryImpl;

	DateTimeFormatter formatter;

	@Before
	public void setUp() throws Exception {
		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		employeeRepositoryImpl = new EmployeeRepositoryImpl();
	}

	@Test
	public void testFindAll() {
		Map<String, Employee> listEmployee = employeeRepositoryImpl.findAll();
		assertNotNull(listEmployee);
		assertThat(listEmployee.size()).isEqualTo(0);
	}

	@Test
	public void testSave() throws ParseException {
		// Given
		Employee employee1 = new Employee();
		employee1.setFirstName("Emple 1");

		employee1.setIdentification("1234567");
		employee1.setLastName("Empl last name");
		employee1.setAge(10);
		employee1.setPosition("Mannager");
		employee1.setSalary(600000.0);
		String birthdate = "2016-08-03";

		employee1.setBirthdate(new SimpleDateFormat("yyyy-MM-dd").parse(birthdate));
		employee1.setDateAdmission(new SimpleDateFormat("yyyy-MM-dd").parse(birthdate));
		employee1.setStatus(StatusEmployee.ACTIVE.toString());

		// when
		Employee employeeResult = employeeRepositoryImpl.save(employee1);

		// Then
		assertThat(employeeResult).isNotNull();

	}

	@Test
	public void testSaveAll() {
		// Given
		Employee employee1 = new Employee();
		employee1.setFirstName("Employee 1");
		employee1.setIdentification("123456733");

		Employee employee2 = new Employee();
		employee2.setFirstName("Employee 1");
		employee2.setIdentification("1234567344");

		Employee employee3 = new Employee();
		employee3.setFirstName("Employee 1");
		employee3.setIdentification("1234567444");

		Map<String, Employee> employeeList = employeeRepositoryImpl
				.saveAll(Arrays.asList(employee1, employee2, employee3));

		assertThat(employeeList).isNotNull();
		assertThat(employeeList.size()).isEqualTo(3);

	}

	@Test
	public void testGetOne() {
		// Given
		String id = "Dont exist id";

		// when
		Optional<Employee> employeeResult = employeeRepositoryImpl.getOne(id);
		// Then
		assertThat(employeeResult).isEqualTo(Optional.empty());
		assertThat(employeeResult.isPresent()).isEqualTo(false);

	}

	@Test
	public void testGetOneExist() {
		// Given
		Employee employee1 = new Employee();
		employee1.setId("1");
		employee1.setFirstName("Employee 1");
		employee1.setIdentification("3444343434");
		employee1 = employeeRepositoryImpl.save(employee1);

		// when
		Optional<Employee> employeeResult = employeeRepositoryImpl.getOne(employee1.getId());
		// Then
		assertThat(employeeResult.isPresent()).isEqualTo(false);

	}

	@Test
	public void testEmployeeExist() {
		// Given
		Employee employee1 = new Employee();
		employee1.setFirstName("Employee 1");
		employee1.setIdentification("3444343434");

		Employee employeeResult = employeeRepositoryImpl.save(employee1);
		assertThat(employeeResult).isNotNull();

		boolean existResult = employeeRepositoryImpl.employeeExist("3444343434");

		assertThat(existResult).isEqualTo(true);

	}

	@Test
	public void testUpdate() {
		// Given
		Employee employee1 = new Employee();
		employee1.setId("12345");
		employee1.setFirstName("Employee 1");
		employee1.setIdentification("3444343434");
		employee1.setStatus(StatusEmployee.INACTIVE.toString());

		Employee employee = employeeRepositoryImpl.update(employee1);

		assertThat(employee).isNotNull();

		Optional<Employee> result = employeeRepositoryImpl.getOne("12345");

		assertThat(result.isPresent()).isEqualTo(true);
		assertThat(result.get().getStatus()).isEqualTo(StatusEmployee.INACTIVE.toString());

	}

	@Test
	public void testFindAllByStatus() {
		// Given
		Employee employee1 = new Employee();
		employee1.setId("12345");
		employee1.setFirstName("Employee 1");
		employee1.setIdentification("3444343434");
		employee1.setStatus(StatusEmployee.INACTIVE.toString());

		Employee employee = employeeRepositoryImpl.update(employee1);

		assertThat(employee).isNotNull();

		List<Employee> result = employeeRepositoryImpl.findAllByStatus(StatusEmployee.INACTIVE.toString());

		assertThat(result).isNotNull();
		assertThat(result.isEmpty()).isEqualTo(true);

	}

	@Test
	public void testFindAllByStatusId() {

		// Given
		Employee employee1 = new Employee();
		employee1.setId("1");
		employee1.setFirstName("Employee one");
		employee1.setIdentification("123456733");
		employee1.setStatus(StatusEmployee.ACTIVE.toString());
		
		employeeRepositoryImpl.update(employee1);
		
		Employee employee2 = new Employee();
		employee2.setId("2");
		employee2.setFirstName("Employee two");
		employee2.setIdentification("1234567344");
		employee2.setStatus(StatusEmployee.INACTIVE.toString());
		
		employeeRepositoryImpl.update(employee2);
		
		Employee employee3 = new Employee();
		employee3.setId("3");
		employee3.setFirstName("Employee three");
		employee3.setIdentification("12345674455");
		employee3.setStatus(StatusEmployee.INACTIVE.toString());
		employeeRepositoryImpl.update(employee3);
		
		
		Optional<Employee> listActives = employeeRepositoryImpl.findAllByStatusId(StatusEmployee.ACTIVE.toString(), "1");
		
		assertThat(listActives).isNotNull();
		assertThat(listActives.isPresent()).isEqualTo(true);
		

	}

}
