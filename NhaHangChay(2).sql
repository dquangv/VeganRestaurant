
/*
1 create datatabase 
2 create table 
3 insert into data
4 create pro and trigger
*/
-- reset mã tự sinh về 0 sau khi xoá toàn bộ dữ liệu (bảng khuyến mãi)
DBCC CHECKIDENT ('KhuyenMai', RESEED, 0);
go

create database NhaHangChay_CohesiveStars;
go

use NhaHangChay_CohesiveStars;
go

create table NhanVien (
	MaNhanVien int identity(1, 1) primary key,
	TenNhanVien nvarchar(30) not null,
	ChucVu bit not null,
	TrangThai nvarchar(20) not null,
	GioiTinh bit not null,
	SDT varchar(15) not null,
	Email varchar(50),
	HinhAnh varchar(500) null,
);
go

create table TaiKhoan (
	TenTaiKhoan varchar(20) primary key,
	MatKhau varchar(50) not null,
	VaiTro bit not null,
	MaNhanVien int
);
go

create table KhachHang (
	MaKhachHang int identity(1, 1) primary key,
	TenKhachHang nvarchar(30) not null,
	SDT varchar(15),
	NgaySinh date
);
go

create table ThanhVien (
	MaThanhVien int identity(1, 1) primary key,
	NgayDangKy datetime not null,
	DiemThuong int,
	MaKhachHang int
);
go

create table PhieuDatBan (
	MaPhieuDatBan int identity(1, 1) primary key,
	ThoiGianDat datetime,
	MaKhachHang int
);
go

create table Ban (
	MaBan int identity(1, 1) primary key,
	ViTri nvarchar(30) not null,
	TrangThai nvarchar(20) not null);
go

create table ChiTietDatBan (
	MaBan int,
	MaPhieuDatBan int,
	primary key (MaBan, MaPhieuDatBan)
);
go

create table DanhGia (
	MaDanhGia int identity(1, 1) primary key,
	DanhGia nvarchar(20) not null
);
go

create table LoaiMon (
	MaLoaiMon int identity(1, 1) primary key,
	TenLoaiMon nvarchar(20) not null
);
go

create table MonAn (
	MaMonAn int identity(1, 1) primary key,
	TenMonAn nvarchar(50) not null,
	DonGia money not null,
	HinhAnh varchar(500),
	TrangThai nvarchar(30) not null,
	MaLoaiMon int);
go

create table ThucDon (
	MaThucDon int identity(1, 1) primary key,
	TenThucDon nvarchar(50) not null,
	NgayPhucVu nvarchar(100) not null
);
go

create table ChiTietTD (
	MaThucDon int not null,
	MaMonAn int not null,
	primary key (MaThucDon, MaMonAn));
go

create table ChiTietGM (
	MaPhieuDatBan int,
	MaMonAn int,
	SoLuong int not null,
	GhiChu nvarchar(200),
	MaDanhGia int,
	primary key (MaPhieuDatBan, MaMonAn)
);
go

create table KhuyenMai (
	MaKhuyenMai int identity(1, 1) primary key,
	MoTa nvarchar(300) not null,
	PhanTram int not null,
	SoLuong int not null,
	NgayBatDau datetime not null,
	NgayKetThuc datetime not null);
go

create table HoaDon (
	MaHoaDon int identity(1, 1) primary key,
	NgayLap datetime not null default getdate(),
	TienMonAn money not null,
	TienGiamDiemThuong money default 0,
	TienGiamKhuyenMai money default 0,
	TongTien money not null,
	PhuongThucThanhToan bit,
	MaPhieuDatBan int,
	MaKhuyenMai int,
	MaNhanVien int
);
go

alter table ThanhVien
add
	constraint fk_tv_kh
	foreign key (MaKhachHang)
	references KhachHang(MaKhachHang)
;
go

alter table TaiKhoan
add
	constraint fk_tk_nv
	foreign key (MaNhanVien)
	references NhanVien(MaNhanVien)
