USE labor_sql

SELECT DISTINCT MAX(lap1.model) AS model1, MIN(lap2.model) AS model2, lap1.ram, lap1.hd 
FROM dbo.laptop AS lap1, dbo.laptop AS lap2 
WHERE lap1.ram = lap2.ram AND lap1.hd = lap2.hd AND lap1.code != lap2.code 
GROUP BY lap1.ram, lap1.hd;