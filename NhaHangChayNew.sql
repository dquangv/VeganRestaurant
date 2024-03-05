create database NhaHangChay_CohesiveStars2;
go

use NhaHangChay_CohesiveStars2;
go

create table TaiKhoan (
	TenTaiKhoan varchar(20) primary key,
	MatKhau varchar(50) not null,
	VaiTro bit not null
	)
go

create table NhanVien (
	MaNhanVien int identity(1, 1) primary key,
	TenNhanVien nvarchar(30) not null,
	ChucVu bit not null,
	TrangThai bit not null,
	GioiTinh bit not null,
	SDT varchar(15) not null,
	Email varchar(50),
	Luong money not null,
	TenTaiKhoan varchar(20),
	hinh nvarchar (200)
	);
go


create table KhachHang (
	MaKhachHang int identity(1, 1) primary key,
	TenKhachHang nvarchar(30) not null,
	SDT varchar(15) not null,
	NgayDkThanhVien date,
	DiemThuong float);
go

/*
create table DanhGia (
	MaDanhGia varchar(10) primary key,
	CauHoi1 int, 
	CauHoi2 int,
	CauHoi3 int,
	DanhGia nvarchar(300));
go
*/

create table KhuyenMai (
	MaKhuyenMai int identity(1, 1) primary key,
	MoTa nvarchar(300) not null,
	PhanTram int not null,
	SoLuong int not null,
	LoaiMa nvarchar(50) not null,
	NgayBatDau datetime not null,
	NgayKetThuc datetime not null);
go

create table Ban (
	MaBan int identity(1, 1) primary key,
	ViTri nvarchar(30) not null,
	TrangThai nvarchar(20) not null);
go

create table NguyenVatLieu (
	MaNguyenVatLieu int identity(1, 1) primary key,
	TenNguyenVatLieu nvarchar(30) not null,
	SoLuong int not null,
	DonViTinh nvarchar(20) not null);
go

create table ThucDon (
	MaThucDon int identity(1, 1) primary key,
	TenThucDon nvarchar(50) not null,
	NgayPhucVu nvarchar(100) not null,
	HinhAnh varchar(500),
	TrangThai nvarchar(30) not null);
go

create table MonAn (
	MaMonAn int identity(1, 1) primary key,
	TenMonAn nvarchar(50) not null,
	DonGia money not null,
	LoaiMonAn nvarchar(50),
	HinhAnh varchar(500),
	TrangThai nvarchar(30) not null);
go


create table ChiTietTD (
	MaThucDon int not null,
	MaMonAn int not null,
	primary key (MaThucDon, MaMonAn));
go

create table ChiTietMA (
	MaMonAn int not null,
	MaNguyenVatLieu int not null,
	SoLuong float not null);
go

create table HoaDon (
	MaHoaDon int identity(1, 1) primary key,
	NgayLap datetime not null default getdate(),
	TienMonAn money not null,
	TienPhatSinh money default 0,
	TienGiamDiemThuong money default 0,
	TienGiamKhuyenMai money default 0,
	TongTien money not null,
	TrangThai nvarchar(30) not null,
	MaBan int not null,
	MaKhuyenMai int,
	MaNhanVien int not null);
go

create table ChiTietHD (
	MaHoaDon int not null,
	MaMonAn int not null,
	SoLuong int not null,
	ThanhTien money not null,
	DanhGia int,
	primary key (MaHoaDon, MaMonAn));
go

create table ChiTietDatBan (
	MaBan int not null,
	ThoiGianDat datetime not null,
	MaKhachHang int not null,
	primary key (MaBan, ThoiGianDat));
go

alter table NhanVien
add
	constraint fk_nv_tk
	foreign key (TenTaiKhoan)
	references TaiKhoan(TenTaiKhoan);
go

alter table ChiTietMA
add
	constraint fk_ctma_nvl
	foreign key (MaNguyenVatLieu)
	references NguyenVatLieu(MaNguyenVatLieu),

	constraint fk_ctma_ma
	foreign key (MaMonAn)
	references MonAn(MaMonAn);
go

alter table ChiTietTD
add
	constraint fk_cttd_td
	foreign key (MaThucDon)
	references ThucDon(MaThucDon),

	constraint fk_cttd_ma
	foreign key (MaMonAn)
	references MonAn(MaMonAn);
