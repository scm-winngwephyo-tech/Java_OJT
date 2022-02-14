package scm.ojt.system.web.controller.student;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import scm.ojt.system.bl.service.student.StudentService;
import scm.ojt.system.persistence.entity.Student;

/**
 * <h2>StudentController Class</h2>
 * <p>
 * Process for Displaying StudentController
 * </p>
 * 
 * @author WinNgwePhyo
 *
 */
@Controller
public class StudentController {

    /**
     * <h2>studentService</h2>
     * <p>
     * studentService
     * </p>
     */
    @Autowired
    private StudentService studentService;

    /**
     * <h2>Show Initial View</h2>
     * <p>
     * Show Initial View
     * </p>
     * 
     * @param model
     * @return String
     */
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String initView() {
        return "redirect:/studentList";
    }

    /**
     * <h2>listStudent</h2>
     * <p>
     * 
     * </p>
     *
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/studentList")
    public ModelAndView listStudent() throws IOException {
        ModelAndView studentListView = new ModelAndView("listStudent");
        studentListView.addObject("listStudent", this.studentService.getAllStudents());
        return studentListView;
    }

	/**
	 * <h2>listStudent</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @throws IOException
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/createStudent")
	public ModelAndView createStudent() throws IOException {
		ModelAndView studentListView = new ModelAndView("listStudent");
		studentListView.addObject("listStudent", this.studentService.getAllStudents());
		return studentListView;
	}

    /**
     * @param model
     * @return ModelAndView
     */
    @RequestMapping(value = "/newStudent", method = RequestMethod.GET)
    public ModelAndView newStudent() {
    	ModelAndView createStudentView = new ModelAndView("createStudent");
        Student student = new Student();
        createStudentView.addObject("student", student);
        return createStudentView;
    }

    /**
     * <h2> saveStudent</h2>
     * <p>
     * saveStudent
     * </p>
     *
     * @param student
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
    public ModelAndView saveStudent(@ModelAttribute Student student) {
        if (student.getId() == 0) { // if employee id is 0 then creating the
            // employee other updating the employee
            studentService.addStudent(student);
        } else {
        	studentService.updateStudent(student);
        }
        return new ModelAndView("redirect:/");
    }

}
