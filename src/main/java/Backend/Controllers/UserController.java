package Backend.Controllers;


import Backend.Models.DAL;
import Backend.Models.DAL.*;
import Backend.Models.User;
import Backend.Views.LoginView;
import Backend.Views.SignupView;
import Backend.Views.UserView;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.sql.SQLException;

import static Backend.Util.Request.*;


public class UserController {

    /**
     * @GET Request
     * Renders the sign-up page
     */

    public static Handler signUpPage = ctx -> {

        ctx.html(SignupView.render());
    };

    /**
     * @GET Request
     * Renders the login page
     */
    public static Handler loginPage = ctx -> {
        ctx.html(LoginView.render());
    };

    /**
     * @POST Request
     * Handles the create User call
     * Adds the User to the DataBase
     * Returns the User Homepage on successful creation
     * else returns the sign-up page
     */

    public static Handler signUpPost = ctx -> {

        User user = null;

        if (validatePassword(ctx)) {
            user = bindObject(ctx);

            if(user.dbAdd() > 0){
                ctx.html(UserView.render(user));
            }else {
                ctx.html(SignupView.render());
            }

        } else {
            ctx.html(SignupView.render());

        }

    };

    public static Handler loginPagePost = ctx -> {

        if(login(getQueryEmail(ctx),getQueryPassword(ctx)) != null){
            User user = login(getQueryEmail(ctx),getQueryPassword(ctx));
            ctx.html(UserView.render(user));

        }else{
            ctx.html(SignupView.render());
        }

        System.out.println(login(getQueryEmail(ctx),
                getQueryPassword(ctx)));


    };

    private static boolean validatePassword(Context ctx) {
        return getQueryPassword(ctx).equals(getQueryConfirmPassword(ctx));
    }

    private static User bindObject(Context ctx) {
        return new User(getQueryFirstName(ctx), getQueryLastName(ctx), getQueryEmail(ctx), getQueryPassword(ctx));
    }

    /**
     * The method might change, or we might use it to implemnet
     * depends on how we implement the view partials.
     *
     * @param email    Requires verification with DataBase
     * @param password Requires verification with DataBase
     * @return
     * @throws SQLException
     */
    private static User login(String email, String password) throws SQLException {
        User user = DAL.getUser(email, password);

        if (user != null) {

            return user;
        }
        return null;
    }


}
