-- 注意：该页面对应的前台目录为views/ambassador.user文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external) 
VALUES ('2022072802007440440', NULL, 'ambassador user list', '/ambassador.user/ambassadorUserList', 'ambassador.user/AmbassadorUserList', NULL, NULL, 0, NULL, '1', 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2022-07-28 14:00:44', NULL, NULL, 0);

-- 权限控制sql
-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2022072802007450441', '2022072802007440440', '添加ambassador user list', NULL, NULL, 0, NULL, NULL, 2, 'org.teleport:ambassador_user:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2022-07-28 14:00:44', NULL, NULL, 0, 0, '1', 0);
-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2022072802007450442', '2022072802007440440', '编辑ambassador user list', NULL, NULL, 0, NULL, NULL, 2, 'org.teleport:ambassador_user:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2022-07-28 14:00:44', NULL, NULL, 0, 0, '1', 0);
-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2022072802007450443', '2022072802007440440', '删除ambassador user list', NULL, NULL, 0, NULL, NULL, 2, 'org.teleport:ambassador_user:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2022-07-28 14:00:44', NULL, NULL, 0, 0, '1', 0);
-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2022072802007450444', '2022072802007440440', '批量删除ambassador user list', NULL, NULL, 0, NULL, NULL, 2, 'org.teleport:ambassador_user:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2022-07-28 14:00:44', NULL, NULL, 0, 0, '1', 0);
-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2022072802007450445', '2022072802007440440', '导出excel_ambassador user list', NULL, NULL, 0, NULL, NULL, 2, 'org.teleport:ambassador_user:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2022-07-28 14:00:44', NULL, NULL, 0, 0, '1', 0);
-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2022072802007450446', '2022072802007440440', '导入excel_ambassador user list', NULL, NULL, 0, NULL, NULL, 2, 'org.teleport:ambassador_user:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2022-07-28 14:00:44', NULL, NULL, 0, 0, '1', 0);