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

import java.util.Map;

import static Backend.Util.Request.*;
import static Backend.Util.Hasher.*;


/*
  We should change the user object to a hashmap
  reasons: with a hashmap we can pass in error messages
           and possibly success messages

   we can pass the user in to the hashmap

 */


public class UserController {

    /**
     * @GET Request
     * Renders the sign-up page
     */

    public static Handler create = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel();

        ctx.html(Create.render(model));
    };


    /**
     * @POST Request
     * Handles the create User call
     * Adds the User to the DataBase
     * Returns the User Homepage on successful creation
     * else returns the sign-up page
     */

    public static Handler createAction = ctx -> {

        Map<String, Object> model = ViewUtil.baseModel();
        try{
            User user = bindObject(ctx);

            if (validatePassword(ctx)) {
                // Hash User password security
                user.setSalt(generateSalt());
                user.setPassword(hashPassword(user.getPassword(), user.getSalt()));

                if (user.dbSave() > 0) {

                    //  ctx.sessionAttribute("currentUser", getQueryEmail(ctx));
                    model.put("authenticationSucceeded", true);
                    ctx.sessionAttribute("model", user);
                    ctx.sessionAttribute("currentUser", user.getId());
                    model.put("authenticationSucceeded", true);
                    model.replace(CURRENTUSER, user);


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
                    ctx.html(Create.render(model));
                }

            } else {

            /*
              We also need to validate the user email
              Email validation to be implemented
             */
                //The password didn't match or email
                model.put("authenticationFailed", true);
                ctx.html(Create.render(model));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }


        ctx.html(Create.render(model));

    };


    public static Handler edit = ctx -> {

        Map<String, Object> model = ViewUtil.baseModel();

        try{
            if (ctx.sessionAttribute("currentUser") != null) {
                int id = (int) ctx.sessionAttribute("currentUser");
                User user = DAL.getUser(id);
                model.replace(CURRENTUSER, user);

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        ctx.html(Edit.render(model));
    };

    public static Handler editAction = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel();

        try{
            User currentUser = bindObject(ctx);
            model.replace(CURRENTUSER, currentUser);

            //if the password is validated
            if (validatePassword(ctx)) {

                //Check if password was changed
                int tempId = Integer.parseInt(ctx.sessionAttribute(CURRENTUSER));
                User tempUser = DAL.getUser(tempId);
                String pass = currentUser.getPassword();

                if(tempUser.getPassword() != hashPassword(pass, tempUser.getSalt())){
                    //Password was changed
                    currentUser.setSalt(generateSalt());
                    currentUser.setPassword(hashPassword(pass, currentUser.getSalt()));
                }

                //Save User
                if (currentUser.dbSave() > 0) {
                    model.put("authenticationSucceeded", true);
                    ctx.sessionAttribute("model", currentUser);

                    ctx.redirect(Path.PROFILE);

                } else {

                    // User wasn't edited to the DataBase
                    ctx.html(Edit.render(model));
                }

                //Password isn't validated
                // return view
            }else{
                ctx.html(Edit.render(model));
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }



    };


    /**
     * @GET Request
     * Renders the login page
     */

    public static Handler login = ctx -> {


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
      //  Map<String, Object> model = ViewUtil.baseModel();
        User user = login(getQueryEmail(ctx), getQueryPassword(ctx));
        try{

            if (user != null) {

                ctx.sessionAttribute("currentUser", user.getId());
                //   model.put("authenticationSucceeded", true);
                //  model.replace("currentUser", user);
                ctx.sessionAttribute("model", user);

            /*
                  Redirects the user
             */

                if (getFormParamRedirect(ctx) != null) {
                    ctx.redirect(getFormParamRedirect(ctx));
                } else {
                    ctx.redirect(Path.PROFILE);
                }


            } else {
                //  model.put("authenticationFailed", true);
                ctx.html(Login.render());
            }

        }catch (Exception ex){
            ex.printStackTrace();
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
        ctx.req.getSession().invalidate();
        ctx.redirect(Path.LOGIN);
    };


    /**
     * Renders the user profile
     */
    public static Handler index = ctx -> {

        // Prevents response from been cached
        ctx.res.setHeader("Cache-Control", "no-cache");
        ctx.res.setHeader("Cache-Control", "no-store");
        ctx.res.setHeader("Pragma", "no-cache");
        ctx.res.setDateHeader("Expire", 0);


        int id = (int) ctx.sessionAttribute("currentUser");
        User user = DAL.getUser(id);


        if (user != null) {
            ctx.html(Index.render(user, true, true));

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
        String temp = getQueryId(ctx);
        int id = Integer.parseInt(temp);
        User user =  new User(id, getQueryFirstName(ctx), getQueryLastName(ctx), getQueryEmail(ctx), getQueryPassword(ctx));

        return user;
    }

    /**
     * The method might change, or we might use it to implemnet
     * depends on how we implement the view partials.
     *
     * @param email    Requires verification with DataBase
     * @param password Requires verification with DataBase
     * @return User logged in User
     */
    private static User login(String email, String password) throws Exception {

        return DAL.getUser(email, password);
    }


}
