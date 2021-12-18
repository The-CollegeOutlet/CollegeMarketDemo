use collegemarket;
call GetAllImages();
call GetAllProducts();


call AddUser(@id,'admin','test', 'admin@test.com','tester', CURDATE());
call AddUser(@id1,'garrett','stouffer', 'garrybear@test.com','DRAKE IS THE GOAT', CURDATE());
call AddUser(@id2,'Kendra','Mattingly', 'kendra@test.com','YOURMOMA', CURDATE());
call AddUser(@id3,'Rachel','Hero', 'hero@test.com','fake', CURDATE());
call AddUser(@id4,'super','man', 'superman@test.com','please', CURDATE());

call GetAllUsers;

