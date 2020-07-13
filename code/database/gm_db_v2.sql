-- u_id 微信号对应id
-- tutor_id 工号
create table if not exists `tutor` (
`Id` INT AUTO_INCREMENT,
`TutorId` VARCHAR(100),
`Uid` VARCHAR(100), 
`TutorName` VARCHAR(100),
primary key(`Id`)
) ENGINE = INNODB DEFAULT CHARSET=utf8;

-- u_id 微信号对应id
-- student_id 学号
create table if not exists `student` (
`Id` INT AUTO_INCREMENT,
`StudentId` VARCHAR(100),
`Uid` VARCHAR(100),
`StudentName` VARCHAR(100),
primary key(`Id`)
) ENGINE = INNODB DEFAULT CHARSET=utf8;

-- 指导关系表
create table if not exists `instruct` (
`Id` INT AUTO_INCREMENT,
`StudentId` VARCHAR(100),
`TutorId` VARCHAR(100),
primary key(`Id`)
) ENGINE = INNODB DEFAULT CHARSET=utf8;

-- 教务处信息
-- phase 消息对应的毕业设计阶段
create table if not exists `jwc_message` (
`Id` INT AUTO_INCREMENT,
`ReleaseTime` VARCHAR(20),
`Title` VARCHAR(200),
`Content` VARCHAR(200),
`Phase` INT,
primary key(`Id`)
) ENGINE = INNODB DEFAULT CHARSET=utf8;

-- 教师信息
create table if not exists `ins_message` (
`Id` INT AUTO_INCREMENT,
`TutorId` VARCHAR(100),
`StudentId` VARCHAR(100),
`Title` VARCHAR(200),
`Content` VARCHAR(500),
`ReleaseTime` VARCHAR(20),
`Phase` INT,
`IfRead` INT,
primary key (`Id`)
) ENGINE = INNODB DEFAULT CHARSET=utf8;

-- 教务处信息阅读情况
drop table if exists`read_jwc_msg`;
create table if not exists `read_jwc_msg` (
`Id` INT AUTO_INCREMENT,
`StudentId` VARCHAR(100),
`TutorId` VARCHAR(100),
`IfRead` INT,
`IfStudent` INT,
`JwcMsgId` INT,
primary key (`Id`)
) ENGINE = INNODB DEFAULT CHARSET=utf8;



