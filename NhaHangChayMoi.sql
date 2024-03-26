
/*
database gop co 4 phan 
1 tao database va cac bang 
2 them du lieu vao database
3 cac proc va trigger 
4 lay nam thang ngay 
cac muc lan luot tu tren xuong duoi
*/
-- tao database
create database NhaHangChay_CohesiveStars;
go
 
use NhaHangChay_CohesiveStars;
go

-- tao table 
create table TaiKhoan (
	TenTaiKhoan varchar(20) primary key,
	MatKhau varchar(50) not null,
	VaiTro bit not null,
	MaNhanVien varchar(10)
--	TrangThai nvarchar(20) not null (không có trong erd)
	);
go


create table NhanVien (
	MaNhanVien varchar(10) primary key,
	TenNhanVien nvarchar(30) not null,
	ChucVu bit not null,
	TrangThai nvarchar(20) not null,
	GioiTinh bit not null,
	SDT varchar(15) not null,
	Email varchar(50),
--	Luong money not null,
--	TenTaiKhoan varchar(20),
	HinhAnh varchar(100)
	);
go

create table KhachHang (
	MaKhachHang int identity(1, 1) primary key,
	TenKhachHang nvarchar(30) not null,
	SDT varchar(15) not null,
	NgayDkThanhVien date,
	DiemThuong float
--	NgaySinh date
	);
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
	MaKhuyenMai varchar(10) primary key,
	MoTa nvarchar(300) not null,
	PhanTram int not null,
	SoLuong int not null,
	LoaiMa nvarchar(50) not null,
	NgayBatDau datetime not null,
	NgayKetThuc datetime not null);
go

create table Ban (
	MaBan varchar(10) primary key,
	ViTri nvarchar(30) not null,
	TrangThai nvarchar(20) not null);
go

create table NguyenVatLieu (
	MaNguyenVatLieu varchar(10) primary key,
	TenNguyenVatLieu nvarchar(30) not null,
	SoLuong int not null,
	DonViTinh nvarchar(20) not null,
--	DonGia money not null (không có trong erd)
	);
go

create table ThucDon (
	MaThucDon varchar(10) primary key,
	TenThucDon nvarchar(50) not null,
	NgayPhucVu nvarchar(100) not null,
--	HinhAnh varchar(500)
--	TrangThai nvarchar(30) not null   (không có trong erd)
	);
go

create table MonAn (
	MaMonAn varchar(10) primary key,
	TenMonAn nvarchar(50) not null,
	DonGia money not null,
	LoaiMonAn nvarchar(50),  -- có cần tách ra 1 thực thể loai môn ăn không?
	HinhAnh varchar(100),
	TrangThai nvarchar(30) not null);
go


create table ChiTietTD (
	MaThucDon varchar(10) not null,
	MaMonAn varchar(10) not null,
	primary key (MaThucDon, MaMonAn));
go

create table ChiTietMA (
	MaMonAn varchar(10) not null,
	MaNguyenVatLieu varchar(10) not null,
	SoLuong float not null);
go

create table HoaDon (
	MaHoaDon varchar(10) primary key,
	NgayLap datetime not null default getdate(),
	TienMonAn money not null,
--	TienPhatSinh money default 0,
	TienGiamDiemThuong money default 0,
	TienGiamKhuyenMai money default 0,
	TongTien money not null,
--	TrangThai nvarchar(30) not null,
--	MaKhachHang varchar(10) not null,
	MaBan varchar(10) not null,
	MaKhuyenMai varchar(10),
	MaNhanVien varchar(10) not null);
--	MaDanhGia varchar(10) trong ERD ko có
go

create table ChiTietHD (
	MaHoaDon varchar(10) not null,
	MaMonAn varchar(10) not null,
	SoLuong int not null,
	ThanhTien money not null,
	DanhGia int,
	primary key (MaHoaDon, MaMonAn));
go

create table ChiTietDB (
	MaBan varchar(10),
	ThoiGianDat datetime,
	MaKhachHang int,
	primary key (MaBan, ThoiGianDat));
go

alter table ChiTietDB
add
	constraint fk_ctdb_b
	foreign key (MaBan)
	references Ban(MaBan),

	constraint fk_ctdb_kh
	foreign key (MaKhachHang)
	references KhachHang(MaKhachHang);
go

alter table TaiKhoan
add
	constraint fk_tk_nv
	foreign key (MaNhanVien)
	references NhanVien(MaNhanVien);
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


insert into NhanVien values
	('NV01', N'Nguyễn Dương Thiên Lý', 1, N'Hoạt động', 1, '0101010101', 'lyndt@fpt.edu.vn', null),
	('NV02', N'Võ Thanh Tùng', 1, N'Hoạt động', 0, '0202020202', 'tungvt@fpt.edu.vn', null),
	('NV03', N'Vũ Đăng Quang', 0, N'Nghỉ', 0, '0303030303', 'quangvd@fpt.edu.vn', null),
	('NV04', N'Vũ Hoàng Chương', 0, N'Hoạt động', 0, '0404040404', 'chuongvh@fpt.edu.vn', null),
	('NV05', N'Bùi Minh Quang', 0, N'Hoạt động', 0, '0505050505', 'quangbm@fpt.edu.vn', null),
	('NV06', N'Phạm Ngọc Rôn', 0, N'Hoạt động', 0, '0606060606', 'ronpn@fpt.edu.vn', null);
go

insert into TaiKhoan values
	('LyNDT', '123', 0, 'NV01'),
	('TungVT', '123', 0, 'NV02'),
	('QuangVD', '123', 1, 'NV03'),
	('ChuongVH', '123', 1, 'NV04'),
	('QuangBM', '123', 1, 'NV05'),
	('RonPN', '123', 1, 'NV06');
go


