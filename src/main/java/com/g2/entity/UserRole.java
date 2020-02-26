package com.g2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user_role", uniqueConstraints = @UniqueConstraint(columnNames = { "role", "userName" }))
public class UserRole {

    @Id
    @GeneratedValue
    @Column(name = "user_role_id", unique = true, nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userName", nullable = false)
    private User user;

    @Column(name = "role", nullable = false, length = 45)
    private String role;

    public UserRole() {
    }

    public UserRole(int id, User user, String role) {
        super();
        this.id = id;
        this.user = user;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
