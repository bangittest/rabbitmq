package com.bn.rabbitmq.messaging;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.util.Set;

/**
 * A user.
 */
@Entity
@Table(name = "`user`")
public class User {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "status")
    private int status;

    @Column(name = "is_active")
    private int isActive;

    @Column(name = "user_name", nullable = false)
    private String username;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "full_name", length = 255)
    private String fullName;

    @Column(name = "phone_number", length = 10)
    private String phoneNumber;

    @Column(name = "gender")
    private int gender;

    @Column(name = "note", length = 255)
    private String note;

    public User(Long id, String code, int status, int isActive, String username, String email, String password, String fullName, String phoneNumber, int gender, String note) {
        this.id = id;
        this.code = code;
        this.status = status;
        this.isActive = isActive;
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.note = note;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
