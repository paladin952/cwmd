USE `cwmd_db`;

SET FOREIGN_KEY_CHECKS=0; DROP TABLE `user_details`; SET FOREIGN_KEY_CHECKS=1;
SET FOREIGN_KEY_CHECKS=0; DROP TABLE `user`; SET FOREIGN_KEY_CHECKS=1;
SET FOREIGN_KEY_CHECKS=0; DROP TABLE `department_user`; SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE IF NOT EXISTS `user_details` (
  `EntryID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(60) NOT NULL,
  `LastName` varchar(60) NOT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `Email` varchar(60) NOT NULL,
  `PhoneNumber` bigint(20) DEFAULT NULL,
  `IsDepartmentChief` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`EntryID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `user` (
  `Username` varchar(60) NOT NULL,
  `Password` varchar(30) NOT NULL,
  `Role` int(11) NOT NULL,
  `userInfo_EntryID` int(11) DEFAULT NULL,
  PRIMARY KEY (`Username`),
  KEY `FKffq5lcso3c8xcmdov20d1jwia` (`userInfo_EntryID`),
  CONSTRAINT `FKffq5lcso3c8xcmdov20d1jwia` FOREIGN KEY (`userInfo_EntryID`) REFERENCES `user_details` (`EntryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `department_user` (
  `DepartmentID` int(11) NOT NULL,
  `Username` varchar(60) NOT NULL,
  PRIMARY KEY (`DepartmentID`,`Username`),
  UNIQUE KEY `Username` (`Username`),
  CONSTRAINT `FK_DepartmentUserDepartment` FOREIGN KEY (`DepartmentID`) REFERENCES `department` (`DepartmentID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_DepartmentUserUser` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKhfbbcrkyn689m5hh6v6m7f9y2` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`),
  CONSTRAINT `FKse13yqj157p3iy977u2m7ui2k` FOREIGN KEY (`DepartmentID`) REFERENCES `department` (`DepartmentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='List of users assigned to a department';

INSERT INTO `user_details` (`EntryID`, `FirstName`, `LastName`, `Address`, `Email`, `PhoneNumber`, `IsDepartmentChief`) VALUES
	(1, 'Awesome', 'McAwesomesauce', '42A Awesome st', 'awesome@awesome.com', 743760319, 1),
	(2, 'Radu', 'Corbu', 'N/A', 'corburadu@hotmail.com', 743760319, 0),
	(3, 'Catalin', 'Cioba', 'N/A', 'cioba.catalin.com', 743760320, 0),
	(4, 'Raduu', 'Corbu', 'N/A', 'corburaduu@gmail.com', 743760321, 0),
	(5, 'Raaduu', 'Corbu', 'N/A', 'corburadu@yahoo.com', 743760322, 0),
	(6, 'Radul', 'Corbu', 'N/A', 'corburadu@aol.com', 743760323, 0),
	(7, 'Raducu', 'Corbu', 'N/A', 'corburadu@yahoo.co.uk', 743760324, 0),
	(8, 'RRadu', 'Corbu', 'N/A', 'corburadu@hotmail.com', 743760325, 0),
	(9, 'Raddu', 'Corbu', 'N/A', 'corburadu@hotmail.com', 743760326, 0),
	(10, 'Rhadu', 'Chorbu', 'N/A', 'corburadu@hotmail.com', 743760327, 0);
	
	
INSERT INTO `user` (`Username`, `Password`, `Role`, `userInfo_EntryID`) VALUES
	('admin', 'admin', 0, 1),
	('catalin', 'catalin', 0, 3),
	('radu', 'radu', 1, 2),
	('raduu', 'radu', 1, 4),
	('raaduu', 'radu', 1, 5),
	('radul', 'radu', 1, 6),
	('raducu', 'raducu', 1, 7),
	('rradu', 'rradu', 1, 8),
	('raddu', 'raddu', 1, 9),
	('rhadu', 'rhadu', 1, 10);
	
INSERT INTO `department_user` (`DepartmentID`, `Username`) VALUES
	(2, 'admin'),
	(3, 'catalin'),
	(4, 'radu'),
	(2, 'raduu'),
	(3, 'raaduu'),
	(4, 'radul'),
	(5, 'raducu'),
	(6, 'rradu'),
	(15, 'raddu'),
	(12, 'rhadu');