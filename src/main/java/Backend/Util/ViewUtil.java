package Backend.Util;

import Backend.Models.DAL;
import io.javalin.http.Context;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static Backend.Util.Request.*;

public class ViewUtil {

    public static Map<String, Object> baseModel(Context ctx) throws SQLException {

        Map<String, Object> model = new HashMap<>();
       // model.put("currentUser", getSessionCurrentUser(ctx));

        return model;
    }



}
