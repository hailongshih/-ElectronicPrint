package com.us.example.domain;

import java.math.BigInteger;
import java.util.List;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private String role;
    private String homeaddress;
    private BigInteger telephone;
    private String nearestembassy;
    private String username;
    private int status;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getHomeaddress() {
        return homeaddress;
    }

    public void setHomeaddress(String homeaddress) {
        this.homeaddress = homeaddress;
    }

    public BigInteger getTelephone() {
        return telephone;
    }

    public void setTelephone(BigInteger telephone) {
        this.telephone = telephone;
    }

    public String getNearestembassy() {
        return nearestembassy;
    }

    public void setNearestembassy(String nearestembassy) {
        this.nearestembassy = nearestembassy;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User(String email, String firstname, String lastname, String username, String password, String role, String homeaddress, BigInteger telephone, String nearestembassy, int status) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.role = role;
        this.homeaddress = homeaddress;
        this.telephone = telephone;
        this.nearestembassy = nearestembassy;
        this.username=username;
        this.status=status;
    }

    protected User() {
    }
}

