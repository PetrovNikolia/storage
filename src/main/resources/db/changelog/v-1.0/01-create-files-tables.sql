    CREATE TABLE `files` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `data` longblob,
                             `name` varchar(255) DEFAULT NULL,
                             `size` bigint(20) DEFAULT NULL,
                             `time` varchar(255) DEFAULT NULL,
                             `type` varchar(255) DEFAULT NULL,
                             PRIMARY KEY (`id`)
    ) ENGINE=InnoDB
GO