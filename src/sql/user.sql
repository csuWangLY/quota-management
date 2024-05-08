
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `tnt_inst_id` varchar(32) DEFAULT NULL COMMENT '租户id',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `name` varchar(64) DEFAULT NULL COMMENT '账户名',
  `email` varchar(256) DEFAULT NULL COMMENT '邮箱',
  `status` varchar(64) DEFAULT NULL COMMENT '账户状态：生效、冻结、注销（本期仅存在生效）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账户信息表';

