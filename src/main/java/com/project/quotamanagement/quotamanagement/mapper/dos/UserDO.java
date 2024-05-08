package com.project.quotamanagement.quotamanagement.mapper.dos;

import java.util.Date;

public class UserDO {
    private Long id;

    private String tntInstId;

    private Date gmtCreate;

    private Date gmtModified;

    private String name;

    private String email;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTntInstId() {
        return tntInstId;
    }

    public void setTntInstId(String tntInstId) {
        this.tntInstId = tntInstId == null ? null : tntInstId.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}