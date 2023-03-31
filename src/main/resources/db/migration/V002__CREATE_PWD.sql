CREATE TABLE `pwd`
(
    `id`          int                        NOT NULL AUTO_INCREMENT,
    `typ`         SET ('fixed','temp')       NOT NULL,
    `pwd`         varchar(18)                NOT NULL,
    `status`      SET ('enabled','disabled') NOT NULL,
    `upt_time` datetime                   NOT NULL,
    `expired_time` datetime,
    PRIMARY KEY (`id`)
)
    COMMENT ='密码表'
    COLLATE = 'utf8mb4_general_ci'
    ENGINE = InnoDB
    ROW_FORMAT = DYNAMIC
;