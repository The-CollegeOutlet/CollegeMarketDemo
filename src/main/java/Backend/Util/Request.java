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


    public static String getSessionCurrentUser(Context ctx) {
        return ctx.sessionAttribute("currentUser");
    }

    public static boolean removeSessionAttrLoggedOut(Context ctx) {
        String loggedOut = ctx.sessionAttribute("loggedOut");
        ctx.sessionAttribute("loggedOut", null);
        return loggedOut != null;
    }

    public static String removeSessionAttrLoginRedirect(Context ctx) {
        String loginRedirect = ctx.sessionAttribute("loginRedirect");
        ctx.sessionAttribute("loginRedirect", null);
        return loginRedirect;
    }


    public static String getFormParamRedirect(Context ctx) {
        return ctx.formParam("loginRedirect");
    }


}
