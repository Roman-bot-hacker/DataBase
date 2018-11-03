USE labor_sql

SELECT DISTINCT maker, PR.model, type, price 
FROM dbo.product PR JOIN dbo.pc PC ON PR.model = PC.model
WHERE maker = 'B'
UNION
SELECT DISTINCT maker, PR.model, type, price 
FROM dbo.product PR JOIN dbo.laptop LT ON PR.model = LT.model 
WHERE maker = 'B' 
ORDER BY maker