
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `quota_account` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `tnt_inst_id` varchar(32) DEFAULT NULL COMMENT '租户id',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `account_no` varchar(64) DEFAULT NULL COMMENT '账号',
  `account_type` varchar(32) DEFAULT NULL COMMENT '账户类型',
  `user_id` bigint unsigned DEFAULT NULL COMMENT '用户id',
  `status` varchar(32) DEFAULT NULL COMMENT '状态：生效、注销、冻结',
  `currency` varchar(16) DEFAULT '156' COMMENT '币种，默认人民币 156',
  `quota_upper_limit` bigint unsigned DEFAULT NULL COMMENT '额度上限',
  `used_quota` bigint unsigned DEFAULT NULL COMMENT '已使用额度',
  `reserve_quota` bigint unsigned DEFAULT NULL COMMENT '剩余额度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账户信息表';
