package scm.bulletin.board.system.web.controller.user;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import scm.bulletin.board.system.bl.dto.login.LoginDTO;
import scm.bulletin.board.system.bl.dto.search.SearchDTO;
import scm.bulletin.board.system.bl.dto.user.UserDTO;
import scm.bulletin.board.system.bl.service.user.UserService;
import scm.bulletin.board.system.web.form.login.LoginForm;
import scm.bulletin.board.system.web.form.user.UserForm;

/**
 * <h2>UserController Class</h2>
 * <p>
 * Process for Displaying UserController
 * </p>
 * 
 * @author WinNgwePhyo
 *
 */
@Controller
public class UserController {
    /**
     * <h2>userService</h2>
     * <p>
     * userService
     * </p>
     */
    @Autowired
    UserService userService;
    /**
     * <h2>messageSource</h2>
     * <p>
     * messageSource
     * </p>
     */
    @Autowired
    MessageSource messageSource;

    /**
     * <h2>createUser</h2>
     * <p>
     * Create User
     * </p>
     *
     * @param model
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/createUser", method = RequestMethod.GET)
    public ModelAndView createUser(Model model) {
        ModelAndView createUserView = new ModelAndView("createUser");
        createUserView.addObject("createUser", new UserForm());
        return createUserView;
    }

    /**
     * <h2>insertUser</h2>
     * <p>
     * Insert User when ConfirmUser Button Click
     * </p>
     *
     * @param userForm
     * @param result
     * @param session
     * @param request
     * @param imageData
     * @return
     * @throws ParseException
     * @return ModelAndView
     */
    @RequestMapping(value = "/createUserConfirm", params = "confirmUser", method = RequestMethod.POST)
    public ModelAndView insertUser(@ModelAttribute("createUser") @Valid UserForm userForm, BindingResult result,
            HttpSession session, HttpServletRequest request, @RequestParam("imageData") String imageData)
            throws ParseException {
        ModelAndView userConfirmView = null;
        if (result.hasErrors()) {
            userConfirmView = new ModelAndView("createUser");
            userConfirmView.addObject("errorMsg", messageSource.getMessage("M_SC_0007", null, null));
            return userConfirmView;
        } else if (!userForm.getPassword().equals(request.getParameter("confirmPassword"))) {
            userConfirmView = new ModelAndView("createUser");
            userConfirmView.addObject("errorMsg", messageSource.getMessage("M_SC_0009", null, null));
            return userConfirmView;
        } else if (userService.doIsUserExist(userForm.getEmail())) {
            userConfirmView = new ModelAndView("createUser");
            userConfirmView.addObject("errorMsg", messageSource.getMessage("M_SC_0010", null, null));
            return userConfirmView;
        } else {
            userConfirmView = new ModelAndView("createUserConfirm");
            if (imageData.length() > 0) {
                userForm.setProfile(imageData);
            }
            userConfirmView.addObject("userForm", userForm);
            return userConfirmView;
        }
    }

    /**
     * <h2>calcelUserMain</h2>
     * <p>
     * Cancel User Adding
     * </p>
     *
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/createUserConfirm", params = "clearUserData", method = RequestMethod.POST)
    public ModelAndView calcelUserMain() {
        ModelAndView createUserView = new ModelAndView("redirect:/userList");
        return createUserView;
    }

    /**
     * <h2>insertUser</h2>
     * <p>
     * Insert User Into Database
     * </p>
     * 
     * @param userForm
     * @param result
     * @param request
     * @return
     * @throws ParseException
     * @throws IOException
     * @return ModelAndView
     */
    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/insertUser", params = "addUser", method = RequestMethod.POST)
    public ModelAndView insertUser(@ModelAttribute("createUser") @Valid UserForm userForm, BindingResult result,
            HttpServletRequest request) throws ParseException, IOException {
        int loginUserId = (int) request.getSession().getAttribute("loginUserId");
        Path path = Paths.get(request.getRealPath("/") + "/resources/profiles/" + userForm.getEmail());
        String userProfilePath = Files.createDirectories(path) + "/" + userForm.getEmail() + ".png";
        this.userService.doInsertUser(userForm, loginUserId, userProfilePath);
        ModelAndView createUserView = new ModelAndView("redirect:/userList");
        return createUserView;
    }

