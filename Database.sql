-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 07, 2023 at 08:47 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `browser`
--

-- --------------------------------------------------------

--
-- Table structure for table `download`
--

CREATE TABLE `download` (
  `id` int(11) NOT NULL,
  `filename` text NOT NULL,
  `url` text NOT NULL,
  `Date_Down` text NOT NULL,
  `useId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `download`
--

INSERT INTO `download` (`id`, `filename`, `url`, `Date_Down`, `useId`) VALUES
(2, '0', '0', '0', 1),
(3, 'ESCIESCI%201%E8re%20ann%E9eAppro-Stock-PlanifGestion%20des%20stocks.pdfhttp:boulachinp.free.frESCIESCI%201%E8re%20ann%E9eAppro-Stock-PlanifGestion%20des%20stocks.pdf', 'http://boulachinp.free.fr/ESCI/ESCI%201%E8re%20ann%E9e/Appro-Stock-Planif/Gestion%20des%20stocks.pdfhttp://boulachinp.free.fr/ESCI/ESCI%201%E8re%20ann%E9e/Appro-Stock-Planif/Gestion%20des%20stocks.pdf', '2023/01/02 22:09:40', 1),
(4, 'ESCIESCI%201%E8re%20ann%E9eAppro-Stock-PlanifGestion%20des%20stocks.pdf', 'http://boulachinp.free.fr/ESCI/ESCI%201%E8re%20ann%E9e/Appro-Stock-Planif/Gestion%20des%20stocks.pdf', '2023/01/02 22:27:19', 1),
(5, 'ESCIESCI%201%E8re%20ann%E9eAppro-Stock-PlanifGestion%20des%20stocks.pdf', 'http://boulachinp.free.fr/ESCI/ESCI%201%E8re%20ann%E9e/Appro-Stock-Planif/Gestion%20des%20stocks.pdf', '2023/01/03 00:38:45', 1),
(6, '~gmg24000pdflect_06.pdf', 'http://www.columbia.edu/~gmg2/4000/pdf/lect_06.pdf', '2023/01/03 00:39:23', 1),
(7, '~gmg24000pdflect_06.pdf', 'http://www.columbia.edu/~gmg2/4000/pdf/lect_06.pdf', '2023/01/03 14:04:31', 1),
(8, 'portailmediasdocumentsenseignement1539107757561.pdf', 'https://www.esen.tn/portail/medias/documents/enseignement/1539107757561.pdf', '2023/01/05 19:07:30', 1),
(9, 'sitesdefaultfilesatomsfiles9782100743131Feuilletage.pdf', 'https://www.dunod.com/sites/default/files/atoms/files/9782100743131/Feuilletage.pdf', '2023/01/05 19:07:54', 1),
(10, 'coursbenchekrounIntro-generale-marketing.pdf', 'http://fsjes.usmba.ac.ma/cours/benchekroun/Intro-generale-marketing.pdf', '2023/01/05 21:40:39', 1),
(11, '?gws_rd=ssl.pdf', 'https://www.google.com/?gws_rd=ssl.pdf', '2023/01/07 13:30:19', 1),
(12, 'coursbenchekrounIntro-generale-marketing.pdf', 'http://fsjes.usmba.ac.ma/cours/benchekroun/Intro-generale-marketing.pdf', '2023/01/07 13:31:07', 1);

-- --------------------------------------------------------

--
-- Table structure for table `history`
--

CREATE TABLE `history` (
  `id` int(11) NOT NULL,
  `url` text NOT NULL,
  `date` text NOT NULL,
  `user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `history`
--

INSERT INTO `history` (`id`, `url`, `date`, `user`) VALUES
(1, 'https://www.google.com/?gws_rd=ssl', '31-12-2022', 1),
(48, 'https://www.google.com/search?q=facebook', '31-12-2022', 1),
(49, 'https://www.google.com/search?q=avito', '31-12-2022', 1),
(50, 'https://www.google.com/search?q=facebook&source=hp&ei=q1ewY57FOvybhbIP5Ni66A8&iflsig=AJiK0e8AAAAAY7BlvMQRhcmVzXmdnhLQT_I9OgrTJvmY&gs_ssp=eJzj4tLP1TfIyK1MKy5TYDRgdGDw4khLTE5Nys_PBgBmYAfL&oq=fa&gs_lcp=Cgdnd3Mtd2l6EAEYADIRCC4QgAQQsQMQgwEQxwEQ0QMyCwgAEIAEELEDEIMBMggIABCxAxCDATILCAAQgAQQsQMQgwEyCwgAEIAEELEDEIMBMgsIABCABBCxAxCDATIICC4QsQMQgwEyCwgAEIAEELEDEIMBMgsIABCABBCxAxCDATILCAAQgAQQsQMQgwE6EQguEIMBEMcBELEDENEDEIAEOgsILhCABBCxAxCDAToFCAAQgAQ6BQguEIAEUB1YigFgswZoAXAAeACAAYYBiAH-AZIBAzAuMpgBAKABAbABAA&sclient=gws-wiz', '31-12-2022', 1),
(51, 'https://m.facebook.com/?locale=fr_FR', '31-12-2022', 1),
(52, 'https://www.google.com/?gws_rd=ssl', '31-12-2022', 1),
(55, 'https://www.google.com/search?q=friv&ei=kFiwY-mkIaiT0PEP9PGFiAY&oq=fri&gs_lcp=Cgxnd3Mtd2l6LXNlcnAQAxgAMgoIABCxAxCDARBDMgoIABCxAxCDARBDMg0ILhCxAxCDARDUAhBDMgoIABCxAxCDARBDMgoIABCxAxCDARBDMgoILhCxAxCDARBDMgcILhDUAhBDMgoIABCxAxCDARBDMgQIABBDMgcIABCxAxBDOgwIABAHEB4Q8QQQsAM6DggAEIAEELEDEIMBELADOggIABCABBCwAzoQCC4QgwEQxwEQsQMQ0QMQQzoICAAQsQMQgwE6CwguEIAEELEDEIMBOgsIABCABBCxAxCDAToRCC4QgAQQsQMQgwEQxwEQ0QM6BQgAEIAEOgcILhCxAxBDOhMILhCxAxCDARDHARDRAxDUAhBDOgQILhBDOhAILhCxAxCDARDHARDRAxBDSgQIQRgBSgQIRhgAUM4KWKcPYP0baANwAHgAgAGOAYgBgwOSAQMwLjOYAQCgAQHIAQrAAQE&sclient=gws-wiz-serp', '31-12-2022', 1),
(59, 'https://www.google.com/?gws_rd=ssl', '31-12-2022', 1),
(63, 'https://www.google.com/search?q=face', '31-12-2022', 1),
(64, 'https://www.larousse.fr/dictionnaires/francais/face/32561', '31-12-2022', 1),
(65, 'https://www.google.com/search?q=face', '31-12-2022', 1),
(73, 'http://boulachinp.free.fr/ESCI/ESCI%201%E8re%20ann%E9e/Appro-Stock-Planif/Gestion%20des%20stocks.pdf', '02-01-2023', 1),
(74, 'http://boulachinp.free.fr/ESCI/ESCI%201%E8re%20ann%E9e/Appro-Stock-Planif/Gestion%20des%20stocks.pdf', '02-01-2023', 1),
(75, 'http://boulachinp.free.fr/ESCI/ESCI%201%E8re%20ann%E9e/Appro-Stock-Planif/Gestion%20des%20stocks.pdf', '02-01-2023', 1),
(76, 'http://boulachinp.free.fr/ESCI/ESCI%201%E8re%20ann%E9e/Appro-Stock-Planif/Gestion%20des%20stocks.pdf', '02-01-2023', 1),
(77, 'http://boulachinp.free.fr/ESCI/ESCI%201%E8re%20ann%E9e/Appro-Stock-Planif/Gestion%20des%20stocks.pdf', '02-01-2023', 1),
(78, 'http://boulachinp.free.fr/ESCI/ESCI%201%E8re%20ann%E9e/Appro-Stock-Planif/Gestion%20des%20stocks.pdf', '02-01-2023', 1),
(79, 'http://boulachinp.free.fr/ESCI/ESCI%201%E8re%20ann%E9e/Appro-Stock-Planif/Gestion%20des%20stocks.pdf', '02-01-2023', 1),
(80, 'http://boulachinp.free.fr/ESCI/ESCI%201%E8re%20ann%E9e/Appro-Stock-Planif/Gestion%20des%20stocks.pdf', '02-01-2023', 1),
(81, 'http://boulachinp.free.fr/ESCI/ESCI%201%E8re%20ann%E9e/Appro-Stock-Planif/Gestion%20des%20stocks.pdf', '02-01-2023', 1),
(82, 'http://boulachinp.free.fr/ESCI/ESCI%201%E8re%20ann%E9e/Appro-Stock-Planif/Gestion%20des%20stocks.pdf', '02-01-2023', 1),
(83, 'http://boulachinp.free.fr/ESCI/ESCI%201%E8re%20ann%E9e/Appro-Stock-Planif/Gestion%20des%20stocks.pdfhttp://boulachinp.free.fr/ESCI/ESCI%201%E8re%20ann%E9e/Appro-Stock-Planif/Gestion%20des%20stocks.pdf', '02-01-2023', 1),
(84, 'http://boulachinp.free.fr/ESCI/ESCI%201%E8re%20ann%E9e/Appro-Stock-Planif/Gestion%20des%20stocks.pdfhttp://boulachinp.free.fr/ESCI/ESCI%201%E8re%20ann%E9e/Appro-Stock-Planif/Gestion%20des%20stocks.pdf', '02-01-2023', 1),
(85, 'http://boulachinp.free.fr/ESCI/ESCI%201%E8re%20ann%E9e/Appro-Stock-Planif/Gestion%20des%20stocks.pdf', '02-01-2023', 1),
(86, 'https://www.google.com/search?q=maraoui', '02-01-2023', 1),
(87, 'https://www.google.com/search?q=maraoui', '02-01-2023', 1),
(88, 'https://www.google.com/search?q=maraoui', '03-01-2023', 1),
(89, 'https://www.google.com/search?q=facebook', '03-01-2023', 1),
(90, 'https://www.google.com/search?q=maraoui', '03-01-2023', 1),
(91, 'https://www.google.com/search?q=avito', '03-01-2023', 1),
(92, 'https://www.avito.ma/fr/maroc/%C3%A0_vendre', '03-01-2023', 1),
(93, 'https://www.google.com/?gws_rd=ssl.pdf', '03-01-2023', 1),
(94, 'https://www.google.com/?gws_rd=ssl.pdf', '03-01-2023', 1),
(95, 'http://boulachinp.free.fr/ESCI/ESCI%201%E8re%20ann%E9e/Appro-Stock-Planif/Gestion%20des%20stocks.pdf', '03-01-2023', 1),
(96, 'https://www.google.com/search?q=maroc', '03-01-2023', 1),
(97, 'https://www.google.com/search?q=maroc', '03-01-2023', 1),
(98, 'https://fr.wikipedia.org/wiki/Maroc', '03-01-2023', 1),
(99, 'https://fr.wikipedia.org/wiki/Maroc', '03-01-2023', 1),
(100, 'https://fr.wikipedia.org/wiki/Maroc', '03-01-2023', 1),
(101, 'https://fr.wikipedia.org/wiki/Maroc', '03-01-2023', 1),
(102, 'https://fr.wikipedia.org/wiki/Maroc', '03-01-2023', 1),
(103, 'http://boulachinp.free.fr/ESCI/ESCI%201%E8re%20ann%E9e/Appro-Stock-Planif/Gestion%20des%20stocks.pdf', '03-01-2023', 1),
(104, 'http://www.columbia.edu/~gmg2/4000/pdf/lect_06.pdf', '03-01-2023', 1),
(105, 'https://www.google.com/search?q=google', '03-01-2023', 1),
(106, 'https://www.google.com/search?q=avito&source=lnms&tbm=nws&sa=X&ved=2ahUKEwj5ivS2grH8AhXFb94KHbNYBTsQ_AUoAXoECAEQAw', '05-01-2023', 1),
(107, 'https://www.google.com/?gws_rd=ssl.pdf', '05-01-2023', 1),
(108, 'https://www.google.com/?gws_rd=ssl.pdf', '05-01-2023', 1),
(109, 'https://fr.wikipedia.org/wiki/Maroc', '05-01-2023', 1),
(110, 'https://www.google.com/search?q=facebook', '05-01-2023', 1),
(111, 'https://www.google.com/search?q=facebook', '05-01-2023', 1),
(112, 'https://www.google.com/search?q=marketing+pdf&ei=hjW3Y7HqLvWMjuMP1IWcmAM&ved=0ahUKEwixxf7jpLH8AhV1hmMGHdQCBzMQ4dUDCA4&uact=5&oq=marketing+pdf&gs_lcp=Cgxnd3Mtd2l6LXNlcnAQAzIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBggAEBYQHjIGCAAQFhAeMgYIABAWEB46CwgAEIAEELEDEIMBOgQILhBDOgoIABCxAxCDARBDOgQIABBDOgoILhDHARDRAxBDOgYIABAKEEM6CwguEIAEELEDENQCOgoILhCxAxCDARBDOhAILhCxAxCDARDHARDRAxBDOggIABCABBCxAzoHCC4QsQMQQzoNCAAQgAQQsQMQgwEQCjoHCAAQgAQQCkoECEEYAEoECEYYAFASWPRQYNRSaAVwAXgDgAH1CIgB1UiSAQ8wLjMuMS4yLjEuMi4zLjOYAQCgAQHAAQE&sclient=gws-wiz-serp', '05-01-2023', 1),
(113, 'https://www.google.com/webhp?hl=fr&sa=X&ved=0ahUKEwibu63upLH8AhWS1zgGHWWoAHQQPAgI', '05-01-2023', 1),
(114, 'https://www.google.com/search?q=facebook', '05-01-2023', 1),
(115, 'https://www.google.com/search?q=avito', '05-01-2023', 1),
(116, 'https://www.google.com/search?q=avito', '05-01-2023', 1),
(117, 'https://www.google.com/?gws_rd=ssl.pdf', '07-01-2023', 1),
(118, 'https://www.google.com/?gws_rd=ssl.pdf', '07-01-2023', 1),
(119, 'https://www.google.com/search?q=marketing pdf', '07-01-2023', 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `Full_name` text NOT NULL,
  `Email` text NOT NULL,
  `Password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `Full_name`, `Email`, `Password`) VALUES
(1, 'Maraoui Yassine', 'yassinelmaraoui76@gmail.com', '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `download`
--
ALTER TABLE `download`
  ADD PRIMARY KEY (`id`),
  ADD KEY `UserId` (`useId`);

--
-- Indexes for table `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `history` (`user`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `download`
--
ALTER TABLE `download`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `history`
--
ALTER TABLE `history`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=120;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `download`
--
ALTER TABLE `download`
  ADD CONSTRAINT `UserId` FOREIGN KEY (`useId`) REFERENCES `users` (`id`);

--
-- Constraints for table `history`
--
ALTER TABLE `history`
  ADD CONSTRAINT `history` FOREIGN KEY (`user`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
