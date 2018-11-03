USE labor_sql

SELECT DISTINCT maker FROM dbo.product PR
WHERE type = 'pc' AND PR.maker NOT IN 
(SELECT DISTINCT maker FROM dbo.product PR WHERE NOT EXISTS
(SELECT * FROM PC WHERE PC.model = PR.model) AND type = 'pc')