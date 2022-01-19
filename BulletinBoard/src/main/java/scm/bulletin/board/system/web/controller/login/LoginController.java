package scm.bulletin.board.system.web.controller.login;

import java.text.ParseException;

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
import org.springframework.web.servlet.ModelAndView;

import scm.bulletin.board.system.bl.dto.login.LoginDTO;
import scm.bulletin.board.system.bl.service.user.UserService;
import scm.bulletin.board.system.web.form.login.LoginForm;

/**
 * <h2>LoginController Class</h2>
 * <p>
 * Process for Displaying LoginController
 * </p>
 * 
 * @author WinNgwePhyo
 *
 */
@Controller
public class LoginController {
    @Autowired
    UserService userService;
    /**
     * <h2>messageSource</h2>
     * <p>
     * messageSource
     * </p>
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * <h2>initView</h2>
     * <p>
     * init view for user login
     * </p>
     *
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ModelAndView initView() {
        ModelAndView loginView = new ModelAndView("redirect:/showLogin");
        loginView.addObject("loginForm", new LoginDTO());
        return loginView;
    }

    /**
     * <h2>showLogin</h2>
     * <p>
     * Show Login For User
     * </p>
     *
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/showLogin", method = RequestMethod.GET)
    public ModelAndView showLogin() {
        ModelAndView loginView = new ModelAndView("login");
        loginView.addObject("loginForm", new LoginDTO());
        return loginView;
    }

    @RequestMapping(value = "/showLogin", params = "login", method = RequestMethod.POST)
    public ModelAndView showUserLogin(@ModelAttribute("loginForm") @Valid LoginForm loginForm, BindingResult result,
            HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ParseException {
        ModelAndView userCView = new ModelAndView("login");
        if (result.hasErrors()) {
            return userCView;
        }
        LoginDTO loginUser = this.userService.doGetLoginResult(loginForm);
        if (loginUser != null && (loginUser.getPassword() != null && loginUser.getEmail() != null)) {
            session.setAttribute("LOGIN_USER", loginUser);
            session.setAttribute("loginUserId", loginUser.getId());

            userCView = new ModelAndView("redirect:/postList");
        } else {
            userCView = new ModelAndView("login");
            userCView.addObject("errorMsg", messageSource.getMessage("M_SC_0001", null, null));
        }

        return userCView;
    }

    /**
     * <h2>logout</h2>
     * <p>
     * User Logout
     * </p>
     *
     * @param model
     * @param session
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(Model model, HttpSession session, HttpServletRequest request,
            HttpServletResponse response) {
        session.removeAttribute("LOGIN_USER");
        ModelAndView loginView = new ModelAndView("redirect:/showLogin");

        return loginView;
    }

}
