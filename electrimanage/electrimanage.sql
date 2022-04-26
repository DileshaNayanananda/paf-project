-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2022 at 06:47 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `electrimanage`
--

-- --------------------------------------------------------

--
-- Table structure for table `inquiry1`
--

CREATE TABLE IF NOT EXISTS `inquiry1` (
  `inquiryId` int(6) NOT NULL AUTO_INCREMENT,
  `inquiry_name` varchar(200) NOT NULL,
  `inquiry_acc` varchar(40) NOT NULL,
  `inquiry_reason` varchar(100) NOT NULL,
  `inquiry_date` varchar(30) NOT NULL,
  `inquiry_pNo` varchar(10) NOT NULL,
  PRIMARY KEY (`inquiryId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Table structure for table `payment1`
--

CREATE TABLE IF NOT EXISTS `payment1` (
  `paymnt_id` int(4) NOT NULL AUTO_INCREMENT,
  `date_time` varchar(20) NOT NULL,
  `user_address` varchar(50) NOT NULL,
  `amount` varchar(10) NOT NULL,
  `payment_method` varchar(20) NOT NULL,
  PRIMARY KEY (`paymnt_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `payment1`
--

INSERT INTO `payment1` (`paymnt_id`, `date_time`, `user_address`, `amount`, `payment_method`) VALUES
(2, '10-04-2022 & 10:30', 'Kandy', '1500', 'Card');

-- --------------------------------------------------------

--
-- Table structure for table `powercut1`
--

CREATE TABLE IF NOT EXISTS `powercut1` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `date` varchar(20) NOT NULL,
  `time` varchar(20) NOT NULL,
  `area` varchar(200) NOT NULL,
  `duration` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Table structure for table `user1`
--

CREATE TABLE IF NOT EXISTS `user1` (
  `user_id` int(8) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(200) NOT NULL,
  `email` varchar(100) NOT NULL,
  `address` varchar(200) NOT NULL,
  `phone_num` varchar(10) NOT NULL,
  `password` varchar(40) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
