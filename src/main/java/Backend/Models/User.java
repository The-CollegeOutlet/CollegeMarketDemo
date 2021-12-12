package Backend.Models;

import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class User extends DataBaseEmailRecord {



    /**
     *
     *
     * Private variables
     * @firstname User First Name
     * @lastname User Last Name
     * @password User Password
     * @productList User's list of products
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
    static final String DB_FIRSTNAME = "firstname";
    static final String DB_LASTNAME = "lastname";
    static final String DB_EMAIL = "email";
    static final String DB_PASSWORD = "password";
    static final String DB_DATE_CREATED = "dateCreated";

    /**
     *
     * @param firstName User firstname
     * @param lastName User lastname
     * @param email User email
     * @param password User password
     *
     */

    public User(String firstName, String lastName, String email, String password) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.productList = new ArrayList<>();
        this.dateCreated = new Date();

    }

    public User(String email) {
        this.email = email;

    }

    /**
     * Creates User from the DataBase
     * @param result Database query result
     * @throws SQLException
     */

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
        this.firstName = result.getString(User.DB_FIRSTNAME);
        this.lastName =  result.getString(User.DB_LASTNAME);
        this.email =  result.getString(User.DB_EMAIL);
        this.password =  result.getString(User.DB_PASSWORD);
        this.dateCreated = result.getDate(User.DB_DATE_CREATED);


    }



    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof User user)) return false;
        if (!super.equals(object)) return false;
        return super.equals(object) && Objects.equals(password, user.password) ;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", dateCreated=" + dateCreated +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", productList=" + productList +
                '}';
    }


    @Override
    public int dbSave() throws SQLException {
        if(getId() < 0){
            return dbAdd();
        }else
            return dbUpdate();

    }

    @Override
    protected int dbAdd() throws SQLException {
        setId(DAL.addUser(this));
        return getId();
    }

    @Override
    protected int dbUpdate() throws SQLException {
        return DAL.updateUser(this);
    }


}
