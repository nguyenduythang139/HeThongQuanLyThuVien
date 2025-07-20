-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: thuvien
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chitietphieumuon`
--

DROP TABLE IF EXISTS `chitietphieumuon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitietphieumuon` (
  `MaPhieuMuon` int NOT NULL,
  `MaSach` int NOT NULL,
  `SoLuong` int DEFAULT '1',
  PRIMARY KEY (`MaPhieuMuon`,`MaSach`),
  KEY `MaSach` (`MaSach`),
  CONSTRAINT `chitietphieumuon_ibfk_1` FOREIGN KEY (`MaPhieuMuon`) REFERENCES `phieumuon` (`MaPhieuMuon`) ON DELETE CASCADE,
  CONSTRAINT `chitietphieumuon_ibfk_2` FOREIGN KEY (`MaSach`) REFERENCES `sach` (`MaSach`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitietphieumuon`
--

LOCK TABLES `chitietphieumuon` WRITE;
/*!40000 ALTER TABLE `chitietphieumuon` DISABLE KEYS */;
INSERT INTO `chitietphieumuon` VALUES (1,2,1),(2,2,1),(2,3,1),(2,4,1);
/*!40000 ALTER TABLE `chitietphieumuon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `docgia`
--

DROP TABLE IF EXISTS `docgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `docgia` (
  `MaDocGia` int NOT NULL AUTO_INCREMENT,
  `HoTen` varchar(100) NOT NULL,
  `GioiTinh` enum('Nam','Nữ','Khác') NOT NULL,
  `NgaySinh` date DEFAULT NULL,
  `CCCD` varchar(20) DEFAULT NULL,
  `SDT` varchar(15) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `DiaChi` varchar(255) DEFAULT NULL,
  `TrangThai` enum('Hoạt động','Bị khóa') DEFAULT 'Hoạt động',
  PRIMARY KEY (`MaDocGia`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docgia`
--

LOCK TABLES `docgia` WRITE;
/*!40000 ALTER TABLE `docgia` DISABLE KEYS */;
INSERT INTO `docgia` VALUES (1,'Nguyen Van A','Nam','2004-09-13','1231231231','022334455666','nguyenvana@gmail.com','312 Quang Trung, Gò Vấp, TPHCM','Hoạt động'),(3,'Nguyen Van B','Nam','2004-09-08','1321321231','0336971705','nguyenvanb@gmail.com','312 Phan Văn Trị, Gò Vấp, TPHCM','Hoạt động');
/*!40000 ALTER TABLE `docgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieumuon`
--

DROP TABLE IF EXISTS `phieumuon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieumuon` (
  `MaPhieuMuon` int NOT NULL AUTO_INCREMENT,
  `MaDocGia` int NOT NULL,
  `NgayMuon` date NOT NULL,
  `HanTra` date NOT NULL,
  `NgayTraThucTe` date DEFAULT NULL,
  `TrangThai` enum('Đang mượn','Đã trả') DEFAULT 'Đang mượn',
  `GhiChu` text,
  PRIMARY KEY (`MaPhieuMuon`),
  KEY `MaDocGia` (`MaDocGia`),
  CONSTRAINT `phieumuon_ibfk_1` FOREIGN KEY (`MaDocGia`) REFERENCES `docgia` (`MaDocGia`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieumuon`
--

LOCK TABLES `phieumuon` WRITE;
/*!40000 ALTER TABLE `phieumuon` DISABLE KEYS */;
INSERT INTO `phieumuon` VALUES (1,1,'2025-07-20','2025-07-22',NULL,'Đang mượn','abc'),(2,3,'2025-07-20','2025-07-23',NULL,'Đang mượn','abc');
/*!40000 ALTER TABLE `phieumuon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieuphat`
--

DROP TABLE IF EXISTS `phieuphat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieuphat` (
  `MaPhieuPhat` int NOT NULL AUTO_INCREMENT,
  `MaPhieuMuon` int DEFAULT NULL,
  `NgayPhat` datetime DEFAULT CURRENT_TIMESTAMP,
  `SoNgayTre` int DEFAULT '0',
  `TienPhatTre` int DEFAULT '0',
  `SoSachHong` int DEFAULT '0',
  `TienPhatHong` int DEFAULT '0',
  `TongTienPhat` int DEFAULT '0',
  PRIMARY KEY (`MaPhieuPhat`),
  KEY `MaPhieuMuon` (`MaPhieuMuon`),
  CONSTRAINT `phieuphat_ibfk_1` FOREIGN KEY (`MaPhieuMuon`) REFERENCES `phieumuon` (`MaPhieuMuon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieuphat`
--

LOCK TABLES `phieuphat` WRITE;
/*!40000 ALTER TABLE `phieuphat` DISABLE KEYS */;
/*!40000 ALTER TABLE `phieuphat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sach`
--

DROP TABLE IF EXISTS `sach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sach` (
  `MaSach` int NOT NULL AUTO_INCREMENT,
  `TenSach` varchar(255) NOT NULL,
  `TacGia` varchar(100) DEFAULT NULL,
  `NhaXuatBan` varchar(100) DEFAULT NULL,
  `TheLoai` varchar(50) DEFAULT NULL,
  `NgayXuatBan` date DEFAULT NULL,
  `SoLuong` int DEFAULT '0',
  `NgonNgu` varchar(50) DEFAULT NULL,
  `TinhTrang` enum('Còn','Hết') DEFAULT 'Còn',
  `ViTriLuuTru` varchar(100) DEFAULT NULL,
  `HinhAnh` text,
  PRIMARY KEY (`MaSach`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sach`
--

LOCK TABLES `sach` WRITE;
/*!40000 ALTER TABLE `sach` DISABLE KEYS */;
INSERT INTO `sach` VALUES (2,'abcd','abc','abc','Giao Trinh','2025-07-10',-1,'Tieng Viet','Còn','1',NULL),(3,'sách 2','abc','abc','Giao Trinh','2025-07-10',0,'Tieng Viet','Còn','1',NULL),(4,'sách 3','abc','abc','Giao Trinh','2025-07-10',0,'Tieng Viet','Còn','1',NULL),(5,'sách 4','abc','abc','Giao Trinh','2025-07-10',1,'Tieng Viet','Còn','1',NULL);
/*!40000 ALTER TABLE `sach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taikhoan` (
  `MaTK` int NOT NULL AUTO_INCREMENT,
  `TenDangNhap` varchar(50) NOT NULL,
  `MatKhau` varchar(100) NOT NULL,
  `VaiTro` enum('Admin','ThuThu') DEFAULT 'ThuThu',
  `Email` varchar(50) DEFAULT NULL,
  `NgaySinh` date DEFAULT NULL,
  `GioiTinh` enum('Nam','Nữ') DEFAULT NULL,
  `TrangThai` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`MaTK`),
  UNIQUE KEY `TenDangNhap` (`TenDangNhap`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
INSERT INTO `taikhoan` VALUES (1,'nguyenvana','12345678','ThuThu','nguyenvana@gmail.com','2004-09-13','Nam',1),(3,'nguyenvanb','12345678','ThuThu','nguyenvanb@gmail.com','2005-10-01','Nữ',1),(5,'nguyenvanc','12345678','ThuThu','nguyenvanc@gmail.com','2025-07-01','Nam',1),(6,'nguyenvand','12345678','ThuThu','nguyenvand@gmail.com','2019-05-09','Nữ',1),(7,'nguyenvane','12345678','ThuThu','nguyenvane@gmail.com','2016-06-29','Nam',1),(8,'nguyenduyf','12345678','ThuThu','nguyenduyf@gmail.com','2022-07-07','Nam',1),(9,'admin','123456','Admin','nguyenduythang1392004@gmail.com','2004-09-13','Nam',1);
/*!40000 ALTER TABLE `taikhoan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-20 17:31:12
