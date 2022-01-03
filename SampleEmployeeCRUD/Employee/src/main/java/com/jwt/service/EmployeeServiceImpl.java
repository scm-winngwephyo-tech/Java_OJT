package com.jwt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwt.dao.EmployeeDAO;
import com.jwt.model.Employee;

/**
 * <h2> EmployeeServiceImpl Class</h2>
 * <p>
 * Process for Displaying EmployeeServiceImpl
 * </p>
 * 
 * @author Lwin Mar Win-PC
 *
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    /**
     * <h2> employeeDAO</h2>
     * <p>
     * employeeDAO
     * </p>
     */
    @Autowired
    private EmployeeDAO employeeDAO;

    /**
     * <h2> addEmployee </h2>
     * <p>
     * addEmployee
     * </p>
     * 
     * @param employee
     */
    @Override
    @Transactional
    public void addEmployee(Employee employee) {
        employeeDAO.addEmployee(employee);
    }

    /**
     * <h2> getAllEmployees </h2>
     * <p>
     * getAllEmployees
     * </p>
     * 
     * @return
     */
    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
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
    @Transactional
    public void deleteEmployee(Integer employeeId) {
        employeeDAO.deleteEmployee(employeeId);
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
        return employeeDAO.getEmployee(empid);
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
    public Employee updateEmployee(Employee employee) {
        // TODO Auto-generated method stub
        return employeeDAO.updateEmployee(employee);
    }

    /**
     * <h2> setEmployeeDAO</h2>
     * <p>
     * setEmployeeDAO
     * </p>
     *
     * @param employeeDAO
     * @return void
     */
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
}
