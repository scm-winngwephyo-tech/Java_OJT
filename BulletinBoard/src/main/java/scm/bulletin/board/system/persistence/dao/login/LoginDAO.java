package scm.bulletin.board.system.persistence.dao.login;

import scm.bulletin.board.system.persistence.entity.user.User;

public interface LoginDAO {

	/**
	 * <h2>getUserByEmail</h2>
	 * <p>
	 * Get User By Email
	 * </p>
	 *
	 * @param email
	 * @return
	 * @return User
	 */
	public User dbGetUserByEmail(String email);

}
