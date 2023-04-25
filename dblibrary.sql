-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2023 at 03:29 PM
-- Server version: 5.7.17
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dblibrary`
--

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `year_published` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`id`, `title`, `author`, `publisher`, `year_published`) VALUES
(1, 'The Great Gatsby', 'F. Scott Fitzgerald', 'Charles Scribner\'s Sons', '1925'),
(3, '1984', 'George Orwell', 'Secker & Warburg', '1949'),
(2, 'Rich Dad Poor Dad', 'Robert Kiyosaki', 'Plata Publishing', '1997'),
(4, 'la magie du rangement', 'Marie Kondo', 'First', '2011'),
(5, 'It Starts With Us', 'Colleen Hoover', 'Atria Books', '2016'),
(6, 'The Shining', 'Stephen King', 'Doubleday', '1977');

-- --------------------------------------------------------

--
-- Table structure for table `book_issues`
--

CREATE TABLE `book_issues` (
  `book_id` int(11) NOT NULL,
  `member_id` int(11) NOT NULL,
  `issue_date` date NOT NULL,
  `due_date` date NOT NULL,
  `return_date` date DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `book_issues`
--

INSERT INTO `book_issues` (`book_id`, `member_id`, `issue_date`, `due_date`, `return_date`) VALUES
(1, 1, '2022-01-01', '2022-01-15', '2023-04-25'),
(2, 2, '2022-02-01', '2022-02-15', NULL),
(3, 1, '2023-04-15', '2023-05-15', NULL),
(3, 2, '2023-04-15', '2023-05-15', NULL),
(3, 216, '2023-04-24', '2023-05-09', '2023-04-24'),
(6, 216, '2023-04-25', '2023-05-10', '2023-04-25'),
(5, 12, '2023-04-25', '2023-05-10', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE `members` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `members`
--

INSERT INTO `members` (`id`, `name`, `email`, `phone`) VALUES
(1, 'John Doe', 'john.doe@example.com', '555-1234'),
(3, 'John Martin', 'John_Martin@example.com', '783-1594'),
(4, 'Anna Harper', 'Anna_Harper@example.com', '599-5368'),
(87586, 'Omaima', 'Omaima@admin.com', '8588226'),
(6863, 'admin', 'admin@admin.com', '54386532'),
(12, 'omaima', 'omaima@admin.com', '1245789');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `book_issues`
--
ALTER TABLE `book_issues`
  ADD KEY `book_id` (`book_id`),
  ADD KEY `member_id` (`member_id`);

--
-- Indexes for table `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `books`
--
ALTER TABLE `books`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7199;
--
-- AUTO_INCREMENT for table `members`
--
ALTER TABLE `members`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=87587;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