insert into KhuyenMai values
	('KM01', N'Mừng khai trương', 30, 200, N'Ngày lễ', '01-02-2024', '01-09-2024'),
	('KM02', N'Bán vì đam mê', 20, 300, N'Quảng bá', '01-18-2024', '01-31-2024'),
	('KM03', N'Mừng thôi nôi', 10, 500, N'Quảng bá', '02-02-2024', '02-09-2024');
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
	('T1B01', N'Tầng 1, Bàn 1', N'Đang hoạt động'),
	('T1B02', N'Tầng 1, Bàn 2', N'Đang hoạt động'),
	('T1B03', N'Tầng 1, Bàn 3', N'Đang hoạt động'),
	('T1B04', N'Tầng 1, Bàn 4', N'Đang hoạt động'),
	('T1B05', N'Tầng 1, Bàn 5', N'Đang hoạt động'),
	('T1B06', N'Tầng 1, Bàn 6', N'Đang hoạt động'),
	('T1B07', N'Tầng 1, Bàn 7', N'Đang hoạt động'),
	('T1B08', N'Tầng 1, Bàn 8', N'Đang hoạt động'),
	('T1B09', N'Tầng 1, Bàn 9', N'Đang hoạt động'),
	('T1B10', N'Tầng 1, Bàn 10', N'Đang hoạt động'),
	('T1B11', N'Tầng 1, Bàn 11', N'Đang hoạt động'),
	('T1B12', N'Tầng 1, Bàn 12', N'Đang hoạt động'),
	('T2B01', N'Tầng 2, Bàn 1', N'Đang hoạt động'),
	('T2B02', N'Tầng 2, Bàn 2', N'Đang hoạt động'),
	('T2B03', N'Tầng 2, Bàn 3', N'Đang hoạt động'),
	('T2B04', N'Tầng 2, Bàn 4', N'Đang hoạt động'),
	('T2B05', N'Tầng 2, Bàn 5', N'Đang hoạt động'),
	('T2B06', N'Tầng 2, Bàn 6', N'Đang hoạt động'),
	('T2B07', N'Tầng 2, Bàn 7', N'Đang hoạt động'),
	('T2B08', N'Tầng 2, Bàn 8', N'Đang hoạt động'),
	('T2B09', N'Tầng 2, Bàn 9', N'Đang hoạt động'),
	('T2B10', N'Tầng 2, Bàn 10', N'Đang hoạt động'),
	('T2B11', N'Tầng 2, Bàn 11', N'Đang hoạt động'),
	('T2B12', N'Tầng 2, Bàn 12', N'Đang bảo trì'),
	('T3B01', N'Tầng 3, Bàn 1', N'Đang hoạt động'),
	('T3B02', N'Tầng 3, Bàn 2', N'Đang hoạt động'),
	('T3B03', N'Tầng 3, Bàn 3', N'Đang hoạt động'),
	('T3B04', N'Tầng 3, Bàn 4', N'Đang hoạt động'),
	('T3B05', N'Tầng 3, Bàn 5', N'Đang hoạt động'),
	('T3B06', N'Tầng 3, Bàn 6', N'Đang hoạt động'),
	('T3B07', N'Tầng 3, Bàn 7', N'Đang hoạt động'),
	('T3B08', N'Tầng 3, Bàn 8', N'Đang hoạt động'),
	('T3B09', N'Tầng 3, Bàn 9', N'Đang hoạt động'),
	('T3B10', N'Tầng 3, Bàn 10', N'Đang hoạt động'),
	('T3B11', N'Tầng 3, Bàn 11', N'Đang hoạt động'),
	('T3B12', N'Tầng 3, Bàn 12', N'Đang hoạt động');
go

insert into NguyenVatLieu values
	('NVL01', N'Trứng', 500, N'Trái'),
	('NVL02', N'Chả', 30, N'Kg'),
	('NVL03', N'Đậu hũ', 1000, N'Miếng'),
	('NVL04', N'Nấm đông cô', 30, N'Kg'),
	('NVL05', N'Mướp', 100, N'Trái'),
	('NVL06', N'Đu đủ', 25, N'Trái'),
	('NVL07', N'Khổ qua', 25, N'Trái'),
	('NVL08', N'Mỳ ống', 200, N'Bịch'),
	('NVL09', N'Khế', 25, N'Trái'),
	('NVL10', N'Rau tần ô', 15, N'Bó');
go

insert into MonAn (MaMonAn, TenMonAn, DonGia, LoaiMonAn, HinhAnh, TrangThai) values
    ('MA01', N'Salad mít non', 125000, N'Khai vị', 'xa-lach-mit-non-2.png', N'Hoạt động'),
    ('MA02', N'Mozzarella Salad', 135000, N'Khai vị', 'saladmozarella.png', N'Hoạt động'),
    ('MA03', N'Gỏi cuốn rau củ', 90000, N'Khai vị', 'goicuonraucu.png', N'Hoạt động'),
    ('MA04', N'Chả giò', 95000, N'Khai vị', 'chagio.png', N'Hoạt động'),
    ('MA05', N'Há cảo Nhật - Gyoza', 95000, N'Khai vị', 'hacaonhat.png', N'Hoạt động'),
    ('MA06', N'Chả nấm mối', 120000, N'Khai vị', 'chachammuoi.png', N'Hoạt động'),
    ('MA07', N'Bún nưa trộn', 105000, N'Khai vị', 'bunnuatron.png', N'Hoạt động'),
    ('MA08', N'Gỏi củ hủ dừa', 115000, N'Khai vị', 'coicuhudua.png', N'Hoạt động'),
    ('MA09', N'Gỏi đu đủ', 105000, N'Khai vị', 'goidudu.png', N'Hoạt động'),
    ('MA10', N'Salad Sung', 135000, N'Khai vị', 'saladsung.png', N'Hoạt động'),
    ('MA11', N'Bánh tart artiso', 105000, N'Khai vị', 'banhtart.png', N'Ngừng phục vụ'),
    ('MA12', N'Đậu hủ bó xôi sốt trứng muối', 125000, N'Món chính', 'dauhuboxoi.png', N'Hoạt động'),
    ('MA13', N'Nấm đông cô sốt tiêu', 95000, N'Món chính', 'namdongcosottieu.png', N'Hoạt động'),
    ('MA14', N'Đậu rồng xào tỏi đen', 90000, N'Món chính', 'dau-rong-xao-1.png', N'Hoạt động'),
    ('MA15', N'Tàu hủ ky sốt chao', 110000, N'Món chính', 'tau-ku-ky-1.png', N'Hoạt động'),
    ('MA16', N'Mướp xào', 90000, N'Món chính', 'muop-xao-1.png', N'Hoạt động'),
    ('MA17', N'Nấm mối xào lá lốt', 125000, N'Món chính', 'nam-moi-xao-1.png', N'Hoạt động'),
    ('MA18', N'Đu đủ xào', 90000, N'Món chính', 'du-du-xao-1.png', N'Hoạt động'),
    ('MA20', N'Măng xào củ kiệu', 105000, N'Món chính', 'mang-xao-cu-kieu-1.png', N'Hoạt động'),
    ('MA21', N'Khổ qua kho ngũ vị', 95000, N'Món chính', 'kho-qua-kho-1.png', N'Hoạt động'),
    ('MA22', N'Đậu hủ kim chi', 95000, N'Món chính', 'dau-hu-kim-chi-1.png', N'Hoạt động'),
    ('MA23', N'Rau củ om Thái', 110000, N'Món chính', 'rau-cu-om-thai-1.png', N'Hoạt động'),
    ('MA24', N'Nấm kho', 105000, N'Món chính', 'nam-kho-2.png', N'Ngừng phục vụ'),
    ('MA25', N'Cơm nếp than', 40000, N'Cơm & Mì', 'com-cac-loai.png', N'Hoạt động'),
    ('MA26', N'Cơm trắng', 40000, N'Cơm & Mì', 'com-cac-loai.png', N'Hoạt động'),
    ('MA27', N'Cơm bó xôi hạt sen', 40000, N'Cơm & Mì', 'com-cac-loai.png', N'Hoạt động'),
    ('MA28', N'Mì Ý sốt rau củ', 150000, N'Cơm & Mì', 'mi-y-sot-rau-cu-1.png', N'Hoạt động'),
    ('MA29', N'Bún nưa xào', 125000, N'Cơm & Mì', 'bun-nua-xao-1.png', N'Hoạt động'),
    ('MA30', N'Mì sốt kem nấm', 150000, N'Cơm & Mì', 'mi-sot-kem-nam-2.png', N'Hoạt động'),
    ('MA31', N'Cơm cà ri', 125000, N'Cơm & Mì', 'com-cari-1.png', N'Hoạt động');
