-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 17, 2017 at 04:29 AM
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
-- Table structure for table `c_calendar`
--

CREATE TABLE `c_calendar` (
  `calendar_id` int(11) NOT NULL,
  `calendar_name` varchar(200) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `working_date` int(11) DEFAULT NULL COMMENT 'ngày đi làm hay không',
  `description` varchar(500) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT 'trạng thái',
  `inserted_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `inserted_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `color` varchar(200) COLLATE utf8_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `c_calendar`
--

INSERT INTO `c_calendar` (`calendar_id`, `calendar_name`, `working_date`, `description`, `status`, `inserted_by`, `updated_by`, `inserted_time`, `updated_time`, `color`) VALUES
(1, '122', 1, '', NULL, NULL, NULL, NULL, NULL, 'RED');

-- --------------------------------------------------------

--
-- Table structure for table `c_color_defined`
--

CREATE TABLE `c_color_defined` (
  `color_name` varchar(10) COLLATE utf8_vietnamese_ci NOT NULL,
  `color_code` varchar(10) COLLATE utf8_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `c_color_defined`
--

INSERT INTO `c_color_defined` (`color_name`, `color_code`) VALUES
('AQUA', '00FFFF'),
('BLACK', '000000'),
('BLUE', '0000FF'),
('FUCHSIA', 'FF00FF'),
('GRAY', '808080'),
('GREEN', '008000'),
('LIME', '00FF00'),
('MAROON', '800000'),
('NAVY', '000080'),
('OLIVE', '808000'),
('PURPLE', '800080'),
('RED', 'FF0000'),
('SILVER', 'C0C0C0'),
('TEAL', '008080'),
('WHITE', 'FFFFFF'),
('YELLOW', 'FFFF00');

-- --------------------------------------------------------

--
-- Table structure for table `c_document`
--

CREATE TABLE `c_document` (
  `id` int(11) NOT NULL,
  `document_code` varchar(45) NOT NULL,
  `document_name` varchar(255) DEFAULT NULL,
  `document_type` varchar(45) DEFAULT NULL,
  `security_level` varchar(45) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `part_storage_time` int(10) DEFAULT NULL,
  `department_storage_time` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `c_document`
--

INSERT INTO `c_document` (`id`, `document_code`, `document_name`, `document_type`, `security_level`, `created_by`, `status`, `part_storage_time`, `department_storage_time`) VALUES
(3, 'test', '123test', 'cứng', 'trung bình', 'test', '1', 32, 12),
(4, 'test234', 'test234', 'test234', 'test234', NULL, 'test234', 33, 22);

-- --------------------------------------------------------

--
-- Table structure for table `c_education`
--

CREATE TABLE `c_education` (
  `C_EDUCATION_ID` int(10) NOT NULL,
  `NAME` text COLLATE utf8_unicode_ci NOT NULL,
  `VALUE` text COLLATE utf8_unicode_ci NOT NULL,
  `IS_ACTIVE` tinyint(1) DEFAULT NULL,
  `CREATED` datetime DEFAULT NULL,
  `CREATED_BY` text COLLATE utf8_unicode_ci,
  `UPDATED` datetime DEFAULT NULL,
  `UPDATED_BY` text COLLATE utf8_unicode_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `c_education`
--

INSERT INTO `c_education` (`C_EDUCATION_ID`, `NAME`, `VALUE`, `IS_ACTIVE`, `CREATED`, `CREATED_BY`, `UPDATED`, `UPDATED_BY`) VALUES
(1, 'DEGREE_TYPE', 'Chứng chỉ', NULL, NULL, NULL, NULL, NULL),
(2, 'DEGREE_TYPE', 'Cao Đẳng', NULL, NULL, NULL, NULL, NULL),
(3, 'DEGREE_TYPE', 'Đại Học', NULL, NULL, NULL, NULL, NULL),
(4, 'DEGREE_TYPE', 'Thạc Sỹ', NULL, NULL, NULL, NULL, NULL),
(5, 'DEGREE_TYPE', 'Tiến Sĩ', NULL, NULL, NULL, NULL, NULL),
(6, 'DEGREE_TYPE', 'Khác', NULL, NULL, NULL, NULL, NULL),
(7, 'EDUCATION_TYPE', 'Tại chức', NULL, NULL, NULL, NULL, NULL),
(8, 'EDUCATION_TYPE', 'Nội bộ', NULL, NULL, NULL, NULL, NULL),
(9, 'EDUCATION_TYPE', 'Chính quy', NULL, NULL, NULL, NULL, NULL),
(10, 'EDUCATION_TYPE', 'Khác', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `c_employees`
--

CREATE TABLE `c_employees` (
  `C_EMPLOYEE_ID` int(10) NOT NULL,
  `EMPLOYEE_CODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Mã cán bộ, nhân viên',
  `EMPLOYEE_TYPE_ID` text COLLATE utf8_unicode_ci NOT NULL COMMENT 'Loại cán bộ hay viên chức hay thử việc',
  `FIRST_NAME` text COLLATE utf8_unicode_ci NOT NULL,
  `LAST_NAME` text COLLATE utf8_unicode_ci NOT NULL,
  `BIRTHDAY` date NOT NULL,
  `EMAIL` text COLLATE utf8_unicode_ci NOT NULL,
  `MOBILE` text COLLATE utf8_unicode_ci NOT NULL,
  `ADDRESS` text COLLATE utf8_unicode_ci NOT NULL,
  `IMAGE_URL` text COLLATE utf8_unicode_ci COMMENT 'Lưu đường dẫn ảnh cá nhân',
  `HIRE_FROM_DATE` date DEFAULT NULL,
  `HIRE_TO_DATE` date DEFAULT NULL,
  `SALARY` text COLLATE utf8_unicode_ci,
  `COMMISSION_PCT` text COLLATE utf8_unicode_ci,
  `DEPARTMENT_ID` text COLLATE utf8_unicode_ci NOT NULL,
  `JOB_ID` text COLLATE utf8_unicode_ci NOT NULL,
  `IS_ACTIVE` tinyint(1) NOT NULL,
  `CREATED_BY` text COLLATE utf8_unicode_ci,
  `CREATED` date DEFAULT NULL,
  `UPDATED_BY` text COLLATE utf8_unicode_ci,
  `UPDATED` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `c_employees`
--

INSERT INTO `c_employees` (`C_EMPLOYEE_ID`, `EMPLOYEE_CODE`, `EMPLOYEE_TYPE_ID`, `FIRST_NAME`, `LAST_NAME`, `BIRTHDAY`, `EMAIL`, `MOBILE`, `ADDRESS`, `IMAGE_URL`, `HIRE_FROM_DATE`, `HIRE_TO_DATE`, `SALARY`, `COMMISSION_PCT`, `DEPARTMENT_ID`, `JOB_ID`, `IS_ACTIVE`, `CREATED_BY`, `CREATED`, `UPDATED_BY`, `UPDATED`) VALUES
(1, '195455', '1', 'Lê Trung', 'Thực', '1987-01-17', 'minhn@viettel.com.vn', '0917333333', 'Cầu Giấy - Hà Nội', '/image/minhn.png', '2010-10-02', NULL, '21000000', 'PCT', '2', '1', 1, NULL, NULL, NULL, NULL),
(2, '195456', '2', 'Nguyễn Diệu', 'Linh', '1988-10-10', 'linhnd20@viettel.com.vn', '0987789987', 'Đống Đa', '/image/2.png', NULL, NULL, NULL, NULL, '3', '2', 1, NULL, NULL, NULL, NULL),
(3, '200118', '2', 'Mai Việt', 'Anh', '1991-10-26', 'anhmv@viettel.com.vn', '01667778889', 'Thanh Xuân', '/image/5.png', NULL, NULL, NULL, NULL, '5', '3', 1, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `c_employee_education`
--

CREATE TABLE `c_employee_education` (
  `C_EMPLOYEE_EDUCATION_ID` int(10) NOT NULL,
  `C_EMPLOYEE_ID` text COLLATE utf8_unicode_ci NOT NULL,
  `FROM_DATE` date NOT NULL,
  `TO_DATE` date NOT NULL,
  `C_DEGREE_TYPE_ID` text COLLATE utf8_unicode_ci NOT NULL,
  `C_EDUCATION_TYPE_ID` text COLLATE utf8_unicode_ci NOT NULL,
  `EDUCATION_PLACE` text COLLATE utf8_unicode_ci NOT NULL,
  `CLASSIFICATION` text COLLATE utf8_unicode_ci NOT NULL,
  `IS_ACTIVE` tinyint(1) DEFAULT NULL,
  `CREATED` datetime DEFAULT NULL,
  `CREATED_BY` text COLLATE utf8_unicode_ci,
  `UPDATED` datetime DEFAULT NULL,
  `UPDATED_BY` text COLLATE utf8_unicode_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `c_employee_education`
--

INSERT INTO `c_employee_education` (`C_EMPLOYEE_EDUCATION_ID`, `C_EMPLOYEE_ID`, `FROM_DATE`, `TO_DATE`, `C_DEGREE_TYPE_ID`, `C_EDUCATION_TYPE_ID`, `EDUCATION_PLACE`, `CLASSIFICATION`, `IS_ACTIVE`, `CREATED`, `CREATED_BY`, `UPDATED`, `UPDATED_BY`) VALUES
(1, '1', '2017-10-03', '2017-10-11', '3', '9', 'ĐH Công nghệ', 'Khá', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `c_employee_process`
--

CREATE TABLE `c_employee_process` (
  `C_EMPLOYEE_PROCESS_ID` int(10) NOT NULL,
  `C_EMPLOYEE_ID` text COLLATE utf8_unicode_ci NOT NULL,
  `FROM_DATE` date NOT NULL,
  `TO_DATE` date NOT NULL,
  `C_JOB_ID` text COLLATE utf8_unicode_ci NOT NULL,
  `C_EMPLOYEE_TYPE_ID` text COLLATE utf8_unicode_ci NOT NULL,
  `C_DEPARTMENT_ID` text COLLATE utf8_unicode_ci NOT NULL,
  `IS_ACTIVE` tinyint(1) DEFAULT NULL,
  `CREATED` datetime DEFAULT NULL,
  `CREATED_BY` text COLLATE utf8_unicode_ci,
  `UPDATED` datetime DEFAULT NULL,
  `UPDATED_BY` text COLLATE utf8_unicode_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `c_employee_process`
--

INSERT INTO `c_employee_process` (`C_EMPLOYEE_PROCESS_ID`, `C_EMPLOYEE_ID`, `FROM_DATE`, `TO_DATE`, `C_JOB_ID`, `C_EMPLOYEE_TYPE_ID`, `C_DEPARTMENT_ID`, `IS_ACTIVE`, `CREATED`, `CREATED_BY`, `UPDATED`, `UPDATED_BY`) VALUES
(1, '1', '2017-10-04', '2017-10-11', '1', '1', '2', NULL, NULL, NULL, NULL, NULL),
(2, '1', '2017-09-24', '2017-09-30', '2', '1', '3', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `c_employee_reward`
--

CREATE TABLE `c_employee_reward` (
  `C_EMPLOYEE_REWARD_ID` int(10) DEFAULT NULL,
  `C_EMPLOYEE_ID` text COLLATE utf8_unicode_ci,
  `REWARD_TYPE` text COLLATE utf8_unicode_ci,
  `REWARD_DATE` date DEFAULT NULL,
  `CONTENT` text COLLATE utf8_unicode_ci,
  `DESCRIPTION` text COLLATE utf8_unicode_ci,
  `IS_ACTIVE` tinyint(1) DEFAULT NULL,
  `CREATED` datetime DEFAULT NULL,
  `CREATED_BY` text COLLATE utf8_unicode_ci,
  `UPDATED` datetime DEFAULT NULL,
  `UPDATED_BY` text COLLATE utf8_unicode_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `c_employee_reward`
--

INSERT INTO `c_employee_reward` (`C_EMPLOYEE_REWARD_ID`, `C_EMPLOYEE_ID`, `REWARD_TYPE`, `REWARD_DATE`, `CONTENT`, `DESCRIPTION`, `IS_ACTIVE`, `CREATED`, `CREATED_BY`, `UPDATED`, `UPDATED_BY`) VALUES
(1, '1', '1', '2017-10-14', 'Lao động tiên tiến năm 2016', 'Triển khai thành công hệ thống BCCS 2.0 phục vụ SXKD thị trường.', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `c_employee_type`
--

CREATE TABLE `c_employee_type` (
  `C_EMPLOYEE_TYPE_ID` int(10) NOT NULL,
  `EMPLOYEE_TYPE` text COLLATE utf8_unicode_ci NOT NULL,
  `IS_ACTIVE` tinyint(1) DEFAULT NULL,
  `CREATED_BY` text COLLATE utf8_unicode_ci,
  `CREATED` date DEFAULT NULL,
  `UPDATED_BY` text COLLATE utf8_unicode_ci,
  `UPDATED` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `c_employee_type`
--

INSERT INTO `c_employee_type` (`C_EMPLOYEE_TYPE_ID`, `EMPLOYEE_TYPE`, `IS_ACTIVE`, `CREATED_BY`, `CREATED`, `UPDATED_BY`, `UPDATED`) VALUES
(1, 'Viên chức', NULL, NULL, NULL, NULL, NULL),
(2, 'Công chức', NULL, NULL, NULL, NULL, NULL),
(3, 'Nhân viên', NULL, NULL, NULL, NULL, NULL),
(4, 'Hợp đồng ngắn hạn', NULL, NULL, NULL, NULL, NULL),
(5, 'Thử việc', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `c_file`
--

CREATE TABLE `c_file` (
  `id` int(11) NOT NULL,
  `file_code` varchar(45) NOT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_type` varchar(45) DEFAULT NULL,
  `security_level` varchar(45) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `part_storage_time` int(10) DEFAULT NULL,
  `department_storage_time` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `c_file`
--

INSERT INTO `c_file` (`id`, `file_code`, `file_name`, `file_type`, `security_level`, `created_by`, `status`, `part_storage_time`, `department_storage_time`) VALUES
(2, 'sử dụng', 'test123', 'test321', 'mềm', NULL, 'test321', 12, 11);

-- --------------------------------------------------------

--
-- Table structure for table `c_job`
--

CREATE TABLE `c_job` (
  `C_JOB_ID` int(11) NOT NULL,
  `JOB_TITLE` text COLLATE utf8_unicode_ci NOT NULL,
  `SALARY_GLONE` decimal(10,0) DEFAULT NULL,
  `SALARY_WAGE` decimal(10,0) DEFAULT NULL,
  `MIN_SALARY` decimal(10,0) DEFAULT NULL,
  `MAX_SALARY` decimal(10,0) DEFAULT NULL,
  `IS_ACTIVE` tinyint(1) DEFAULT NULL,
  `CREATED_BY` text COLLATE utf8_unicode_ci,
  `CREATED` date DEFAULT NULL,
  `UPDATED_BY` text COLLATE utf8_unicode_ci,
  `UPDATED` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `c_job`
--

INSERT INTO `c_job` (`C_JOB_ID`, `JOB_TITLE`, `SALARY_GLONE`, `SALARY_WAGE`, `MIN_SALARY`, `MAX_SALARY`, `IS_ACTIVE`, `CREATED_BY`, `CREATED`, `UPDATED_BY`, `UPDATED`) VALUES
(1, 'Trưởng phòng', '3', '5', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, 'Nhân viên', '2', '3', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(3, 'Phó Trưởng Phòng', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `c_job_history`
--

CREATE TABLE `c_job_history` (
  `C_JOB_HISTORY_ID` int(10) NOT NULL,
  `EMPLOYEE_ID` text COLLATE utf8_unicode_ci NOT NULL,
  `EMPLOYEE_TYPE_ID` text COLLATE utf8_unicode_ci NOT NULL COMMENT 'Loại cán bộ, viên chức, hợp đồng, thử việc...',
  `START_DATE` text COLLATE utf8_unicode_ci NOT NULL,
  `END_DATE` date NOT NULL,
  `JOB_ID` text COLLATE utf8_unicode_ci NOT NULL,
  `DEPARTMENT_ID` text COLLATE utf8_unicode_ci NOT NULL,
  `SALARY_GLONE` decimal(10,0) DEFAULT NULL COMMENT 'Ngạch lương',
  `SALARY_WAGE` decimal(10,0) DEFAULT NULL COMMENT 'Bậc lương',
  `IS_ACTIVE` tinyint(1) DEFAULT NULL,
  `CREATED_BY` text COLLATE utf8_unicode_ci,
  `CREATED` date DEFAULT NULL,
  `UPDATED_BY` text COLLATE utf8_unicode_ci,
  `UPDATED` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `c_organization_task`
--

CREATE TABLE `c_organization_task` (
  `task_id` int(11) NOT NULL,
  `task_name` varchar(200) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `task_group_id` int(11) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `budget` float DEFAULT NULL,
  `currency` varchar(5) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `task_piority_id` int(11) DEFAULT NULL,
  `task_content` varchar(1000) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `attachment` varchar(1000) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `process_user_id` int(11) DEFAULT NULL COMMENT 'người thực hiện',
  `progress` int(11) DEFAULT NULL COMMENT 'tiến độ công việc',
  `status` int(11) DEFAULT NULL,
  `task_parent_id` int(11) DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `inserted_user_id` int(11) DEFAULT NULL COMMENT 'người tạo',
  `updated_user_id` int(11) DEFAULT NULL COMMENT 'người cập nhật'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `c_security_level`
--

CREATE TABLE `c_security_level` (
  `id` int(11) NOT NULL,
  `security_level_name` varchar(255) DEFAULT NULL,
  `security_level_color` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_active` int(11) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `c_security_level`
--

INSERT INTO `c_security_level` (`id`, `security_level_name`, `security_level_color`, `description`, `is_active`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES
(1, 'test', '#0000', 'test324', 0, 'test', NULL, 'test', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `c_task`
--

CREATE TABLE `c_task` (
  `task_id` int(11) NOT NULL,
  `task_name` varchar(200) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `task_group_id` int(11) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `budget` float DEFAULT NULL,
  `currency` varchar(5) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `task_piority_id` int(11) DEFAULT NULL,
  `task_content` varchar(1000) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `task_parent_id` int(11) DEFAULT NULL,
  `assigned_by` int(11) DEFAULT NULL COMMENT 'người giao việc',
  `insert_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL COMMENT 'người tạo',
  `updated_by` int(11) DEFAULT NULL COMMENT 'người cập nhật',
  `reviewed_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `reviewed_result` int(11) DEFAULT NULL,
  `reviewed_content` varchar(3000) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `reviewed_by` int(11) DEFAULT NULL,
  `rate` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `c_task`
--

INSERT INTO `c_task` (`task_id`, `task_name`, `task_group_id`, `department_id`, `start_time`, `end_time`, `budget`, `currency`, `task_piority_id`, `task_content`, `status`, `task_parent_id`, `assigned_by`, `insert_time`, `update_time`, `created_by`, `updated_by`, `reviewed_time`, `reviewed_result`, `reviewed_content`, `reviewed_by`, `rate`) VALUES
(1, 'task_name', 1, 2, '2017-10-04 00:00:00', '2017-10-06 00:00:00', 100000, NULL, 1, 'content', 1, NULL, NULL, '2017-10-04 00:00:00', '2017-10-04 00:00:00', 1, 1, '2017-10-05 15:27:03', NULL, NULL, NULL, 70),
(2, 'task_childname', 1, 2, '2017-10-04 00:00:00', '2017-10-06 00:00:00', 100000, NULL, 1, 'sub_content', 1, 1, NULL, '2017-10-04 00:00:00', '2017-10-04 00:00:00', 1, 1, '2017-10-05 15:27:03', NULL, NULL, NULL, 30),
(3, 'abc', 1, 2, '2017-10-11 16:49:35', '2017-10-11 16:49:38', 1234, NULL, 1, 'ác', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `c_task_assignee`
--

CREATE TABLE `c_task_assignee` (
  `task_id` int(11) NOT NULL,
  `seq` int(11) NOT NULL,
  `assigned_id` int(11) NOT NULL,
  `is_main` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Triggers `c_task_assignee`
--
DELIMITER $$
CREATE TRIGGER `c_task_assignee_BI` BEFORE INSERT ON `c_task_assignee` FOR EACH ROW BEGIN
    SET NEW.seq = (
       SELECT IFNULL(MAX(seq), 0) + 1
       FROM c_task_assignee
       WHERE task_id  = NEW.task_id
    );
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `c_task_attachment`
--

CREATE TABLE `c_task_attachment` (
  `task_id` int(11) NOT NULL,
  `seq` int(11) NOT NULL,
  `attachment_type` int(11) NOT NULL COMMENT 'loại đính kèm: task hoặc báo cáo',
  `file_name` varchar(100) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `attachment_url` varchar(1000) COLLATE utf8_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `c_task_attachment`
--

INSERT INTO `c_task_attachment` (`task_id`, `seq`, `attachment_type`, `file_name`, `attachment_url`) VALUES
(1, 1, 1, 'tesst', 'test'),
(1, 2, 1, 'tesst', 'test'),
(3, 1, 1, '164924_OOC_data_model.docx', '../data/task/2017/10/10/');

--
-- Triggers `c_task_attachment`
--
DELIMITER $$
CREATE TRIGGER `c_task_attachment_BI` BEFORE INSERT ON `c_task_attachment` FOR EACH ROW BEGIN
    SET NEW.seq = (
       SELECT IFNULL(MAX(seq), 0) + 1
       FROM c_task_attachment
       WHERE task_id  = NEW.task_id
       AND attachment_type = NEW.attachment_type
    );
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `c_task_group`
--

CREATE TABLE `c_task_group` (
  `task_group_id` int(11) NOT NULL,
  `task_group_name` varchar(200) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `description` varchar(1000) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `color` varchar(20) COLLATE utf8_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `c_task_group`
--

INSERT INTO `c_task_group` (`task_group_id`, `task_group_name`, `description`, `status`, `color`) VALUES
(1, 'group__1323', 'group__1', 1, 'blue'),
(2, 'group__2', 'group__2', 1, 'red'),
(3, 'group__3', 'group__3', 1, 'green');

-- --------------------------------------------------------

--
-- Table structure for table `c_task_history`
--

CREATE TABLE `c_task_history` (
  `task_id` int(11) NOT NULL,
  `hist_type` int(11) NOT NULL COMMENT 'loại tác động: báo cáo, đánh giá, điều chỉnh',
  `hist_content` varchar(3000) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `start_time` datetime DEFAULT NULL COMMENT 'dành cho trường hợp điều chỉnh',
  `end_time` datetime DEFAULT NULL COMMENT 'dành cho trường hợp điều chỉnh',
  `inserted_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `inserted_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `assiged_emp_id` int(11) DEFAULT NULL COMMENT 'dành cho trường hợp điều chỉnh',
  `rate` int(3) DEFAULT NULL COMMENT 'tiến độ công việc: dánh cho báo cáo và kiểm tra',
  `status` int(1) DEFAULT NULL COMMENT 'trạng thái'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Triggers `c_task_history`
--
DELIMITER $$
CREATE TRIGGER `CHECK_RATE_AI` AFTER INSERT ON `c_task_history` FOR EACH ROW BEGIN
        update c_task set rate = (select check_rate from c_task_history where task_id = NEW.task_id and inserted_time = (SELECT max(inserted_time) from c_task_history where task_id = NEW.task_id GROUP BY task_id)) where task_id = NEW.task_id;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `CHECK_RATE_AU` AFTER UPDATE ON `c_task_history` FOR EACH ROW BEGIN
        update c_task set rate = (select check_rate from c_task_history where task_id = NEW.task_id and inserted_time = (SELECT max(inserted_time) from c_task_history where task_id = NEW.task_id GROUP BY task_id)) where task_id = NEW.task_id;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `c_task_piority`
--

CREATE TABLE `c_task_piority` (
  `task_piority_id` int(11) NOT NULL,
  `task_piority_name` varchar(100) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `description` varchar(1000) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `color` varchar(20) COLLATE utf8_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `c_task_priority`
--

CREATE TABLE `c_task_priority` (
  `task_priority_id` int(11) NOT NULL,
  `task_priority_name` varchar(100) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `description` varchar(1000) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `color` varchar(20) COLLATE utf8_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `c_task_priority`
--

INSERT INTO `c_task_priority` (`task_priority_id`, `task_priority_name`, `description`, `status`, `color`) VALUES
(1, 'pri01', 'pri01', 1, 'white');

-- --------------------------------------------------------

--
-- Table structure for table `c_task_review`
--

CREATE TABLE `c_task_review` (
  `task_id` int(11) NOT NULL,
  `seq` int(11) NOT NULL,
  `criteria_name` varchar(500) COLLATE utf8_vietnamese_ci DEFAULT NULL COMMENT 'tên tiêu chí',
  `mark` int(11) DEFAULT NULL COMMENT 'điểm đánh giá',
  `reviewed_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `result` int(11) NOT NULL COMMENT 'đạt hay ko đạt',
  `unsuitable` int(11) NOT NULL COMMENT 'điểm không phù hợp. chưa rõ là gì'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Triggers `c_task_review`
--
DELIMITER $$
CREATE TRIGGER `update_seq` BEFORE INSERT ON `c_task_review` FOR EACH ROW BEGIN
    SET NEW.seq = (
       SELECT IFNULL(MAX(seq), 0) + 1
       FROM c_task_review
       WHERE task_id  = NEW.task_id
    );
END
$$
DELIMITER ;

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
(1, '2017-09-20', '2017-09-20', 'Công ty XXX', 'cty.x', '-1', '1', 0, 'DHCP Vendor Options\nThe Device Identity and Gateway Identity information exchanged via DHCP MUST be\ncontained within the DHCPv4 V-I Vendor-Specific Information Option (option number\n125, as define', 'ACTIVE', 'admin', 'admin', '0'),
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
  `SEX` int(2) DEFAULT NULL,
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
(1, '2017-09-06', 'EMAIL@gmail.com', 'EMAIL', 'EMAIL', 'fields', 1, 'Mai Việt anh', 'fields', 'fields', 'username1', 'fields'),
(2, '2017-09-06', 'EMAIL@gmail.com', 'name1', 'name1', 'fields', 1, 'phone1', 'fields', 'fields', 'username2', 'fields'),
(3, '2017-09-06', 'EMAIL@gmail.com', 'name2', 'name2', 'fields', 1, 'phone1', 'fields', 'fields', 'username3', 'fields'),
(4, '2017-09-06', 'EMAIL@gmail.com', 'name3', 'name3', 'fields', 1, 'phone1', 'fields', 'fields', 'username4', 'fields'),
(5, '2017-09-06', 'EMAIL@gmail.com', 'name4', 'name4', 'fields', 1, 'phone1', 'fields', 'fields', 'username5', 'fields'),
(6, '2017-09-06', 'EMAIL@gmail.com', 'name5', 'name5', 'fields', 1, 'phone1', 'fields', 'fields', 'username6', 'fields'),
(7, '2017-09-06', 'EMAIL@gmail.com', 'name6', 'name6', 'fields', 1, 'phone1', 'fields', 'fields', 'username7', 'fields');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cata_user`
--
ALTER TABLE `cata_user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `c_calendar`
--
ALTER TABLE `c_calendar`
  ADD PRIMARY KEY (`calendar_id`);

--
-- Indexes for table `c_color_defined`
--
ALTER TABLE `c_color_defined`
  ADD PRIMARY KEY (`color_name`);

--
-- Indexes for table `c_document`
--
ALTER TABLE `c_document`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `c_employees`
--
ALTER TABLE `c_employees`
  ADD PRIMARY KEY (`C_EMPLOYEE_ID`);

--
-- Indexes for table `c_employee_education`
--
ALTER TABLE `c_employee_education`
  ADD PRIMARY KEY (`C_EMPLOYEE_EDUCATION_ID`);

--
-- Indexes for table `c_employee_process`
--
ALTER TABLE `c_employee_process`
  ADD PRIMARY KEY (`C_EMPLOYEE_PROCESS_ID`);

--
-- Indexes for table `c_employee_type`
--
ALTER TABLE `c_employee_type`
  ADD PRIMARY KEY (`C_EMPLOYEE_TYPE_ID`);

--
-- Indexes for table `c_file`
--
ALTER TABLE `c_file`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `c_job`
--
ALTER TABLE `c_job`
  ADD PRIMARY KEY (`C_JOB_ID`);

--
-- Indexes for table `c_job_history`
--
ALTER TABLE `c_job_history`
  ADD PRIMARY KEY (`C_JOB_HISTORY_ID`);

--
-- Indexes for table `c_organization_task`
--
ALTER TABLE `c_organization_task`
  ADD PRIMARY KEY (`task_id`);

--
-- Indexes for table `c_security_level`
--
ALTER TABLE `c_security_level`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `c_task`
--
ALTER TABLE `c_task`
  ADD PRIMARY KEY (`task_id`);

--
-- Indexes for table `c_task_attachment`
--
ALTER TABLE `c_task_attachment`
  ADD KEY `task_id` (`task_id`,`attachment_type`,`seq`) USING BTREE;

--
-- Indexes for table `c_task_group`
--
ALTER TABLE `c_task_group`
  ADD PRIMARY KEY (`task_group_id`);

--
-- Indexes for table `c_task_history`
--
ALTER TABLE `c_task_history`
  ADD KEY `task_id` (`task_id`,`hist_type`,`inserted_time`) USING BTREE;

--
-- Indexes for table `c_task_piority`
--
ALTER TABLE `c_task_piority`
  ADD PRIMARY KEY (`task_piority_id`);

--
-- Indexes for table `c_task_priority`
--
ALTER TABLE `c_task_priority`
  ADD PRIMARY KEY (`task_priority_id`);

--
-- Indexes for table `c_task_review`
--
ALTER TABLE `c_task_review`
  ADD KEY `task_id` (`task_id`,`seq`);

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
-- AUTO_INCREMENT for table `c_calendar`
--
ALTER TABLE `c_calendar`
  MODIFY `calendar_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `c_document`
--
ALTER TABLE `c_document`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `c_file`
--
ALTER TABLE `c_file`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `c_organization_task`
--
ALTER TABLE `c_organization_task`
  MODIFY `task_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `c_security_level`
--
ALTER TABLE `c_security_level`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `c_task`
--
ALTER TABLE `c_task`
  MODIFY `task_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `c_task_group`
--
ALTER TABLE `c_task_group`
  MODIFY `task_group_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `c_task_piority`
--
ALTER TABLE `c_task_piority`
  MODIFY `task_piority_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `c_task_priority`
--
ALTER TABLE `c_task_priority`
  MODIFY `task_priority_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `organization`
--
ALTER TABLE `organization`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
