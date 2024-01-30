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
exec Sp_ThongKeMonAn

exec SP_DoanhThuThang 2024
go
-- thong ke doanh thu
CREATE OR ALTER PROCEDURE SP_DoanhThuThang (@nam int)
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
        YEAR(hd.NgayLap) = 2023
    GROUP BY 
        MONTH(hd.NgayLap);
END

 -- lay nam cua ngay lap hoa don
 SELECT DISTINCT year(NgayLap) Year FROM hoadon ORDER BY Year DESC

 -- lay chuoi cua so tang trong bang ban
 select distinct SUBSTRING(vitri,1,CHARINDEX(',',vitri)-1) from Ban

 select * from ban
 update Ban set TrangThai = N'Đang phục vụ' where MaBan = 'T2B11'
 update Ban set TrangThai = N'Đang phục vụ' where MaBan = 'T1B12'
 update Ban set TrangThai = N'Đã đặt' where MaBan = 'T2B01'
 update Ban set TrangThai = N'Đang hoạt động' where MaBan = 'T2B03'
 update Ban set TrangThai = N'Đã đặt' where MaBan = 'T1B03'
 update Ban set TrangThai = N'Đang phục vụ' where MaBan = 'T2B07'
 update Ban set TrangThai = N'Đang bảo trì' where MaBan = 'T2B12'

 select * from KhachHang

 select * from HoaDon order by NgayLap desc