;
go

alter table PhieuDatBan
add
	constraint fk_pdb_kh
	foreign key (MaKhachHang)
	references KhachHang(MaKhachHang)
;
go

alter table ChiTietDatBan
add
	constraint fk_ctdb_b
	foreign key (MaBan)
	references Ban(MaBan),

	constraint fk_ctdb_pdb
	foreign key (MaPhieuDatBan)
	references PhieuDatBan(MaPhieuDatBan)
;
go

alter table MonAn
add
	constraint fk_ma_l
	foreign key (MaLoaiMon)
	references LoaiMon(MaLoaiMon)
;
go

alter table ChiTietTD
add
	constraint fk_cttd_td
	foreign key (MaThucDon)
	references ThucDon (MaThucDon),

	constraint fk_cttd_ma
	foreign key (MaMonAn)
	references MonAn(MaMonAn)
;
go

alter table ChiTietGM
add
	constraint fk_ctgm_pdb
	foreign key (MaPhieuDatBan)
	references PhieuDatBan(MaPhieuDatBan),

	constraint fk_ctgm_ma
	foreign key (MaMonAn)
	references MonAn(MaMonAn),

	constraint fk_ctgm_dg
	foreign key (MaDanhGia)
	references DanhGia(MaDanhGia)
;
go

alter table HoaDon
add
	constraint fk_hd_db
	foreign key (MaPhieuDatBan)
	references PhieuDatBan(MaPhieuDatBan),

	constraint fk_hd_km
	foreign key (MaKhuyenMai)
	references KhuyenMai(MaKhuyenmai),

	constraint fk_hd_nv
	foreign key (MaNhanVien)
	references NhanVien(MaNhanVien)
;
go

insert into NhanVien (TenNhanVien, ChucVu, TrangThai, GioiTinh, SDT, Email, HinhAnh) values
	(N'Nguyễn Dương Thiên Lý', 1, N'Hoạt động', 1, '0101010101', 'lyndtps36846@fpt.edu.vn', null),
	(N'Võ Thanh Tùng', 1, N'Hoạt động', 0, '0202020202', 'tungvtps27852@fpt.edu.vn', null),
	(N'Vũ Đăng Quang', 0, N'Nghỉ', 0, '0303030303', 'quangvdps36680@fpt.edu.vn', null);
go

insert into TaiKhoan (TenTaiKhoan, MatKhau, VaiTro, MaNhanVien) values
	('LyNDT', '123', 1, 1),
	('TungVT', '123', 1, 2),
	('QuangVD', '123', 0, 3);
go

insert into KhachHang (TenKhachHang, SDT, NgaySinh) values
	(N'Nguyễn Hải Triều', '1010101010', '2024-09-23'),
	(N'Dương Quang Nghĩa', '2020202020', null),
	(N'Mai Việt Cường', '3030303030', null);
go

insert into ThanhVien (NgayDangKy, DiemThuong, MaKhachHang) values
	('2024-01-18', 5000, 1);
go

