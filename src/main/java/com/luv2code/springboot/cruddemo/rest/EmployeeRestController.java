package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	private EmployeeService employeeService;
	//inject Dao
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService=theEmployeeService;
	}
	//add get mapping for /employee/{emplyeeId}
	@GetMapping("/employees/{employeeId}")
	public Employee findById(@PathVariable int employeeId){
		
		
		Employee theEmployee= employeeService.findById(employeeId);
		if (theEmployee==null) {
			throw new RuntimeException("Employee Id not found : "+ employeeId);
		}
		return theEmployee;
		
	}
	@GetMapping("/employees/")
	public List<Employee> getEmployees(){
		List<Employee> theEmployee= employeeService.findAll();
		if (theEmployee.isEmpty()==true) {
			throw new RuntimeException("Employees not found");
		}
		return theEmployee;
		
	}
	//add mapping for Post
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		theEmployee.setId(0);
		employeeService.save(theEmployee);
		return theEmployee;
	}
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		employeeService.save(theEmployee);
		return theEmployee;
	}
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		
		Employee theEmployee=employeeService.findById(employeeId);
		if(theEmployee==null) {
			throw new RuntimeException("Employee not found with Id : "+employeeId);
		}
		employeeService.delete(employeeId);
		return "Employee Deleted with Id : "+employeeId;
	}
	
	

}
