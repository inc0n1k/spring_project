CREATE DATABASE  IF NOT EXISTS `spring_project` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `spring_project`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: spring_project
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,1,6,1,'Оргтехника'),(2,4,5,2,'Телевизоры'),(3,15,28,1,'Аудиотехника'),(4,7,14,1,'Комплектующие'),(5,16,21,2,'Наушники'),(6,10,11,2,'Процессоры'),(7,12,13,2,'ОЗУ'),(8,19,20,3,'С микрофоном'),(9,22,27,2,'Микрофоны'),(10,2,3,2,'Мониторы'),(11,25,26,3,'Радио микрофон'),(14,17,18,3,'Без микрофона'),(19,8,9,2,'HDD'),(21,23,24,3,'Проводной микрофон');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `options`
--

LOCK TABLES `options` WRITE;
/*!40000 ALTER TABLE `options` DISABLE KEYS */;
INSERT INTO `options` VALUES (1,6,'Тактовая частота'),(2,7,'Тайминги'),(3,19,'Объем'),(4,7,'Объем'),(5,10,'Марка'),(6,2,'Марка'),(7,10,'Диагональ'),(8,2,'Диагональ'),(9,14,'Подключение'),(10,8,'Подключение'),(11,21,'Длина кабеля'),(12,11,'Шумоподавление'),(13,21,'Шумоподавление'),(14,19,'Формат'),(15,14,'Длина кабеля');
/*!40000 ALTER TABLE `options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order_products`
--

LOCK TABLES `order_products` WRITE;
/*!40000 ALTER TABLE `order_products` DISABLE KEYS */;
INSERT INTO `order_products` VALUES (1,1,7,1),(2,1,6,2),(3,1,5,1),(4,2,3,2),(5,2,4,3),(6,3,1,2),(7,3,5,1),(8,4,4,1),(9,4,6,3);
/*!40000 ALTER TABLE `order_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,NULL,'2020-10-22 09:14:27',1,NULL,0,'Order comment...'),(2,2,'2020-10-22 09:17:49',1,NULL,0,'Text comment...'),(3,1,'2020-10-22 09:21:46',1,NULL,0,'My oorder...'),(4,3,'2020-10-28 15:44:34',1,NULL,0,'Test');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,6,'i7',45000),(3,19,'Toshiba',15000),(4,6,'i5',29700),(5,19,'Seagate',14500),(6,7,'DDR3L',45800),(7,6,'AMD Ryzen 7',47000),(8,2,'Test',777);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'admin','Администратор'),(2,'moder','Модератор'),(3,'user','Пользователь');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'qwe','$2a$10$RAJay.LnhoCFa0ZrE84RUOew0C6hvVcRPrWUt0xg6aqN5EAxX9MEe','QWE','QWE',1),(2,'asd','$2a$10$RAJay.LnhoCFa0ZrE84RUOew0C6hvVcRPrWUt0xg6aqN5EAxX9MEe','ASD','ASD',2),(3,'zxc','$2a$10$RAJay.LnhoCFa0ZrE84RUOew0C6hvVcRPrWUt0xg6aqN5EAxX9MEe','ZXC','ZXC',3);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `values`
--

LOCK TABLES `values` WRITE;
/*!40000 ALTER TABLE `values` DISABLE KEYS */;
INSERT INTO `values` VALUES (1,1,1,'3.0 GHz'),(3,3,3,'500 Gb'),(4,4,1,'2.85 Ghz'),(5,5,3,'750 Gb'),(6,6,2,'17-17-17'),(8,6,4,'16 gb'),(9,7,1,'3.1 Ghz'),(10,8,6,'Test'),(11,8,8,'Test');
/*!40000 ALTER TABLE `values` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-22 21:42:32
