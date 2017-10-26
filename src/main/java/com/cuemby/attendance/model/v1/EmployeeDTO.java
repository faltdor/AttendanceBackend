package com.cuemby.attendance.model.v1;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeDTO { 
	private String id;
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
	@Max(120)
	private Integer age;
	
	@NotBlank
	private String position;
	private Double salary;
	private Date birthdate;	
	private Date dateAdmission;
	
	
	private String status;
}
