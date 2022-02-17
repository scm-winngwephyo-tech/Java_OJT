package scm.bulletin.board.system.bl.service.user.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import scm.bulletin.board.system.bl.dto.login.LoginDTO;
import scm.bulletin.board.system.bl.dto.search.SearchDTO;
import scm.bulletin.board.system.bl.dto.user.UserDTO;
import scm.bulletin.board.system.bl.service.user.UserService;
import scm.bulletin.board.system.persistence.dao.login.LoginDAO;
import scm.bulletin.board.system.persistence.dao.user.UserDAO;
import scm.bulletin.board.system.persistence.entity.user.User;
import scm.bulletin.board.system.web.form.login.LoginForm;
import scm.bulletin.board.system.web.form.user.UserForm;

/**
 * <h2>UserServiceImpl Class</h2>
 * <p>
 * Process for Displaying UserServiceImpl
 * </p>
 * 
 * @author WinNgwePhyo
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service
@Primary
public class UserServiceImpl implements UserService {
    /**
     * <h2>DATE</h2>
     * <p>
     * DATE
     * </p>
     */
    java.util.Date DATE = new java.util.Date();
    /**
     * <h2>loginDAO</h2>
     * <p>
     * loginDAO
     * </p>
     */
    @Autowired
    private LoginDAO loginDAO;
    /**
     * <h2>userDAO</h2>
     * <p>
     * userDAO
     * </p>
     */
    @Autowired
    private UserDAO userDAO;
    /**
     * <h2>encode</h2>
     * <p>
     * encode
     * </p>
     */
    @Autowired
    private PasswordEncoder encode;

    /**
     * <h2>getLoginResult</h2>
     * <p>
     * Get Login Result From Login FOrm
     * </p>
     * 
     * @param loginForm
     * @return LoginDTO
     */

    @Override
    public LoginDTO doGetLoginResult(LoginForm loginForm) {
        User user = this.loginDAO.dbGetUserByEmail(loginForm.getEmail());
        LoginDTO loginDTO = new LoginDTO();
        boolean isPasswordMatch = encode.matches(loginForm.getPassword(), user.getPassword());
        if (isPasswordMatch) {
            if (user != null) {
                loginDTO.setEmail(user.getEmail());
                loginDTO.setPassword(user.getPassword());
                loginDTO.setType(user.getType());
                loginDTO.setId(user.getId());
            }
        }
        return loginDTO;
    }

    /**
     * <h2>doInsertUser</h2>
     * <p>
     * Insert User From the database
     * </p>
     * 
     * @param userForm
     * @param loginUserId
     * @param userProfilePath
     * @throws IOException
     * @throws ParseException
     */
    @Override
    public void doInsertUser(UserForm userForm, int currentUserId, String userProfilePath)
            throws ParseException, IOException {
        Date currentDate = new Date();
        String imageBase64 = userForm.getProfile();
//        if (!imageBase64.isEmpty() && !imageBase64.equals("") && !imageBase64.equals(null)) {
        if (imageBase64 != null) {
            String[] block = imageBase64.split(",");
            String realData = block[1];
            byte[] data = Base64.decodeBase64(realData);
            try (FileOutputStream stream = new FileOutputStream(userProfilePath)) {
                stream.write(data);
            }
            userForm.setProfile(userProfilePath);
        }
        String password = userForm.getPassword().toString();
        String pwEncode = encode.encode(password);
        userForm.setPassword(pwEncode);
        User user = new User(userForm);
        userDAO.dbInsertUser(user, currentUserId, currentDate);
    }

    /**
     * <h2>dogetUserList</h2>
     * <p>
     * Get User List
     * </p>
     * 
     * @return UserDTO
     */
    @Override
    public List<UserDTO> dogetUserList() {
        List<User> resList = (List<User>) this.userDAO.dbGetUserList();
        List<UserDTO> listUserDTO = new ArrayList<>();
        if (resList != null) {
            for (User user : resList) {
                UserDTO resUserDTO = new UserDTO(user);
                listUserDTO.add(resUserDTO);
            }
        }
        return listUserDTO;
    }

    /**
     * <h2>doSearchUserByLimit</h2>
     * <p>
     * Search User By name or email
     * </p>
     * 
     * @param currentPage
     * @param noOfUser
     * @param userForm
     * @return
     * @throws ParseException
     */
    @Override
    public List<UserDTO> doSearchUserByLimit(int currentPage, int recordsPerPage, SearchDTO searchDTO)
            throws ParseException {
        List<UserDTO> userDTOList = new ArrayList<UserDTO>();
        List<User> userList = this.userDAO.dbGetUserListWithLimit(currentPage, recordsPerPage, searchDTO);
        if (userList.size() > 0) {
            for (User user : userList) {
                UserDTO userDTO = new UserDTO(user);
                int id = userDTO.getCreatedUserId();
                String name = this.userDAO.dbGetUserNamebyID(id);
                userDTO.setCreatedUserName(name);
                userDTOList.add(userDTO);
            }
        }
        return userDTOList;
    }

    /**
     * <h2>doGetUserbyId</h2>
     * <p>
     * Get User By Id
     * </p>
     * 
     * @param userId
     * @return
     * @throws IOException
     */
    @Override
    public UserForm doGetUserbyId(Integer userId) throws IOException {
        User resultUser = this.userDAO.dbGetUserbyID(userId);
        UserForm resultUserForm = new UserForm(resultUser);
        String userProfilePath = resultUserForm.getProfile();
        if (userProfilePath != null) {
            File userProfile = new File(userProfilePath);
            Boolean userProfileExist = userProfile.exists();
            if (userProfileExist) {
                FileInputStream fis = new FileInputStream(userProfile);
                byte byteArray[] = new byte[(int) userProfile.length()];
                fis.read(byteArray);
                String imageString = "data:image/png;base64," + Base64.encodeBase64String(byteArray);
                resultUserForm.setProfile(imageString);
            }
        } else {
            resultUserForm.setProfile(null);
        }
        return resultUserForm;
    }

    /**
     * <h2>doIsUserExist</h2>
     * <p>
     * User Exit With User Email For Insert
     * </p>
     * 
     * @param email
     * @return
     */
    @Override
    public boolean doIsUserExist(String email) {
        boolean userExist = false;
        User user = this.userDAO.dbGetUserByEmail(email);
        if (user != null) {
            userExist = true;
        }
        return userExist;
    }

    /**
     * <h2>doEmailExist</h2>
     * <p>
     * Check Email Is Exist For User Information Update
     * </p>
     * 
     * @param email
     * @param id
     * @return
     */
    @Override
    public Boolean doEmailExist(String email, Integer userId) {
        boolean updateEmailExist = false;
        List<User> userList = userDAO.dbUpdatedUserExistList(email);
        User userById = userDAO.dbGetUserbyID(userId);
        if (userList != null) {
            for (User user : userList) {
                if (userById != null) {
                    updateEmailExist = user.getEmail() != userById.getEmail() ? true : false;
                }
            }
        }
        return updateEmailExist;
    }

    @Override
    public void doUpdateUser(@Valid UserForm userForm, int currentUserId, String userProfilePath)
            throws IOException, ParseException {
        User user = new User(userForm);
        Date currentDate = new Date();
        String updateImagePath = userForm.getProfile();
        if (updateImagePath != "" && updateImagePath != null && !updateImagePath.equals(userProfilePath)) {
            File deletedOldProfile = new File(userProfilePath);
            deletedOldProfile.delete();
            String imageBase64 = userForm.getProfile();
            String[] block = imageBase64.split(",");
            String realData = block[1];
            byte[] data = Base64.decodeBase64(realData);
            try (FileOutputStream stream = new FileOutputStream(userProfilePath)) {
                stream.write(data);
            }
            user.setProfile(userProfilePath);
        }
        User updatedUser = this.userDAO.dbGetUserbyID(userForm.getId());
        if (updatedUser != null) {
            updatedUser.setName(user.getName());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setType(user.getType());
            updatedUser.setDob(user.getDob());
            updatedUser.setPhone(user.getPhone());
            updatedUser.setProfile(user.getProfile());
            updatedUser.setUpdatedAt(currentDate);
            updatedUser.setUpdatedUserId(currentUserId);
            updatedUser.setAddress(user.getAddress());
            this.userDAO.dbUpdateUser(updatedUser);
        }
    }

    /**
     * <h2>doUpdatePassword</h2>
     * <p>
     * Update Password
     * </p>
     * 
     * @param newPassword
     * @param userId
     */
    @Override
    public void doUpdatePassword(String newPassword, Integer userId) {
        BCryptPasswordEncoder encodePassword = new BCryptPasswordEncoder();
        String pwEncode = encodePassword.encode(newPassword);
        this.userDAO.dbUpdateUserPassword(pwEncode, userId);
    }

    /**
     * <h2>doDeleteUser</h2>
     * <p>
     * Delete User BY Id
     * </p>
     * 
     * @param userId
     */
    @SuppressWarnings("null")
    @Override
    public void doDeleteUser(Integer userId) {
        User deletedUser = this.userDAO.dbGetUserbyID(userId);
        deletedUser.setDeletedAt(DATE);
        String deletedProfilePath = deletedUser.getProfile();        
        if (deletedProfilePath != null) {
            File deletedUserProfile = new File(deletedProfilePath);
            Boolean deletedProfileExist = deletedUserProfile.exists();
            if (deletedProfileExist) {
                deletedUserProfile.delete();
            }
        }

    }

}
