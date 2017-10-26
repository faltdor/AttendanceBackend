package com.cuemby.attendance.controllers.v1;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cuemby.attendance.controllers.v1.EmployeeController;
import com.cuemby.attendance.controllers.v1.exceptions.RestResponseEntityExceptionHandler;
import com.cuemby.attendance.model.v1.EmployeeDTO;
import com.cuemby.attendance.services.impl.EmployeeServiceImpl;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

public class EmployeeControllerTest extends AbstractRestControllerTest {

	@Mock
	EmployeeServiceImpl employeeServiceImpl;

	@InjectMocks
	EmployeeController employeeController;

	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
				.setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
	}

	@Test
	public void testGetallEmployees() throws Exception {
		// Given

		EmployeeDTO employee1 = new EmployeeDTO();
		employee1.setFirstName("Employee 1");
		employee1.setIdentification("1234567");

		EmployeeDTO employee2 = new EmployeeDTO();
		employee1.setFirstName("Employee 1");
		employee1.setIdentification("1234567");

		EmployeeDTO employee3 = new EmployeeDTO();
		employee1.setFirstName("Employee 1");
		employee1.setIdentification("1234567");

		List<EmployeeDTO> employeeList = Arrays.asList(employee1, employee2, employee3);

		when(employeeServiceImpl.listAll()).thenReturn(employeeList);

		mockMvc.perform(get(EmployeeController.BASE_URL).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		verify(employeeServiceImpl, times(1)).listAll();

	}

	@Test
	public void testInactiveUser() throws Exception {
		// Given
		mockMvc.perform(delete(EmployeeController.BASE_URL+"/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
		
		verify(employeeServiceImpl, times(1)).updateEstatusEmployeeInactive(anyString());
	}

	@Test
	public void testSaveEmployeer() throws Exception {
		// Given
		EmployeeDTO employee1 = new EmployeeDTO();
		employee1.setFirstName("Employee 1");
		employee1.setIdentification("1234567");

		when(employeeServiceImpl.create(any(EmployeeDTO.class))).thenReturn(employee1);

		//{"id":null,"identification":"1234567","firstName":"Employee 1","lastName":null,"age":null,"position":null,"salary":null,"birthdate":null,"dateAdmission":null,"status":null}
		mockMvc.perform(post(EmployeeController.BASE_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(employee1)))
				.andExpect(status().isBadRequest());

	}
	
	
	
	
	

}
