package scm.bulletin.board.system.bl.service.post;

import java.util.List;

import javax.validation.Valid;

import scm.bulletin.board.system.bl.dto.post.PostDTO;
import scm.bulletin.board.system.persistence.entity.post.Post;
import scm.bulletin.board.system.web.form.post.PostForm;
import scm.bulletin.board.system.web.form.user.UserForm;

public interface PostService {

    /**
     * <h2> doGetPostList</h2>
     * <p>
     * Get Post List By ID
     * </p>
     *
     * @param postCreatedUserId
     * @return
     * @return List<PostDTO>
     */


    /**
     * <h2> doSearchPostByLimit</h2>
     * <p>
     * Search Post
     * </p>
     *
     * @param currentPage
     * @param recordsPerPage
     * @param postForm
     * @param loginedUser
     * @return
     * @return List<PostDTO>
     */

    //List<PostDTO> doGetPostLists();

    /**
     * <h2> doGetPostList</h2>
     * <p>
     * 
     * </p>
     *
     * @param postCreatedUserId
     * @return
     * @return List<PostDTO>
     */
    
    List<PostDTO> doGetPostList(int postCreatedUserId);

    /**
     * <h2> doSearchPostByLimit</h2>
     * <p>
     * 
     * </p>
     *
     * @param currentPage
     * @param recordsPerPage
     * @param postForm
     * @param loginedUser
     * @return
     * @return List<PostDTO>
     */
    List<PostDTO> doSearchPostByLimit(int currentPage, int recordsPerPage, PostForm postForm, UserForm loginedUser);

    /**
     * <h2> doPostDelete</h2>
     * <p>
     * Delete Post By Id
     * </p>
     *
     * @param postId
     * @return void
     */
    void doPostDelete(Integer postId);

    /**
     * <h2> dogetPostById</h2>
     * <p>
     * Get Post By Id
     * </p>
     *
     * @param postId
     * @return
     * @return PostForm
     */
    PostForm dogetPostById(Integer postId);

    /**
     * <h2> doUpdateTitleExist</h2>
     * <p>
     * Test Post Title is Exit
     * </p>
     *
     * @param title
     * @param id
     * @return
     * @return Boolean
     */
    Boolean doUpdateTitleExist(String title, Integer id);

    /**
     * <h2> doUpdatePost</h2>
     * <p>
     * Update Post Form
     * </p>
     *
     * @param postForm
     * @param loginUserForm
     * @return void
     */
    void doUpdatePost(@Valid PostForm postForm, UserForm loginUserForm);

    /**
     * <h2> doInsertTitleExist</h2>
     * <p>
     * Insert Title Exist or Not
     * </p>
     *
     * @param title
     * @return
     * @return boolean
     */
    boolean doInsertTitleExist(String title);

    /**
     * <h2> doAddPost</h2>
     * <p>
     * Add POst When Add Button Click
     * </p>
     *
     * @param postForm
     * @param loginUserID
     * @return void
     */
    void doAddPost(@Valid PostForm postForm, int loginUserID);


}