package scm.bulletin.board.system.web.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * <h2>ErrorController Class</h2>
 * <p>
 * Process for Displaying ErrorController
 * </p>
 * 
 * @author WinNgwePhyo
 *
 */
@Controller
public class ErrorController {
    /**
     * <h2>showErr401Page</h2>
     * <p>
     * Show Error Message
     * </p>
     *
     * @param model
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/err401", method = RequestMethod.GET)
    public ModelAndView showErr401Page(Model model) {
        ModelAndView errView = new ModelAndView("common/error/401");
        return errView;
    }

    /**
     * <h2>showErr404Page</h2>
     * <p>
     * Show Error Message
     * </p>
     *
     * @param model
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/err404", method = RequestMethod.GET)
    public ModelAndView showErr404Page(Model model) {
        ModelAndView errView = new ModelAndView("common/error/404");
        return errView;
    }
}
