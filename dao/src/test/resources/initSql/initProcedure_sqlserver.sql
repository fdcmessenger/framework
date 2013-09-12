
-- ----------------------------
-- Procedure structure for [commonProcedure]
-- ----------------------------
DROP PROCEDURE [commonProcedure]
GO




CREATE PROCEDURE [commonProcedure]
@rc int output,  
@startint  int ,
  @stepint  int ,
  @rm varchar(100) OUTPUT 
AS
BEGIN
SET NOCOUNT ON;
--DECLARE @userRm  VARCHAR(100);

--set @userRm = '20';
set @rm = '20';
--left(@userRm,100)
--@startint + @stepint;
set @rc = 0;

return @rc
END




GO

-- ----------------------------
-- Procedure structure for [queryProcedure]
-- ----------------------------
DROP PROCEDURE [queryProcedure]
GO
CREATE PROCEDURE [queryProcedure]
  @rc int OUTPUT ,
  @rm varchar(100) OUTPUT 
AS
BEGIN
SET NOCOUNT ON;
set @rm = '20';
set @rc = 0;


SELECT * from t_demo_entity;

return @rc
END
GO
