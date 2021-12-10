package Backend.Util;

import io.javalin.http.Context;

public class Request {


    public static String getQueryPassword(Context ctx){
        return ctx.formParam("password");
    }

    public static String getQueryEmail(Context ctx) {
        return ctx.formParam("email");
    }

    public static String getQueryFirstName(Context ctx) {
        return ctx.formParam("firstname");
    }

    public static String getQueryLastName(Context ctx) {
        return ctx.formParam("lastname");
    }

    public static String getQueryConfirmPassword(Context ctx){
        return ctx.formParam("confirm-password");
    }

}
