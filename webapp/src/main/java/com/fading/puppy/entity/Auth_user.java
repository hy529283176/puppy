package com.fading.puppy.entity;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
@Entity
@Table(name="auth_user")
public class Auth_user implements Serializable {
    @Id
    @Column(name="id")
    private Integer id;

    @Basic
    @Column(name="username")
    private String username;

    @Basic
    @Column(name="pwd")
    private String pwd;

    @Basic
    @Column(name="role_id")
    private Integer role_id;

    @Basic
    @Column(name="uName")
    private String uName;

    @Basic
    @Column(name="enStatus")
    private Integer enStatus;

    @Basic
    @Column(name="visible")
    private Boolean visible;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public Integer getEnStatus() {
        return enStatus;
    }

    public void setEnStatus(Integer enStatus) {
        this.enStatus = enStatus;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
