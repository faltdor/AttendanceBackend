package com.cuemby.attendance.domain;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee extends Entity {
	
	
	private String identification;
	
	
	private String firstName;
	
	
	private String lastName;
	
	
	private Integer age;
	
	
	private String position;
	private Double salary;
	
	
	private Date birthdate;	
	private Date dateAdmission;
	
	@NotBlank
	private String status;
	
}
