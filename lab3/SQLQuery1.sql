USE labor_sql;

SELECT name, launched 
FROM dbo.ships 
WHERE launched > 1920 AND launched < 1942 
ORDER BY launched DESC;