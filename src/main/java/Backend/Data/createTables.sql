drop database collegemarket;

create database CollegeMarket;

use CollegeMarket;

create table `users`(
`id` int(11) not null auto_increment,
`firstname` varchar(35) not null,
`lastname` varchar(35) not null,
`email` varchar(80) not null unique,
`password` varchar(70) not null,
`dateCreated` date not null,
PRIMARY KEY (id)
);

create table `products`(
`id`  int(11) not null primary key auto_increment,
`name` varchar(40) not null,
`type` varchar(20) not null,
`description` varchar(200) not null,
`price` DECIMAL(13,2) not null,
`email` varchar(80) not null,
`dateCreated` date not null,
FOREIGN KEY (email) REFERENCES users(email)
);

create table `images`(
`id` int(11) not null primary key auto_increment,
`image` varchar(80) not null,
`productID`  int(11) not null,
`dateCreated` date not null,
FOREIGN KEY (productID) REFERENCES products(id)

);
