package scm.bulletin.board.system.persistence.dao.user.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import scm.bulletin.board.system.bl.dto.search.SearchDTO;
import scm.bulletin.board.system.persistence.dao.user.UserDAO;
import scm.bulletin.board.system.persistence.entity.user.User;

/**
 * <h2>UserDAOImpl Class</h2>
 * <p>
 * Process for Displaying UserDAOImpl
 * </p>
 * 
 * @author WinNgwePhyo
 *
 */
@Repository
public class UserDAOImpl implements UserDAO   {
    /**
     * <h2>SELECT_USER_HQL</h2>
     * <p>
     * SELECT_USER_HQL
     * </p>
     */
    private static String SELECT_USER_HQL = "SELECT u FROM User u  where u.deletedAt is NULL ";

    private static String SELECT_USER_BY_ID_HQL = "SELECT u FROM User u where u.id = :id ";

    private static String SELECT_USER_BY_EMAIL_HQL = "SELECT u FROM User u WHERE u.email = :email ";
    /**
     * <h2>sessionFactory</h2>
     * <p>
     * sessionFactory
     * </p>
     */
    @Autowired
    private SessionFactory sessionFactory;
    /**
     * <h2>dbGetUserList</h2>
     * <p>
     * get all user from the database
     * </p>
     * 
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
    @Override
    public List<User> dbGetUserList() {
        Query queryUserList = this.sessionFactory.getCurrentSession().createQuery(SELECT_USER_HQL);
        return queryUserList.list();
    }
    /**
     * <h2>dbGetUserListWithLimit</h2>
     * <p>
     * 
     * </p>
     * 
     * @param currentPage
     * @param noOfUser
     * @param userForm
     * @return
     * @throws ParseException
     */
    @SuppressWarnings({ "deprecation", "rawtypes" })
    @Override
    public List<User> dbGetUserListWithLimit(int currentPage, int recordsPerPage, SearchDTO searchDTO) throws ParseException {
        int start = currentPage * recordsPerPage - recordsPerPage;
        StringBuffer strQuery = new StringBuffer(SELECT_USER_HQL);
        Query queryUserList = this.sessionFactory.getCurrentSession().createQuery(strQuery.toString());
        if (searchDTO != null) {
            if (searchDTO.getName() != null && !searchDTO.getName().isEmpty()) {
                strQuery.append("and u.name = :name ");
            }
            if (searchDTO.getEmail() != null && !searchDTO.getEmail().isEmpty()) {
                strQuery.append("and u.email = :email ");
            }
            if ((searchDTO.getCreatedFrom() != null && !searchDTO.getCreatedFrom().isEmpty())
                    && (searchDTO.getCreatedTo() != null && !searchDTO.getCreatedTo().isEmpty())) {
                strQuery.append("and u.createdAt between :createdFrom and :createdTo ");
            }
            queryUserList = this.sessionFactory.getCurrentSession().createQuery(strQuery.toString());
            if (searchDTO.getName() != null && !searchDTO.getName().isEmpty()) {
                queryUserList.setParameter("name", searchDTO.getName());
            }
            if (searchDTO.getEmail() != null && !searchDTO.getEmail().isEmpty()) {
                queryUserList.setParameter("email", searchDTO.getEmail());
            }
            if ((searchDTO.getCreatedFrom() != null && !searchDTO.getCreatedFrom().isEmpty())
                    && (searchDTO.getCreatedTo() != null && !searchDTO.getCreatedTo().isEmpty())) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");             
                Date from = sdf.parse(searchDTO.getCreatedFrom());
                Date to = sdf.parse(searchDTO.getCreatedTo());
                queryUserList.setParameter("createdFrom", from);
                queryUserList.setParameter("createdTo", to);
              
            }
        }
       
        queryUserList.setFirstResult(start);
        queryUserList.setMaxResults(recordsPerPage);
        @SuppressWarnings("unchecked")
        List<User> userList = (List<User>) queryUserList.list();
        return userList;
    }
    @SuppressWarnings("deprecation")
    @Override
    public User dbGetUserbyID(Integer userId) {
        Query queryUserById = this.sessionFactory.getCurrentSession().createQuery(SELECT_USER_BY_ID_HQL);
        queryUserById.setParameter("id", userId);
        User resultUser = (User) queryUserById.uniqueResult();
        return resultUser;
    }
    
    /**
     * <h2>dbGetUserByEmail</h2>
     * <p>
     * Get Email From The Database
     * </p>
     * 
     * @param email
     * @return User
     */
    @SuppressWarnings("deprecation")
    @Override
    public User dbGetUserByEmail(String email) {
        Query queryUserByEmail = this.sessionFactory.getCurrentSession().createQuery(SELECT_USER_BY_EMAIL_HQL);
        queryUserByEmail.setParameter("email", email);
        User user = (User) queryUserByEmail.uniqueResult();

        return user;
    }
    /**
     * <h2>dbInsertUser</h2>
     * <p>
     * 
     * </p>
     * 
     * @param user
     * @param currentUserId
     * @param currentDate
     */
    @Override
    public void dbInsertUser(User user, int createUserId, Date currentDate) {
        user.setCreatedUserId(createUserId);
        user.setUpdatedUserId(createUserId);
        user.setCreatedAt(currentDate);
        user.setUpdatedAt(currentDate);
        this.sessionFactory.getCurrentSession().save(user);

    }
    /**
     * <h2>dbUpdatedUserExistList</h2>
     * <p>
     * Update User Email Exit or Not Exit
     * </p>
     * 
     * @param email
     * @return
     */
    @SuppressWarnings("deprecation")
    @Override
    public List<User> dbUpdatedUserExistList(String email) {
        Query queryUser = this.sessionFactory.getCurrentSession().createQuery(SELECT_USER_BY_EMAIL_HQL);
        queryUser.setParameter("email", email);

        @SuppressWarnings("unchecked")
        List<User> userList = (List<User>) queryUser.list();
        return userList;
    }
    /**
     * <h2>dbUpdateUser</h2>
     * <p>
     * Update User To the Database
     * </p>
     * 
     * @param updatedUser
     */
    @Override
    public void dbUpdateUser(User updatedUser) {
        this.sessionFactory.getCurrentSession().update(updatedUser);
    }
}
