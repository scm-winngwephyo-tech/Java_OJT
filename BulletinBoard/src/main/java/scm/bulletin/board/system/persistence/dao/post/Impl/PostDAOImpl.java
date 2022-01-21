package scm.bulletin.board.system.persistence.dao.post.Impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import scm.bulletin.board.system.persistence.dao.post.PostDAO;
import scm.bulletin.board.system.persistence.entity.post.Post;
import scm.bulletin.board.system.persistence.entity.user.User;
import scm.bulletin.board.system.web.form.post.PostForm;
import scm.bulletin.board.system.web.form.user.UserForm;

@Repository
public class PostDAOImpl implements PostDAO {

    /**
     * <h2>SELECT_POST_HQL</h2>
     * <p>
     * SELECT_POST_HQL
     * </p>
     */
    private static String SELECT_POST_HQL = "SELECT p FROM Post p where p.deletedAt is NULL ";
    /**
     * <h2>SELECT_POST_BY_ID_HQL</h2>
     * <p>
     * SELECT_POST_BY_ID_HQL
     * </p>
     */
    private static String SELECT_POST_BY_ID_HQL = "SELECT p FROM Post p where p.id = :id ";
    /**
     * <h2>SELECT_POST_BY_TITLE</h2>
     * <p>
     * SELECT_POST_BY_TITLE
     * </p>
     */
    private static String SELECT_POST_BY_TITLE = "SELECT p FROM Post p WHERE p.title = :title ";

    /**
     * <h2>sessionFactory</h2>
     * <p>
     * sessionFactory
     * </p>
     */
    @Autowired
    SessionFactory sessionFactory;

    /**
     * <h2>dbAddPost</h2>
     * <p>
     * Adding Post
     * </p>
     * 
     * @param post
     * @param currentUserId
     * @param date
     */
    @Override
    public void dbAddPost(Post post, int currentUserId, Date date) {
        post.setStatus(1);
        post.setCreatedUserId(currentUserId);
        post.setUpdatedUserId(currentUserId);
        post.setCreatedAt(date);
        post.setUpdatedAt(date);
        this.sessionFactory.getCurrentSession().save(post);
    }

    /**
     * <h2>dbgetPostList</h2>
     * <p>
     * Get Post List By User
     * </p>
     * 
     * @param user
     * @return
     */
    @SuppressWarnings({ "deprecation", "rawtypes" })
    @Override
    public List<Post> dbgetPostList(User user) {
        StringBuffer bufferQuery = new StringBuffer(SELECT_POST_HQL);
        if ((user.getType()).equals("1")) {
            bufferQuery.append("and p.createdUserId = :createdUserId ");
        }
        Query queryPostList = this.sessionFactory.getCurrentSession().createQuery(bufferQuery.toString());
        if ((user.getType()).equals("1")) {
            queryPostList.setParameter("createdUserId", user.getId());
        }
        @SuppressWarnings("unchecked")
        List<Post> postList = (List<Post>) queryPostList.list();
        return postList;
    }

    /**
     * <h2>getPostListWithLimit</h2>
     * <p>
     * Get Post List For SearchData
     * </p>
     * 
     * @param currentPage
     * @param recordsPerPage
     * @param postForm
     * @param loginedUser
     * @return
     */
    @SuppressWarnings("deprecation")
    @Override
    public List<Post> doGetPostListWithLimit(int currentPage, int noOfPost, PostForm postForm, UserForm userForm) {
        // CORRECT
        int start = currentPage * noOfPost - noOfPost;
        StringBuffer querySearch = new StringBuffer(SELECT_POST_HQL);
        if (userForm.getType().equals("1")) {
            querySearch.append("and p.createdUserId = :postCreatedUserId ");
        }
        Query queryPostList = this.sessionFactory.getCurrentSession().createQuery(querySearch.toString());
        if (postForm != null && (postForm.getTitle() != null || postForm.getDescription() != null)) {
            querySearch.append("and p.title = :title or p.description = :description ");
            queryPostList = this.sessionFactory.getCurrentSession().createQuery(querySearch.toString());
            queryPostList.setParameter("title", postForm.getTitle());
            queryPostList.setParameter("description", postForm.getDescription());
        }
        if (userForm.getType().equals("1")) {
            queryPostList.setParameter("postCreatedUserId", userForm.getId());
        }
        queryPostList.setFirstResult(start);
        queryPostList.setMaxResults(noOfPost);
        @SuppressWarnings("unchecked")
        List<Post> postList = (List<Post>) queryPostList.list();
        return postList;
    }

