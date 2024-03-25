delete from ChiTietGoiMon;
go
delete from ChiTietTD;
go
delete from MonAn;
go
go
DBCC CHECKIDENT ('MonAn', RESEED, 0);
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
values
(1,1),
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
(1,30)
go