package scm.bulletin.board.system.bl.dto.user;

import java.io.Serializable;
import java.util.Date;

import scm.bulletin.board.system.persistence.entity.user.User;

/**
 * <h2>UserDTO Class</h2>
 * <p>
 * Process for Displaying UserDTO
 * </p>
 * 
 * @author WinNgwePhyo
 *
 */
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String email;
	private String password;
	private String profile;
	private String type;
	private String phone;
	private String address;
	private String dob;
	private Integer createdUserId;
	private Integer updatedUserId;
	private Integer deletedUserId;
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;

	/**
	 * <h2>Constructor for UserDTO</h2>
	 * <p>
	 * Constructor for UserDTO
	 * </p>
	 */
	public UserDTO() {
		super();
	}

	/**
	 * <h2>Constructor for UserDTO</h2>
	 * <p>
	 * Constructor for UserDTO
	 * </p>
	 * 
	 * @param user
	 */
	public UserDTO(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.profile = user.getProfile();
		this.type = user.getType();
		this.phone = user.getPhone();
		this.address = user.getAddress();
		this.dob = user.getDob();
		this.createdUserId = user.getCreatedUserId();
		this.updatedUserId = user.getUpdatedUserId();
		this.deletedUserId = user.getDeletedUserId();
		this.createdAt = user.getCreatedAt();
		this.updatedAt = user.getUpdatedAt();
		this.deletedAt = user.getDeletedAt();
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

	/**
	 * <h2>getName</h2>
	 * <p>
	 * Getter Method For User Name
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * <h2>setName</h2>
	 * <p>
	 * Setter Method For User Name
	 * </p>
	 *
	 * @param name
	 * @return void
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * <h2>getEmail</h2>
	 * <p>
	 * Getter Method For User Email
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
	 * <h2>getProfile</h2>
	 * <p>
	 * Getter Method For User Profile
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	public String getProfile() {
		return profile;
	}

	/**
	 * <h2>setProfile</h2>
	 * <p>
	 * Setter Method For User Profile
	 * </p>
	 *
	 * @param profile
	 * @return void
	 */
	public void setProfile(String profile) {
		this.profile = profile;
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
	 * <h2>getPhone</h2>
	 * <p>
	 * Getter Method For User Phone
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * <h2>setPhone</h2>
	 * <p>
	 * Setter Method For User Phone
	 * </p>
	 *
	 * @param phone
	 * @return void
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * <h2>getAddress</h2>
	 * <p>
	 * Getter Method For User Address
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * <h2>setAddress</h2>
	 * <p>
	 * Setter Method For User Address
	 * </p>
	 *
	 * @param address
	 * @return void
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * <h2>getDob</h2>
	 * <p>
	 * Getter Method For User Date Of Birth
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * <h2>setDob</h2>
	 * <p>
	 * Setter Method For User Date of Birth
	 * </p>
	 *
	 * @param dob
	 * @return void
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * <h2>getCreatedUserId</h2>
	 * <p>
	 * Getter Method For User Created User Id
	 * </p>
	 *
	 * @return
	 * @return Integer
	 */
	public Integer getCreatedUserId() {
		return createdUserId;
	}

	/**
	 * <h2>setCreatedUserId</h2>
	 * <p>
	 * Setter Method For User Created User Id
	 * </p>
	 *
	 * @param createdUserId
	 * @return void
	 */
	public void setCreatedUserId(Integer createdUserId) {
		this.createdUserId = createdUserId;
	}

	/**
	 * <h2>getUpdatedUserId</h2>
	 * <p>
	 * Getter Method For User Updated User Id
	 * </p>
	 *
	 * @return
	 * @return Integer
	 */
	public Integer getUpdatedUserId() {
		return updatedUserId;
	}

	/**
	 * <h2>setUpdatedUserId</h2>
	 * <p>
	 * Setter Method For User Updated User Id
	 * </p>
	 *
	 * @param updatedUserId
	 * @return void
	 */
	public void setUpdatedUserId(Integer updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	/**
	 * <h2>getDeletedUserId</h2>
	 * <p>
	 * Getter Method For Deleted User Id
	 * </p>
	 *
	 * @return
	 * @return Integer
	 */
	public Integer getDeletedUserId() {
		return deletedUserId;
	}

	/**
	 * <h2>setDeletedUserId</h2>
	 * <p>
	 * Setter Method For Deleted User Id
	 * </p>
	 *
	 * @param deletedUserId
	 * @return void
	 */
	public void setDeletedUserId(Integer deletedUserId) {
		this.deletedUserId = deletedUserId;
	}

	/**
	 * <h2>getCreatedAt</h2>
	 * <p>
	 * Getter Method For User Created Time
	 * </p>
	 *
	 * @return
	 * @return Date
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * <h2>setCreatedAt</h2>
	 * <p>
	 * Setter Method For User Created Time
	 * </p>
	 *
	 * @param createdAt
	 * @return void
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * <h2>getUpdatedAt</h2>
	 * <p>
	 * Getter Method For User Updated Time
	 * </p>
	 *
	 * @return
	 * @return Date
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * <h2>setUpdatedAt</h2>
	 * <p>
	 * Setter Method For User Updated Time
	 * </p>
	 *
	 * @param updatedAt
	 * @return void
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * <h2>getDeletedAt</h2>
	 * <p>
	 * Getter Method For User Deleted Time
	 * </p>
	 *
	 * @return
	 * @return Date
	 */
	public Date getDeletedAt() {
		return deletedAt;
	}

	/**
	 * <h2>setDeletedAt</h2>
	 * <p>
	 * Setter Method For User Deleted Time
	 * </p>
	 *
	 * @param deletedAt
	 * @return void
	 */
	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}
}
