-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 21, 2017 at 07:32 PM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `iso`
--

-- --------------------------------------------------------

--
-- Table structure for table `cata_user`
--

CREATE TABLE `cata_user` (
  `id` int(11) NOT NULL,
  `BIRTH_DAY` date DEFAULT NULL,
  `EMAIL` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `FIRST_NAME` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `LAST_NAME` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `LOCATION` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `SEX` bit(1) DEFAULT NULL,
  `PHONE` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `ROLE` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `TITLE` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `USERNAME` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `WEBSITE` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `organization`
--

CREATE TABLE `organization` (
  `id` int(11) NOT NULL,
  `CREATE_TIME` date DEFAULT NULL,
  `TIME_UPDATE` date DEFAULT NULL,
  `NAME` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `CODE` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `VALUE` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `POSITION` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `PARENT_ID` int(11) DEFAULT '0',
  `DESCRIPTION` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `STATUS` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `CREATED_BY` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `UPDATE_BY` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `level` varchar(25) COLLATE utf8_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `organization`
--

INSERT INTO `organization` (`id`, `CREATE_TIME`, `TIME_UPDATE`, `NAME`, `CODE`, `VALUE`, `POSITION`, `PARENT_ID`, `DESCRIPTION`, `STATUS`, `CREATED_BY`, `UPDATE_BY`, `level`) VALUES
(1, '2017-09-20', '2017-09-20', 'Công ty XXX', 'cty.x', '-1', '1', 0, NULL, 'ACTIVE', 'admin', 'admin', '0'),
(2, '2017-09-20', '2017-09-20', 'Trung tâm A', 'tt.a', '1', '1', 1, NULL, 'ACTIVE', 'admin', 'admin', '1'),
(3, '2017-09-20', '2017-09-20', 'Trung tâm B', 'tt.b', '2', '2', 1, NULL, 'ACTIVE', 'admin', 'admin', '1'),
(4, '2017-09-20', '2017-09-20', 'Trung tâm C', 'tt.c', '3', '3', 1, NULL, 'ACTIVE', 'admin', 'admin', '1'),
(5, '2017-09-20', '2017-09-20', 'Phòng A1', 'p.a1', '1', '1', 2, NULL, 'ACTIVE', 'admin', 'admin', '2'),
(6, '2017-09-20', '2017-09-20', 'Phòng A2', 'p.a2', '2', '2', 2, NULL, 'ACTIVE', 'admin', 'admin', '2'),
(7, '2017-09-20', '2017-09-20', 'Phòng B1', 'p.b', '1', '1', 3, NULL, 'ACTIVE', 'admin', 'admin', '2'),
(10, '2017-09-21', '2017-09-21', 'Phong D', 'p.D', '1', '1', 1, 'asdasd', NULL, 'admin', 'admin', '1');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `FIRST_NAME` varchar(255) COLLATE utf8_vietnamese_ci NOT NULL,
  `LAST_NAME` varchar(255) COLLATE utf8_vietnamese_ci NOT NULL,
  `SECTION` varchar(255) COLLATE utf8_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `BIRTH_DAY` date DEFAULT NULL,
  `EMAIL` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `FIRST_NAME` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `LAST_NAME` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `LOCATION` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `SEX` bit(1) DEFAULT NULL,
  `PHONE` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `ROLE` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `TITLE` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `USERNAME` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `WEBSITE` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `BIRTH_DAY`, `EMAIL`, `FIRST_NAME`, `LAST_NAME`, `LOCATION`, `SEX`, `PHONE`, `ROLE`, `TITLE`, `USERNAME`, `WEBSITE`) VALUES
(1, '2017-09-06', 'EMAIL', 'fields', 'EMAIL', 'fields', b'1111111111111111111111111111111', 'fields', 'fields', 'fields', 'fields', 'fields');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cata_user`
--
ALTER TABLE `cata_user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `organization`
--
ALTER TABLE `organization`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cata_user`
--
ALTER TABLE `cata_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `organization`
--
ALTER TABLE `organization`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
