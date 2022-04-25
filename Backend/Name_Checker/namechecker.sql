-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2022 at 07:14 AM
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
  `Check_In` varchar(10) COLLATE utf8mb4_thai_520_w2 NOT NULL,
  `Date` varchar(100) COLLATE utf8mb4_thai_520_w2 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_thai_520_w2;

--
-- Dumping data for table `section`
--

INSERT INTO `section` (`Subject`, `Sec`, `Time`, `Name`, `Pass`, `Check_In`, `Date`) VALUES
('Mobile', 1, '9.00-12.00', 'Teacher', '6CBM2QJA', 'BZABG', '25/Apr'),
('IoT', 1, '9.00-12.00', 'Teacher', 'L6TYAMZ8', 'IIHIQ', '25/Apr'),
('Mobile', 2, 'M. 13.00-16.00', 'Teacher2', 'LOJ7WZJX', 'FWKYZ', '25/Apr'),
('IoT', 2, 'T. 9.00-12.00', 'Teacher2', '76WXCZ9Z', 'TKGFP', '25/Apr'),
('HCI', 1, 'W. 9.00-12.00', 'Teacher3', 'P9FX4UPT', 'SGNDG', '25/Apr');

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
  `Count` int(10) NOT NULL,
  `Last` varchar(100) COLLATE utf8mb4_thai_520_w2 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_thai_520_w2;

--
-- Dumping data for table `student_list`
--

INSERT INTO `student_list` (`Subject`, `Subject_Id`, `Sec`, `Time`, `Teacher`, `Name`, `Id`, `Count`, `Last`) VALUES
('Mobile', '1', 1, '9.00-12.00', 'Teacher', 'Student', 'S1', 4, '25/Apr'),
('IoT', '2', 1, '9.00-12.00', 'Teacher', 'Student', 'S1', 3, '25/Apr'),
('Mobile', '1', 2, 'M. 13.00-16.00', 'Teacher2', 'Student2', 'S2', 0, ''),
('IoT', '2', 2, 'T. 9.00-12.00', 'Teacher2', 'Student2', 'S2', 0, ''),
('Mobile', '1', 1, '9.00-12.00', 'Teacher', 'Nutdanai Srinithichetsadaporn', '6204062620097', 0, ''),
('IoT', '2', 1, '9.00-12.00', 'Teacher', 'Nutdanai Srinithichetsadaporn', '6204062620097', 0, ''),
('HCI', '3', 1, 'W. 9.00-12.00', 'Teacher3', 'Student', 'S1', 1, '25/Apr'),
('HCI', '3', 1, 'W. 9.00-12.00', 'Teacher3', 'Student2', 'S2', 0, ''),
('HCI', '3', 1, 'W. 9.00-12.00', 'Teacher3', 'Nutdanai Srinithichetsadaporn', '6204062620097', 0, '');

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `Subject` varchar(255) COLLATE utf8mb4_thai_520_w2 NOT NULL,
  `Id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_thai_520_w2;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`Subject`, `Id`) VALUES
('HCI', 3),
('IoT', 2),
('Mobile', 1);

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
-- Dumping data for table `user`
--

INSERT INTO `user` (`Id`, `Name`, `Username`, `Password`, `Role`) VALUES
('6204062620097', 'Nutdanai Srinithichetsadaporn', 's6204062620097', '1111', 'Student'),
('S1', 'Student', 'S1', '1111', 'Student'),
('S2', 'Student2', 'S2', '1111', 'Student'),
('T1', 'Teacher', 'T1', '1111', 'Teacher'),
('t111', 'Teacher2', 'T2', '1111', 'Teacher'),
('T3', 'Teacher3', 'T3', '1111', 'Teacher');

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
