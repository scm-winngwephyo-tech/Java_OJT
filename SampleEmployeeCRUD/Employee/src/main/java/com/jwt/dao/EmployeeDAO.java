package com.jwt.dao;

import java.util.List;
import com.jwt.model.Employee;

/**
 * <h2> EmployeeDAO Class</h2>
 * <p>
 * Process for Displaying EmployeeDAO
 * </p>
 * 
 * @author Lwin Mar Win-PC
 *
 */
public interface EmployeeDAO {

    /**
     * <h2> addEmployee</h2>
     * <p>
     * addEmployee
     * </p>
     *
     * @param employee
     * @return void
     */
    public void addEmployee(Employee employee);

    /**
     * <h2> getAllEmployees</h2>
     * <p>
     * addEmployee
     * </p>
     *
     * @return
     * @return List<Employee>
     */
    public List<Employee> getAllEmployees();

    /**
     * <h2> deleteEmployee</h2>
     * <p>
     * deleteEmployee
     * </p>
     *
     * @param employeeId
     * @return void
     */
    public void deleteEmployee(Integer employeeId);

    /**
     * <h2> updateEmployee</h2>
     * <p>
     * updateEmployee
     * </p>
     *
     * @param employee
     * @return
     * @return Employee
     */
    public Employee updateEmployee(Employee employee);

    /**
     * <h2> getEmployee</h2>
     * <p>
     * getEmployee
     * </p>
     *
     * @param employeeid
     * @return
     * @return Employee
     */
    public Employee getEmployee(int employeeid);
}
