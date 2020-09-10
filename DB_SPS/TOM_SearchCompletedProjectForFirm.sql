USE [GT]
GO
/****** Object:  StoredProcedure [dbo].[TOM_SearchCompletedProjectForFirm]    Script Date: 8/17/2020 3:58:02 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER      PROCEDURE [dbo].[TOM_SearchCompletedProjectForFirm] 
	
	@argUserId VARCHAR(10) = NULL

AS 
  BEGIN 
      SET nocount ON; 
      DECLARE @sqlCommand  VARCHAR(max), 
              @selectClause  VARCHAR(max), 
              @sqlString   VARCHAR(max), 
              @whereClause VARCHAR(max) = ' WHERE  PCT.isCompletedByPro = 1 AND
											PCT.isCompletedByFirm = 1 AND PCT.isCompletedByAdmin = 1 AND
											PT.status = ''Completed'' ',
              @orderBy     VARCHAR(250) = ''
	  
	  IF(ISNULL(@argUserId,'') <> '')
	  BEGIN
		SET @whereClause = @whereClause + ' AND PT.createdById = '+@argUserId
	  END
	  SET @selectClause = ' SELECT
							PT.projectName,PT.createdDate,PT.projectId,
							PFT.requiredHours, PFT.deadLine, PFT.workScope,
							CONCAT(PPT.lastName,'', '',PPT.firstName) as proName,
							PPT.professionalProfileId as proProfileId,
							PPT.picBase64 as picBase64,
							(select AVG(rating)  from ReviewTable RT WHERE  PPT.professionalProfileId = RT.proProfileId  AND RT.reviewFor=''Professional'') as averageRating,
							(select COUNT(1)  from ReviewTable RT WHERE  PPT.professionalProfileId = RT.proProfileId  AND RT.reviewFor=''Professional'') as reviewCount,
							(select AVG(rating) from ReviewTable RT WHERE  PPT.professionalProfileId = RT.proProfileId AND RT.projectId = PCT.projectId  AND RT.reviewFor=''Professional'') as currentProjectRating,
							(select TOP 1 reviewId from ReviewTable RT WHERE  PPT.professionalProfileId = RT.proProfileId AND RT.projectId = PCT.projectId AND RT.reviewFor=''Professional'' ) as reviewId
							
						  '

	  SET @sqlString = ' FROM  ProjectTable PT
						 INNER JOIN projectFinalizeTable PFT ON PFT.projectId =  PT.projectId
						 INNER JOIN projectCompleteTable PCT ON PCT.projectId =  PT.projectId
						 INNER JOIN ProfessionalProfileTable PPT ON PPT.professionalProfileId  = PCT.proProfileId
					   ' 
	  PRINT (@selectClause+@sqlString+@whereClause)
	  EXEC (@selectClause+@sqlString+@whereClause)
			
END 
 -- EXEC [TOM_SearchCompletedProjectForFirm] 148