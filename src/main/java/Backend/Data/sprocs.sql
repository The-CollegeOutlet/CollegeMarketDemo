use CollegeMarket;


DROP PROCEDURE IF EXISTS AddProducts;
DROP PROCEDURE IF EXISTS GetAllProducts;
DROP PROCEDURE IF EXISTS EditProduct;
DROP PROCEDURE IF EXISTS GetUserForProduct;


DROP PROCEDURE IF EXISTS AddImages;
DROP PROCEDURE IF EXISTS GetImages;
DROP PROCEDURE IF EXISTS EditImage;
DROP PROCEDURE IF EXISTS GetAllImages;

DROP PROCEDURE IF EXISTS AddUser;
DROP PROCEDURE IF EXISTS EditUser;
DROP PROCEDURE IF EXISTS GetUser;
DROP PROCEDURE IF EXISTS GetAllUsers;
DROP PROCEDURE IF EXISTS GetAllUserProducts;





/*
    PRODUCT SPROCS

*/


/**
@@ Add the product to the DataBase
 */

DELIMITER //

CREATE PROCEDURE AddProducts(OUT id_check int(11), IN _type VARCHAR(20),IN _description VARCHAR(200)
                           , IN _price DECIMAL(13,2), emailCheck VARCHAR(80), IN _dateTime DATETIME )
BEGIN
    INSERT INTO products(ID, TYPE, DESCRIPTION,  PRICE, EMAIL, DATECREATED)
    VALUES (id_check, _type, _description, _price, emailCheck, _dateTime);

END //

DELIMITER ;



/**@@param:

  @@Edits the product in the DB
 */

DELIMITER //
CREATE PROCEDURE EditProduct(OUT id_check int(11), IN _type VARCHAR(20),IN _description VARCHAR(200)
, IN _price DECIMAL(13,2), emailCheck VARCHAR(80), IN _dateTime DATETIME )

BEGIN
    UPDATE products
    SET
        type = _type,
        description = _description,
        price = _price,
        DATECREATED = _dateTime

    WHERE
          id = id_check && email = emailCheck;

END //

DELIMITER ;

/**
  @@Gets all the products

 */

DELIMITER //

CREATE PROCEDURE GetAllProducts()
BEGIN
    SELECT *  FROM products;
END //

DELIMITER ;



/**@@param: Takes in email
  @@Gets all products from owned by a user
 */
DELIMITER //

CREATE PROCEDURE GetAllUserProducts(
    IN emailCheck VARCHAR(80)
)
BEGIN
    SELECT products.id, products.type, products.description,
           products.price, products.email, products.dateCreated
    FROM products,users
    WHERE products.email = emailCheck && users.email = products.email;
END //

DELIMITER ;


/**@@param: Takes in email
  @@Gets all products from owned by a user
 */
DELIMITER //

CREATE PROCEDURE GetUserForProduct(IN _id int(11))
BEGIN
    SELECT users.id, users.firstname, users.lastname, users.email, users.password, users.dateCreated
    FROM users,products
    WHERE users.email = products.email && products.id = _id;
END //

DELIMITER ;





/*
    IMAGE SPROCS

*/


/**
  @@Gets all images associated with a product

 */

DELIMITER //

CREATE PROCEDURE GetImages(IN _id INT(11))
BEGIN

    SELECT images.image, images.id
    FROM images, products
    WHERE images.id = _id && images.id = products.id;

END //

DELIMITER ;

/**
  @@Adds all Images for a product

 */

DELIMITER //

CREATE PROCEDURE AddImages( IN _id INT(11) ,IN emailCheck VARCHAR(80), IN _images longblob)
BEGIN
    INSERT INTO images
        VALUES(_id, emailCheck, _images);

END //

DELIMITER ;



/**@@param:

  @@Edits the Image of a product in the DB
 */

DELIMITER //
CREATE PROCEDURE EditImage(IN _id INT(11) ,IN emailCheck VARCHAR(80), IN _images longblob)

BEGIN
    UPDATE images
    SET
        image = _images

    WHERE
         id = _id && email = emailCheck;

END //

DELIMITER ;


/**
  @@Gets all the products

 */

DELIMITER //

CREATE PROCEDURE GetAllImages()
BEGIN
    SELECT image  FROM images;
END //

DELIMITER ;



/*
    USER SPROCS

*/


/**@@param: Takes in email and password

  @@Gets user that matches the parameters
 */

DELIMITER //


CREATE PROCEDURE GetUser(
    IN emailCheck VARCHAR(80), IN passwordCheck varchar(70)
)
BEGIN
    SELECT *
    FROM users
    WHERE email = emailCheck && password = passwordCheck;
END //

DELIMITER ;

/**
    @@param:
    @@Adds user to the DB

 */

DELIMITER //
CREATE PROCEDURE AddUser(OUT id_check INT(11), IN first_Name VARCHAR(35), IN last_Name VARCHAR(35),
                            IN emailCheck VARCHAR(80),IN Pass_Word VARCHAR(70), IN Date_Created DATETIME)

BEGIN
    INSERT INTO users(ID, FIRSTNAME, LASTNAME, EMAIL, PASSWORD, DATECREATED)
    VALUES (id_check, first_Name, last_Name, emailCheck,Pass_Word, Date_Created );

END //

DELIMITER ;


/**
     @@param:
     @@Edits the User in the DB

 */

DELIMITER //
CREATE PROCEDURE EditUser(OUT id_check INT(11), IN first_Name VARCHAR(35), IN last_Name VARCHAR(35),
                         IN emailCheck VARCHAR(80),IN Pass_Word VARCHAR(70), IN Date_Created DATETIME)

BEGIN
    UPDATE users
    SET
        FIRSTNAME = first_Name,
        LASTNAME = last_Name,
        EMAIL = emailCheck,
        PASSWORD = Pass_Word,
        DATECREATED = Date_Created
    WHERE
          id = id_check && email = emailCheck;

END //

DELIMITER ;


/**
  @@Gets all the users

 */

DELIMITER //

CREATE PROCEDURE GetAllUsers()
BEGIN
    SELECT *  FROM users;
END //

DELIMITER ;

