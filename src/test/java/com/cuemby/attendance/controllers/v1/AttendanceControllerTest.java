package com.cuemby.attendance.controllers.v1;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
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

import com.cuemby.attendance.controllers.v1.exceptions.RestResponseEntityExceptionHandler;
import com.cuemby.attendance.model.v1.AttendanceDTO;
import com.cuemby.attendance.services.impl.AttendanceServiceImpl;

import static org.mockito.Matchers.any;

public class AttendanceControllerTest extends AbstractRestControllerTest {

	@Mock
	AttendanceServiceImpl attendanceServiceImpl;

	@InjectMocks
	AttendanceController attendanceController;

	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(attendanceController)
				.setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
	}

	@Test
	public void testGetallAttendance() throws Exception {
		// Given
		String dateAssistance = "2016-08-03";
		AttendanceDTO attendance1 = new AttendanceDTO();
		attendance1.setId("23445");
		attendance1.setEmployeeId("1234455");
		attendance1.setCurrentDateAssistance(new SimpleDateFormat("yyyy-MM-dd").parse(dateAssistance));

		AttendanceDTO attendance2 = new AttendanceDTO();
		attendance2.setId("23445");
		attendance2.setEmployeeId("1234455");
		attendance2.setCurrentDateAssistance(new SimpleDateFormat("yyyy-MM-dd").parse(dateAssistance));

		List<AttendanceDTO> attenddanceList = Arrays.asList(attendance1, attendance2);

		when(attendanceServiceImpl.listAll()).thenReturn(attenddanceList);

		mockMvc.perform(get(AttendanceController.BASE_URL).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		verify(attendanceServiceImpl, times(1)).listAll();

	}

	@Test
	public void testCreateNewAttendance() throws Exception {
		// Given
		String dateAssistance = "2016-08-03";
		AttendanceDTO attendance1 = new AttendanceDTO();
		attendance1.setId("23445");
		attendance1.setEmployeeId("1234455");
		attendance1.setCurrentDateAssistance(new SimpleDateFormat("yyyy-MM-dd").parse(dateAssistance));

		when(attendanceServiceImpl.create(any(AttendanceDTO.class))).thenReturn(attendance1);

		mockMvc.perform(post(AttendanceController.BASE_URL).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(attendance1))).andExpect(status().isCreated());

	}

}
