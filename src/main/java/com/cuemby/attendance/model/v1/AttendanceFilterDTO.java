package com.cuemby.attendance.model.v1;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class AttendanceFilterDTO {
	
	private Date dateInit;
	private Date dateEnd;
}
