USE labor_sql;

SELECT * 
FROM dbo.trip 
WHERE CAST(time_out AS TIME) BETWEEN '12:00:00.000' AND '17:00:00.000' 
ORDER BY time_out