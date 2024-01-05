-- yukmakan.akun definition

CREATE TABLE `akun` (
  `username` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `password` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `nama` varchar(100) NOT NULL,
  `phoneNum` varchar(30) NOT NULL,
  `email` varchar(100) NOT NULL,
  `role` varchar(5) NOT NULL,
  `profilePicture` longblob,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- yukmakan.bank definition

CREATE TABLE `bank` (
  `id` varchar(40) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `nama_bank` varchar(100) NOT NULL,
  `logo_bank` longblob NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- yukmakan.campaign definition

CREATE TABLE `campaign` (
  `id` varchar(40) NOT NULL,
  `admin_username` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `judul` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `deskripsi` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `targetDonasi` int NOT NULL,
  `currentDonasi` int NOT NULL,
  `imagePath` longblob NOT NULL,
  `tanggal` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `end_date` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `admin_username` (`admin_username`),
  CONSTRAINT `campaign_ibfk_1` FOREIGN KEY (`admin_username`) REFERENCES `akun` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- yukmakan.kontenedukasi definition

CREATE TABLE `kontenedukasi` (
  `id` varchar(40) NOT NULL,
  `admin_username` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_cs DEFAULT NULL,
  `judul` varchar(255) DEFAULT NULL,
  `konten` text,
  `tanggal` varchar(30) DEFAULT NULL,
  `imagePath` longblob,
  PRIMARY KEY (`id`),
  KEY `admin_username` (`admin_username`),
  CONSTRAINT `kontenedukasi_ibfk_1` FOREIGN KEY (`admin_username`) REFERENCES `akun` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- yukmakan.metode_pembayaran definition

CREATE TABLE `metode_pembayaran` (
  `id` varchar(40) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `bank_id` varchar(40) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `atas_nama` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `nomor_rekening` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `metode_pembayaran_FK` (`bank_id`),
  CONSTRAINT `metode_pembayaran_FK` FOREIGN KEY (`bank_id`) REFERENCES `bank` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- yukmakan.resep definition

CREATE TABLE `resep` (
  `id` varchar(40) NOT NULL,
  `admin_username` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_cs DEFAULT NULL,
  `judul` varchar(255) DEFAULT NULL,
  `datePosted` varchar(30) DEFAULT NULL,
  `deskripsi` text,
  `langkah` text,
  `bahan` text,
  `kandunganGizi` text,
  `imageResep` longblob,
  PRIMARY KEY (`id`),
  KEY `admin_username` (`admin_username`),
  CONSTRAINT `resep_ibfk_1` FOREIGN KEY (`admin_username`) REFERENCES `akun` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- yukmakan.riwayatdonasi definition

CREATE TABLE `riwayatdonasi` (
  `id` varchar(40) NOT NULL,
  `campaign_id` varchar(40) DEFAULT NULL,
  `user_username` varchar(100) CHARACTER SET latin1 COLLATE latin1_general_cs DEFAULT NULL,
  `nominal` int DEFAULT NULL,
  `tanggal` varchar(30) DEFAULT NULL,
  `metode_pembayaran_id` varchar(40) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  PRIMARY KEY (`id`),
  KEY `campaign_id` (`campaign_id`),
  KEY `user_username` (`user_username`),
  KEY `riwayatdonasi_FK` (`metode_pembayaran_id`),
  CONSTRAINT `riwayatdonasi_FK` FOREIGN KEY (`metode_pembayaran_id`) REFERENCES `metode_pembayaran` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `riwayatdonasi_FK_1` FOREIGN KEY (`user_username`) REFERENCES `akun` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `riwayatdonasi_FK_2` FOREIGN KEY (`campaign_id`) REFERENCES `campaign` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- yukmakan.ulasan definition

CREATE TABLE `ulasan` (
  `id` varchar(40) NOT NULL,
  `resep_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_username` varchar(100) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `isi` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tanggal` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `rating` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `resep_id` (`resep_id`),
  KEY `user_username` (`user_username`),
  CONSTRAINT `ulasan_ibfk_1` FOREIGN KEY (`resep_id`) REFERENCES `resep` (`id`),
  CONSTRAINT `ulasan_ibfk_2` FOREIGN KEY (`user_username`) REFERENCES `akun` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- yukmakan.available_metode_pembayaran definition

CREATE TABLE `available_metode_pembayaran` (
  `id` varchar(40) NOT NULL,
  `campaign_id` varchar(40) NOT NULL,
  `metode_pembayaran_id` varchar(40) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  KEY `available_metode_pembayaran_FK` (`campaign_id`),
  KEY `available_metode_pembayaran_FK_1` (`metode_pembayaran_id`),
  CONSTRAINT `available_metode_pembayaran_FK` FOREIGN KEY (`campaign_id`) REFERENCES `campaign` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `available_metode_pembayaran_FK_1` FOREIGN KEY (`metode_pembayaran_id`) REFERENCES `metode_pembayaran` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- yukmakan.daftarfavorit definition

CREATE TABLE `daftarfavorit` (
  `user_username` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_cs DEFAULT NULL,
  `resep_id` varchar(40) DEFAULT NULL,
  KEY `daftarfavorit_FK` (`user_username`),
  KEY `daftarfavorit_FK_1` (`resep_id`),
  CONSTRAINT `daftarfavorit_FK` FOREIGN KEY (`user_username`) REFERENCES `akun` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `daftarfavorit_FK_1` FOREIGN KEY (`resep_id`) REFERENCES `resep` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;