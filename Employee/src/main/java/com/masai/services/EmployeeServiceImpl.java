package com.masai.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.EmployeeException;
import com.masai.model.Address;
import com.masai.model.Employee;
import com.masai.model.EmployeeDTO;
import com.masai.repository.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	public EmployeeRepo emplRepo;

	@Override
	public Employee registerEmployee(Employee employee) throws EmployeeException {
		if (emplRepo.findByEmail(employee.getEmail()) != null) {
			throw new EmployeeException("Email is already registered...");
		}
		return emplRepo.save(employee);
	}

	@Override
	public Employee getEmployeeById(Integer employeeId) throws EmployeeException {
		return emplRepo.findById(employeeId)
				.orElseThrow(() -> new EmployeeException("No employee found with this id " + employeeId));
	}

//not working
	@Override
	public Employee loginEmployee(String email, String password) throws EmployeeException {
		Employee employee = emplRepo.findByEmailAndPassword(email, password);
		if (employee == null) {
			throw new EmployeeException("Employee details not match..");
		}
		return employee;
	}

	@Override
	public Employee updateEmployeePassword(String email, String oldPassword, String newPassword)
			throws EmployeeException {

		Employee employee = emplRepo.findByEmail(email);
		if (email == null) {
			throw new EmployeeException("Employee Not found....");
		}
		if (!employee.getPassword().equals(oldPassword)) {
			throw new EmployeeException("Old password is incorrect....");
		}
		employee.setPassword(newPassword);
		return emplRepo.save(employee);
	}

	@Override
	public List<Employee> getAllEmployeeDetails() throws EmployeeException {
		List<Employee> listEmployee = emplRepo.findAll();
		if (listEmployee.isEmpty()) {
			throw new EmployeeException("Employees not found in this list....");
		}
		return listEmployee;

	}

	@Override
	public Employee deleteEmployeeById(Integer employeeId) throws EmployeeException {
		Optional<Employee> optEmployee = emplRepo.findById(employeeId);
		if (optEmployee.isEmpty()) {
			throw new EmployeeException("Employee with this is not found...");
		}
		Employee emplDelete = optEmployee.get();// get employee before delete
		emplRepo.deleteById(employeeId);
		return emplDelete;
	}

	@Override
	public List<Employee> getEmployeeDetailsByAddress(Address address) throws EmployeeException {
		List<Employee> listOfEmplByAdd = emplRepo.findByAddress(address);
		if (listOfEmplByAdd.isEmpty()) {
			throw new EmployeeException("Employee for this address not found...");
		}
		return listOfEmplByAdd;

	}

	@Override
	public Employee updateEmployee(Employee employee) throws EmployeeException {
		// Validate input
		if (employee == null || employee.getEmployeeId() == null) {
			throw new EmployeeException("Invalid employee data provided.");
		}

		// Fetch the existing employee record from the database or data source
	Optional<Employee> extEmpl= emplRepo.findById(employee.getEmployeeId());
	if(!extEmpl.isPresent()) {
		throw new EmployeeException("Employee not found with this id");
	}

		// Update the necessary fields (you may update all fields or selectively)
		
        Employee updateEmployee = extEmpl.get();
    	
        updateEmployee.setEmployeeName(employee.getEmployeeName());
        updateEmployee.setMobile(employee.getMobile());
		// Save the updated employee record back to the data source
	
        return emplRepo.save(updateEmployee);
	}
	

	@Override
	public Employee updateEmployeeAddress(Integer employeeId, Address address) throws EmployeeException {
	 Employee empl =emplRepo.findById(employeeId).orElseThrow(()-> new EmployeeException("No employee found with this id"+employeeId));
	 // Update the address
     empl.setAddress(address);
     
     // Save the updated employee object
     return emplRepo.save(empl);
	      
	      
	}
	

	@Override
	public List<Employee> getEmployeesBetweenAge(Integer start_age, Integer end_age) throws EmployeeException {
	List<Employee> getList=	emplRepo.findByAgeBetween(start_age, end_age);
	if(getList.isEmpty()) {
		throw new EmployeeException("Employee not found in this age ...");
	}
	return getList;
	}

	@Override
	public List<EmployeeDTO> getNameAddressAgeOfAllEmployees() throws EmployeeException {
	List<EmployeeDTO> empldto =	emplRepo.findEmployeeNameAddressAndAge();
	if(empldto.isEmpty()) {
		throw new EmployeeException("No employee found");
	}
	return empldto;
		
		
	}

}
