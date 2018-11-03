USE Labor_SQL;

SELECT SH.name, CL.country 
FROM Ships AS SH, Classes AS CL 
WHERE SH.class = CL.class