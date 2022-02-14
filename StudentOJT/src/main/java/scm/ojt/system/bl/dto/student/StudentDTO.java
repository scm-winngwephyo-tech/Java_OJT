package scm.ojt.system.bl.dto.student;

import java.sql.Date;

/**
 * <h2>StudentDTO Class</h2>
 * <p>
 * Process for Displaying StudentDTO
 * </p>
 * 
 * @author WinNgwePhyo
 *
 */
public class StudentDTO {

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
     * Getter method for get student Id
     * </p>
     * 
     * @return int
     */

    public int getId() {
        return id;
    }

    /**
     * <h2>setId</h2>
     * <p>
     * Setter method for Student Id
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
     * Getter mmethod for Student Roll Nummber
     * </p>
     * 
     * @return String
     */
    public String getRoll_number() {
        return roll_number;
    }

    /**
     * <h2>setRoll_number</h2>
     * <p>
     * Setter Method For Student Roll Number
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
     * @return
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * <h2>setName</h2>
     * <p>
     * Setter Method For Student Name
     * </p>
     *
     * @param name
     * @return void
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <h2>getMajor</h2>
     * <p>
     * Getter Method For Student major
     * </p>
     *
     * @return
     * @return String
     */
    public String getMajor() {
        return major;
    }

    /**
     * <h2>setMajor</h2>
     * <p>
     * Setter Method For Student Major
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
     * Getter Method For Student attendance Year
     * </p>
     * 
     * @return int
     */
    public int getYear() {
        return year;
    }

    /**
     * <h2>setYear</h2>
     * <p>
     * Setter Method For Student Attendance Year
     * </p>
     *
     * @param year
     * @return void
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * <h2>getEmail</h2>
     * <p>
     * Getter Method For Student EMail
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
     * Setter Method For Student Email
     * </p>
     *
     * @param email
     * @return void
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * <h2>getDob</h2>
     * <p>
     * Getter Method For Student Date of birth
     * </p>
     *
     * @return
     * @return Date
     */
    public Date getDob() {
        return dob;
    }

    /**
     * <h2>setDob</h2>
     * <p>
     * Setter Method For Student Date of birth
     * </p>
     *
     * @param dob
     * @return void
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * <h2>getCreatedAt</h2>
     * <p>
     * Getter Method For Student Created Date
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
     * Setter Method For Student Created Date
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
     * Getter Method For Student Updated date
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
     * Setter Method For Student Updated Date
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
     * 
     * </p>
     *
     * @return
     * @return Date
     */
    /**
     * <h2>getDeletedAt</h2>
     * <p>
     * Getter Method For Student Deleted Date
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
     * Setter Method For Student deleted Date
     * </p>
     *
     * @param deletedAt
     * @return void
     */
    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
}
