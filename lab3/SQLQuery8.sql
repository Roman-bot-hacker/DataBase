USE labor_sql

SELECT maker, MIN(speed) AS min_price 
FROM dbo.pc PC, dbo.product PR 
WHERE PC.model = PR.model 
GROUP BY maker 
HAVING MIN(speed) >= 500
