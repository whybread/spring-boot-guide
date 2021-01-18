SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for 'foreign-users'
-- ----------------------------
DROP TABLE IF EXISTS `foreign-users`;
CREATE TABLE `foreign-users` (
  `seq` int(255) NOT NULL AUTO_INCREMENT COMMENT 'Sequence',
  `name` varchar(255) NOT NULL COMMENT 'User Name',
  `country` varchar(255) NOT NULL COMMENT 'User Nationality',
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of 'foreign-users'
-- ----------------------------
BEGIN;
INSERT INTO `foreign-users` VALUES (1, 'James', 'England');
INSERT INTO `foreign-users` VALUES (2, 'Adele', 'China');
INSERT INTO `foreign-users` VALUES (3, 'Maple', 'Japan');
INSERT INTO `foreign-users` VALUES (4, 'Karis', 'USA');
INSERT INTO `foreign-users` VALUES (5, 'Nuguri', 'USA');
INSERT INTO `foreign-users` VALUES (6, 'Faker', 'Japan');
INSERT INTO `foreign-users` VALUES (7, 'Kiin', 'England');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
