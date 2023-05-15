/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.27 : Database - library-admin
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`library-admin` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `library-admin`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `isbn` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `author` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `publisher` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `edition_number` int DEFAULT NULL,
  `publication_date` datetime DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `book` */

insert  into `book`(`isbn`,`title`,`author`,`publisher`,`edition_number`,`publication_date`,`type`) values ('1000019','政治无意识','詹姆逊',NULL,NULL,NULL,NULL),('1000020','樱桃小丸子.第2辑(电影原作特别版II)','樱桃小丸子',NULL,NULL,NULL,NULL),('1000034','生死遗言','伊能静',NULL,NULL,NULL,NULL),('1000042','女人的声音','女性',NULL,NULL,NULL,NULL),('1000076','植物化石','植物',NULL,NULL,NULL,NULL),('1000083','尼采、海德格尔与德里达','哲学',NULL,NULL,NULL,NULL),('1000093','民族国家与经济政策','韦伯',NULL,NULL,NULL,NULL),('1000140','品读世界摄影大师精品(全六册)','摄影',NULL,NULL,NULL,NULL),('1000141','价值投资','投资',NULL,NULL,NULL,NULL),('1000144','交往行为理论','哈贝马斯',NULL,NULL,NULL,NULL),('1000165','压抑的女性','心理学',NULL,NULL,NULL,NULL),('1000180','世界摄影大师・布列松','摄影',NULL,NULL,NULL,NULL),('1000199','苏东坡传、武则天正传','林语堂',NULL,NULL,NULL,NULL),('1000210','伦勃朗的人生苦旅','房龙',NULL,NULL,NULL,NULL),('1000213','迎向灵光消逝的年代','本雅明',NULL,NULL,NULL,NULL),('1000216','呐喊','鲁迅',NULL,NULL,NULL,NULL),('1000217','你属于哪种人','职场',NULL,NULL,NULL,NULL),('1000246','信息时代的战争法则','军事',NULL,NULL,NULL,NULL),('1000251','王尔德全集','王尔德',NULL,NULL,NULL,NULL),('1000280','袁氏当国','历史',NULL,NULL,NULL,NULL),('1000304','清澈的理性','科学人文',NULL,NULL,NULL,NULL),('1000323','电视人','村上春树',NULL,NULL,NULL,NULL),('1000325','人·岁月·生活（全三册）','回忆录',NULL,NULL,NULL,NULL),('1000333','中国孩子成功法','黑人音乐',NULL,NULL,NULL,NULL),('1000340','水母与蜗牛','科普',NULL,NULL,NULL,NULL),('1000348','知堂乙酉文编','周作人',NULL,NULL,NULL,NULL),('1000353','纳尼亚王国传奇（全7册）','纳尼亚',NULL,NULL,NULL,NULL),('1000355','熊掌与鱼-一位诺贝尔奖得主的精神历程','医学',NULL,NULL,NULL,NULL),('1000392','卡耐基成功学教程','励志',NULL,NULL,NULL,NULL),('1000407','竹林的故事','废名',NULL,NULL,NULL,NULL),('1000418','体验与诗','狄尔泰',NULL,NULL,NULL,NULL),('1000433','电光幻影100年','电影',NULL,NULL,NULL,NULL),('1000437','索多玛城','伊能静',NULL,NULL,NULL,NULL),('1000449','小女贼在惦记','钱海燕',NULL,NULL,NULL,NULL),('1000482','山居笔记','余秋雨',NULL,NULL,NULL,NULL),('1000517','故事新编','鲁迅',NULL,NULL,NULL,NULL),('1000523','梦的解析','心理学',NULL,NULL,NULL,NULL),('1000526','迷失在网络中的爱情','李寻欢',NULL,NULL,NULL,NULL),('1000531','第二次世界大战回忆录（全六卷）','二战',NULL,NULL,NULL,NULL),('1000534','中国少年儿童百科全书','百科全书',NULL,NULL,NULL,NULL),('1000538','数字的力量--揭示日常生活中数学的乐趣和威力','数学',NULL,NULL,NULL,NULL),('1000561','精神的力量','心理学',NULL,NULL,NULL,NULL),('1000564','一生的读书计划','读书',NULL,NULL,NULL,NULL),('1000577','马茂元说唐诗','唐诗',NULL,NULL,NULL,NULL),('1000579','嚎叫','垮掉的一代',NULL,NULL,NULL,NULL),('1000580','回忆大哥鲁迅','鲁迅',NULL,NULL,NULL,NULL);

/*Table structure for table `reader` */

DROP TABLE IF EXISTS `reader`;

CREATE TABLE `reader` (
  `reader_id` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `first_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone_number` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `limit` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `reader` */

/*Table structure for table `record` */

DROP TABLE IF EXISTS `record`;

CREATE TABLE `record` (
  `record_id` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `isbn` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `reader_id` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `borrowing_date` datetime DEFAULT NULL,
  `return_date` datetime DEFAULT NULL,
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `record` */

insert  into `record`(`record_id`,`isbn`,`reader_id`,`borrowing_date`,`return_date`) values ('412871394410500096','1000034','412448357781475328','2023-05-11 01:57:05','2023-05-15 01:57:12');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `username` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`) values ('1','test','15be54e99b5385ef3c429859a95c2479da3d28cc91e78843'),('412448357781475328','lear','29d295094903e8be0a722a1258815d496b6fe2a90d245f6b'),('412450762120105984','123','f13b72800e8c27df6d496d12a1c37244ea17544034770c26');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
