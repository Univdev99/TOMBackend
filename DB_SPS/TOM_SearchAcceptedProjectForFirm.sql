USE [GT]
GO
/****** Object:  StoredProcedure [dbo].[TOM_SearchAcceptedProjectForFirm]    Script Date: 8/24/2020 2:06:19 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER    PROCEDURE [dbo].[TOM_SearchAcceptedProjectForFirm] 
	
	@argUserId VARCHAR(10) = NULL

AS 
  BEGIN 
      SET nocount ON; 
      DECLARE @sqlCommand  VARCHAR(max), 
              @selectClause  VARCHAR(max), 
              @sqlString   VARCHAR(max), 
              @whereClause VARCHAR(max) = ' WHERE  PT.status = ''Accepted'' ',
              @orderBy     VARCHAR(250) = ''
	  
	  IF(ISNULL(@argUserId,'') <> '')
	  BEGIN
		SET @whereClause = @whereClause + ' AND PT.createdById = '+@argUserId
	  END
	  SET @selectClause = ' SELECT
							PT.projectName,PT.createdDate,PT.projectId,
							PFT.requiredHours,PFT.deadLine,PFT.workScope, 
							PCT.isCompletedByPro, PCT.projectCompleteId, PCT.isCompletedByFirm,
							CONCAT(PPT.lastName,'', ''+PPT.firstName) as proName,
							PPT.lastName as proLastName,
							PPT.firstName  as proFirstName,
							PPT.picBase64,
							(select AVG(rating) from ReviewTable RT WHERE  PPT.professionalProfileId = RT.proProfileId  AND RT.reviewFor=''Professional'') as avgProRating,
							(select COUNT(1)    from ReviewTable RT WHERE  PPT.professionalProfileId = RT.proProfileId  AND RT.reviewFor=''Professional'') as proReviewCount,

							FT.businessName,
							
							PAT.projectAdditionalTimeId,
							PAT.requiredHours as additionalTime,
							PAT.deadLine as additionalDeadLine,
							PAT.workScope as additionalWorkScope,
							PAT.amends as additionalAmends,
							PPT.hourlyFee,
							PAT.isFinalizeByFirm,
							PAT.isDeclineByFirm
						  '

	  SET @sqlString = ' FROM  ProjectTable PT
						 INNER JOIN ProjectScheduleTable PST ON PST.projectId  = PT.projectId
						 INNER JOIN ProfessionalProfileTable PPT ON PPT.professionalProfileId  = PST.proProfileId
						 INNER JOIN projectFinalizeTable PFT ON PFT.projectId =  PT.projectId
						 LEFT  JOIN ProjectAdditionalTimeTable PAT ON PAT.projectFinalizeId =  PFT.projectFinalizeId AND PAT.status = 1
						 LEFT JOIN projectCompleteTable PCT ON PCT.projectId =  PT.projectId
						 LEFT JOIN FirmProfileTable FT ON FT.FirmProfileId  = PST.FirmProfileId
					   ' 
	  PRINT (@selectClause+@sqlString+@whereClause)
	  EXEC (@selectClause+@sqlString+@whereClause)
			
END 
 -- EXEC [TOM_SearchAcceptedProjectForFirm] 148