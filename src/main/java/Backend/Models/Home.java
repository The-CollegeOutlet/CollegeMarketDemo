package Backend.Models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Home {

    static List<Product> productList = new ArrayList<>();


    public static List<Product> getProductList() throws SQLException {
        productList = DAL.getAllProducts();
        return productList;
    }
}
