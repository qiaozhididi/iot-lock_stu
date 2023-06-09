CREATE TABLE `config` (
                          `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '锁ID',
                          `k` VARCHAR(128) NOT NULL COMMENT '键',
                          `v` VARCHAR(128) NOT NULL COMMENT '值',
                          `upt_time` TIMESTAMP COMMENT '修改时间',
                          `remark` VARCHAR(256) COMMENT '备注',
                          PRIMARY KEY (`id`) USING BTREE
)
    COMMENT='配置'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
ROW_FORMAT=DYNAMIC
;
