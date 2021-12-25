drop database collegemarket;

create database CollegeMarket;

use CollegeMarket;


create table `users`(
`id` int(11) not null auto_increment,
`firstname` varchar(50) not null,
`lastname` varchar(50) not null,
`email` varchar(191) not null unique,
`password` varchar(191) not null,
`salt` varchar(100) not null,
`dateCreated` date not null,
PRIMARY KEY (id)
);

create table `products`(
`id`  int(11) not null primary key auto_increment,
`name` varchar(50) not null,
`category` varchar(20) not null,
`description` varchar(400) not null,
`price` DECIMAL(13,2) not null,
`email` varchar(191) not null,
`dateCreated` date not null,
FOREIGN KEY (email) REFERENCES users(email)
);

create table `images`(
`id` int(11) not null primary key auto_increment,
`image` varchar(300) not null,
`productID`  int(11) not null,
`dateCreated` date not null,
FOREIGN KEY (productID) REFERENCES products(id)

);

