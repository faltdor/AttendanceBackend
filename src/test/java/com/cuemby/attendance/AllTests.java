package com.cuemby.attendance;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.cuemby.attendance.controllers.v1.AttendanceControllerTest;
import com.cuemby.attendance.controllers.v1.EmployeeControllerTest;
import com.cuemby.attendance.repositories.AttendanceRepositoryImplTest;
import com.cuemby.attendance.repositories.EmployeeRepositoryImplTest;
import com.cuemby.attendance.services.AttendanceServiceImplTest;
import com.cuemby.attendance.services.EmployeeServiceImplTest;
import com.cuemby.attendance.v1.model.mappers.AttendanceMapperImplTest;
import com.cuemby.attendance.v1.model.mappers.EmployeeMapperImplTest;

@RunWith(Suite.class)
@SuiteClasses({ AttendanceApiApplicationTests.class,
				EmployeeRepositoryImplTest.class,
				EmployeeMapperImplTest.class,
				EmployeeControllerTest.class,
				EmployeeServiceImplTest.class,
				AttendanceMapperImplTest.class,
				AttendanceRepositoryImplTest.class,
				AttendanceControllerTest.class,
				AttendanceServiceImplTest.class
				
})		


public class AllTests {

}
