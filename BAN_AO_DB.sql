﻿create database Ban_Ao_DB
go
use Ban_Ao_DB

go

IF OBJECT_ID('THUONG_HIEU') IS NOT NULL
DROP TABLE THUONG_HIEU
CREATE TABLE THUONG_HIEU (
ID INT IDENTITY(1,1) PRIMARY KEY,
TEN_THUONG_HIEU NVARCHAR(225) NOT NULL,
NGAY_TAO DATETIME DEFAULT GETDATE() ,
NGAY_SUA DATETIME
)
----CHỈNH SỬA----
---THƯƠNG HIÊU "MÃ"
ALTER TABLE THUONG_HIEU
ADD MA_THUONG_HIEU VARCHAR(10);
------------------
---DANH MUC "MÃ"
ALTER TABLE DANH_MUC
ADD MA_DANH_MUC VARCHAR(10);
------------------
---CỔ ÁO "MÃ"
ALTER TABLE CO_AO
ADD MA_CO_AO VARCHAR(10);
------------------
GO
IF OBJECT_ID('CHAT_LIEU') IS NOT NULL
DROP TABLE CHAT_LIEU
CREATE TABLE CHAT_LIEU (
ID INT IDENTITY(1,1) PRIMARY KEY,
MA_CHAT_LIEU VARCHAR(12) ,
TEN_CHAT_LIEU NVARCHAR(225) ,
TRANG_THAI BIT,
NGAY_TAO DATETIME DEFAULT GETDATE() ,
NGAY_SUA DATETIME
)
GO
IF OBJECT_ID('DANH_MUC') IS NOT NULL
DROP TABLE DANH_MUC
CREATE TABLE DANH_MUC (
ID INT IDENTITY(1,1) PRIMARY KEY,
TEN_DANH_MUC NVARCHAR(225) NOT NULL,
TRANG_THAI BIT,
NGAY_TAO DATETIME DEFAULT GETDATE() ,
NGAY_SUA DATETIME
)
GO
IF OBJECT_ID('NHA_SAN_XUAT') IS NOT NULL
DROP TABLE NHA_SAN_XUAT
CREATE TABLE NHA_SAN_XUAT (
ID INT IDENTITY(1,1) PRIMARY KEY,
MA_NHA_SAN_XUAT VARCHAR(12) ,
TEN_NHA_SAN_XUAT NVARCHAR(225) ,
TRANG_THAI BIT,
NGAY_TAO DATETIME DEFAULT GETDATE(),
NGAY_SUA DATETIME
)
GO
IF OBJECT_ID('CO_AO') IS NOT NULL
DROP TABLE CO_AO
CREATE TABLE CO_AO (
ID INT IDENTITY(1,1) PRIMARY KEY,
TEN_CO_AO NVARCHAR(225) ,
TRANG_THAI BIT,
NGAY_TAO DATETIME DEFAULT GETDATE() ,
NGAY_SUA DATETIME
)
GO
IF OBJECT_ID('SAN_PHAM') IS NOT NULL
DROP TABLE SAN_PHAM
CREATE TABLE SAN_PHAM (
ID INT IDENTITY(1,1) PRIMARY KEY,
MA_SAN_PHAM VARCHAR(12) ,
TEN_SAN_PHAM NVARCHAR(255),
ANH_SAN_PHAM NVARCHAR(255),
NGAY_TAO DATETIME DEFAULT GETDATE(),
NGAY_SUA DATETIME,
ID_DANH_MUC INT,
ID_NHA_SAN_XUAT INT,
SO_LUONG_SAN_PHAM INT,
ID_THUONG_HIEU INT,
ID_CO_AO INT,
TRANG_THAI BIT,
CONSTRAINT FK_SAN_PHAM_DANH_MUC FOREIGN KEY(ID_DANH_MUC) REFERENCES DANH_MUC(ID),
CONSTRAINT FK_SAN_PHAM_NHA_SAN_XUA FOREIGN KEY(ID_NHA_SAN_XUAT) REFERENCES NHA_SAN_XUAT(ID),
CONSTRAINT FK_SAN_PHAM_THUONG_HIEU FOREIGN KEY(ID_THUONG_HIEU) REFERENCES THUONG_HIEU(ID),
CONSTRAINT FK_SAN_PHAM_CO_AO FOREIGN KEY(ID_CO_AO) REFERENCES CO_AO (ID),
)
GO
IF OBJECT_ID('KICH_THUOC') IS NOT NULL
DROP TABLE KICH_THUOC
CREATE TABLE KICH_THUOC (
ID INT IDENTITY(1,1) PRIMARY KEY,
MA_KICH_THUOC VARCHAR(10),
TEN_KICH_THUOC NVARCHAR(225) ,
TRANG_THAI BIT,
NGAY_TAO  DATETIME DEFAULT GETDATE(),
NGAY_SUA DATETIME
)

