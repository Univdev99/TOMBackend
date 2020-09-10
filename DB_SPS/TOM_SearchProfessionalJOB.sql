USE [GT]
GO
/****** Object:  StoredProcedure [dbo].[TOM_SearchProfessionalJOB]    Script Date: 8/17/2020 4:04:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER       PROCEDURE [dbo].[TOM_SearchProfessionalJOB] 
		@proProfileId VARCHAR(max) = '',
		@jobStatus VARCHAR(max) = ''

AS 
  BEGIN 
      SET nocount ON; 

      DECLARE @sqlCommand  VARCHAR(max), 
              @selectClause  VARCHAR(max), 
              @sqlString   VARCHAR(max), 
              @whereClause VARCHAR(max) = ' WHERE PST.proProfileId ='+@proProfileId, 
              @orderBy     VARCHAR(250) = ''
	  
	  IF(ISNULL(@jobStatus,'') <> '' AND @jobStatus = 'Requested')
	  BEGIN
		--SET @whereClause += ' and isAcceptByPro = 0 AND isAcceptByFirm = 0 AND isAcceptByAdmin = 0 '
		PRINT @whereClause
		
	  END
	  SET @selectClause = ' SELECT DISTINCT 
							CAST(0 as BIT) as isView,
							FT.businessName,
							PST.projectScheduleId,
							CASE WHEN PSBP.isCompletedByAdmin = 1 THEN ''Pending Approval''
								 WHEN ISNULL(PSBP.scheduleByAdmin,'''') <> '''' OR PSBP.isCompletedByAdmin = 0 THEN ''Pending Meeting''
							ELSE ''Pending Review'' END as jobStatus,
							
							PT.*,
							projectFinalizeId
						  '

	  SET @sqlString = ' FROM  ProjectScheduleTable  PST
						 LEFT  JOIN  ProjectScheduleByProTable PSBP ON PSBP.projectScheduleId = PST.projectScheduleId
						 INNER JOIN FirmProfileTable FT ON FT.firmProfileId= PST.firmProfileId
						 INNER JOIN ProjectTable PT ON PT.ProjectId= PST.ProjectId
						 LEFT JOIN ProjectFinalizeTable PFT ON PFT.projectId = PST.projectId
					   ' 
	  PRINT (@selectClause+@sqlString+@whereClause)
	  EXEC (@selectClause+@sqlString+@whereClause)
			
END 
 --     EXEC TOM_SearchProfessionalJOB @proProfileId = '15', @jobStatus = 'Requested'