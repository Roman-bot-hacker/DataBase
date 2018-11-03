USE labor_sql

SELECT DISTINCT maker, (CASE WHEN maker = ANY(SELECT maker 
FROM dbo.product PR, dbo.laptop LT WHERE PR.model = LT.model) 
THEN ('yes('+CONVERT(varchar, COUNT(*))+')') ELSE 'no' END) 
AS laptop 
FROM dbo.product PR 
GROUP BY maker