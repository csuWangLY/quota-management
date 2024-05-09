
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `account_order` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `tnt_inst_id` varchar(32) DEFAULT NULL COMMENT '租户id',
  `gmt_create` timestamp DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建日期',
  `gmt_modified` timestamp  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NULL COMMENT '修改时间',
  `debit_account_no` varchar(64) DEFAULT NULL COMMENT '出款账号',
  `user_id` bigint unsigned DEFAULT NULL COMMENT '用户id',
  `trade_type` varchar(32) DEFAULT NULL COMMENT '交易类型',
  `trade_amount` bigint unsigned DEFAULT NULL COMMENT '交易金额',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  `out_biz_no` varchar(64) DEFAULT NULL COMMENT '外部单号',
  `biz_time` timestamp DEFAULT NULL COMMENT '业务时间',
  `biz_date` timestamp DEFAULT NULL COMMENT '业务日期',
  `account_type` varchar(32) DEFAULT NULL COMMENT '账户类型',
  `currency` varchar(16) DEFAULT NULL COMMENT '币种',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='交易信息表';
