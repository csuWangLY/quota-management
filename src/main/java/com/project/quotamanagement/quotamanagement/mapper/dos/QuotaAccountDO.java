package com.project.quotamanagement.quotamanagement.mapper.dos;

import java.util.Date;

public class QuotaAccountDO {
    private Long id;

    private String tntInstId;

    private Date gmtCreate;

    private Date gmtModified;

    private String accountNo;

    private String accountType;

    private Long userId;

    private String status;

    private String currency;

    private Long quotaUpperLimit;

    private Long usedQuota;

    private Long reserveQuota;

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

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType == null ? null : accountType.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public Long getQuotaUpperLimit() {
        return quotaUpperLimit;
    }

    public void setQuotaUpperLimit(Long quotaUpperLimit) {
        this.quotaUpperLimit = quotaUpperLimit;
    }

    public Long getUsedQuota() {
        return usedQuota;
    }

    public void setUsedQuota(Long usedQuota) {
        this.usedQuota = usedQuota;
    }

    public Long getReserveQuota() {
        return reserveQuota;
    }

    public void setReserveQuota(Long reserveQuota) {
        this.reserveQuota = reserveQuota;
    }
}