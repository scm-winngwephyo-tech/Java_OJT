package scm.ojt.system.persistence.dao.student.Impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import scm.ojt.system.persistence.dao.student.StudentDao;
import scm.ojt.system.persistence.entity.Student;

/**
 * <h2>StudentDaoImpl Class</h2>
 * <p>
 * Process for Displaying StudentDaoImpl
 * </p>
 * 
 * @author WinNgwePhyo
 *
 */
@Repository
public class StudentDaoImpl implements StudentDao {

    /**
     * <h2>sessionFactory</h2>
     * <p>
     * sessionFactory
     * </p>
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * <h2>getAllStudents</h2>
     * <p>
     * Get All Student From Database
     * </p>
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Student> getAllStudents() {
        return sessionFactory.getCurrentSession().createQuery("from Student").list();
    }

	@Override
	public void addStudent(Student student) {
		 sessionFactory.getCurrentSession().saveOrUpdate(student);
		
	}

	@Override
	public Student updateStudent(Student student) {
		sessionFactory.getCurrentSession().update(student);
        return student;
		
	}

}
