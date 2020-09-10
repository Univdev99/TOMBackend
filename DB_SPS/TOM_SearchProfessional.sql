USE [GT]
GO
/****** Object:  StoredProcedure [dbo].[TOM_SearchProfessional]    Script Date: 8/24/2020 12:46:36 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER     PROCEDURE [dbo].[TOM_SearchProfessional] 
		@proWhereClause VARCHAR(max) = ''

AS 
  BEGIN 
      SET nocount ON; 

	  DECLARE @adminProfit varchar(max) = '1'
	  SET @adminProfit = (SELECT profit from HADMIN.adminProfitTable WHERE status = 1)
	  PRINT  @adminProfit
      DECLARE @sqlCommand  VARCHAR(max), 
              @selectClause  VARCHAR(max), 
              @sqlString   VARCHAR(max), 
              @whereClause VARCHAR(max) = ' WHERE 1=1 ', 
              @orderBy     VARCHAR(250) = ''
      
		IF(isNull(@proWhereClause , '') <> '')
		BEGIN
		DECLARE @ix INT = 1
		DECLARE @leng INT = 0
		select @leng = COUNT(Name) from parseJSON(@proWhereClause) where StringValue <> ''  AND ISNULL(NAME,'')<>''
		SELECT ROW_NUMBER() OVER (ORDER BY Name) as RNo, Name, StringValue INTO #temp FROM parseJSON(@proWhereClause) where StringValue <> ''  AND ISNULL(NAME,'')<>''

		WHILE @ix <= @leng
		BEGIN
			DECLARE @nameInLoop varchar(50)
			DECLARE @valueInLoop varchar(50)

			SELECT @nameInLoop = Name, @valueInLoop = StringValue FROM #temp where RNo = @ix

			--IF CONDITION CAN BE USED AS AND WHEN IN CASE OF ANY SPECIAL SEARCHES AS BELOW
			IF(@nameInLoop in ('isAccountingFirm'))
			BEGIN
				SET @whereClause += ' AND PEDT.isAccountingFirm = '''+@valueInLoop+''''
			END ELSE IF(@nameInLoop in ('proMasterSkill'))
			BEGIN
				SET @whereClause = CONCAT(@whereClause,' AND PST.proSkillMasterId = ',@valueInLoop)
			END ELSE IF(@nameInLoop in ('proCategorySkill'))
			BEGIN
				SET @whereClause = CONCAT(@whereClause,' AND PST.proSkillCategoryId  =  ',@valueInLoop)
			END  ELSE IF(@nameInLoop = 'workAvailability')
			BEGIN
				PRINT 'workAvailability'
				SET @whereClause += ' AND PWAT.workAvailability  IN (select Item from fn_SplitRow('''+@valueInLoop+''','',''))'
			END  ELSE IF(@nameInLoop = 'isFullTime')
			BEGIN
				PRINT 'isFullTime'
				SET @whereClause += ' AND PWAT.isFullTime  IN (select Item from fn_SplitRow('''+@valueInLoop+''','',''))'
			END ELSE IF(@nameInLoop  = 'locationAvailability')
			BEGIN
				PRINT 'locationAvailability'
				SET @whereClause += ' AND PWAT.locationAvailability  IN (select Item from fn_SplitRow('''+@valueInLoop+''','',''))'
			END ELSE IF(@nameInLoop in ('hourRange'))
			BEGIN
				PRINT 'hourRange'
				DECLARE @i INT = 1
				DECLARE @length INT = 0
				DECLARE @stringValue VARCHAR(MAX)= '';
				DECLARE @startValue VARCHAR(MAX)= '';
				DECLARE @endValue VARCHAR(MAX)= '';
				DECLARE @tempWhereValue VARCHAR(MAX)= '';
				select @length = COUNT(1) FROM fn_SplitRow(@valueInLoop,',') 
				SELECT ROW_NUMBER() OVER (ORDER BY Item) as id, Item INTO #tempHour FROM fn_SplitRow(@valueInLoop,',') 

				WHILE @i <= @length
				BEGIN
					SET @stringValue = (select  Item from #tempHour WHERE id =  @i)
					SET @startValue = (SELECT SUBSTRING( @stringValue, 0, CHARINDEX('-', @stringValue) ))
					SET @endValue = (SELECT SUBSTRING( @stringValue, CHARINDEX('-', @stringValue)+1 , LEN(@stringValue)))
					SET @tempWhereValue += ' AND '+CAST(@adminProfit as varchar(max))+' * PPT.hourlyFee /100 + PPT.hourlyFee >= '''+@startValue+''' AND '+CAST(@adminProfit as varchar(max))+' * PPT.hourlyFee /100 + PPT.hourlyFee <= '''+@endValue+''''
					SET @i = @i+1
				END
				SET @whereClause += @tempWhereValue
			END ELSE IF(@nameInLoop in ('Age'))
			BEGIN
				SET @whereClause += ' 1=1 '
			END
				SET @ix = @ix + 1
			END
		END 
	  
	 
	  SET @selectClause = ' SELECT DISTINCT CONCAT(PPT.lastName,'', ''+PPT.firstName) as proName,
							PPT.professionalProfileId,
							PWAT.workAvailability,PWAT.locationAvailability,
							PPT.picBase64,
							PPT.hourlyFee,
							'+CAST(@adminProfit as varchar(max))+' * PPT.hourlyFee /100 + PPT.hourlyFee as countedFee, 
							'+CAST(@adminProfit as varchar(max))+' as adminProfit, 
							PET.selfDescription, PWAT.isFullTime,
							UT.userId
						  '

	  SET @sqlString = ' FROM ProfessionalProfileTable  PPT
						 INNER JOIN UserTable UT On UT.userId = PPT.userId
						 LEFT  JOIN ProWorkAvailabilityTable     PWAT ON PWAT.professionalProfileId = PPT.professionalProfileId	 
						 LEFT  JOIN ProWorkExperienceTable       PET ON PET.ProfessionalProfileId   = PPT.ProfessionalProfileId	 
						 LEFT  JOIN ProWorkExperienceDetailTable PEDT ON PEDT.ProWorkExperienceId   = PET.ProWorkExperienceId	 
						 LEFT  JOIN ProSkillTable                PST ON PST.ProWorkExperienceId     = PET.ProWorkExperienceId 
						 
					   ' 
      -- SELECT workAvailability from ProWorkAvailabilityTable group by workAvailability
	  PRINT @proWhereClause
	  PRINT (@selectClause+@sqlString+@whereClause)
	  EXEC (@selectClause+@sqlString+@whereClause)
END 
 --      EXEC TOM_SearchProfessional '{"isAccountingFirm":1}'
 --     EXEC TOM_SearchProfessional  '{"isAccountingFirm":1,"workAvailability":"< 10 hrs,> 40 hrs","locationAvailability":"Off Site","hourRange":"100-999999","isFullTime":"0"}'
 --     EXEC TOM_SearchProfessional '{"isAccountingFirm":1,"workAvailability":"","locationAvailability":"","hourRange":"50-75","isFullTime":""}'