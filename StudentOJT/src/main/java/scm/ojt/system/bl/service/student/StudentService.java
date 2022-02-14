package scm.ojt.system.bl.service.student;

import java.util.List;

import scm.ojt.system.persistence.entity.Student;

/**
 * @author WinNgwePhyo
 *
 */
public interface StudentService {

    /**
     * <h2>getAllStudents</h2>
     * <p>
     * getAllStudents
     * </p>
     *
     * @return
     * @return List<Student>
     */
    public List<Student> getAllStudents();

	/**
	 * <h2>addStudent</h2>
     * <p>
     * add Student to the database
     * </p>
     *
	 * @param student
	 * return void
	 */
	public void addStudent(Student student);
	/**
	 * <h2>updateStudent</h2>
     * <p>
     * update Student to the database
     * </p>
     *
	 * @param student
	 * return void
	 */
	public Student updateStudent(Student student);

}
