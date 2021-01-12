SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for 'test-people'
-- ----------------------------
DROP TABLE IF EXISTS `test-people`;
CREATE TABLE `test-people` (
  `seq` int(255) NOT NULL AUTO_INCREMENT COMMENT 'Sequence',
  `name` varchar(255) NOT NULL COMMENT 'User Name',
  `country` varchar(255) NOT NULL COMMENT 'User Nationality',
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of 'test-people'
-- ----------------------------
BEGIN;
INSERT INTO `test-people` VALUES (1, '김철수', '한국');
INSERT INTO `test-people` VALUES (2, '이영희', '미국');
INSERT INTO `test-people` VALUES (3, '박촌놈', '영국');
INSERT INTO `test-people` VALUES (4, '최도시', '한국');
INSERT INTO `test-people` VALUES (5, '정서울', '미국');
INSERT INTO `test-people` VALUES (6, '윤파주', '영국');
INSERT INTO `test-people` VALUES (7, '권강릉', '일본');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
