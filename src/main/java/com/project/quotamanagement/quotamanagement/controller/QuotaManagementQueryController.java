package com.project.quotamanagement.quotamanagement.controller;

import com.project.quotamanagement.quotamanagement.controller.vo.QuotaQueryRequest;
import com.project.quotamanagement.quotamanagement.controller.vo.QuotaQueryResponse;
import com.project.quotamanagement.quotamanagement.controller.vo.base.CommonResult;
import com.project.quotamanagement.quotamanagement.controller.vo.UserAccountVO;
import com.project.quotamanagement.quotamanagement.model.converter.UserAccountConverter;
import com.project.quotamanagement.quotamanagement.service.UserAccountRepository;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 额度管理查询模块
 */
@Controller
@RequestMapping("/query")
public class QuotaManagementQueryController {

    private static Logger LOGGER = LoggerFactory.getLogger(QuotaManagementQueryController.class);

    @Autowired
    private UserAccountRepository userAccountRepository;

    /**
     * 账户额度查询
     *
     * @param request 账户额度查询请求
     * @return 账户额度查询结果
     */
    @ResponseBody
    @RequestMapping(value = "/quotaQuery", method = RequestMethod.POST)
    public ResponseEntity<QuotaQueryResponse> quotaQuery(QuotaQueryRequest request) {
        QuotaQueryResponse result = new QuotaQueryResponse();

        // 请求校验
        if (!quotaQueryRequestCheck(request)) {
            result.setSuccess(false);
            result.setRetriable(false);

            // to do 身份校验
            return ResponseEntity.ok(result);
        }

        LOGGER.info("额度查询开始，查询账户id = {}", request.getUserAccountId());

        // 查询
        UserAccountVO userAccountVO = UserAccountConverter.model2vo(
                userAccountRepository.accountQuery(request.getUserAccountId(), request.getQuotaAccountType()));
        result.setUserAccountVO(userAccountVO);

        return ResponseEntity.ok(result);
    }

    /**
     * 检查请求
     *
     * @param request 请求
     */
    private boolean quotaQueryRequestCheck(QuotaQueryRequest request) {
        if (request.getUserAccountId() <= 0 || Strings.isBlank(request.getOperatorId())) {

            LOGGER.error("请求不符合预期");
            return false;
        }

        return true;
    }
}
