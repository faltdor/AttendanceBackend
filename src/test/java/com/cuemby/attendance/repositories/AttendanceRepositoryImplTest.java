package com.cuemby.attendance.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.cuemby.attendance.domain.Attendance;
import com.cuemby.attendance.repositories.impl.AttendanceRepositoryImpl;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class AttendanceRepositoryImplTest {

	// @Autowired
	AttendanceRepositoryImpl attendanceRepositoryImpl;

	DateTimeFormatter formatter;

	@Before
	public void setUp() throws Exception {
		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		attendanceRepositoryImpl = new AttendanceRepositoryImpl();
	}

	@Test
	public void testFindAll() {
		Map<String, Attendance> attendanceList = attendanceRepositoryImpl.findAll();
		assertNotNull(attendanceList);
		assertThat(attendanceList.size()).isEqualTo(0);
	}

	@Test
	public void testSave() throws ParseException {
		String dateAssistance = "2016-08-03";
		Attendance attendance = new Attendance();
		attendance.setEmployeeId("1234455");
		attendance.setCurrentDateAssistance(new SimpleDateFormat("yyyy-MM-dd").parse(dateAssistance));
		// when
		Attendance attendanceResult = attendanceRepositoryImpl.save(attendance);

		// Then
		assertThat(attendanceResult).isNotNull();

	}

	@Test
	public void testSaveAll() {
		// Given

		try {
			String dateAssistance = "2016-08-03";
			Attendance attendance1 = new Attendance();
			attendance1.setEmployeeId("1234455");
			attendance1.setCurrentDateAssistance(new SimpleDateFormat("yyyy-MM-dd").parse(dateAssistance));

			Attendance attendance2 = new Attendance();
			attendance2.setEmployeeId("1234455");
			attendance2.setCurrentDateAssistance(new SimpleDateFormat("yyyy-MM-dd").parse(dateAssistance));

			Attendance attendance3 = new Attendance();
			attendance3.setEmployeeId("1234455");
			attendance3.setCurrentDateAssistance(new SimpleDateFormat("yyyy-MM-dd").parse(dateAssistance));

			Map<String, Attendance> attendanceList = attendanceRepositoryImpl
					.saveAll(Arrays.asList(attendance1, attendance2, attendance3));

			assertThat(attendanceList).isNotNull();
			assertThat(attendanceList.size()).isEqualTo(3);

		} catch (ParseException e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void testGetOne() {
		// Given
		String id = "Dont exist id";

		// when
		Optional<Attendance> attendanceResult = attendanceRepositoryImpl.getOne(id);
		// Then
		assertThat(attendanceResult).isEqualTo(Optional.empty());
		assertThat(attendanceResult.isPresent()).isEqualTo(false);

	}

	@Test
	public void testGetOneExist() {
		// Given
		String dateAssistance = "2016-08-03";
		Attendance attendance1 = new Attendance();
		attendance1.setEmployeeId("1234455");
		
		try {
			attendance1.setCurrentDateAssistance(new SimpleDateFormat("yyyy-MM-dd").parse(dateAssistance));

			attendance1 = attendanceRepositoryImpl.save(attendance1);

			// when
			Optional<Attendance> attendanceResult = attendanceRepositoryImpl.getOne(attendance1.getId());
			// Then
			assertThat(attendanceResult.isPresent()).isEqualTo(true);

		} catch (ParseException e) {
			fail(e.getMessage());
		}

	}
	
	@Test
	public void testFindAllByDateInitDateEnd() throws ParseException {
		String dateAssistance = "2017-01-01";
		Attendance attendance = new Attendance();
		attendance.setId("1");
		attendance.setEmployeeId("1234455");
		attendance.setCurrentDateAssistance(new SimpleDateFormat("yyyy-MM-dd").parse(dateAssistance));

		Attendance attendance2 = new Attendance();
		attendance2.setId("1");
		attendance2.setEmployeeId("1234455");
		attendance2.setCurrentDateAssistance(new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-15"));
		
		Attendance attendance3 = new Attendance();
		attendance3.setId("1");
		attendance3.setEmployeeId("1234455");
		attendance3.setCurrentDateAssistance(new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-30"));
		
		// when
		Attendance attendanceResult = attendanceRepositoryImpl.save(attendance);
		attendanceRepositoryImpl.save(attendance2);
		attendanceRepositoryImpl.save(attendance3);
		
		Date dateInit = new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01");
		Date dateEnd = new SimpleDateFormat("yyyy-MM-dd").parse("2017-02-01");
		
		
		List<Attendance> listAtte = attendanceRepositoryImpl.findAllByDateInitDateEnd(dateInit, dateEnd);
		
		
		// Then
		assertThat(attendanceResult).isNotNull();
		assertThat(listAtte).isNotEmpty();
		
		
	}

	

	

	

}
