CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_authorities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authority` varchar(50) NOT NULL,
  `userId` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_uniq_user_auth` (`userId`,`authority`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
  `userId` varchar(32) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `email` (`email`) USING BTREE,
  UNIQUE KEY `phone` (`phone`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#----------
INSERT INTO `test`.`users` (`userId`, `password`, `email`, `name`, `enabled`) VALUES ('2', '96e79218965eb72c92a549dd5a330112', 'test@163.com', 'sky', '1');

INSERT INTO `test`.`user_authorities` (`authority`, `userId`) VALUES ('ROLE_USER', '1');
INSERT INTO `test`.`user_authorities` (`authority`, `userId`) VALUES ('ROLE_ADMIN', '1');
