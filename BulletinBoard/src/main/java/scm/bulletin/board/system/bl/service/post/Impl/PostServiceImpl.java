package scm.bulletin.board.system.bl.service.post.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import scm.bulletin.board.system.bl.dto.post.PostDTO;
import scm.bulletin.board.system.bl.service.post.PostService;
import scm.bulletin.board.system.persistence.dao.post.PostDAO;
import scm.bulletin.board.system.persistence.dao.user.UserDAO;
import scm.bulletin.board.system.persistence.entity.post.Post;
import scm.bulletin.board.system.persistence.entity.user.User;
import scm.bulletin.board.system.web.form.post.PostForm;
import scm.bulletin.board.system.web.form.user.UserForm;

/**
 * <h2>PostServiceImpl Class</h2>
 * <p>
 * Process for Displaying PostServiceImpl
 * </p>
 * 
 * @author WinNgwePhyo
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service
@Primary
public class PostServiceImpl implements PostService {

    /**
     * <h2>userDAO</h2>
     * <p>
     * userDAO
     * </p>
     */
    @Autowired
    private UserDAO userDAO;
    /**
     * <h2>postDAO</h2>
     * <p>
     * postDAO
     * </p>
     */
    @Autowired
    private PostDAO postDAO;

    /**
     * <h2>doGetPostList</h2>
     * <p>
     * Get Post List By PostCreatedUser ID
     * </p>
     * 
     * @param postCreatedUserId
     * @return
     */
    @Override
    public List<PostDTO> doGetPostList(int postCreatedUserId) {
        User user = this.userDAO.dbGetUserbyID(postCreatedUserId);
        List<Post> postList = this.postDAO.dbgetPostList(user);
        List<PostDTO> postListDTO = new ArrayList<>();

        if (postList != null) {
            for (Post post : postList) {
                PostDTO resPostDTO = new PostDTO(post);
                postListDTO.add(resPostDTO);
            }
        }
        return postListDTO;
    }

    /**
     * <h2>doSearchPostByLimit</h2>
     * <p>
     * 
     * </p>
     * 
     * @param currentPage
     * @param recordsPerPage
     * @param postForm
     * @param loginedUser
     * @return
     */
    @Override
    public List<PostDTO> doSearchPostByLimit(int currentPage, int recordsPerPage, PostForm postForm,
            UserForm loginedUser) {
        List<PostDTO> postDTOList = new ArrayList<PostDTO>();
        List<Post> postList = this.postDAO.doGetPostListWithLimit(currentPage, recordsPerPage, postForm, loginedUser);
        if (postList.size() > 0) {
            for (Post post : postList) {
                PostDTO postDTO = new PostDTO(post);
                postDTOList.add(postDTO);
            }
        }

        return postDTOList;
    }

    /**
     * <h2>doPostDelete</h2>
     * <p>
     * 
     * </p>
     * 
     * @param postId
     */
    @Override
    public void doPostDelete(Integer postId) {
        Post postDeletedUser = this.postDAO.dbGetPostById(postId);
        postDeletedUser.setDeletedAt(new Date());

    }

    /**
     * <h2>dogetPostById</h2>
     * <p>
     * Get Post By Post Id
     * </p>
     * 
     * @param postId
     * @return
     */
    @Override
    public PostForm dogetPostById(Integer postId) {
        Post postResult = this.postDAO.dbGetPostById(postId);
        if (postResult != null) {
            PostForm resultPostForm = new PostForm(postResult);
            return resultPostForm;
        }
        return null;
    }

    /**
     * <h2>doUpdateTitleExist</h2>
     * <p>
     * Test Update Title is Exist or Not
     * </p>
     * 
     * @param title
     * @param id
     * @return
     */
    @Override
    public Boolean doUpdateTitleExist(String title, Integer postId) {
        boolean updateTitleExist = false;
        List<Post> posList = this.postDAO.dbUpdatePostExist(title);
        Post postById = this.postDAO.dbGetPostById(postId);
        if (posList != null) {
            for (Post post : posList)
                updateTitleExist = post.getTitle() != postById.getTitle() ? true : false;
        }
        return updateTitleExist;
    }

    /**
     * <h2>doUpdatePost</h2>
     * <p>
     * Post Update
     * </p>
     * 
     * @param postForm
     * @param loginUserForm
     */
    @Override
    public void doUpdatePost(@Valid PostForm postForm, UserForm currentUser) {
        Post post=new Post(postForm);
        if (currentUser.getType().equals("0")
                || currentUser.getType().equals("1")) {
            
            Post updatePostById = this.postDAO.dbGetPostById(post.getId());
            Post postTitle = this.postDAO.dbGetPostByTitle(post.getTitle());
            if (updatePostById != null) {
                if (postTitle == null || (postTitle != null && postTitle.getTitle() == updatePostById.getTitle())) {
                    updatePostById.setTitle(post.getTitle());
                    updatePostById.setDescription(post.getDescription());
                    updatePostById.setStatus(post.getStatus());
                    updatePostById.setUpdatedAt(new Date());
                    updatePostById.setCreatedUserId(currentUser.getId());
                }

            }
            this.postDAO.dbUpdatePost(updatePostById);
        }

    }

    /**
     * <h2> doInsertTitleExist </h2>
     * <p>
     * Insert Title Is Exit or Not
     * </p>
     * 
     * @param title
     * @return
     */
    @Override
    public boolean doInsertTitleExist(String title) {
     Post resultPost=this.postDAO.dbGetPostByTitle(title);
     boolean titleExist =false;
     if(resultPost !=null) {
         titleExist=true;
     }
     return titleExist;
    }

    @Override
    public void doAddPost(@Valid PostForm postForm, int currentUserId) {
        Post post=new Post(postForm);
        this.postDAO.dbAddPost(post,currentUserId,new Date());
       
        
    }
}
