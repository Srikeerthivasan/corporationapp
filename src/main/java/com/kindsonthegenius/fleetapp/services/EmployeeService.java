package com.kindsonthegenius.fleetapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kindsonthegenius.fleetapp.models.Employee;
import com.kindsonthegenius.fleetapp.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	//Returns the list of employees from the database
	public List<Employee> getEmployees(){
		return employeeRepository.findAll();
	}
	
	//Save new employee to the database
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}
	
	//get by id
	public Optional<Employee> findById(int id) {
		Optional<Employee> employee = employeeRepository.findById(id);
	    if (employee.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Employee with id %d not found", id));
	    }
	    
	    return employee;
	}

	//Delete a employee by id
	public void delete(int id) {
		employeeRepository.deleteById(id);
	}
}
