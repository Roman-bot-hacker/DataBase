USE Labor_SQL;

SELECT CONCAT('CODE: ', code) AS code, 
	CONCAT('MODEL: ', model) AS model, 
    CONCAT('SPEED: ', speed) AS speed, 
    CONCAT('RAM: ', ram) AS ram, 
    CONCAT('HD: ', hd) AS hd, 
    CONCAT('CD: ', cd) AS cd, 
    CONCAT('PRICE: ', price) AS price 
FROM PC