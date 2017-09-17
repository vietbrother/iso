-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 17, 2017 at 04:48 AM
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
-- Indexes for dumped tables
--

--
-- Indexes for table `cata_user`
--
ALTER TABLE `cata_user`
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
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
