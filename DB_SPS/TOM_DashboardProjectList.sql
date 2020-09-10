USE [GT]
GO
/****** Object:  StoredProcedure [dbo].[TOM_DashboardProjectList]    Script Date: 8/17/2020 3:52:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER      PROCEDURE [dbo].[TOM_DashboardProjectList] 
	
	@argUserId    VARCHAR(10) = NULL,
	@argFrom	  VARCHAR(50) = NULL,
	@proProfileId VARCHAR(10) = NULL

AS 
  BEGIN 
      SET nocount ON; 
      DECLARE @sqlCommand  VARCHAR(max), 
              @selectClause  VARCHAR(max), 
              @sqlString   VARCHAR(max), 
              @whereClause VARCHAR(max) = ' WHERE = 1=1',
              @orderBy     VARCHAR(250) = ''

			  DECLARE @openClause  VARCHAR(max) = ''
			  DECLARE @openSqlClause  VARCHAR(max) = ''
			  DECLARE @openWhereClause  VARCHAR(max) = ''
			  
			  DECLARE @acceptedClause  VARCHAR(max) = ''
			  DECLARE @acceptedSqlClause  VARCHAR(max) = ''
			  DECLARE @acceptedWhereClause  VARCHAR(max) = ''
			
			  DECLARE @historyClause  VARCHAR(max) = ''
			  DECLARE @historySqlClause  VARCHAR(max) = ''
			  DECLARE @historyWhereClause  VARCHAR(max) = ''
	  
	  IF(ISNULL(@argFrom,'') <> '')
	  BEGIN
		 IF(@argFrom = 'Firm')
	     BEGIN
		     PRINT @argFrom
			 SET @orderBy = ' ORDER BY createdDate desc'
			 SET @openWhereClause = ' WHERE status = ''Open'' AND createdById = '+@argUserId
			 SET @openClause = ' SELECT TOP 2 projectName,createdDate,status,
								 (select COUNT(1) from ProjectTable T1 WHERE  T1.createdById = '''+@argUserId+''' and status = ''Open'') as projectCount
								'
			 SET @openSqlClause = ' FROM  ProjectTable ' 

			 SET @acceptedWhereClause = ' WHERE status = ''Accepted'' AND createdById = '+@argUserId
			 SET @acceptedClause = ' SELECT TOP 2 projectName,createdDate,status,
								     (select COUNT(1) from ProjectTable T1 WHERE  T1.createdById = '''+@argUserId+''' and status = ''Accepted'') as projectCount
								   '
			 SET @acceptedSqlClause = ' FROM  ProjectTable ' 


			 SET @historyWhereClause = ' WHERE status = ''Completed'' AND createdById = '+@argUserId
			 SET @historyClause = ' SELECT TOP 2 projectName,createdDate,status,
									(select COUNT(1) from ProjectTable T1 WHERE  T1.createdById = '''+@argUserId+''' and status = ''Completed'') as projectCount
								  '
			 SET @historySqlClause = ' FROM  ProjectTable ' 


			 PRINT (@openClause+@openSqlClause+@openWhereClause+@orderBy+' UNION ALL '+
			  	    @acceptedClause+@acceptedSqlClause+@acceptedWhereClause+@orderBy+' UNION ALL '+
				    @historyClause+@historySqlClause+@historyWhereClause+@orderBy
				   )

			 EXEC (' SELECT O.* FROM ('+@openClause+@openSqlClause+@openWhereClause+@orderBy+' ) as O UNION ALL '+
				   ' SELECT A.* FROM ('+@acceptedClause+@acceptedSqlClause+@acceptedWhereClause+@orderBy+' ) as A  UNION ALL '+
				   @historyClause+@historySqlClause+@historyWhereClause+@orderBy)
		  END ELSE
		  IF(@argFrom = 'Professional')
	      BEGIN
		     PRINT @argFrom
			 SET @orderBy = ' ORDER BY PT.createdDate desc '
			 SET @openWhereClause = ' WHERE PSBP.ProjectScheduleByProId is null AND PT.status = ''Open'' AND PST.proProfileId =  '+@proProfileId
			 SET @openClause = ' SELECT TOP 2 PT.projectName,PT.createdDate,PT.status '
			 SET @openSqlClause = ' FROM  ProjectTable PT
									INNER JOIN ProjectScheduleTable PST ON PST.projectId = PT.projectId
									LEFT  JOIN ProjectScheduleByProTable PSBP ON PSBP.projectScheduleId = PST.projectScheduleId
								  ' 

			 SET @acceptedWhereClause = ' WHERE isFinalizeByFirm = 1 AND isFinalizeByAdmin = 1 
										  AND PT.status = ''Accepted'' AND PFT.proProfileId =  '+@proProfileId
			 SET @acceptedClause = ' SELECT TOP 2 PT.projectName,PT.createdDate,PT.status '
			 SET @acceptedSqlClause = ' FROM  ProjectTable PT
										INNER JOIN ProjectFinalizeTable PFT ON PFT.projectId = PT.projectId
									' 


			 SET @historyWhereClause = '  WHERE PCT.isCompletedByPro = 1 AND PCT.isCompletedByFirm  = 1 
										  AND PCT.isCompletedByAdmin  = 1 AND PCT.isPaymentReceived = 1 
										  AND PT.status = ''Completed'' AND PCT.proProfileId = '+@proProfileId
			 SET @historyClause = ' SELECT TOP 2 PT.projectName,PT.createdDate,PT.status '
			 SET @historySqlClause = ' FROM  ProjectTable PT
									   INNER JOIN ProjectCompleteTable PCT ON PCT.projectId = PT.projectId
									 ' 

			  PRINT (' SELECT O.* FROM ('+@openClause+@openSqlClause+@openWhereClause+@orderBy+' ) as O UNION ALL '+
				   ' SELECT A.* FROM ('+@acceptedClause+@acceptedSqlClause+@acceptedWhereClause+@orderBy+' ) as A  UNION ALL '+
				   ' SELECT H.* FROM ('+@historyClause+@historySqlClause+@historyWhereClause+@orderBy+' ) as H')

			 EXEC (' SELECT O.* FROM ('+@openClause+@openSqlClause+@openWhereClause+@orderBy+' ) as O UNION ALL '+
				   ' SELECT A.* FROM ('+@acceptedClause+@acceptedSqlClause+@acceptedWhereClause+@orderBy+' ) as A  UNION ALL '+
				   ' SELECT H.* FROM ('+@historyClause+@historySqlClause+@historyWhereClause+@orderBy+' ) as H')
		  END ELSE
		  IF(@argFrom = 'Admin')
	      BEGIN
		     PRINT @argFrom
			 SET @orderBy = ' ORDER BY PT.createdDate desc '
			 SET @openWhereClause = ' WHERE PT.status = ''Open'' '
			 SET @openClause = ' SELECT TOP 5 PT.projectName,PT.createdDate,PT.status '
			 SET @openSqlClause = ' FROM  ProjectTable PT ' 

			 SET @acceptedWhereClause = ' WHERE status = ''Accepted'' '
			 SET @acceptedClause = ' SELECT TOP 5 PT.projectName,PT.createdDate,PT.status '
			 SET @acceptedSqlClause = ' FROM  ProjectTable PT ' 


			 SET @historyWhereClause = '  WHERE PT.status = ''Completed'' '
			 SET @historyClause = ' SELECT TOP 5 PT.projectName,PT.createdDate,PT.status '
			 SET @historySqlClause = ' FROM  ProjectTable PT ' 

			  PRINT (' SELECT O.* FROM ('+@openClause+@openSqlClause+@openWhereClause+@orderBy+' ) as O UNION ALL '+
				   ' SELECT A.* FROM ('+@acceptedClause+@acceptedSqlClause+@acceptedWhereClause+@orderBy+' ) as A  UNION ALL '+
				   ' SELECT H.* FROM ('+@historyClause+@historySqlClause+@historyWhereClause+@orderBy+' ) as H')

			 EXEC (' SELECT O.* FROM ('+@openClause+@openSqlClause+@openWhereClause+@orderBy+' ) as O UNION ALL '+
				   ' SELECT A.* FROM ('+@acceptedClause+@acceptedSqlClause+@acceptedWhereClause+@orderBy+' ) as A  UNION ALL '+
				   ' SELECT H.* FROM ('+@historyClause+@historySqlClause+@historyWhereClause+@orderBy+' ) as H')
		  END
	  END	
END 
 -- EXEC [TOM_DashboardProjectList] 148 , @argFrom='Firm', @proProfileId=14