GO
IF OBJECT_ID('MAU_SAC') IS NOT NULL
DROP TABLE MAU_SAC
CREATE TABLE MAU_SAC (
ID INT IDENTITY(1,1) PRIMARY KEY,
MA_MAU_SAC VARCHAR(10),
TEN_MAU_SAC NVARCHAR(225) ,
TRANG_THAI BIT,
NGAY_TAO DATETIME DEFAULT GETDATE(),
NGAY_SUA DATETIME,
)
GO
IF OBJECT_ID('SAN_PHAM_CHI_TIET') IS NOT NULL
DROP TABLE SAN_PHAM_CHI_TIET
CREATE TABLE SAN_PHAM_CHI_TIET (
ID INT IDENTITY(1,1) PRIMARY KEY,
ANH_SAN_PHAM_CHI_TIET NVARCHAR(255),
ID_SAN_PHAM INT,
ID_KICH_THUOC INT,
ID_MAU_SAC INT,
ID_CHAT_LIEU INT,
SO_LUONG_SAN_PHAM_CHI_TIET INT,
DON_GIA DECIMAL(10, 2),
MO_TA NVARCHAR(255) ,
NGAY_TAO DATETIME DEFAULT GETDATE() ,
NGAY_SUA DATETIME,
TRANG_THAI BIT,
CONSTRAINT FK_SAN_PHAM_CHI_TIET_CHAT_LIEU FOREIGN KEY(ID_CHAT_LIEU) REFERENCES CHAT_LIEU(ID),
CONSTRAINT FK_SAN_PHAM_CHI_TIET_SAN_PHAM FOREIGN KEY(ID_SAN_PHAM) REFERENCES SAN_PHAM(ID),
CONSTRAINT FK_SAN_PHAM_CHI_TIET_KICH_THUOC FOREIGN KEY(ID_KICH_THUOC) REFERENCES KICH_THUOC(ID),
CONSTRAINT FK_SAN_PHAM_CHI_TIET_MAU_SAC FOREIGN KEY(ID_MAU_SAC) REFERENCES MAU_SAC(ID),
)
GO

