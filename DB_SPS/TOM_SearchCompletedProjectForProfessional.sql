USE [GT]
GO
/****** Object:  StoredProcedure [dbo].[TOM_SearchCompletedProjectForProfessional]    Script Date: 8/17/2020 3:59:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER      PROCEDURE [dbo].[TOM_SearchCompletedProjectForProfessional] 
	
	@proProfileId VARCHAR(10) = NULL

AS 
  BEGIN 
      SET nocount ON; 
      DECLARE @sqlCommand  VARCHAR(max), 
              @selectClause  VARCHAR(max), 
              @sqlString   VARCHAR(max), 
              @whereClause VARCHAR(max) = ' WHERE PFT.isFinalizeByFirm = 1 AND PFT.isFinalizeByAdmin = 1 AND PT.status = ''Completed'' ',
              @orderBy     VARCHAR(250) = ''
	  
	  IF(ISNULL(@proProfileId,'') <> '')
	  BEGIN
		SET @whereClause = @whereClause + ' AND PFT.proProfileId = '+@proProfileId
	  END
	  SET @selectClause = ' SELECT PT.projectName,PT.projectId,FT.firmProfileId,
							FT.businessName,PT.createdDate,
							PFT.requiredHours, PFT.deadLine, PFT.workScope,
							PCT.modifiedDate as completedDate,
							(select AVG(rating)  from ReviewTable RT WHERE FT.firmProfileId  = RT.firmProfileId AND RT.reviewFor=''Firm'') as averageRating,
							(select COUNT(1)  from ReviewTable RT WHERE   FT.firmProfileId  = RT.firmProfileId  AND RT.reviewFor=''Firm'') as reviewCount,
							(select AVG(rating) from ReviewTable RT WHERE   FT.firmProfileId  = RT.firmProfileId AND RT.projectId = PCT.projectId  AND RT.reviewFor=''Firm'') as currentProjectRating,
							(select TOP 1 reviewId from ReviewTable RT WHERE  FT.firmProfileId = RT.firmProfileId AND RT.projectId = PCT.projectId AND RT.reviewFor=''Firm'' ) as reviewId
						  '

	  SET @sqlString = ' FROM  ProjectTable PT
						 INNER JOIN projectScheduleTable PST ON PST.projectId =  PT.projectId
						 INNER JOIN projectFinalizeTable PFT ON PFT.projectId =  PT.projectId
						 INNER JOIN FirmProfileTable FT ON FT.firmProfileId  = PST.firmProfileId
						 INNER JOIN projectCompleteTable PCT ON PCT.projectId =  PT.projectId
					   ' 
	  PRINT (@selectClause+@sqlString+@whereClause)
	  EXEC (@selectClause+@sqlString+@whereClause)
			
END 
 -- EXEC [TOM_SearchCompletedProjectForProfessional] 