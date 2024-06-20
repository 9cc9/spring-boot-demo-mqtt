DROP TABLE IF EXISTS `demo_monitor`;
CREATE TABLE `demo_monitor` (
                              `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
                              `name` VARCHAR(32) NOT NULL UNIQUE COMMENT '用户名',
                              `status` VARCHAR(32) NOT NULL COMMENT '状态',
                              `gmt_create` DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间',
                              `gmt_modified` DATETIME NOT NULL DEFAULT NOW() COMMENT '上次更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='监控表';
