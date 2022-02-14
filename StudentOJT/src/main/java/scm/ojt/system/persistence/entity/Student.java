package scm.ojt.system.persistence.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity for Student
 */
/**
 * <h2>Student Class</h2>
 * <p>
 * Process for Displaying Student
 * </p>
 * 
 * @author WinNgwePhyo
 *
 */
@Entity
@Table(name = "student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String roll_number;
    private String name;
    private String major;
    private int year;
    private String email;
    private Date dob;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    /**
     * <h2>getId</h2>
     * <p>
     * Getter Method For Student ID
     * </p>
     * 
     * @return int
     */
    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    /**
     * <h2>setId</h2>
     * <p>
     * Setter Method for Student Id
     * </p>
     * 
     * @param id
     * @return void
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * <h2>getRoll_number</h2>
     * <p>
     * Getter Method For Student Roll Number
     * </p>
     * 
     * @return String
     */
    @Column(name = "roll_number")
    public String getRoll_number() {
        return roll_number;
    }

    /**
     * <h2>setRoll_number</h2>
     * <p>
     * Setter Method for Student Roll Number
     * </p>
     * 
     * @param roll_number
     * @return void
     */
    public void setRoll_number(String roll_number) {
        this.roll_number = roll_number;
    }

    /**
     * <h2>getName</h2>
     * <p>
     * Getter Method For Student Name
     * </p>
     * 
     * @return String
     */
    @Column(name = "name")
    public String getName() {
        return name;
    }

    /**
     * <h2>setName</h2>
     * <p>
     * Setter Method For Student name *
     * </p>
     * 
     * @param name return void
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <h2>getMajor</h2>
     * <p>
     * Getter Method for Student Major
     * </p>
     * 
     * @return
     * @return String
     */
    @Column(name = "major")
    public String getMajor() {
        return major;
    }

    /**
     * <h2>setMajor</h2>
     * <p>
     * Setter Method for Student Major
     * </p>
     * 
     * @param major
     * @return void
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * <h2>getYear</h2>
     * <p>
     * Getter Method For Student Attendance Year
     * </p>
     * 
     * @return int
     */
    @Column(name = "year")
    public int getYear() {
        return year;
    }

    /**
     * <h2>setYear</h2>
     * <p>
     * Setter Method For Student attendance year *
     * </p>
     * 
     * @param year return void
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * <h2>getEmail</h2>
     * <p>
     * Getter Method For Student Email
     * </p>
     * 
     * @return String
     */
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    /**
     * <h2>setEmail</h2>
     * <p>
     * Setter Method For Student email
     * </p>
     * 
     * @param email return void
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * <h2>getDob</h2>
     * <p>
     * Getter Method for student Date of Birth
     * </p>
     * 
     * @return Date
     */
    @Column(name = "dob")
    public Date getDob() {
        return dob;
    }

    /**
     * <h2>setDob</h2>
     * <p>
     * Setter Method For Student dob
     * </p>
     * 
     * @param dob return void
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * <h2>getCreatedAt</h2>
     * <p>
     * Getter Method For Student Created At
     * </p>
     * 
     * @return Date
     */
    @Column(name = "created_at")
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * <h2>setCreatedAt</h2>
     * <p>
     * Setter Method For Student createdAt
     * </p>
     * 
     * @param createdAt return void
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * <h2>getUpdatedAt</h2>
     * <p>
     * Getter Method For Student updatedAt
     * </p>
     * 
     * @return Date
     */
    @Column(name = "updated_at")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * <h2>setUpdatedAt</h2>
     * <p>
     * Setter Method For Post updatedAt
     * </p>
     * 
     * @param updatedAt return void
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * <h2>getDeletedAt</h2>
     * <p>
     * Getter Method For Student deletedAt
     * </p>
     * 
     * @return
     * @return Date
     */
    @Column(name = "deleted_at")
    public Date getDeletedAt() {
        return deletedAt;
    }

    /**
     * <h2>setDeletedAt</h2>
     * <p>
     * Setter Method For Student deletedAt *
     * </p>
     * 
     * @param deletedAt return void
     */
    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

}
