-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 23, 2022 at 01:05 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `namechecker`
--

-- --------------------------------------------------------

--
-- Table structure for table `section`
--

CREATE TABLE `section` (
  `Subject` varchar(255) COLLATE utf8mb4_thai_520_w2 NOT NULL,
  `Sec` int(10) NOT NULL,
  `Time` varchar(255) COLLATE utf8mb4_thai_520_w2 NOT NULL,
  `Name` varchar(100) COLLATE utf8mb4_thai_520_w2 NOT NULL,
  `Pass` varchar(10) COLLATE utf8mb4_thai_520_w2 NOT NULL,
  `Check_In` varchar(10) COLLATE utf8mb4_thai_520_w2 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_thai_520_w2;

-- --------------------------------------------------------

--
-- Table structure for table `student_list`
--

CREATE TABLE `student_list` (
  `Subject` varchar(255) COLLATE utf8mb4_thai_520_w2 NOT NULL,
  `Subject_Id` varchar(255) COLLATE utf8mb4_thai_520_w2 NOT NULL,
  `Sec` int(10) NOT NULL,
  `Time` varchar(255) COLLATE utf8mb4_thai_520_w2 NOT NULL,
  `Teacher` varchar(100) COLLATE utf8mb4_thai_520_w2 NOT NULL,
  `Name` varchar(255) COLLATE utf8mb4_thai_520_w2 NOT NULL,
  `Id` varchar(13) COLLATE utf8mb4_thai_520_w2 DEFAULT NULL,
  `Count` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_thai_520_w2;

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `Subject` varchar(255) COLLATE utf8mb4_thai_520_w2 NOT NULL,
  `Id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_thai_520_w2;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `Id` varchar(13) COLLATE utf8mb4_thai_520_w2 NOT NULL,
  `Name` varchar(255) COLLATE utf8mb4_thai_520_w2 NOT NULL,
  `Username` varchar(100) COLLATE utf8mb4_thai_520_w2 NOT NULL,
  `Password` varchar(100) COLLATE utf8mb4_thai_520_w2 NOT NULL,
  `Role` varchar(100) COLLATE utf8mb4_thai_520_w2 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_thai_520_w2;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `section`
--
ALTER TABLE `section`
  ADD KEY `Subject` (`Subject`),
  ADD KEY `Name` (`Name`),
  ADD KEY `Sec` (`Sec`);

--
-- Indexes for table `student_list`
--
ALTER TABLE `student_list`
  ADD KEY `Subject` (`Subject`),
  ADD KEY `Sec` (`Sec`),
  ADD KEY `Name` (`Name`),
  ADD KEY `Id` (`Id`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Subject` (`Subject`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `Username` (`Username`),
  ADD UNIQUE KEY `Name` (`Name`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `section`
--
ALTER TABLE `section`
  ADD CONSTRAINT `section_ibfk_2` FOREIGN KEY (`Name`) REFERENCES `user` (`Name`),
  ADD CONSTRAINT `section_ibfk_3` FOREIGN KEY (`Subject`) REFERENCES `subject` (`Subject`);

--
-- Constraints for table `student_list`
--
ALTER TABLE `student_list`
  ADD CONSTRAINT `student_list_ibfk_2` FOREIGN KEY (`Sec`) REFERENCES `section` (`Sec`),
  ADD CONSTRAINT `student_list_ibfk_3` FOREIGN KEY (`Name`) REFERENCES `user` (`Name`),
  ADD CONSTRAINT `student_list_ibfk_4` FOREIGN KEY (`Subject`) REFERENCES `subject` (`Subject`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
