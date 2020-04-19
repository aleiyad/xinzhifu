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
-- Table structure for table `zfb_qc_code_record`
--

DROP TABLE IF EXISTS `zfb_qc_code_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zfb_qc_code_record` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `alipay_operation_no` varchar(255) DEFAULT NULL,
  `alipay_order_no` varchar(255) DEFAULT NULL,
  `amount` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `credit_amount` bigint DEFAULT NULL,
  `fund_amount` bigint DEFAULT NULL,
  `gmt_create` varchar(255) DEFAULT NULL,
  `gmt_tran` varchar(255) DEFAULT NULL,
  `gmt_trans` datetime DEFAULT NULL,
  `is_active` int DEFAULT '0',
  `operation_no` varchar(255) DEFAULT NULL,
  `operation_type` varchar(255) DEFAULT NULL,
  `order_no` varchar(255) DEFAULT NULL,
  `payee_logon_id` varchar(255) DEFAULT NULL,
  `payee_user_id` varchar(255) DEFAULT NULL,
  `payer_logon_id` varchar(255) DEFAULT NULL,
  `payer_user_id` varchar(255) DEFAULT NULL,
  `pre_auth_type` varchar(255) DEFAULT NULL,
  `rest_amount` bigint DEFAULT NULL,
  `rest_credit_amount` bigint DEFAULT NULL,
  `rest_fund_amount` bigint DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total_freeze_amount` bigint DEFAULT NULL,
  `total_freeze_credit_amount` bigint DEFAULT NULL,
  `total_freeze_fund_amount` bigint DEFAULT NULL,
  `total_pay_amount` bigint DEFAULT NULL,
  `total_pay_credit_amount` bigint DEFAULT NULL,
  `total_pay_fund_amount` bigint DEFAULT NULL,
  `trans_currency` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
