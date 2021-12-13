package Backend.Models;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * The database currently stores a string which will be the image path
 *
 * Reading and storing the file from the computer next challenge

 *
 * Directory : resources/public
 */


public class Image extends DataBaseRecord{


    /**
     *
     *
     * Private variables
     *
     */

    @Getter
    private File file;
    private int productId;
    private Product product;

    /**
     *
     *
     * DataBase column names for Image Table
     *
     *
     */

    static final String DB_ID = "id";
    static final String DB_IMAGE = "image";
    static final String DB_PRODUCTID = "productID";
    static final String DB_DATECREATED = "dateCreated";


    /**
     *
     * @param file The Image file
     */

    public Image(File file){
        this.file = file;
    }

    /**
     *
     */
    public Image(ResultSet result) throws SQLException {
        fill(result);
    }


    /**
     *
     * @param path image path
     *
     */
    public Image(String path){
        this.file = new File(path);
        this.dateCreated = new Date();
    }

    private void setProduct() throws SQLException {
        this.product = DAL.getProduct(productId);
    }

    /**
     *
     * @return Product of the Image
     */

    public Product getProduct() throws SQLException {
        if (product == null){
            setProduct();
        }
        return product;
    }

    private void fill(ResultSet result) throws SQLException {
        this.id = result.getInt(DB_ID);
        //this.file =
        this.productId = result.getInt(DB_PRODUCTID);
        this.dateCreated = result.getDate(Product.DB_DATE_CREATED);


    }



    @Override
    public String toString() {
        return "Image{" +
                "file=" + file +
                '}';
    }

    @Override
    protected int dbSave() throws SQLException {
        if(getId() < 0){
            return dbAdd();
        }else {
            return dbUpdate();
        }

    }

    @Override
    public int dbAdd() throws SQLException {
        setId(DAL.addImage(this));
        return getId();
    }

    @Override
    protected int dbUpdate() throws SQLException {
        return DAL.updateImage(this);
    }


}
