USE labor_sql

SELECT * FROM dbo.product PR LEFT JOIN dbo.pc PC ON PR.model = PC.model

SELECT * FROM dbo.product WHERE maker!='A'

SELECT maker FROM dbo.product PR LEFT JOIN dbo.pc PC ON PR.model = PC.model WHERE code!=NULL