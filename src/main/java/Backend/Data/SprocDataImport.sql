use collegemarket;


call AddUser(@id,'admin','test', 'admin@test.com','tester', current_timestamp());
call AddUser(@id1,'garrett','stouffer', 'garrybear@test.com','DRAKE IS THE GOAT', current_timestamp());
call AddUser(@id2,'Kendra','Mattingly', 'kendra@test.com','YOURMOMA', current_timestamp());
call AddUser(@id3,'Rachel','Hero', 'hero@test.com','fake', current_timestamp());
call AddUser(@id4,'super','man', 'superman@test.com','please', current_timestamp());

use collegemarket;
call GetAllUsers;


call AddProducts(@_id,'CLOTHING', 'THIS IS A BLACK DRESS', 20.50, 'kendra@test.com',current_timestamp());
call AddProducts(@_id1,'BOOKS', 'THE BIBLE', 100.50, 'garrybear@test.com',current_timestamp());
call AddProducts(@_id2,'SHOES', 'Jordans', 55.50, 'hero@test.com',current_timestamp());
call AddProducts(@_id3,'GLASSWARE', 'Wine glasses', 15.70, 'superman@test.com',current_timestamp());
call AddProducts(@_id4,'FURNITURE', 'Brown couch', 11.50, 'admin@test.com',current_timestamp());
use collegemarket;
call AddImages(1,'kendra@test.com', LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\icon1.png'));
call GetAllImages();
use collegemarket;
call AddProducts(@_id8,'CLOTHING', 'A Sexy BLACK DRESS', 50.50, 'kendra@test.com',current_timestamp());
call GetAllUserProducts('kendra@test.com');
call GetAllProducts();

call GetUserForProduct(1);
call GetUserForProduct(3);


call GetAllImages();
call AddImages(1,'kendra@test.com', LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\blackdress.jpg'));
call AddImages(3,'hero@test.com', LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\jordans.jpg'));
call AddImages(3,'superman@test.com', LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\wineglass.jpg'));

