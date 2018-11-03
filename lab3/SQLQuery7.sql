USE labor_sql

SELECT COUNT(*) AS count_pc, model, AVG(price) AS avg_price 
FROM dbo.pc 
WHERE model = ANY
(SELECT model FROM dbo.product WHERE type = 'pc') 
GROUP BY model 
HAVING AVG(price) < 800.00