package scm.bulletin.board.system.bl.dto.login;

import java.io.Serializable;

import scm.bulletin.board.system.persistence.entity.user.User;

/**
 * <h2>LoginDto Class</h2>
 * <p>
 * Process for Displaying LoginDto
 * </p>
 * 
 * @author WinNgwePhyo
 *
 */
public class LoginDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String email;

    private String password;

    private String type;

    private int id;

    /**
     * <h2>Constructor for LoginDTO</h2>
     * <p>
     * Constructor for LoginDTO
     * </p>
     */
    public LoginDTO() {

    }

    /**
     * <h2>Constructor for LoginDTO</h2>
     * <p>
     * Constructor for LoginDTO
     * </p>
     * 
     * @param user
     */
    public LoginDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    /**
     * <h2>getEmail</h2>
     * <p>
     * Getter method for User Email
     * </p>
     *
     * @return
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * <h2>setEmail</h2>
     * <p>
     * Setter Method For User Email
     * </p>
     *
     * @param email
     * @return void
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * <h2>getPassword</h2>
     * <p>
     * Getter Method For User Password
     * </p>
     *
     * @return
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * <h2>setPassword</h2>
     * <p>
     * Setter Method For User Password
     * </p>
     *
     * @param password
     * @return void
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * <h2>getType</h2>
     * <p>
     * Getter Method For User Type
     * </p>
     *
     * @return
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * <h2>setType</h2>
     * <p>
     * Setter Method For User Type
     * </p>
     *
     * @param type
     * @return void
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * <h2>getId</h2>
     * <p>
     * Getter Method For User ID
     * </p>
     *
     * @return
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * <h2>setId</h2>
     * <p>
     * Setter Method For User ID
     * </p>
     *
     * @param id
     * @return void
     */
    public void setId(int id) {
        this.id = id;
    }
}
