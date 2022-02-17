package scm.bulletin.board.system.bl.service.post;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import net.sf.jasperreports.engine.JRException;
import scm.bulletin.board.system.bl.dto.post.PostDTO;
import scm.bulletin.board.system.web.form.post.PostForm;
import scm.bulletin.board.system.web.form.user.UserForm;

/**
 * <h2>PostService Class</h2>
 * <p>
 * Process for Displaying PostService
 * </p>
 * 
 * @author WinNgwePhyo
 *
 */
public interface PostService {
    /**
     * <h2>doAddPost</h2>
     * <p>
     * Add POst When Add Button Click
     * </p>
     *
     * @param postForm
     * @param loginUserID
     * @return void
     */
    public void doAddPost(@Valid PostForm postForm, int loginUserID);

    /**
     * <h2>doInsertTitleExist</h2>
     * <p>
     * Insert Title Exist or Not
     * </p>
     *
     * @param title
     * @return
     * @return boolean
     */
    public boolean doInsertTitleExist(String title);

    /**
     * <h2>doGetPostList</h2>
     * <p>
     * Get Post List By Post Id
     * </p>
     *
     * @param postCreatedUserId
     * @return
     * @return List<PostDTO>
     */

    public List<PostDTO> doGetPostList(int postCreatedUserId);

    /**
     * <h2>doSearchPostByLimit</h2>
     * <p>
     * Search Post List With Pagination
     * </p>
     *
     * @param currentPage
     * @param recordsPerPage
     * @param postForm
     * @param loginedUser
     * @return
     * @return List<PostDTO>
     */
    public List<PostDTO> doSearchPostByLimit(int currentPage, int recordsPerPage, PostForm postForm,
            UserForm loginedUser);

    /**
     * <h2>doSearchPostList</h2>
     * <p>
     * Search Post List
     * </p>
     *
     * @param postForm
     * @param loginedUser
     * @return
     * @return List<PostDTO>
     */
    public List<PostDTO> doSearchPostList(PostForm postForm, UserForm loginedUser);

    /**
     * <h2>dogetPostById</h2>
     * <p>
     * Get Post By Id
     * </p>
     *
     * @param postId
     * @return
     * @return PostForm
     */
    public PostForm dogetPostById(Integer postId);

    /**
     * <h2>doUpdateTitleExist</h2>
     * <p>
     * Test Post Title is Exit
     * </p>
     *
     * @param title
     * @param id
     * @return
     * @return Boolean
     */
    public Boolean doUpdateTitleExist(String title, Integer id);

    /**
     * <h2>doUpdatePost</h2>
     * <p>
     * Update Post Form
     * </p>
     *
     * @param postForm
     * @param loginUserForm
     * @return void
     */
    public void doUpdatePost(@Valid PostForm postForm, UserForm loginUserForm);

    /**
     * <h2>doUploadCSV</h2>
     * <p>
     * Post Upload With CSV File
     * </p>
     *
     * @param uploadFile
     * @param loginUserId
     * @return
     * @return List<String>
     * @throws IOException
     */
    public List<String> doUploadCSV(MultipartFile uploadFile, int loginUserId) throws IOException;

    /**
     * <h2>doGenerateDownloadExcelFile</h2>
     * <p>
     * 
     * </p>
     *
     * @param searchPostList
     * @param baos
     * @return
     * @return String
     */
    //public void doGenerateDownloadExcelFile(List<PostDTO> searchPostList) throws IOException;

    /**
     * <h2>doPostDelete</h2>
     * <p>
     * Delete Post By Id
     * </p>
     *
     * @param postId
     * @return void
     */
    public void doPostDelete(Integer postId);

    /**
     * <h2> doJasperGenerateReport</h2>
     * <p>
     * 
     * </p>
     *
     * @param searchPostList
     * @param baos
     * @param fileName
     * @param parameter
     * @param reportPath
     * @return
     * @return String
     */
    public String doJasperGenerateReport(List<PostDTO> searchPostList,
            HashMap<String, Object> parameter, String reportPath) throws JRException;

}
