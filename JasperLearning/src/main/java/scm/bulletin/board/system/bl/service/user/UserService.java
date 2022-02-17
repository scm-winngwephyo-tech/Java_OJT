package scm.bulletin.board.system.bl.service.user;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import scm.bulletin.board.system.bl.dto.login.LoginDTO;
import scm.bulletin.board.system.bl.dto.search.SearchDTO;
import scm.bulletin.board.system.bl.dto.user.UserDTO;
import scm.bulletin.board.system.web.form.login.LoginForm;
import scm.bulletin.board.system.web.form.user.UserForm;

/**
 * <h2>UserService Class</h2>
 * <p>
 * Process for Displaying UserService
 * </p>
 * 
 * @author WinNgwePhyo
 *
 */
public interface UserService {
    /**
     * <h2>getLoginResult</h2>
     * <p>
     * Get Login Result
     * </p>
     * 
     * @param loginForm
     * @return
     * @return LoginDTO
     */
    public LoginDTO doGetLoginResult(LoginForm loginForm);

    /**
     * <h2>doInsertUser</h2>
     * <p>
     * Insert UserFrom The database
     * </p>
     *
     * @param userForm
     * @param loginUserId
     * @param userProfilePath
     * @return void
     * @throws IOException
     * @throws FileNotFoundException
     */
    public void doInsertUser(UserForm userForm, int currentUserId, String userProfilePath)
            throws ParseException, IOException;

    /**
     * <h2>dogetUserList</h2>
     * <p>
     * Get User From Database
     * </p>
     *
     * @return
     * @return List<UserDTO>
     */
    public List<UserDTO> dogetUserList();

    /**
     * <h2>doSearchUserByLimit</h2>
     * <p>
     * Search User By Email or Name
     * </p>
     *
     * @param currentPage
     * @param noOfUser
     * @param searchDTO
     * @return
     * @return List<UserDTO>
     */
    public List<UserDTO> doSearchUserByLimit(int currentPage, int noOfUser, @Valid SearchDTO searchDTO)
            throws ParseException;

    /**
     * <h2>doGetUserbyId</h2>
     * <p>
     * Get User By Id
     * </p>
     *
     * @param userId
     * @return
     * @return UserForm
     */
    public UserForm doGetUserbyId(Integer userId) throws IOException;

    /**
     * <h2>doIsUserExist</h2>
     * <p>
     * User Exist With Email
     * </p>
     *
     * @param email
     * @return
     * @return boolean
     */
    public boolean doIsUserExist(String email);

    /**
     * <h2>doEmailExist</h2>
     * <p>
     * Test Email Exist
     * </p>
     *
     * @param email
     * @param id
     * @return
     * @return Boolean
     */
    public Boolean doEmailExist(String email, Integer id);

    /**
     * <h2>doUpdateUser</h2>
     * <p>
     * Update User into Database
     * </p>
     *
     * @param userForm
     * @param loginUserId
     * @param userProfilePath
     * @return void
     * @throws ParseException
     * @throws IOException
     */
    public void doUpdateUser(@Valid UserForm userForm, int loginUserId, String userProfilePath)
            throws IOException, ParseException;

    /**
     * <h2>doDeleteUser</h2>
     * <p>
     * Delete User BY ID
     * </p>
     *
     * @param userId
     * @return void
     */
    public void doDeleteUser(Integer userId);

    /**
     * <h2>doUpdatePassword</h2>
     * <p>
     * 
     * </p>
     *
     * @param newPassword
     * @param userId
     * @return void
     */
    public void doUpdatePassword(String newPassword, Integer userId);

}
