package com.cuemby.attendance.model.v1;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
public class AttendanceEmployeeDTO {

	private Date currentDateAssistance;
	
	private String idemployee;
	
	@NotBlank
	@Size(min = 3, max = 20)
	private String identification;
	@NotBlank
	@Size(min = 3, max = 255)
	private String firstName;
	@NotBlank
	@Size(min = 3, max = 255)
	private String lastName;
	@Min(1)
	@Max(200)
	private Integer age;
	
	@NotBlank
	private String position;
	private Double salary;
	private Date birthdate;	
	private Date dateAdmission;
	
	@NotBlank
	private String status;
	
}
