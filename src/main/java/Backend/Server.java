package Backend;

import Backend.Controllers.IndexController;
import Backend.Controllers.UserController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Server {


    public void newServer(){



        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("C:\\Users\\Daniel\\Desktop\\CollegeMarketDemo\\src\\main\\resources\\public", Location.EXTERNAL);
        }).start(8000);

        app.get("", IndexController.IndexPage);
        app.get("/create", UserController.signUpPage);
        app.post("/create",UserController.signUpPost );
        app.get("/login", UserController.loginPage);
        app.post("/login", UserController.loginPagePost);
        app.get("/test", IndexController.test);






    }


}
