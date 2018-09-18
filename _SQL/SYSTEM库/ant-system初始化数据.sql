INSERT INTO `t_system_user`(`id`, `account`, `password`, `state`, `create_time`, `create_user_id`) VALUES ('2817a4b39cec41249276cec66e18be34', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'Y', '2018-09-04 22:54:38', '2817a4b39cec41249276cec66e18be34');

INSERT INTO `t_system_role`(`id`, `name`, `is_code`, `state`, `create_time`, `create_user_id`, `remark`) VALUES ('d912b00711234d7ea121cf69486b49b5', '超管理员', 'ADMIN', 'Y', '2018-09-04 23:10:15', '2817a4b39cec41249276cec66e18be34', '初始化数据');

INSERT INTO `t_system_user_role`(`system_user_id`, `system_role_id`) VALUES ('2817a4b39cec41249276cec66e18be34', 'd912b00711234d7ea121cf69486b49b5');


INSERT INTO `t_system_permission`(`id`, `is_type`, `name`, `is_code`, `address`, `state`, `icon`, `sort`, `create_time`, `create_user_id`, `pid`) VALUES ('158b2c60e04e4c25a102f5a0b959856d', 'MENU', '会员管理', 'MEMBER', 'javascript:void(0)', 'Y', '&#xe6e9;', 1, '2018-09-05 15:00:24', '2817a4b39cec41249276cec66e18be34', '0');
INSERT INTO `t_system_permission`(`id`, `is_type`, `name`, `is_code`, `address`, `state`, `icon`, `sort`, `create_time`, `create_user_id`, `pid`) VALUES ('1f2c4a4e754d4c65b13947750e2291da', 'PAGE', '权限', 'SYSTEM.PERMISSION', '/system/permission/permission-index.html', 'Y', '&#xe6f6;', 3, '2018-09-05 15:02:55', '2817a4b39cec41249276cec66e18be34', 'e1ce76507e964f8d9bf4848948dae1ec');
INSERT INTO `t_system_permission`(`id`, `is_type`, `name`, `is_code`, `address`, `state`, `icon`, `sort`, `create_time`, `create_user_id`, `pid`) VALUES ('23efc37d235a40b49e256e6bd9b07e58', 'PAGE', '图标', 'SYSTEM.ICON', '/system/unicode.html', 'Y', '&#xe6eb;', 0, '2018-09-08 07:52:00', '2817a4b39cec41249276cec66e18be34', 'e1ce76507e964f8d9bf4848948dae1ec');
INSERT INTO `t_system_permission`(`id`, `is_type`, `name`, `is_code`, `address`, `state`, `icon`, `sort`, `create_time`, `create_user_id`, `pid`) VALUES ('38fb6134bc044fb081a80aa255b39eeb', 'PAGE', '参数', 'SYSTEM.PARAMS', '/system/params/params-index.html', 'Y', '&#xe704;', 4, '2018-09-10 17:26:17', '2817a4b39cec41249276cec66e18be34', 'e1ce76507e964f8d9bf4848948dae1ec');
INSERT INTO `t_system_permission`(`id`, `is_type`, `name`, `is_code`, `address`, `state`, `icon`, `sort`, `create_time`, `create_user_id`, `pid`) VALUES ('590bf08e3de9421e9579a0cea8c9e4b1', 'PAGE', '成员', 'SYSTEM.USER', '/system/user/user-index.html', 'Y', '&#xe70b;', 1, '2018-09-05 15:01:20', '2817a4b39cec41249276cec66e18be34', 'e1ce76507e964f8d9bf4848948dae1ec');
INSERT INTO `t_system_permission`(`id`, `is_type`, `name`, `is_code`, `address`, `state`, `icon`, `sort`, `create_time`, `create_user_id`, `pid`) VALUES ('cda925720adf422c9311e82e4d033e53', 'PAGE', '角色', 'SYSTEM.ROLE', '/system/role/role-index.html', 'Y', '&#xe705;', 2, '2018-09-05 15:02:09', '2817a4b39cec41249276cec66e18be34', 'e1ce76507e964f8d9bf4848948dae1ec');
INSERT INTO `t_system_permission`(`id`, `is_type`, `name`, `is_code`, `address`, `state`, `icon`, `sort`, `create_time`, `create_user_id`, `pid`) VALUES ('e1ce76507e964f8d9bf4848948dae1ec', 'MENU', '系统设置', 'SYSTEM', 'javascript:void(0)', 'Y', '&#xe696;', 0, '2018-09-05 14:59:44', '2817a4b39cec41249276cec66e18be34', '0');



INSERT INTO `t_system_params`(`id`, `name`, `key`, `value`, `remark`) VALUES ('1b0c5787697a481692cf7ce301394ea8', '默认密码', 'RESET_PASSWORD', 'dpassword', '管理员重置默认密码');
INSERT INTO `t_system_params`(`id`, `name`, `key`, `value`, `remark`) VALUES ('1b0c5787697a481692cf7ce301394ea9', '网页状态', 'FRONT_WEB_STATE', 'N', '网页是否可以访问(Y:可以,N:维护中)');
