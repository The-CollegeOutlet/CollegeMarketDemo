package Backend;

import Backend.Controllers.HomeController;
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
            before(UserController.loginBeforeviewProfile);
            before(UserController.loginBeforeEdit);
            get(Path.HOME, HomeController.index);
            get(Path.CREATE, UserController.create);
            post(Path.CREATE, UserController.createAction);
            get(Path.LOGIN, UserController.login);
            post(Path.LOGIN, UserController.loginAction);
            get("/test", HomeController.test);
            get(Path.PROFILE, UserController.index);
            get(Path.LOGOUT, UserController.logout);

        });


    }


}
