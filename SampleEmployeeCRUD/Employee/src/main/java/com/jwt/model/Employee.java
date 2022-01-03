package com.jwt.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <h2> Employee Class</h2>
 * <p>
 * Process for Displaying Employee
 * </p>
 * 
 * @author Lwin Mar Win-PC
 *
 */
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = -3465813074586302847L;

    /**
     * <h2> id</h2>
     * <p>
     * id
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * <h2> name</h2>
     * <p>
     * name
     * </p>
     */
    @Column
    private String name;

    /**
     * <h2> salary</h2>
     * <p>
     * salary
     * </p>
     */
    @Column
    private int salary;

    /**
     * <h2> position</h2>
     * <p>
     * position
     * </p>
     */
    @Column
    private String position;

    /**
     * <h2> getId</h2>
     * <p>
     * getId
     * </p>
     *
     * @return
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * <h2> setId</h2>
     * <p>
     * setId
     * </p>
     *
     * @param id
     * @return void
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * <h2> getName</h2>
     * <p>
     * getName
     * </p>
     *
     * @return
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * <h2> setName</h2>
     * <p>
     * setName
     * </p>
     *
     * @param name
     * @return void
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <h2> getSalary</h2>
     * <p>
     * getSalary
     * </p>
     *
     * @return
     * @return int
     */
    public int getSalary() {
        return salary;
    }

    /**
     * <h2> setSalary</h2>
     * <p>
     * setSalary
     * </p>
     *
     * @param salary
     * @return void
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * <h2> getPosition</h2>
     * <p>
     * getPosition
     * </p>
     *
     * @return
     * @return String
     */
    public String getPosition() {
        return position;
    }

    /**
     * <h2> setPosition</h2>
     * <p>
     * setPosition
     * </p>
     *
     * @param position
     * @return void
     */
    public void setPosition(String position) {
        this.position = position;
    }

}