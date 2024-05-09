package com.project.quotamanagement.quotamanagement.controller;

import com.project.quotamanagement.quotamanagement.controller.vo.QuotaQueryRequest;
import com.project.quotamanagement.quotamanagement.controller.vo.QuotaQueryResponse;
import com.project.quotamanagement.quotamanagement.controller.vo.UserVO;
import com.project.quotamanagement.quotamanagement.controller.vo.base.CommonResult;
import com.project.quotamanagement.quotamanagement.mapper.dos.UserDO;
import com.project.quotamanagement.quotamanagement.model.User;
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

import java.util.Objects;


/**
 * 额度管理查询模块
 */
@Controller
@RequestMapping("/query")
public class QuotaManagementQueryController extends CommonController  {

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

        execute(new ControllerTemplate() {
            @Override
            public void check() throws Exception {
                // 请求校验
                if (!quotaQueryRequestCheck(request)) {
                    throw new Exception("参数校验失败");
                }
            }

            @Override
            public void execute() throws Exception {
                LOGGER.info("额度查询开始，查询账户id = {}", request.getUserId());

                // 查询
                User user = userAccountRepository.userQuery(request.getUserId(), request.getQuotaAccountType());
                UserVO userVO = Objects.isNull(user) ? null : UserAccountConverter.model2vo(user);
                result.setUserVO(userVO);

            }
        }, result);

        return ResponseEntity.ok(result);
    }

    /**
     * 检查请求
     *
     * @param request 请求
     */
    private boolean quotaQueryRequestCheck(QuotaQueryRequest request) {
        if (request.getUserId() <= 0 || Strings.isBlank(request.getOperatorId())) {

            LOGGER.error("请求不符合预期");
            return false;
        }

        return true;
    }
}
