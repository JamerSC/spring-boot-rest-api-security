USE `employee_directory`;

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: https://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: test123
--

INSERT INTO `users`  (`username`, `password`, `enabled`)
VALUES 
('john','{bcrypt}$2a$10$3PwNWoZBniqzTkIuZ6nyOOBDnp1yKIo.pCG56PP6.Wiln9WBSizJ6.q',1),
('mary','{bcrypt}$2a$10$3PwNWoZBniqzTkIuZ6nyOOBDnp1yKIo.pCG56PP6.Wiln9WBSizJ6',1),
('susan','{bcrypt}$2a$10$3PwNWoZBniqzTkIuZ6nyOOBDnp1yKIo.pCG56PP6.Wiln9WBSizJ6.q',1);


--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities4_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities4_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SHOW CREATE TABLE authorities; -- Check the current structure of the table

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities`  (`username`, `authority`) 
VALUES 
('john','ROLE_EMPLOYEE'),
('mary','ROLE_EMPLOYEE'),
('mary','ROLE_MANAGER'),
('susan','ROLE_EMPLOYEE'),
('susan','ROLE_MANAGER'),
('susan','ROLE_ADMIN');