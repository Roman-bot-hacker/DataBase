USE Labor_SQL;

SELECT name, SH.class, launched, type, country, numGuns, bore, displacement
FROM Ships SH LEFT JOIN Classes CL ON SH.class = CL.class
WHERE CASE numGuns
		WHEN 9 THEN 1
        ELSE 0
	END
    +CASE bore
		WHEN 16 THEN 1
        ELSE 0
	END
    +CASE displacement
		WHEN 46000 THEN 1
        ELSE 0
	END
    +CASE type
		WHEN'bb' THEN 1
        ELSE 0
	END
    +CASE country
		WHEN 'Japan' THEN 1
        ELSE 0
	END
    +CASE launched
		WHEN 1916 THEN 1
        ELSE 0
	END
    +CASE SH.class
		WHEN 'Revenge' THEN 1
        ELSE 0
	END >= 3


		
    
    