GO
IF OBJECT_ID('KHACH_HANG') IS NOT NULL
DROP TABLE KHACH_HANG
CREATE TABLE KHACH_HANG (
ID INT IDENTITY(1,1) PRIMARY KEY,
MA_KHACH_HANG NVARCHAR(225) ,
TEN_TAI_KHOAN NVARCHAR(225) ,
MAT_KHAU VARCHAR(15),
SO_DIEN_THOAI VARCHAR(12),
DIA_CHI NVARCHAR(225),
TEN_KHACH_HANG NVARCHAR(225) ,
TRANG_THAI BIT,
NGAY_TAO DATETIME DEFAULT GETDATE() ,
NGAY_SUA DATETIME,
)
GO
IF OBJECT_ID('GIO_HANG') IS NOT NULL
DROP TABLE GIO_HANG
CREATE TABLE GIO_HANG (
ID INT IDENTITY(1,1) PRIMARY KEY,
ID_KHACH_HANG INT ,
ID_SAN_PHAM_CHI_TIET INT ,
SO_LUONG INT,
TONG_TIEN DECIMAL(10,2),
CONSTRAINT FK_GIO_HANG_KHACH_HANG FOREIGN KEY (ID_KHACH_HANG) REFERENCES KHACH_HANG (ID),
CONSTRAINT FK_GIO_HANG_SAN_PHAM_CHI_TIET FOREIGN KEY (ID_SAN_PHAM_CHI_TIET) REFERENCES SAN_PHAM_CHI_TIET (ID),
)
GO
IF OBJECT_ID('NHAN_VIEN') IS NOT NULL
DROP TABLE NHAN_VIEN
CREATE TABLE NHAN_VIEN (
ID INT IDENTITY(1,1) PRIMARY KEY,
TEN_NHAN_VIEN NVARCHAR(225) ,
MA_NHAN_VIEN VARCHAR(225) ,
TEN_TAI_KHOAN VARCHAR(225) ,
MAT_KHAU VARCHAR(15),
CHUC_VU VARCHAR(255),
TRANG_THAI BIT,
NGAY_TAO DATETIME DEFAULT GETDATE() ,
NGAY_SUA DATETIME,
)
GO
IF OBJECT_ID('HOA_DON') IS NOT NULL
DROP TABLE HOA_DON
CREATE TABLE HOA_DON (
ID INT IDENTITY(1,1) PRIMARY KEY,
ID_SAN_PHAM INT ,
SO_LUONG INT ,
DON_GIA DECIMAL(10,2) ,
SO_DIEN_THOAI VARCHAR(12) ,
ID_KHACH_HANG INT ,
TONG_TIEN DECIMAL(10,2) ,
NGAY_MUA DATETIME DEFAULT GETDATE(),
TRANG_THAI BIT,
NGAY_TAO DATETIME DEFAULT GETDATE() ,
NGAY_SUA DATETIME,
CONSTRAINT FK_HOA_DON_KHACH_HANG FOREIGN KEY (ID_KHACH_HANG) REFERENCES KHACH_HANG (ID),
CONSTRAINT FK_HOA_DON_SAN_PHAM FOREIGN KEY (ID_SAN_PHAM) REFERENCES SAN_PHAM (ID),
)
GO
IF OBJECT_ID('THANH_TOAN') IS NOT NULL
DROP TABLE THANH_TOAN
CREATE TABLE THANH_TOAN (
ID INT IDENTITY(1,1) PRIMARY KEY,
HINH_THUC_THANH_TOAN INT ,
ID_KHACH_HANG INT ,
ID_HOA_DON INT ,
CONSTRAINT FK_THANH_TOAN_KHACH_HANG FOREIGN KEY (ID_KHACH_HANG) REFERENCES KHACH_HANG (ID),
CONSTRAINT FK_THANH_TOAN_HOA_DON FOREIGN KEY (ID_HOA_DON) REFERENCES HOA_DON (ID),
)
GO
IF OBJECT_ID('HOA_DON_CHI_TIET') IS NOT NULL
DROP TABLE HOA_DON_CHI_TIET
CREATE TABLE HOA_DON_CHI_TIET (
ID INT IDENTITY(1,1) PRIMARY KEY,
ID_NHAN_VIEN INT ,
ID_HOA_DON INT ,
SO_LUONG INT,
DON_GIA DECIMAL(10,2) ,
NGAY_TAO DATETIME DEFAULT GETDATE() ,
NGAY_SUA DATETIME,
THANH_TIEN DECIMAL(10,2),
HINH_THUC_THANH_TOAN NVARCHAR(255) ----- LƯU Ý-------
CONSTRAINT FK_HOA_DON_CHI_TIET_NHAN_VIEN FOREIGN KEY (ID_NHAN_VIEN) REFERENCES NHAN_VIEN (ID),
CONSTRAINT FK_HOA_DON_CHI_TIET_HOA_DON FOREIGN KEY (ID_HOA_DON) REFERENCES HOA_DON (ID),
)
GO
IF OBJECT_ID('DOI_TRA') IS NOT NULL
DROP TABLE DOI_TRA
CREATE TABLE DOI_TRA (
ID INT IDENTITY(1,1) PRIMARY KEY,
ID_KHACH_HANG INT ,
LY_DO_DOI NVARCHAR(255) ,
NGAY_DOI_TRA DATETIME,
TRANG_THAI BIT ,
MO_MA NVARCHAR(255),
ID_HOA_DON_CHI_TIET INT,
HINH_THUC_THANH_TOAN NVARCHAR(255) ----- LƯU Ý-------
CONSTRAINT FK_DOI_TRA_KHACH_HANG FOREIGN KEY (ID_KHACH_HANG) REFERENCES KHACH_HANG (ID),
CONSTRAINT FK_DOI_TRA_HOA_DON_CHI_TIET FOREIGN KEY (ID_HOA_DON_CHI_TIET) REFERENCES HOA_DON_CHI_TIET (ID),
)
GO
IF OBJECT_ID('SAN_PHAM_LOI') IS NOT NULL
DROP TABLE SAN_PHAM_LOI
CREATE TABLE SAN_PHAM_LOI (
ID INT IDENTITY(1,1) PRIMARY KEY,
TEN_SAN_PHAM_LOI NVARCHAR(255),
MO_MA NVARCHAR(255),
ID_HOA_DON_CHI_TIET INT,
SO_LUONG INT ,
CONSTRAINT FK_SAN_PHAM_LOI_HOA_DON_CHI_TIET FOREIGN KEY (ID_HOA_DON_CHI_TIET) REFERENCES HOA_DON_CHI_TIET (ID),
)

