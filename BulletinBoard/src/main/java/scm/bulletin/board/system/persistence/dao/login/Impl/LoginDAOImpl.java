package scm.bulletin.board.system.persistence.dao.login.Impl;

import org.hibernate.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import scm.bulletin.board.system.persistence.dao.login.LoginDAO;
import scm.bulletin.board.system.persistence.entity.user.User;

/**
 * <h2>LoginDAOImpl Class</h2>
 * <p>
 * Process for Displaying LoginDAOImpl
 * </p>
 * 
 * @author WinNgwePhyo
 *
 */
@Repository
public class LoginDAOImpl implements LoginDAO {

	/**
	 * <h2> SELECT_USER_BY_EMAIL_HQL</h2>
	 * <p>
	 * SELECT_USER_BY_EMAIL_HQL
	 * </p>
	 */
	public static String SELECT_USER_BY_EMAIL_HQL = "SELECT u FROM User u WHERE u.email = :email";

	/**
	 * <h2>sessionFactory</h2>
	 * <p>
	 * sessionFactory
	 * </p>
	 */
	@Autowired
	private SessionFactory sessionFactory;


	/**
	 * <h2>getUserByEmail</h2>
	 * <p>
	 * Get Email Form Login User
	 * </p>
	 * 
	 * @param email
	 * @return
	 */
	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Override
	public User dbGetUserByEmail(String email) {
		Query queryUser = this.sessionFactory.getCurrentSession().createQuery(SELECT_USER_BY_EMAIL_HQL);
		queryUser.setParameter("email", email);
		User user = (User) queryUser.uniqueResult();
		return user;
	}

}