go


insert into ThucDon values
	('TD1', N'Thực đơn chẵn', N'Thứ 2-4-6-CN'),
	('TD2', N'Thực đơn lẻ', N'Thứ 3-5-7');
go

insert into ChiTietTD values
	('TD1', 'MA01'),
	('TD1', 'MA03'),
	('TD1', 'MA05'),
	('TD1', 'MA07'),
	('TD1', 'MA09'),
	('TD1', 'MA11'),
	('TD1', 'MA13'),
	('TD1', 'MA15'),
	('TD1', 'MA17'),
--	('TD1','MA19') ,
	('TD1', 'MA21'),
	('TD1', 'MA23'),
	('TD1', 'MA25'),
	('TD1', 'MA27'),
	('TD1', 'MA29'),
	('TD1', 'MA31'),
	('TD2', 'MA02'),
	('TD2', 'MA04'),
	('TD2', 'MA06'),
	('TD2', 'MA08'),
	('TD2', 'MA10'),
	('TD2', 'MA12'),
	('TD2', 'MA14'),
	('TD2', 'MA16'),
	('TD2', 'MA18'),
	('TD2', 'MA20'),
	('TD2', 'MA22'),
	('TD2', 'MA24'),
	('TD2', 'MA26'),
	('TD2', 'MA28'),
	('TD2', 'MA30');
go

insert into ChiTietMA values
	('MA12', 'NVL01', 2),
	('MA12', 'NVL03', 3),
	('MA13', 'NVL04', 0.3)
go


insert into HoaDon values
	('HD01', '1-03-2024', 345000, 0, 0, 345000, 'T1B01',null, 'NV02'),
	('HD02', '1-04-2024', 685000, 205500, 0, 479500, 'T1B09', null, 'NV03'),
	('HD03', '1-24-2024', 590000, 119000, 0, 476000,'T2B07', null,'NV06'),
	('HD04', '1-25-2024', 550000, 116000, 464000, 10, 'T3B02', 'KM02', 'NV04'),
	('HD05', '1-26-2024', 325000, 0, 0, 330000, 'T3B06', null, 'NV01'),
	('HD06', '1-26-2024', 235000, 0, 0, 240000, 'T3B12', null, 'NV05')
go


insert into ChiTietHD values
	('HD01', 'MA01', 1, 125000, 5),
	('HD01', 'MA15', 2, 220000, 5),
	('HD02', 'MA04', 4, 380000, 5),
	('HD02', 'MA12', 1, 125000, 5),
	('HD02', 'MA16', 2, 180000, 5),
	('HD03', 'MA01', 2, 250000, 5),
	('HD03', 'MA05', 2, 190000, 5),
	('HD03', 'MA15', 1, 110000, 5),
	('HD03', 'MA25', 1, 40000, 5),
	('HD04', 'MA02', 1, 135000, 5),
	('HD04', 'MA04', 2, 190000, 5),
	('HD04', 'MA16', 1, 90000, 5),
	('HD04', 'MA22', 1, 95000, 5),
	('HD04', 'MA26', 2, 40000, 5),
	('HD05', 'MA07', 1, 105000, 5),
	('HD05', 'MA15', 2, 220000, 5),
	('HD06', 'MA01', 1, 125000, 5),
	('HD06', 'MA15', 1, 110000, 5);
	--('MaHD','MaMonAn',soluong,tongtien,'đánh giá') nếu cần thêm
go



-- Tháng 2/2023
insert into HoaDon values
	('HD07', '2-01-2023', 500000, 0, 150000, 365000, 'T1B01', 'KM01', 'NV02'),
	('HD08', '2-02-2023', 650000, 0, 250000, 370000, 'T1B02', 'KM02', 'NV03'),
	('HD09', '2-03-2023', 700000, 0, 300000, 360000, 'T1B03', 'KM01', 'NV04'),
	('HD10', '2-04-2023', 800000, 0, 350000, 400000, 'T1B04', 'KM02', 'NV05'),
	('HD11', '2-05-2023', 900000, 0, 400000, 440000, 'T1B05', 'KM01', 'NV06');
go

