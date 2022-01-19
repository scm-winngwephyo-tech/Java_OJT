package scm.bulletin.board.system.web.controller.post;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.velocity.app.event.ReferenceInsertionEventHandler.referenceInsertExecutor;
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

import scm.bulletin.board.system.bl.dto.post.PostDTO;
import scm.bulletin.board.system.bl.service.post.PostService;
import scm.bulletin.board.system.bl.service.user.UserService;
import scm.bulletin.board.system.web.form.post.PostForm;
import scm.bulletin.board.system.web.form.user.UserForm;

/**
 * <h2>PostController Class</h2>
 * <p>
 * Process for Displaying PostController
 * </p>
 * 
 * @author WinNgwePhyo
 *
 */
@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    /**
     * <h2>createPost</h2>
     * <p>
     * Create Post View
     * </p>
     *
     * @param model
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/createPost", method = RequestMethod.GET)
    public ModelAndView createPost(Model model) {
        ModelAndView createPostView = new ModelAndView("createPost");
        createPostView.addObject("createPost", new PostForm());
        return createPostView;
    }

    @RequestMapping(value = "/createPostConfirm", params = "confirmPost", method = RequestMethod.POST)
    public ModelAndView addPost(@ModelAttribute("createPost") @Valid PostForm postForm, BindingResult result,
            HttpServletRequest request) {
        ModelAndView postAddConfirm;
        if (result.hasErrors()) {
            postAddConfirm = new ModelAndView("createPost");
            postAddConfirm.addObject("errorMsg", messageSource.getMessage("M_SC_0007", null, null));
            return postAddConfirm;
        } else if (this.postService.doInsertTitleExist(postForm.getTitle())) {
            postAddConfirm = new ModelAndView("createPost");
            postAddConfirm.addObject("errorMsg", messageSource.getMessage("M_SC_0018", null, null));
            return postAddConfirm;
        } else {
            postAddConfirm = new ModelAndView("createPostConfirm");
            postAddConfirm.addObject("postForm", postForm);
            return postAddConfirm;
        }

    }

    /**
     * <h2>createPostConfirm</h2>
     * <p>
     * Create Post Confirm When Add Button
     * </p>
     *
     * @param postForm
     * @param result
     * @param request
     * @param response
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/insertPost", params = "addPost", method = RequestMethod.POST)
    public ModelAndView createPostConfirm(@ModelAttribute("postForm") @Valid PostForm postForm, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) {
        int LoginUserID = (int) request.getSession().getAttribute("loginUserId");
        this.postService.doAddPost(postForm, LoginUserID);
        ModelAndView createPostView = new ModelAndView("redirect:/postList");
        return createPostView;

    }

    @RequestMapping(value = "/insertPost", params = "cancel", method = RequestMethod.POST)
    public ModelAndView cancelPostConfirm(@ModelAttribute("createPost") @Valid PostForm postForm,
            BindingResult result) {
        ModelAndView createPostView = new ModelAndView("createPost");
        createPostView.addObject("rollBackPostForm", postForm);

        return createPostView;
    }

    /**
     * <h2>getpagination</h2>
     * <p>
     * Get Pagination For Post List
     * </p>
     *
     * @param postListView
     * @param currentPage
     * @param recordsPerPage
     * @param resultSearch
     * @param postForm
     * @param postCreatedUserId
     * @throws FileNotFoundException
     * @throws IOException
     * @return void
     */
    private void getpagination(ModelAndView postListView, int currentPage, int recordsPerPage, boolean resultSearch,
            PostForm postForm, int postCreatedUserId) throws FileNotFoundException, IOException {
        List<PostDTO> postList = this.postService.doGetPostList(postCreatedUserId);
        UserForm loginedUser = this.userService.doGetUserbyId(postCreatedUserId);
        int rows = postList.size();
        int nOfPages = rows / recordsPerPage;
        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }
        List<PostDTO> searchPostList;
        if (loginedUser.getType().equals("1")) {
            searchPostList = this.postService.doSearchPostByLimit(currentPage, recordsPerPage, postForm, loginedUser);
        } else {
            searchPostList = this.postService.doSearchPostByLimit(currentPage, recordsPerPage, postForm, loginedUser);
        }
        postListView.addObject("noOfPages", nOfPages);
        postListView.addObject("currentPage", currentPage);
        postListView.addObject("recordsPerPage", recordsPerPage);
        postListView.addObject("postList", searchPostList);
    }

    /**
     * <h2>createPostList</h2>
     * <p>
     * Create Post List With View
     * </p>
     *
     * @param postForm
     * @param request
     * @param response
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/postList", method = RequestMethod.GET)
    public ModelAndView createPostList(PostForm postForm, HttpServletRequest request, HttpServletResponse response)
            throws FileNotFoundException, IOException {
        int loginUserId = (int) request.getSession().getAttribute("loginUserId");
        ModelAndView postListView = new ModelAndView("postList");
        int currentPage = request.getParameter("currentPage") != null
                ? Integer.valueOf(request.getParameter("currentPage"))
                : 1;
        int recordsPerPage = request.getParameter("recordsPerPage") != null
                ? Integer.valueOf(request.getParameter("recordsPerPage"))
                : 10;
        this.getpagination(postListView, currentPage, recordsPerPage, false, postForm, loginUserId);

        return postListView;
    }

    /**
     * <h2>deletePost</h2>
     * <p>
     * Delete Post By Id
     * </p>
     *
     * @param postId
     * @param request
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/deletePost", method = RequestMethod.GET)
    public ModelAndView deletePost(@RequestParam("id") Integer postId, HttpServletRequest request)
            throws FileNotFoundException, IOException {
        int loginUserId = (int) request.getSession().getAttribute("loginUserId");
        ModelAndView updateDeletedView = new ModelAndView("postList");
        this.postService.doPostDelete(postId);
        updateDeletedView.addObject("errorMsg", messageSource.getMessage("M_SC_0013", null, null));
        this.getpagination(updateDeletedView, 1, 10, false, new PostForm(), loginUserId);
        return updateDeletedView;

    }

    @RequestMapping(value = "/detailPost", method = RequestMethod.GET)
    public ModelAndView detailPost(@RequestParam("id") Integer postId, HttpServletRequest request) {
        ModelAndView detailPostView = new ModelAndView("detailPost");
        PostForm detailPostForm = this.postService.dogetPostById(postId);
        detailPostView.addObject("detailPost", detailPostForm);
        return detailPostView;
    }

    @RequestMapping(value = "/updatePost", method = RequestMethod.GET)
    public ModelAndView update(@RequestParam("id") Integer postId, HttpServletRequest request) {
        ModelAndView updateView = new ModelAndView("updatePost");
        PostForm oldPostForm = this.postService.dogetPostById(postId);
        updateView.addObject("oldPostForm", oldPostForm);

        return updateView;
    }

    @RequestMapping(value = "/updatePostConfirm", method = RequestMethod.POST)
    public ModelAndView callUpdatePostConfirm(@ModelAttribute("editedPostForm") @Valid PostForm updatePostForm,
            BindingResult result) {
        ModelAndView updatePostView;
        if (result.hasErrors()) {
            updatePostView = new ModelAndView("updatePost");
            updatePostView.addObject("errorMsg", messageSource.getMessage("M_SC_0008", null, null));
            return updatePostView;
        } else {
            Boolean isPostTitleExist = this.postService.doUpdateTitleExist(updatePostForm.getTitle(),
                    updatePostForm.getId());
            if (isPostTitleExist) {
                updatePostView = new ModelAndView("updatePost");
                updatePostView.addObject("errorMsg", messageSource.getMessage("M_SC_0018", null, null));
            } else {
                updatePostView = new ModelAndView("updatePostConfirm");
                updatePostView.addObject("updatePostForm", updatePostForm);
            }
            return updatePostView;
        }
    }

    /**
     * <h2>updatePost</h2>
     * <p>
     * Update Post When Update Button Click
     * </p>
     *
     * @param postForm
     * @param result
     * @param request
     * @param response
     * @return
     * @throws ParseException
     * @throws FileNotFoundException
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/editPost", params = "update", method = RequestMethod.POST)
    public ModelAndView updatePost(@ModelAttribute("finalConfirmPostForm") @Valid PostForm postForm,
            BindingResult result, HttpServletRequest request, HttpServletResponse response)
            throws ParseException, FileNotFoundException, IOException {
        int loginUserId = (int) request.getSession().getAttribute("loginUserId");
        UserForm loginUser = this.userService.doGetUserbyId(loginUserId);
        this.postService.doUpdatePost(postForm, loginUser);
        ModelAndView updatePostView = new ModelAndView("redirect:/postList");
        return updatePostView;
    }

    @RequestMapping(value = "/editPost", params = "cancel", method = RequestMethod.POST)
    public ModelAndView cancelUpdatePost(@ModelAttribute("finalCOnfirmPostForm") @Valid PostForm postForm,
            BindingResult result) {
        ModelAndView updatePostView = new ModelAndView("updatePost");
        updatePostView.addObject("oldPostForm", postForm);

        return updatePostView;

    }

}
