package com.cuemby.attendance.model.v1;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AttendanceDTO {
	
	private String id;
	
	@NotBlank
	private String employeeId;
	
	private Date currentDateAssistance;
	
}