insert into Ban (ViTri, TrangThai) values
	(N'Tầng 1, Bàn 1', N'Hoạt động'),
	(N'Tầng 1, Bàn 2', N'Hoạt động'),
	(N'Tầng 1, Bàn 3', N'Hoạt động'),
	(N'Tầng 1, Bàn 4', N'Hoạt động'),
	(N'Tầng 1, Bàn 5', N'Hoạt động'),
	(N'Tầng 1, Bàn 6', N'Hoạt động'),
	(N'Tầng 1, Bàn 7', N'Hoạt động'),
	(N'Tầng 1, Bàn 8', N'Hoạt động'),
	(N'Tầng 1, Bàn 9', N'Hoạt động'),
	(N'Tầng 1, Bàn 10', N'Hoạt động'),
	(N'Tầng 1, Bàn 11', N'Hoạt động'),
	(N'Tầng 1, Bàn 12', N'Hoạt động'),
	(N'Tầng 2, Bàn 1', N'Hoạt động'),
	(N'Tầng 2, Bàn 2', N'Hoạt động'),
	(N'Tầng 2, Bàn 3', N'Hoạt động'),
	(N'Tầng 2, Bàn 4', N'Hoạt động'),
	(N'Tầng 2, Bàn 5', N'Hoạt động'),
	(N'Tầng 2, Bàn 6', N'Hoạt động'),
	(N'Tầng 2, Bàn 7', N'Hoạt động'),
	(N'Tầng 2, Bàn 8', N'Hoạt động'),
	(N'Tầng 2, Bàn 9', N'Hoạt động'),
	(N'Tầng 2, Bàn 10', N'Hoạt động'),
	(N'Tầng 2, Bàn 11', N'Hoạt động'),
	(N'Tầng 2, Bàn 12', N'Hoạt động'),
	(N'Tầng 3, Bàn 1', N'Hoạt động'),
	(N'Tầng 3, Bàn 2', N'Hoạt động'),
	(N'Tầng 3, Bàn 3', N'Hoạt động'),
	(N'Tầng 3, Bàn 4', N'Hoạt động'),
	(N'Tầng 3, Bàn 5', N'Hoạt động'),
	(N'Tầng 3, Bàn 6', N'Hoạt động'),
	(N'Tầng 3, Bàn 7', N'Hoạt động'),
	(N'Tầng 3, Bàn 8', N'Hoạt động'),
	(N'Tầng 3, Bàn 9', N'Hoạt động'),
	(N'Tầng 3, Bàn 10', N'Hoạt động'),
	(N'Tầng 3, Bàn 11', N'Hoạt động'),
	(N'Tầng 3, Bàn 12', N'Hoạt động');
go

insert into PhieuDatBan (ThoiGianDat, MaKhachHang) values
	('2024-01-18 09:00', 1),
	('2024-01-19 12:00', 2),
	('2024-01-19 17:00', 3);
go

insert into ChiTietDatBan (MaBan, MaPhieuDatBan) values
	(1, 1),
	(1, 2),
	(4, 3);
go

insert into LoaiMon (TenLoaiMon) values
	(N'Bún'),
	(N'Cơm'),
	(N'Khai vị');
go

insert into MonAn (TenMonAn, DonGia, HinhAnh, TrangThai, MaLoaiMon) values
	(N'Bún Huế', 60000, null, N'Hoạt động', 1),
	(N'Bún chay 1', 50000, null, N'Hoạt động', 1),
	(N'Bún chay 2', 50000, null, N'Hoạt động', 1),
	(N'Cơm chay 1', 50000, null, N'Hoạt động', 2),
	(N'Cơm chay 2', 50000, null, N'Tạm ngưng', 2),
	(N'Đậu hũ chay', 25000, null, N'Hoạt động', 3),
	(N'Chả giò chay', 25000, null, N'Hoạt động', 3);
go

insert into ThucDon (TenThucDon, NgayPhucVu) values
	(N'Ngày chẵn', '2-4-6-CN'),
	(N'Ngày lẻ', '3-5-7');
go

insert into ChiTietTD (MaThucDon, MaMonAn) values
	(1, 1),
	(1, 2),
	(1, 4),
	(1, 6),
	(2, 1),
	(2, 3),
	(2, 4),
	(2, 7);
go

insert into DanhGia (DanhGia) values
	(N'Tệ'),
	(N'Không ngon'),
	(N'Bình thường'),
	(N'Khá ổn'),
	(N'Tuyệt vời');
go

insert into ChiTietGM (MaPhieuDatBan, MaMonAn, SoLuong, GhiChu, MaDanhGia) values
	(1, 1, 1, N'Làm mặn xíu', 5),
	(1, 4, 1, null, 4),
	(1, 7, 2, N'Thêm tương ớt', 5),
	(2, 1, 1, null, 3),
	(2, 2, 1, null, 2),
	(2, 4, 1, null, null),
	(2, 6, 2, null, 4),
	(3, 1, 1, N'Không hành', 4),
	(3, 4, 1, null, 4);
