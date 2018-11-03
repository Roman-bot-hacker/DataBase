USE Labor_SQL;

SELECT model, type, price 
FROM Printer 
WHERE price < 300.00 
ORDER BY type DESC