package com.cuemby.attendance.v1.model.mappers;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import com.cuemby.attendance.domain.Employee;
import com.cuemby.attendance.model.v1.EmployeeDTO;
import com.cuemby.attendance.model.v1.mappers.IEmployeeMapper;

public class EmployeeMapperImplTest {
	
	IEmployeeMapper mapper; 
	
	DateTimeFormatter formatter;
	
	@Before
	public void setUp() throws Exception {
		this.mapper = IEmployeeMapper.INSTANCE;
		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	}

	@Test
	public void testMapper() throws ParseException {
		//Given
		Employee employee1 = new Employee();
		employee1.setFirstName("Employee 1");
		employee1.setIdentification("1234567");
		employee1.setLastName("Empl last name");
		employee1.setAge(10);
		employee1.setPosition("Mannager");
		employee1.setSalary(600000.0);
		String birthdate = "2016-08-01";
		employee1.setBirthdate(new SimpleDateFormat("yyyy-MM-dd").parse(birthdate));
		
		//when
		EmployeeDTO employeeDTO = mapper.employeeToEmployeeDTO(employee1);
		
		//them
		assertThat(employeeDTO).isNotNull();
		assertThat(employeeDTO.getFirstName()).isEqualTo("Employee 1");
		assertThat(employeeDTO.getLastName()).isEqualTo("Empl last name");
		assertThat(employeeDTO.getIdentification()).isEqualTo("1234567");
		assertThat(employeeDTO.getAge()).isEqualTo(10);
		assertThat(employeeDTO.getPosition()).isEqualTo("Mannager");
		
	
	}

}