GO
IF OBJECT_ID('VOUCHER') IS NOT NULL
DROP TABLE VOUCHER
CREATE TABLE VOUCHER (
ID INT IDENTITY(1,1) PRIMARY KEY,
MA_KHUYEN_MAI NVARCHAR(255),
MO_MA NVARCHAR(255),
TEN_KHUYEN_MAI NVARCHAR(255),
NGAY_BAT_DAU DATETIME DEFAULT GETDATE(),
NGAY_KET_THUC DATETIME ,
MUC_GIAM VARCHAR(100),
TRANG_THAI BIT,
DIEU_KIEN_KHUYEN_MAI NVARCHAR(255),
)

----SỬA CHỮA------
ALTER TABLE Voucher
ADD MA_AP_DUNG VARCHAR(50);
ALTER TABLE Voucher
	ADD Loai Bit;
	ALTER TABLE Voucher
	ADD So_Luong Nvarchar(10);
------------------


GO
IF OBJECT_ID('HOA_DON_VOUCHER') IS NOT NULL
DROP TABLE HOA_DON_VOUCHER
CREATE TABLE HOA_DON_VOUCHER (
ID INT IDENTITY(1,1) PRIMARY KEY,
ID_VOUCHER INT ,
ID_HOA_DON_CHI_TIET INT,
SO_TIEN_CON_LAI DECIMAL(10,2) ,
CONSTRAINT FK_HOA_DON_VOUCHER_VOUCHER FOREIGN KEY (ID_VOUCHER) REFERENCES VOUCHER (ID),
CONSTRAINT FK_HOA_DON_VOUCHER_HOA_DON_CHI_TIEN FOREIGN KEY (ID_HOA_DON_CHI_TIET) REFERENCES HOA_DON_CHI_TIET (ID),
)


----data-----
INSERT INTO THUONG_HIEU (TEN_THUONG_HIEU, NGAY_TAO)
VALUES (N'Thương hiệu A', GETDATE()),
       (N'Thương hiệu B', GETDATE());

INSERT INTO CHAT_LIEU (MA_CHAT_LIEU, TEN_CHAT_LIEU, TRANG_THAI, NGAY_TAO)
VALUES ('CL001', N'Chất liệu A', 1, GETDATE()),
       ('CL002', N'Chất liệu B', 0, GETDATE());

INSERT INTO DANH_MUC (TEN_DANH_MUC, TRANG_THAI, NGAY_TAO)
VALUES (N'Danh mục A', 1, GETDATE()),
       (N'Danh mục B', 0, GETDATE());

 INSERT INTO NHA_SAN_XUAT (MA_NHA_SAN_XUAT, TEN_NHA_SAN_XUAT, TRANG_THAI, NGAY_TAO)
VALUES ('NSX001', N'Nhà sản xuất A', 1, GETDATE()),
       ('NSX002', N'Nhà sản xuất B', 0, GETDATE());

INSERT INTO CO_AO (TEN_CO_AO, TRANG_THAI, NGAY_TAO)
VALUES (N'Cỡ áo A', 1, GETDATE()),
       (N'Cỡ áo B', 0, GETDATE());
INSERT INTO SAN_PHAM (MA_SAN_PHAM, TEN_SAN_PHAM, ANH_SAN_PHAM, NGAY_TAO, ID_DANH_MUC, ID_NHA_SAN_XUAT, SO_LUONG_SAN_PHAM, ID_THUONG_HIEU, ID_CO_AO, TRANG_THAI)
VALUES ('SP001', N'Sản phẩm A', 'image1.jpg', GETDATE(), 1, 1, 100, 1, 1, 1),
       ('SP002', N'Sản phẩm B', 'image2.jpg', GETDATE(), 2, 2, 150, 2, 2, 1);
INSERT INTO KICH_THUOC (MA_KICH_THUOC, TEN_KICH_THUOC, TRANG_THAI, NGAY_TAO)
VALUES ('KT001', N'Kích thước A', 1, GETDATE()),
       ('KT002', N'Kích thước B', 0, GETDATE());
INSERT INTO MAU_SAC (MA_MAU_SAC, TEN_MAU_SAC, TRANG_THAI, NGAY_TAO)
VALUES ('MS001', N'Màu sắc A', 1, GETDATE()),
       ('MS002', N'Màu sắc B', 0, GETDATE());
INSERT INTO SAN_PHAM_CHI_TIET (ANH_SAN_PHAM_CHI_TIET, ID_SAN_PHAM, ID_KICH_THUOC, ID_MAU_SAC, ID_CHAT_LIEU, SO_LUONG_SAN_PHAM_CHI_TIET, DON_GIA, MO_TA, NGAY_TAO, TRANG_THAI)
VALUES ('image_detail1.jpg', 1, 1, 1, 1, 50, 100000, N'Mô tả sản phẩm chi tiết A', GETDATE(), 1),
       ('image_detail2.jpg', 2, 2, 2, 2, 75, 120000, N'Mô tả sản phẩm chi tiết B', GETDATE(), 1);
