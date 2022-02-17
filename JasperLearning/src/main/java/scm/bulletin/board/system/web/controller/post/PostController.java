package scm.bulletin.board.system.web.controller.post;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.sf.jasperreports.engine.JRException;
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
    /**
     * <h2>postService</h2>
     * <p>
     * postService
     * </p>
     */
    @Autowired
    private PostService postService;

    /**
     * <h2>userService</h2>
     * <p>
     * userService
     * </p>
     */
    @Autowired
    private UserService userService;

    /**
     * <h2>messageSource</h2>
     * <p>
     * messageSource
     * </p>
     */
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

    /**
     * <h2>addPost</h2>
     * <p>
     * Adding Post
     * </p>
     *
     * @param postForm
     * @param result
     * @param request
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/createPostConfirm", params = "confirmPost", method = RequestMethod.POST)
    public ModelAndView addPost(@ModelAttribute("createPost") @Valid PostForm postForm, BindingResult result,
            HttpServletRequest request) {
        ModelAndView postAddConfirm = new ModelAndView("createPost");
        if (result.hasErrors()) {
            postAddConfirm.addObject("errorMsg", messageSource.getMessage("M_SC_0007", null, null));
            return postAddConfirm;
        }
        if (this.postService.doInsertTitleExist(postForm.getTitle())) {
            postAddConfirm.addObject("errorMsg", messageSource.getMessage("M_SC_0018", null, null));
            return postAddConfirm;
        }
        postAddConfirm = new ModelAndView("createPostConfirm");
        postAddConfirm.addObject("postForm", postForm);
        return postAddConfirm;
    }

    /**
     * <h2>cancelPost</h2>
     * <p>
     * Redirect Cancel Button
     * </p>
     *
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/createPostConfirm", params = "back", method = RequestMethod.POST)
    public ModelAndView cancelPost() {
        ModelAndView createPostView = new ModelAndView("redirect:/postList");
        return createPostView;
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

    /**
     * <h2>cancelPostConfirm</h2>
     * <p>
     * Cancel Post Adding
     * </p>
     *
     * @param postForm
     * @param result
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/insertPost", params = "cancel", method = RequestMethod.POST)
    public ModelAndView cancelPostConfirm(@ModelAttribute("createPost") @Valid PostForm postForm,
            BindingResult result) {
        ModelAndView createPostView = new ModelAndView("createPost");
        createPostView.addObject("rollBackPostForm", postForm);
        return createPostView;
    }

    /**
     * <h2>getPaginationPostList</h2>
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
    private void getPaginationPostList(ModelAndView postListView, int currentPage, int recordsPerPage,
            boolean resultSearch, PostForm postForm, int postCreatedUserId) throws FileNotFoundException, IOException {
        UserForm loginedUser = this.userService.doGetUserbyId(postCreatedUserId);
        List<PostDTO> postList;
        if (resultSearch == false) {
            postList = this.postService.doGetPostList(postCreatedUserId);
        } else {
            postList = this.postService.doSearchPostList(postForm, loginedUser);
        }
        List<PostDTO> searchPostList;
        int rows = postList.size();
        int nOfPages = rows / recordsPerPage;
        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }
        searchPostList = this.postService.doSearchPostByLimit(currentPage, recordsPerPage, postForm, loginedUser);
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
        this.getPaginationPostList(postListView, currentPage, recordsPerPage, false, postForm, loginUserId);
        return postListView;
    }

    /**
     * <h2>createSearchList</h2>
     * <p>
     * Create Search List
     * </p>
     *
     * @param request
     * @param response
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/searchPost", method = RequestMethod.GET)
    public ModelAndView createSearchList(HttpServletRequest request, HttpServletResponse response)
            throws FileNotFoundException, IOException {
        String searchInput = request.getParameter("searchInput");
        PostForm postForm = new PostForm();
        int loginUserId = (int) request.getSession().getAttribute("loginUserId");
        ModelAndView postListView = new ModelAndView("postList");
        postForm.setTitle(searchInput);
        postForm.setDescription(searchInput);
        int currentPage = request.getParameter("currentPage") != null
                ? Integer.valueOf(request.getParameter("currentPage"))
                : 1;
        int recordsPerPage = request.getParameter("recordsPerPage") != null
                ? Integer.valueOf(request.getParameter("recordsPerPage"))
                : 10;
        this.getPaginationPostList(postListView, currentPage, recordsPerPage, true, postForm, loginUserId);
        postListView.addObject("searchData", searchInput);
        return postListView;
    }

    /**
     * <h2>searchPost</h2>
     * <p>
     * Search Post title or Sear Post description
     * </p>
     *
     * @param searchTitle
     * @param searchDescription
     * @param request
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @return ModelAndView
     */
    @SuppressWarnings("unused")
    @RequestMapping(value = "/searchPost", params = "searchPost", method = RequestMethod.POST)
    public ModelAndView searchPost(@RequestParam("searchInput") String searchInput, HttpServletRequest request)
            throws FileNotFoundException, IOException {
        int loginUserId = (int) request.getSession().getAttribute("loginUserId");
        PostForm postForm = new PostForm();
        ModelAndView postSearchListView = new ModelAndView("postList");
        if (searchInput.isEmpty()) {
            this.getPaginationPostList(postSearchListView, 1, 10, false, postForm, loginUserId);
        } else {
            postForm.setTitle(searchInput);
            postForm.setDescription(searchInput);
            this.getPaginationPostList(postSearchListView, 1, 10, true, postForm, loginUserId);
            postSearchListView.addObject("searchData", searchInput);
        }
        return postSearchListView;
    }

    /**
     * <h2>uploadPostCSV</h2>
     * <p>
     * Upload Post File
     * </p>
     *
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/uploadCSV", method = RequestMethod.GET)
    public ModelAndView uploadPostView() {
        return new ModelAndView("uploadCSV");
    }

    /**
     * <h2>uploadPostView</h2>
     * <p>
     * Upload Post CSV File
     * </p>
     *
     * @param request
     * @param uploadFile
     * @return
     * @throws Exception
     * @return ModelAndView
     */
    @RequestMapping(value = "/uploadCSV", method = RequestMethod.POST)
    public ModelAndView uploadPostView(HttpServletRequest request, @RequestParam("fileUpload") MultipartFile uploadFile)
            throws Exception {
        ModelAndView resultUploadView = new ModelAndView("uploadCSV");
        if (uploadFile.getSize() == 0) {
            resultUploadView.addObject("UploadErrorMsg", messageSource.getMessage("M_SC_0006", null, null));
            return resultUploadView;
        }
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        List<String> errorUpload = this.postService.doUploadCSV(uploadFile, loginUserId);
        if (errorUpload.size() != 0) {
            resultUploadView = new ModelAndView("uploadCSV");
            resultUploadView.addObject("UploadErrorMsg", errorUpload);
        } else {
            resultUploadView = new ModelAndView("redirect:/postList");
        }
        return resultUploadView;
    }

    /**
     * <h2>downloadExcel</h2>
     * <p>
     * Download Excel
     * </p>
     *
     * @param postForm
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws JRException
     * @return ModelAndView
     */
    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/searchPost", params = "downloadExcel", method = RequestMethod.POST)
    public ModelAndView downloadExcel(@RequestParam("searchInput") String search_input,PostForm postForm, HttpServletRequest request, HttpServletResponse response)
            throws IOException, JRException {
        int loginUserId = (int) request.getSession().getAttribute("loginUserId");
        UserForm loginedUser = this.userService.doGetUserbyId(loginUserId);
        postForm.setTitle(search_input);
        postForm.setDescription(search_input);
        List<PostDTO> searchPostList = this.postService.doSearchPostList(postForm, loginedUser);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HashMap<String, Object> parameter = new HashMap<String, Object>();
        String reportFileName = "";
        String reportPath = request.getRealPath("/") + "WEB-INF/reports/";
        reportFileName = this.postService.doJasperGenerateReport(searchPostList, parameter, reportPath);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + reportFileName);
        try {
            baos.writeTo(response.getOutputStream());
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ModelAndView postView = new ModelAndView("redirect:/postList");
        return postView;
    }

    /**
     * <h2>update</h2>
     * <p>
     * Update Post By Id Connect with View
     * </p>
     *
     * @param postId
     * @param request
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/updatePost", method = RequestMethod.GET)
    public ModelAndView updatePost(@RequestParam("id") Integer postId, HttpServletRequest request) {
        ModelAndView updateView = new ModelAndView("updatePost");
        PostForm oldPostForm = this.postService.dogetPostById(postId);
        updateView.addObject("oldPostForm", oldPostForm);
        return updateView;
    }

    /**
     * <h2>callUpdatePostConfirm</h2>
     * <p>
     * Call Post COnfirm when Confirm Button Click
     * </p>
     *
     * @param updatePostForm
     * @param result
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/updatePostConfirm", params = "update", method = RequestMethod.POST)
    public ModelAndView callUpdatePostConfirm(@ModelAttribute("editedPostForm") @Valid PostForm updatePostForm,
            BindingResult result) {
        ModelAndView updatePostView = new ModelAndView("updatePost");
        if (result.hasErrors()) {
            updatePostView.addObject("errorMsg", messageSource.getMessage("M_SC_0008", null, null));
            return updatePostView;
        }
        Boolean isPostTitleExist = this.postService.doUpdateTitleExist(updatePostForm.getTitle(),
                updatePostForm.getId());
        if (isPostTitleExist) {
            updatePostView.addObject("errorMsg", messageSource.getMessage("M_SC_0018", null, null));
            return updatePostView;
        }
        updatePostView = new ModelAndView("updatePostConfirm");
        if (updatePostForm.getStatus() == null) {
            updatePostForm.setStatus(0);
        } else {
            updatePostForm.setStatus(1);
        }
        updatePostView.addObject("updatePostForm", updatePostForm);
        return updatePostView;
    }

    /**
     * <h2>cancelUpdatePostMain</h2>
     * <p>
     * 
     * </p>
     *
     * @param postForm
     * @param result
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/updatePostConfirm", params = "cancel", method = RequestMethod.POST)
    public ModelAndView cancelUpdatePostMain(@ModelAttribute("finalCOnfirmPostForm") @Valid PostForm postForm,
            BindingResult result) {
        ModelAndView updatePostView = new ModelAndView("redirect:/postList");
        return updatePostView;

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

    /**
     * <h2>cancelUpdatePost</h2>
     * <p>
     * Cancel Update when Cancel button click
     * </p>
     *
     * @param postForm
     * @param result
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/editPost", params = "cancel", method = RequestMethod.POST)
    public ModelAndView cancelUpdatePost(@ModelAttribute("finalCOnfirmPostForm") @Valid PostForm postForm,
            BindingResult result) {
        ModelAndView updatePostView = new ModelAndView("updatePost");
        updatePostView.addObject("oldPostForm", postForm);
        return updatePostView;
    }
    /**
     * <h2>detailPost</h2>
     * <p>
     * Detail Post
     * </p>
     *
     * @param postId
     * @param request
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/detailPost", method = RequestMethod.GET)
    public ModelAndView detailPost(@RequestParam("id") Integer postId, HttpServletRequest request) {
        ModelAndView detailPostView = new ModelAndView("detailPost");
        PostForm detailPostForm = this.postService.dogetPostById(postId);
        detailPostView.addObject("detailPost", detailPostForm);
        return detailPostView;
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
        this.postService.doPostDelete(postId);
        ModelAndView updateDeletedView = new ModelAndView("redirect:/postList");
        updateDeletedView.addObject("errorMsg", messageSource.getMessage("M_SC_0013", null, null));
        this.getPaginationPostList(updateDeletedView, 1, 10, false, new PostForm(), loginUserId);
        return updateDeletedView;
    }
}