-- Tháng 3/2023
insert into HoaDon values
	('HD12', '3-01-2023', 750000 ,0, 200000, 510000, 'T1B06', 'KM02', 'NV01'),
	('HD13', '3-02-2023', 600000, 0, 180000, 372000, 'T1B07', 'KM01', 'NV02'),
	('HD14', '3-03-2023', 850000, 0, 160000, 669000, 'T1B08', 'KM02', 'NV03'),
	('HD15', '3-04-2023', 950000, 0, 140000, 713000, 'T1B09', 'KM01', 'NV04'),
	('HD16', '3-05-2023', 1000000,0, 120000, 887000, 'T1B10', 'KM02', 'NV05');
go

	-- Tháng 4/2023
insert into HoaDon values
	('HD17', '4-01-2023', 750000, 0, 200000, 510000, 'T1B06', 'KM02', 'NV01'),
	('HD18', '4-02-2023', 600000, 0, 180000, 372000, 'T1B07', 'KM01', 'NV02'),
	('HD19', '4-03-2023', 850000, 0, 160000, 669000, 'T1B08', 'KM02', 'NV03'),
	('HD20', '4-04-2023', 950000, 0, 140000, 713000, 'T1B09', 'KM01', 'NV04'),
	('HD21', '4-05-2023', 1000000,0, 120000, 887000, 'T1B10', 'KM02', 'NV05');
go

	-- Tháng 5/2023
insert into HoaDon values
	('HD22', '5-01-2023', 750000, 0, 200000, 510000, 'T1B06', 'KM02', 'NV01'),
	('HD23', '5-02-2023', 600000, 0, 180000, 372000, 'T1B07', 'KM01', 'NV02'),
	('HD24', '5-03-2023', 850000, 0, 160000, 669000, 'T1B08', 'KM02', 'NV03'),
	('HD25', '5-04-2023', 950000, 0, 140000, 713000, 'T1B09', 'KM01', 'NV04'),
	('HD26', '5-05-2023', 1000000 ,0, 120000, 887000, 'T1B10', 'KM02', 'NV05');
go


	-- Tháng 6/2023
insert into HoaDon values
	('HD27', '6-01-2023', 750000, 0, 200000, 510000, 'T1B06', 'KM02', 'NV01'),
	('HD28', '6-02-2023', 600000, 0, 180000, 372000, 'T1B07', 'KM01', 'NV02'),
	('HD29', '6-03-2023', 850000, 0, 160000, 669000, 'T1B08', 'KM02', 'NV03'),
	('HD30', '7-04-2023', 950000, 0, 140000, 713000, 'T1B09', 'KM01', 'NV04'),
	('HD31', '4-05-2023', 1000000, 0, 120000, 887000, 'T1B10', 'KM02', 'NV05');
go
	
	-- Tháng 7/2023
insert into HoaDon values
	('HD32', '7-01-2023', 750000, 0, 200000, 510000, 'T1B06', 'KM02', 'NV01'),
	('HD33', '7-02-2023', 600000, 0, 180000, 372000, 'T1B07', 'KM01', 'NV02'),
	('HD34', '7-03-2023', 850000, 0, 160000, 669000, 'T1B08', 'KM02', 'NV03'),
	('HD35', '7-04-2023', 950000, 0, 140000, 713000, 'T1B09', 'KM01', 'NV04'),
	('HD36', '7-05-2023', 1000000, 0, 120000, 887000, 'T1B10', 'KM02', 'NV05');
go
	
	-- Tháng 8/2023
insert into HoaDon values
	('HD37', '8-01-2023', 750000, 0, 200000, 510000, 'T1B06', 'KM02', 'NV01'),
	('HD38', '8-02-2023', 600000, 0, 180000, 372000, 'T1B07', 'KM01', 'NV02'),
	('HD39', '8-03-2023', 850000, 0, 160000, 669000, 'T1B08', 'KM02', 'NV03'),
	('HD40', '8-04-2023', 950000, 0, 140000, 713000, 'T1B09', 'KM01', 'NV04'),
	('HD41', '8-05-2023', 1000000, 0, 120000, 887000, 'T1B10', 'KM02', 'NV05');
go
	
	-- Tháng 9/2023
insert into HoaDon values
	('HD42', '9-01-2023', 750000, 0, 200000, 510000, 'T1B06', 'KM02', 'NV01'),
	('HD43', '9-02-2023', 600000,  0, 180000, 372000, 'T1B07', 'KM01', 'NV02'),
	('HD44', '9-03-2023', 850000, 0, 160000, 669000, 'T1B08', 'KM02', 'NV03'),
	('HD45', '9-04-2023', 950000,  0, 140000, 713000, 'T1B09', 'KM01', 'NV04'),
	('HD46', '9-05-2023', 1000000,  0, 120000, 887000, 'T1B10', 'KM02', 'NV05');
go

	-- Tháng 10/2023
insert into HoaDon values
	('HD47', '10-01-2023', 750000, 0, 200000, 510000, 'T1B06', 'KM02', 'NV01'),
	('HD48', '10-02-2023', 600000, 0, 180000, 372000, 'T1B07', 'KM01', 'NV02'),
	('HD49', '10-03-2023', 850000, 0, 160000, 669000, 'T1B08', 'KM02', 'NV03'),
	('HD50', '10-04-2023', 950000, 0, 140000, 713000, 'T1B09', 'KM01', 'NV04'),
	('HD51', '10-05-2023', 1000000, 0, 120000, 887000, 'T1B10', 'KM02', 'NV05');
go
	
	-- Tháng 11/2023
insert into HoaDon values
	('HD52', '11-01-2023', 750000, 0, 200000, 510000, 'T1B06', 'KM02', 'NV01'),
	('HD53', '11-02-2023', 600000, 0, 180000, 372000, 'T1B07', 'KM01', 'NV02'),
	('HD54', '11-03-2023', 850000, 0, 160000, 669000, 'T1B08', 'KM02', 'NV03'),
	('HD55', '11-04-2023', 950000,  0, 140000, 713000, 'T1B09', 'KM01', 'NV04'),
	('HD56', '11-05-2023', 1000000, 0, 120000, 887000, 'T1B10', 'KM02', 'NV05');
go

	-- Tháng 12/2023
