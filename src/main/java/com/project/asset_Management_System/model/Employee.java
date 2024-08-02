package com.project.asset_Management_System.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Column(unique = true)
    private String employeeCode;

    @NotNull
    private String name;

    @NotNull
    private String position;

    @NotNull
    private String department;

    @NotNull
    @Column(unique = true)
    private int phoneNumber;

    private boolean isDeleted = Boolean.FALSE;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted( boolean deleted) {
        isDeleted = deleted;
    }

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @NotNull String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(@NotNull String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public @NotNull String getPosition() {
        return position;
    }

    public void setPosition(@NotNull String position) {
        this.position = position;
    }

    public @NotNull String getDepartment() {
        return department;
    }

    public void setDepartment(@NotNull String department) {
        this.department = department;
    }

    @NotNull
    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotNull int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
