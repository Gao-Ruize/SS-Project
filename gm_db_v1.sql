-- u_id 微信号对应id
-- tutor_id 工号
create table if not exists `tutor` (
`tutor_id` INT,
`u_id` INT, 
`tutor_name` VARCHAR(50),
primary key(`tutor_id`)
) ENGINE = INNODB DEFAULT CHARSET=utf8;

-- u_id 微信号对应id
-- student_id 学号
create table if not exists `student` (
`student_id` INT,
`u_id` INT,
`student_name` VARCHAR(50),
primary key(`student_id`)
) ENGINE = INNODB DEFAULT CHARSET=utf8;

-- 指导关系表
create table if not exists `instruct` (
`ins_id` INT AUTO_INCREMENT,
`student_id` INT,
`tutor_id` INT,
primary key(`ins_id`),
foreign key (student_id) references student(student_id),
foreign key(tutor_id) references tutor(tutor_id)
) ENGINE = INNODB DEFAULT CHARSET=utf8;

-- 教务处信息
create table if not exists `jwc_message` (
`jwc_msg_id` INT AUTO_INCREMENT,
`release_time` TIMESTAMP,
`title` VARCHAR(200),
`content` VARCHAR(200),
primary key(`jwc_msg_id`)
) ENGINE = INNODB DEFAULT CHARSET=utf8;

-- 教师信息
create table if not exists `ins_message` (
`ins_msg_id` INT AUTO_INCREMENT,
`tutor_id` INT,
`student_id` INT,
`title` VARCHAR(200),
`content` VARCHAR(500),
`release_time` TIMESTAMP,
`if_read` INT,
primary key (`ins_msg_id`),
foreign key (`tutor_id`) references tutor (`tutor_id`),
foreign key (`student_id`) references student (`student_id`)
) ENGINE = INNODB DEFAULT CHARSET=utf8;

-- 教务处信息阅读情况
create table if not exists `read_jwc_msg` (
`rjm_id` INT AUTO_INCREMENT,
`student_id` INT,
`tutor_id` INT,
`if_read` INT,
primary key (`rjm_id`),
foreign key (`student_id`) references student (`student_id`),
foreign key (`tutor_id`) references tutor (`tutor_id`)
) ENGINE = INNODB DEFAULT CHARSET=utf8;



