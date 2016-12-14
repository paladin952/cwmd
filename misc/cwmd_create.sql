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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.department: ~0 rows (approximately)
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` (`DepartmentID`, `Name`) VALUES
	(2, 'MI');
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

-- Dumping data for table cwmd_db.department_user: ~1 rows (approximately)
/*!40000 ALTER TABLE `department_user` DISABLE KEYS */;
INSERT INTO `department_user` (`DepartmentID`, `Username`) VALUES
	(2, 'asdf');
/*!40000 ALTER TABLE `department_user` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.document
CREATE TABLE IF NOT EXISTS `document` (
  `DocumentID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(150) NOT NULL,
  `DateAdded` date NOT NULL,
  `Owner` varchar(50) NOT NULL,
  `Status` int(11) NOT NULL DEFAULT '0',
  `Version` float NOT NULL,
  `Path` varchar(255) NOT NULL,
  PRIMARY KEY (`DocumentID`),
  KEY `FK54bckn7stqnapx9l837ufqdqm` (`Owner`),
  CONSTRAINT `FK_DocumentOwner` FOREIGN KEY (`Owner`) REFERENCES `user` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.document: ~1 rows (approximately)
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
INSERT INTO `document` (`DocumentID`, `Name`, `DateAdded`, `Owner`, `Status`, `Version`, `Path`) VALUES
	(2, 'Bob\'s diary', '2016-12-04', 'asdf', 0, 0.1, 'N/A');
