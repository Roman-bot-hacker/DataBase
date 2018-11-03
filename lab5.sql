USE db_labs
GO
IF DB_ID('[electro_goods]') IS NULL
	DROP DATABASE [electro_goods];
GO
CREATE DATABASE [electro_goods];
GO
USE [electro_goods];
GO

CREATE TABLE [provider] (
	[id] INT IDENTITY PRIMARY KEY,
	[name] VARCHAR(50) NOT NULL UNIQUE,
	[director] VARCHAR(50) NOT NULL,
	[phone] CHAR(13) NOT NULL,
	CONSTRAINT CK_PROVIDER_PHONE 
	CHECK (phone LIKE ('[0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]-[0-9][0-9][0-9]'))
	);

CREATE TABLE [good] (
	[code] CHAR(5) NOT NULL PRIMARY KEY,
	[image] VARBINARY(MAX) NULL,
	[brand] VARCHAR(50) NOT NULL,
	[description] TEXT NULL,
	[price] REAL NOT NULL,
	[count] INT NOT NULL DEFAULT(0),
	CONSTRAINT CK_GOODS_PRICE CHECK (price >= 0),
	);

CREATE TABLE [good_provider] (
	[good_code] CHAR(5) NOT NULL,
	[provider_id] INT NOT NULL,
	CONSTRAINT PK_GOOD_PROVIDER PRIMARY KEY (good_code, provider_id),
	CONSTRAINT FK_GOOD_PROVIDER_GOOD_ID FOREIGN KEY ([good_code]) REFERENCES [good] ([code]),
	CONSTRAINT FK_GOOD_PROVIDER_PROVIDER_ID FOREIGN KEY ([provider_id]) REFERENCES [provider] ([id])
	);

CREATE TABLE [invoice] (
	[number] CHAR(5) NOT NULL PRIMARY KEY,
	[data] DATE NOT NULL,
	[provider_id] INT NOT NULL,
	CONSTRAINT FK_iNVOICE_PROVIDER_ID FOREIGN KEY ([provider_id]) REFERENCES [provider] ([id])
	);

-----[provider]--------------------------------------------------------------------------------
INSERT INTO [provider] VALUES('LG', 'Matviychuk Y.M.', '123-45-67-890');
INSERT INTO [provider] VALUES('LENOVO', 'Burkackiy Y.T.', '098-76-54-321');
INSERT INTO [provider] VALUES('SAMSUNG', 'Shahray V.V.', '564-78-32-190');

-----[goods]------------------------------------------------------------------------------------
INSERT INTO [good] VALUES('12345', NULL, 'LG', NULL, 2330.0, 2);
INSERT INTO [good] VALUES('12346', NULL, 'LENOVO', NULL, 5330.0, 1);
INSERT INTO [good] VALUES('12347', NULL, 'SAMSUNG', NULL, 9330.0, 0);
INSERT INTO [good] VALUES('12348', NULL, 'LG', NULL, 8330.0, 0);

----[goods_provider]----------------------------------------------------------------------------
INSERT INTO [good_provider] VALUES('12348', 1);
INSERT INTO [good_provider] VALUES('12345', 1);
INSERT INTO [good_provider] VALUES('12346', 2);
INSERT INTO [good_provider] VALUES('12347', 3);

----[invoice]-----------------------------------------------------------------------------------
INSERT INTO [invoice] VALUES('11111', '2018-08-12', 1);
INSERT INTO [invoice] VALUES('11112', '2018-08-12', 2);
INSERT INTO [invoice] VALUES('11113', '2018-08-12', 3);
INSERT INTO [invoice] VALUES('11114', '2018-08-12', 1);
