go

alter table ChiTietHD
add
	constraint fk_cthd_hd
	foreign key (MaHoaDon)
	references HoaDon(MaHoaDon),

	constraint fk_cthd_ma
	foreign key (MaMonAn)
	references MonAn (MaMonAn);
go

alter table HoaDon
add
	constraint fk_hd_b
	foreign key (MaBan)
	references Ban(MaBan),

	constraint fk_hd_km
	foreign key (MaKhuyenMai)
	references KhuyenMai(MaKhuyenMai),

	constraint fk_hd_nv
	foreign key (MaNhanVien)
	references NhanVien(MaNhanVien);
go

alter table ChiTietDatBan
add 
	constraint fk_db_kh
	foreign key (MaKhachHang)
	references KhachHang(MaKhachHang),
	
	constraint fk_db_b
	foreign key (MaBan)
	references Ban(MaBan);
go

insert into TaiKhoan values
	('LyNDT', '123', 0),
	('TungVT', '123', 0),
	('QuangVD', '123', 1),
	('ChuongVH', '123', 1),
	('QuangBM', '123', 1),
	('RonPN', '123', 1);
go

insert into NhanVien values
  (N'Nguyễn Dương Thiên Lý', 1, 0, 1, '0101010101', 'lyndt@fpt.edu.vn', 45000000, 'LyNDT', 'duong_dan_hinh_1'),
    (N'Võ Thanh Tùng', 1, 0, 0, '0202020202', 'tungvt@fpt.edu.vn', 50000000, 'TungVT', 'duong_dan_hinh_2'),
    (N'Vũ Đăng Quang', 0, 1, 0, '0303030303', 'quangvd@fpt.edu.vn', 30000000, 'QuangVD', 'duong_dan_hinh_3'),
    (N'Vũ Hoàng Chương', 0, 0, 0, '0404040404', 'chuongvh@fpt.edu.vn', 35000000, 'ChuongVH', 'duong_dan_hinh_4'),
    (N'Bùi Minh Quang', 0, 0, 0, '0505050505', 'quangbm@fpt.edu.vn', 40000000, 'QuangBM', 'duong_dan_hinh_5'),
    (N'Phạm Ngọc Rôn', 0, 0, 0, '0606060606', 'ronpn@fpt.edu.vn', 35000000, 'ronpn', 'duong_dan_hinh_6');
go

/*
insert into DanhGia values
	('DG01', 3, 1, 5, null),
	('DG02', 4, 5, 5, N'Quá tuyệt vời'),
	('DG03', 5, 5, 5, N'Sẽ tiếp tục ủng hộ'),
	('DG04', 1, 1, 1, null),
	('DG05', 4, 5, 4, N'Đồ ăn ngon, không gian đẹp, nhân viên dễ thương');
go
*/

insert into KhuyenMai values
	(N'Mừng khai trương', 30, 200, N'Ngày lễ', '01-02-2024', '01-09-2024'),
	(N'Bán vì đam mê', 20, 300, N'Quảng bá', '01-18-2024', '01-31-2024'),
	(N'Mừng thôi nôi', 10, 500, N'Quảng bá', '02-02-2024', '02-09-2024');
go

insert into KhachHang values
	(N'Nghĩa', '1010101010', null, null),
	(N'Triều', '2020202020', '01-02-2024', 0.3),
	(N'Khánh', '3030303030', null, null),
	(N'Đủ', '4040404040', '01-18-2024', 0.1),
	(N'Cường', '5050505050', '01-20-2024', 0.5),
	(N'Thạch', '6060606060', '01-02-2024', 1),
	(N'Quân', '7070707070', '01-02-2024', 1.5),
	(N'Long', '8080808080', '01-10-2024', 0.3),
	(N'Minh', '9090909090', null, null),
	(N'Khoa', '1111111111', '01-02-2024', 0.8);
go
	