/*!40000 ALTER TABLE `document` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.drbankinfo
CREATE TABLE IF NOT EXISTS `drbankinfo` (
  `drBankInfoId` int(11) NOT NULL AUTO_INCREMENT,
  `accountOwner` varchar(255) DEFAULT NULL,
  `bankName` varchar(255) DEFAULT NULL,
  `cnp` varchar(255) DEFAULT NULL,
  `homeAddress` varchar(255) DEFAULT NULL,
  `iban` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`drBankInfoId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.drbankinfo: ~0 rows (approximately)
/*!40000 ALTER TABLE `drbankinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `drbankinfo` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.drdailycosts
CREATE TABLE IF NOT EXISTS `drdailycosts` (
  `drDailyCostsId` int(11) NOT NULL AUTO_INCREMENT,
  `dailyPayment2_1Amount` varchar(255) DEFAULT NULL,
  `dailyPayment2_1Currency` varchar(255) DEFAULT NULL,
  `dailyPayment2_1Days` varchar(255) DEFAULT NULL,
  `dailyPayment2_1Funding` varchar(255) DEFAULT NULL,
  `dailyPayment2_1Total` varchar(255) DEFAULT NULL,
  `dailyPayment2_2Amount` varchar(255) DEFAULT NULL,
  `dailyPayment2_2Days` varchar(255) DEFAULT NULL,
  `dailyPayment2_2Funding` varchar(255) DEFAULT NULL,
  `dailyPayment2_3Amount` varchar(255) DEFAULT NULL,
  `dailyPayment2_3Currency` varchar(255) DEFAULT NULL,
  `dailyPayment2_3Funding` varchar(255) DEFAULT NULL,
  `dailyPayment2_3Months` varchar(255) DEFAULT NULL,
  `dailyPayment2_3Total` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`drDailyCostsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.drdailycosts: ~0 rows (approximately)
/*!40000 ALTER TABLE `drdailycosts` DISABLE KEYS */;
/*!40000 ALTER TABLE `drdailycosts` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.drdocument
CREATE TABLE IF NOT EXISTS `drdocument` (
  `DocumentID` int(11) NOT NULL,
  `DateAdded` datetime NOT NULL,
  `Name` varchar(150) NOT NULL,
  `Owner` varchar(50) NOT NULL,
  `Status` int(11) NOT NULL DEFAULT '0',
  `Version` float NOT NULL,
  `Path` varchar(255) NOT NULL,
  `drBankInfo_drBankInfoId` int(11) DEFAULT NULL,
  `drDailyCosts_drDailyCostsId` int(11) DEFAULT NULL,
  `drHousingCosts_drHousingCostsId` int(11) DEFAULT NULL,
  `drOtherCosts_drOtherCostsId` int(11) DEFAULT NULL,
  `drPersonInfo_drPersonInfoId` int(11) DEFAULT NULL,
  `drTotalCosts_drTotalCostsId` int(11) DEFAULT NULL,
  `drTransportationCosts_drTransportationCostsId` int(11) DEFAULT NULL,
  `drTravelInfo_drTravelInfoId` int(11) DEFAULT NULL,
  PRIMARY KEY (`DocumentID`),
  KEY `FK5lmm2lh036nfm7x6yann31cgh` (`drBankInfo_drBankInfoId`),
  KEY `FK105edkqrpfxj876hygl9aos2` (`drDailyCosts_drDailyCostsId`),
  KEY `FKn2g4piekywfy9phvkubuvs0bn` (`drHousingCosts_drHousingCostsId`),
  KEY `FKf2yocl90wp3xpw9whjjrxpn5a` (`drOtherCosts_drOtherCostsId`),
  KEY `FKnkvw5yhlb4w3y8khdewpdm6lv` (`drPersonInfo_drPersonInfoId`),
  KEY `FKam39xbwe6f1gavtnxknb2se5a` (`drTotalCosts_drTotalCostsId`),
  KEY `FK308fko1dpth31flg4fdqu7x38` (`drTransportationCosts_drTransportationCostsId`),
  KEY `FK6or6c5it2qao4iehsv0ybnnbj` (`drTravelInfo_drTravelInfoId`),
  KEY `FK_Owner` (`Owner`),
  CONSTRAINT `FK105edkqrpfxj876hygl9aos2` FOREIGN KEY (`drDailyCosts_drDailyCostsId`) REFERENCES `drdailycosts` (`drDailyCostsId`),
  CONSTRAINT `FK308fko1dpth31flg4fdqu7x38` FOREIGN KEY (`drTransportationCosts_drTransportationCostsId`) REFERENCES `drtransportationcosts` (`drTransportationCostsId`),
  CONSTRAINT `FK5lmm2lh036nfm7x6yann31cgh` FOREIGN KEY (`drBankInfo_drBankInfoId`) REFERENCES `drbankinfo` (`drBankInfoId`),
  CONSTRAINT `FK6or6c5it2qao4iehsv0ybnnbj` FOREIGN KEY (`drTravelInfo_drTravelInfoId`) REFERENCES `drtravelinfo` (`drTravelInfoId`),
  CONSTRAINT `FK_Owner` FOREIGN KEY (`Owner`) REFERENCES `user` (`Username`),
  CONSTRAINT `FKam39xbwe6f1gavtnxknb2se5a` FOREIGN KEY (`drTotalCosts_drTotalCostsId`) REFERENCES `drtotalcosts` (`drTotalCostsId`),
  CONSTRAINT `FKf2yocl90wp3xpw9whjjrxpn5a` FOREIGN KEY (`drOtherCosts_drOtherCostsId`) REFERENCES `drothercosts` (`drOtherCostsId`),
  CONSTRAINT `FKn2g4piekywfy9phvkubuvs0bn` FOREIGN KEY (`drHousingCosts_drHousingCostsId`) REFERENCES `drhousingcosts` (`drHousingCostsId`),
  CONSTRAINT `FKnkvw5yhlb4w3y8khdewpdm6lv` FOREIGN KEY (`drPersonInfo_drPersonInfoId`) REFERENCES `drpersoninfo` (`drPersonInfoId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.drdocument: ~0 rows (approximately)
/*!40000 ALTER TABLE `drdocument` DISABLE KEYS */;
/*!40000 ALTER TABLE `drdocument` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.drhousingcosts
CREATE TABLE IF NOT EXISTS `drhousingcosts` (
  `drHousingCostsId` int(11) NOT NULL AUTO_INCREMENT,
  `housing3_1Amount` varchar(255) DEFAULT NULL,
  `housing3_1Currency` varchar(255) DEFAULT NULL,
  `housing3_1Days` varchar(255) DEFAULT NULL,
  `housing3_1Funding` varchar(255) DEFAULT NULL,
  `housing3_1Total` varchar(255) DEFAULT NULL,
  `housing3_2Amount` varchar(255) DEFAULT NULL,
  `housing3_2Currency` varchar(255) DEFAULT NULL,
  `housing3_2Days` varchar(255) DEFAULT NULL,
  `housing3_2Funding` varchar(255) DEFAULT NULL,
  `housing3_2Total` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`drHousingCostsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.drhousingcosts: ~0 rows (approximately)
/*!40000 ALTER TABLE `drhousingcosts` DISABLE KEYS */;
/*!40000 ALTER TABLE `drhousingcosts` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.drothercosts
CREATE TABLE IF NOT EXISTS `drothercosts` (
  `drOtherCostsId` int(11) NOT NULL AUTO_INCREMENT,
  `otherCosts4_1Amount` varchar(255) DEFAULT NULL,
  `otherCosts4_1Currency` varchar(255) DEFAULT NULL,
  `otherCosts4_1Funding` varchar(255) DEFAULT NULL,
  `otherCosts4_2Amount` varchar(255) DEFAULT NULL,
  `otherCosts4_2Currency` varchar(255) DEFAULT NULL,
  `otherCosts4_2Funding` varchar(255) DEFAULT NULL,
  `otherCosts4_3Amount` varchar(255) DEFAULT NULL,
  `otherCosts4_3Currency` varchar(255) DEFAULT NULL,
  `otherCosts4_3Funding` varchar(255) DEFAULT NULL,
  `otherCosts4_4Amount` varchar(255) DEFAULT NULL,
  `otherCosts4_4Currency` varchar(255) DEFAULT NULL,
  `otherCosts4_4Funding` varchar(255) DEFAULT NULL,
  `otherCosts4_5Amount` varchar(255) DEFAULT NULL,
  `otherCosts4_5Currency` varchar(255) DEFAULT NULL,
  `otherCosts4_5Funding` varchar(255) DEFAULT NULL,
  `otherCosts4_6Amount` varchar(255) DEFAULT NULL,
  `otherCosts4_6Funding` varchar(255) DEFAULT NULL,
  `otherCosts4_7Amount` varchar(255) DEFAULT NULL,
  `otherCosts4_7Currency` varchar(255) DEFAULT NULL,
  `otherCosts4_7Funding` varchar(255) DEFAULT NULL,
  `otherCosts4_8Amount` varchar(255) DEFAULT NULL,
  `otherCosts4_8Funding` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`drOtherCostsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.drothercosts: ~0 rows (approximately)
/*!40000 ALTER TABLE `drothercosts` DISABLE KEYS */;
/*!40000 ALTER TABLE `drothercosts` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.drpersoninfo
CREATE TABLE IF NOT EXISTS `drpersoninfo` (
  `drPersonInfoId` int(11) NOT NULL AUTO_INCREMENT,
  `department` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `fullName` varchar(255) NOT NULL,
  `phoneNumber` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`drPersonInfoId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.drpersoninfo: ~0 rows (approximately)
/*!40000 ALTER TABLE `drpersoninfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `drpersoninfo` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.drtotalcosts
CREATE TABLE IF NOT EXISTS `drtotalcosts` (
  `drTotalCostsId` int(11) NOT NULL AUTO_INCREMENT,
  `advancePayment` varchar(255) DEFAULT NULL,
  `totalSpending` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`drTotalCostsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.drtotalcosts: ~0 rows (approximately)
/*!40000 ALTER TABLE `drtotalcosts` DISABLE KEYS */;
/*!40000 ALTER TABLE `drtotalcosts` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.drtransportationcosts
CREATE TABLE IF NOT EXISTS `drtransportationcosts` (
  `drTransportationCostsId` int(11) NOT NULL AUTO_INCREMENT,
  `transport1_1Amount` varchar(255) DEFAULT NULL,
  `transport1_1Currency` varchar(255) DEFAULT NULL,
  `transport1_1Funding` varchar(255) DEFAULT NULL,
  `transport1_2_1Amount` varchar(255) DEFAULT NULL,
  `transport1_2_1Funding` varchar(255) DEFAULT NULL,
  `transport1_2_2Amount` varchar(255) DEFAULT NULL,
  `transport1_2_2Currency` varchar(255) DEFAULT NULL,
  `transport1_2_2Funding` varchar(255) DEFAULT NULL,
  `transport1_3Amount` varchar(255) DEFAULT NULL,
  `transport1_3Funding` varchar(255) DEFAULT NULL,
  `transport1_4Amount` varchar(255) DEFAULT NULL,
  `transport1_4Currency` varchar(255) DEFAULT NULL,
  `transport1_4Funding` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`drTransportationCostsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.drtransportationcosts: ~0 rows (approximately)
/*!40000 ALTER TABLE `drtransportationcosts` DISABLE KEYS */;
/*!40000 ALTER TABLE `drtransportationcosts` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.drtravelinfo
CREATE TABLE IF NOT EXISTS `drtravelinfo` (
  `drTravelInfoId` int(11) NOT NULL AUTO_INCREMENT,
  `costBearer` varchar(255) NOT NULL,
  `displacementPeriod` varchar(255) NOT NULL,
  `eventTime` varchar(255) NOT NULL,
  `meansOfTransportation` varchar(255) NOT NULL,
  `route` varchar(255) NOT NULL,
  `travelLocation` varchar(255) NOT NULL,
  `travelScope` varchar(255) NOT NULL,
  PRIMARY KEY (`drTravelInfoId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.drtravelinfo: ~0 rows (approximately)
/*!40000 ALTER TABLE `drtravelinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `drtravelinfo` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.flow
CREATE TABLE IF NOT EXISTS `flow` (
  `FlowID` int(11) NOT NULL AUTO_INCREMENT,
  `CurrentDepartment` int(11) NOT NULL DEFAULT '0',
  `Remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`FlowID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.flow: ~0 rows (approximately)
/*!40000 ALTER TABLE `flow` DISABLE KEYS */;
INSERT INTO `flow` (`FlowID`, `CurrentDepartment`, `Remarks`) VALUES
	(1, 0, 'Hi');
