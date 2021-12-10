use CollegeMarket;


DROP PROCEDURE IF EXISTS AddProducts;
DROP PROCEDURE IF EXISTS GetAllProducts;
DROP PROCEDURE IF EXISTS EditProduct;
DROP PROCEDURE IF EXISTS GetUserForProduct;


DROP PROCEDURE IF EXISTS AddImages;
DROP PROCEDURE IF EXISTS GetImage;
DROP PROCEDURE IF EXISTS EditImage;
DROP PROCEDURE IF EXISTS GetAllImages;
DROP PROCEDURE IF EXISTS GetImagesforProduct;


DROP PROCEDURE IF EXISTS AddUser;
DROP PROCEDURE IF EXISTS EditUser;
DROP PROCEDURE IF EXISTS GetUser;
DROP PROCEDURE IF EXISTS GetAllUsers;
DROP PROCEDURE IF EXISTS GetAllUserProducts;
DROP PROCEDURE IF EXISTS GetUserByEmail;





/*
    PRODUCT SPROCS

*/


/**
@@ Add the product to the DataBase
 */

DELIMITER //

CREATE PROCEDURE AddProducts(OUT _id int(11), IN _name varchar(40), IN _type VARCHAR(20),IN _description VARCHAR(200)
                           , IN _price DECIMAL(13,2), _email VARCHAR(80), IN _dateCreated DATE )
BEGIN
    INSERT INTO products(NAME ,TYPE, DESCRIPTION,  PRICE, EMAIL, DATECREATED)
    VALUES (_name , _type, _description, _price, _email, _dateCreated);

    SET _id = LAST_INSERT_ID();


END //

DELIMITER ;



/**@@param:

  @@Edits the product in the DB
 */

DELIMITER //
CREATE PROCEDURE EditProduct(IN _id int(11), IN _name varchar(40),IN _type VARCHAR(20),IN _description VARCHAR(200)
, IN _price DECIMAL(13,2), _email VARCHAR(80), IN _dateCreated date )

BEGIN
    UPDATE products
    SET
        name = _name,
        type = _type,
        description = _description,
        price = _price,
        DATECREATED = _dateCreated

    WHERE
          id = _id && email = _email;

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
    IN _email VARCHAR(80)
)
BEGIN
    SELECT products.id, products.name, products.type, products.description,
           products.price, products.email, products.dateCreated

    FROM products,users
    WHERE products.email = _email && users.email = products.email;
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
  @@returns the image associated with the id

 */

DELIMITER //

CREATE PROCEDURE GetImage(IN _id INT(11))
BEGIN

    SELECT images.id, images.image, images.dateCreated
    FROM images
    WHERE images.id = _id;

END //

DELIMITER ;



/**
  @@Gets all images associated with a product

 */

DELIMITER //

CREATE PROCEDURE GetImagesforProduct(IN _id INT(11))
BEGIN

    SELECT images.id, images.image, images.dateCreated
    FROM images, products
    WHERE images.productID = _id && images.productID = products.id;

END //

DELIMITER ;


/**
  @@Adds all Images for a product

 */

DELIMITER //

CREATE PROCEDURE AddImages( OUT _id int(11),IN _image VARCHAR(50), IN _productID int(11), IN _dateCreated DATE)
BEGIN

    INSERT INTO images( IMAGE, PRODUCTID, DATECREATED)
        VALUES( _image, _productID, _dateCreated);

    SET _id = LAST_INSERT_ID();

END //

DELIMITER ;



/**@@param:

  @@Edits the Image of a product in the DB
 */

DELIMITER //
CREATE PROCEDURE EditImage( IN _id INT(11) ,IN _image VARCHAR(50), IN _productID int(11),IN _dateCreated DATE)

BEGIN
    UPDATE images
    SET
        image = _image,
        dateCreated = _dateCreated

    WHERE
         id = _id && images.productID = _productID;

END //

DELIMITER ;


/**
  @@Gets all the products

 */

DELIMITER //

CREATE PROCEDURE GetAllImages()
BEGIN
    SELECT *  FROM images;
END //

DELIMITER ;



/*
    USER SPROCS

*/


/**@@param: Takes in User ID

  @@Gets user that matches the parameter
 */

DELIMITER //


CREATE PROCEDURE GetUser( IN _id int(11)

)
BEGIN
    SELECT *
    FROM users
    WHERE id = _id;
END //

DELIMITER ;

/**
    @@param:
    @@Adds user to the DB

 */

DELIMITER //
CREATE PROCEDURE AddUser(OUT _id int(11),IN _firstName VARCHAR(35), IN _lastName VARCHAR(35),
                            IN _email VARCHAR(80),IN _password VARCHAR(70), IN _dateCreated DATE)

BEGIN
    INSERT INTO users(FIRSTNAME, LASTNAME, EMAIL, PASSWORD, DATECREATED)
    VALUES ( _firstName, _lastName, _email,_password, _dateCreated );

    SET _id = LAST_INSERT_ID();


END //

DELIMITER ;


/**
     @@param:
     @@Edits the User in the DB

 */

DELIMITER //
CREATE PROCEDURE EditUser(IN _id INT(11), IN _firstName VARCHAR(35), IN _lastName VARCHAR(35),
                         IN _email VARCHAR(80),IN _password VARCHAR(70), IN _dateCreated DATE)

BEGIN
    UPDATE users
    SET
        FIRSTNAME = _firstName,
        LASTNAME = _lastName,
        EMAIL = _email,
        PASSWORD = _password,
        DATECREATED = _dateCreated
    WHERE
          id = _id && email = _email;

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



/**
Returns the user by email

 */
DELIMITER //


CREATE PROCEDURE GetUserByEmail( IN _email VARCHAR(80))
BEGIN
    SELECT *  FROM users
    WHERE email = _email;
END //

DELIMITER ;