insert into HoaDon values
	('HD57', '12-01-2023', 750000, 0, 200000, 510000, 'T1B06', 'KM02', 'NV01'),
	('HD58', '12-02-2023', 600000, 0, 180000, 372000, 'T1B07', 'KM01', 'NV02'),
	('HD59', '12-03-2023', 850000, 0, 160000, 669000, 'T1B08', 'KM02', 'NV03'),
	('HD60', '12-04-2023', 950000, 0, 140000, 713000, 'T1B09', 'KM01', 'NV04'),
	('HD61', '12-05-2023', 1000000, 0, 120000, 887000, 'T1B10', 'KM02', 'NV05');
go

	-- thang 1/23 
insert into HoaDon values
	('HD62', '1-01-2023', 750000, 0, 200000, 510000, 'T1B06', 'KM02', 'NV01'),
    ('HD63', '1-02-2023', 600000, 0, 180000, 372000, 'T1B07', 'KM01', 'NV02'),
    ('HD64', '1-03-2023', 850000, 0, 160000, 669000, 'T1B08', 'KM02', 'NV03'),
    ('HD65', '1-04-2023', 950000, 0, 140000, 713000, 'T1B09', 'KM01', 'NV04'),
    ('HD66', '1-05-2023', 1000000, 0, 120000, 887000, 'T1B10', 'KM02', 'NV05');
go

-- thang 12 2023 bo sung	
insert into HoaDon values
	('HD67', '12-01-2023', 750000, 0, 200000, 510000, 'T1B06', 'KM02', 'NV01'),
	('HD68', '12-02-2023', 600000, 0, 180000, 372000, 'T1B07', 'KM01', 'NV02'),
	('HD69', '12-03-2023', 850000, 0, 160000, 669000, 'T1B08', 'KM02', 'NV03'),
	('HD70', '12-04-2023', 950000, 0, 140000, 713000, 'T1B09', 'KM01', 'NV04'),
	('HD71', '12-05-2023', 1000000, 0, 120000, 887000, 'T1B10', 'KM02', 'NV05');
go

-- Tháng 2/2023

insert into ChiTietHD values
	('HD07', 'MA01', 1, 125000, 5),
	('HD07', 'MA02', 1, 135000, 5),
	('HD07', 'MA03', 2, 180000, 5),
	('HD07', 'MA04', 2, 190000, 5),
	('HD07', 'MA05', 1, 95000, 5),
	('HD08', 'MA06', 2, 240000, 5),
	('HD08', 'MA07', 1, 105000, 5),
	('HD08', 'MA08', 2, 230000, 5),
	('HD08', 'MA09', 1, 105000, 5),
	('HD08', 'MA10', 1, 135000, 5),
	('HD09', 'MA11', 2, 210000, 5),
	('HD09', 'MA12', 1, 125000, 5),
	('HD09', 'MA13', 1, 95000, 5),
	('HD09', 'MA14', 2, 180000, 5),
	('HD09', 'MA15', 1, 110000, 5),
	('HD10', 'MA16', 2, 180000, 5),
	('HD10', 'MA17', 1, 125000, 5),
	('HD10', 'MA18', 1, 90000, 5),
	('HD10', 'MA22', 2, 260000, 5),
	('HD10', 'MA20', 1, 105000, 5),
	('HD11', 'MA21', 2, 190000, 5),
	('HD11', 'MA22', 1, 95000, 5),
	('HD11', 'MA23', 1, 110000, 5),
	('HD11', 'MA24', 2, 210000, 5),
	('HD11', 'MA25', 1, 40000, 5);
go

-- Tháng 3/2023
insert into ChiTietHD values
	('HD12', 'MA11', 2, 80000, 5),
	('HD12', 'MA27', 1, 40000, 5),
	('HD12', 'MA28', 1, 150000, 5),
	('HD12', 'MA29', 2, 250000, 5),
	('HD12', 'MA30', 1, 150000, 5),
	('HD13', 'MA01', 2, 250000, 5),
	('HD13', 'MA02', 1, 135000, 5),
	('HD13', 'MA03', 1, 90000, 5),
	('HD13', 'MA04', 1, 95000, 5),
	('HD13', 'MA05', 1, 95000, 5),
	('HD14', 'MA06', 2, 240000, 5),
	('HD14', 'MA07', 1, 105000, 5),
	('HD14', 'MA08', 1, 115000, 5),
	('HD14', 'MA09', 1, 105000, 5),
	('HD14', 'MA10', 1, 135000, 5),
	('HD15', 'MA11', 2, 210000, 5),
	('HD15', 'MA12', 1, 125000, 5),
	('HD15', 'MA13', 1, 95000, 5),
	('HD15', 'MA14', 2, 180000, 5),
	('HD15', 'MA15', 1, 110000, 5),
	('HD16', 'MA16', 2, 180000, 5),
	('HD16', 'MA17', 1, 125000, 5),
	('HD16', 'MA18', 1, 90000, 5),
	('HD16', 'MA12', 2, 260000, 5),
	('HD16', 'MA20', 1, 105000, 5);
go

	-- Chi tiết hóa đơn từ số 17 đến số 61
