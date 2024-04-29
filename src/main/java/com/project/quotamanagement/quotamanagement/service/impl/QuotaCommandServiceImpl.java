package com.project.quotamanagement.quotamanagement.service.impl;

import com.project.quotamanagement.quotamanagement.dao.QuotaAccountDao;
import com.project.quotamanagement.quotamanagement.dao.UserAccountDao;
import com.project.quotamanagement.quotamanagement.model.QuotaAccount;
import com.project.quotamanagement.quotamanagement.model.UserAccount;
import com.project.quotamanagement.quotamanagement.service.QuotaCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class QuotaCommandServiceImpl implements QuotaCommandService {

    @Autowired
    private UserAccountDao userAccountDao;

    @Autowired
    private QuotaAccountDao quotaAccountDao;

    @Override
    @Transactional
    public void applyQuota(long userAccountId, String quotaAccountType, long totalQuota) throws Exception {

        // 锁账户
        UserAccount userAccount = userAccountDao.lockAccount(userAccountId);
        if (Objects.isNull(userAccount)) {
            throw new Exception("传入的用户id无效");
        }

        // 检查当前是否有此类额度
        List<QuotaAccount> quotaAccountList =  quotaAccountDao.queryQuotaAccount(userAccountId, quotaAccountType);
        if (CollectionUtils.isEmpty(quotaAccountList)) {
            // 无此类型，直接创建
            addQuotaAccount(userAccountId, quotaAccountType, totalQuota);
        } else if (quotaAccountList.size() == 1) {
            // 已有此类型，修改额度
            modifyQuotaAccount(userAccountId, quotaAccountType, totalQuota);
        } else {
            throw new Exception("该用户同类型额度账户超过一个，请检查数据");
        }

    }

    @Override
    @Transactional
    public void occupiedQuota(long userAccountId, String quotaAccountType, long usedQuota) throws Exception {
        QuotaAccount quotaAccount = quotaAccountDao.lockQuotaAccount(userAccountId, quotaAccountType);
        if (Objects.isNull(quotaAccount)) {
            throw new Exception("额度账户加锁失败");
        }

        if (usedQuota > quotaAccount.getAvailableQuota()) {
            throw new Exception("此次消费额度大于可用额度，交易失败");
        }

        quotaAccountDao.updateQuota(userAccountId, quotaAccountType, quotaAccount.getTotalQuota(), quotaAccount.getAvailableQuota() - usedQuota, quotaAccount.getOccupiedQuota() + usedQuota);

    }

    @Override
    @Transactional
    public void releaseQuota(long userAccountId, String quotaAccountType, long restoredQuota) throws Exception {
        QuotaAccount quotaAccount = quotaAccountDao.lockQuotaAccount(userAccountId, quotaAccountType);
        if (Objects.isNull(quotaAccount)) {
            throw new Exception("额度账户加锁失败");
        }

        if (restoredQuota > quotaAccount.getOccupiedQuota()) {
            throw new Exception("此次释放额度大于已占用额度，交易失败");
        }

        quotaAccountDao.updateQuota(userAccountId, quotaAccountType, quotaAccount.getTotalQuota(), quotaAccount.getAvailableQuota() + restoredQuota, quotaAccount.getOccupiedQuota() - restoredQuota);

    }

    private void modifyQuotaAccount(long userAccountId, String quotaAccountType, long totalQuota) throws Exception {
        QuotaAccount quotaAccount = quotaAccountDao.lockQuotaAccount(userAccountId, quotaAccountType);
        if (Objects.isNull(quotaAccount)) {
            throw new Exception("额度账户加锁失败");
        }


        // 如果可用额度小于要变动的值，则变动失败
        if (quotaAccount.getOccupiedQuota() > totalQuota) {
            throw new Exception("额度账户调额度失败，已占用额度大于调整后总额度");
        }

        quotaAccountDao.updateQuota(userAccountId, quotaAccountType, totalQuota, totalQuota - quotaAccount.getOccupiedQuota(), quotaAccount.getOccupiedQuota());

    }

    private void addQuotaAccount(long userAccountId, String quotaAccountType, long totalQuota) {
        QuotaAccount quotaAccount = new QuotaAccount();
        quotaAccount.setQuotaAccountNo(UUID.randomUUID().toString());
        quotaAccount.setQuotaAccountType(quotaAccountType);
        quotaAccount.setAvailableQuota(totalQuota);
        quotaAccount.setTotalQuota(totalQuota);
        quotaAccount.setOccupiedQuota(0L);
        quotaAccount.setUserAccountId(userAccountId);

        quotaAccountDao.insert(quotaAccount);
    }
}
