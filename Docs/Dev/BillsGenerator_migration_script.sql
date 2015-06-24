-- ----------------------------------------------------------------------------
-- MySQL Workbench Migration
-- Migrated Schemata: dbo
-- Source Schemata: dbo
-- Created: Thu Jun 11 10:12:14 2015
-- ----------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;;

-- ----------------------------------------------------------------------------
-- Schema dbo
-- ----------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `dbo` ;
CREATE SCHEMA IF NOT EXISTS `dbo` ;

-- ----------------------------------------------------------------------------
-- Table dbo.MeterM
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`MeterM` (
  `MetID` VARCHAR(10) NOT NULL,
  `MetNumber` VARCHAR(30) NOT NULL,
  `MtrMkID` INT NOT NULL,
  `MetSizeID` INT NULL,
  `Description` VARCHAR(500) NULL,
  `UserID` INT NOT NULL,
  `DTStamp` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`MetID`),
  CONSTRAINT `FK__MeterM__MtrMkID__2A4B4B5E`
    FOREIGN KEY (`MtrMkID`)
    REFERENCES `dbo`.`GeneralMaster` (`GenID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK__MeterM__UserID__2B3F6F97`
    FOREIGN KEY (`UserID`)
    REFERENCES `dbo`.`Users` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table dbo.MeterSizes
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`MeterSizes` (
  `MeterSizeID` INT NOT NULL,
  `SzShtName` VARCHAR(20) NULL,
  `SzLName` VARCHAR(150) NULL,
  `Rent` DECIMAL(9,2) NOT NULL,
  `Description` VARCHAR(500) NULL,
  PRIMARY KEY (`MeterSizeID`));

-- ----------------------------------------------------------------------------
-- Table dbo.MeterConn
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`MeterConn` (
  `MeterID` VARCHAR(10) NOT NULL,
  `ConnID` INT NOT NULL,
  `TappingID` INT NOT NULL,
  `EffDt` DATETIME NOT NULL,
  CONSTRAINT `FK__MeterConn__ConnI__75A278F5`
    FOREIGN KEY (`ConnID`)
    REFERENCES `dbo`.`GeneralMaster` (`GenID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK__MeterConn__Meter__398D8EEE`
    FOREIGN KEY (`MeterID`)
    REFERENCES `dbo`.`MeterM` (`MetID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK__MeterConn__Tappi__778AC167`
    FOREIGN KEY (`TappingID`)
    REFERENCES `dbo`.`GeneralMaster` (`GenID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table dbo.Roles
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`Roles` (
  `RoleID` INT NOT NULL,
  `Role` VARCHAR(50) NOT NULL,
  `Description` VARCHAR(500) NULL,
  PRIMARY KEY (`RoleID`));

-- ----------------------------------------------------------------------------
-- Table dbo.SewPrc
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`SewPrc` (
  `SewagePrc` INT NOT NULL DEFAULT 25,
  PRIMARY KEY (`SewagePrc`));

-- ----------------------------------------------------------------------------
-- Table dbo.Tapping
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`Tapping` (
  `SchemeID` INT NOT NULL,
  `TappingID` VARCHAR(2) NOT NULL,
  `SHName` VARCHAR(20) NULL DEFAULT 0,
  PRIMARY KEY (`SchemeID`, `TappingID`));

-- ----------------------------------------------------------------------------
-- Table dbo.Consumer
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`Consumer` (
  `ConsID` INT NOT NULL,
  `ConsCode` VARCHAR(30) NOT NULL,
  `PrefixID` INT NOT NULL,
  `ConsLName` VARCHAR(150) NOT NULL,
  `ConsAddr1` VARCHAR(75) NOT NULL,
  `ConsAddr2` VARCHAR(75) NULL,
  `ConsAddr3` VARCHAR(75) NULL,
  `ConsPhoneNo` VARCHAR(50) NULL,
  `ConsStatus` TINYINT(1) NULL DEFAULT 1,
  `ConsMembers` INT NOT NULL DEFAULT 1,
  `ConsRegDate` DATETIME NULL,
  `WardID` INT NOT NULL,
  `ReligionID` INT NOT NULL,
  `UserID` INT NOT NULL,
  `DTStamp` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `UrbanRural` TINYINT(1) NULL,
  `MinAvgUnit` INT NULL,
  `ConsCatID` INT NULL DEFAULT 0,
  `ConsSewage` TINYINT UNSIGNED NULL DEFAULT 0,
  `AvgSewUnit` INT NULL,
  `ZoneID` INT NOT NULL,
  `TappingId` VARCHAR(50) NULL,
  `ReservoirId` VARCHAR(2) NULL,
  `SundryChrgs` INT NULL,
  `TmpDate` DATETIME NULL,
  PRIMARY KEY (`ConsID`),
  CONSTRAINT `FK__Consumer__Prefix__117F9D94`
    FOREIGN KEY (`PrefixID`)
    REFERENCES `dbo`.`GeneralMaster` (`GenID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK__Consumer__Religi__15502E78`
    FOREIGN KEY (`ReligionID`)
    REFERENCES `dbo`.`Religion` (`ReligionID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK__Consumer__UserID__164452B1`
    FOREIGN KEY (`UserID`)
    REFERENCES `dbo`.`Users` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK__Consumer__WardID__145C0A3F`
    FOREIGN KEY (`WardID`)
    REFERENCES `dbo`.`Ward` (`WardID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Consumer_Zone_R1`
    FOREIGN KEY (`ZoneID`)
    REFERENCES `dbo`.`Zone` (`ZoneID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table dbo.MeterStatus
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`MeterStatus` (
  `MetID` VARCHAR(10) NOT NULL,
  `MeterState` TINYINT(1) NOT NULL DEFAULT 1,
  `MeterFaultID` INT NULL,
  `Condition` TINYINT(1) NOT NULL DEFAULT 1,
  `MtrFaultDt` DATETIME NULL,
  CONSTRAINT `FK__MeterStat__Meter__36B12243`
    FOREIGN KEY (`MeterFaultID`)
    REFERENCES `dbo`.`GeneralMaster` (`GenID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table dbo.Tariffs
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`Tariffs` (
  `TariffID` INT NOT NULL,
  `TariffCode` INT NOT NULL DEFAULT 0,
  `FromUnit` INT NOT NULL DEFAULT 0,
  `ToUnit` INT NOT NULL DEFAULT 0,
  `Rate` DECIMAL(9,2) NOT NULL DEFAULT 0,
  `MinChrg` INT NOT NULL DEFAULT 0,
  `EffDate` DATETIME NULL,
  PRIMARY KEY (`TariffID`));

-- ----------------------------------------------------------------------------
-- Table dbo.Users
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`Users` (
  `UserID` INT NOT NULL,
  `UserSHName` VARCHAR(20) NOT NULL,
  `UserLName` VARCHAR(50) NOT NULL,
  `Password` VARCHAR(150) NOT NULL,
  `Description` VARCHAR(500) NULL,
  `RoleID` INT NOT NULL,
  `DTStamp` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `IsMeterReader` TINYINT(1) NULL,
  `EMail` VARCHAR(50) NULL,
  `IsActive` TINYINT(1) NULL,
  PRIMARY KEY (`UserID`),
  CONSTRAINT `FK__Users__RoleID__06CD04F7`
    FOREIGN KEY (`RoleID`)
    REFERENCES `dbo`.`Roles` (`RoleID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK__Users__RoleID__4A8310C6`
    FOREIGN KEY (`RoleID`)
    REFERENCES `dbo`.`Roles` (`RoleID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table dbo.Ward
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`Ward` (
  `WardID` INT NOT NULL,
  `WardSHName` VARCHAR(20) NOT NULL,
  `WardLName` VARCHAR(50) NOT NULL,
  `ZoneID` INT NOT NULL,
  `Description` VARCHAR(500) NULL,
  `UserID` INT NOT NULL,
  `DTStamp` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`WardID`),
  CONSTRAINT `FK__Ward__UserID__07C12930`
    FOREIGN KEY (`UserID`)
    REFERENCES `dbo`.`Users` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK__Ward__ZoneID__08B54D69`
    FOREIGN KEY (`ZoneID`)
    REFERENCES `dbo`.`Zone` (`ZoneID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK__Ward__ZoneID__4C6B5938`
    FOREIGN KEY (`ZoneID`)
    REFERENCES `dbo`.`Zone` (`ZoneID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK__Ward__UserID__4B7734FF`
    FOREIGN KEY (`UserID`)
    REFERENCES `dbo`.`Users` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table dbo.Zone
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`Zone` (
  `ZoneID` INT NOT NULL,
  `ZoneSHName` VARCHAR(20) NOT NULL,
  `ZoneLName` VARCHAR(50) NOT NULL,
  `SubDivID` INT NOT NULL,
  `Description` VARCHAR(500) NULL,
  `UserID` INT NOT NULL,
  `DTStamp` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `ConstituencyID` INT NULL,
  `UrbanRural` TINYINT(1) NULL,
  `SchemeId` INT NULL,
  `ZoneVill` VARCHAR(100) NULL,
  `ZoneJE` VARCHAR(100) NULL,
  PRIMARY KEY (`ZoneID`),
  CONSTRAINT `FK__Zone__Constituen__09A971A2`
    FOREIGN KEY (`ConstituencyID`)
    REFERENCES `dbo`.`GeneralMaster` (`GenID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK__Zone__SubDivID__0A9D95DB`
    FOREIGN KEY (`SubDivID`)
    REFERENCES `dbo`.`Division` (`DivID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK__Zone__UserID__0B91BA14`
    FOREIGN KEY (`UserID`)
    REFERENCES `dbo`.`Users` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK__Zone__Constituen__4D5F7D71`
    FOREIGN KEY (`ConstituencyID`)
    REFERENCES `dbo`.`GeneralMaster` (`GenID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK__Zone__SubDivID__4E53A1AA`
    FOREIGN KEY (`SubDivID`)
    REFERENCES `dbo`.`Division` (`DivID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK__Zone__UserID__4F47C5E3`
    FOREIGN KEY (`UserID`)
    REFERENCES `dbo`.`Users` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table dbo.Division
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`Division` (
  `DivID` INT NOT NULL,
  `DivSHName` VARCHAR(20) NOT NULL,
  `DivLName` VARCHAR(50) NOT NULL,
  `PDivID` INT NULL,
  `Description` VARCHAR(500) NULL,
  `UserID` INT NOT NULL,
  `DTStamp` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`DivID`),
  CONSTRAINT `FK__Division__PDivID__3E1D39E1`
    FOREIGN KEY (`PDivID`)
    REFERENCES `dbo`.`Division` (`DivID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK__Division__UserID__3F115E1A`
    FOREIGN KEY (`UserID`)
    REFERENCES `dbo`.`Users` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK__Division__PDivID__73BA3083`
    FOREIGN KEY (`PDivID`)
    REFERENCES `dbo`.`Division` (`DivID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK__Division__UserID__74AE54BC`
    FOREIGN KEY (`UserID`)
    REFERENCES `dbo`.`Users` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table dbo.DataDownload
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`DataDownload` (
  `DownLoadID` BIGINT NOT NULL AUTO_INCREMENT,
  `CycleID` INT NULL DEFAULT 0,
  `BillID` BIGINT NULL,
  `ZoneId` INT NULL,
  `BillNumber` VARCHAR(4) NULL,
  `ConsID` INT NULL,
  `ConsCatID` INT NULL,
  `ConsMembers` INT NULL,
  `MeterDID` INT NULL,
  `FromDate` DATETIME NULL,
  `IssueDate` DATETIME NULL,
  `CyclePeriod` INT NULL,
  `WardID` INT NULL,
  `PymtDueDate` DATETIME NULL,
  `MeterRent` INT NULL,
  `MinUnits` INT NULL,
  `Avgunits` INT NULL,
  `SecDeposit` INT NULL,
  `CummulativeUnits` INT NULL,
  `PrevReading` INT NULL,
  `CurrReading` INT NULL,
  `UnitsBill` INT NULL,
  `AddlUnits` INT NULL,
  `Lpcd` INT NULL,
  `BillBase` CHAR(1) NULL,
  `PrevCycUnits` INT NULL,
  `PrevCycDays` INT NULL,
  `MeterFault` TINYINT UNSIGNED NULL,
  `MeterFaultDate` DATETIME NULL,
  `ReadingRepeatCnt` INT NULL,
  `WaterChrgs` INT NULL,
  `SundryChrgs` INT NULL,
  `ArrCredit` INT NULL,
  `ConsSewage` TINYINT UNSIGNED NULL,
  `SewPrc` DECIMAL(9,2) NULL,
  `SewChrgs` INT NULL,
  `TotBillAmt` DECIMAL(9,2) NULL,
  `CarryFwd` DECIMAL(9,2) NULL,
  `Dpc` DECIMAL(9,2) NULL,
  `PaidAmt` DECIMAL(9,2) NULL,
  `PaymDate` DATETIME NULL,
  `ModePaym` VARCHAR(10) NULL,
  `BankID` INT NULL,
  `UserID` INT NULL,
  `SchemeID` INT NULL,
  `TappingID` VARCHAR(2) NULL,
  `ReservoirID` VARCHAR(2) NULL,
  `ConstituencyID` INT NULL,
  `UrbanRural` TINYINT(1) NULL,
  `RemarkBit` TINYINT UNSIGNED NULL DEFAULT 0,
  `MeterNumber` VARCHAR(10) NULL,
  `MetSizeID` INT NULL,
  `MeterFixDate` DATETIME NULL,
  `SubDivID` INT NULL,
  `PymtDtStatus` TINYINT(1) NULL,
  `ConnStatus` VARCHAR(1) NULL,
  `ConnStatusDate` DATETIME NULL,
  `DpcNext` DECIMAL(9,2) NULL,
  `IssueTime` DATETIME NULL,
  `MachineID` VARCHAR(5) NULL,
  `ConsID1` INT NULL,
  `IssueDate1` DATETIME NULL,
  `CyclePeriod1` INT NULL,
  `PaymDueDate1` DATETIME NULL,
  `MeterRent1` INT NULL,
  `AverageUnit1` INT NULL,
  `CummUnit1` INT NULL,
  `CurrReading1` INT NULL,
  `UnitsBill1` INT NULL,
  `AddlUnits1` INT NULL,
  `Lpcd1` INT NULL,
  `BillBase1` VARCHAR(1) NULL,
  `MeterFault1` VARCHAR(1) NULL,
  `FaultDate1` DATETIME NULL,
  `ReadRepeatCnt1` INT NULL,
  `WaterChrg1` INT NULL,
  `SewageChrg1` INT NULL,
  `TotBillAmt1` INT NULL,
  `ConnStatus1` VARCHAR(1) NULL,
  `ConnStatusDate1` DATETIME NULL,
  `IssueTime1` DATETIME NULL,
  PRIMARY KEY (`DownLoadID`));


-- ----------------------------------------------------------------------------
-- Table dbo.ObjectMst
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`ObjectMst` (
  `ObjectTypeCd` VARCHAR(5) NOT NULL,
  `ObjectCd` VARCHAR(30) NOT NULL,
  `ObjectName` VARCHAR(50) NULL,
  PRIMARY KEY (`ObjectTypeCd`, `ObjectCd`));

-- ----------------------------------------------------------------------------
-- Table dbo.UserRights
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`UserRights` (
  `UserSHName` VARCHAR(20) NOT NULL,
  `ObjectTypeCd` VARCHAR(5) NOT NULL,
  `ObjectCd` VARCHAR(30) NOT NULL,
  `CanView` TINYINT(1) NULL,
  `CanAdd` TINYINT(1) NULL,
  `CanChange` TINYINT(1) NULL,
  `CanDel` TINYINT(1) NULL,
  PRIMARY KEY (`UserSHName`, `ObjectTypeCd`, `ObjectCd`));



-- ----------------------------------------------------------------------------
-- Table dbo.Bill
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`Bill` (
  `CycleID` INT NOT NULL DEFAULT 0, 
  `BillID` BIGINT NOT NULL AUTO_INCREMENT, 
  `ZoneId` INT NULL,
  `BillNumber` VARCHAR(4) NOT NULL,
  `ConsID` INT NULL,
  `ConsCatID` INT NULL,
  `ConsMembers` INT NULL,
  `MeterDID` INT NULL,
  `FromDate` DATETIME NULL,
  `IssueDate` DATETIME NULL,
  `CyclePeriod` INT NULL,
  `WardID` INT NULL,
  `PymtDueDate` DATETIME NULL,
  `MeterRent` INT NULL,
  `MinUnits` INT NULL,
  `Avgunits` INT NULL,
  `MeterState` TINYINT(1) NULL DEFAULT 0,
  `SecDeposit` INT NULL,
  `CummulativeUnits` INT NULL,
  `PrevReading` INT NULL,
  `CurrReading` INT NULL,
  `UnitsBill` INT NULL,
  `AddlUnits` INT NULL,
  `Lpcd` INT NULL,
  `BillBase` CHAR(1) NULL,
  `PrevCycUnits` INT NULL,
  `PrevCycDays` INT NULL,
  `MeterFault` TINYINT UNSIGNED NULL,
  `MeterFaultDate` DATETIME NULL,
  `ReadingRepeatCnt` INT NULL,
  `WaterChrgs` INT NULL,
  `SundryChrgs` INT NULL,
  `ArrCredit` INT NULL,
  `ConsSewage` TINYINT UNSIGNED NULL,
  `SewPrc` DECIMAL(9,2) NULL,
  `SewChrgs` INT NULL,
  `TotBillAmt` BIGINT NULL,
  `CarryFwd` BIGINT NULL,
  `Dpc` BIGINT NULL,
  `PaidAmt` BIGINT NULL,
  `PaymDate` DATETIME NULL,
  `ModePaym` VARCHAR(10) NULL,
  `BankID` INT NULL,
  `UserID` INT NOT NULL,
  `SchemeID` INT NULL,
  `TappingID` VARCHAR(2) NULL,
  `ReservoirID` VARCHAR(2) NULL,
  `ConstituencyID` INT NULL,
  `UrbanRural` TINYINT(1) NOT NULL,
  `RemarkBit` TINYINT UNSIGNED NOT NULL DEFAULT 0,
  `MeterNumber` VARCHAR(10) NULL,
  `MetSizeID` INT NULL,
  `MeterFixDate` DATETIME NULL,
  `SubDivID` INT NULL,
  `PymtDtStatus` TINYINT(1) NULL,
  `ConnStatus` VARCHAR(1) NULL,
  `ConnStatusDate` DATETIME NULL,
  `DpcNext` DECIMAL(9,2) NULL,
  `IssueTime` DATETIME NULL,
  `IssueDateOld` DATETIME NULL,
  PRIMARY KEY (`BillID`));

-- ----------------------------------------------------------------------------
-- Table dbo.RepMeterCondition
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`RepMeterCondition` (
  `ConsCatID` INT NOT NULL DEFAULT 0,
  `Code` VARCHAR(10) NULL,
  `Category` VARCHAR(50) NULL,
  `ZoneName` VARCHAR(50) NULL,
  `ZoneVill` VARCHAR(50) NULL,
  `ZoneJE` VARCHAR(50) NULL,
  `DivName` VARCHAR(50) NULL,
  `SubDivName` VARCHAR(50) NULL,
  `CondCurr` INT NULL,
  `A_1Curr` INT NULL,
  `D_2Curr` INT NULL,
  `G_3Curr` INT NULL,
  `L_4Curr` INT NULL,
  `M_5Curr` INT NULL,
  `N_6Curr` INT NULL,
  `R_7Curr` INT NULL,
  `I_8Curr` INT NULL,
  `C_9Curr` INT NULL,
  `ReplCurr` INT NULL,
  `CondPrev` INT NULL,
  `A_1Prev` INT NULL,
  `D_2Prev` INT NULL,
  `G_3Prev` INT NULL,
  `L_4Prev` INT NULL,
  `M_5Prev` INT NULL,
  `N_6Prev` INT NULL,
  `R_7Prev` INT NULL,
  `I_8Prev` INT NULL,
  `C_9Prev` INT NULL,
  `ReplPrev` INT NULL,
  PRIMARY KEY (`ConsCatID`));

-- ----------------------------------------------------------------------------
-- Table dbo.dtproperties
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`dtproperties` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `objectid` INT NULL,
  `property` VARCHAR(64) NOT NULL,
  `value` VARCHAR(255) NULL,
  `uvalue` VARCHAR(255) NULL,
  `lvalue` TINYBLOB NULL,
  `version` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`, `property`));

-- ----------------------------------------------------------------------------
-- Table dbo.MeterD
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`MeterD` (
  `MeterDID` INT NOT NULL,
  `MetNumber` VARCHAR(10) NOT NULL DEFAULT 0,
  `ConsID` INT NOT NULL DEFAULT 0,
  `MtrMkID` INT NULL,
  `InitialRdg` INT NOT NULL DEFAULT 0,
  `MetSizeID` INT NOT NULL DEFAULT 0,
  `MetEffDt` DATETIME NULL,
  `MetCloseDt` DATETIME NULL,
  `MetSecDeposit` DECIMAL(9,2) NULL,
  `MinUnits` INT NOT NULL,
  `IsActive` VARCHAR(1) NULL COMMENT 'Meter State',
  `ConnStatus` VARCHAR(1) NULL DEFAULT 'E',
  `Condition` TINYINT(1) NULL,
  `MeterFaultID` INT NULL,
  `MeterFaultDt` DATETIME NULL,
  `ConnStatusDate` DATETIME NULL,
  PRIMARY KEY (`MeterDID`));

-- ----------------------------------------------------------------------------
-- Table dbo.Cumm_Units_Temp
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`Cumm_Units_Temp` (
  `zoneid` INT NULL,
  `CycleID` INT NOT NULL,
  `BillID` BIGINT NOT NULL,
  `ConsID` INT NULL,
  `MeterFault` TINYINT UNSIGNED NULL,
  `CummulativeUnits` INT NULL,
  `AddlUnits` INT NULL,
  `UnitsBill` INT NULL,
  `cycleid1` INT NOT NULL,
  `BillID1` BIGINT NOT NULL,
  `consid1` INT NULL,
  `cum_units1` INT NULL,
  `MeterFault1` TINYINT UNSIGNED NULL,
  `AddlUnits1` INT NULL,
  `UnitsBill1` INT NULL);

-- ----------------------------------------------------------------------------
-- Table dbo.AllocateDataM
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`AllocateDataM` (
  `TrsYear` VARCHAR(4) NOT NULL,
  `TrsDate` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `TrsNo` INT NOT NULL DEFAULT 0,
  `DivID` INT NOT NULL DEFAULT 0,
  `SubDivID` INT NOT NULL DEFAULT 0,
  `ZoneID` INT NOT NULL DEFAULT 0,
  `CycleID` INT NOT NULL DEFAULT 0,
  `UserID` INT NOT NULL DEFAULT 0,
  `TrsID` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`TrsID`),
  INDEX `IX_AllocateDataM` (`TrsYear` ASC, `TrsNo` ASC));

-- ----------------------------------------------------------------------------
-- Table dbo.Bank
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`Bank` (
  `BankID` INT NOT NULL,
  `BankSHName` VARCHAR(20) NOT NULL,
  `BankLName` VARCHAR(255) NOT NULL,
  `Addr1` VARCHAR(75) NOT NULL,
  `Addr2` VARCHAR(75) NULL,
  `Phone` VARCHAR(30) NULL,
  `Fax` VARCHAR(30) NULL,
  `Description` VARCHAR(500) NULL,
  `UserID` INT NOT NULL,
  `DTStamp` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`BankID`),
  CONSTRAINT `FK__Bank__UserID__395884C4`
    FOREIGN KEY (`UserID`)
    REFERENCES `dbo`.`Users` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK__Bank__UserID__66603565`
    FOREIGN KEY (`UserID`)
    REFERENCES `dbo`.`Users` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table dbo.ConSewage
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`ConSewage` (
  `ConSewageID` INT NOT NULL,
  `ConsID` INT NOT NULL,
  `ConsSewage` TINYINT(1) NOT NULL DEFAULT 1,
  `EffectiveDate` DATETIME NOT NULL,
  PRIMARY KEY (`ConSewageID`));

-- ----------------------------------------------------------------------------
-- Table dbo.ConsCat
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`ConsCat` (
  `ConsCatID` INT NOT NULL,
  `ConsID` INT NOT NULL,
  `CatID` INT NOT NULL,
  `EffectiveDate` DATETIME NOT NULL,
  PRIMARY KEY (`ConsCatID`),
  CONSTRAINT `FK__ConsCat__ConsID__24927208`
    FOREIGN KEY (`ConsID`)
    REFERENCES `dbo`.`Consumer` (`ConsID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table dbo.AllocateData
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`AllocateData` (
  `WardID` INT NOT NULL DEFAULT 0,
  `MachineID` VARCHAR(5) NOT NULL DEFAULT 0,
  `ReaderID` INT NOT NULL DEFAULT 0,
  `CycleID` INT NOT NULL DEFAULT 0,
  `TotNoOfRows` INT NOT NULL DEFAULT 0,
  `MaxBillIssDate` DATETIME NULL,
  `DownloadDate` DATETIME NULL,
  `UploadDate` DATETIME NULL,
  `TrsID` INT NOT NULL DEFAULT 0,
  `Status` TINYINT UNSIGNED NULL DEFAULT 0);

-- ----------------------------------------------------------------------------
-- Table dbo.ConsumerTypes
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`ConsumerTypes` (
  `ConCatID` INT NOT NULL,
  `CatCode` VARCHAR(5) NOT NULL,
  `CatSHName` VARCHAR(20) NOT NULL,
  `CatLName` VARCHAR(150) NULL,
  `TariffCode` INT NULL DEFAULT 0,
  `Description` VARCHAR(255) NULL,
  `UserID` INT NOT NULL,
  `DTStamp` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `LPCD` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`ConCatID`),
  CONSTRAINT `FK__ConsumerT__UserI__3D2915A8`
    FOREIGN KEY (`UserID`)
    REFERENCES `dbo`.`Users` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK__ConsumerT__UserI__72C60C4A`
    FOREIGN KEY (`UserID`)
    REFERENCES `dbo`.`Users` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



-- ----------------------------------------------------------------------------
-- Table dbo.GeneralMaster
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`GeneralMaster` (
  `GenID` INT NOT NULL,
  `GenSHName` VARCHAR(20) NOT NULL,
  `GenLName` VARCHAR(50) NULL,
  `Description` VARCHAR(500) NULL,
  `GenCodeFor` TINYINT UNSIGNED NOT NULL,
  PRIMARY KEY (`GenID`));

-- ----------------------------------------------------------------------------
-- Table dbo.Holiday
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`Holiday` (
  `TblID` INT NOT NULL DEFAULT 0,
  `HodYYYY` VARCHAR(4) NOT NULL,
  `HodMM` VARCHAR(2) NOT NULL,
  `HodDD` VARCHAR(2) NOT NULL,
  `Description` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`TblID`),
  INDEX `IX_Holiday` (`HodYYYY` ASC, `HodMM` ASC, `HodDD` ASC),
  INDEX `IX_UniqueHod` (`HodYYYY` ASC, `HodMM` ASC, `HodDD` ASC));
SET FOREIGN_KEY_CHECKS = 1;;
