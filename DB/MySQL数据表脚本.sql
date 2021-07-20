USE db_admin;

-- 创建数据表：user_info（用户信息表）
DROP TABLE IF EXISTS user_info;
CREATE TABLE IF NOT EXISTS user_info
(
	user_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID（主键、自增）',
	user_name VARCHAR(50) UNIQUE NOT NULL COMMENT '用户姓名',
	PASSWORD VARCHAR(50) NOT NULL COMMENT '登录密码',
	salt VARCHAR(50) NOT NULL NOT NULL COMMENT '盐',
	state CHAR(2) DEFAULT '0' COMMENT '状态（0：禁用；1：锁定；2：启用）',
	blog_url VARCHAR(50) NOT NULL COMMENT '博客地址',
	blog_remark VARCHAR(50) COMMENT '博客信息'
) COMMENT = '用户信息表';

-- 创建数据表：permission_info（权限信息表）
DROP TABLE IF EXISTS permission_info;
CREATE TABLE IF NOT EXISTS permission_info
( 
	permission_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '权限ID（主键、自增）',
	permission_code VARCHAR(50) UNIQUE NOT NULL COMMENT '权限编号',
	permission_name VARCHAR(50) NOT NULL COMMENT '权限名称'
) COMMENT = '权限信息表';

-- 创建数据表：role_info（角色信息表）
DROP TABLE IF EXISTS role_info;
CREATE TABLE IF NOT EXISTS role_info
( 
	role_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '角色ID（主键、自增）',
	role_code VARCHAR(50) UNIQUE NOT NULL COMMENT '角色编号',
	role_name VARCHAR(50) NOT NULL COMMENT '角色名称'
) COMMENT = '角色信息表';

-- 创建数据表：role_permission_mapping（角色与权限映射表）
DROP TABLE IF EXISTS role_permission_mapping;
CREATE TABLE IF NOT EXISTS role_permission_mapping
( 
	mapping_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '映射ID（主键、自增）',
	role_id INT NOT NULL COMMENT '角色ID',
	permission_id INT NOT NULL COMMENT '权限ID'
) COMMENT = '角色与权限映射表';

-- 创建数据表：user_role_mapping（用户与角色映射表）
DROP TABLE IF EXISTS user_role_mapping;
CREATE TABLE IF NOT EXISTS user_role_mapping
( 
	mapping_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '映射ID（主键、自增）',
	user_id INT NOT NULL COMMENT '用户ID',
	role_id INT NOT NULL COMMENT '角色ID'
) COMMENT = '用户与角色映射表';

-- 初始化：user_info（用户信息表）
TRUNCATE TABLE user_info;
insert into user_info(user_name,PASSWORD,salt,state,blog_url,blog_remark)
value('pan_junbiao的博客','ab2d90c1307e8a2733d8332710b0ca16','ca4f8a8f17f0e2f80698ba3005b2df15'
,2,'https://blog.csdn.net/pan_junbiao','您好，欢迎访问 pan_junbiao的博客');

-- 初始化：permission_info（权限信息表）
TRUNCATE TABLE permission_info;
INSERT INTO permission_info(permission_code,permission_name) VALUES('user:list','用户列表');
INSERT INTO permission_info(permission_code,permission_name) VALUES('user:detail','用户详情');
INSERT INTO permission_info(permission_code,permission_name) VALUES('user:add','新增用户');
INSERT INTO permission_info(permission_code,permission_name) VALUES('user:edit','编辑用户');
INSERT INTO permission_info(permission_code,permission_name) VALUES('user:delete','删除用户');
INSERT INTO permission_info(permission_code,permission_name) VALUES('user:summarize','用户统计');
INSERT INTO permission_info(permission_code,permission_name) VALUES('system:info','系统信息');

-- 初始化：role_info（角色信息表）
TRUNCATE TABLE role_info;
INSERT INTO role_info(role_code,role_name) VALUES('2001','用户管理员');
INSERT INTO role_info(role_code,role_name) VALUES('2002','数据分析员');
INSERT INTO role_info(role_code,role_name) VALUES('2003','系统管理员');

-- 初始化：role_permission_mapping（角色与权限映射表）
TRUNCATE TABLE role_permission_mapping;
INSERT INTO role_permission_mapping(role_id,permission_id) VALUES(1,1);
INSERT INTO role_permission_mapping(role_id,permission_id) VALUES(1,2);
INSERT INTO role_permission_mapping(role_id,permission_id) VALUES(1,3);
INSERT INTO role_permission_mapping(role_id,permission_id) VALUES(1,4);
INSERT INTO role_permission_mapping(role_id,permission_id) VALUES(1,5);
INSERT INTO role_permission_mapping(role_id,permission_id) VALUES(2,6);
INSERT INTO role_permission_mapping(role_id,permission_id) VALUES(3,7);

-- 初始化：user_role_mapping（用户与角色映射表）
TRUNCATE TABLE user_role_mapping;
INSERT INTO user_role_mapping(user_id,role_id) VALUES(1,1);
INSERT INTO user_role_mapping(user_id,role_id) VALUES(1,3);


select * from user_info where user_id=1;
SELECT * FROM permission_info;
SELECT * FROM role_info;
SELECT * FROM role_permission_mapping;
SELECT * FROM user_role_mapping;


