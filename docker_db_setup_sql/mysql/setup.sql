
CREATE TABLE IF NOT EXISTS `mysql_db`.`m_users` (
  `id` BIGINT NOT NULL,
  `uname` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `sname` VARCHAR(45) NULL,
  PRIMARY KEY (`id`)
);


INSERT INTO `mysql_db`.`m_users` (`id`, `uname`, `name`, `sname`)
VALUES
(1, 'mysql_login_1', 'name1', 'sn1'),
(2, 'mysql_login_2', 'name2', 'sn2'),
(3, 'mysql_login_3', 'name3', 'sn3');
