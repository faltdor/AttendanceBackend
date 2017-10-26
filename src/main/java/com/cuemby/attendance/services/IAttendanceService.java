package com.cuemby.attendance.services;

import java.util.List;

import com.cuemby.attendance.model.v1.AttendanceDTO;
import com.cuemby.attendance.model.v1.AttendanceEmployeeDTO;
import com.cuemby.attendance.model.v1.AttendanceFilterDTO;

public interface IAttendanceService {
	
	public AttendanceDTO create(AttendanceDTO domainObject);

	public List<AttendanceDTO> listAll();

	public List<AttendanceEmployeeDTO> listEmployeeAttendance(AttendanceFilterDTO attendanceFilter);
}
  