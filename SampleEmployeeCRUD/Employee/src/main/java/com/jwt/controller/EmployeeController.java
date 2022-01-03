package com.jwt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jwt.model.Employee;
import com.jwt.service.EmployeeService;

/**
 * <h2> EmployeeController Class</h2>
 * <p>
 * Process for Displaying EmployeeController
 * </p>
 * 
 * @author Lwin Mar Win-PC
 *
 */
@Controller
public class EmployeeController {

    private static final Logger logger = Logger.getLogger(EmployeeController.class);

    /**
     * <h2> Constructor for EmployeeController </h2>
     * <p>
     * Constructor for EmployeeController
     * </p>
     */
    public EmployeeController() {
        System.out.println("EmployeeController()");
    }

    /**
     * <h2> employeeService</h2>
     * <p>
     * employeeService
     * </p>
     */
    @Autowired
    private EmployeeService employeeService;

    /**
     * <h2> listEmployee</h2>
     * <p>
     * listEmployee
     * </p>
     *
     * @param model
     * @return
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/")
    public ModelAndView listEmployee(ModelAndView model) throws IOException {
        List<Employee> listEmployee = employeeService.getAllEmployees();
        model.addObject("listEmployee", listEmployee);
        model.setViewName("home");
        return model;
    }

    /**
     * <h2> newContact</h2>
     * <p>
     * newContact
     * </p>
     *
     * @param model
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/newEmployee", method = RequestMethod.GET)
    public ModelAndView newContact(ModelAndView model) {
        Employee employee = new Employee();
        model.addObject("employee", employee);
        model.setViewName("EmployeeForm");
        return model;
    }

    /**
     * <h2> saveEmployee</h2>
     * <p>
     * saveEmployee
     * </p>
     *
     * @param employee
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
    public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
        if (employee.getId() == 0) { // if employee id is 0 then creating the
            // employee other updating the employee
            employeeService.addEmployee(employee);
        } else {
            employeeService.updateEmployee(employee);
        }
        return new ModelAndView("redirect:/");
    }

    /**
     * <h2> deleteEmployee</h2>
     * <p>
     * deleteEmployee
     * </p>
     *
     * @param request
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
    public ModelAndView deleteEmployee(HttpServletRequest request) {
        int employeeId = Integer.parseInt(request.getParameter("id"));
        employeeService.deleteEmployee(employeeId);
        return new ModelAndView("redirect:/");
    }

    /**
     * <h2> editContact</h2>
     * <p>
     * editContact
     * </p>
     *
     * @param request
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/editEmployee", method = RequestMethod.GET)
    public ModelAndView editContact(HttpServletRequest request) {
        int employeeId = Integer.parseInt(request.getParameter("id"));
        Employee employee = employeeService.getEmployee(employeeId);
        ModelAndView model = new ModelAndView("EmployeeForm");
        model.addObject("employee", employee);
        return model;
    }
}