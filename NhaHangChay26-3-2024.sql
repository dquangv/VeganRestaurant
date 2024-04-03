
/*
1 create datatabase 
2 create table 
3 insert into data
4 create pro and trigger
*/
-- reset mã tự sinh về 0 sau khi xoá toàn bộ dữ liệu (bảng khuyến mãi)


DBCC CHECKIDENT ('KhachHang', RESEED, 0);
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
	TenKhachHang nvarchar(30) ,
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
	ThoiGianDat datetime default getdate(),
	MaKhachHang int null
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
	soLuong int,
	HinhAnh varchar(500),
	TrangThai nvarchar(30) not null,
	MaLoaiMon int
	);
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
update Ban set TrangThai = N'Trống' where TrangThai = N'Hoạt động' 
insert into PhieuDatBan (ThoiGianDat, MaKhachHang) values
	('2024-01-18 09:00', 1),
	('2024-01-19 12:00', 2),
	('2024-01-19 17:00', 3);
go

insert into ChiTietDatBan (MaBan, MaPhieuDatBan) values
	(1, 1),
	(1, 2),
	(2, 3);
go

insert into LoaiMon (TenLoaiMon) values
	(N'Bún'),
	(N'Cơm'),
	(N'Khác'),
	(N'Khai vị');
go


insert into MonAn ( TenMonAn, DonGia, SoLuong, HinhAnh, TrangThai, MaLoaiMon) values
    ( N'Salad mít non', 125000, 50, 'xa-lach-mit-non-2.png', N'Hoạt động',3),
    ( N'Mozzarella Salad', 135000, 50,'saladmozarella.png', N'Hoạt động',3),
    ( N'Gỏi cuốn rau củ', 90000, 50,'goicuonraucu.png', N'Hoạt động',3),
    (N'Chả giò', 95000, 50,'chagio.png', N'Hoạt động',3),
    ( N'Há cảo Nhật - Gyoza', 95000, 50, 'hacaonhat.png', N'Hoạt động',3),
    (N'Chả nấm mối', 120000, 50,  'chachammuoi.png', N'Hoạt động',3),
    (N'Bún nưa trộn', 105000, 50, 'bunnuatron.png', N'Hoạt động',3),
    (N'Gỏi củ hủ dừa', 115000, 50,  'coicuhudua.png', N'Hoạt động',3),
    (N'Gỏi đu đủ', 105000, 50,  'goidudu.png', N'Hoạt động',3),
    (N'Salad Sung', 135000, 50, 'saladsung.png', N'Hoạt động',3),
    (N'Bánh tart artiso', 105000, 50, 'banhtart.png', N'Ngừng phục vụ',3),
    (N'Đậu hủ bó xôi sốt trứng muối', 125000, 50,  'dauhuboxoi.png', N'Hoạt động',3),
    (N'Nấm đông cô sốt tiêu', 95000, 50, 'namdongcosottieu.png', N'Hoạt động',3),
    (N'Đậu rồng xào tỏi đen', 90000, 50, 'dau-rong-xao-1.png', N'Hoạt động',3),
    (N'Tàu hủ ky sốt chao', 110000, 50, 'tau-ku-ky-1.png', N'Hoạt động',3),
    (N'Mướp xào', 90000, 50, 'muop-xao-1.png', N'Hoạt động',3),
    (N'Nấm mối xào lá lốt', 125000, 50, 'nam-moi-xao-1.png', N'Hoạt động',3),
    (N'Đu đủ xào', 90000, 50, 'du-du-xao-1.png', N'Hoạt động',3),
    (N'Măng xào củ kiệu', 105000, 50, 'mang-xao-cu-kieu-1.png', N'Hoạt động',3),
    (N'Khổ qua kho ngũ vị', 95000, 50, 'kho-qua-kho-1.png', N'Hoạt động',3),
    (N'Đậu hủ kim chi', 95000, 50, 'dau-hu-kim-chi-1.png', N'Hoạt động',3),
    (N'Rau củ om Thái', 110000, 50, 'rau-cu-om-thai-1.png', N'Hoạt động',3),
    (N'Nấm kho', 105000, 50, 'nam-kho-2.png', N'Ngừng phục vụ',3),
    (N'Cơm nếp than', 40000, 50, 'com-cac-loai.png', N'Hoạt động',2),
    (N'Cơm trắng', 40000, 50, 'com-cac-loai.png', N'Hoạt động',2),
    (N'Cơm bó xôi hạt sen', 40000, 50, 'com-cac-loai.png', N'Hoạt động',2),
    (N'Mì Ý sốt rau củ', 150000, 50, 'mi-y-sot-rau-cu-1.png', N'Hoạt động',1),
    (N'Bún nưa xào', 125000, 50, 'bun-nua-xao-1.png', N'Hoạt động',1),
    (N'Mì sốt kem nấm', 150000, 50, 'mi-sot-kem-nam-2.png', N'Hoạt động',1),
    (N'Cơm cà ri', 125000, 50, 'com-cari-1.png', N'Hoạt động',2);
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
	('2024-01-19 17:30', 110000, 0, 55000, 55000, 1, 3, 1, 2),
	('2024-02-15 11:30', 180000, 0, 90000, 90000, 1, 2, 2, 2),
	('2024-03-10 19:00', 250000, 0, 125000, 125000, 1, 1, 2, 2),
	('2024-04-05 12:45', 120000, 0, 60000, 60000, 0, 1, 3, 1),
	('2024-05-20 14:15', 190000, 0, 95000, 95000, 1, 3, 3, 2),
	('2024-06-08 18:30', 220000, 0, 110000, 110000, 3, 3, 1, 1),
	('2024-07-03 13:00', 200000, 0, 100000, 100000, 2, 2, 1, 2),
	('2024-08-12 16:45', 150000, 0, 75000, 75000, 1, 2, 2, 1),
	('2024-09-25 20:20', 260000, 0, 130000, 130000, 1, 1, 2, 1),
	('2024-10-17 11:00', 140000, 0, 70000, 70000, 0, 2, 1, 1),
	('2024-11-28 17:30', 180000, 0, 90000, 90000, 1, 3, 2, 2),
	('2024-12-09 12:15', 210000, 0, 105000, 105000, 1, 1, 1, 1);
