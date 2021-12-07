package Backend.Models;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAL {


    /**
     *
     *
     * Private variables
     *
     */

    static String url;
    static String user;
    static String password;
    static Connection connection;
    static CallableStatement callableStatement;
    static String sqlCommand;


    /**
     * Creates Connection to the Database
     *
     * @return
     * @throws SQLException
     */

    private static Connection connectToDatabase() throws SQLException {

         url = "jdbc:mysql://localhost:3306/collegemarket";
         user = "root";
        password= "smileyface";

        try{
            connection = DriverManager.getConnection(url,user, password);
            return connection;

        }  catch(SQLException ex){
            ex.printStackTrace();
        }
        return connection;
    }



    ///USER DAL METHODS

    /**
     *
     * @param user
     */

    protected void addUser(User user){

    }

    protected void editUser(User user){

    }

    /**
     *
     * @param email email of the user that needs to login
     * @param password password of the user that needs to login
     * @return User The logged in User
     * @throws SQLException
     */
    protected static User getUser(String email, String password) throws SQLException {
        User user = null;

        sqlCommand = "{call GetUser(?,?)}";

        try{
            callableStatement = connectToDatabase().prepareCall(sqlCommand);
            callableStatement.setString(1, email);
            callableStatement.setString(2, password);
            ResultSet resultSet = callableStatement.executeQuery();

            while(resultSet.next()){
                user = new User(resultSet);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            callableStatement.close();
            connectToDatabase().close();
        }

        return user;

    }

    /**
     *
     * @param product The product that needs to know its user
     * @return User The owner of the product
     * @throws SQLException
     */


    protected static User getUserForProduct(Product product) throws SQLException {

        User user = null;
        sqlCommand = "{call GetUserForProduct(?)}";

        try{
            callableStatement = connectToDatabase().prepareCall(sqlCommand);
            callableStatement.setInt(1, product.getId());
            ResultSet resultSet = callableStatement.executeQuery();


            while(resultSet.next()){
                user = new User(resultSet);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            callableStatement.close();
            connectToDatabase().close();
        }

        return user;
    }

    /**
     *
     * @return The list of all users
     * @throws SQLException
     */
    protected static List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();

        sqlCommand = "{call GetAllUsers}";
        try{
            callableStatement = connectToDatabase().prepareCall(sqlCommand);
            ResultSet resultSet = callableStatement.executeQuery();

            while(resultSet.next()){
                users.add(new User(resultSet));
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            callableStatement.close();
        }

        return users;
    }


    ///IMAGE DAL METHODS

    /**
     * Has not been tested yet, probably ahve to work on this again
     *
     * @param product The product that needs to an image added
     * @return true If the image is successfully stored in the DB
     * @throws SQLException
     */

    protected static boolean AddImage(Product product) throws SQLException {

        sqlCommand = "{call GetAllImages}";


        try{
            callableStatement = connectToDatabase().prepareCall(sqlCommand);

            // Change path

            File file = new File("E:\\image1.png");
            InputStream inputStream = new FileInputStream(file);

            callableStatement.setInt(1,product.getId());
            callableStatement.setString(2, product.getEmail());
            callableStatement.setBlob(3, inputStream);


            ResultSet resultSet = callableStatement.executeQuery();

            return true;


        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            callableStatement.close();

        }

        return false;
    }

    protected static void editImage(Image image){}

    protected static List<Image> getImages(Product product) throws SQLException {
        List<Image> imageList =  new ArrayList<>();

        sqlCommand = "{call GetImages(?)}";

        try{

            callableStatement = connectToDatabase().prepareCall(sqlCommand);
            callableStatement.setInt(1, product.getId());
            ResultSet resultSet = callableStatement.executeQuery();

            byte[] byteArray;
            Blob blob;
            FileOutputStream writer = null;
            int imageName = 0;

            while(resultSet.next()){
                imageName++;
                File file = new File("C:\\Users\\Daniel\\Desktop\\CollegeMarketDemo\\src\\main\\resources\\public"+ "\\"+imageName+".jpg");
                writer = new FileOutputStream(file);
                blob = resultSet.getBlob(Image.DB_image);
                byteArray = blob.getBytes(1,(int)blob.length());
                writer.write(byteArray);

                Image image = new Image(file);
                //image.id = resultSet.getInt(Image.DB_ID);
                imageList.add(image);
            }


        }
        catch (Exception ex){
            ex.printStackTrace();

        }finally {
            callableStatement.close();
            connectToDatabase().close();
        }
        return imageList;
    }

    protected static List<Image> getAllImages() throws SQLException {
        List<Image> images = new ArrayList<>();

        sqlCommand = "{call GetAllImages}";

        try{

            //Connects to the Database and executes the stored procedure
            //Returns the procedure result as resultSet
            callableStatement = connectToDatabase().prepareCall(sqlCommand);
            ResultSet resultSet = callableStatement.executeQuery();


            byte[] byteArray;
            Blob blob;
            FileOutputStream writer = null;
            int imageName = 0;

            while(resultSet.next()){
                imageName++;
                File file = new File("C:\\Users\\Daniel\\Desktop\\CollegeMarketDemo\\src\\main\\resources\\public"+ "\\"+imageName+".jpg");
                writer = new FileOutputStream(file);
                blob = resultSet.getBlob("image");
                byteArray = blob.getBytes(1,(int)blob.length());
                writer.write(byteArray);
                images.add(new Image(file));
              //  image.id = resultSet.getInt(Image.DB_ID);

            }

            if(writer != null){
                writer.close();
            }


        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            callableStatement.close();

        }
        return images;
    }




    //// PRODUCTS DAL METHODS


    protected static void addProduct(Product product){


    }
    protected static void editProduct(Product product){


    }


    /**
     *
     * @param user The user who is requesting a list of products owned
     * @return products The list of products owned by the user
     * @throws SQLException
     */

    protected static List<Product> getAllUserProducts(User user) throws SQLException {

        List<Product> products = new ArrayList<>();

        sqlCommand = "{call GetAllUserProducts(?)}";

        try{
            callableStatement = connectToDatabase().prepareCall(sqlCommand);
            callableStatement.setString(1, user.getEmail());
            ResultSet resultSet = callableStatement.executeQuery();

            while(resultSet.next()){
                products.add(new Product(resultSet));
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            callableStatement.close();
            connectToDatabase().close();
        }

        return products;
    }


    /**
     *
     * @return The List of all products
     * @throws SQLException
     */
    protected static List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();

        sqlCommand = "{call GetAllProducts}";
        try{
            callableStatement = connectToDatabase().prepareCall(sqlCommand);
            ResultSet resultSet = callableStatement.executeQuery();

            while(resultSet.next()){
                products.add(new Product(resultSet));
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            callableStatement.close();
            connectToDatabase().close();
        }

        return products;
    }





    public static void main(String[] args) throws SQLException {
        System.out.println(getAllProducts());
        System.out.println(getAllUsers());
        System.out.println(getUserForProduct(new Product(1)));
        System.out.println(getAllUserProducts(new User("kendra@test.com")));
       // System.out.println(getAllImages());
        System.out.println(getUser("kendra@test.com","YOURMOMA"));
        System.out.println(getUser("admin@test.com", "tester"));
        System.out.println(getUser("fail", "your meant to fail"));
    }


}
