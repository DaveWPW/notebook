package com.dave.notebook.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "nb_user")
public class User {
    @Id
    @Column(name = "user_id")
    private Integer userId;
    private String username;
    private String realname;
    private String password;
    @Column(name = "password_salt")
    private String passwordSalt;
    @Column(name = "role_id")
    private Integer roleId;
    private Integer status;
    @Column(name = "create_user")
    private String createUser;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "modify_user")
    private String modifyUser;
    @Column(name = "modify_time")
    private Date modifyTime;
    @Transient
    private Integer isRestPassword;

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getRealname() {
        return realname;
    }
    public void setRealname(String realname) {
        this.realname = realname;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPasswordSalt() {
        return passwordSalt;
    }
    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getCreateUser() {
        return createUser;
    }
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getModifyUser() {
        return modifyUser;
    }
    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }
    public Date getModifyTime() {
        return modifyTime;
    }
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    public Integer getIsRestPassword() {
        return isRestPassword;
    }
    public void setIsRestPassword(Integer isRestPassword) {
        this.isRestPassword = isRestPassword;
    }

}