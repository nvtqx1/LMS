-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 08, 2022 at 04:54 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `marcodata`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `bookTitle` varchar(100) NOT NULL,
  `author` varchar(100) NOT NULL,
  `bookType` varchar(100) NOT NULL,
  `image` varchar(500) NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`bookTitle`, `author`, `bookType`, `image`, `date`) VALUES
('Programming Language', 'Thumbnail_01', 'Non-fiction', 'C:\\Users\\WINDOWS 10\\Documents\\NetBeansProjects\\libraryManagement\\src\\image\\programming language book.jpg', '2018-10-16'),
('JavaFX Tutorial', 'Thumbnail_02', 'Non-fiction', 'C:\\Users\\WINDOWS 10\\Documents\\NetBeansProjects\\libraryManagement\\src\\image\\javafx tutorial book.jpg', '2020-04-24'),
('Java Tutorial', 'Thumbnail_04', 'Non-fiction', 'C:\\Users\\WINDOWS 10\\Documents\\NetBeansProjects\\libraryManagement\\src\\image\\java tutorial.jpg', '2019-12-17'),
('Python Tutorial', 'Thumbnail_05', 'Non-fiction', 'C:\\Users\\WINDOWS 10\\Documents\\NetBeansProjects\\libraryManagement\\src\\image\\python tutorial.jpg', '2017-08-06'),
('C# Tutorial', 'Thumbnail_03', 'Non-fiction', 'C:\\Users\\WINDOWS 10\\Documents\\NetBeansProjects\\libraryManagement\\src\\image\\c#  tutorial book.jpg', '2022-05-18'),
('weqwewq', '', '', '', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `save`
--

CREATE TABLE `save` (
  `studentNumber` varchar(100) NOT NULL,
  `bookTitle` varchar(100) NOT NULL,
  `author` varchar(100) NOT NULL,
  `bookType` varchar(100) NOT NULL,
  `image` varchar(500) DEFAULT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `save`
--

INSERT INTO `save` (`studentNumber`, `bookTitle`, `author`, `bookType`, `image`, `date`) VALUES
('2022024', 'Programming Language', 'Thumbnail_01', 'Non-fiction', 'C:\\Users\\WINDOWS 10\\Documents\\NetBeansProjects\\libraryManagement\\src\\image\\programming language book.jpg', '2018-10-16'),
('2022024', 'JavaFX Tutorial', 'Thumbnail_02', 'Non-fiction', 'C:\\Users\\WINDOWS 10\\Documents\\NetBeansProjects\\libraryManagement\\src\\image\\javafx tutorial book.jpg', '2020-04-24'),
('2022025', 'Programming Language', 'Thumbnail_01', 'Non-fiction', 'C:\\Users\\WINDOWS 10\\Documents\\NetBeansProjects\\libraryManagement\\src\\image\\programming language book.jpg', '2018-10-16'),
('2022025', 'Python Tutorial', 'Thumbnail_05', 'Non-fiction', 'C:\\Users\\WINDOWS 10\\Documents\\NetBeansProjects\\libraryManagement\\src\\image\\python tutorial.jpg', '2017-08-06'),
('2022024', 'Java Tutorial', 'Thumbnail_04', 'Non-fiction', 'C:\\Users\\WINDOWS 10\\Documents\\NetBeansProjects\\libraryManagement\\src\\image\\java tutorial.jpg', '2019-12-17');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `studentNumber` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `image` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`studentNumber`, `password`, `image`) VALUES
('2022024', 'admin123', 'D:\\Users\\WINDOWS 10\\Downloads\\logo.jpg'),
('2022025', 'admin123', 'C:\\Users\\WINDOWS 10\\Documents\\NetBeansProjects\\libraryManagement\\src\\image\\programming language book.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `take`
--

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `take`
--

INSERT INTO `take` (`studentNumber`, `firstName`, `lastName`, `gender`, `bookTitle`, `author`, `bookType`, `image`, `date`, `checkReturn`) VALUES
('2022024', 'Marco', 'Man', 'Male', 'Python Tutorial', 'Thumbnail_05', 'Non-fiction', 'C:\\Users\\WINDOWS 10\\Documents\\NetBeansProjects\\libraryManagement\\src\\image\\python tutorial.jpg', '2022-08-05', 'Returned'),
('2022024', 'Marco', 'Man', 'Male', 'Programming Language', 'Thumbnail_01', 'Non-fiction', 'C:\\Users\\WINDOWS 10\\Documents\\NetBeansProjects\\libraryManagement\\src\\image\\programming language book.jpg', '2022-08-05', 'Returned'),
('2022024', 'Marco', 'Man', 'Male', 'JavaFX Tutorial', 'Thumbnail_02', 'Non-fiction', 'C:\\Users\\WINDOWS 10\\Documents\\NetBeansProjects\\libraryManagement\\src\\image\\javafx tutorial book.jpg', '2022-08-05', 'Returned'),
('2022024', 'Marco', 'Man', 'Male', 'JavaFX Tutorial', 'Thumbnail_02', 'Non-fiction', 'C:\\Users\\WINDOWS 10\\Documents\\NetBeansProjects\\libraryManagement\\src\\image\\javafx tutorial book.jpg', '2022-08-05', 'Not Return'),
('2022025', 'Benchi', 'Chongskie', 'Female', 'JavaFX Tutorial', 'Thumbnail_02', 'Non-fiction', 'C:\\Users\\WINDOWS 10\\Documents\\NetBeansProjects\\libraryManagement\\src\\image\\javafx tutorial book.jpg', '2022-08-05', 'Not Return'),
('2022025', 'Benchi', 'Chongskie', 'Female', 'Programming Language', 'Thumbnail_01', 'Non-fiction', 'C:\\Users\\WINDOWS 10\\Documents\\NetBeansProjects\\libraryManagement\\src\\image\\programming language book.jpg', '2022-08-05', 'Returned'),
('2022024', 'Marco', 'Man', 'Male', 'Programming Language', 'Thumbnail_01', 'Non-fiction', 'C:\\Users\\WINDOWS 10\\Documents\\NetBeansProjects\\libraryManagement\\src\\image\\programming language book.jpg', '2022-08-05', 'Not Return'),
('2022024', 'Marco', 'Man', 'Male', 'Python Tutorial', 'Thumbnail_05', 'Non-fiction', 'C:\\Users\\WINDOWS 10\\Documents\\NetBeansProjects\\libraryManagement\\src\\image\\python tutorial.jpg', '2022-08-05', 'Returned'),
('2022024', 'Marco', 'Man', 'Male', 'C# Tutorial', 'Thumbnail_03', 'Non-fiction', 'C:\\Users\\WINDOWS 10\\Documents\\NetBeansProjects\\libraryManagement\\src\\image\\c#  tutorial book.jpg', '2022-08-05', 'Not Return');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
