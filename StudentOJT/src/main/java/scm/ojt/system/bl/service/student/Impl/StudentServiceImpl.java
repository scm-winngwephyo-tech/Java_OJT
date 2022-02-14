package scm.ojt.system.bl.service.student.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scm.ojt.system.bl.service.student.StudentService;
import scm.ojt.system.persistence.dao.student.StudentDao;
import scm.ojt.system.persistence.entity.Student;

/**
 * <h2>StudentServiceImpl Class</h2>
 * <p>
 * Process for Displaying StudentServiceImpl
 * </p>
 * 
 * @author WinNgwePhyo
 *
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    /**
     * <h2>studentDao</h2>
     * <p>
     * studentDao
     * </p>
     */
    @Autowired
    private StudentDao studentDao;

    /**
     * <h2>getAllStudents</h2>
     * <p>
     * Get All Student from the database
     * </p>
     * 
     * @return List<Student>
     */
    @Override
    public List<Student> getAllStudents() {
        return this.studentDao.getAllStudents();
    }
    /**
	 * <h2>addStudent</h2>
     * <p>
     * add Student to the database
     * </p>
     *
	 * @param student
	 * return void
	 */
    @Transactional
	@Override
	public void addStudent(Student student) {
		this.studentDao.addStudent(student);
		
	}
	/**
	 * <h2>updateStudent</h2>
     * <p>
     * update Student to the database
     * </p>
     *
	 * @param student
	 * return void
	 */
	@Override
	public Student updateStudent(Student student) {
		return this.studentDao.updateStudent(student);
		
	}

}
