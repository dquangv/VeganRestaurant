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
CREATE proc SP_DoanhThuThang (@nam int)
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
GO


 -- lay nam cua ngay lap hoa don
 SELECT DISTINCT year(NgayLap) Year FROM hoadon ORDER BY Year DESC

 -- lay chuoi cua so tang trong bang ban
 select distinct SUBSTRING(vitri,1,CHARINDEX(',',vitri)-1) from Ban
 go
 -- lay thong tin khach hay 
 create proc SP_ThongKhachHangDatBan (@maBan int)
 as 
 begin
	select TenKhachHang,sdt,ThoiGianDat from ChiTietDatBan ctdb
join KhachHang kh on kh.MaKhachHang = ctdb.MaKhachHang
where MaBan = @maBan
 end
