USE Labor_SQL;

SELECT class 
FROM (SELECT SH.class
	FROM Ships SH LEFT JOIN Classes CL ON SH.class = CL.class
	GROUP BY SH.class
	HAVING COUNT(SH.class) = 1) AS SH
    UNION 
    (SELECT class 
	FROM Outcomes JOIN Classes
	WHERE ship = class
	GROUP BY class
	HAVING COUNT(class) = 1);

