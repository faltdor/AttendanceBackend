package com.cuemby.attendance.model.v1;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttendanceListDTO {

	List<AttendanceDTO> attendanceList = null;
}
