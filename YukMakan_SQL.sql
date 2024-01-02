create table yukmakan.akun
(
    username       varchar(50) collate latin1_general_cs not null
        primary key,
    password       varchar(50) collate latin1_general_cs not null,
    nama           varchar(100)                          not null,
    phoneNum       varchar(30)                           not null,
    email          varchar(100)                          not null,
    role           varchar(5)                            not null,
    profilePicture longblob                              null,
    constraint email
        unique (email),
    constraint username
        unique (username)
);

create table yukmakan.campaign
(
    id             varchar(40)                           not null
        primary key,
    admin_username varchar(50) collate latin1_general_cs null,
    judul          varchar(255)                          null,
    deskripsi      text                                  null,
    targetDonasi   int                                   null,
    currentDonasi  int                                   null,
    imagePath      varchar(100)                          null,
    tanggal        varchar(30)                           null,
    constraint campaign_ibfk_1
        foreign key (admin_username) references yukmakan.akun (username)
);

create index admin_username
    on yukmakan.campaign (admin_username);

create table yukmakan.kontenedukasi
(
    id             varchar(40)                           not null
        primary key,
    admin_username varchar(50) collate latin1_general_cs null,
    judul          varchar(255)                          null,
    konten         text                                  null,
    tanggal        varchar(30)                           null,
    imagePath      longblob                              null,
    constraint kontenedukasi_ibfk_1
        foreign key (admin_username) references yukmakan.akun (username)
);

create index admin_username
    on yukmakan.kontenedukasi (admin_username);

create table yukmakan.resep
(
    id             varchar(40)                           not null
        primary key,
    admin_username varchar(50) collate latin1_general_cs null,
    judul          varchar(255)                          null,
    datePosted     varchar(30)                           null,
    deskripsi      text                                  null,
    langkah        text                                  null,
    bahan          text                                  null,
    kandunganGizi  text                                  null,
    imageResep     longblob                              null,
    constraint resep_ibfk_1
        foreign key (admin_username) references yukmakan.akun (username)
);

create table yukmakan.daftarfavorit
(
    user_username varchar(50) collate latin1_general_cs null,
    id_resep      varchar(40)                           null,
    constraint id_resep
        unique (id_resep),
    constraint user_username
        unique (user_username),
    constraint daftarfavorit_ibfk_1
        foreign key (user_username) references yukmakan.akun (username),
    constraint daftarfavorit_ibfk_2
        foreign key (id_resep) references yukmakan.resep (id)
);

create index admin_username
    on yukmakan.resep (admin_username);

create table yukmakan.riwayatdonasi
(
    id            varchar(40)                            not null
        primary key,
    campaign_id   varchar(40)                            null,
    user_username varchar(100) collate latin1_general_cs null,
    nominal       int                                    null,
    tanggal       varchar(30)                            null,
    constraint riwayatdonasi_ibfk_1
        foreign key (campaign_id) references yukmakan.campaign (id),
    constraint riwayatdonasi_ibfk_2
        foreign key (user_username) references yukmakan.akun (username)
);

create index campaign_id
    on yukmakan.riwayatdonasi (campaign_id);

create index user_username
    on yukmakan.riwayatdonasi (user_username);

create table yukmakan.ulasan
(
    id            varchar(40)                            not null
        primary key,
    resep_id      varchar(40)                            not null,
    user_username varchar(100) collate latin1_general_cs not null,
    isi           text                                   not null,
    tanggal       varchar(30)                            not null,
    rating        int                                    not null,
    constraint ulasan_ibfk_1
        foreign key (resep_id) references yukmakan.resep (id),
    constraint ulasan_ibfk_2
        foreign key (user_username) references yukmakan.akun (username)
);

create index resep_id
    on yukmakan.ulasan (resep_id);

create index user_username
    on yukmakan.ulasan (user_username);

