package Backend.Models;

import lombok.Getter;

import java.io.File;

/**
 * This currently been stoered in the Database
 * I am thinking of stroring images in the file system
 * So I might have to give products a name to help name the images
 * in the file directory
 *
 * Directory : resources/public
 */


public class Image extends DatabaseRecord {


    /**
     *
     *
     * Private variables
     *
     *
     */

    @Getter
    private File file;

    /**
     *
     *
     * DataBase column names for Image Table
     *
     *
     */

    static final String DB_ID = "id";
    static final String DB_email = "email";
    static final String DB_image = "image";


    /**
     *
     * @param file The Image file
     */

    public Image(File file){
        this.file = file;
    }

    /**
     *
     * @return
     */



    public Product getProduct(){
        return new Product(5);
    }


    @Override
    public String toString() {
        return "Image{" +
                "file=" + file +
                '}';
    }
}
