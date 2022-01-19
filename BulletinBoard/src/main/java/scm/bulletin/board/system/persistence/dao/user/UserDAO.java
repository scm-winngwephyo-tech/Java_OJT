package scm.bulletin.board.system.persistence.dao.user;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import scm.bulletin.board.system.bl.dto.search.SearchDTO;
import scm.bulletin.board.system.persistence.entity.user.User;
import scm.bulletin.board.system.web.form.user.UserForm;

/**
 * <h2>UserDAO Class</h2>
 * <p>
 * Process for Displaying UserDAO
 * </p>
 * 
 * @author WinNgwePhyo
 *
 */
public interface UserDAO {
    /**
     * <h2>dbGetUserList</h2>
     * <p>
     * Get User From Database
     * </p>
     *
     * @return
     * @return List<User>
     */
    public List<User> dbGetUserList();

    /**
     * <h2>dbGetUserListWithLimit</h2>
     * <p>
     * 
     * </p>
     *
     * @param currentPage
     * @param noOfUser
     * @param userForm
     * @return
     * @return List<User>
     */
    public List<User> dbGetUserListWithLimit(int currentPage, int noOfUser, SearchDTO searchDTO) throws ParseException;

    /**
     * <h2>dbGetUserbyID</h2>
     * <p>
     * Get User By ID
     * </p>
     *
     * @param userId
     * @return
     * @return User
     */
    public User dbGetUserbyID(Integer userId);

    /**
     * <h2>dbGetUserByEmail</h2>
     * <p>
     * Get Email From the Database
     * </p>
     *
     * @param email
     * @return
     * @return User
     */
    public User dbGetUserByEmail(String email);

    /**
     * <h2>dbInsertUser</h2>
     * <p>
     * Insert User From The Database
     * </p>
     *
     * @param user
     * @param currentUserId
     * @param currentDate
     * @return void
     */
    public void dbInsertUser(User user, int currentUserId, Date currentDate);

    /**
     * <h2>dbUpdatedUserExistList</h2>
     * <p>
     * Update User Email Exit or Not Exit
     * </p>
     *
     * @param email
     * @return
     * @return List<User>
     */
    public List<User> dbUpdatedUserExistList(String email);

    /**
     * <h2>dbUpdateUser</h2>
     * <p>
     * Update User To the Database
     * </p>
     *
     * @param updatedUser
     * @return void
     */
    public void dbUpdateUser(User updatedUser);

}