INSERT INTO KHACH_HANG (MA_KHACH_HANG, TEN_TAI_KHOAN, MAT_KHAU, SO_DIEN_THOAI, DIA_CHI, TEN_KHACH_HANG, TRANG_THAI, NGAY_TAO)
VALUES ('KH001', 'username1', 'password1', '0123456789', 'Địa chỉ A', 'Khách hàng A', 1, GETDATE()),
       ('KH002', 'username2', 'password2', '0987654321', 'Địa chỉ B', 'Khách hàng B', 0, GETDATE());
INSERT INTO GIO_HANG (ID_KHACH_HANG, ID_SAN_PHAM_CHI_TIET, SO_LUONG, TONG_TIEN)
VALUES (1, 1, 2, 200000),
       (2, 2, 1, 120000);
INSERT INTO NHAN_VIEN (TEN_NHAN_VIEN, MA_NHAN_VIEN, TEN_TAI_KHOAN, MAT_KHAU, CHUC_VU, TRANG_THAI, NGAY_TAO)
VALUES (N'Nhân viên A', 'NV001', 'staff1', 'password1', N'Nhân viên bán hàng', 1, GETDATE()),
       (N'Nhân viên B', 'NV002', 'staff2', 'password2', N'Nhân viên kho', 0, GETDATE());
INSERT INTO HOA_DON (ID_SAN_PHAM, SO_LUONG, DON_GIA, SO_DIEN_THOAI, ID_KHACH_HANG, TONG_TIEN, NGAY_MUA, TRANG_THAI, NGAY_TAO)
VALUES (1, 2, 100000, '0123456789', 1, 200000, GETDATE(), 1, GETDATE()),
       (2, 1, 120000, '0987654321', 2, 120000, GETDATE(), 1, GETDATE());
INSERT INTO THANH_TOAN (HINH_THUC_THANH_TOAN, ID_KHACH_HANG, ID_HOA_DON)
VALUES (1, 1, 1),
       (2, 2, 2);
INSERT INTO HOA_DON_CHI_TIET (ID_NHAN_VIEN, ID_HOA_DON, SO_LUONG, DON_GIA, NGAY_TAO, THANH_TIEN, HINH_THUC_THANH_TOAN)
VALUES (1, 1, 2, 100000, GETDATE(), 200000, N'Tiền mặt'),
       (2, 2, 1, 120000, GETDATE(), 120000, N'Chuyển khoản');
INSERT INTO DOI_TRA (ID_KHACH_HANG, LY_DO_DOI, NGAY_DOI_TRA, TRANG_THAI, MO_MA, ID_HOA_DON_CHI_TIET, HINH_THUC_THANH_TOAN)
VALUES (1, N'Lỗi size', GETDATE(), 1, 'Exchange1', 1, N'Tiền mặt'),
       (2, N'Lỗi màu', GETDATE(), 1, 'Exchange2', 2, N'Chuyển khoản');
INSERT INTO SAN_PHAM_LOI (TEN_SAN_PHAM_LOI, MO_MA, ID_HOA_DON_CHI_TIET, SO_LUONG)
VALUES (N'Lỗi sản phẩm A', 'Error1', 1, 1),
       (N'Lỗi sản phẩm B', 'Error2', 2, 2);
INSERT INTO VOUCHER (MA_KHUYEN_MAI, MO_MA, TEN_KHUYEN_MAI, NGAY_BAT_DAU, NGAY_KET_THUC, MUC_GIAM, TRANG_THAI, DIEU_KIEN_KHUYEN_MAI)
VALUES ('VOUCHER001', 'Voucher1', N'Giảm giá 10%', GETDATE(), DATEADD(MONTH, 1, GETDATE()), '10%', 1, N'Áp dụng cho đơn hàng trên 500,000 đồng'),
       ('VOUCHER002', 'Voucher2', N'Giảm giá 20%', GETDATE(), DATEADD(MONTH, 1, GETDATE()), '20%', 1, N'Áp dụng cho đơn hàng trên 1,000,000 đồng');

	
	SELECT * FROM VOUCHER



	   select * from THUONG_HIEU
	   
	   
  ALTER TABLE nhan_vien
DROP COLUMN chuc_vu


  ALTER TABLE nhan_vien
add chuc_vu bit


ALTER TABLE nhan_vien
ALTER COLUMN NGAY_TAO date

ALTER TABLE nhan_vien
ALTER COLUMN Ngay_sua date

