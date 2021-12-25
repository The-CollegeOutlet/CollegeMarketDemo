package Backend.Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static Backend.Util.Hasher.*;

public class DAL {


    /*
     * DAL methods used query the DataBase
     * for model data.
     *
     */


    /**
     * Private variables
     *
     * @URL DataBase location
     * @user DataBase user
     * @password DataBase password
     * @callableStatement Object used to execute stored procedures
     * @sqlcommand Calling the strored procedures
     * @sqlDate Object used to convert java Date to sql
     */

    static String url;
    static String user;
    static String password;
    static Connection connection;
    static CallableStatement callableStatement;
    static String sqlCommand;
    static java.sql.Date sqlDate;


    /**
     * Creates Connection to the Database
     *
     * @return
     * @throws Exception
     */

    private static Connection connectToDatabase() throws Exception {

        url = "jdbc:mysql://localhost:3306/collegemarket";
        user = "root";
        password = "smileyface";

        try {
            connection = DriverManager.getConnection(url, user, password);
            return connection;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return connection;
    }


    /**
     *
     * * USER DAL METHODS
     * *
     */


    /**
     * * Adds User to the Database
     * @return The User ID
     * @param user User object
     */

    protected static int addUser(User user) throws Exception {

        int id = -1;

        sqlCommand = "{call AddUser(?,?,?,?,?,?,?)}";
        sqlDate = new Date(user.getDateCreated().getTime());

        try {

            callableStatement = connectToDatabase().prepareCall(sqlCommand);

            callableStatement.registerOutParameter(1,Types.INTEGER);
            userCall(user);

            callableStatement.execute();

            id = callableStatement.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            callableStatement.close();
            connectToDatabase().close();
        }

        return id;
    }




    /**
     * @param user Edited User
     * @return int The number of rows affected should return 1
     * returns 0 or less on failure 
     * @throws Exception
     */
    protected static int editUser(User user) throws Exception {

        int rowsAffected = 0;

        sqlCommand = "{call EditUser(?,?,?,?,?,?,?)}";
        sqlDate = new Date(user.getDateCreated().getTime());

        try {

            callableStatement = connectToDatabase().prepareCall(sqlCommand);
            userCall(user);

            rowsAffected = callableStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            callableStatement.close();
            connectToDatabase().close();
        }

        return rowsAffected;

    }

    /**
     * @param email User email
     * @return User of the email parameter
     * @throws Exception
     */

    public static User getUserByEmail(String email) throws Exception {
        sqlCommand = "{call GetUserByEmail(?) }";
        User user = null;

        try {
            callableStatement = connectToDatabase().prepareCall(sqlCommand);
            callableStatement.setString(1, email);
            ResultSet result = callableStatement.executeQuery();

            while (result.next()) {
                user = new User(result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            callableStatement.close();
            connectToDatabase().close();
        }

        return user;
    }

    public static User getUser(String email, String password) throws Exception {

        // Retries the user via email
        User user = getUserByEmail(email);


        //If the User isn't null
        if (user != null && user.getSalt() != null) {

            if (user.getPassword().equals(hashPassword(password, user.getSalt()))) {

                //Password match
                return user;

            } else {
                user = null;
            }
        }

        return user;
    }


    /**
     * @param id of the User
     * @return User of the id given
     */
    public static User getUser(int id) throws Exception {
        User user = null;

        sqlCommand = "{call GetUser(?)}";

        try {
            callableStatement = connectToDatabase().prepareCall(sqlCommand);
            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                user = new User(resultSet);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            callableStatement.close();
            connectToDatabase().close();
        }

        return user;

    }


    /**
     * @param product The product that needs to know its user
     * @return User The owner of the product
     */

    protected static User getUserForProduct(Product product) throws Exception {

        User user = null;
        sqlCommand = "{call GetUserForProduct(?)}";

        try {
            callableStatement = connectToDatabase().prepareCall(sqlCommand);
            callableStatement.setInt(1, product.getId());
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                user = new User(resultSet);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            callableStatement.close();
            connectToDatabase().close();
        }

        return user;
    }

    /**
     * @return The list of all users
     */
    protected static List<User> getAllUsers() throws Exception {
        List<User> users = new ArrayList<>();

        sqlCommand = "{call GetAllUsers}";
        try {
            callableStatement = connectToDatabase().prepareCall(sqlCommand);
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                users.add(new User(resultSet));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            callableStatement.close();
        }

        return users;
    }


    /**
     *
     * IMAGE DAL METHODS
     *
     */

    /**
     * Has not been tested yet, probably have to work on this again
     * Change to liST<iMAGE>
     *
     * @param product The product that needs to an image added
     * @return id Image ID if the image is successfully stored in the DB
     * returns int less than 1 on failure
     */

    protected static int addImages(Product product) throws Exception {
        
        int id = -1;

        sqlCommand = "{call AddImages(?,?,?,?)}";
        sqlDate = new Date(product.getDateCreated().getTime());

        try {

            callableStatement = connectToDatabase().prepareCall(sqlCommand);
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setInt(3, product.getId());

            for (Image image : product.getImageList()) {
                callImage(image);
                callableStatement.execute();

                id = callableStatement.getInt(1);

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            callableStatement.close();
            connectToDatabase().close();
        }




        return id;
    }

    /**
     *
     * @param image The image been updated
     * @return The number of rows affected
     * returns 0 or less than 0 on failure
     */

    protected static int updateImage(Image image) throws Exception {

        int rowsAffected = -1;

        sqlCommand = "{call EditImage(?,?,?,?)}";
        sqlDate = new Date(image.getDateCreated().getTime());


        try {


            callableStatement = connectToDatabase().prepareCall(sqlCommand);

            callImage(image);

            rowsAffected = callableStatement.executeUpdate();


        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            callableStatement.close();
            connectToDatabase().close();

        }

        return rowsAffected;
    }

    /**
     * Fills in stored procedure parameters using model
     * @param image
     * @throws Exception
     */



    protected static List<Image> getImages(Product product) throws Exception {
        List<Image> imageList = new ArrayList<>();

        sqlCommand = "{call GetImagesforProduct(?)}";

        try {

            callableStatement = connectToDatabase().prepareCall(sqlCommand);
            callableStatement.setInt(1, product.getId());
            ResultSet result = callableStatement.executeQuery();


            while (result.next()) {
                Image image = new Image(result);
                imageList.add(image);
            }


        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            callableStatement.close();
            connectToDatabase().close();
        }
        return imageList;
    }

    protected static List<Image> getAllImages() throws Exception {
        List<Image> images = new ArrayList<>();

        sqlCommand = "{call GetAllImages}";

        try {

            //Connects to the Database and executes the stored procedure
            //Returns the procedure result as resultSet
            callableStatement = connectToDatabase().prepareCall(sqlCommand);
            ResultSet resultSet = callableStatement.executeQuery();



        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            callableStatement.close();

        }
        return images;
    }


    /**
     * * PRODUCTS DAL METHODS
     *
     * @return
     */


    protected static int addProduct(Product product) throws Exception {

        int id = -1;

        sqlCommand = "{call AddProducts(?,?,?,?,?,?,?)}";
        sqlDate = new Date(product.getDateCreated().getTime());

        try{

            callableStatement = connectToDatabase().prepareCall(sqlCommand);
            callableStatement.registerOutParameter(1,Types.INTEGER);
            productCall(product);


            callableStatement.execute();
            id = callableStatement.getInt(1);



        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            callableStatement.close();
            connectToDatabase().close();
        }


        return id;
    }



    protected static int editProduct(Product product) throws Exception {
        int rowsAffected = -1;

        sqlCommand = "{call EditProduct(?,?,?,?,?,?,?)}";
        sqlDate = new Date(product.getDateCreated().getTime());

        try{
            callableStatement = connectToDatabase().prepareCall(sqlCommand);

            productCall(product);


            rowsAffected =  callableStatement.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            callableStatement.close();
            connectToDatabase().close();
        }


        return rowsAffected;

    }

    public static Product getProduct(int id) throws Exception{
        Product product = null;

        sqlCommand = "{call GetProduct(?)}";
        try{

            callableStatement = connectToDatabase().prepareCall(sqlCommand);
            callableStatement.setInt(1, id);

            ResultSet result = callableStatement.executeQuery();

            while (result.next()) {
               product = new Product(result);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            callableStatement.close();
            connectToDatabase().close();
        }
        return product;
    }

    /**
     * @param user The user who is requesting a list of products owned
     * @return products The list of products owned by the user
     */

    protected static List<Product> getAllUserProducts(User user) throws Exception {

        List<Product> products = new ArrayList<>();

        sqlCommand = "{call GetAllUserProducts(?)}";

        try {
            callableStatement = connectToDatabase().prepareCall(sqlCommand);
            callableStatement.setString(1, user.getEmail());
            ResultSet result = callableStatement.executeQuery();

            while (result.next()) {
                products.add(new Product(result));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            callableStatement.close();
            connectToDatabase().close();
        }

        return products;
    }


    /**
     * @return The List of all products
     */
    public static List<Product> getAllProducts() throws Exception {
        List<Product> products = new ArrayList<>();

        sqlCommand = "{call GetAllProducts}";
        try {
            callableStatement = connectToDatabase().prepareCall(sqlCommand);
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                products.add(new Product(resultSet));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            callableStatement.close();
            connectToDatabase().close();
        }

        return products;
    }




    private static void productCall(Product product) throws Exception {
        callableStatement.setInt(1, product.getId());
        callableStatement.setString(2, product.getName());
        callableStatement.setString(3,String.valueOf(product.getCategory()));
        callableStatement.setString(4,product.getDescription());
        callableStatement.setFloat(5, product.getPrice());
        callableStatement.setString(6, product.getEmail());
        callableStatement.setDate(7, sqlDate);

    }

    private static void userCall(User user) throws Exception {
        callableStatement.setInt(1,user.getId());
        callableStatement.setString(2, user.getFirstName());
        callableStatement.setString(3, user.getLastName());
        callableStatement.setString(4, user.getEmail());
        callableStatement.setString(5, user.getPassword());
        callableStatement.setString(6, user.getSalt());
        callableStatement.setDate(7, sqlDate);
    }


    private static void callImage(Image image) throws Exception {
        callableStatement.setInt(1, image.getId());
        callableStatement.setString(2, image.getFile().getPath());
        callableStatement.setDate(4, sqlDate);
    }


}
