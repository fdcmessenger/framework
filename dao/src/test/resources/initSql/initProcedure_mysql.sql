-- ----------------------------
-- Procedure structure for commonProcedure
-- ----------------------------
DROP PROCEDURE IF EXISTS commonProcedure;
;;
CREATE  PROCEDURE commonProcedure(OUT rc integer,IN startint integer,IN stepint integer,OUT rm varchar(100))
BEGIN
	set rm = startint + stepint;
	set rc = 0;
	SELECT rm,rc;
END

;;
-- ----------------------------
-- Procedure structure for queryProcedure
-- ----------------------------
DROP PROCEDURE IF EXISTS queryProcedure;
;;
CREATE  PROCEDURE queryProcedure(OUT rc int,OUT rm varchar(100))
BEGIN
	set rm = '20';
set rc = 0;

SELECT * from t_demo_entity;
END

