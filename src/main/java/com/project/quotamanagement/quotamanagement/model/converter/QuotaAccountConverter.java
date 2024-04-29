package com.project.quotamanagement.quotamanagement.model.converter;

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
        quotaAccountVO.setQuotaAccountNo(model.getQuotaAccountNo());
        quotaAccountVO.setQuotaAccountType(model.getQuotaAccountType());
        quotaAccountVO.setAvailableQuota(model.getAvailableQuota());
        quotaAccountVO.setUserAccountId(model.getUserAccountId());
        quotaAccountVO.setOccupiedQuota(model.getOccupiedQuota());
        quotaAccountVO.setTotalQuota(model.getTotalQuota());

        return quotaAccountVO;
    }
}
