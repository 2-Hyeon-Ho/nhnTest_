CREATE TABLE `employee`
(
    `employee_id`     int    NOT NULL primary key auto_increment,
    `employee_number` Bigint NOT NULL unique,
    `name`            nvarchar(10) NOT NULL
);

CREATE TABLE `group_information`
(
    `group_information_id` int NOT NULL primary key auto_increment,
    `employee_id`          int NOT NULL,
    `department_id`        int NOT NULL
);

CREATE TABLE `department`
(
    `department_id`   int NOT NULL primary key auto_increment,
    `department_code` nvarchar(20) NOT NULL unique,
    `name`            nvarchar(20) NOT NULL
);

ALTER TABLE `group_information`
    ADD CONSTRAINT `FK_employee_TO_group_information_1` FOREIGN KEY (
                                                                     `employee_id`
        )
        REFERENCES `employee` (
                               `employee_id`
            );

ALTER TABLE `group_information`
    ADD CONSTRAINT `FK_department_TO_group_information_1` FOREIGN KEY (
                                                                       `department_id`
        )
        REFERENCES `department` (
                                 `department_id`
            );

insert into employee
values (null, 20202201, '김이름'),
       (null, 20202202, '김이름');
insert into department
values (null, 'L1001', '백엔드1팀'),
       (null, 'L1002', '백엔드2팀'),
       (null, 'L1003', '백엔드3팀'),
       (null, 'L1004', '백엔드4팀'),
       (null, 'L1005', '백엔드5팀');
insert into group_information
values(null, 1, 1),
      (null, 2, 2),
      (null, 1, 3),
      (null, 1, 4),
      (null, 2, 5),
      (null, 2, 1);
--
-- grant file on *.* to ''nhn_academy_39''@''133.186.151.141'' identified by ''*6]fBj1*QmPlpH2c'' with grant option;
-- LOAD DATA INFILE ''src/main/resources/department.txt'' INTO TABLE `group_information`;