-- Tháng 4/2023
insert into ChiTietHD values
	('HD17', 'MA01', 2, 250000, 5),
	('HD17', 'MA02', 1, 135000, 5),
	('HD17', 'MA03', 1, 90000, 5),
	('HD17', 'MA04', 2, 190000, 5),
	('HD17', 'MA05', 1, 95000, 5),
	('HD18', 'MA06', 2, 240000, 5),
	('HD18', 'MA07', 1, 105000, 5),
	('HD18', 'MA08', 2, 230000, 5),
	('HD18', 'MA09', 1, 105000, 5),
	('HD18', 'MA10', 1, 135000, 5),
	('HD19', 'MA11', 2, 210000, 5),
	('HD19', 'MA12', 1, 125000, 5),
	('HD19', 'MA13', 1, 95000, 5),
	('HD19', 'MA14', 2, 180000, 5),
	('HD19', 'MA15', 1, 110000, 5),
	('HD20', 'MA16', 2, 180000, 5),
	('HD20', 'MA17', 1, 125000, 5),
	('HD20', 'MA18', 1, 90000, 5),
	('HD20', 'MA22', 2, 260000, 5),
	('HD20', 'MA20', 1, 105000, 5),
	('HD21', 'MA21', 2, 190000, 5),
	('HD21', 'MA22', 1, 95000, 5),
	('HD21', 'MA23', 1, 110000, 5),
	('HD21', 'MA24', 2, 210000, 5),
	('HD21', 'MA25', 1, 40000, 5),
	('HD22', 'MA01', 2, 250000, 5),
	('HD22', 'MA02', 1, 135000, 5),
	('HD22', 'MA03', 1, 90000, 5),
	('HD22', 'MA04', 1, 95000, 5),
	('HD22', 'MA05', 1, 95000, 5),
	('HD23', 'MA06', 2, 240000, 5),
	('HD23', 'MA07', 1, 105000, 5),
	('HD23', 'MA08', 1, 115000, 5),
	('HD23', 'MA09', 1, 105000, 5),
	('HD23', 'MA10', 1, 135000, 5),
	('HD24', 'MA11', 2, 210000, 5),
	('HD24', 'MA12', 1, 125000, 5),
	('HD24', 'MA13', 1, 95000, 5),
	('HD24', 'MA14', 2, 180000, 5),
	('HD24', 'MA15', 1, 110000, 5),
	('HD25', 'MA16', 2, 180000, 5),
	('HD25', 'MA17', 1, 125000, 5),
	('HD25', 'MA18', 1, 90000, 5),
	('HD25', 'MA22', 2, 260000, 5),
	('HD25', 'MA20', 1, 105000, 5),
	('HD26', 'MA21', 2, 190000, 5),
	('HD26', 'MA22', 1, 95000, 5),
	('HD26', 'MA23', 1, 110000, 5),
	('HD26', 'MA24', 2, 210000, 5),
	('HD26', 'MA25', 1, 40000, 5),
	('HD27', 'MA01', 2, 250000, 5),
	('HD27', 'MA02', 1, 135000, 5),
	('HD27', 'MA03', 1, 90000, 5),
	('HD27', 'MA04', 2, 190000, 5),
	('HD27', 'MA05', 1, 95000, 5),
	('HD28', 'MA06', 2, 240000, 5),
	('HD28', 'MA07', 1, 105000, 5),
	('HD28', 'MA08', 2, 230000, 5),
	('HD28', 'MA09', 1, 105000, 5),
	('HD28', 'MA10', 1, 135000, 5),
	('HD29', 'MA11', 2, 210000, 5),
	('HD29', 'MA12', 1, 125000, 5),
	('HD29', 'MA13', 1, 95000, 5),
	('HD29', 'MA14', 2, 180000, 5),
	('HD29', 'MA15', 1, 110000, 5),
	('HD30', 'MA16', 2, 180000, 5),
	('HD30', 'MA17', 1, 125000, 5),
	('HD30', 'MA18', 1, 90000, 5),
	('HD30', 'MA22', 2, 260000, 5),
	('HD30', 'MA20', 1, 105000, 5),
	('HD31', 'MA21', 2, 190000, 5),
	('HD31', 'MA22', 1, 95000, 5),
	('HD31', 'MA23', 1, 110000, 5),
	('HD31', 'MA24', 2, 210000, 5),
	('HD31', 'MA25', 1, 40000, 5),
	('HD32', 'MA01', 2, 250000, 5),
	('HD32', 'MA02', 1, 135000, 5),
	('HD32', 'MA03', 1, 90000, 5),
	('HD32', 'MA04', 1, 95000, 5),
	('HD32', 'MA05', 1, 95000, 5),
	('HD33', 'MA06', 2, 240000, 5),
	('HD33', 'MA07', 1, 105000, 5),
	('HD33', 'MA08', 1, 115000, 5),
	('HD33', 'MA09', 1, 105000, 5),
	('HD33', 'MA10', 1, 135000, 5),
	('HD34', 'MA11', 2, 210000, 5),
	('HD34', 'MA12', 1, 125000, 5),
	('HD34', 'MA13', 1, 95000, 5),
	('HD34', 'MA14', 2, 180000, 5),
	('HD34', 'MA15', 1, 110000, 5),
	('HD35', 'MA16', 2, 180000, 5),
	('HD35', 'MA17', 1, 125000, 5),
	('HD35', 'MA18', 1, 90000, 5),
	('HD35', 'MA22', 2, 260000, 5),
	('HD35', 'MA20', 1, 105000, 5),
	('HD36', 'MA21', 2, 190000, 5),
	('HD36', 'MA22', 1, 95000, 5),
	('HD36', 'MA23', 1, 110000, 5),
	('HD36', 'MA24', 2, 210000, 5),
	('HD36', 'MA25', 1, 40000, 5),
	('HD37', 'MA01', 2, 250000, 5),
	('HD37', 'MA02', 1, 135000, 5),
	('HD37', 'MA03', 1, 90000, 5),
	('HD37', 'MA04', 2, 190000, 5),
	('HD37', 'MA05', 1, 95000, 5),
	('HD38', 'MA06', 2, 240000, 5),
	('HD38', 'MA07', 1, 105000, 5),
	('HD38', 'MA08', 2, 230000, 5),
	('HD38', 'MA09', 1, 105000, 5),
	('HD38', 'MA10', 1, 135000, 5),
	('HD39', 'MA11', 2, 210000, 5),
	('HD39', 'MA12', 1, 125000, 5),
	('HD39', 'MA13', 1, 95000, 5),
	('HD39', 'MA14', 2, 180000, 5),
	('HD39', 'MA15', 1, 110000, 5),
	('HD40', 'MA16', 2, 180000, 5),
	('HD40', 'MA17', 1, 125000, 5),
	('HD40', 'MA18', 1, 90000, 5),
	('HD40', 'MA22', 2, 260000, 5),
	('HD40', 'MA20', 1, 105000, 5),
	('HD41', 'MA21', 2, 190000, 5),
	('HD41', 'MA22', 1, 95000, 5),
	('HD41', 'MA23', 1, 110000, 5),
	('HD41', 'MA24', 2, 210000, 5),
	('HD41', 'MA25', 1, 40000, 5),
	('HD42', 'MA01', 2, 250000, 5),
	('HD42', 'MA02', 1, 135000, 5),
	('HD42', 'MA03', 1, 90000, 5),
	('HD42', 'MA04', 1, 95000, 5),
	('HD42', 'MA05', 1, 95000, 5),
	('HD43', 'MA06', 2, 240000, 5),
	('HD43', 'MA07', 1, 105000, 5),
	('HD43', 'MA08', 1, 115000, 5),
	('HD43', 'MA09', 1, 105000, 5),
	('HD43', 'MA10', 1, 135000, 5),
	('HD44', 'MA11', 2, 210000, 5),
	('HD44', 'MA12', 1, 125000, 5),
	('HD44', 'MA13', 1, 95000, 5),
	('HD44', 'MA14', 2, 180000, 5),
	('HD44', 'MA15', 1, 110000, 5),
	('HD45', 'MA16', 2, 180000, 5),
	('HD45', 'MA17', 1, 125000, 5),
	('HD45', 'MA18', 1, 90000, 5),
	('HD45', 'MA22', 2, 260000, 5),
	('HD45', 'MA20', 1, 105000, 5),
	('HD46', 'MA21', 2, 190000, 5),
	('HD46', 'MA22', 1, 95000, 5),
	('HD46', 'MA23', 1, 110000, 5),
	('HD46', 'MA24', 2, 210000, 5),
	('HD46', 'MA25', 1, 40000, 5),
	('HD47', 'MA01', 2, 250000, 5),
	('HD47', 'MA02', 1, 135000, 5),
	('HD47', 'MA03', 1, 90000, 5),
	('HD47', 'MA04', 1, 95000, 5),
	('HD47', 'MA05', 1, 95000, 5),
	('HD48', 'MA06', 2, 240000, 5),
	('HD48', 'MA07', 1, 105000, 5),
	('HD48', 'MA16', 2, 180000, 5),
	('HD48', 'MA17', 1, 125000, 5),
	('HD48', 'MA18', 1, 90000, 5),
	('HD48', 'MA22', 2, 260000, 5),
	('HD48', 'MA20', 1, 105000, 5),
	('HD49', 'MA21', 2, 190000, 5),
	('HD49', 'MA22', 1, 95000, 5),
	('HD49', 'MA23', 1, 110000, 5),
	('HD49', 'MA24', 2, 210000, 5),
	('HD49', 'MA25', 1, 40000, 5),
	('HD50', 'MA11', 2, 80000, 5),
	('HD50', 'MA27', 1, 40000, 5),
	('HD50', 'MA28', 1, 150000, 5),
	('HD50', 'MA29', 2, 250000, 5),
	('HD50', 'MA30', 1, 150000, 5),
	('HD51', 'MA01', 2, 250000, 5),
	('HD51', 'MA02', 1, 135000, 5),
	('HD51', 'MA03', 1, 90000, 5),
	('HD51', 'MA04', 1, 95000, 5),
	('HD51', 'MA05', 1, 95000, 5),
	('HD52', 'MA06', 2, 240000, 5),
	('HD52', 'MA07', 1, 105000, 5),
	('HD52', 'MA08', 1, 115000, 5),
	('HD52', 'MA09', 1, 105000, 5),
	('HD52', 'MA10', 1, 135000, 5),
	('HD53', 'MA11', 2, 210000, 5),
	('HD53', 'MA12', 1, 125000, 5),
	('HD53', 'MA13', 1, 95000, 5),
	('HD53', 'MA14', 2, 180000, 5),
	('HD53', 'MA15', 1, 110000, 5),
	('HD54', 'MA16', 2, 180000, 5),
	('HD54', 'MA17', 1, 125000, 5),
	('HD54', 'MA18', 1, 90000, 5),
	('HD54', 'MA12', 2, 260000, 5),
	('HD54', 'MA20', 1, 105000, 5);
