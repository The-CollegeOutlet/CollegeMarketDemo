package Backend.Controllers;


import Backend.Models.DAL;
import Backend.Models.User;
import Backend.Util.Path;
import Backend.Util.ViewUtil;
import Backend.Views.User.Create;
import Backend.Views.User.Edit;
import Backend.Views.User.Login;
import Backend.Views.User.Index;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;

import static Backend.Util.Request.*;


public class UserController {

    /**
     * @GET Request
     * Renders the sign-up page
     */

    public static Handler create = ctx -> {

        ctx.html(Create.render());
    };


    /**
     * @POST Request
     * Handles the create User call
     * Adds the User to the DataBase
     * Returns the User Homepage on successful creation
     * else returns the sign-up page
     */

    public static Handler createAction = ctx -> {

        Map<String, Object> model = ViewUtil.baseModel(ctx);

        User user = null;

        if (validatePassword(ctx)) {
            user = bindObject(ctx);

            if (user.dbSave() > 0) {

              //  ctx.sessionAttribute("currentUser", getQueryEmail(ctx));
                model.put("authenticationSucceeded", true);
                ctx.sessionAttribute("model", user);
                ctx.sessionAttribute("currentUser", user.getId());

                /*
                  Redirects the user
                 */
                if (getFormParamRedirect(ctx) != null) {
                    ctx.redirect(getFormParamRedirect(ctx));
                } else {
                    ctx.redirect(Path.PROFILE);
                }


            } else {

                // User wasn't added to the DataBase
                ctx.html(Create.render());
            }

        } else {

            /*
              We also need to validate the user email
              Email validation to be implemented
             */
            //The password didn't match or email
            model.put("authenticationFailed", true);
            ctx.html(Create.render());

        }

    };


    public static Handler edit = ctx -> {

        if (ctx.sessionAttribute("currentUser") != null) {
            int id = (int) ctx.sessionAttribute("currentUser");
            User user = DAL.getUser(id);

            assert user != null;
            ctx.html(Edit.render(user));
        }
    };

    public static Handler editAction = ctx -> {

        Map<String, Object> model = ViewUtil.baseModel(ctx);

        User user = ctx.sessionAttribute("model");
        if (user != null) {
            int temp = user.getId();
            System.out.println(temp);

            if (validatePassword(ctx)) {

                user = bindObject(ctx);
                user.setId(temp);


                if (user.dbSave() > 0) {
                    model.put("authenticationSucceeded", true);
                    ctx.sessionAttribute("model", user);

                    ctx.redirect(Path.PROFILE);

                } else {

                    // User wasn't edited to the DataBase
                    ctx.html(Edit.render(user));
                }

            }
        } else {
            // User password didn't validate(match the confirmation password field)
            ctx.html(Edit.render(user));
        }

    };


    /**
     * @GET Request
     * Renders the login page
     */

    public static Handler login = ctx -> {

    //    Map<String, Object> model = ViewUtil.baseModel(ctx);

      // model.put("loggedOut", removeSessionAttrLoggedOut(ctx));
        // model.put("loginRedirect", removeSessionAttrLoginRedirect(ctx));

      //  System.out.println(model.get("loggedOut"));
     //   System.out.println(model.get("loginRedirect"));

        ctx.html(Login.render());
    };


    /**
     * @POST Request
     * Handles the User login action
     * Gets the User from the DataBase
     * Returns the User profile-page on successful login
     * else returns the login-page with error
     */

    public static Handler loginAction = ctx -> {

        Map<String, Object> model = ViewUtil.baseModel(ctx);
        User user = login(getQueryEmail(ctx), getQueryPassword(ctx));

        if (user != null) {

          //  ctx.sessionAttribute("currentUser", getQueryEmail(ctx));
            ctx.sessionAttribute("currentUser", user.getId());
            model.put("authenticationSucceeded", true);
            ctx.sessionAttribute("model", user);


            /*
                  Redirects the user
             */
           // System.out.println(getFormParamRedirect(ctx));
            if (getFormParamRedirect(ctx) != null) {
                ctx.redirect(getFormParamRedirect(ctx));
            } else {
                ctx.redirect(Path.PROFILE);
            }


        } else {
            model.put("authenticationFailed", true);
            ctx.html(Login.render());
        }

    };


    /**
     * Logs the user out
     * Renders the login page
     */

    public static Handler logout = ctx -> {
        ctx.sessionAttribute("currentUser", null);
        ctx.sessionAttribute("loggedOut", "true");
        ctx.sessionAttribute("model", null);
        ctx.redirect(Path.LOGIN);
    };


    /**
     * Renders the user profile
     */
    public static Handler index = ctx -> {

        int id = (int) ctx.sessionAttribute("currentUser");
        User user = DAL.getUser(id);


        if (user != null) {
            ctx.html(Index.render(user));
        }

    };

    /**
     * These are before handlers might move them
     * to their own class
     */

    public static Handler loginBeforeViewProfile = ctx -> {
        if (!ctx.path().startsWith("/viewProfile")) {
            return;
        }
        if (ctx.sessionAttribute("currentUser") == null) {
            ctx.sessionAttribute("loginRedirect", ctx.path());
            ctx.redirect(Path.LOGIN);
        }
    };


    public static Handler loginBeforeEditProfile = ctx -> {
        if (!ctx.path().startsWith("/editProfile")) {
            return;
        }
        if (ctx.sessionAttribute("currentUser") == null) {
            ctx.sessionAttribute("loginRedirect", ctx.path());
            ctx.redirect(Path.LOGIN);
        }
    };

    public static Handler loginBeforeAddProduct = ctx -> {
        if (!ctx.path().startsWith("/createProduct")) {
            return;
        }
        if (ctx.sessionAttribute("currentUser") == null) {
            ctx.sessionAttribute("loginRedirect", ctx.path());
            ctx.redirect(Path.LOGIN);
        }
    };

    //Before handlers end



    /*
      These are the private utility methods
      utilized by the controller to handle user
      requests
     */


    /**
     * @param ctx User Request
     * @return true if the passwords match
     */

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

        return DAL.getUser(email, password);
    }


}
