package com.cuemby.attendance.repositories;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.cuemby.attendance.domain.Attendance;
import com.cuemby.attendance.domain.Employee;

public interface IEmployeeRepository extends ICustomCrudRepository<Employee, String>{
	
	public boolean employeeExist(String identification);
	public List<Employee> findAllByStatus(String status);
	public Optional<Employee> findAllByStatusId(String status, String employeeId);
	Optional<Employee> findByIdentification(String status, String identification);

}
