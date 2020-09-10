USE [GT]
GO
/****** Object:  StoredProcedure [dbo].[TOM_SearchFirmProfileForAdmin]    Script Date: 8/17/2020 4:01:34 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER         PROCEDURE [dbo].[TOM_SearchFirmProfileForAdmin] 
		@proProfileId VARCHAR(max) = '',
		@jobStatus VARCHAR(max) = ''

AS 
  BEGIN 
      SET nocount ON; 

      DECLARE @sqlCommand  VARCHAR(max), 
              @selectClause  VARCHAR(max), 
              @sqlString   VARCHAR(max), 
              @whereClause VARCHAR(max) = ' WHERE 1=1 ',
              @orderBy     VARCHAR(250) = ''
	  
	
	  SET @selectClause = ' SELECT DISTINCT 
							CAST(0 as BIT) as isView,
							UT.userId,
							CASE WHEN userStatus = ''Approve'' THEN ''Accepted''
								 WHEN userStatus = ''Decline'' THEN ''Declined''
							ELSE '''' END as  userStatus,
							FT.firmProfileId,
							FT.businessName,
							branchName,
							streetAddress,
							CT.cityName,
							provinceId as provinceName, zipcode, businessDesc, isAccountingFirm,
							isRegisteredFirm, hstNumber, webSite, billEmailAddress,
							billStreetAddress, BCT.cityName as billCityName, billProvinceId as billProvinceName,billZipcode,
							FT.firstName, FT.lastName, FT.phoneNumber, FT.workEmail
							
						  '

	  SET @sqlString = ' FROM FirmProfileTable FT
						 INNER JOIN UserTable UT on UT.userId = FT.userId
						 INNER JOIN CityMasterTable CT ON CT.cityId = FT.cityId 
						 INNER JOIN CityMasterTable BCT ON BCT.cityId = FT.billCityId
					   ' 
	  PRINT (@selectClause+@sqlString+@whereClause)
	  EXEC (@selectClause+@sqlString+@whereClause)
			
END 
 --     EXEC TOM_SearchFirmProfileForAdmin 