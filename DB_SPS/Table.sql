CREATE TABLE ProjectTable (
projectId BIGINT PRIMARY KEY Identity(1,1),
isShortOrLong varchar(30) ,
projectName varchar(500) ,
expertise varchar(MAX) ,
cityId BIGINT, 
workLocation varchar(MAX) ,
hours FLOAT,
hoursDuration varchar(30) ,
projectDescription varchar(1000) ,
targetDate DATETIME2,
createdById BIGINT,
modifiedById BIGINT,
createdDate DATETIME2,
modifiedDate DATETIME2,
status VARCHAR(10)
	
) 

DROP TABLE ProWorkAvailabilityTable
CREATE TABLE ProfessionalProfileTable  (
 ProfessionalProfileId BIGINT Primary key IDENTITY(1,1),
 FirstName VARCHAR(1028), 
 LastName VARCHAR(1028),
 Address VARCHAR(MAX),
 CityId BIGINT,
 ProvinceId BIGINT,
 ZipCode VARCHAR(MAX),
 HourlyFee FLOAT,
 UserId BIGINT,
 createdById BIGINT,
modifiedById BIGINT,
createdDate DATETIME2,
modifiedDate DATETIME2,
Status TINYINT 
 )
 
 DROP TABLE ProWorkAvailabilityTable
create TABLE  ProWorkAvailabilityTable (
ProWorkAvailabilityId BIGINT Primary key IDENTITY(1,1),
StartDate Date,
LastDate Date,
DateRange VARCHAR(250),
WorkAvailability VARCHAR(250),
LocationAvailability VARCHAR(250),
IsFullTime BIT,
professionalProfileId BIGINT
)
)

 DROP TABLE ProWorkExperienceTable
create TABLE  ProWorkExperienceTable (
ProWorkExperienceId BIGINT Primary key IDENTITY(1,1),
SelfDescription VARCHAR(MAX),
IsCA BIT,
ProHighLights VARCHAR(MAX),
ProfessionalProfileId BIGINT,
 CreatedById BIGINT,
 ModifiedById BIGINT,
 CreatedDate DATETIME,
 ModifiedDate DATETIME,
 Status BIT
)

DROP TABLE ProEducationTable
 create TABLE  ProEducationTable (
 ProEducationId BIGINT Primary key IDENTITY(1,1),
 Year VARCHAR(10),
 Degree VARCHAR(MAX),
 ProWorkExperienceId BIGINT
)

DROP TABLE ProStrengthTable
 create TABLE  ProStrengthTable (
 professionalStrengthId BIGINT Primary key IDENTITY(1,1),
 Year VARCHAR(10),
 Degree VARCHAR(MAX),
 ProWorkExperienceId BIGINT
)


 DROP TABLE ProWorkExperienceDetailTable
 create TABLE  ProWorkExperienceDetailTable (
 ProWorkExperienceDetailId BIGINT Primary key IDENTITY(1,1),
 StartYear VARCHAR(10),
 EndYear VARCHAR(10),
 IsAccountingFirm BIT,
 PosTitle varchar(1028),
 FirmType VARCHAR(1028),
 Company VARCHAR(1028),
 ProWorkExperienceId BIGINT,

)

DROP TABLE  ProSoftwareKnowledgeTable
CREATE TABLE  ProSoftwareKnowledgeTable (
proSoftwateKnowledgeId BIGINT Primary key IDENTITY(1,1),
 knowledge VARCHAR(1000),
     otherValue1 VARCHAR(1000),
	 otherValue2 VARCHAR(1000),
	 otherValue3 VARCHAR(1000),
	 otherValue4 VARCHAR(1000),
	 otherValue5 VARCHAR(1000),
 proWorkExperienceId BIGINT,
)


DROP TABLE DocumentTable
create TABLE  DocumentTable (
documentId BIGINT Primary key IDENTITY(1,1),
docTitle VARCHAR(MAX),
 docPath VARCHAR(MAX),
 moduleName VARCHAR(MAX),
 source VARCHAR(MAX),
 transactionId BIGINT,
 createdById BIGINT,
 modifiedById BIGINT,
 createdDate DATETIME,
 modifiedDate DATETIME,
 status BIT
)

DROP TABLE  ProSkillMasterTable
CREATE TABLE  ProSkillMasterTable (
ProSkillMasterId BIGINT Primary key IDENTITY(1,1),
MasterSkillName VARCHAR(MAX)
)

DROP TABLE  ProSkillCategoryTable
CREATE TABLE  ProSkillCategoryTable (
proSkillCategoryId BIGINT Primary key IDENTITY(1,1),
categorySkillName VARCHAR(MAX),
proSkillMasterId BIGINT 
)

DROP TABLE  ProSkillDetailTable
CREATE TABLE  ProSkillDetailTable (
proSkillDetailId BIGINT Primary key IDENTITY(1,1),
detailSkillName VARCHAR(MAX),
proSkillCategoryId BIGINT ,
proSkillMasterId BIGINT
)

DROP TABLE  ProSkillTable
CREATE TABLE  ProSkillTable (
ProSkillId BIGINT Primary key IDENTITY(1,1),
proSkillMasterId BIGINT,
proSkillCategoryId BIGINT,
proSkillDetailId BIGINT,
ProSkillDetailId BIGINT,=
)

DROP TABLE  ProjectScheduleTable
CREATE TABLE  ProjectScheduleTable (
projectScheduleId BIGINT Primary key IDENTITY(1,1),
firmProfileId BIGINT,
proProfileId BIGINT,
projectId BIGINT,
scheduleDateTime1 DATETIME,
scheduleDateTime2 DATETIME,
scheduleDateTime3 DATETIME,
isDeclineByPro BIT,
isDeclineByFirm BIT,
adminProfit REAL,
createdById BIGINT,
modifiedById BIGINT,
createdDate DATETIME2,
modifiedDate DATETIME2,
status TINYINT 
)

