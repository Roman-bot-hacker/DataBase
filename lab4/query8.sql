USE Labor_SQL;

SELECT maker, AVG(screen) AS average_screen 
FROM Product PR LEFT JOIN Laptop LT ON PR.model = LT.model
WHERE type = 'Laptop' 
GROUP BY maker