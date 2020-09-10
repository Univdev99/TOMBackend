USE [GT]
GO
/****** Object:  StoredProcedure [dbo].[TOM_CommonSoftSearch]    Script Date: 7/22/2020 4:19:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER   PROCEDURE [dbo].[TOM_CommonSoftSearch] 
		@componentName     VARCHAR(32),
		@subType           VARCHAR(32) = NULL, 
		@searchParam       VARCHAR(64) = NULL, 
		@staticWhereClause VARCHAR(max)= NULL,
		@argEntityId	   VARCHAR(max)= NULL,
		@argUserId		   VARCHAR(max)= NULL

AS 
  BEGIN 
      SET nocount ON; 

      DECLARE @sqlCommand  VARCHAR(max), 
              @columnName  VARCHAR(max), 
              @tableName   VARCHAR(max), 
              @whereClause VARCHAR(max), 
              @orderBy     VARCHAR(250) = ''
      DECLARE @userClinicIds VARCHAR(1000)
	  
	  IF @componentName = 'allCity' 
	  BEGIN 
			SET @tableName = ' CityMasterTable ' 
			SET @whereClause = ' where 1=1' 

			IF ISNULL(@searchParam,'') <> ''
			BEGIN 
				SET @whereClause += ' and  CityName like ''%'+@searchParam+'%'' '
			END 
			IF @staticWhereClause IS NOT NULL 
			BEGIN 
				SET @whereClause = @whereClause + ' and ' + @staticWhereClause 
			END 
			SET @orderBy = ' order by CityName '
			SET @columnName = ' CityName as label, CityId as value ' 
	  END ELSE
	   IF @componentName = 'allProvince' 
	  BEGIN 
			SET @tableName = ' ProvinceMasterTable ' 
			SET @whereClause = ' where 1=1' 

			IF ISNULL(@searchParam,'') <> ''
			BEGIN 
				SET @whereClause += ' and  ProvinceName like ''%'+@searchParam+'%'' '
			END 
			IF @staticWhereClause IS NOT NULL 
			BEGIN 
				SET @whereClause = @whereClause + ' and ' + @staticWhereClause 
			END 
			SET @orderBy = ' order by ProvinceName '
			SET @columnName = ' ProvinceName as label, ProvinceId as value ' 
	  END ELSE
	  IF @componentName = 'allMasterSkill' 
	  BEGIN 
			SET @tableName = ' ProSkillMasterTable ' 
			SET @whereClause = ' where 1=1' 

			IF ISNULL(@searchParam,'') <> ''
			BEGIN 
				SET @whereClause += ' and  MasterSkillName like ''%'+@searchParam+'%'' '
			END 
			IF @staticWhereClause IS NOT NULL 
			BEGIN 
				SET @whereClause = @whereClause + ' and ' + @staticWhereClause 
			END 
			SET @orderBy = ' order by MasterSkillName '
			SET @columnName = ' MasterSkillName as label, MasterSkillName as value ' 
	  END ELSE
	  IF @componentName = 'allCategorySkill' 
	  BEGIN 
			SET @tableName = ' ProSkillCategoryTable ' 
			SET @whereClause = ' where 1=1' 

			IF ISNULL(@searchParam,'') <> ''
			BEGIN 
				SET @whereClause += ' and  categorySkillName like ''%'+@searchParam+'%'' '
			END 
			IF @staticWhereClause IS NOT NULL 
			BEGIN 
				SET @whereClause = @whereClause + ' and ' + @staticWhereClause 
			END 
			SET @orderBy = ' order by categorySkillName '
			SET @columnName = ' categorySkillName as label, categorySkillName as value ' 
	  END 

	  PRINT '1111'+ISNULL(@sqlCommand,'ss')
	  SET @sqlCommand = 'SELECT '+@columnName+' FROM '+@tableName+@whereClause
	  PRINT '2222'+@sqlCommand
	  EXEC(@sqlCommand)
			
END 
-- exec [dbo].[TOM_CommonSoftSearch] @componentName='allCity',@staticWhereClause='1 = 1', @searchParam='amit'