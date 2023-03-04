package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.entity.Employee;

import jakarta.persistence.EntityManager;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {
	//Define field for Entity Manager
	private EntityManager entityManager;
	//set up constructor Injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager=theEntityManager;
	}
	//@Override
	public List<Employee> findAll() {
		//get the current hibernate session
		Session currentSession= entityManager.unwrap(Session.class);
		//create a query 
		Query<Employee> theQuery=currentSession.createQuery("from Employee",Employee.class);
		//execute a query
		List<Employee> employees=theQuery.getResultList();
		//return the result
		
		return employees;
	}
	@Override
	public Employee findById(int theId) {
		Session currentSession=entityManager.unwrap(Session.class);
		Employee theEmployee =currentSession.get(Employee.class,theId);
		return theEmployee;
	}
	@Override
	public void save(Employee theEmployee) {
		Session currentSession=entityManager.unwrap(Session.class);
		currentSession.merge(theEmployee);
		
		
	}
	@Override
	public void delete(int theId) {
		Session currentSession=entityManager.unwrap(Session.class);
		Employee theEmployee=currentSession.get(Employee.class,theId);
		currentSession.remove(theEmployee);
		
	}

}
