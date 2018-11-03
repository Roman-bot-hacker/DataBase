USE Labor_SQL;

SELECT country, battle, COUNT(country) AS count
FROM 
	(SELECT name, country FROM Ships SH JOIN Classes CL ON SH.class = CL.class) AS UN 
    LEFT JOIN Outcomes OC ON UN.name = OC.ship
WHERE battle IS NOT NULL 
GROUP BY battle 
HAVING count >= 2