go

insert into KhuyenMai (MoTa, PhanTram, SoLuong, NgayBatDau, NgayKetThuc) values
	(N'Mừng khai trương', 50, 200, '2024-01-18', '2024-01-19 23:59'),
	(N'Mừng sinh nhật', 10, 10000, '2024-01-18', '2025-01-17 23:59'),
	(N'Quảng bá món mới', 15, 300, '2024-03-01', '2024-03-07 23:59');
go

insert into HoaDon (NgayLap, TienMonAn, TienGiamDiemThuong, TienGiamKhuyenMai, TongTien, PhuongThucThanhToan, MaPhieuDatBan, MaKhuyenMai, MaNhanVien) values
	('2024-01-18 10:00', 160000, 0, 80000, 80000, 0, 1, 1, 2),
	('2024-01-19 13:30', 210000, 0, 105000, 105000, 1, 2, 1, 1),
	('2024-01-19 17:30', 110000, 0, 55000, 55000, 1, 3, 1, 2);
go

insert into MonAn ( TenMonAn, DonGia, HinhAnh, TrangThai, MaLoaiMon) values
    ( N'Salad mít non', 125000, 'xa-lach-mit-non-2.png', N'Hoạt động',3),
    ( N'Mozzarella Salad', 135000,'saladmozarella.png', N'Hoạt động',3),
    ( N'Gỏi cuốn rau củ', 90000,'goicuonraucu.png', N'Hoạt động',3),
    (N'Chả giò', 95000,'chagio.png', N'Hoạt động',3),
    ( N'Há cảo Nhật - Gyoza', 95000, 'hacaonhat.png', N'Hoạt động',3),
    (N'Chả nấm mối', 120000,  'chachammuoi.png', N'Hoạt động',3),
    (N'Bún nưa trộn', 105000, 'bunnuatron.png', N'Hoạt động',3),
    (N'Gỏi củ hủ dừa', 115000,  'coicuhudua.png', N'Hoạt động',3),
    (N'Gỏi đu đủ', 105000,  'goidudu.png', N'Hoạt động',3),
    (N'Salad Sung', 135000, 'saladsung.png', N'Hoạt động',3),
    (N'Bánh tart artiso', 105000, 'banhtart.png', N'Ngừng phục vụ',3),
    (N'Đậu hủ bó xôi sốt trứng muối', 125000,  'dauhuboxoi.png', N'Hoạt động',3),
    (N'Nấm đông cô sốt tiêu', 95000, 'namdongcosottieu.png', N'Hoạt động',3),
    (N'Đậu rồng xào tỏi đen', 90000,  'dau-rong-xao-1.png', N'Hoạt động',3),
    (N'Tàu hủ ky sốt chao', 110000, 'tau-ku-ky-1.png', N'Hoạt động',3),
    (N'Mướp xào', 90000,  'muop-xao-1.png', N'Hoạt động',3),
    (N'Nấm mối xào lá lốt', 125000, 'nam-moi-xao-1.png', N'Hoạt động',3),
    (N'Đu đủ xào', 90000,  'du-du-xao-1.png', N'Hoạt động',3),
    (N'Măng xào củ kiệu', 105000,  'mang-xao-cu-kieu-1.png', N'Hoạt động',3),
    (N'Khổ qua kho ngũ vị', 95000,  'kho-qua-kho-1.png', N'Hoạt động',3),
    (N'Đậu hủ kim chi', 95000,  'dau-hu-kim-chi-1.png', N'Hoạt động',3),
    (N'Rau củ om Thái', 110000,  'rau-cu-om-thai-1.png', N'Hoạt động',3),
    (N'Nấm kho', 105000,  'nam-kho-2.png', N'Ngừng phục vụ',3),
    (N'Cơm nếp than', 40000,  'com-cac-loai.png', N'Hoạt động',2),
    (N'Cơm trắng', 40000, 'com-cac-loai.png', N'Hoạt động',2),
    (N'Cơm bó xôi hạt sen', 40000, 'com-cac-loai.png', N'Hoạt động',2),
    (N'Mì Ý sốt rau củ', 150000,  'mi-y-sot-rau-cu-1.png', N'Hoạt động',1),
    (N'Bún nưa xào', 125000,  'bun-nua-xao-1.png', N'Hoạt động',1),
    (N'Mì sốt kem nấm', 150000,  'mi-sot-kem-nam-2.png', N'Hoạt động',1),
    (N'Cơm cà ri', 125000,  'com-cari-1.png', N'Hoạt động',2);
