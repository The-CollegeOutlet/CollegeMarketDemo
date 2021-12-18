package Backend;

import Backend.Controllers.HomeController;
import Backend.Controllers.ProductController;
import Backend.Controllers.UserController;
import Backend.Util.Path;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import static io.javalin.apibuilder.ApiBuilder.before;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;

public class Server {


    public void newServer() {


        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/public", Location.CLASSPATH);
        }).start(8000);


        app.routes(() -> {

            before(UserController.loginBeforeAddProduct);
            before(UserController.loginBeforeViewProfile);
            before(UserController.loginBeforeEditProfile);

            get(Path.HOME, HomeController.index);

            get(Path.CREATEUSER, UserController.create);
            post(Path.CREATEUSER, UserController.createAction);

            get(Path.EDITUSER, UserController.edit);
            post(Path.EDITUSER, UserController.editAction);

            get(Path.LOGIN, UserController.login);
            post(Path.LOGIN, UserController.loginAction);

            get(Path.PROFILE, UserController.index);
            get(Path.LOGOUT, UserController.logout);

            get(Path.CREATEPRODUCT, ProductController.create);
            post(Path.CREATEPRODUCT, ProductController.createAction);

            get(Path.EDITPRODUCT, ProductController.edit);
            post(Path.EDITPRODUCT, ProductController.editAction);

            //
            get("/test", HomeController.test);
            post("/test", HomeController.testAction);

        });



    }


}
