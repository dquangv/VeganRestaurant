create database NhaHangChay_CohesiveStars;
go

use NhaHangChay_CohesiveStars;
go

create table TaiKhoan (
	TenTaiKhoan varchar(20) primary key,
	MatKhau varchar(50) not null,
	VaiTro bit not null,
	TrangThai nvarchar(20) not null);
go

create table NhanVien (
	MaNhanVien varchar(10) primary key,
	TenNhanVien nvarchar(30) not null,
	ChucVu bit not null,
	TrangThai nvarchar(20) not null,
	GioiTinh bit not null,
	SDT varchar(15) not null,
	Email varchar(50),
	Luong money not null,
	TenTaiKhoan varchar(20));
go

create table KhachHang (
	MaKhachHang varchar(10) primary key,
	TenKhachHang nvarchar(30) not null,
	SDT varchar(15) not null,
	NgayDkThanhVien date,
	DiemThuong float);
go

create table DanhGia (
	MaDanhGia varchar(10) primary key,
	CauHoi1 int, 
	CauHoi2 int,
	CauHoi3 int,
	DanhGia nvarchar(300));
go

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
	DonGia money not null);
go

create table ThucDon (
	MaThucDon varchar(10) primary key,
	TenThucDon nvarchar(50) not null,
	DonGia money not null,
	NgayPhucVu nvarchar(100) not null,
	HinhAnh varchar(500),
	TrangThai nvarchar(30) not null);
go

create table MonAn (
	MaMonAn varchar(10) primary key,
	TenMonAn nvarchar(50) not null,
	DonGia money not null,
	LoaiMonAn nvarchar(50),
	HinhAnh varchar(500),
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
	SoLuong int not null);
go

create table HoaDon (
	MaHoaDon varchar(10) primary key,
	NgayLap datetime not null default getdate(),
	TienMonAn money not null,
	TienPhatSinh money default 0,
	TienGiamDiemThuong money default 0,
	TienGiamKhuyenMai money default 0,
	TongTien money not null,
	TrangThai nvarchar(30) not null,
	MaKhachHang varchar(10) not null,
	MaBan varchar(10) not null,
	MaKhuyenMai varchar(10),
	MaNhanVien varchar(10) not null,
	MaDanhGia varchar(10));
go

create table ChiTietHD (
	MaHoaDon varchar(10) not null,
	MaMonAn varchar(10) not null,
	SoLuong int not null,
	ThanhTien money not null,
	primary key (MaHoaDon, MaMonAn));
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
	constraint fk_hd_kh
	foreign key (MaKhachHang)
	references KhachHang(MaKhachHang),

	constraint fk_hd_b
	foreign key (MaBan)
	references Ban(MaBan),

	constraint fk_hd_km
	foreign key (MaKhuyenMai)
	references KhuyenMai(MaKhuyenMai),

	constraint fk_hd_nv
	foreign key (MaNhanVien)
	references NhanVien(MaNhanVien),

	constraint fk_hd_dg
	foreign key (MaDanhGia)
	references DanhGia(MaDanhGia);
go

insert into TaiKhoan values
	('LyNDT', '123', 0, N'Hoạt động'),
	('TungVT', '123', 0, N'Hoạt động'),
	('QuangVD', '123', 1, N'Đã nghỉ'),
	('ChuongVH', '123', 1, N'Hoạt động'),
	('QuangBM', '123', 1, N'Hoạt động'),
	('RonPN', '123', 1, N'Hoạt động');
go

