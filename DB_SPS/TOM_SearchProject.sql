USE [GT]
GO
/****** Object:  StoredProcedure [dbo].[TOM_SearchProject]    Script Date: 8/17/2020 4:05:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER       PROCEDURE [dbo].[TOM_SearchProject] 
		@argUserId  VARCHAR(max) = '',
		@argProjectStatus  VARCHAR(max) = 'Open',
		@strForTo2Record  VARCHAR(max) = ''

AS 
  BEGIN 
      SET nocount ON; 

      DECLARE @sqlCommand  VARCHAR(max), 
              @selectClause  VARCHAR(max), 
              @sqlString   VARCHAR(max), 
              @whereClause VARCHAR(max) = ' WHERE PT.status = ''Open''', 
              @orderBy     VARCHAR(250) = ' Order By PT.CreatedDate Desc'
	 
	  IF(ISNULL(@argUserId,'') <> '')
	  BEGIN
		SET @whereClause = CONCAT(@whereClause, ' AND PT.createdById = ',@argUserId)
	  END

	  SET @selectClause = ' SELECT '+@strForTo2Record+'
							CONCAT(PPT.lastName,'', ''+firstName) as proName,
							PPT.professionalProfileId as proProfileId,
							CASE WHEN PST.isDeclineByFirm = 1 OR PSBPT.isDeclineByFirmAfterMeeting = 1 THEN ''Declined''
								 WHEN ISNULL(ProjectScheduleByProId,'''') = '''' THEN ''Requested''
								 WHEN ISNULL(ProjectScheduleByProId,'''') <> '''' AND isCompletedByAdmin = 0  THEN ''Pending Meeting''
								 WHEN ISNULL(ProjectScheduleByProId,'''') <> '''' AND isCompletedByAdmin = 1 AND ISNULL(PFT.isFinalizeByFirm,0) = 0 THEN ''Pending Approval''
								 WHEN PFT.isFinalizeByFirm = 1  THEN ''Accepted''
							END as projectStatus,
							PT.projectId,
							CAST(0 as BIT) as isView,
							(select COUNT(1) from  projectTable T1 WHERE T1.status = PT.status AND T1.createdById = PT.createdById group by status) as projectCount,
							PT.isShortOrLong,PT.projectName,PT.expertise,PT.cityId,CT.cityName,PT.workLocation ,
							PT.hours,PT.hoursDuration,PT.projectDescription,PT.targetDate,PT.createdDate,
							PT.status,

							PPT.hourlyFee,
							PPT.picBase64,
							PFT.requiredHours,
							PFT.deadLine,
							PFT.workScope,
							PFT.projectFinalizeId,
							PWT.workAvailability,
							(select AVG(rating) from ReviewTable RT WHERE  PPT.professionalProfileId = RT.proProfileId  AND RT.reviewFor=''Professional'') as avgProRating,
							(select COUNT(1)    from ReviewTable RT WHERE  PPT.professionalProfileId = RT.proProfileId  AND RT.reviewFor=''Professional'') as proReviewCount

						  '

	  SET @sqlString = ' FROM ProjectTable PT 
						 LEFT JOIN ProjectScheduleTable PST ON PST.projectId = PT.projectId
						 LEFT JOIN ProfessionalProfileTable PPT ON PPT.ProfessionalProfileId = PST.proProfileId
						 LEFT JOIN proWorkAvailabilityTable PWT ON PPT.ProfessionalProfileId = PWT.ProfessionalProfileId
						 LEFT JOIN ProjectScheduleByProTable PSBPT ON PSBPT.projectScheduleId = PST.projectScheduleId
						 LEFT JOIN ProjectFinalizeTable PFT  ON PFT.projectId = PST.projectId AND PFT.proProfileId =  PST.proProfileId 
						 LEFT JOIN CityMasterTable CT ON CT.cityId = PT.cityId
					   ' 
	  PRINT (@selectClause+@sqlString+@whereClause+@orderBy)
	  EXEC (@selectClause+@sqlString+@whereClause+@orderBy)

			
END 
 --      EXEC TOM_SearchProject 
 --     EXEC TOM_SearchProject @argUserId = 148, @argProjectStatus = 'Open', @strForTo2Record = ' '