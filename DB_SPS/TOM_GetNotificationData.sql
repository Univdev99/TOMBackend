USE [GT]
GO
/****** Object:  StoredProcedure [dbo].[TOM_GetNotificationData]    Script Date: 8/24/2020 2:16:51 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER       PROCEDURE [dbo].[TOM_GetNotificationData] 
		@toUserId VARCHAR(40) = '',
		@notificationFor VARCHAR(1000) = ''

AS 
  BEGIN 
      SET nocount ON; 

      DECLARE @selectClause  VARCHAR(max), 
              @sqlString   VARCHAR(max),
			  @whereClause   VARCHAR(max),
			  @orderby   VARCHAR(max) = ' order by CreatedDate desc'
	  
	  SET @selectClause = ' SELECT * '
	  SET @whereClause = CONCAT(' WHERE isRead = 0  and toUserId =',@toUserId,' AND notificationFor =  ',''''+@notificationFor+'''') 


	  SET @sqlString = ' FROM NotificationTable'
					    
	  PRINT @toUserId
	  PRINT @notificationFor
	  PRINT (@selectClause+@sqlString+@whereClause+@orderby)
	  EXEC (@selectClause+@sqlString+@whereClause+@orderby)
END 
--exec TOM_GetNotificationData @toUserId = '14', @notificationFor = 'Professional'