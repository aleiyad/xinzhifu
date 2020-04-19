-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 192.168.3.7    Database: zfb
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `zfb_order`
--

DROP TABLE IF EXISTS `zfb_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zfb_order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` varchar(255) DEFAULT NULL,
  `app_id` varchar(255) DEFAULT NULL,
  `auth_app_id` varchar(255) DEFAULT NULL,
  `auth_no` varchar(255) DEFAULT NULL,
  `charset` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_create` varchar(255) DEFAULT NULL,
  `gmt_trans` varchar(255) DEFAULT NULL,
  `is_active` int DEFAULT '0',
  `notify_id` varchar(255) DEFAULT NULL,
  `notify_time` varchar(255) DEFAULT NULL,
  `notify_type` varchar(255) DEFAULT NULL,
  `operation_id` varchar(255) DEFAULT NULL,
  `operation_type` varchar(255) DEFAULT NULL,
  `order_sts` int DEFAULT NULL,
  `out_order_no` varchar(255) DEFAULT NULL,
  `out_request_no` varchar(255) DEFAULT NULL,
  `payee_logon_id` varchar(255) DEFAULT NULL,
  `payee_user_id` varchar(255) DEFAULT NULL,
  `payer_logon_id` varchar(255) DEFAULT NULL,
  `payer_user_id` varchar(255) DEFAULT NULL,
  `rest_amount` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total_freeze_amount` varchar(255) DEFAULT NULL,
  `total_pay_amount` varchar(255) DEFAULT NULL,
  `total_unfreeze_amount` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `version` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-19 11:00:46
