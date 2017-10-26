package com.cuemby.attendance.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cuemby.attendance.domain.Employee;
import com.cuemby.attendance.enums.StatusEmployee;
import com.cuemby.attendance.model.v1.EmployeeDTO;
import com.cuemby.attendance.model.v1.mappers.IEmployeeMapper;
import com.cuemby.attendance.repositories.IEmployeeRepository;
import com.cuemby.attendance.services.IEmployeeService;
import com.cuemby.attendance.services.exception.ResourceAlreadyExistsException;
import com.cuemby.attendance.services.exception.ResourceNotFoundException;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	
	private final IEmployeeRepository employeeRepository;
	private final IEmployeeMapper employeeMapper;
	
	public EmployeeServiceImpl(IEmployeeRepository employeeRepository,IEmployeeMapper employeeMapper) {
		this.employeeRepository = employeeRepository;
		this.employeeMapper = employeeMapper;
	}

	@Override
	public List<EmployeeDTO> listAll() {
		return employeeRepository.findAllByStatus(StatusEmployee.ACTIVE.toString())
								 .stream()
								  .map(employeeMapper::employeeToEmployeeDTO)
								  .collect(Collectors.toList());
	}
	



	@Override
	public EmployeeDTO create(EmployeeDTO employeeDto) {
		
		if(employeeRepository.employeeExist(employeeDto.getIdentification())) {
			
			throw new ResourceAlreadyExistsException("The user "+employeeDto.getIdentification()+" is already registered.");
		}
		
		Employee employee = employeeRepository.save(employeeMapper.employeeDTOToEmployee(employeeDto));
		
		return employeeMapper.employeeToEmployeeDTO(employee);	
	
	}

	
	@Override
	public void updateEstatusEmployeeInactive(String id) {
		employeeRepository.getOne(id).map(employee -> {
			employee.setStatus(StatusEmployee.INACTIVE.toString());
			return employeeMapper.employeeToEmployeeDTO(employeeRepository.update(employee));
		}).orElseThrow(ResourceNotFoundException::new);

				
	}
	
	@Override
	public EmployeeDTO employeByIdentification(String identification) {
		
		Optional<Employee> optional = employeeRepository.findByIdentification(StatusEmployee.ACTIVE.toString(), identification);
		
		if(!optional.isPresent()) {
			throw new ResourceNotFoundException();
		}
		
		return employeeMapper.employeeToEmployeeDTO(optional.get());
	}

}
