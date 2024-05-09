package com.project.quotamanagement.quotamanagement;

import com.project.quotamanagement.quotamanagement.controller.QuotaManagementCommandController;
import com.project.quotamanagement.quotamanagement.controller.vo.OccupiedQuotaRequest;
import com.project.quotamanagement.quotamanagement.controller.vo.base.CommonResult;
import com.project.quotamanagement.quotamanagement.mapper.AccountOrderDOMapper;
import com.project.quotamanagement.quotamanagement.mapper.QuotaAccountDOMapper;
import com.project.quotamanagement.quotamanagement.mapper.dos.AccountOrderDO;
import com.project.quotamanagement.quotamanagement.mapper.dos.QuotaAccountDO;
import com.project.quotamanagement.quotamanagement.service.AccountOrderService;
import com.project.quotamanagement.quotamanagement.service.QuotaCommandService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.Assert;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@SpringBootTest
class QuotamanagementApplicationTests {

    @Autowired
    private QuotaManagementCommandController quotaManagementCommandController;

    @Autowired
    private AccountOrderService accountOrderService;

    @Autowired
    private QuotaCommandService quotaCommandService;

	@Test
	void contextLoads() {
	}

    /**
     *  0. 成功-幂等
     *  1. 成功-正常请求，额度占用正常
     *  2. 失败-请求校验异常
     *  4. 失败-消费额度大于可用额度
     *  5. 失败-单据插入幂等
     *
     */
	@Test
	void test_occupied_quota_success() {
        long current = Timestamp.valueOf(LocalDateTime.now()).getTime();

        OccupiedQuotaRequest request = new OccupiedQuotaRequest();
        request.setUserId(1);
        request.setAccountNo("10001");
        request.setOccupiedQuota(10);
        request.setOutBizNo("10001");
        request.setOperatorId("wly");

        QuotaAccountDOMapper quotaAccountDOMapper = Mockito.mock(QuotaAccountDOMapper.class);
        AccountOrderDOMapper accountOrderDOMapper = Mockito.mock(AccountOrderDOMapper.class);

        Mockito.reset(quotaAccountDOMapper);
        Mockito.reset(accountOrderDOMapper);


        QuotaAccountDO quotaAccountDO = new QuotaAccountDO();
        quotaAccountDO.setReserveQuota(10L);
        quotaAccountDO.setUsedQuota(10L);
        quotaAccountDO.setQuotaUpperLimit(20L);


        Mockito.when(accountOrderDOMapper.getByOutBizNo(Mockito.any())).thenReturn(new AccountOrderDO());
        Mockito.when(quotaAccountDOMapper.updateByPrimaryKey(Mockito.any())).thenReturn(1);
        Mockito.when(quotaAccountDOMapper.lockByAccountNo(Mockito.any())).thenReturn(quotaAccountDO);
        Mockito.when(accountOrderDOMapper.insert(Mockito.any())).thenReturn(1);

        ReflectionTestUtils.setField(accountOrderService, "accountOrderDOMapper", accountOrderDOMapper);
        ReflectionTestUtils.setField(quotaCommandService, "quotaAccountDOMapper", quotaAccountDOMapper);
        ReflectionTestUtils.setField(quotaCommandService, "quotaAccountDOMapper", quotaAccountDOMapper);


        ResponseEntity<CommonResult> resultResponseEntity = quotaManagementCommandController.occupiedQuota(request);

        Assert.isTrue(Objects.requireNonNull(resultResponseEntity.getBody()).isSuccess());
    }

    /**
     * 额度释放：
     *  0. 成功-幂等
     *  1. 成功-正常请求，额度释放正常
     *  2. 失败-请求校验异常
     *  4. 失败-此次释放额度大于已占用额度
     *  5. 失败-单据插入幂等
     */

    /**
     * 额度申请：
     * 0. 成功-无此类账户，新增
     * 1. 成功- 有此类账户，修改
     *  2. 失败-请求校验异常
     *  3， 失败-传入用户id无效
     *  4. 失败-账户和用户id不匹配
     *  5. 失败-额度账户调额度失败，已占用额度大于调整后总额度
     *  6. 失败-额度账户加锁失败
     */

}
