drop database collegemarket;

create database CollegeMarket;

use CollegeMarket;

create table `users`(
`id` int(11) not null auto_increment,
`firstname` varchar(35) not null,
`lastname` varchar(35) not null,
`email` varchar(80) not null unique,
`password` varchar(70) not null,
`dateCreated` datetime not null,
PRIMARY KEY (id)
);

create table `products`(
`id`  int(11) not null primary key auto_increment,
`type` varchar(20) not null,
`description` varchar(200) not null,
`price` DECIMAL(13,2) not null,
`email` varchar(80) not null,
`dateCreated` datetime not null,
FOREIGN KEY (email) REFERENCES users(email)
);

create table `images`(
`id` int(11) not null,
`email` varchar(80) not null,
`image` longblob not null,
FOREIGN KEY (id) REFERENCES products(id)
);
