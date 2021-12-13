package Backend.Models;

import lombok.Getter;
import lombok.Setter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Product extends DataBaseEmailRecord {


    /**
     *
     * Private variables
     *
     */
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Category category;
    @Getter
    @Setter
    private String description;
    private List<Image> imageList;
    @Getter
    @Setter
    private float price;
    private User user;


    /**
     *
     *
     * DataBase column names for Product Table
     *
     *
     */

     static final String DB_ID = "id";
     static final String DB_NAME = "name";
     static final String DB_CATEGORY = "category";
     static final String DB_DESCRIPTION = "description";
     static final String DB_PRICE = "price";
     static final String DB_EMAIL = "email";
     static final String DB_DATE_CREATED = "dateCreated";


    /**
     * *Testing purposes
     * * Might delete later
     *
     * @param name Product name
     * @param category Product type
     * @param description Product Description
     * @param price Product price
     * @param email Users email(ISU) address
     */

    public Product(String name, String email, Category category, String description, float price) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.imageList = new ArrayList<>();
        this.price = price;
        this.email = email;
        this.dateCreated = new Date();
    }

    /**
     * Testing purposes
     * @param x
     */
    public Product(int x){
        this.id = x;
        
    }

    /**
     * Creates object using Database information
     * @param result Sql result (Product variables)
     * @throws SQLException
     */

    public Product(ResultSet result) throws SQLException {
        fill(result);
    }

    private void setImageList() throws SQLException {
        this.imageList = DAL.getImages(this);
    }

    private void setUser() throws SQLException {
        this.user = DAL.getUserByEmail(this.email);
    }

    /**
     *
     * @return The products owner
     * @throws SQLException
     */

    public User getUser() throws SQLException {
       if(this.user == null) {
          setUser();
       }
        return this.user;
    }



    /**
     *
     * @param image An image of the product that needs to be added
     * @return true If the image is added to the Image list
     */

    public boolean addImage(Image image){
        return imageList.add(image);
    }

    /**
     *
     * @return A list of the products images
     * @throws SQLException
     */

    public List<Image> getImageList() throws SQLException {

       if(imageList == null){
           setImageList();
       }

        return imageList;
    }

    /**
     * Used to create model from DataBase data
     * Called only by the constructor
     * @param result SQL result used to fill the object private variables
     * @throws SQLException
     */


    private void fill(ResultSet result) throws SQLException {
        this.id = result.getInt(Product.DB_ID);
        this.name = result.getString(Product.DB_NAME);
        this.category = Category.valueOf(result.getString(Product.DB_CATEGORY));
        this.description = result.getString(Product.DB_DESCRIPTION);
        this.price = result.getFloat(Product.DB_PRICE);
        this.email = result.getString(Product.DB_EMAIL);
        this.dateCreated = result.getDate(Product.DB_DATE_CREATED);

    }


    @Override
    public int dbSave() throws SQLException {
        if(getId() < 0){
            return dbAdd();
        }else {
            return dbUpdate();
        }
    }

    @Override
    protected int dbAdd() throws SQLException {
        setId(DAL.addProduct(this));
        return getId();
    }

    @Override
    protected int dbUpdate() throws SQLException {
        return DAL.editProduct(this);
    }

    @Override
    public String toString() {
        return "Product{" +
                "email='" + email + '\'' +
                ", id=" + id +
                ", dateCreated=" + dateCreated +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", imageList=" + imageList +
                ", price=" + price +
                ", user=" + user +
                '}';
    }
}
