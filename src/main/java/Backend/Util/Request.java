package Backend.Util;

import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Request {


    public static final String EMAIL = "email";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String PASSWORD = "password";
    public static final String CONFIRMPASSWORD = "confirm-password";
    public static final String CURRENTUSER = "currentUser";
    public static final String LOGGEDOUT = "loggedOut";
    public static final String REDIRECT = "loginRedirect";
    public static final String PRODUCTNAME = "productName";
    public static final String PRODUCTDESC = "productDescription";
    public static final String PRODUCTPRICE = "productPrice";
    public static final String PRODUCTCATEGORY = "productCategory";
    public static final String IMAGE = "images";
    public static final String MODEL = "model";
    public static final String ID = "id";
    public static final String PRODUCT = "product";




    public static String getQueryPassword(@NotNull Context ctx){
        return ctx.formParam(Request.PASSWORD);
    }

    public static String getQueryEmail(Context ctx) {
        return ctx.formParam(Request.EMAIL);
    }

    public static String getQueryFirstName(Context ctx) {
        return ctx.formParam(Request.FIRSTNAME);
    }

    public static String getQueryLastName(Context ctx) {
        return ctx.formParam(Request.LASTNAME);
    }

    public static String getQueryConfirmPassword(Context ctx){
        return ctx.formParam(Request.CONFIRMPASSWORD);
    }

    public static String getQueryName(Context ctx){return ctx.formParam(Request.PRODUCTNAME);}

    public static String getQueryDesc(Context ctx){return ctx.formParam(Request.PRODUCTDESC);}

    public static String getQueryCategory(Context ctx){return ctx.formParam(Request.PRODUCTCATEGORY);}

    public static List<UploadedFile> getQueryImage(Context ctx){return ctx.uploadedFiles();}

    public static String getQueryPrice(Context ctx){return ctx.formParam(Request.PRODUCTPRICE);}

    public static String getRedirectId(Context ctx){return ctx.queryParam(Request.ID);}

    public static String getQueryId(Context ctx){
        return ctx.formParam(Request.ID);
    }



    public static int getSessionCurrentUser(Context ctx) {
        return (int) ctx.sessionAttribute(Request.CURRENTUSER);
    }

    public static boolean removeSessionAttrLoggedOut(Context ctx) {
        String loggedOut = ctx.sessionAttribute(Request.LOGGEDOUT);
        ctx.sessionAttribute(Request.LOGGEDOUT, null);
        return loggedOut != null;
    }

    public static String removeSessionAttrLoginRedirect(Context ctx) {
        String loginRedirect = ctx.sessionAttribute(Request.REDIRECT);
        ctx.sessionAttribute(Request.REDIRECT, null);
        return loginRedirect;
    }


    public static String getFormParamRedirect(Context ctx) {
        return ctx.sessionAttribute("loginRedirect");
    }



}
