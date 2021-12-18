package Backend.Util;

import Backend.Models.DAL;
import Backend.Models.Product;
import Backend.Models.User;
import io.javalin.http.Context;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static Backend.Util.Request.*;

public class ViewUtil {

    public static Map<String, Object> baseModel() throws SQLException {

        Map<String, Object> model = new HashMap<>();
        User user = new User();
        Product product = new Product();
        model.put("currentUser", user);
        model.put("product", product);
        return model;
    }



}
