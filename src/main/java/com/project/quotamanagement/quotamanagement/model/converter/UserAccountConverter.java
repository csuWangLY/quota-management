package com.project.quotamanagement.quotamanagement.model.converter;

import com.project.quotamanagement.quotamanagement.controller.vo.UserAccountVO;
import com.project.quotamanagement.quotamanagement.model.UserAccount;

public class UserAccountConverter {

    public static UserAccountVO model2vo(UserAccount model) {

        UserAccountVO userAccountVO = new UserAccountVO();
        userAccountVO.setAccountName(model.getAccountName());
        userAccountVO.setEmail(model.getEmail());
        userAccountVO.setId(model.getId());
        userAccountVO.setQuotaAccountVOList(QuotaAccountConverter.models2vos(model.getQuotaAccountList()));

        return userAccountVO;
    }
}