go

	
/*
	đây là những proc 

	mn chú ý 
*/
-- thong ke mon an

create   proc Sp_ThongKeMonAn
as 
begin 
	select top 7 TenMonAn ,sum(ctgm.soluong) as Soluongmonan
	from MonAn ma
	join ChiTietGM ctgm on ctgm.MaMonAn = ma.MaMonAn
	group by TenMonAn
	order by Soluongmonan desc	
end 
go

-- thong ke doanh thu
CREATE PROCEDURE SP_DoanhThuThang (@nam int)
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

 -- proc hoa don 
 create proc [dbo].[sp_ChiTietHD] @maHD varchar(10)
as
select TenMonAn,DonGia,ctgm.SoLuong,sum(DonGia*ctgm.SoLuong) as ThanhTien from HoaDon hd
join PhieuDatBan pdb on hd.MaPhieuDatBan = pdb.MaPhieuDatBan
join ChiTietGM ctgm on pdb.MaPhieuDatBan = ctgm.MaPhieuDatBan
join MonAn ma on ma.MaMonAn = ctgm.MaMonAn
where hd.MaHoaDon = @maHD
group by TenMonAn,DonGia,ctgm.SoLuong
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
go


CREATE or alter PROCEDURE SP_ReSetMaPhieuDatBan (@MaPhieuDatBan int)
AS
BEGIN
    DBCC CHECKIDENT ('PhieuDatBan', RESEED, @MaPhieuDatBan);
END
go


CREATE or alter PROCEDURE SP_ReSetMaKhachHang (@MaKhachHang int)
AS
BEGIN
    DBCC CHECKIDENT ('KhachHang', RESEED, @MaKhachHang);
END
go
-- nhung cai nao chua duooc thanh toan ko can biet qua khu tuong lai  

 select db.MaPhieuDatBan, db.MaBan, kh.MaKhachHang,TenKhachHang,SDT,ThoiGianDat,TrangThai from ChiTietDatBan db 
             inner join PhieuDatBan pdb on pdb.MaPhieuDatBan = db.MaPhieuDatBan 
             inner join KhachHang kh on kh.MaKhachHang = pdb.MaKhachHang 
			 inner join Ban b on b.MaBan = db.MaBan
			where (TenKhachHang like '%' or SDT like '%') 
			And (TrangThai = N'Đã đặt' or TrangThai = N'Đang phục vụ')
            order by thoigiandat


SELECT ChiTietDatBan.MaPhieuDatBan, Ban.MaBan
from ChiTietDatBan
join PhieuDatBan on ChiTietDatBan.MaPhieuDatBan = PhieuDatBan.MaPhieuDatBan
join Ban on ChiTietDatBan.MaBan = Ban.MaBan
where Ban.TrangThai = N'Đang phục vụ' 
and (ChiTietDatBan.MaPhieuDatBan not in (Select MaPhieuDatBan from HoaDon))

select * from ChiTietDatBan