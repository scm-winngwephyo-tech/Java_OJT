package scm.bulletin.board.system.persistence.dao.post;

import java.util.Date;
import java.util.List;

import scm.bulletin.board.system.bl.dto.post.PostDTO;
import scm.bulletin.board.system.persistence.entity.post.Post;
import scm.bulletin.board.system.persistence.entity.user.User;
import scm.bulletin.board.system.web.form.post.PostForm;
import scm.bulletin.board.system.web.form.user.UserForm;

/**
 * <h2>PostDAO Class</h2>
 * <p>
 * Process for Displaying PostDAO
 * </p>
 * 
 * @author WinNgwePhyo
 *
 */
public interface PostDAO {
    /**
     * <h2>dbAddPost</h2>
     * <p>
     * Add Post When Add Button Click
     * </p>
     *
     * @param post
     * @param currentUserId
     * @param date
     * @return void
     */
    void dbAddPost(Post post, int currentUserId, Date date);

    /**
     * <h2>dbgetPostList</h2>
     * <p>
     * 
     * </p>
     *
     * @param user
     * @return
     * @return List<Post>
     */
    List<Post> dbgetPostList(User user);

    /**
     * <h2>getPostListWithLimit</h2>
     * <p>
     * 
     * </p>
     *
     * @param currentPage
     * @param recordsPerPage
     * @param postForm
     * @param loginedUser
     * @return
     * @return List<Post>
     */
    List<Post> doGetPostListWithLimit(int currentPage, int recordsPerPage, PostForm postForm, UserForm loginedUser);

    /**
     * <h2>getPostListBySearchData</h2>
     * <p>
     * 
     * </p>
     *
     * @param loginedUser
     * @param postForm
     * @return
     * @return List<Post>
     */
    List<Post> dbGetPostListBySearchData(UserForm loginedUser, PostForm postForm);

    /**
     * <h2>dbGetPostById</h2>
     * <p>
     * Get Post By Id
     * </p>
     *
     * @param postId
     * @return
     * @return Post
     */
    Post dbGetPostById(Integer postId);

    /**
     * <h2>dbUpdatePostExist</h2>
     * <p>
     * Update Post title is Exist or Not Exist
     * </p>
     *
     * @param title
     * @return
     * @return List<Post>
     */
    List<Post> dbUpdatePostExist(String title);

    /**
     * <h2>dbGetPostByTitle</h2>
     * <p>
     * Get Post By Title
     * </p>
     *
     * @param title
     * @return
     * @return Post
     */
    Post dbGetPostByTitle(String title);

    /**
     * <h2>dbUpdatePost</h2>
     * <p>
     * Update Post
     * </p>
     *
     * @param updatePostById
     * @return void
     */

    void dbUpdatePost(Post updatePostById);

    /**
     * <h2>dbPostUploadData</h2>
     * <p>
     * Upload Data For Post
     * </p>
     *
     * @param postData
     * @return void
     */
    void dbPostUploadData(Post postData);

}