go

	
	-- Thêm chi tiết hóa đơn cho các hóa đơn tháng 1
insert into ChiTietHD values
    ('HD62', 'MA01', 1, 125000, 5),
    ('HD62', 'MA02', 1, 135000, 5),
    ('HD62', 'MA03', 2, 180000, 5),
    ('HD62', 'MA04', 2, 190000, 5),
    ('HD62', 'MA05', 1, 95000, 5),
    ('HD63', 'MA06', 2, 240000, 5),
    ('HD63', 'MA07', 1, 105000, 5),
    ('HD63', 'MA08', 2, 230000, 5),
    ('HD63', 'MA09', 1, 105000, 5),
    ('HD63', 'MA10', 1, 135000, 5),
    ('HD64', 'MA11', 2, 210000, 5),
    ('HD64', 'MA12', 1, 125000, 5),
    ('HD64', 'MA13', 1, 95000, 5),
    ('HD64', 'MA14', 2, 180000, 5),
    ('HD64', 'MA15', 1, 110000, 5),
    ('HD65', 'MA16', 2, 180000, 5),
    ('HD65', 'MA17', 1, 125000, 5),
    ('HD65', 'MA18', 1, 90000, 5),
    ('HD65', 'MA22', 2, 260000, 5),
    ('HD65', 'MA20', 1, 105000, 5),
    ('HD66', 'MA21', 2, 190000, 5),
    ('HD66', 'MA22', 1, 95000, 5),
    ('HD66', 'MA23', 1, 110000, 5),
    ('HD66', 'MA24', 2, 210000, 5),
    ('HD66', 'MA25', 1, 40000, 5);
go

-- Thêm chi tiết hóa đơn tháng 12
insert into ChiTietHD values
    ('HD67', 'MA01', 1, 125000, 5),
    ('HD67', 'MA02', 1, 135000, 5),
    ('HD67', 'MA03', 2, 180000, 5),
    ('HD67', 'MA04', 2, 190000, 5),
    ('HD67', 'MA05', 1, 95000, 5),
    ('HD68', 'MA06', 2, 240000, 5),
    ('HD68', 'MA07', 1, 105000, 5),
    ('HD68', 'MA08', 2, 230000, 5),
    ('HD68', 'MA09', 1, 105000, 5),
    ('HD68', 'MA10', 1, 135000, 5),
    ('HD69', 'MA11', 2, 210000, 5),
    ('HD69', 'MA12', 1, 125000, 5),
    ('HD69', 'MA13', 1, 95000, 5),
    ('HD69', 'MA14', 2, 180000, 5),
    ('HD69', 'MA15', 1, 110000, 5),
    ('HD70', 'MA16', 2, 180000, 5),
    ('HD70', 'MA17', 1, 125000, 5),
    ('HD70', 'MA18', 1, 90000, 5),
    ('HD70', 'MA22', 2, 260000, 5),
    ('HD70', 'MA20', 1, 105000, 5),
    ('HD71', 'MA21', 2, 190000, 5),
    ('HD71', 'MA22', 1, 95000, 5),
    ('HD71', 'MA23', 1, 110000, 5),
    ('HD71', 'MA24', 2, 210000, 5),
    ('HD71', 'MA25', 1, 40000, 5);
	--2/2024