insert into NhanVien values
	('NV01', N'Nguyễn Dương Thiên Lý', 1, N'Hoạt động', 1, '0101010101', 'lyndt@fpt.edu.vn', 4500, 'LyNDT'),
	('NV02', N'Võ Thanh Tùng', 1, N'Hoạt động', 0, '0202020202', 'tungvt@fpt.edu.vn', 5000, 'TungVT'),
	('NV03', N'Vũ Đăng Quang', 0, N'Nghỉ', 0, '0303030303', 'quangvd@fpt.edu.vn', 3000, 'QuangVD'),
	('NV04', N'Vũ Hoàng Chương', 0, N'Hoạt động', 0, '0404040404', 'chuongvh@fpt.edu.vn', 3500, 'ChuongVH'),
	('NV05', N'Bùi Minh Quang', 0, N'Hoạt động', 0, '0505050505', 'quangbm@fpt.edu.vn', 4000, 'QuangBM'),
	('NV06', N'Phạm Ngọc Rôn', 0, N'Hoạt động', 0, '0606060606', 'ronpn@fpt.edu.vn', 3500, 'ronpn');
go

insert into DanhGia values
	('DG01', 3, 1, 5, null),
	('DG02', 4, 5, 5, N'Quá tuyệt vời'),
	('DG03', 5, 5, 5, N'Sẽ tiếp tục ủng hộ'),
	('DG04', 1, 1, 1, null),
	('DG05', 4, 5, 4, N'Đồ ăn ngon, không gian đẹp, nhân viên dễ thương');
go

insert into KhuyenMai values
	('KM01', N'Mừng khai trương', 30, 200, N'Ngày lễ', '01-02-2024', '01-09-2024'),
	('KM02', N'Bán vì đam mê', 20, 300, N'Quảng bá', '01-18-2024', '01-31-2024'),
	('KM03', N'Mừng thôi nôi', 10, 500, N'Quảng bá', '02-02-2024', '02-09-2024');
go

insert into KhachHang values
	('KH01', N'Nghĩa', '1010101010', null, null),
	('KH02', N'Triều', '2020202020', '01-02-2024', 0.3),
	('KH03', N'Khánh', '3030303030', null, null),
	('KH04', N'Đủ', '4040404040', '01-18-2024', 0.1),
	('KH05', N'Cường', '5050505050', '01-20-2024', 0.5),
	('KH06', N'Thạch', '6060606060', '01-02-2024', 1),
	('KH07', N'Quân', '7070707070', '01-02-2024', 1.5),
	('KH08', N'Long', '8080808080', '01-10-2024', 0.3),
	('KH09', N'Minh', '9090909090', null, null),
	('KH10', N'Khoa', '1111111111', '01-02-2024', 0.8);
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
	('NVL01', N'Trứng', 500, N'Trái', 100),
	('NVL02', N'Chả', 30, N'Kg', 500),
	('NVL03', N'Đậu hũ', 1000, N'Miếng', 100),
	('NVL04', N'Nấm đông cô', 30, N'Kg', 300),
	('NVL05', N'Mướp', 100, N'Trái', 250),
	('NVL06', N'Đu đủ', 25, N'Trái', 200),
	('NVL07', N'Khổ qua', 25, N'Trái', 150),
	('NVL08', N'Mỳ ống', 200, N'Bịch', 350),
	('NVL09', N'Khế', 25, N'Trái', 170),
	('NVL10', N'Rau tần ô', 15, N'Bó', 230);
go