    /**
     * <h2>calcelUser</h2>
     * <p>
     * Cancel Insert User
     * </p>
     * 
     * @param userForm
     * @param result
     * @param session
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/insertUser", params = "cancel", method = RequestMethod.POST)
    public ModelAndView calcelUser(@ModelAttribute("createUser") @Valid UserForm userForm, BindingResult result,
            HttpSession session) {
        ModelAndView createUserView = new ModelAndView("createUser");
        createUserView.addObject("rollBackUserForm", userForm);
        return createUserView;
    }

    // READ OR SELECT USER
    /**
     * <h2>listUserPagination</h2>
     * <p>
     * Pagination For User List
     * </p>
     * 
     * @param model
     * @param currentPage
     * @param recordsPerPage
     * @param resultSearch
     * @param searchDTO
     * @return
     * @throws IOException
     * @return ModelAndView
     */
    public ModelAndView listUserPagination(ModelAndView model, int currentPage, int recordsPerPage,
            boolean resultSearch, @Valid SearchDTO searchDTO) throws IOException, ParseException {
        List<UserDTO> userList = this.userService.dogetUserList();
        int userRows = userList.size();
        int noOfPages = userRows / recordsPerPage;
        if (userRows % recordsPerPage > 0) {
            noOfPages++;
        }
        List<UserDTO> searchUserList = this.userService.doSearchUserByLimit(currentPage, recordsPerPage, searchDTO);
        model.addObject("userList", searchUserList);
        model.addObject("noOfPages", noOfPages);
        model.addObject("currentPage", currentPage);
        model.addObject("recordsPerPage", recordsPerPage);
        model.setViewName("userList");
        return model;
    }