/*!40000 ALTER TABLE `flow` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.flow_document
CREATE TABLE IF NOT EXISTS `flow_document` (
  `FlowID` int(11) NOT NULL,
  `DocumentID` int(11) NOT NULL,
  PRIMARY KEY (`FlowID`,`DocumentID`),
  UNIQUE KEY `DocumentID` (`DocumentID`),
  CONSTRAINT `FK_FlowDocumentDocumentID` FOREIGN KEY (`DocumentID`) REFERENCES `document` (`DocumentID`),
  CONSTRAINT `FKqennofhcscbxw9cfogxvyhxum` FOREIGN KEY (`FlowID`) REFERENCES `flow` (`FlowID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.flow_document: ~0 rows (approximately)
/*!40000 ALTER TABLE `flow_document` DISABLE KEYS */;
INSERT INTO `flow_document` (`FlowID`, `DocumentID`) VALUES
	(1, 2);
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
INSERT INTO `flow_path` (`FlowID`, `DepartmentID`) VALUES
	(1, 2);
/*!40000 ALTER TABLE `flow_path` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.user
CREATE TABLE IF NOT EXISTS `user` (
  `Username` varchar(60) NOT NULL,
  `Password` varchar(30) NOT NULL,
  `Role` int(11) NOT NULL,
  `userInfo_EntryID` int(11) DEFAULT NULL,
  PRIMARY KEY (`Username`),
  KEY `FKffq5lcso3c8xcmdov20d1jwia` (`userInfo_EntryID`),
  CONSTRAINT `FKffq5lcso3c8xcmdov20d1jwia` FOREIGN KEY (`userInfo_EntryID`) REFERENCES `user_details` (`EntryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.user: ~3 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`Username`, `Password`, `Role`, `userInfo_EntryID`) VALUES
	('admin', 'admin', 0, 1),
	('asdf', 'asdf', 0, 1),
	('radu', 'radu', 1, 2);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.user_details: ~0 rows (approximately)
/*!40000 ALTER TABLE `user_details` DISABLE KEYS */;
INSERT INTO `user_details` (`EntryID`, `Username`, `FirstName`, `LastName`, `Address`, `Email`, `PhoneNumber`, `IsDepartmentChief`) VALUES
	(1, 'admin', 'Awesome', 'McAwesomesauce', '42A Awesome st', 'awesome@awesome.com', 743760319, 1),
	(2, 'radu', 'Radu', 'Corbu', 'N/A', 'corburadu@hotmail.com', 743760319, 0);
/*!40000 ALTER TABLE `user_details` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
