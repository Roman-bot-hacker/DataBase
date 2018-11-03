USE labor_sql

SELECT DISTINCT 'AVERAGE PRICE = '+CONVERT(varchar, AVG(price)) AS average_price 
FROM dbo.pc PC 
WHERE PC.model = ANY
(SELECT model FROM dbo.product WHERE type = 'laptop')