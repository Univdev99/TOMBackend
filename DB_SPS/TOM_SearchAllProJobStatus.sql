USE [GT]
GO
/****** Object:  StoredProcedure [dbo].[TOM_SearchAllProJobStatus]    Script Date: 8/24/2020 12:54:18 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER   PROCEDURE [dbo].[TOM_SearchAllProJobStatus] 
		@proProfileId VARCHAR(max) = '',
		@jobStatus VARCHAR(max) = ''

AS 
  BEGIN 
      SET nocount ON; 

      DECLARE @sqlCommand  VARCHAR(max), 
              @selectClause  VARCHAR(max), 
              @sqlString   VARCHAR(max), 
              @whereClause VARCHAR(max) = ' WHERE 1=1',
              @orderBy     VARCHAR(250) = ''

	  SET @selectClause = ' SELECT DISTINCT 
							CAST(0 as BIT) as isView,
							PT.projectName,
							CONCAT(PPT.lastName,'', ''+PPT.firstName) as proName,
							PFT.requiredHours,
							PST.adminProfit * PPT.hourlyFee /100 + PPT.hourlyFee as rateByFirm,
							PPT.hourlyFee as rateByPro, 
							CASE WHEN PCT.isPaymentReceived = 1 THEN ''Completed''
							ELSE ''Ongoing'' END as projectStatus,
							PST.adminProfit
						  '

	  SET @sqlString = ' FROM  ProjectTable   PT
						 INNER JOIN ProjectScheduleTable PST ON PST.projectId  = PT.projectId
					     INNER JOIN ProjectScheduleByProTable PSBP ON PSBP.projectScheduleId = PST.projectScheduleId
						 INNER JOIN ProfessionalProfileTable PPT ON PPT.professionalProfileId= PST.proProfileId
						 INNER JOIN ProjectFinalizeTable PFT ON PFT.projectId = PST.projectId AND PFT.proProfileId = PST.proProfileId
						 LEFT  JOIN ProjectCompleteTable PCT ON PCT.projectId = PST.projectId AND PCT.proProfileId = PST.proProfileId	
					   ' 
	  PRINT (@selectClause+@sqlString+@whereClause)
	  EXEC (@selectClause+@sqlString+@whereClause)
			
END 
 --     EXEC TOM_SearchAllProJobStatus