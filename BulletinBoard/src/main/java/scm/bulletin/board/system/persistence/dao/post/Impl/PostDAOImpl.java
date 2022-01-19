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

    private static String SELECT_POST_HQL = "SELECT p FROM Post p where p.deletedAt is NULL ";
    private static String SELECT_POST_BY_ID_HQL = "SELECT p FROM Post p where p.id = :id ";
    private static String SELECT_POST_BY_TITLE = "SELECT p FROM Post p WHERE p.title = :title ";

    @Autowired
    SessionFactory sessionFactory;

    /**
     * <h2>dbgetPostList</h2>
     * <p>
     * Get Post List By User
     * </p>
     * 
     * @param user
     * @return
     */
    @SuppressWarnings("deprecation")
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
    public List<Post> doGetPostListWithLimit(int currentPage, int recordsPerPage, PostForm postForm,
            UserForm userForm) {
        StringBuffer querySearch = new StringBuffer(SELECT_POST_HQL);
        int start = currentPage * recordsPerPage - recordsPerPage;
        if (userForm.getType().equals("1")) {
            querySearch.append("and p.createdUserId = :postCreatedUserId ");
        }
        Query queryPostList = this.sessionFactory.getCurrentSession().createQuery(querySearch.toString());
        if (postForm != null) {
            if (postForm.getTitle() != null) {
                querySearch.append("p.title = :title");
            }
            if (postForm.getDescription() != null) {
                querySearch.append("p.description = :description");
            }
            queryPostList = this.sessionFactory.getCurrentSession().createQuery(querySearch.toString());
            if (postForm.getTitle() != null) {
                queryPostList.setParameter("title", postForm.getTitle());
            }
            if (postForm.getDescription() != null) {
                queryPostList.setParameter("description", postForm.getDescription());
            }
        }
//        if (postForm != null && (postForm.getTitle() != null || postForm.getDescription() != null)) {
//            sql += userForm.getType().equals("1") ? "and (p.title = :title or p.description = :description) "
//                    : " where (p.title = :title or p.description = :description)";
//            queryPostList = this.sessionFactory.getCurrentSession().createQuery(sql);
//            queryPostList.setParameter("title", postForm.getTitle());
//            queryPostList.setParameter("description", postForm.getDescription());
//        }

        if (userForm.getType().equals("1")) {
            queryPostList.setParameter("postCreatedUserId", userForm.getId());
        }
        queryPostList.setFirstResult(start);
        queryPostList.setMaxResults(recordsPerPage);

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

    @Override
    public void dbUpdatePost(Post updatePost) {
        this.sessionFactory.getCurrentSession().update(updatePost);

    }

    @Override
    public void dbAddPost(Post post, int currentUserId, Date date) {
        post.setStatus(1);
        post.setCreatedUserId(currentUserId);
        post.setUpdatedUserId(currentUserId);
        post.setCreatedAt(date);
        post.setUpdatedAt(date);
        this.sessionFactory.getCurrentSession().save(post);
    }

}