insert into MonAn values
	('MA01', N'Salad mít non', 125000, N'Khai vị', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\xa-lach-mit-non-1.png', N'Hoạt động'),
	('MA02', N'Mozzarella Salad', 135000, N'Khai vị', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\mozzarella-salad-1.png', N'Hoạt động'),
	('MA03', N'Gỏi cuốn rau củ', 90000, N'Khai vị', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\goi-cuon-rau-cu-1.png', N'Hoạt động'),
	('MA04', N'Chả giò', 95000, N'Khai vị', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\cha-gio-3.png', N'Hoạt động'),
	('MA05', N'Há cảo Nhật - Gyoza', 95000, N'Khai vị', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\ha-cao-nhat-1.png', N'Hoạt động'),
	('MA06', N'Chả nấm mối', 120000, N'Khai vị', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\cha-nam-moi-1.png', N'Hoạt động'),
	('MA07', N'Bún nưa trộn', 105000, N'Khai vị', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\bun-nua-tron-1.png', N'Hoạt động'),
	('MA08', N'Gỏi củ hủ dừa', 115000, N'Khai vị', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\goi-cu-hu-dua-2.png', N'Hoạt động'),
	('MA09', N'Gỏi đu đủ', 105000, N'Khai vị', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\goi-du-du-1.webp', N'Hoạt động'),
	('MA10', N'Salad Sung', 135000, N'Khai vị', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\salad-sung-1.webp', N'Hoạt động'),
	('MA11', N'Bánh tart artiso', 105000, N'Khai vị', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\salad-sung-1.webp', N'Ngừng phục vụ'),
	('MA12', N'Đậu hủ bó xôi sốt trứng muối', 125000, N'Món chính', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\dau-hu-bo-xoi-1.webp', N'Hoạt động'),
	('MA13', N'Nấm đông cô sốt tiêu', 95000, N'Món chính', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\nam-dong-co-1.webp', N'Hoạt động'),
	('MA14', N'Đậu rồng xào tỏi đen', 90000, N'Món chính', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\dau-rong-xao-1.webp', N'Hoạt động'),
	('MA15', N'Tàu hủ ky sốt chao', 110000, N'Món chính', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\tau-ku-ky-1.webp', N'Hoạt động'),
	('MA16', N'Mướp xào', 90000, N'Món chính', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\muop-xao-1.webp', N'Hoạt động'),
	('MA17', N'Nấm mối xào lá lốt', 125000, N'Món chính', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\nam-moi-xao-1.webp', N'Hoạt động'),
	('MA18', N'Đu đủ xào', 90000, N'Món chính', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\du-du-xao-1.webp', N'Hoạt động'),
	('MA20', N'Măng xào củ kiệu', 105000, N'Món chính', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\mang-xao-cu-kieu-1.webp', N'Hoạt động'),
	('MA21', N'Khổ qua kho ngũ vị', 95000, N'Món chính', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\kho-qua-kho-1.webp', N'Hoạt động'),
	('MA22', N'Đậu hủ kim chi', 95000, N'Món chính', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\dau-ku-kim-chi-1.webp', N'Hoạt động'),
	('MA23', N'Rau củ om Thái', 110000, N'Món chính', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\rau-cu-om-thai-1.webp', N'Hoạt động'),
	('MA24', N'Nấm kho', 105000, N'Món chính', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\nam-kho-2.webp', N'Ngừng phục vụ'),
	('MA25', N'Cơm nếp than', 40000, N'Cơm & Mì', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\com-cac-loai.webp', N'Hoạt động'),
	('MA26', N'Cơm trắng', 40000, N'Cơm & Mì', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\com-cac-loai.webp', N'Hoạt động'),
	('MA27', N'Cơm bó xôi hạt sen', 40000, N'Cơm & Mì', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\com-cac-loai.webp', N'Hoạt động'),
	('MA28', N'Mì Ý sốt rau củ', 150000, N'Cơm & Mì', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\mi-y-sot-rau-cu-1.webp', N'Hoạt động'),
	('MA29', N'Bún nưa xào', 125000, N'Cơm & Mì', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\bun-nua-xao-1.webp', N'Hoạt động'),
	('MA30', N'Mì sốt kem nấm', 150000, N'Cơm & Mì', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\mi-sot-kem-nam-2.webp', N'Hoạt động'),
	('MA31', N'Cơm cà ri', 125000, N'Cơm & Mì', 'C:\Users\Quang\OneDrive - FPT Polytechnic\Desktop\fpl\hk4\nhapmonkythuatphanmem\officical\asm\Hinh Anh\com-cari-1.png', N'Hoạt động');
go


--insert into ThucDon values
--	(
