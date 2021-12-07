package Backend.Models;

import lombok.Getter;
import lombok.Setter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Product extends DatabaseRecord {


    /**
     *
     *
     * Private variables
     *
     *
     */

    @Getter
    @Setter
    private Category type;
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
     static final String DB_Type = "type";
     static final String DB_Description = "description";
     static final String DB_Price = "price";
     static final String DB_email = "email";
     static final String DB_dateCreated = "dateCreated";


    /**
     * *Testing purposes
     * * Might delete later
     *
     * @param ID Product ID
     * @param type Product type
     * @param description Product Description
     * @param images Product Images
     * @param price Product price
     * @param email Users email(ISU) address
     */

    public Product(int ID, String email, Category type, String description, List<Image> images, float price) {
        this.id = ID;
        this.type = type;
        this.description = description;
        this.imageList = images;
        this.price = price;
        this.email = email;

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

    /**
     *
     * @return The products owner
     * @throws SQLException
     */

    public User getUser() throws SQLException {
        this.user = DAL.getUserForProduct(this);
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
        this.imageList = DAL.getImages(this);
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
        this.type = Category.valueOf(result.getString(Product.DB_Type));
        this.description = result.getString(Product.DB_Description);
        this.price = result.getFloat(Product.DB_Price);
        this.dateTimeCreated = result.getString(Product.DB_dateCreated);
        this.email = result.getString(Product.DB_email);
        this.imageList = getImageList();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Product product)) return false;
        if (!super.equals(object)) return false;
        return super.equals(object) &&
                Float.compare(product.price, price) == 0
                && type == product.type;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", dateTimeCreated='" + dateTimeCreated + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", imageList=" + imageList +
                ", price=" + price +
                ", user=" + user +
                '}';
    }
}
