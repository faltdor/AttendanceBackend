package com.cuemby.attendance.controllers.v1;


import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cuemby.attendance.model.v1.EmployeeDTO;
import com.cuemby.attendance.model.v1.EmployeeListDTO;
import com.cuemby.attendance.services.impl.EmployeeServiceImpl;


@RestController
@RequestMapping(EmployeeController.BASE_URL)
public class EmployeeController {
	
	public static final String BASE_URL = "/api/v1/employee";
	
	private final EmployeeServiceImpl employeeServiceImpl;
	
	public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
		this.employeeServiceImpl = employeeServiceImpl;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public EmployeeListDTO getallEmployees(){
		return new EmployeeListDTO(employeeServiceImpl.listAll());
	}
	
	@GetMapping("/{identification}")
	@ResponseStatus(HttpStatus.OK)
	public EmployeeDTO getEmployeByIdentification(@PathVariable String identification){
		return employeeServiceImpl.employeByIdentification(identification);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EmployeeDTO saveEmployee(@Valid @RequestBody EmployeeDTO employeeDto){
		return employeeServiceImpl.create(employeeDto);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void inactiveUser(@PathVariable String id){
		
		employeeServiceImpl.updateEstatusEmployeeInactive(id);
	}
	
}
