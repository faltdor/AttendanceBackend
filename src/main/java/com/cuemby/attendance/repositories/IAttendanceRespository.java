package com.cuemby.attendance.repositories;

import java.util.Date;
import java.util.List;

import com.cuemby.attendance.domain.Attendance;

public interface IAttendanceRespository extends ICustomCrudRepository<Attendance, String> {

	List<Attendance> findAllByDateInitDateEnd(Date dateInit, Date dateEnd);

}