insert into Ban values
	(N'Tầng 1, Bàn 1', N'Đang hoạt động'),
	(N'Tầng 1, Bàn 2', N'Đang hoạt động'),
	(N'Tầng 1, Bàn 3', N'Đang hoạt động'),
	(N'Tầng 1, Bàn 4', N'Đang hoạt động'),
	(N'Tầng 1, Bàn 5', N'Đang hoạt động'),
	(N'Tầng 1, Bàn 6', N'Đang hoạt động'),
	(N'Tầng 1, Bàn 7', N'Đang hoạt động'),
	(N'Tầng 1, Bàn 8', N'Đang hoạt động'),
	(N'Tầng 1, Bàn 9', N'Đang hoạt động'),
	(N'Tầng 1, Bàn 10', N'Đang hoạt động'),
	(N'Tầng 1, Bàn 11', N'Đang hoạt động'),
	(N'Tầng 1, Bàn 12', N'Đang hoạt động'),
	(N'Tầng 2, Bàn 1', N'Đang hoạt động'),
	(N'Tầng 2, Bàn 2', N'Đang hoạt động'),
	(N'Tầng 2, Bàn 3', N'Đang hoạt động'),
	(N'Tầng 2, Bàn 4', N'Đang hoạt động'),
	(N'Tầng 2, Bàn 5', N'Đang hoạt động'),
	(N'Tầng 2, Bàn 6', N'Đang hoạt động'),
	(N'Tầng 2, Bàn 7', N'Đang hoạt động'),
	(N'Tầng 2, Bàn 8', N'Đang hoạt động'),
	(N'Tầng 2, Bàn 9', N'Đang hoạt động'),
	(N'Tầng 2, Bàn 10', N'Đang hoạt động'),
	(N'Tầng 2, Bàn 11', N'Đang hoạt động'),
	(N'Tầng 2, Bàn 12', N'Đang bảo trì'),
	(N'Tầng 3, Bàn 1', N'Đang hoạt động'),
	(N'Tầng 3, Bàn 2', N'Đang hoạt động'),
	(N'Tầng 3, Bàn 3', N'Đang hoạt động'),
	(N'Tầng 3, Bàn 4', N'Đang hoạt động'),
	(N'Tầng 3, Bàn 5', N'Đang hoạt động'),
	(N'Tầng 3, Bàn 6', N'Đang hoạt động'),
	(N'Tầng 3, Bàn 7', N'Đang hoạt động'),
	(N'Tầng 3, Bàn 8', N'Đang hoạt động'),
	(N'Tầng 3, Bàn 9', N'Đang hoạt động'),
	(N'Tầng 3, Bàn 10', N'Đang hoạt động'),
	(N'Tầng 3, Bàn 11', N'Đang hoạt động'),
	(N'Tầng 3, Bàn 12', N'Đang hoạt động');
go

insert into NguyenVatLieu values
	(N'Trứng', 500, N'Trái'),
	(N'Chả', 30, N'Kg'),
	(N'Đậu hũ', 1000, N'Miếng'),
	(N'Nấm đông cô', 30, N'Kg'),
	(N'Mướp', 100, N'Trái'),
	(N'Đu đủ', 25, N'Trái'),
	(N'Khổ qua', 25, N'Trái'),
	(N'Mỳ ống', 200, N'Bịch'),
	(N'Khế', 25, N'Trái'),
	(N'Rau tần ô', 15, N'Bó');
go

