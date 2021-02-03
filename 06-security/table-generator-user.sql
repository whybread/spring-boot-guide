CREATE TABLE USER_TB (
  id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  username VARCHAR(30) UNIQUE,
  email VARCHAR(255) UNIQUE,
  password_encrypted VARCHAR(255),
  role VARCHAR(20) DEFAULT 'ROLE_USER',
  is_deleted TINYINT(1) NOT NULL DEFAULT 0,
  created_datetime DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_datetime DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  description VARCHAR(500),
  PRIMARY KEY (id)
)

DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_unicode_ci;


INSERT INTO USER_TB (username, email, role) VALUE ('admin', 'admin@email.com', 'ROLE_ADMIN');
INSERT INTO USER_TB (username, email, role) VALUE ('test', 'test@email.com', 'ROLE_USER');
