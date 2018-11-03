USE Labor_SQL;

SELECT name, launched, displacement 
FROM Ships SH LEFT JOIN Classes CL ON SH.class = CL.class 
WHERE launched > '1921' AND displacement > 35000 
ORDER BY launched