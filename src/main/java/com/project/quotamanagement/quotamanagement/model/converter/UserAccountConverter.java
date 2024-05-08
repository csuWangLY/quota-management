package com.project.quotamanagement.quotamanagement.model.converter;

import com.project.quotamanagement.quotamanagement.controller.vo.UserVO;
import com.project.quotamanagement.quotamanagement.model.User;

public class UserAccountConverter {

    public static UserVO model2vo(User model) {

        UserVO userVO = new UserVO();
        userVO.setAccountName(model.getName());
        userVO.setEmail(model.getEmail());
        userVO.setId(model.getId());
        userVO.setQuotaAccountVOList(QuotaAccountConverter.models2vos(model.getQuotaAccountList()));

        return userVO;
    }
}
