-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lms
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `bookTitle` varchar(100) NOT NULL,
  `author` varchar(100) NOT NULL,
  `bookType` varchar(100) NOT NULL,
  `image` varchar(500) NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES ('Đồng Minh của Nhân Loại TẬP MỘT (Allies of Humanity, Book One - Vietnamese)','Marshall Vian Summers','Body, Mind & Spirit','http://books.google.com/books/content?id=w1LbDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2020-04-03'),('I Conan','L. J. LaRoch','Fiction','http://books.google.com/books/content?id=M__SEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2023-04-12'),('The Adventures of Freddie the Frog and Mixi the Mouse','Lesley Day','Juvenile Fiction','http://books.google.com/books/content?id=eYlOAAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2011-11-16'),('Pew','Catherine Lacey','Fiction','http://books.google.com/books/content?id=e_-wDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2020-07-21'),('Five Stars','Carmine Gallo','Business & Economics','http://books.google.com/books/content?id=XXM-DwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2018-06-05'),('The Jolliest Bunch','Danny Pellegrino','Humor','http://books.google.com/books/content?id=hTCzEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2023-10-24'),('Truyen ngan - Doi anh o Toronto','Nguyen Thu Hoai','Family & Relationships','http://books.google.com/books/content?id=YxPnCgAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2015-11-04'),('Giải Bài Tập SGK Toán Lớp 6 (Tập 1) Cánh Diều','LAM HUYNH ','Education','http://books.google.com/books/content?id=Fv_aEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2023-10-06'),('Giải Sách Bài Tập Toán 9 Chân Trời Sáng Tạo (Tập 2)','LAM HUYNH','Education','http://books.google.com/books/content?id=J1MoEQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2024-10-10'),('Giải Vở Bài Tập Toán Lớp 3 - Chân Trời Sáng Tạo (Tập 1)','LAM HUYNH','Education','http://books.google.com/books/content?id=WV2_EAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2023-05-19'),('Giải Vở Bài Tập Toán Lớp 4 Chân Trời Sáng Tạo (Tập 1)','LAM HUYNH','Education','http://books.google.com/books/content?id=5LLaEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2023-10-04'),('Phân Số- Các Tính Chất Và Phép Toán - Chương 5- Chân Trời Sáng Tạo','Nguyễn Quốc Tuấn','Education','http://books.google.com/books/content?id=9OJfEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2022-02-18'),('Phân Số- Các Tính Chất Và Phép Toán - Chương 6- Kết Nối Tri Thức Với Cuộc Sống','Nguyễn Quốc Tuấn','Education','http://books.google.com/books/content?id=SNCaEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2022-11-08'),('Giải Tiếng Anh 7 Global Success (Sách Học Sinh) Tập 1','LAM HUYNH','Education','http://books.google.com/books/content?id=Gm0NEQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2024-06-10'),('Giải Bài Tập SGK Toán Lớp 8 (Tập 1) - Kết Nối Tri Thức','LAM HUYNH','Education','http://books.google.com/books/content?id=u57eEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2023-10-25'),('Giải Tiếng Anh 9 Global Success (Tập 1)','LAM HUYNH','Education','http://books.google.com/books/content?id=tOAkEQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2024-09-28'),('B_ gi_i ngh_a Tân __c c_a Tyndale: Gia-c_','Douglas J. Moo','Religion','http://books.google.com/books/content?id=0fiuDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2019-07-25'),('Sách luyện thi Hội thi Tin học trẻ với Python bảng B cấp THCS','VIETSTEM','Education','http://books.google.com/books/content?id=O99WEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2021-12-29'),('BONG TRE','Van Hoc Moi','Religion','http://books.google.com/books/content?id=t0GwDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2019-07-15'),('Pathways of the Craftsmen, vol. 2  More Light and Travels','William Thomas','Self-Help','http://books.google.com/books/content?id=GUgxDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2017-07-30'),('Sherlock Holmes, Volume 3','Arthur Conan Doyle','Fiction','http://books.google.com/books/content?id=e9gsDQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2016-02-15'),('Cuoc Chien Ho Den','Dong Yen','Reference','http://books.google.com/books/content?id=hmWPDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2019-03-23'),('Big Data','Fei Hu','Computers','http://books.google.com/books/content?id=oewbDAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2016-04-27'),('Fuck','Christopher Fairman','Law','http://books.google.com/books/content?id=cB6E9Ii--_sC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2009-09-01'),('Fuck','Christopher Fairman','Law','http://books.google.com/books/content?id=cB6E9Ii--_sC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2009-09-01'),('Tenements, Towers & Trash','Julia Wertz','Comics & Graphic Novels','http://books.google.com/books/content?id=DxkUDgAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2017-10-03'),('Organizational Fit','Amy L. Kristof-Brown','Psychology','http://books.google.com/books/content?id=hgxrT2zXaSsC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2012-11-15'),('Digital Forensics and Watermarking','Christian Kraetzer','Computers','http://books.google.com/books/content?id=B0UwDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2017-08-09'),('Fit 2 Finish','Wendy LeBolt','Medical','http://books.google.com/books/content?id=UBIiBAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2015-01-13'),('Time Series Analysis','Rifaat Abdalla','Computers','http://books.google.com/books/content?id=LnK-EAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2023-01-18'),('Logistic Regression Models','Joseph M. Hilbe','Mathematics','http://books.google.com/books/content?id=tmHMBQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2009-05-11'),('Time Series Analysis','Rifaat Abdalla','Computers','http://books.google.com/books/content?id=LnK-EAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2023-01-18'),('Next generation staging in head and neck cancers','N. Gopalakrishna Iyer','Medical','http://books.google.com/books/content?id=oAmoEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2023-01-18'),('Fat 2 Fab','Tobi Beck','Family & Relationships','http://books.google.com/books/content?id=wpLRAwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2013-01-27'),('Fen','Julie Ankerson','Fiction','http://books.google.com/books/content?id=eoJyAgAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2010-10-23'),('Predictive, Prognostic Biomarkers and Therapeutic Targets in Breast Cancer','Zhi-Ming Shao','Medical','http://books.google.com/books/content?id=HVSMEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2022-09-28'),('Nikon DF','Jon Sparks','Photography','http://books.google.com/books/content?id=99xLDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2014-08-20'),('Nikon DF','Jon Sparks','Photography','http://books.google.com/books/content?id=99xLDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2014-08-20'),('Smooth Manifolds','Rajnikant Sinha','Mathematics','http://books.google.com/books/content?id=BH5qBQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2014-11-15'),('Smooth Manifolds','Rajnikant Sinha','Mathematics','http://books.google.com/books/content?id=BH5qBQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2014-11-15'),('Oracle ADF Survival Guide','Sten Vesterli','Computers','http://books.google.com/books/content?id=Dhk0DwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2017-09-04'),('Web Systems Design and Online Consumer Behavior','Yuan Gao','Business & Economics','http://books.google.com/books/content?id=J79sIVeP8M8C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2005-01-01'),('Network Warrior','Gary Donahue','Computers','http://books.google.com/books/content?id=6CqQgz8r4GsC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2011-05-20'),('Decentralizing the Online Experience With Web3 Technologies','Darwish, Dina','Computers','http://books.google.com/books/content?id=j8j8EAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2024-03-18'),('Mastering Windows Server 2022','Jordan Krause','Computers','http://books.google.com/books/content?id=w8nAEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2023-05-26'),('The Metaweb','Bridgit DAO','Computers','http://books.google.com/books/content?id=eqMIEQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2023-10-09');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-29  0:00:15
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lms
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `save`
--

