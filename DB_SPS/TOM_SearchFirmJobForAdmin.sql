USE [GT]
GO
/****** Object:  StoredProcedure [dbo].[TOM_SearchFirmJobForAdmin]    Script Date: 8/24/2020 2:04:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER         PROCEDURE [dbo].[TOM_SearchFirmJobForAdmin] 
		

AS 
  BEGIN 
      SET nocount ON; 

      DECLARE @sqlCommand  VARCHAR(max), 
              @selectClause  VARCHAR(max), 
              @sqlString   VARCHAR(max), 
              @whereClause VARCHAR(max) = ' WHERE 1=1 ',
              @orderBy     VARCHAR(250) = ''
	  
	
	  SET @selectClause = ' SELECT DISTINCT 

							CASE WHEN isDeclineByPro = 1 OR PST.isDeclineByFirm = 1 THEN  ''Decline'' 
								 WHEN ISNULL(projectScheduleByProId,'''') = '''' THEN  ''Awaiting Pro'' 
								 WHEN ISNULL(projectScheduleByProId,'''') <>  '''' AND ISNULL(scheduleByAdmin,'''') = ''''  THEN  ''Awaiting Admin'' 
								 WHEN ISNULL(scheduleByAdmin,'''') <> '''' THEN  ''Completed''
							END as projectReqStatus,

							CASE WHEN PST.isDeclineByPro = 1 OR PST.isDeclineByFirm = 1 THEN  PST.modifiedDate 
								 WHEN ISNULL(projectScheduleByProId,'''') <>  ''''  THEN  PSBP.modifiedDate
								 ELSE PST.createdDate 
							END as projectReqDate,
							
							CASE WHEN PSBP.isCompletedByAdmin = 1 THEN ''Completed''
								 WHEN PSBP.isCompletedByAdmin = 0 AND ISNULL(scheduleByAdmin,'''') <> ''''  THEN  ''Awaiting Meeting'' 
							END as meetingStatus,

							PSBP.modifiedDate as meetingDate,

							CASE WHEN  PSBP.isDeclineByProAfterMeeting  = 1 OR PSBP.isDeclineByFirmAfterMeeting = 1 THEN ''Declined''
								 WHEN  ISNULL(PFT.projectFinalizeId,'''') =  '''' AND ISNULL(PFT.isFinalizeByFirm,0) = 0  THEN ''Awaiting Pro''
								 WHEN  ISNULL(PFT.projectFinalizeId,'''') <>  '''' AND ISNULL(PFT.isFinalizeByFirm,0) = 0  THEN ''Awaiting Firm''
								 WHEN  PFT.isFinalizeByFirm = 1 AND ISNULL(PFT.isFinalizeByAdmin,0) = 0 THEN ''Awaiting Admin''
								 WHEN  PFT.isFinalizeByAdmin = 1 THEN ''Completed''
							END as finalizedStatus,

							CASE WHEN  PSBP.isDeclineByProAfterMeeting  = 1 OR PSBP.isDeclineByFirmAfterMeeting = 1 THEN PSBP.modifiedDate
								 ELSE  PFT.modifiedDate
							END as finalizedDate,

							CASE WHEN  PAT.isDeclineByFirm  = 1 THEN ''Declined''
								 WHEN  ISNULL(PAT.projectAdditionalTimeId,'''') <>  '''' AND  ISNULL(PAT.isFinalizeByFirm,0) = 0  THEN ''Awaiting Firm''
								 WHEN  PAT.isFinalizeByFirm = 1 AND ISNULL(PAT.isFinalizeByAdmin,0) = 0 THEN ''Awaiting Admin''
								 WHEN  PAT.isFinalizeByAdmin = 1 THEN ''Completed''
							END as addtionalTimeStatus,

							PAT.modifiedDate as addtionalTimeDate,

							CASE WHEN  PCT.isCompletedByPro is null THEN ''Awaiting Pro''
								 WHEN  PCT.isCompletedByPro = 1 AND PCT.isCompletedByFirm  = 0  THEN ''Awaiting Firm''
								 WHEN  PCT.isCompletedByFirm = 1 AND PCT.isCompletedByAdmin  = 0  THEN ''Awaiting Admin''
								 WHEN  PCT.isCompletedByAdmin = 1 AND ISNULL(PCT.isPaymentReceived,0) = 0 THEN ''Awaiting Payment''
								 WHEN  PCT.isPaymentReceived = 1 THEN ''Completed''
							END  as jobCompleteStatus,
							
							PCT.modifiedDate  as jobCompleteDate,
			

							CAST(0 as BIT) as isView,
							PST.projectScheduleId,
							FT.firmProfileId,
							businessName,
							projectName,
							PT.createdDate,
							PT.hours,
							PT.projectId,
							CONCAT(PPT.lastName,'', '',PPT.firstName) as proName,
							projectScheduleByProId,
							PSBP.isCompletedByAdmin,
							isDeclineByPro,
							PPT.ProfessionalProfileId as proProfileId,
							PSBP.scheduleByAdmin,
							CAST(FORMAT(CAST(PST.scheduleDateTime1 as DATETIME),''MMMM dd, yyyy hh:mm tt'') as varchar(100)) as scheduleDateTime1,
							CAST(FORMAT(CAST(PST.scheduleDateTime2 as DATETIME),''MMMM dd, yyyy hh:mm tt'') as varchar(100)) as scheduleDateTime2,
							CAST(FORMAT(CAST(PST.scheduleDateTime3 as DATETIME),''MMMM dd, yyyy hh:mm tt'') as varchar(100)) as scheduleDateTime3,
							
							PFT.projectFinalizeId,
							PFT.requiredHours,
							PFT.deadLine,
							PFT.workScope,  PPT.picBase64, PPT.hourlyFee, 
							(select workAvailability FROM ProWorkAvailabilityTable T1 WHERE T1.ProfessionalProfileId = PPT.ProfessionalProfileId) as workAvailability,

							PCT.projectCompleteId,
							ISNULL(RT.rating,0) as firmRating,
							ISNULL(RT1.rating ,0)as proRating,


							PAT.projectAdditionalTimeId,
							PAT.requiredHours as additionalTime,
							PAT.deadLine as additionalDeadLine,
							PAT.workScope as additionalWorkScope,
							PAT.amends as additionalAmends

							
							
						  '

	  SET @sqlString = ' FROM  FirmProfileTable  FT
						 INNER JOIN ProjectTable PT ON PT.createdById = FT.UserId
						 INNER JOIN ProjectScheduleTable PST ON PST.projectId = PT.projectId
						 LEFT  JOIN ProjectScheduleByProTable PSBP ON PSBP.projectScheduleId = PST.projectScheduleId
						 LEFT  JOIN ProfessionalProfileTable PPT ON PPT.ProfessionalProfileId = PST.proProfileId 
						 LEFT  JOIN ProjectFinalizeTable PFT ON PFT.proProfileId = PPT.ProfessionalProfileId  AND PFT.projectId = PST.projectId
						 LEFT  JOIN ProjectAdditionalTimeTable PAT ON PAT.projectFinalizeId =  PFT.projectFinalizeId AND PAT.status = 1
						 LEFT  JOIN ProjectCompleteTable PCT ON PCT.proProfileId = PPT.ProfessionalProfileId  AND PCT.projectId = PST.projectId
						 LEFT  JOIN ReviewTable RT ON PST.projectId = RT.projectId AND RT.reviewFor = ''Firm''
						 LEFT  JOIN ReviewTable RT1 ON PST.projectId = RT1.projectId  AND RT1.reviewFor = ''Professional''
					   ' 
	  PRINT (@selectClause+@sqlString+@whereClause)
	  EXEC (@selectClause+@sqlString+@whereClause)
			
END
 --     EXEC TOM_SearchFirmJobForAdmin 