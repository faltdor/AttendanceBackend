package com.cuemby.attendance.model.v1.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.cuemby.attendance.domain.Attendance;
import com.cuemby.attendance.model.v1.AttendanceDTO;

@Mapper
public interface IAttendanceMapper {

	IAttendanceMapper INSTANCE = Mappers.getMapper(IAttendanceMapper.class);

	AttendanceDTO attendanceToAttendanceDTO(Attendance attendance);

	@InheritInverseConfiguration
	Attendance attendanceDTOToAttendance(AttendanceDTO attendanceDto);

}
