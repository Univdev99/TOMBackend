USE [GT]
GO
/****** Object:  StoredProcedure [dbo].[TOM_SearchAcceptedProjectForProfessional]    Script Date: 8/24/2020 2:07:51 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER    PROCEDURE [dbo].[TOM_SearchAcceptedProjectForProfessional] 
	
	@proProfileId VARCHAR(10) = NULL

AS 
  BEGIN 
      SET nocount ON; 
      DECLARE @sqlCommand  VARCHAR(max), 
              @selectClause  VARCHAR(max), 
              @sqlString   VARCHAR(max), 
              @whereClause VARCHAR(max) = ' WHERE PFT.isFinalizeByFirm = 1 AND PFT.isFinalizeByAdmin = 1 AND PT.status = ''Accepted'' ',
              @orderBy     VARCHAR(250) = ''
	  
	  IF(ISNULL(@proProfileId,'') <> '')
	  BEGIN
		SET @whereClause = @whereClause + ' AND PFT.proProfileId = '+@proProfileId
	  END
	  SET @selectClause = ' SELECT PT.projectName,PT.projectId,FT.firmProfileId,
							FT.businessName,PT.createdDate,PT.expertise,CT.cityName,PT.workLocation,
							PT.hours,PT.hoursDuration,PT.projectDescription,PT.targetDate,
							PCT.projectCompleteId,
							
							
							PPT.firstName as proFirstName,
							CONCAT(PPT.firstName,'', ''+PPT.lastName) as proName,
							PFT.projectFinalizeId,
							PAT.projectAdditionalTimeId
						  '

	  SET @sqlString = ' FROM  ProjectTable PT
						 LEFT JOIN cityMasterTable CT ON CT.cityId = PT.cityId
						 INNER JOIN ProjectScheduleTable PST ON PST.projectId  = PT.projectId
						 LEFT  JOIN ProfessionalProfileTable PPT ON PPT.ProfessionalProfileId = PST.proProfileId 
						 INNER JOIN FirmProfileTable FT ON FT.FirmProfileId  = PST.FirmProfileId
						 INNER JOIN projectFinalizeTable PFT ON PFT.projectId =  PT.projectId
						 LEFT  JOIN ProjectAdditionalTimeTable PAT ON PAT.projectFinalizeId =  PFT.projectFinalizeId AND PAT.status = 1
						 LEFT JOIN projectCompleteTable PCT ON PCT.projectId =  PT.projectId
					   ' 
	  PRINT (@selectClause+@sqlString+@whereClause)
	  EXEC (@selectClause+@sqlString+@whereClause)
			
END 
 -- EXEC [TOM_SearchAcceptedProjectForProfessional] 14