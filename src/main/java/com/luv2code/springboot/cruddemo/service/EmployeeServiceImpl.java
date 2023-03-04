package com.luv2code.springboot.cruddemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDAO theEmployeeDAO;
	@Autowired
	public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO) {
		this.theEmployeeDAO=theEmployeeDAO;
	}
	@Override
	@Transactional
	public List<Employee> findAll() {
		return theEmployeeDAO.findAll();
	}

	@Override
	@Transactional
	public Employee findById(int theId) {
		return theEmployeeDAO.findById(theId);
	}

	@Override
	@Transactional
	public void save(Employee theEmployee) {
		theEmployeeDAO.save(theEmployee);
	}

	@Override
	@Transactional
	public void delete(int theId) {
		theEmployeeDAO.delete(theId);
		
	}

}
