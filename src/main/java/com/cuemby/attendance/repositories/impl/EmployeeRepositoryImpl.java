package com.cuemby.attendance.repositories.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.cuemby.attendance.domain.Employee;
import com.cuemby.attendance.domain.factories.UUIDCreator;
import com.cuemby.attendance.enums.StatusEmployee;
import com.cuemby.attendance.repositories.IEmployeeRepository;


@Component
public class EmployeeRepositoryImpl implements IEmployeeRepository{
	
	private Map<String,Employee> employees;
	
	
	
	public EmployeeRepositoryImpl() {
		this.employees = new ConcurrentHashMap<>();
	}

	@Override
	public Map<String,Employee> findAll() {
		return this.employees;
	}
	
	@Override
	public List<Employee> findAllByStatus(String status) {
		return this.employees.values()
							 .stream()
							 .filter(e -> e.getStatus().equalsIgnoreCase(StatusEmployee.ACTIVE.toString()))
							 .collect(Collectors.toList());
	}

	@Override
	public Map<String, Employee> saveAll(Iterable<Employee> entities) {
		
		entities.forEach(employee ->{
			save(employee);			
		});
		
		return this.employees;
	}

	@SuppressWarnings("static-access")
	@Override
	public Employee save(Employee entity) {
		if(entity.getId() == null && !employeeExist(entity.getIdentification()) ) {
			entity.setId(UUIDCreator.getInstance().randomUUID());
			entity.setStatus(StatusEmployee.ACTIVE.toString());
			entity.setDateAdmission(new Date());
			this.employees.put(entity.getId(), entity);
		}				
		return entity;
	}


	@Override
	public Optional<Employee> getOne(String id) {
		return this.employees.values().stream().
							 filter(e-> e.getId().equalsIgnoreCase(id))
							 .findFirst();
							 
	}

	@Override
	public Employee update(Employee entity) {
		this.employees.put(entity.getId(), entity);
		return entity;
	}

	@Override
	public boolean employeeExist(String identification) {
		Optional<Employee> employeeOptional = this.employees.values().stream().
				 filter(e-> e.getIdentification().equalsIgnoreCase(identification))
				 .findFirst();
		
		return employeeOptional.isPresent();
	}

	@Override
	public Optional<Employee> findAllByStatusId(String status, String employeeId) {
		return this.employees.values()
				 .stream()
				 .filter(e -> (e.getStatus().equalsIgnoreCase(status) && e.getId().equalsIgnoreCase(employeeId)))
				 .findFirst();
	}
	
	@Override
	public Optional<Employee> findByIdentification(String status, String identification) {
		return this.employees.values()
				 .stream()
				 .filter(e -> (e.getStatus().equalsIgnoreCase(status) && e.getIdentification().equalsIgnoreCase(identification)))
				 .findFirst();
	}

	


	
}
