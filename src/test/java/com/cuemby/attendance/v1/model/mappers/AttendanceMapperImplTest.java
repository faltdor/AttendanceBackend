package com.cuemby.attendance.v1.model.mappers;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import com.cuemby.attendance.domain.Attendance;
import com.cuemby.attendance.model.v1.AttendanceDTO;
import com.cuemby.attendance.model.v1.mappers.IAttendanceMapper;

public class AttendanceMapperImplTest {
	
	IAttendanceMapper attendanceMapper;
	
	DateTimeFormatter formatter;
	
	@Before
	public void setUp() throws Exception {
		this.attendanceMapper = IAttendanceMapper.INSTANCE;
		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	}

	@Test
	public void testMapper() throws ParseException {
		//Given
		String dateAssistance = "2016-08-03";
		Attendance attendance = new Attendance();
		attendance.setEmployeeId("1234455");
		attendance.setCurrentDateAssistance(new SimpleDateFormat("yyyy-MM-dd").parse(dateAssistance));
		
		//when
		AttendanceDTO attendanceDTO = attendanceMapper.attendanceToAttendanceDTO(attendance);
		
		//them
		assertThat(attendanceDTO).isNotNull();
		assertThat(attendanceDTO.getEmployeeId()).isEqualTo("1234455");
		assertThat(attendanceDTO.getCurrentDateAssistance()).isEqualTo(dateAssistance);
		
	
	}

}