go
insert into ChiTietTD
values(1,1),
(1,2),
(1,3),
(1,4),
(1,5),
(1,6),
(1,7),
(1,8),
(1,9),
(1,10),
(1,11),
(1,12),
(1,13),
(1,14),
(1,15),
(1,16),
(1,17),
(1,18),
(1,19),
(1,20),
(1,21),
(1,22),
(1,23),
(1,24),
(1,25),
(1,26),
(1,27),
(1,28),
(1,29),
(1,30);

	 
/*
	đây là những proc 

	mn chú ý 
*/
-- thong ke mon an
create or alter proc Sp_ThongKeMonAn
as 
begin 
	select top 7 TenMonAn,SUM(ctgm.SoLuong)  Soluongmonan from ChiTietGM ctgm 
inner join MonAn ma on ctgm.MaMonAn = ma.MaMonAn
group by MA.MaMonAn,TenMonAn
order by Soluongmonan desc
end 
go

select * from ChiTietGM



exec Sp_ThongKeMonAn
-- thong ke doanh thu
CREATE or alter PROCEDURE SP_DoanhThuThang (@nam int)
AS
BEGIN
    
SELECT 
        MONTH(hd.NgayLap) AS Thang,
        SUM(hd.TongTien) AS TongThanhTien
    FROM 
        HoaDon hd
    WHERE 
        YEAR(hd.NgayLap) = @nam
    GROUP BY 
        MONTH(hd.NgayLap);
END
go

SELECT 
        MONTH(hd.NgayLap) AS Thang,
        SUM(hd.TongTien) AS TongThanhTien
    FROM 
        HoaDon hd
    WHERE 
        YEAR(hd.NgayLap) = 2024
    GROUP BY 
        MONTH(hd.NgayLap);
go
select * from HoaDon

 -- proc hoa don 
 go
 create or alter proc sp_HoaDon @maHD varchar(10)
as
select ma.TenMonAn,SoLuong,ThanhTien from ChiTietHD cthd
join MonAn ma on cthd.MaMonAn = ma.MaMonAn
join HoaDon hd on hd.MaHoaDon = cthd.MaHoaDon
where hd.MaHoaDon = @maHD
go

CREATE TRIGGER Trig_UpdateVaiTro
ON NhanVien
AFTER UPDATE
AS
BEGIN
    IF UPDATE(ChucVu) -- Kiểm tra xem cột ChucVu đã được cập nhật
    BEGIN
        UPDATE TaiKhoan
        SET VaiTro = CASE
                        WHEN inserted.ChucVu = 1 THEN 1 -- Nếu ChucVu là 1 (ví dụ: quản lý), gán VaiTro là 1
                        ELSE 0 -- Ngược lại, gán VaiTro là 0
                     END
        FROM TaiKhoan
        INNER JOIN inserted ON TaiKhoan.MaNhanVien = inserted.MaNhanVien
    END
END;


select * from TaiKhoan
 -- lay nam cua ngay lap hoa don
 SELECT DISTINCT year(NgayLap) Year FROM hoadon ORDER BY Year DESC

 -- lay chuoi cua so tang trong bang ban
 select distinct SUBSTRING(vitri,1,CHARINDEX(',',vitri)-1) from Ban
go
