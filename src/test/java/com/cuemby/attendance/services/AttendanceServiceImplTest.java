package com.cuemby.attendance.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cuemby.attendance.domain.Attendance;
import com.cuemby.attendance.domain.Employee;
import com.cuemby.attendance.enums.StatusEmployee;
import com.cuemby.attendance.model.v1.AttendanceDTO;
import com.cuemby.attendance.model.v1.AttendanceEmployeeDTO;
import com.cuemby.attendance.model.v1.mappers.IAttendanceMapper;
import com.cuemby.attendance.repositories.IAttendanceRespository;
import com.cuemby.attendance.repositories.IEmployeeRepository;
import com.cuemby.attendance.services.exception.ResourceNotFoundException;
import com.cuemby.attendance.services.impl.AttendanceServiceImpl;

public class AttendanceServiceImplTest {

	@Mock
	IAttendanceRespository attendanceRepository;

	@Mock
	IEmployeeRepository employeeRepository;

	AttendanceServiceImpl attendanceServiceImpl;

	DateTimeFormatter formatter;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.attendanceServiceImpl = new AttendanceServiceImpl(attendanceRepository, IAttendanceMapper.INSTANCE,
				employeeRepository);
	}

	@Test
	public void testCreate() {
		// Given
		try {
			String dateAssistance = "2016-08-03";
			Attendance attendance = new Attendance();
			attendance.setId("23445");
			attendance.setEmployeeId("11111111");
			attendance.setCurrentDateAssistance(new SimpleDateFormat("yyyy-MM-dd").parse(dateAssistance));

			AttendanceDTO attendanceDTO = new AttendanceDTO();
			attendanceDTO.setEmployeeId("11111111");
			attendanceDTO.setCurrentDateAssistance(new SimpleDateFormat("yyyy-MM-dd").parse(dateAssistance));

			Employee employee1 = new Employee();
			employee1.setId("11111111");
			employee1.setFirstName("Emple 1");
			employee1.setIdentification("9999999");

			when(employeeRepository.findByIdentification(anyString(),anyString())).thenReturn(Optional.of(employee1));

			when(attendanceRepository.save(any(Attendance.class))).thenReturn(attendance);

			// When
			AttendanceDTO result = attendanceServiceImpl.create(attendanceDTO);

			// Them
			assertThat(result).isNotNull();
			assertThat(result.getId()).isNotNull();
			assertThat(result.getEmployeeId()).isEqualTo("11111111");

		} catch (ParseException e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void testListAll() {
		// Given
		Map<String, Attendance> attendaceMap = new ConcurrentHashMap<>(3);

		try {
			String dateAssistance = "2016-08-03";
			Attendance attendance1 = new Attendance();
			attendance1.setEmployeeId("1234455");
			attendance1.setCurrentDateAssistance(new SimpleDateFormat("yyyy-MM-dd").parse(dateAssistance));
			attendaceMap.put("1", attendance1);

			Attendance attendance2 = new Attendance();
			attendance2.setEmployeeId("1234455");
			attendance2.setCurrentDateAssistance(new SimpleDateFormat("yyyy-MM-dd").parse(dateAssistance));
			attendaceMap.put("2", attendance2);

			Attendance attendance3 = new Attendance();
			attendance3.setEmployeeId("1234455");
			attendance3.setCurrentDateAssistance(new SimpleDateFormat("yyyy-MM-dd").parse(dateAssistance));
			attendaceMap.put("3", attendance3);

			when(attendanceRepository.findAll()).thenReturn(attendaceMap);

			// When
			List<AttendanceDTO> AttendanceList = attendanceServiceImpl.listAll();

			assertThat(AttendanceList).isNotNull();
			assertThat(AttendanceList).isNotEmpty();
			assertThat(AttendanceList.size()).isEqualTo(3);

		} catch (ParseException e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void testListAttendance() {
		try {
			Attendance attendance = new Attendance();
			attendance.setId("1");
			attendance.setEmployeeId("1");
			attendance.setCurrentDateAssistance(new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01"));

			Attendance attendance2 = new Attendance();
			attendance2.setId("1");
			attendance2.setEmployeeId("1");
			attendance2.setCurrentDateAssistance(new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-15"));
			
			Attendance attendance3 = new Attendance();
			attendance3.setId("1");
			attendance3.setEmployeeId("1");
			attendance3.setCurrentDateAssistance(new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-30"));
			
			
			
			// when
			Date dateInit = new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01");
			Date dateEnd = new SimpleDateFormat("yyyy-MM-dd").parse("2017-02-01");
			
			List<Attendance> listAtte = Arrays.asList(attendance,attendance2,attendance3);
			
			when(attendanceRepository.findAllByDateInitDateEnd(any(),any())).thenReturn(listAtte);
			
			Employee employee1 = new Employee();
			employee1.setId("1");
			employee1.setFirstName("Employee one");
			employee1.setIdentification("123456733");
			employee1.setStatus(StatusEmployee.ACTIVE.toString());
			
			when(employeeRepository.findAllByStatusId(anyString(), anyString())).thenReturn(Optional.of(employee1));
			
			List<Attendance> attList = attendanceRepository.findAllByDateInitDateEnd(dateInit,dateEnd);
			assertThat(attList).isNotEmpty();
			
			List<AttendanceEmployeeDTO> listAttendance = new ArrayList<>(attList.size());
			
			
			attList.forEach(atten -> {
				
				Optional<Employee> optional = employeeRepository.findAllByStatusId(StatusEmployee.ACTIVE.toString(), "1");
				
				if(optional.isPresent()) {
					Employee employee = optional.get();
					listAttendance.add(
							new AttendanceEmployeeDTO(atten.getCurrentDateAssistance(),
									employee.getId(),
									employee.getIdentification(),
									employee.getFirstName(),
									employee.getLastName(),
									employee.getAge(),
									employee.getPosition(),
									employee.getSalary(),
									employee.getBirthdate(),
									employee.getDateAdmission(),
									employee.getStatus()));
					
				}
						
				
			});
			
			assertThat(listAttendance).isNotEmpty();
			
			
		} catch (ParseException e) {
			fail(e.getMessage());
		}

	}

}
