USE labor_sql

SELECT country, class 
FROM dbo.classes 
WHERE country = ALL
(SELECT country FROM dbo.classes WHERE country = 'USA') 
GROUP BY class, country