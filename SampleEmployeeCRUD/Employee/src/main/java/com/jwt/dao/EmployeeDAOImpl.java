package com.jwt.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jwt.model.Employee;

/**
 * <h2> EmployeeDAOImpl Class</h2>
 * <p>
 * Process for Displaying EmployeeDAOImpl
 * </p>
 * 
 * @author Lwin Mar Win-PC
 *
 */
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    /**
     * <h2> sessionFactory</h2>
     * <p>
     * sessionFactory
     * </p>
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * <h2> addEmployee </h2>
     * <p>
     * addEmployee
     * </p>
     * 
     * @param employee
     */
    public void addEmployee(Employee employee) {
        sessionFactory.getCurrentSession().saveOrUpdate(employee);

    }

    /**
     * <h2> getAllEmployees </h2>
     * <p>
     * getAllEmployees
     * </p>
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Employee> getAllEmployees() {
        return sessionFactory.getCurrentSession().createQuery("from Employee").list();
    }

    /**
     * <h2> deleteEmployee </h2>
     * <p>
     * deleteEmployee
     * </p>
     * 
     * @param employeeId
     */
    @Override
    public void deleteEmployee(Integer employeeId) {
        Employee employee = (Employee) sessionFactory.getCurrentSession().load(Employee.class, employeeId);
        if (null != employee) {
            this.sessionFactory.getCurrentSession().delete(employee);
        }

    }

    /**
     * <h2> getEmployee </h2>
     * <p>
     * getEmployee
     * </p>
     * 
     * @param empid
     * @return
     */
    public Employee getEmployee(int empid) {
        return (Employee) sessionFactory.getCurrentSession().get(Employee.class, empid);
    }

    /**
     * <h2> updateEmployee </h2>
     * <p>
     * updateEmployee
     * </p>
     * 
     * @param employee
     * @return
     */
    @Override
    public Employee updateEmployee(Employee employee) {
        sessionFactory.getCurrentSession().update(employee);
        return employee;
    }

}