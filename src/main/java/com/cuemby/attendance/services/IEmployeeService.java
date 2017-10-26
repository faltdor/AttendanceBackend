package com.cuemby.attendance.services;

import com.cuemby.attendance.model.v1.EmployeeDTO;
import com.cuemby.attendance.services.config.ICRUDService;

public interface IEmployeeService extends ICRUDService<EmployeeDTO> {

	void updateEstatusEmployeeInactive(String id);

	EmployeeDTO employeByIdentification(String identification);

}
