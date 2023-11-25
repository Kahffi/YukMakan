-- yukmakan.akun definition

CREATE TABLE `akun` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `phoneNum` varchar(30) NOT NULL,
  `email` varchar(100) NOT NULL,
  `role` varchar(5) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- yukmakan.campaign definition

CREATE TABLE `campaign` (
  `id` varchar(40) NOT NULL,
  `admin_username` varchar(50) DEFAULT NULL,
  `judul` varchar(255) DEFAULT NULL,
  `deskripsi` text,
  `targetDonasi` int DEFAULT NULL,
  `currentDonasi` int DEFAULT NULL,
  `imagePath` varchar(100) DEFAULT NULL,
  `tanggal` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `admin_username` (`admin_username`),
  CONSTRAINT `campaign_ibfk_1` FOREIGN KEY (`admin_username`) REFERENCES `akun` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- yukmakan.kontenedukasi definition

CREATE TABLE `kontenedukasi` (
  `id` varchar(40) NOT NULL,
  `admin_username` varchar(50) DEFAULT NULL,
  `judul` varchar(255) DEFAULT NULL,
  `konten` text,
  `tanggal` varchar(30) DEFAULT NULL,
  `imagePath` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `admin_username` (`admin_username`),
  CONSTRAINT `kontenedukasi_ibfk_1` FOREIGN KEY (`admin_username`) REFERENCES `akun` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- yukmakan.resep definition

CREATE TABLE `resep` (
  `id` varchar(40) NOT NULL,
  `admin_username` varchar(50) DEFAULT NULL,
  `judul` varchar(255) DEFAULT NULL,
  `datePosted` varchar(30) DEFAULT NULL,
  `deskripsi` text,
  `langkah` text,
  `bahan` text,
  `kandunganGizi` text,
  `imagePath` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `admin_username` (`admin_username`),
  CONSTRAINT `resep_ibfk_1` FOREIGN KEY (`admin_username`) REFERENCES `akun` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- yukmakan.riwayatdonasi definition

CREATE TABLE `riwayatdonasi` (
  `id` varchar(40) NOT NULL,
  `campaign_id` varchar(40) DEFAULT NULL,
  `user_username` varchar(100) DEFAULT NULL,
  `nominal` int DEFAULT NULL,
  `tanggal` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `campaign_id` (`campaign_id`),
  KEY `user_username` (`user_username`),
  CONSTRAINT `riwayatdonasi_ibfk_1` FOREIGN KEY (`campaign_id`) REFERENCES `campaign` (`id`),
  CONSTRAINT `riwayatdonasi_ibfk_2` FOREIGN KEY (`user_username`) REFERENCES `akun` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- yukmakan.ulasan definition

CREATE TABLE `ulasan` (
  `id` varchar(40) NOT NULL,
  `resep_id` varchar(40) DEFAULT NULL,
  `user_username` varchar(100) DEFAULT NULL,
  `isi` varchar(1000) DEFAULT NULL,
  `tanggal` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `resep_id` (`resep_id`),
  KEY `user_username` (`user_username`),
  CONSTRAINT `ulasan_ibfk_1` FOREIGN KEY (`resep_id`) REFERENCES `resep` (`id`),
  CONSTRAINT `ulasan_ibfk_2` FOREIGN KEY (`user_username`) REFERENCES `akun` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- yukmakan.daftarfavorit definition

CREATE TABLE `daftarfavorit` (
  `user_username` varchar(50) DEFAULT NULL,
  `id_resep` varchar(40) DEFAULT NULL,
  KEY `user_username` (`user_username`),
  KEY `id_resep` (`id_resep`),
  CONSTRAINT `daftarfavorit_ibfk_1` FOREIGN KEY (`user_username`) REFERENCES `akun` (`username`),
  CONSTRAINT `daftarfavorit_ibfk_2` FOREIGN KEY (`id_resep`) REFERENCES `resep` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;