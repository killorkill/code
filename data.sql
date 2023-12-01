-- test.account definition

CREATE TABLE `account` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `created_by` varchar(255) DEFAULT NULL,
                           `created_date` datetime DEFAULT NULL,
                           `modified_by` varchar(255) DEFAULT NULL,
                           `modified_date` datetime DEFAULT NULL,
                           `active` bit(1) DEFAULT NULL,
                           `address` varchar(255) DEFAULT NULL,
                           `avatar` varchar(255) DEFAULT NULL,
                           `cccd` varchar(255) DEFAULT NULL,
                           `email` varchar(255) DEFAULT NULL,
                           `fullname` varchar(255) DEFAULT NULL,
                           `keyvalidate` varchar(255) DEFAULT NULL,
                           `password` varchar(255) DEFAULT NULL,
                           `phone` varchar(255) DEFAULT NULL,
                           `username` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- account_role definition

CREATE TABLE `account_role` (
                                `id_account` int(11) NOT NULL,
                                `id_role` int(11) NOT NULL,
                                KEY `FKmr3obsyrlhd6d7sjvpweo7x96` (`id_role`),
                                KEY `FKg3mcv97dxi3nw1sh7e3hdcxq9` (`id_account`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- product definition

CREATE TABLE `product` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `created_by` varchar(255) DEFAULT NULL,
                           `created_date` datetime DEFAULT NULL,
                           `modified_by` varchar(255) DEFAULT NULL,
                           `modified_date` datetime DEFAULT NULL,
                           `active` bit(1) DEFAULT NULL,
                           `name` varchar(255) DEFAULT NULL,
                           `price` int(11) DEFAULT NULL,
                           `so_luong` int(11) DEFAULT NULL,
                           `url_image` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- product_tableorder definition

CREATE TABLE `product_tableorder` (
                                      `id` int(11) NOT NULL AUTO_INCREMENT,
                                      `number` int(11) DEFAULT NULL,
                                      `id_product` int(11) DEFAULT NULL,
                                      `id_order` int(11) DEFAULT NULL,
                                      PRIMARY KEY (`id`),
                                      KEY `FKkl9poklo8btnr9c7tfihgjd4b` (`id_product`),
                                      KEY `FKsfeq3qh5spl9lbe65943f8xy0` (`id_order`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- `role` definition

CREATE TABLE `role` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `created_by` varchar(255) DEFAULT NULL,
                        `created_date` datetime DEFAULT NULL,
                        `modified_by` varchar(255) DEFAULT NULL,
                        `modified_date` datetime DEFAULT NULL,
                        `description` varchar(255) DEFAULT NULL,
                        `name` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- tableorder definition

CREATE TABLE `tableorder` (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `active` bit(1) DEFAULT NULL,
                              `checked` bit(1) DEFAULT NULL,
                              `name` varchar(255) DEFAULT NULL,
                              `id_account` int(11) DEFAULT NULL,
                              `date` date DEFAULT NULL,
                              PRIMARY KEY (`id`),
                              KEY `FKhd0q85dodvepcl16sf6b5m7vi` (`id_account`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO account
(id, created_by, created_date, modified_by, modified_date, active, address, avatar, cccd, email, fullname, keyvalidate, password, phone, username)
VALUES(1, 'System', '2023-10-18 20:17:24.000', 'admin', '2023-10-28 18:16:32.000', 1, 'Hà Nội', NULL, NULL, 'linhkien@gmail.com', 'ADMIN', 'ADMIN', '$2a$10$FuQrgw97azGqU.2iwDHxTu4v62AnYlNsBVv5Zjcd/YY7Nze1Xfxh6', '0123456789', 'admin');


INSERT INTO account_role
(id_account, id_role)
VALUES(1, 2);

INSERT INTO product
(id, created_by, created_date, modified_by, modified_date, active, name, price, so_luong, url_image)
VALUES(4, 'admin', '2023-10-27 21:01:41.000', 'admin', '2023-10-28 17:53:15.000', 1, 'Điện Trở Băng A07 ( Trở Thanh )', 1000, 23, '1698490393830.png');
INSERT INTO product
(id, created_by, created_date, modified_by, modified_date, active, name, price, so_luong, url_image)
VALUES(2, 'admin', '2023-10-27 20:39:21.000', 'admin', '2023-10-28 17:59:54.000', 1, 'Combo 36 Module Cảm Biến Cho Arduino', 369000, 2, '1698490792894.png');
INSERT INTO product
(id, created_by, created_date, modified_by, modified_date, active, name, price, so_luong, url_image)
VALUES(3, 'admin', '2023-10-27 21:01:41.000', 'admin', '2023-10-28 17:52:18.000', 1, 'Điện trở 10k', 1000, 23, '1698490337208.png');
INSERT INTO product
(id, created_by, created_date, modified_by, modified_date, active, name, price, so_luong, url_image)
VALUES(5, 'admin', '2023-10-27 21:01:41.000', 'admin', '2023-10-28 17:54:11.000', 1, 'Trở Vạch 1/4W 5% 1K2 → 10K( 50Con )', 1500, 10, '1698490449719.png');
INSERT INTO product
(id, created_by, created_date, modified_by, modified_date, active, name, price, so_luong, url_image)
VALUES(6, 'admin', '2023-10-27 21:01:41.000', 'admin', '2023-10-28 17:55:13.000', 1, 'Trở Công Suất 10W 5%', 2990, 100, '1698490512640.png');
INSERT INTO product
(id, created_by, created_date, modified_by, modified_date, active, name, price, so_luong, url_image)
VALUES(7, 'admin', '2023-10-27 21:01:41.000', 'admin', '2023-10-28 17:56:24.000', 1, 'Cuộn Cảm Xuyến 3A LM2596', 3990, 100, '1698490582344.png');
INSERT INTO product
(id, created_by, created_date, modified_by, modified_date, active, name, price, so_luong, url_image)
VALUES(8, 'admin', '2023-10-27 21:01:41.000', 'admin', '2023-10-28 17:57:34.000', 1, 'Bo Tích Hợp 9 In 1 Tương Thích Arduino UNO R3 (BH 06 Tháng)', 145000, 10, '1698490653836.png');
INSERT INTO product
(id, created_by, created_date, modified_by, modified_date, active, name, price, so_luong, url_image)
VALUES(9, 'admin', '2023-10-27 21:01:41.000', 'admin', '2023-10-28 17:58:57.000', 1, 'KIT Arduino Pro Micro 5V/16Mhz ATmega32U4 (BH 06 Tháng)', 169000, 40, '1698490736911.png');
INSERT INTO product
(id, created_by, created_date, modified_by, modified_date, active, name, price, so_luong, url_image)
VALUES(10, 'admin', '2023-10-27 21:01:41.000', 'admin', '2023-10-28 18:00:50.000', 1, 'Màn Hình Cảm Ứng TFT 2.4 inch UNO/Mega2560 240x320 ILI9341 ( Tặng Bút Cảm Ứng )', 239000, 10, '1698490849969.png');
INSERT INTO product
(id, created_by, created_date, modified_by, modified_date, active, name, price, so_luong, url_image)
VALUES(11, 'admin', '2023-10-27 21:01:41.000', 'admin', '2023-10-28 18:01:46.000', 1, 'Module Ethernet W5500 TCP/IP Stack51/STM32 /SPI Wiznet', 119000, 10, '1698490905304.png');

INSERT INTO product_tableorder
(id, `number`, id_product, id_order)
VALUES(1, 2, 2, 1);
INSERT INTO product_tableorder
(id, `number`, id_product, id_order)
VALUES(2, 1, 3, 1);
INSERT INTO product_tableorder
(id, `number`, id_product, id_order)
VALUES(3, 1, 4, 2);
INSERT INTO product_tableorder
(id, `number`, id_product, id_order)
VALUES(4, 1, 4, 3);
INSERT INTO product_tableorder
(id, `number`, id_product, id_order)
VALUES(8, 2, 4, 7);
INSERT INTO product_tableorder
(id, `number`, id_product, id_order)
VALUES(6, 1, 4, 5);
INSERT INTO product_tableorder
(id, `number`, id_product, id_order)
VALUES(7, 1, 4, 6);
INSERT INTO product_tableorder
(id, `number`, id_product, id_order)
VALUES(9, 1, 10, 8);


INSERT INTO `role`
(id, created_by, created_date, modified_by, modified_date, description, name)
VALUES(2, 'System', '2023-10-18 20:17:24.000', 'System', '2023-10-18 20:17:24.000', 'Admin', 'Admin');
INSERT INTO `role`
(id, created_by, created_date, modified_by, modified_date, description, name)
VALUES(3, 'System', '2023-10-18 20:17:24.000', 'System', '2023-10-18 20:17:24.000', '', 'User');


INSERT INTO tableorder
(id, active, checked, name, id_account, `date`)
VALUES(1, 1, 1, 'e543183f4-56a2-43f3-97c4-d2192b24313', 1, '2023-10-27');
INSERT INTO tableorder
(id, active, checked, name, id_account, `date`)
VALUES(2, 1, 0, 'e543183f4-56a2-43f3-97c4-d2192b24314', 1, '2023-10-26');
INSERT INTO tableorder
(id, active, checked, name, id_account, `date`)
VALUES(3, 1, 0, 'e543183f4-56a2-43f3-97c4-d2192b24315', 1, '2023-10-28');
INSERT INTO tableorder
(id, active, checked, name, id_account, `date`)
VALUES(5, 1, 1, '923483f4-56a2-43f3-97c4-d2192b244975', 1, '2023-10-28');
INSERT INTO tableorder
(id, active, checked, name, id_account, `date`)
VALUES(6, 1, 0, 'bb415a1e-50e0-47b7-9764-2fe7c8cc3744', 1, '2023-10-28');
INSERT INTO tableorder
(id, active, checked, name, id_account, `date`)
VALUES(7, 1, 0, '5e27f7d4-327f-43e2-ba58-2ad3002c57f8', 1, '2023-10-28');
INSERT INTO tableorder
(id, active, checked, name, id_account, `date`)
VALUES(8, 0, 0, 'b00152f0-70db-4f99-9562-754a2ba2d04c', 1, NULL);



































