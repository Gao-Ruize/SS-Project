-- u_id 微信号对应id
-- tutor_id 工号
create table if not exists `tutor` (
`id` INT AUTO_INCREMENT,
`tutor_id` VARCHAR(100),
`u_id` VARCHAR(100), 
`tutor_name` VARCHAR(100),
primary key(`id`)
) ENGINE = INNODB DEFAULT CHARSET=utf8;

-- u_id 微信号对应id
-- student_id 学号
create table if not exists `student` (
`id` INT AUTO_INCREMENT,
`student_id` VARCHAR(100),
`u_id` VARCHAR(100),
`student_name` VARCHAR(100),
primary key(`id`)
) ENGINE = INNODB DEFAULT CHARSET=utf8;

-- 指导关系表
create table if not exists `instruct` (
`ins_id` INT AUTO_INCREMENT,
`student_id` VARCHAR(100),
`tutor_id` VARCHAR(100),
primary key(`ins_id`)
) ENGINE = INNODB DEFAULT CHARSET=utf8;

-- 教务处信息
create table if not exists `jwc_message` (
`jwc_msg_id` INT AUTO_INCREMENT,
`release_time` VARCHAR(20),
`title` VARCHAR(200),
`content` VARCHAR(200),
primary key(`jwc_msg_id`)
) ENGINE = INNODB DEFAULT CHARSET=utf8;

-- 教师信息
create table if not exists `ins_message` (
`ins_msg_id` INT AUTO_INCREMENT,
`tutor_id` VARCHAR(100),
`student_id` VARCHAR(100),
`title` VARCHAR(200),
`content` VARCHAR(500),
`release_time` VARCHAR(20),
`if_read` INT,
primary key (`ins_msg_id`)
) ENGINE = INNODB DEFAULT CHARSET=utf8;

-- 教务处信息阅读情况
create table if not exists `read_jwc_msg` (
`rjm_id` INT AUTO_INCREMENT,
`student_id` VARCHAR(100),
`tutor_id` VARCHAR(100),
`if_read` INT,
`if_student` INT,
primary key (`rjm_id`)
) ENGINE = INNODB DEFAULT CHARSET=utf8;



