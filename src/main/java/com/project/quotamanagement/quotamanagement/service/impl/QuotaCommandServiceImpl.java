package com.project.quotamanagement.quotamanagement.service.impl;

import com.project.quotamanagement.quotamanagement.mapper.QuotaAccountDOMapper;
import com.project.quotamanagement.quotamanagement.mapper.UserDOMapper;
import com.project.quotamanagement.quotamanagement.mapper.dos.QuotaAccountDO;
import com.project.quotamanagement.quotamanagement.mapper.dos.UserDO;
import com.project.quotamanagement.quotamanagement.model.AccountOrder;
import com.project.quotamanagement.quotamanagement.model.QuotaAccount;
import com.project.quotamanagement.quotamanagement.model.constants.QuotaManagementConstants;
import com.project.quotamanagement.quotamanagement.model.enums.AccountOrderStatusEnum;
import com.project.quotamanagement.quotamanagement.model.enums.AccountOrderTypeEnum;
import com.project.quotamanagement.quotamanagement.model.enums.QuotaAccountStatusEnum;
import com.project.quotamanagement.quotamanagement.service.AccountOrderService;
import com.project.quotamanagement.quotamanagement.service.QuotaCommandService;
import com.project.quotamanagement.quotamanagement.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class QuotaCommandServiceImpl implements QuotaCommandService {

    private static Logger LOGGER = LoggerFactory.getLogger(QuotaCommandServiceImpl.class);

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private QuotaAccountDOMapper quotaAccountDOMapper;

    @Autowired
    private AccountOrderService accountOrderService;

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public void applyQuota(long userId, String quotaAccountType, long totalQuota, String accountNo) throws Exception {

        // 锁账户
        UserDO userDO = userDOMapper.lockById(userId);

        if (Objects.isNull(userDO)) {
            LOGGER.error("传入的用户id无效");
            throw new Exception("传入的用户id无效");
        }

        // 检查当前是否有此类额度
        QuotaAccountDO quotaAccountDO = quotaAccountDOMapper.getByAccountNo(accountNo);

        if (Objects.isNull(quotaAccountDO)) {

            // 直接新增
            addQuotaAccount(userId, quotaAccountType, totalQuota, accountNo);
        } else {

            // 检查是否是该用户的账户
            if (quotaAccountDO.getUserId() != userId) {
                LOGGER.error("该账户和用户id不匹配！");
                throw new Exception("该账户和用户id不匹配！");
            }

            // 已有，修改额度
            modifyQuotaAccount(userId, accountNo, totalQuota);
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public void occupiedQuota(long userId, String accountNo, long usedQuota, String outBizNo) throws Exception {
        // 幂等判断
        if (accountOrderService.judgeIdempotent(outBizNo)) {
            LOGGER.info("幂等，直接返回成功");

            // 幂等，直接返回成功
            return;
        }

        // 锁额度账户
        QuotaAccount quotaAccount = lockQuotaAccount(accountNo);

        if (usedQuota > quotaAccount.getReserveQuota()) {
            LOGGER.error("此次消费额度大于可用额度，交易失败");
            throw new Exception("此次消费额度大于可用额度，交易失败");
        }

        quotaAccount.setGmtModified(Timestamp.valueOf(LocalDateTime.now()));
        quotaAccount.setReserveQuota(quotaAccount.getReserveQuota() - usedQuota);
        quotaAccount.setUsedQuota(quotaAccount.getUsedQuota() + usedQuota);

        updateQuotaAccount(quotaAccount);

        // 创造单据
        accountOrderService.createAccountOrder(buildOrder(quotaAccount, AccountOrderTypeEnum.OCCUPIED, usedQuota, outBizNo));
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public void releaseQuota(long userId, String accountNo, long restoredQuota, String outBizNo) throws Exception {
        // 幂等判断
        if (accountOrderService.judgeIdempotent(outBizNo)) {
            // 幂等，直接返回成功
            return;
        }

        // 锁额度账户
        QuotaAccount quotaAccount = lockQuotaAccount(accountNo);


        if (restoredQuota > quotaAccount.getUsedQuota()) {
            LOGGER.error("此次释放额度大于已占用额度，交易失败");
            throw new Exception("此次释放额度大于已占用额度，交易失败");
        }

        quotaAccount.setGmtModified(Timestamp.valueOf(LocalDateTime.now()));
        quotaAccount.setUsedQuota(quotaAccount.getUsedQuota() - restoredQuota);
        quotaAccount.setReserveQuota(quotaAccount.getReserveQuota() + restoredQuota);

        updateQuotaAccount(quotaAccount);

        // 创造单据
        accountOrderService.createAccountOrder(buildOrder(quotaAccount, AccountOrderTypeEnum.RELEASE, restoredQuota, outBizNo));
    }

    private AccountOrder buildOrder(QuotaAccount quotaAccount, AccountOrderTypeEnum accountOrderType, long tradeAmount, String outBizNo) {
        AccountOrder accountOrder = new AccountOrder();

        // 获取当前日期
        LocalDateTime currentDate = LocalDateTime.now();

        accountOrder.setTntInstId(QuotaManagementConstants.DEFAULT_TNT_INST_ID);
        accountOrder.setGmtCreate(Timestamp.valueOf(LocalDateTime.now()));
        accountOrder.setGmtModified(Timestamp.valueOf(LocalDateTime.now()));
        accountOrder.setAccountNo(quotaAccount.getAccountNo());
        accountOrder.setUserId(quotaAccount.getUserId());
        accountOrder.setTradeType(accountOrderType.getCode());
        accountOrder.setTradeAmount(tradeAmount);
        accountOrder.setStatus(AccountOrderStatusEnum.SUCCESS.getCode());
        accountOrder.setOutBizNo(outBizNo);
        accountOrder.setBizTime(Timestamp.valueOf(currentDate));
        accountOrder.setBizDate(DateUtil.getCurrentDateAsYYYYMMDD(currentDate));
        accountOrder.setAccountType(quotaAccount.getAccountType());
        accountOrder.setCurrency(quotaAccount.getCurrency());

        return accountOrder;
    }

    private QuotaAccount lockQuotaAccount(String accountNo) throws Exception {
        QuotaAccountDO quotaAccountDO = quotaAccountDOMapper.lockByAccountNo(accountNo);
        if (Objects.isNull(quotaAccountDO)) {
            throw new Exception("额度账户加锁失败");
        }

        QuotaAccount quotaAccount = new QuotaAccount();
        BeanUtils.copyProperties(quotaAccountDO, quotaAccount);

        return quotaAccount;
    }

    private void modifyQuotaAccount(long userAccountId, String accountNo, long totalQuota) throws Exception {
        QuotaAccount quotaAccount = lockQuotaAccount(accountNo);


        // 如果可用额度小于要变动的值，则变动失败
        if (quotaAccount.getUsedQuota() > totalQuota) {
            throw new Exception("额度账户调额度失败，已占用额度大于调整后总额度");
        }

        quotaAccount.setGmtModified(Timestamp.valueOf(LocalDateTime.now()));
        quotaAccount.setQuotaUpperLimit(totalQuota);
        quotaAccount.setReserveQuota(totalQuota - quotaAccount.getUsedQuota());

        updateQuotaAccount(quotaAccount);
    }

    private void updateQuotaAccount(QuotaAccount quotaAccount) {

        QuotaAccountDO quotaAccountDO = new QuotaAccountDO();
        BeanUtils.copyProperties(quotaAccount, quotaAccountDO);
        quotaAccountDOMapper.updateByPrimaryKey(quotaAccountDO);

    }

    private void addQuotaAccount(long userId, String quotaAccountType, long totalQuota, String accountNo) {

        QuotaAccountDO quotaAccount = new QuotaAccountDO();
        quotaAccount.setTntInstId(QuotaManagementConstants.DEFAULT_TNT_INST_ID);
        quotaAccount.setGmtCreate(Timestamp.valueOf(LocalDateTime.now()));
        quotaAccount.setGmtModified(Timestamp.valueOf(LocalDateTime.now()));
        quotaAccount.setAccountNo(accountNo);
        quotaAccount.setAccountType(quotaAccountType);
        quotaAccount.setUserId(userId);
        quotaAccount.setStatus(QuotaAccountStatusEnum.EFFECTIVE.getCode());
        quotaAccount.setCurrency(QuotaManagementConstants.DEFAULT_CURRENCY);
        quotaAccount.setQuotaUpperLimit(totalQuota);
        quotaAccount.setUsedQuota(QuotaManagementConstants.DEFAULT_USED_QUOTA);
        quotaAccount.setReserveQuota(totalQuota);

        quotaAccountDOMapper.insert(quotaAccount);
    }
}
