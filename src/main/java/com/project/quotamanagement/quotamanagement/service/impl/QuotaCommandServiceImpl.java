package com.project.quotamanagement.quotamanagement.service.impl;

import com.project.quotamanagement.quotamanagement.mapper.QuotaAccountMapper;
import com.project.quotamanagement.quotamanagement.mapper.UserMapper;
import com.project.quotamanagement.quotamanagement.model.AccountOrder;
import com.project.quotamanagement.quotamanagement.model.QuotaAccount;
import com.project.quotamanagement.quotamanagement.model.User;
import com.project.quotamanagement.quotamanagement.model.constants.QuotaManagementConstants;
import com.project.quotamanagement.quotamanagement.model.enums.AccountOrderStatusEnum;
import com.project.quotamanagement.quotamanagement.model.enums.AccountOrderTypeEnum;
import com.project.quotamanagement.quotamanagement.model.enums.QuotaAccountStatusEnum;
import com.project.quotamanagement.quotamanagement.service.AccountOrderService;
import com.project.quotamanagement.quotamanagement.service.QuotaCommandService;
import com.project.quotamanagement.quotamanagement.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public class QuotaCommandServiceImpl implements QuotaCommandService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuotaAccountMapper quotaAccountMapper;

    @Autowired
    private AccountOrderService accountOrderService;

    @Override
    @Transactional
    public void applyQuota(long userId, String quotaAccountType, long totalQuota, String accountNo) throws Exception {

        // 锁账户
        User user = userMapper.lockById(userId);
        if (Objects.isNull(user)) {
            throw new Exception("传入的用户id无效");
        }

        // 检查当前是否有此类额度
        QuotaAccount quotaAccount = quotaAccountMapper.getByAccountNo(accountNo);

        if (Objects.isNull(quotaAccount)) {

            // 直接新增
            addQuotaAccount(userId, quotaAccountType, totalQuota, accountNo);
        } else {

            // 检查是否是该用户的账户
            if (quotaAccount.getUserId() != userId) {
                throw new Exception("该账户和用户id不匹配！");
            }

            // 已有，修改额度
            modifyQuotaAccount(userId, accountNo, totalQuota);
        }
    }


    @Override
    @Transactional
    public void occupiedQuota(long userId, String accountNo, long usedQuota, String outBizNo) throws Exception {
        // 幂等判断
        if (accountOrderService.judgeIdempotent(outBizNo)) {
            // 幂等，直接返回成功
            return;
        }

        // 锁额度账户
        QuotaAccount quotaAccount = lockQuotaAccount(accountNo);

        if (usedQuota > quotaAccount.getReserveQuota()) {
            throw new Exception("此次消费额度大于可用额度，交易失败");
        }

        quotaAccount.setReserveQuota(quotaAccount.getReserveQuota() + usedQuota);
        quotaAccount.setUsedQuota(quotaAccount.getUsedQuota() + usedQuota);

        quotaAccountMapper.updateQuotaAccount(quotaAccount);

        // 创造单据
        accountOrderService.createAccountOrder(buildOrder(quotaAccount, AccountOrderTypeEnum.OCCUPIED, usedQuota, outBizNo));
    }

    @Override
    @Transactional
    public void releaseQuota(long userId, String accountNo, long restoredQuota, String outBizNo) throws Exception {
        // 幂等判断
        if (accountOrderService.judgeIdempotent(outBizNo)) {
            // 幂等，直接返回成功
            return;
        }

        // 锁额度账户
        QuotaAccount quotaAccount = lockQuotaAccount(accountNo);


        if (restoredQuota > quotaAccount.getUsedQuota()) {
            throw new Exception("此次释放额度大于已占用额度，交易失败");
        }

        quotaAccount.setUsedQuota(quotaAccount.getUsedQuota() - restoredQuota);
        quotaAccount.setReserveQuota(quotaAccount.getReserveQuota() + restoredQuota);

        quotaAccountMapper.updateQuotaAccount(quotaAccount);

        // 创造单据
        accountOrderService.createAccountOrder(buildOrder(quotaAccount, AccountOrderTypeEnum.RELEASE, restoredQuota, outBizNo));
    }

    private AccountOrder buildOrder(QuotaAccount quotaAccount, AccountOrderTypeEnum accountOrderType, long tradeAmount, String outBizNo) {
        AccountOrder accountOrder = new AccountOrder();

        // 获取当前日期
        LocalDateTime currentDate = LocalDateTime.now();

        accountOrder.setTntInstId(QuotaManagementConstants.DEFAULT_TNT_INST_ID);
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
        QuotaAccount quotaAccount = quotaAccountMapper.lockByAccountNo(accountNo);
        if (Objects.isNull(quotaAccount)) {
            throw new Exception("额度账户加锁失败");
        }

        return quotaAccount;
    }

    private void modifyQuotaAccount(long userAccountId, String accountNo, long totalQuota) throws Exception {
        QuotaAccount quotaAccount = lockQuotaAccount(accountNo);


        // 如果可用额度小于要变动的值，则变动失败
        if (quotaAccount.getUsedQuota() > totalQuota) {
            throw new Exception("额度账户调额度失败，已占用额度大于调整后总额度");
        }

        quotaAccount.setQuotaUpperLimit(totalQuota);
        quotaAccount.setReserveQuota(totalQuota - quotaAccount.getUsedQuota());

        quotaAccountMapper.updateQuotaAccount(quotaAccount);
    }

    private void addQuotaAccount(long userId, String quotaAccountType, long totalQuota, String accountNo) {

        QuotaAccount quotaAccount = new QuotaAccount();
        quotaAccount.setTntInstId(QuotaManagementConstants.DEFAULT_TNT_INST_ID);
        quotaAccount.setAccountNo(accountNo);
        quotaAccount.setAccountType(quotaAccountType);
        quotaAccount.setUserId(userId);
        quotaAccount.setStatus(QuotaAccountStatusEnum.EFFECTIVE.getCode());
        quotaAccount.setCurrency(QuotaManagementConstants.DEFAULT_CURRENCY);
        quotaAccount.setQuotaUpperLimit(totalQuota);
        quotaAccount.setUsedQuota(QuotaManagementConstants.DEFAULT_USED_QUOTA);
        quotaAccount.setReserveQuota(totalQuota);

        quotaAccountMapper.insertQuotaAccount(quotaAccount);
    }
}