CREATE TABLE  projectScheduleByProTable (
	 projectScheduleByProId BIGINT Primary key IDENTITY(1,1),
	 projectScheduleId BIGINT,
	 scheduleDateTime1 DATETIME,
	 scheduleDateTime2 DATETIME,
	 scheduleDateTime3 DATETIME,
	 isNotAvailable BIT ,
	 isCompletedByAdmin BIT ,
	 scheduleByAdmin DATETIME
	 isDeclineByProAfterMeeting BIT,
     isDeclineByFirmAfterMeeting BIT,
	 createdById BIGINT,
     modifiedById BIGINT,
     createdDate DATETIME2,
     modifiedDate DATETIME2,
     status TINYINT 
  )
  
  CREATE TABLE ProjectFinalizeTable(
  projectFinalizeId BIGINT Primary key IDENTITY(1,1),
  projectId BIGINT,
  proProfileId BIGINT,
  requiredHours FLOAT,
  deadLine DATE,
  workScope VARCHAR(MAX),
  isFinalizeByFirm BIT,
  isFinalizeByAdmin BIT,
  amends VARCHAR(MAX),
  createdById BIGINT,
  modifiedById BIGINT,
  createdDate DATETIME2,
  modifiedDate DATETIME2,
  )
  
  CREATE TABLE ProjectCompleteTable (
	projectCompleteId BIGINT PRIMARY KEY Identity(1,1),
	projectId BIGINT,
    firmProfileId BIGINT,
    proProfileId BIGINT,
    isCompletedByPro BIT,
    isCompletedByFirm  BIT,
    isCompletedByAdmin BIT,
    isPaymentReceived BIT,
	createdById BIGINT,
	modifiedById BIGINT,
	createdDate DATETIME2,
	modifiedDate DATETIME2,
)


CREATE TABLE HelpTable (
helpId BIGINT PRIMARY KEY Identity(1,1),
firstName varchar(30) ,
email varchar(100) ,
subject varchar(500) ,
message varchar(MAX) ,
createdById BIGINT,
modifiedById BIGINT,
createdDate DATETIME2,
modifiedDate DATETIME2,
) 

CREATE TABLE AdminProfitTable  (
 adminProfitId BIGINT Primary key IDENTITY(1,1),
 profit INT, 
)


CREATE TABLE ReviewTable  (
 reviewId BIGINT Primary key IDENTITY(1,1),
 projectId BIGINT, 
 firmProfileId BIGINT, 
 proProfileId BIGINT, 
 reviewFor VARCHAR(50), 
 rating Real,
createdById BIGINT,
modifiedById BIGINT,
createdDate DATETIME2,
modifiedDate DATETIME2
)


DROP TABLE NotificationTable
CREATE TABLE NotificationTable (
notificationId	bigint PRIMARY KEY IDENTITY(1,1),
notificationMessage	varchar(MAX),
toUserId	bigint,
fromUserId	bigint,
isRead	tinyint,
createdById	bigint,
modifiedById	bigint,
createdDate	datetime2,
modifiedDate	datetime2,
status	tinyint)

CREATE TABLE ProjectAdditionalTimeTable(
  projectAdditionalTimeId BIGINT Primary key IDENTITY(1,1),
  projectFinalizeId BIGINT,
  projectId BIGINT,
  proProfileId BIGINT,
  requiredHours FLOAT,
  deadLine DATE,
  workScope VARCHAR(MAX),
  isFinalizeByFirm BIT,
  isDeclineByFirm BIT,
  isFinalizeByAdmin BIT,
  amends VARCHAR(MAX),
  status tinyint,
  createdById BIGINT,
  modifiedById BIGINT,
  createdDate DATETIME2,
  modifiedDate DATETIME2,
  )
  
  CREATE TABLE CalenderTable  (
 calenderId BIGINT Primary key IDENTITY(1,1),
 eventTitle VARCHAR(MAX),
 eventDateTime  DATETIME2,
 description VARCHAR(MAX),
 createdById BIGINT,
modifiedById BIGINT,
createdDate DATETIME2,
modifiedDate DATETIME2
 )
 
 
 
DROP TABLE ProvinceMasterTable
CREATE TABLE ProvinceMasterTable (
provinceId	bigint PRIMARY KEY Identity(1,1),
provinceName	varchar(MAX)
)

DROP TABLE CityMasterTable
CREATE TABLE CityMasterTable (
cityId	bigint PRIMARY KEY Identity(1,1),
cityName	varchar(MAX),
provinceId	bigint ,
)

INSERT INTO ProvinceMasterTable  VALUES ('Alberta')
INSERT INTO ProvinceMasterTable  VALUES ('British Columbia')
INSERT INTO ProvinceMasterTable  VALUES ('Manitoba')
INSERT INTO ProvinceMasterTable  VALUES ('New Brunswick')
INSERT INTO ProvinceMasterTable  VALUES ('Newfoundland and Labrador')
INSERT INTO ProvinceMasterTable  VALUES ('Northwest Territories')
INSERT INTO ProvinceMasterTable  VALUES ('Nova Scotia')
INSERT INTO ProvinceMasterTable  VALUES ('Nunavut')
INSERT INTO ProvinceMasterTable  VALUES ('Ontario')
INSERT INTO ProvinceMasterTable  VALUES ('Prince Edward Island')
INSERT INTO ProvinceMasterTable  VALUES ('Quebec')
INSERT INTO ProvinceMasterTable  VALUES ('Saskatchewan')
INSERT INTO ProvinceMasterTable  VALUES ('Yukon')
INSERT INTO ProvinceMasterTable  VALUES ('Vaughan')