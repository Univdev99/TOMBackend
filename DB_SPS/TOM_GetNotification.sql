USE [GT]
GO
/****** Object:  StoredProcedure [dbo].[TOM_GetNotificationCount]    Script Date: 8/19/2020 2:53:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

--exec TOM_GetNotificationCount @userId = '149', @argFrom = 'Professional'
ALTER     PROCEDURE [dbo].[TOM_GetNotificationCount] 
		@userId VARCHAR(40) = '',
		@argFrom VARCHAR(1000) = ''

AS 
  BEGIN 
      SET nocount ON; 

      DECLARE 
              @selectClause  VARCHAR(max), 
              @sqlString   VARCHAR(max)
      
	  
	  SET @selectClause = ' SELECT COUNT(1) as notificationCount '

	  SET @sqlString = CONCAT(' FROM NotificationTable WHERE isRead = 0 and toUserId =',@userId,' AND notificationFor =  ',''''+@argFrom+'''')
					    
	  PRINT @userId
	  PRINT (@selectClause+@sqlString)
	  EXEC (@selectClause+@sqlString)
END 
 