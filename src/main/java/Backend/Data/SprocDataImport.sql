use collegemarket;


call AddUser(@id,'admin','test', 'admin@test.com','tester', CURDATE());
call AddUser(@id1,'garrett','stouffer', 'garrybear@test.com','DRAKE IS THE GOAT', CURDATE());
call AddUser(@id2,'Kendra','Mattingly', 'kendra@test.com','YOURMOMA', CURDATE());
call AddUser(@id3,'Rachel','Hero', 'hero@test.com','fake', CURDATE());
call AddUser(@id4,'super','man', 'superman@test.com','please', CURDATE());

call GetAllUsers;


call AddProducts(@n3,'dress','CLOTHING', 'THIS IS A BLACK DRESS', 20.50, 'kendra@test.com',CURDATE());
call AddProducts(@n4,'King James Version','BOOKS', 'THE BIBLE', 100.50, 'garrybear@test.com',CURDATE());
call AddProducts(@89,'Jordans','SHOES', 'Jordans', 55.50, 'hero@test.com',CURDATE());
call AddProducts(@ui,'wine glass','GLASSWARE', 'Wine glasses', 15.70, 'superman@test.com',CURDATE());
call AddProducts(@gh,'couch','FURNITURE', 'Brown couch', 11.50, 'admin@test.com',CURDATE());
call AddProducts(@id8,'NFT','ART', 'sun', 100.50, 'admin@test.com',CURDATE());


call AddImages(@id7,'/icon1.png',6,CURDATE());
               
call AddImages(@if6,'/blackdress.jpg',1,CURDATE());
call AddImages(@ity,'/jordans.jpg',3,CURDATE());
call AddImages(@tyu,'/wineglass.jpg',4,CURDATE());
call GetAllImages();


call GetAllUserProducts('kendra@test.com');
call GetAllProducts();

call GetUserForProduct(1);
call GetUserForProduct(3);
call GetAllImages();
call GetImagesforProduct(3);


call GetAllUserProducts('admin@test.com');