    /**
     * <h2>getPostListBySearchData</h2>
     * <p>
     * Get SearchData Without Pagination
     * </p>
     * 
     * @param loginedUser
     * @param postForm
     * @return
     */
    @SuppressWarnings("deprecation")
    @Override
    public List<Post> dbGetPostListBySearchData(UserForm loginedUser, PostForm postForm) {
        String postHqlQuery = "SELECT p FROM Post p";
        if (loginedUser.getType().equals("1")) {
            postHqlQuery += " where p.createdUserId = :postCreatedUserId ";
        }
        Query queryPostList = this.sessionFactory.getCurrentSession().createQuery(postHqlQuery);

        if (postForm != null && (postForm.getTitle() != null || postForm.getDescription() != null)) {
            postHqlQuery += loginedUser.getType().equals("1")
                    ? "and (p.title = :title or p.description = :description) "
                    : " where (p.title = :title or p.description = :description)";
            queryPostList = this.sessionFactory.getCurrentSession().createQuery(postHqlQuery);
            queryPostList.setParameter("title", postForm.getTitle());
            queryPostList.setParameter("description", postForm.getDescription());
        }
        if (loginedUser.getType().equals("1")) {
            queryPostList.setParameter("postCreatedUserId", loginedUser.getId());
        }
        @SuppressWarnings("unchecked")
        List<Post> postList = (List<Post>) queryPostList.list();

        return postList;
    }

    /**
     * <h2>dbGetPostById</h2>
     * <p>
     * Delete Post By ID
     * </p>
     * 
     * @param postId
     * @return
     */
    @SuppressWarnings("deprecation")
    @Override
    public Post dbGetPostById(Integer postId) {
        Query queryPostById = this.sessionFactory.getCurrentSession().createQuery(SELECT_POST_BY_ID_HQL);
        queryPostById.setParameter("id", postId);
        Post resultPost = (Post) queryPostById.uniqueResult();
        return resultPost;
    }

    /**
     * <h2>dbUpdatePostExist</h2>
     * <p>
     * Update Post Title is exist or not
     * </p>
     * 
     * @param title
     * @return
     */
    @SuppressWarnings("deprecation")
    @Override
    public List<Post> dbUpdatePostExist(String title) {
        @SuppressWarnings("rawtypes")
        Query queryPost = this.sessionFactory.getCurrentSession().createQuery(SELECT_POST_BY_TITLE);
        queryPost.setParameter("title", title);
        @SuppressWarnings("unchecked")
        List<Post> postList = (List<Post>) queryPost.list();
        return postList;
    }

    /**
     * <h2>dbGetPostByTitle</h2>
     * <p>
     * Get POst BY Title
     * </p>
     * 
     * @param title
     * @return
     */
    @SuppressWarnings("deprecation")
    @Override
    public Post dbGetPostByTitle(String title) {
        @SuppressWarnings("rawtypes")
        Query queryPostByTitle = this.sessionFactory.getCurrentSession().createQuery(SELECT_POST_BY_TITLE);
        queryPostByTitle.setParameter("title", title);
        Post resultPost = (Post) queryPostByTitle.uniqueResult();
        return resultPost;
    }

    /**
     * <h2>dbUpdatePost</h2>
     * <p>
     * Update Post
     * </p>
     * 
     * @param updatePost
     */
    @Override
    public void dbUpdatePost(Post updatePost) {
        this.sessionFactory.getCurrentSession().update(updatePost);
    }

    /**
     * <h2>dbPostUploadData</h2>
     * <p>
     * Upload Data
     * </p>
     * 
     * @param postData
     */
    @Override
    public void dbPostUploadData(Post postData) {
        this.sessionFactory.getCurrentSession().save(postData);

    }

}
