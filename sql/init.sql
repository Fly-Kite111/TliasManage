-- Tlias 教学管理系统数据库初始化脚本
create database if not exists tlias default charset utf8mb4 collate utf8mb4_general_ci;

use tlias;

-- 部门表
create table if not exists dept (
    id int unsigned primary key auto_increment comment 'ID',
    name varchar(50) not null unique comment '部门名称',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '修改时间'
) comment '部门表';

-- 员工表
create table if not exists emp (
    id int unsigned primary key auto_increment comment 'ID',
    username varchar(20) not null unique comment '用户名',
    password varchar(32) default '123456' comment '密码',
    name varchar(10) not null comment '姓名',
    gender tinyint unsigned comment '性别, 1:男, 2:女',
    image varchar(300) comment '头像',
    job tinyint unsigned comment '职位, 1:班主任, 2:讲师, 3:学工主管, 4:教研主管',
    entrydate date comment '入职日期',
    dept_id int unsigned comment '部门ID',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '修改时间',
    constraint fk_emp_dept foreign key (dept_id) references dept(id)
) comment '员工表';

-- 部门测试数据
insert into dept(name, create_time, update_time) values
('学工部', now(), now()),
('教研部', now(), now()),
('咨询部', now(), now()),
('就业部', now(), now()),
('人事部', now(), now());

-- 员工测试数据
insert into emp(username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time) values
('jinyong', '123456', '金庸', 1, 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', 4, '2007-01-01', 2, now(), now()),
('yangguo', '123456', '杨过', 1, 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', 2, '2013-01-01', 2, now(), now()),
('xiaolongnv', '123456', '小龙女', 2, 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', 2, '2015-05-01', 2, now(), now()),
('zhangwuji', '123456', '张无忌', 1, 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', 1, '2018-09-01', 1, now(), now()),
('zhaomin', '123456', '赵敏', 2, 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', 3, '2019-03-15', 1, now(), now()),
('zhouzhiruo', '123456', '周芷若', 2, 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', 1, '2020-07-01', 1, now(), now()),
('linghuchong', '123456', '令狐冲', 1, 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', 2, '2021-02-20', 3, now(), now()),
('renyingying', '123456', '任盈盈', 2, 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', 3, '2022-06-10', 3, now(), now()),
('guojing', '123456', '郭靖', 1, 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', 2, '2023-01-05', 4, now(), now()),
('huangrong', '123456', '黄蓉', 2, 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', 1, '2023-08-18', 4, now(), now()),
('qiaofeng', '123456', '乔峰', 1, 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', 4, '2024-03-01', 5, now(), now()),
('wangyuyan', '123456', '王语嫣', 2, 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', 1, '2024-11-11', 5, now(), now());
