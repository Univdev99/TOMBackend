USE [GT]
GO
/****** Object:  StoredProcedure [dbo].[TOM_MergeAddtionalReqToFinalizeDetails]    Script Date: 8/24/2020 2:14:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER       PROCEDURE [dbo].[TOM_MergeAddtionalReqToFinalizeDetails] 
		@projectAdditionalTimeId VARCHAR(40) = ''

AS 
  BEGIN 
      SET nocount OFF; 

      DECLARE 
              @selectClause  VARCHAR(max), 
              @sqlString   VARCHAR(max),
              @whereClauseString   VARCHAR(max)
	  
		IF(ISNULL(@projectAdditionalTimeId,'') <> '')
		BEGIN
		SET @selectClause= 'UPDATE PFT SET PFT.requiredHours += PAT.requiredHours,PFT.deadLine = PAT.deadLine,PFT.workScope = PAT.workScope '
		SET @sqlString= ' FROM ProjectFinalizeTable PFT
						  INNER JOIN ProjectAdditionalTimeTable PAT ON PAT.projectFinalizeId = PFT.projectFinalizeId  AND PAT.status = 1'
		
		SET @whereClauseString = 'WHERE PFT.projectFinalizeId = (select projectFinalizeId from ProjectAdditionalTimeTable WHERE projectAdditionalTimeId = '''+@projectAdditionalTimeId+''')'
					    
		  PRINT (@selectClause+@sqlString+@whereClauseString)
		  EXEC (@selectClause+@sqlString+@whereClauseString)
	  END
END 
--exec TOM_MergeAddtionalReqToFinalizeDetails 7