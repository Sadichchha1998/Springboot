package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Address;
import com.masai.model.Employee;
import com.masai.model.EmployeeDTO;
import com.masai.repository.EmployeeRepo;
import com.masai.services.EmployeeService;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

	@Autowired
	public EmployeeService emplservice;
	
	@PostMapping("/registerEmployee")
	public ResponseEntity<Employee> AddNewEmployee(@RequestBody Employee employee){
		//return emplservice.registerEmployee(employee);
		return new ResponseEntity<Employee>(emplservice.registerEmployee(employee),HttpStatus.CREATED);
		
	}
	@GetMapping("/getEmployee/{employeeId}")
	public ResponseEntity<Employee> getEmployeeByUsingId(@PathVariable ("employeeId") Integer employeeId){
		return new ResponseEntity<Employee>(emplservice.getEmployeeById(employeeId),HttpStatus.ACCEPTED);
		
	}
	// not working 
//	@GetMapping("/login/{email}/{password}")
//	public ResponseEntity<Employee> getEmployeeByUsingId(@RequestBody Employee employee , @RequestParam ("email") String email , @RequestParam("password") String password){
//		return new ResponseEntity<Employee>(emplservice.loginEmployee(email, password),HttpStatus.ACCEPTED);
//		
//	}
	@PutMapping("/updatePassword")
	public ResponseEntity<Employee> changePasswordOrUpdate (@RequestParam("email") String email ,@RequestParam("oldPassword") String oldPassword , @RequestParam ("newPassword") String newPassword){
		
		return new ResponseEntity<Employee>(emplservice.updateEmployeePassword(email, oldPassword, newPassword),HttpStatus.ACCEPTED);
		
	}
	@GetMapping("/AllEmployeee")
	public ResponseEntity<List<Employee>> getAllEmployeeDetails(@RequestBody Employee employee){
		return new ResponseEntity<List<Employee>>(emplservice.getAllEmployeeDetails(),HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/deleteEmployee/{employeeId}")
	public ResponseEntity<Employee>deleteEmployeeById(@RequestBody Employee employee, @PathVariable("employeeId") Integer employeeId){
		return new ResponseEntity<Employee>(emplservice.deleteEmployeeById(employeeId),HttpStatus.ACCEPTED);
	}
	@GetMapping("/AllEmployeeByaddress")
	public ResponseEntity<List<Employee>> getListOfEmployeeByAddress(@RequestBody Address address){
		return new ResponseEntity<List<Employee>>(emplservice.getEmployeeDetailsByAddress(address), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/updateEmployee")
	public ResponseEntity<Employee> updateEmployee (@RequestBody Employee employee){
		return new ResponseEntity<Employee>(emplservice.updateEmployee(employee),HttpStatus.OK);
	}
	@PutMapping("/updateAddress/{employeeId}")
	public ResponseEntity<Employee> updateEmployeeAddress (@PathVariable ("employeeId") Integer employeeId, @RequestBody Address address){
		return new ResponseEntity<Employee>(emplservice.updateEmployeeAddress(employeeId, address),HttpStatus.OK);
	}
	@GetMapping("/getEmployeeBetweenage{start_age}{end_age}")
	public ResponseEntity<List<Employee>> getEmployeeBetweenAge(@RequestParam("start_age") Integer start_age  ,@RequestParam("end_age") Integer end_age){
		return new ResponseEntity<List<Employee>>(emplservice.getEmployeesBetweenAge(start_age, end_age), HttpStatus.ACCEPTED);
	}
	@GetMapping("/GetNameAgeAddressOfAllEmployees")
	public ResponseEntity<List<EmployeeDTO>> getNameAgeAndAddress() {
	    List<EmployeeDTO> employeeDTOs = emplservice.getNameAddressAgeOfAllEmployees();
	    return new ResponseEntity<>(employeeDTOs, HttpStatus.OK);
	}

	
}
