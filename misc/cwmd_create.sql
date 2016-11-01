-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.11-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for cwmd_db
CREATE DATABASE IF NOT EXISTS `cwmd_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `cwmd_db`;

-- Dumping structure for table cwmd_db.department
CREATE TABLE IF NOT EXISTS `department` (
  `DepartmentID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  PRIMARY KEY (`DepartmentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.department: ~0 rows (approximately)
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
/*!40000 ALTER TABLE `department` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.department_user
CREATE TABLE IF NOT EXISTS `department_user` (
  `DepartmentID` int(11) NOT NULL,
  `Username` varchar(60) NOT NULL,
  PRIMARY KEY (`DepartmentID`,`Username`),
  UNIQUE KEY `Username` (`Username`),
  CONSTRAINT `FK_DepartmentUserDepartment` FOREIGN KEY (`DepartmentID`) REFERENCES `department` (`DepartmentID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_DepartmentUserUser` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='List of users assigned to a department';

-- Dumping data for table cwmd_db.department_user: ~0 rows (approximately)
/*!40000 ALTER TABLE `department_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `department_user` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.document
CREATE TABLE IF NOT EXISTS `document` (
  `DocumentID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(60) NOT NULL,
  `DateAdded` date NOT NULL,
  `Owner` varchar(50) NOT NULL,
  `Version` float NOT NULL,
  PRIMARY KEY (`DocumentID`),
  KEY `FK_DocumentOwner` (`Owner`),
  CONSTRAINT `FK_DocumentOwner` FOREIGN KEY (`Owner`) REFERENCES `user` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.document: ~0 rows (approximately)
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
/*!40000 ALTER TABLE `document` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.flow
CREATE TABLE IF NOT EXISTS `flow` (
  `FlowID` int(11) NOT NULL AUTO_INCREMENT,
  `CurrentDepartment` int(11) NOT NULL DEFAULT '0',
  `Remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`FlowID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.flow: ~0 rows (approximately)
/*!40000 ALTER TABLE `flow` DISABLE KEYS */;
/*!40000 ALTER TABLE `flow` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.flow_document
CREATE TABLE IF NOT EXISTS `flow_document` (
  `FlowID` int(11) NOT NULL,
  `DocumentID` int(11) NOT NULL,
  PRIMARY KEY (`FlowID`,`DocumentID`),
  UNIQUE KEY `DocumentID` (`DocumentID`),
  CONSTRAINT `FK_FlowDocumentDocument` FOREIGN KEY (`FlowID`) REFERENCES `flow` (`FlowID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_FlowDocumentFlow` FOREIGN KEY (`DocumentID`) REFERENCES `document` (`DocumentID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.flow_document: ~0 rows (approximately)
/*!40000 ALTER TABLE `flow_document` DISABLE KEYS */;
/*!40000 ALTER TABLE `flow_document` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.flow_path
CREATE TABLE IF NOT EXISTS `flow_path` (
  `FlowID` int(11) NOT NULL,
  `DepartmentID` int(11) NOT NULL,
  PRIMARY KEY (`FlowID`,`DepartmentID`),
  KEY `FK_FlowDepartmentDepartment` (`DepartmentID`),
  CONSTRAINT `FK_FlowDepartmentDepartment` FOREIGN KEY (`DepartmentID`) REFERENCES `department` (`DepartmentID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_FlowDepartmentFlow` FOREIGN KEY (`FlowID`) REFERENCES `flow` (`FlowID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.flow_path: ~0 rows (approximately)
/*!40000 ALTER TABLE `flow_path` DISABLE KEYS */;
/*!40000 ALTER TABLE `flow_path` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.user
CREATE TABLE IF NOT EXISTS `user` (
  `Username` varchar(60) NOT NULL,
  `Password` varchar(30) NOT NULL,
  `Role` int(11) NOT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.user: ~0 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`Username`, `Password`, `Role`) VALUES
	('admin', 'admin', 0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.user_details
CREATE TABLE IF NOT EXISTS `user_details` (
  `EntryID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(60) NOT NULL,
  `FirstName` varchar(60) NOT NULL,
  `LastName` varchar(60) NOT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `Email` varchar(60) NOT NULL,
  `PhoneNumber` bigint(20) DEFAULT NULL,
  `IsDepartmentChief` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`EntryID`),
  KEY `FK7nor195hp6whxc5qt7xgga842` (`Username`),
  CONSTRAINT `FK7nor195hp6whxc5qt7xgga842` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`),
  CONSTRAINT `FK_UserDetailsUsername` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.user_details: ~1 rows (approximately)
/*!40000 ALTER TABLE `user_details` DISABLE KEYS */;
INSERT INTO `user_details` (`EntryID`, `Username`, `FirstName`, `LastName`, `Address`, `Email`, `PhoneNumber`, `IsDepartmentChief`) VALUES
	(1, 'admin', 'Awesome', 'McAwesomesauce', '42A Awesome st', 'awesome@awesome.com', 743760319, 1);
/*!40000 ALTER TABLE `user_details` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