insert into MonAn values
	(N'Salad mít non', 125000, N'Khai vị', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\xa-lach-mit-non-1.png', N'Hoạt động'),
	(N'Mozzarella Salad', 135000, N'Khai vị', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\mozzarella-salad-1.png', N'Hoạt động'),
	( N'Gỏi cuốn rau củ', 90000, N'Khai vị', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\goi-cuon-rau-cu-1.png', N'Hoạt động'),
	( N'Chả giò', 95000, N'Khai vị', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\cha-gio-3.png', N'Hoạt động'),
	(N'Há cảo Nhật - Gyoza', 95000, N'Khai vị', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\ha-cao-nhat-1.png', N'Hoạt động'),
	( N'Chả nấm mối', 120000, N'Khai vị', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\cha-nam-moi-1.png', N'Hoạt động'),
	(N'Bún nưa trộn', 105000, N'Khai vị', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\bun-nua-tron-1.png', N'Hoạt động'),
	( N'Gỏi củ hủ dừa', 115000, N'Khai vị', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\goi-cu-hu-dua-2.png', N'Hoạt động'),
	( N'Gỏi đu đủ', 105000, N'Khai vị', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\goi-du-du-1.webp', N'Hoạt động'),
	(N'Salad Sung', 135000, N'Khai vị', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\salad-sung-1.webp', N'Hoạt động'),
	(N'Bánh tart artiso', 105000, N'Khai vị', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\salad-sung-1.webp', N'Ngừng phục vụ'),
	(N'Đậu hủ bó xôi sốt trứng muối', 125000, N'Món chính', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\dau-hu-bo-xoi-1.webp', N'Hoạt động'),
	(N'Nấm đông cô sốt tiêu', 95000, N'Món chính', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\nam-dong-co-1.webp', N'Hoạt động'),
	(N'Đậu rồng xào tỏi đen', 90000, N'Món chính', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\dau-rong-xao-1.webp', N'Hoạt động'),
	(N'Tàu hủ ky sốt chao', 110000, N'Món chính', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\tau-ku-ky-1.webp', N'Hoạt động'),
	(N'Mướp xào', 90000, N'Món chính', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\muop-xao-1.webp', N'Hoạt động'),
	(N'Nấm mối xào lá lốt', 125000, N'Món chính', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\nam-moi-xao-1.webp', N'Hoạt động'),
	(N'Đu đủ xào', 90000, N'Món chính', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\du-du-xao-1.webp', N'Hoạt động'),
	(N'Măng xào củ kiệu', 105000, N'Món chính', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\mang-xao-cu-kieu-1.webp', N'Hoạt động'),
	(N'Khổ qua kho ngũ vị', 95000, N'Món chính', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\kho-qua-kho-1.webp', N'Hoạt động'),
	(N'Đậu hủ kim chi', 95000, N'Món chính', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\dau-ku-kim-chi-1.webp', N'Hoạt động'),
	(N'Rau củ om Thái', 110000, N'Món chính', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\rau-cu-om-thai-1.webp', N'Hoạt động'),
	(N'Nấm kho', 105000, N'Món chính', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\nam-kho-2.webp', N'Ngừng phục vụ'),
	(N'Cơm nếp than', 40000, N'Cơm & Mì', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\com-cac-loai.webp', N'Hoạt động'),
	(N'Cơm trắng', 40000, N'Cơm & Mì', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\com-cac-loai.webp', N'Hoạt động'),
	(N'Cơm bó xôi hạt sen', 40000, N'Cơm & Mì', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\com-cac-loai.webp', N'Hoạt động'),
	(N'Mì Ý sốt rau củ', 150000, N'Cơm & Mì', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\mi-y-sot-rau-cu-1.webp', N'Hoạt động'),
	(N'Bún nưa xào', 125000, N'Cơm & Mì', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\bun-nua-xao-1.webp', N'Hoạt động'),
	(N'Mì sốt kem nấm', 150000, N'Cơm & Mì', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\mi-sot-kem-nam-2.webp', N'Hoạt động'),
	(N'Cơm cà ri', 125000, N'Cơm & Mì', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\com-cari-1.png', N'Hoạt động');
go

insert into ThucDon values
	(N'Thực đơn 2-4-6', N'Thứ 2-4-6', null, N'Đang phục vụ'),
	(N'Thực đơn 3-5-7', N'Thứ 3-5-7', null, N'Đang phục vụ'),
	(N'Thực đơn CN', N'Chúa nhật', null, N'Đang phục vụ'),
	(N'Thực đơn mừng lễ khai trương', '01/02/2024', null, N'Ngừng phục vụ'),
	(N'Thực đơn tất niên', N'02/09/2024-02/10-2024', null, N'Chưa phục vụ');
go
insert into HoaDon 
values
    ('2024-01-01 08:30:00', 250000, 0, 0, 0, 250000, N'Đã thanh toán', 1, null, 1),
    ('2024-02-02 12:15:00', 300000, 20000, 0, 0, 320000, N'Đã thanh toán', 2, null, 2),
    ('2024-02-03 19:45:00', 500000, 0, 30000, 0, 530000, N'Đã thanh toán', 3, null, 3),
    ('2024-03-04 14:00:00', 400000, 0, 0, 50000, 450000, N'Chưa thanh toán', 4, 1, 1),
    ('2024-04-05 11:20:00', 350000, 0, 25000, 0, 375000, N'Chưa thanh toán', 5, 2, 2),
    ('2024-05-06 17:30:00', 450000, 0, 0, 0, 450000, N'Chưa thanh toán', 6, 3, 3);
go



insert into ChiTietTD values 
	(1,1),
	(2,2),
	(3,3),
	(4,4),
	(5,5),
	(1,6),
	(2,7),
	(3,8),
	(4,9),
	(5,10),
	(1,11),
	(2,12),
	(3,13),
	(4,14),
	(5,15),
	(1,16),
	(2,17),
	(3,18),
	(4,19),
	(5,20),
	(1,21),
	(2,22),
	(3,23),
	(4,24),
	(5,25),
	(1,26),
	(2,27),
	(3,28),
	(4,29),
	(5,30);
go

insert into ChiTietMA values 
	(1,1,30),
	(2,2,20),
	(3,3,10),
	(4,4,50),
	(5,5,40),
	(6,6,30),
	(7,7,70),
	(8,8,80),
	(9,9,30),
	(10,10,10),
	(11,1,30),
	(12,2,20),
	(13,3,50),
	(14,4,40),
	(15,5,70),
	(16,6,90),
	(17,7,80),
	(18,8,100),
	(19,9,90),
	(20,10,30),
	(21,1,20),
	(22,2,15),
	(23,3,92),
	(24,4,30),
	(25,5,50),
	(26,6,70),
	(27,7,89),
	(28,8,30),
	(29,9,40),
	(30,10,50);
go

insert into ChiTietHD values
	(1,1,1,125000,5),
	(1,15,2,220000,5),
	(2,4,4,380000,5),
	(2,12,1,125000,5),
	(2,16,2,180000,5),
	(3,1,2,250000,5),
	(4,2,2,190000,5),
	(3,15,1,110000,5),
	(3,25,1,40000,5),
	(4,3,1,135000,5),
	(4,4,2,190000,5),
	(4,16,1,90000,5),
	(4,22,1,95000,5),
	(4,25,2,40000,5),
	(5,7,1,105000,5),
	(5,15,2,220000,5),
	(6,1,1,125000,5),
	(6,15,1,110000,5);
go


insert into ChiTietDatBan values 
(1,'2024-01-01 08:30:00',1),
(2,'2024-01-01 08:30:00',2),
(3,'2024-01-01 08:30:00',3),
(4,'2024-01-01 08:30:00',4),
(5,'2024-01-01 08:30:00',5),
(6,'2024-01-01 08:30:00',6),
(7,'2024-01-01 08:30:00',7),
(8,'2024-01-01 08:30:00',8),
(9,'2024-01-01 08:30:00',9),
(10,'2024-01-01 08:30:00',10),
(11,'2024-01-01 08:30:00',1),
(12,'2024-01-01 08:30:00',2),
(13,'2024-01-01 08:30:00',3),
(14,'2024-01-01 08:30:00',4),
(15,'2024-01-01 08:30:00',5),
(16,'2024-01-01 08:30:00',6),
(17,'2024-01-01 08:30:00',7),
(18,'2024-01-01 08:30:00',8),
(19,'2024-01-01 08:30:00',9),
(20,'2024-01-01 08:30:00',10),
(21,'2024-01-01 08:30:00',1),
(22,'2024-01-01 08:30:00',2),
(23,'2024-01-01 08:30:00',3),
(24,'2024-01-01 08:30:00',4),
(25,'2024-01-01 08:30:00',5),
(26,'2024-01-01 08:30:00',6),
(27,'2024-01-01 08:30:00',7),
(28,'2024-01-01 08:30:00',8),
(29,'2024-01-01 08:30:00',9),
(30,'2024-01-01 08:30:00',10),
(31,'2024-01-01 08:30:00',1),
(32,'2024-01-01 08:30:00',2),
(33,'2024-01-01 08:30:00',3),
(34,'2024-01-01 08:30:00',4),
(35,'2024-01-01 08:30:00',5),
(36,'2024-01-01 08:30:00',6);
go