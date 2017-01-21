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
  `IsUserGroup` bit(1) NOT NULL,
  PRIMARY KEY (`DepartmentID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.department: ~14 rows (approximately)
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` (`DepartmentID`, `Name`, `IsUserGroup`) VALUES
	(3, 'Rector Office', b'1'),
	(4, 'Dean Office', b'1'),
	(5, 'Faculty of Mathematics', b'0'),
	(6, 'Faculty of Computer Science', b'0'),
	(7, 'Faculty of Physics', b'0'),
	(8, 'Faculty of Chemistry', b'0'),
	(9, 'Faculty of Physical Education and Sport', b'0'),
	(10, 'Management Center for Scientific Research', b'0'),
	(11, 'CFO', b'0'),
	(12, 'Grants', b'0'),
	(13, 'Public Procurement Service', b'0'),
	(14, 'International Cooperation Center', b'0'),
	(15, 'Chief Operating Officer', b'0');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.department_user
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

-- Dumping data for table cwmd_db.department_user: ~3 rows (approximately)
/*!40000 ALTER TABLE `department_user` DISABLE KEYS */;
INSERT INTO `department_user` (`DepartmentID`, `Username`) VALUES
	(5, 'admin'),
	(5, 'asdf'),
	(3, 'radu');
/*!40000 ALTER TABLE `department_user` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.document
CREATE TABLE IF NOT EXISTS `document` (
  `DocumentID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(150) NOT NULL,
  `DateAdded` date NOT NULL,
  `Status` int(11) NOT NULL DEFAULT '0',
  `Version` float NOT NULL,
  `Path` varchar(255) NOT NULL,
  `Username` varchar(60) NOT NULL,
  `isPartOfFlow` bit(1) DEFAULT NULL,
  PRIMARY KEY (`DocumentID`),
  KEY `FKh2xnpiuqw33vcb6llvn8dt9cn` (`Username`),
  CONSTRAINT `FKh2xnpiuqw33vcb6llvn8dt9cn` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.document: ~1 rows (approximately)
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
INSERT INTO `document` (`DocumentID`, `Name`, `DateAdded`, `Status`, `Version`, `Path`, `Username`, `isPartOfFlow`) VALUES
	(1, 'Bob\'s adventures', '2017-01-18', 0, 0.1, 'N/A', 'radu', b'1');
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
  `Username` varchar(60) NOT NULL,
  `isPartOfFlow` bit(1) DEFAULT NULL,
  PRIMARY KEY (`DocumentID`),
  KEY `FK5lmm2lh036nfm7x6yann31cgh` (`drBankInfo_drBankInfoId`),
  KEY `FK105edkqrpfxj876hygl9aos2` (`drDailyCosts_drDailyCostsId`),
  KEY `FKn2g4piekywfy9phvkubuvs0bn` (`drHousingCosts_drHousingCostsId`),
  KEY `FKf2yocl90wp3xpw9whjjrxpn5a` (`drOtherCosts_drOtherCostsId`),
  KEY `FKnkvw5yhlb4w3y8khdewpdm6lv` (`drPersonInfo_drPersonInfoId`),
  KEY `FKam39xbwe6f1gavtnxknb2se5a` (`drTotalCosts_drTotalCostsId`),
  KEY `FK308fko1dpth31flg4fdqu7x38` (`drTransportationCosts_drTransportationCostsId`),
  KEY `FK6or6c5it2qao4iehsv0ybnnbj` (`drTravelInfo_drTravelInfoId`),
  KEY `FK_6ry8xs477d7484h51wy0npuqs` (`Username`),
  CONSTRAINT `FK105edkqrpfxj876hygl9aos2` FOREIGN KEY (`drDailyCosts_drDailyCostsId`) REFERENCES `drdailycosts` (`drDailyCostsId`),
  CONSTRAINT `FK308fko1dpth31flg4fdqu7x38` FOREIGN KEY (`drTransportationCosts_drTransportationCostsId`) REFERENCES `drtransportationcosts` (`drTransportationCostsId`),
  CONSTRAINT `FK5lmm2lh036nfm7x6yann31cgh` FOREIGN KEY (`drBankInfo_drBankInfoId`) REFERENCES `drbankinfo` (`drBankInfoId`),
  CONSTRAINT `FK6or6c5it2qao4iehsv0ybnnbj` FOREIGN KEY (`drTravelInfo_drTravelInfoId`) REFERENCES `drtravelinfo` (`drTravelInfoId`),
  CONSTRAINT `FK_6ry8xs477d7484h51wy0npuqs` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`),
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
  `Username` varchar(60) NOT NULL,
  PRIMARY KEY (`FlowID`),
  KEY `FKoa8tduo82td4vbnfd4yadrbd2` (`Username`),
  CONSTRAINT `FKoa8tduo82td4vbnfd4yadrbd2` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.flow: ~3 rows (approximately)
/*!40000 ALTER TABLE `flow` DISABLE KEYS */;
INSERT INTO `flow` (`FlowID`, `CurrentDepartment`, `Remarks`, `Username`) VALUES
	(1, 0, 'asdadadadad', 'radu'),
	(2, 0, 'asd', 'radu'),
	(4, 1, 'I changed this', 'radu');
/*!40000 ALTER TABLE `flow` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.flow_document
CREATE TABLE IF NOT EXISTS `flow_document` (
  `FlowID` int(11) NOT NULL,
  `DocumentID` int(11) NOT NULL,
  PRIMARY KEY (`FlowID`,`DocumentID`),
  UNIQUE KEY `DocumentID` (`DocumentID`),
  UNIQUE KEY `UK_bg72aphuccehpip6exti784cy` (`DocumentID`),
  CONSTRAINT `FK_FlowDocumentDocumentID` FOREIGN KEY (`DocumentID`) REFERENCES `document` (`DocumentID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKqennofhcscbxw9cfogxvyhxum` FOREIGN KEY (`FlowID`) REFERENCES `flow` (`FlowID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.flow_document: ~1 rows (approximately)
/*!40000 ALTER TABLE `flow_document` DISABLE KEYS */;
INSERT INTO `flow_document` (`FlowID`, `DocumentID`) VALUES
	(4, 1);
/*!40000 ALTER TABLE `flow_document` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.flow_path
CREATE TABLE IF NOT EXISTS `flow_path` (
  `FlowPathID` int(11) NOT NULL AUTO_INCREMENT,
  `FlowID` int(11) NOT NULL,
  `DepartmentID` int(11) NOT NULL,
  PRIMARY KEY (`FlowID`,`DepartmentID`),
  KEY `FK_FlowDepartmentDepartment` (`DepartmentID`),
  KEY `FlowPathID` (`FlowPathID`),
  CONSTRAINT `FK79ov9ymvuj3ngjryb6hhqtxam` FOREIGN KEY (`FlowID`) REFERENCES `flow` (`FlowID`),
  CONSTRAINT `FK_FlowDepartmentDepartment` FOREIGN KEY (`DepartmentID`) REFERENCES `department` (`DepartmentID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_FlowDepartmentFlow` FOREIGN KEY (`FlowID`) REFERENCES `flow` (`FlowID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKjlxasdf2b4nuplik3djyjg56q` FOREIGN KEY (`DepartmentID`) REFERENCES `department` (`DepartmentID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.flow_path: ~4 rows (approximately)
/*!40000 ALTER TABLE `flow_path` DISABLE KEYS */;
INSERT INTO `flow_path` (`FlowPathID`, `FlowID`, `DepartmentID`) VALUES
	(1, 1, 5),
	(2, 4, 13),
	(3, 4, 5),
	(4, 4, 14);
/*!40000 ALTER TABLE `flow_path` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.hibernate_sequence: ~0 rows (approximately)
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(3);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.log
CREATE TABLE IF NOT EXISTS `log` (
  `EntryID` bigint(20) NOT NULL AUTO_INCREMENT,
  `Level` int(11) NOT NULL,
  `Timestamp` datetime NOT NULL,
  `Tag` varchar(60) NOT NULL,
  `User` varchar(60) DEFAULT NULL,
  `Department` varchar(60) DEFAULT NULL,
  `DocumentType` varchar(60) DEFAULT NULL,
  `Message` varchar(255) NOT NULL,
  `Exception` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`EntryID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.log: ~4 rows (approximately)
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` (`EntryID`, `Level`, `Timestamp`, `Tag`, `User`, `Department`, `DocumentType`, `Message`, `Exception`) VALUES
	(1, 1, '2017-01-11 13:30:03', 'UserController', NULL, NULL, NULL, 'Retrieving all users', NULL),
	(2, 1, '2017-01-11 13:30:43', 'UserController', NULL, NULL, NULL, 'Retrieving all users', NULL),
	(3, 1, '2017-01-11 13:32:22', 'UserController', NULL, NULL, NULL, 'Retrieving all users', NULL),
	(4, 3, '2017-01-11 14:35:10', 'LogController', NULL, NULL, NULL, 'Error while retrieving application log', 'org.hibernate.InstantiationException: No default constructor for entity:  : application.core.model.logging.LogItem'),
	(5, 1, '2017-01-11 14:54:08', 'UserController', NULL, NULL, NULL, 'Retrieving the user list', NULL),
	(6, 3, '2017-01-12 12:13:53', 'LogController', NULL, NULL, NULL, 'Error while retrieving application log', 'Unparseable date: "2017-01-09"');
/*!40000 ALTER TABLE `log` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.rndocument
CREATE TABLE IF NOT EXISTS `rndocument` (
  `DocumentID` int(11) NOT NULL,
  `DateAdded` date NOT NULL,
  `Name` varchar(150) NOT NULL,
  `Path` varchar(255) NOT NULL,
  `Status` int(11) NOT NULL,
  `Version` float NOT NULL,
  `budget` float DEFAULT NULL,
  `personalFunds` float DEFAULT NULL,
  `rnOthers_rnOthersId` int(11) DEFAULT NULL,
  `rnResearch_rnResearchId` int(11) DEFAULT NULL,
  `rnSponsors_rnSponsorsId` int(11) DEFAULT NULL,
  `rnTotal_rnTotalId` int(11) DEFAULT NULL,
  `Username` varchar(60) NOT NULL,
  `isPartOfFlow` bit(1) DEFAULT NULL,
  PRIMARY KEY (`DocumentID`),
  KEY `FK1bjqpc37c5dwhu8lf3gdl2199` (`rnOthers_rnOthersId`),
  KEY `FKnpnvai2txm0tv0aea611ukq08` (`rnResearch_rnResearchId`),
  KEY `FKi025k7hxkqwfjwwqt3xiolo5k` (`rnSponsors_rnSponsorsId`),
  KEY `FKi6j8pcdapvidrq9o16v6vq6e6` (`rnTotal_rnTotalId`),
  KEY `FK_6xvpd8rklbm6f8bxxbxnxhi5g` (`Username`),
  CONSTRAINT `FK1bjqpc37c5dwhu8lf3gdl2199` FOREIGN KEY (`rnOthers_rnOthersId`) REFERENCES `rnothers` (`rnOthersId`),
  CONSTRAINT `FK_6xvpd8rklbm6f8bxxbxnxhi5g` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`),
  CONSTRAINT `FKi025k7hxkqwfjwwqt3xiolo5k` FOREIGN KEY (`rnSponsors_rnSponsorsId`) REFERENCES `rnsponsors` (`rnSponsorsId`),
  CONSTRAINT `FKi6j8pcdapvidrq9o16v6vq6e6` FOREIGN KEY (`rnTotal_rnTotalId`) REFERENCES `rntotal` (`rnTotalId`),
  CONSTRAINT `FKnpnvai2txm0tv0aea611ukq08` FOREIGN KEY (`rnResearch_rnResearchId`) REFERENCES `rnresearch` (`rnResearchId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.rndocument: ~0 rows (approximately)
/*!40000 ALTER TABLE `rndocument` DISABLE KEYS */;
/*!40000 ALTER TABLE `rndocument` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.rnothers
CREATE TABLE IF NOT EXISTS `rnothers` (
  `rnOthersId` int(11) NOT NULL AUTO_INCREMENT,
  `advancePayment` float DEFAULT NULL,
  `externalFunding` float DEFAULT NULL,
  `externalFundingIdentification` varchar(255) DEFAULT NULL,
  `other` float DEFAULT NULL,
  `otherIdentification` varchar(255) DEFAULT NULL,
  `structuralFunds` float DEFAULT NULL,
  `structuralFundsIdentification` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rnOthersId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.rnothers: ~0 rows (approximately)
/*!40000 ALTER TABLE `rnothers` DISABLE KEYS */;
/*!40000 ALTER TABLE `rnothers` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.rnproduct
CREATE TABLE IF NOT EXISTS `rnproduct` (
  `rnProductId` int(11) NOT NULL AUTO_INCREMENT,
  `codCPV` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nrCrt` int(11) DEFAULT NULL,
  `pricePerUnit` float DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `totalPrice` float DEFAULT NULL,
  `um` varchar(255) DEFAULT NULL,
  `rnDocumentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`rnProductId`),
  KEY `FK8i3egfs905pk87llfacelt4aq` (`rnDocumentId`),
  CONSTRAINT `FK8i3egfs905pk87llfacelt4aq` FOREIGN KEY (`rnDocumentId`) REFERENCES `rndocument` (`DocumentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.rnproduct: ~0 rows (approximately)
/*!40000 ALTER TABLE `rnproduct` DISABLE KEYS */;
/*!40000 ALTER TABLE `rnproduct` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.rnresearch
CREATE TABLE IF NOT EXISTS `rnresearch` (
  `rnResearchId` int(11) NOT NULL AUTO_INCREMENT,
  `nationalFunds1` float DEFAULT NULL,
  `nationalFunds1Identification` varchar(255) DEFAULT NULL,
  `nationalFunds2` float DEFAULT NULL,
  `nationalFunds2Identification` varchar(255) DEFAULT NULL,
  `ternaryContracts` float DEFAULT NULL,
  `ternaryContractsIdentification` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rnResearchId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.rnresearch: ~0 rows (approximately)
/*!40000 ALTER TABLE `rnresearch` DISABLE KEYS */;
/*!40000 ALTER TABLE `rnresearch` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.rnsponsors
CREATE TABLE IF NOT EXISTS `rnsponsors` (
  `rnSponsorsId` int(11) NOT NULL AUTO_INCREMENT,
  `amount` float DEFAULT NULL,
  `sponsorName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rnSponsorsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.rnsponsors: ~0 rows (approximately)
/*!40000 ALTER TABLE `rnsponsors` DISABLE KEYS */;
/*!40000 ALTER TABLE `rnsponsors` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.rntotal
CREATE TABLE IF NOT EXISTS `rntotal` (
  `rnTotalId` int(11) NOT NULL AUTO_INCREMENT,
  `totalPrice` float DEFAULT NULL,
  `totalPricePerUnit` float DEFAULT NULL,
  `totalQuantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`rnTotalId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cwmd_db.rntotal: ~0 rows (approximately)
/*!40000 ALTER TABLE `rntotal` DISABLE KEYS */;
/*!40000 ALTER TABLE `rntotal` ENABLE KEYS */;

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

-- Dumping data for table cwmd_db.user: ~4 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`Username`, `Password`, `Role`, `userInfo_EntryID`) VALUES
	('admin', 'admin', 0, 1),
	('asdf', 'asdf', 0, 2),
	('radu', 'radu', 1, 2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table cwmd_db.user_details
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

-- Dumping data for table cwmd_db.user_details: ~3 rows (approximately)
/*!40000 ALTER TABLE `user_details` DISABLE KEYS */;
INSERT INTO `user_details` (`EntryID`, `FirstName`, `LastName`, `Address`, `Email`, `PhoneNumber`, `IsDepartmentChief`) VALUES
	(1, 'Awesome', 'McAwesomesauce', '42A Awesome st', 'senekrum@gmail.com', 743760319, 1),
	(2, 'Radu', 'Corbu', 'N/A', 'corburadu@hotmail.com', 743760319, 0);
/*!40000 ALTER TABLE `user_details` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
