drop database if exists music_store_recommendations;
create database music_store_recommendations;
use music_store_recommendations;
create table label_recommendation (
    label_recommendation_id int primary key auto_increment,
    label_id int not null,
    user_id int not null,
    liked bool not null
);
insert into label_recommendation
values (1, 4, 5, true),
(2, 6, 8, false),
(3, 12, 32, true);
create table artist_recommendation (
    artist_recommendation_id int primary key auto_increment,
    artist_id int not null,
    user_id int not null,
    liked bool not null
);
insert into artist_recommendation
values (1, 45, 56, true),
(2, 11, 54, false),
(3, 12, 12, true);
create table album_recommendation (
    album_recommendation_id int primary key auto_increment,
    album_id int not null,
    user_id int not null,
    liked bool not null
);
insert into album_recommendation
values (1, 42, 25, true),
(2, 16, 18, false),
(3, 22, 12, true);
create table track_recommendation (
    track_recommendation_id int primary key auto_increment,
    track_id int not null,
    user_id int not null,
    liked bool not null
);
insert into track_recommendation
values (1, 41, 51, true),
(2, 61, 81, false),
(3, 2, 3, true);