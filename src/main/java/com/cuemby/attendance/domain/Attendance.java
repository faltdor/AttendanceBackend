package com.cuemby.attendance.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Attendance extends Entity{

	private String employeeId;

	private Date currentDateAssistance;
	
	
}