go


insert into HoaDon values 
    ('HD72', '2024-02-01', 500000, 0, 0, 525000, 'T1B06', NULL, 'NV01'),
    ('HD73', '2024-02-02', 600000, 0, 0, 630000, 'T1B06', NULL, 'NV01'),
    ('HD74', '2024-02-03', 700000,  0, 0, 735000, 'T1B06', NULL, 'NV01'),
    ('HD75', '2024-02-04', 800000,  0, 0, 840000, 'T1B06', NULL, 'NV01'),
    ('HD76', '2024-02-05', 900000,  0, 0, 945000, 'T1B06', NULL, 'NV01'),
    ('HD77', '2024-03-01', 1000000, 0, 0, 1050000, 'T1B06', NULL, 'NV01'),
    ('HD78', '2024-03-02', 1100000, 0, 0, 1155000, 'T1B06', NULL, 'NV01'),
    ('HD79', '2024-03-03', 1200000, 0, 0, 1260000, 'T1B06', NULL, 'NV01'),
    ('HD80', '2024-03-04', 1300000, 0, 0, 1365000, 'T1B06', NULL, 'NV01'),
    ('HD81', '2024-03-05', 1400000, 0, 0, 1470000, 'T1B06', NULL, 'NV01'),
    ('HD82', '2024-04-01', 1500000, 0, 0, 1575000, 'T1B06', NULL, 'NV01'),
    ('HD83', '2024-04-02', 1600000, 0, 0, 1680000, 'T1B06', NULL, 'NV01'),
    ('HD84', '2024-04-03', 1700000, 0, 0, 1785000, 'T1B06', NULL, 'NV01'),
    ('HD85', '2024-04-04', 1800000, 0, 0, 1890000, 'T1B06', NULL, 'NV01'),
    ('HD86', '2024-04-05', 1900000, 0, 0, 1995000, 'T1B06', NULL, 'NV01'),
    ('HD87', '2024-05-01', 2000000, 0, 0, 2100000, 'T1B06', NULL, 'NV01'),
    ('HD88', '2024-05-02', 2100000, 0, 0, 2205000, 'T1B06', NULL, 'NV01'),
    ('HD89', '2024-05-03', 2200000, 0, 0, 2310000, 'T1B06', NULL, 'NV01'),
    ('HD90', '2024-05-04', 2300000, 0, 0, 2415000, 'T1B06', NULL, 'NV01'),
    ('HD91', '2024-05-05', 2400000, 0, 0, 2520000, 'T1B06', NULL, 'NV01');
	-- cthd 2 / 2024
go

insert into ChiTietHD values 
    ('HD72', 'MA06', 1, 200000, 5),
    ('HD73', 'MA07', 2, 210000, 4),
    ('HD74', 'MA08', 1, 220000, 3),
    ('HD75', 'MA09', 3, 230000, 2),
    ('HD76', 'MA10', 2, 240000, 1),
    ('HD77', 'MA11', 1, 250000, 5),
    ('HD78', 'MA12', 2, 260000, 4),
    ('HD79', 'MA13', 1, 270000, 3),
    ('HD80', 'MA14', 3, 280000, 2),
    ('HD81', 'MA15', 2, 290000, 1),
    ('HD82', 'MA16', 1, 300000, 5),
    ('HD83', 'MA17', 2, 310000, 4),
    ('HD84', 'MA18', 1, 320000, 3),
    ('HD85', 'MA11', 3, 330000, 2),
    ('HD86', 'MA20', 2, 340000, 1),
    ('HD87', 'MA21', 1, 350000, 5),
    ('HD88', 'MA22', 2, 360000, 4),
    ('HD89', 'MA23', 1, 370000, 3),
    ('HD90', 'MA24', 3, 380000, 2),
    ('HD91', 'MA25', 2, 390000, 1),
	('HD88', 'MA01', 1, 125000, 5),
    ('HD77', 'MA02', 2, 150000, 4),
    ('HD72', 'MA03', 1, 200000, 3),
    ('HD90', 'MA04', 3, 175000, 2),
    ('HD91', 'MA05', 2, 180000, 1);
GO
	 
/*
	đây là những proc 

	mn chú ý 
*/
-- thong ke mon an
create or alter proc Sp_ThongKeMonAn
as 
begin 
	select top 7 TenMonAn ,sum(cthd.soluong) as SoLuongDaBan
	from MonAn ma
	join ChiTietHD cthd on cthd.MaMonAn = ma.MaMonAn
	group by TenMonAn
	order by SoLuongDaBan desc	
end 
go

-- thong ke doanh thu
CREATE PROCEDURE SP_DoanhThuThang (@nam int)
AS
BEGIN
    SELECT 
        MONTH(hd.NgayLap) AS Thang,
        SUM(cthd.ThanhTien) AS TongThanhTien
    FROM 
        HoaDon hd
    JOIN 
        ChiTietHD cthd ON cthd.MaHoaDon = hd.MaHoaDon
    WHERE 
        YEAR(hd.NgayLap) = @nam
    GROUP BY 
        MONTH(hd.NgayLap);
END
go

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

create trigger Tr_ThemTenTK 
on nhanvien
after insert
	AS
	begin
		insert into TaiKhoan
		values
		()
	end
go
select * from TaiKhoan
 -- lay nam cua ngay lap hoa don
 SELECT DISTINCT year(NgayLap) Year FROM hoadon ORDER BY Year DESC

 -- lay chuoi cua so tang trong bang ban
 select distinct SUBSTRING(vitri,1,CHARINDEX(',',vitri)-1) from Ban
go

insert into ChiTietDB values
	('T1B01', '1-03-2024', 1),
	('T1B02', '1-04-2024', 3),
	('T1B09', '1-24-2024', 7),
	('T1B12', '1-25-2024', 6),
	('T1B07', '1-26-2024', 4);
go