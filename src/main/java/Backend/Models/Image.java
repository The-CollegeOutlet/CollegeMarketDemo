package Backend.Models;

import lombok.Getter;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 *
 * Images can be stored from the frontend
 * editing images and displaying mutiple
 * images next challenge

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
     */
    public Image(ResultSet result) throws SQLException {
        fill(result);
    }


    /**
     *
     * @param path image path
     *
     */
    public Image(File file){
        StopGreedy();
        this.file = file;
        this.dateCreated = new Date();
    }

    private void setProduct() throws Exception {
        this.product = DAL.getProduct(productId);
    }

    /**
     *
     * @return Product of the Image
     */

    public Product getProduct() throws Exception {
        if (GetFromDatabase && product == null){
            setProduct();
        }
        return product;
    }

    private void fill(ResultSet result) throws SQLException {
        AllowGreedy();
        this.id = result.getInt(DB_ID);
        this.file = new File(result.getString(Image.DB_IMAGE));
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
    protected int dbSave() throws Exception {
        if(getId() < 0){
            return dbAdd();
        }else {
            return dbUpdate();
        }

    }

    @Override
    public int dbAdd() throws Exception {
        setId(DAL.addImages(this.product));
        return getId();
    }

    @Override
    protected int dbUpdate() throws Exception {
        return DAL.updateImage(this);
    }


}
