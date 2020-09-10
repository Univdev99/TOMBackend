USE [GT]
GO
/****** Object:  StoredProcedure [dbo].[TOM_SearchProfessionalForAdmin]    Script Date: 8/17/2020 4:03:29 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER PROCEDURE [dbo].[TOM_SearchProfessionalForAdmin] 
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
	  
	  IF(ISNULL(@jobStatus,'') <> '' AND @jobStatus = 'Requested')
	  BEGIN
		SET @whereClause += ' '
		
	  END
	  SET @selectClause = ' SELECT DISTINCT 
							CAST(0 as BIT) as isView,
							CONCAT(PPT.lastName,'', ''+PPT.firstName) as proName,
							
							UT.workEmail,UT.userId,
							CASE WHEN userStatus = ''Approve'' THEN ''Accepted''
								 WHEN userStatus = ''Decline'' THEN ''Declined''
							ELSE '''' END as  userStatus,

							PWT.startDate,
							PWT.lastDate, PWT.workAvailability, PWT.locationAvailability, PWT.isFullTime,

							PPT.professionalProfileId as proProfileId,PPT.lastName, PPT.firstName, CT.cityName, PPT.address,
							PRT.cityName as provinceName, PPT.zipCode, PPT.hourlyFee, PPT.picBase64
						  '

	  SET @sqlString = ' FROM  ProWorkAvailabilityTable  PWT
						 LEFT  JOIN ProfessionalProfileTable PPT ON PPT.professionalProfileId= PWT.professionalProfileId
						 INNER JOIN UserTable UT ON UT.userId = PPT.userId
						 LEFT  JOIN CityMasterTable CT ON CT.cityId= PPT.cityId
						 LEFT  JOIN CityMasterTable PRT ON PRT.cityId= PPT.provinceId

					   ' 
	  PRINT (@selectClause+@sqlString+@whereClause)
	  EXEC (@selectClause+@sqlString+@whereClause)
			
END 
 --     EXEC TOM_SearchProfessionalForAdmin