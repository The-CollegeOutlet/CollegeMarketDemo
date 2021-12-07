package Backend.Models;

import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User extends  DatabaseRecord{



    /**
     *
     *
     * Private variables
     *
     */

    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private String lastName;
    @Getter
    @Setter
    private String password;
    List<Product> productList;


    /**
     *
     *
     * DataBase column names for Product Table
     *
     *
     */

    static final String DB_ID = "id";
    static final String DB_firstname = "firstname";
    static final String DB_lastname = "lastname";
    static final String DB_email = "email";
    static final String DB_password = "password";
    static final String DB_dateCreated = "dateCreated";

    /**
     * * Testing purposes
     * * Might delete later
     *
     * @param ID User ID
     * @param firstName User firstname
     * @param lastName User lastname
     * @param email User email
     * @param password User password
     */


    public User(int ID, String firstName, String lastName, String email, String password) {
        this.id = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.productList = new ArrayList<>();

    }

    public User(String email) {
        this.email = email;

    }

    public User(ResultSet result) throws SQLException {
        fill(result);
    }


    public boolean addProduct(Product product){
        return productList.add(product);
    }

    public boolean removeProduct(Product product){
        for(Product element : productList){
            if(element.equals(product)){
                return productList.remove(product);
            }

        }
       return false;
    }

    public List<Product> getProductList() throws SQLException {
        this.productList = DAL.getAllUserProducts(this);
        return this.productList;
    }

    private void fill(ResultSet result) throws SQLException {
        this.id = Integer.parseInt(result.getString(User.DB_ID));
        this.firstName = result.getString(User.DB_firstname);
        this.lastName =  result.getString(User.DB_lastname);
        this.email =  result.getString(User.DB_email);
        this.password =  result.getString(User.DB_password);
        this.dateTimeCreated = result.getString(User.DB_dateCreated);
        this.productList = getProductList();

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", dateTimeCreated='" + dateTimeCreated + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", productList=" + productList +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof User user)) return false;
        if (!super.equals(object)) return false;
        return super.equals(object) && Objects.equals(password, user.password) ;
    }


}