    /**
     * <h2>createUserList</h2>
     * <p>
     * Create User List Check Pagination
     * </p>
     * 
     * @param session
     * @param userForm
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @return ModelAndView
     * @throws ParseException
     */
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public ModelAndView createUserList(HttpSession session, SearchDTO searchDTO, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ParseException {
        ModelAndView createUserListView = new ModelAndView("userList");
        int currentPage = request.getParameter("currentPage") != null
                ? Integer.valueOf(request.getParameter("currentPage"))
                : 1;
        int recordsPerPage = request.getParameter("recordsPerPage") != null
                ? Integer.valueOf(request.getParameter("recordsPerPage"))
                : 10;
        this.listUserPagination(createUserListView, currentPage, recordsPerPage, false, searchDTO);
        createUserListView.addObject("searchForm", new SearchDTO());
        return createUserListView;
    }

    /**
     * <h2>searchUser</h2>
     * <p>
     * search user by name or email
     * </p>
     *
     * @param search_input
     * @return
     * @throws IOException
     * @return ModelAndView
     * @throws ParseException
     */
    @RequestMapping(value = "/searchUser", method = RequestMethod.POST)
    public ModelAndView searchUser(@ModelAttribute("searchForm") @Valid SearchDTO searchDTO)
            throws IOException, ParseException {
        ModelAndView userListView = new ModelAndView("userList");
        if (searchDTO == null) {
            this.listUserPagination(userListView, 1, 10, false, searchDTO);
            return userListView;
        } else {
            this.listUserPagination(userListView, 1, 10, true, searchDTO);
            userListView.addObject("searchDTO", searchDTO);
            return userListView;
        }
    }

    /**
     * <h2>detailUser</h2>
     * <p>
     * Detail User By Selected Id
     * </p>
     *
     * @param userId
     * @param request
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/userDetail", method = RequestMethod.GET)
    public ModelAndView detailUser(@RequestParam("id") Integer userId, HttpServletRequest request)
            throws FileNotFoundException, IOException {
        ModelAndView userView = new ModelAndView("userDetail");
        UserForm userDetail = this.userService.doGetUserbyId(userId);
        userView.addObject("userDetail", userDetail);

        return userView;
    }

//UPDATE
    /**
     * <h2>update</h2>
     * <p>
     * Update User Connect With View
     * </p>
     *
     * @param userId
     * @param request
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.GET)
    public ModelAndView update(@RequestParam("id") Integer userId, HttpServletRequest request)
            throws FileNotFoundException, IOException {
        ModelAndView updateView = new ModelAndView("updateUser");
        UserForm oldUserForm = this.userService.doGetUserbyId(userId);
        updateView.addObject("oldUserForm", oldUserForm);
        return updateView;
    }

    /**
     * <h2>callUpdateUserConfirm</h2>
     * <p>
     * Update User Confirm
     * </p>
     *
     * @param updateUserForm
     * @param result
     * @param session
     * @param imageData
     * @return
     * @throws ParseException
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/updateUserConfirm", params = "updateUserConfirm", method = RequestMethod.POST)
    public ModelAndView callUpdateUserConfirm(@ModelAttribute("editedUserForm") @Valid UserForm updateUserForm,
            BindingResult result, HttpSession session, @RequestParam("imageData") String imageData)
            throws ParseException, IOException {
        ModelAndView updateConfirmView = new ModelAndView("updateUser");
        if (result.hasErrors()) {
            updateConfirmView.addObject("errorMsg", messageSource.getMessage("M_SC_0008", null, null));
        } else {
            Boolean isUpdatedEmailExist = this.userService.doEmailExist(updateUserForm.getEmail(),
                    updateUserForm.getId());
            if (isUpdatedEmailExist) {
                updateConfirmView = new ModelAndView("updateUser");
                updateConfirmView.addObject("errorMsg", messageSource.getMessage("M_SC_0010", null, null));
            } else {
                UserForm userById = this.userService.doGetUserbyId(updateUserForm.getId());
                updateConfirmView = new ModelAndView("updateUserConfirm");
                if (imageData == null || imageData == "" || imageData.isEmpty()) {
                    updateUserForm.setProfile(userById.getProfile());
                } else {
                    updateUserForm.setProfile(imageData);
                }
                updateConfirmView.addObject("updateConfirmForm", updateUserForm);
            }
        }
        return updateConfirmView;
    }

    /**
     * <h2> calcelUserUpdate</h2>
     * <p>
     * Cancel User Update
     * </p>
     *
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/updateUserConfirm", params = "cancelUser", method = RequestMethod.POST)
    public ModelAndView calcelUserUpdate() {
        ModelAndView createUserView = new ModelAndView("redirect:/userList");
        return createUserView;
    }

    /**
     * <h2>updateUser</h2>
     * <p>
     * Update User Confirm
     * </p>
     *
     * @param userForm
     * @param result
     * @param session
     * @param request
     * @return
     * @throws ParseException
     * @throws IOException
     * @return ModelAndView
     */
    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/editUser", params = "update", method = RequestMethod.POST)
    public ModelAndView updateUser(@ModelAttribute("finalConfirmUserForm") @Valid UserForm userForm,
            BindingResult result, HttpSession session, HttpServletRequest request) throws ParseException, IOException {
        int loginUserId = (int) request.getSession().getAttribute("loginUserId");
        Path path = Paths.get(request.getRealPath("/") + "/resources/profiles/" + userForm.getEmail());
        String userProfilePath = Files.createDirectories(path) + "/" + userForm.getEmail() + ".png";
        this.userService.doUpdateUser(userForm, loginUserId, userProfilePath);
        ModelAndView updateUserView = new ModelAndView("redirect:/userList");
        return updateUserView;
    }

    /**
     * <h2>cancelUpdateUser</h2>
     * <p>
     * Update User Cancel
     * </p>
     *
     * @param userForm
     * @param result
     * @param session
     * @return
     * @throws ParseException
     * @return ModelAndView
     */
    @RequestMapping(value = "/editUser", params = "cancel", method = RequestMethod.POST)
    public ModelAndView cancelUpdateUser(@ModelAttribute("finalConfirmUserForm") @Valid UserForm userForm,
            BindingResult result, HttpSession session) throws ParseException {
        ModelAndView updateUserView = new ModelAndView("updateUser");
        System.out.println(userForm.getProfile());
        updateUserView.addObject("oldUserForm", userForm);
        return updateUserView;
    }

    // DELETE USER
    /**
     * <h2>deleteUser</h2>
     * <p>
     * Delete User BY Id
     * </p>
     *
     * @param userId
     * @param request
     * @return
     * @throws IOException
     * @return ModelAndView
     * @throws ParseException
     */
    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public ModelAndView deleteUser(@RequestParam("id") Integer userId, HttpServletRequest request)
            throws IOException, ParseException {
        ModelAndView updateDeleteView = new ModelAndView("userList");
        this.userService.doDeleteUser(userId);
        updateDeleteView.addObject("errorMsg", messageSource.getMessage("M_SC_0013", null, null));
        this.listUserPagination(updateDeleteView, 1, 10, false, new SearchDTO());
        return updateDeleteView;
    }
}