DROP TABLE IF EXISTS `save`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `save` (
  `studentNumber` varchar(100) NOT NULL,
  `bookTitle` varchar(100) NOT NULL,
  `author` varchar(100) NOT NULL,
  `bookType` varchar(100) NOT NULL,
  `image` varchar(500) DEFAULT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `save`
--

LOCK TABLES `save` WRITE;
/*!40000 ALTER TABLE `save` DISABLE KEYS */;
INSERT INTO `save` VALUES ('23021745','Giải Vở Bài Tập Toán Lớp 4 Chân Trời Sáng Tạo (Tập 1)','LAM HUYNH','Education','http://books.google.com/books/content?id=5LLaEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2023-10-04'),('23021745','Giải Tiếng Anh 9 Global Success (Tập 1)','LAM HUYNH','Education','http://books.google.com/books/content?id=tOAkEQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2024-09-28'),('23021745','Sách luyện thi Hội thi Tin học trẻ với Python bảng B cấp THCS','VIETSTEM','Education','http://books.google.com/books/content?id=O99WEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2021-12-29'),('23021745','Sherlock Holmes, Volume 3','Arthur Conan Doyle','Fiction','http://books.google.com/books/content?id=e9gsDQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2016-02-15'),('23021745','Fuck','Christopher Fairman','Law','http://books.google.com/books/content?id=cB6E9Ii--_sC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2009-09-01'),('23021745','Fuck','Christopher Fairman','Law','http://books.google.com/books/content?id=cB6E9Ii--_sC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2009-09-01'),('23021745','Giải Tiếng Anh 7 Global Success (Sách Học Sinh) Tập 1','LAM HUYNH','Education','http://books.google.com/books/content?id=Gm0NEQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2024-06-10'),('23021745','Web Systems Design and Online Consumer Behavior','Yuan Gao','Business & Economics','http://books.google.com/books/content?id=J79sIVeP8M8C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2005-01-01'),('23021745','Smooth Manifolds','Rajnikant Sinha','Mathematics','http://books.google.com/books/content?id=BH5qBQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2014-11-15'),('23021745','Decentralizing the Online Experience With Web3 Technologies','Darwish, Dina','Computers','http://books.google.com/books/content?id=j8j8EAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2024-03-18'),('23021745','Giải Bài Tập SGK Toán Lớp 6 (Tập 1) Cánh Diều','LAM HUYNH ','Education','http://books.google.com/books/content?id=Fv_aEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2023-10-06');
/*!40000 ALTER TABLE `save` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-29  0:00:15
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lms
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `studentNumber` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `image` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('admin','admin','E:\\LMS\\src\\main\\resources\\com\\example\\lms\\image\\ava.png'),('23021745','12345','E:\\LMS\\image\\Ava.jpg'),('21020601','12345','E:\\LMS\\image\\logo1.jpg'),('21020724','12345','E:\\LMS\\image\\Ava.jpg');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-29  0:00:15
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lms
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `take`
--

DROP TABLE IF EXISTS `take`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `take` (
  `studentNumber` varchar(100) NOT NULL,
  `firstName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `gender` varchar(100) NOT NULL,
  `bookTitle` varchar(100) NOT NULL,
  `author` varchar(100) NOT NULL,
  `bookType` varchar(100) NOT NULL,
  `image` varchar(500) NOT NULL,
  `date` date DEFAULT NULL,
  `checkReturn` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `take`
--

LOCK TABLES `take` WRITE;
/*!40000 ALTER TABLE `take` DISABLE KEYS */;
INSERT INTO `take` VALUES ('23021745','Trung','Nguyen','Nam','Đồng Minh của Nhân Loại TẬP MỘT (Allies of Humanity, Book One - Vietnamese)','Marshall Vian Summers','Body, Mind & Spirit','http://books.google.com/books/content?id=w1LbDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2024-11-28','Not Return'),('23021745','Trung','Nguyen','Nam','Pew','Catherine Lacey','Fiction','http://books.google.com/books/content?id=e_-wDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2024-11-28','Not Return'),('23021745','Trung','Nguyen','Nam','Giải Sách Bài Tập Toán 9 Chân Trời Sáng Tạo (Tập 2)','LAM HUYNH','Education','http://books.google.com/books/content?id=J1MoEQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2024-11-28','Not Return'),('23021745','Trung','Nguyen','Nam','I Conan','L. J. LaRoch','Fiction','http://books.google.com/books/content?id=M__SEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2024-11-28','Not Return'),('23021745','Trung','Nguyen','Nam','B_ gi_i ngh_a Tân __c c_a Tyndale: Gia-c_','Douglas J. Moo','Religion','http://books.google.com/books/content?id=0fiuDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2024-11-28','Not Return'),('23021745','Trung','Nguyen','Nam','Fen','Julie Ankerson','Fiction','http://books.google.com/books/content?id=eoJyAgAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2024-11-28','Not Return'),('23021745','Trung','Nguyen','Nam','Organizational Fit','Amy L. Kristof-Brown','Psychology','http://books.google.com/books/content?id=hgxrT2zXaSsC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2024-11-28','Not Return'),('23021745','Trung','Nguyen','Nam','Web Systems Design and Online Consumer Behavior','Yuan Gao','Business & Economics','http://books.google.com/books/content?id=J79sIVeP8M8C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2024-11-28','Not Return'),('23021745','Trung','Nguyen','Nam','Web Systems Design and Online Consumer Behavior','Yuan Gao','Business & Economics','http://books.google.com/books/content?id=J79sIVeP8M8C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2024-11-28','Not Return'),('23021745','Trung','Nguyen','Nam','Mastering Windows Server 2022','Jordan Krause','Computers','http://books.google.com/books/content?id=w8nAEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2024-11-28','Not Return'),('23021745','Trung','Nguyen','Nam','Cuoc Chien Ho Den','Dong Yen','Reference','http://books.google.com/books/content?id=hmWPDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','2024-11-28','Not Return');
/*!40000 ALTER TABLE `take` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-29  0:00:15
