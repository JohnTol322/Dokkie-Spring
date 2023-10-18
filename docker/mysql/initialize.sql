CREATE USER 'PMA'@'%' IDENTIFIED BY 'pma-pw';
GRANT ALL ON *.* TO 'PMA'@'%';

CREATE USER 'spring'@'%' IDENTIFIED BY 'spring-pw';
CREATE DATABASE IF NOT EXISTS `spring` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
GRANT ALL ON `spring`.* TO 'spring'@'%';
