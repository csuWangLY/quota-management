package com.project.quotamanagement.quotamanagement.model.converter;
import java.sql.Timestamp;

import com.project.quotamanagement.quotamanagement.controller.vo.QuotaAccountVO;
import com.project.quotamanagement.quotamanagement.model.QuotaAccount;

import java.util.List;
import java.util.stream.Collectors;

public class QuotaAccountConverter {

    public static List<QuotaAccountVO> models2vos(List<QuotaAccount> modelList) {
        return modelList.stream().map(QuotaAccountConverter::model2vo).collect(Collectors.toList());
    }

    public static QuotaAccountVO model2vo(QuotaAccount model) {
        QuotaAccountVO quotaAccountVO = new QuotaAccountVO();
        quotaAccountVO.setTntInstId(model.getTntInstId());
        quotaAccountVO.setGmtCreate(model.getGmtCreate());
        quotaAccountVO.setGmtModified(model.getGmtModified());
        quotaAccountVO.setAccountNo(model.getAccountNo());
        quotaAccountVO.setAccountType(model.getAccountType());
        quotaAccountVO.setUserId(model.getUserId());
        quotaAccountVO.setStatus(model.getStatus());
        quotaAccountVO.setCurrency(model.getCurrency());
        quotaAccountVO.setQuotaUpperLimit(model.getQuotaUpperLimit());
        quotaAccountVO.setUsedQuota(model.getUsedQuota());
        quotaAccountVO.setReserveQuota(model.getReserveQuota());

        return quotaAccountVO;
    }
}
