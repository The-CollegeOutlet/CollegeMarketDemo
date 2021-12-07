package Backend;

import Backend.Controllers.HomeController;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import io.javalin.http.staticfiles.Location;

import static org.eclipse.jetty.util.component.LifeCycle.start;

public class Server {


    public void newServer(){




        Javalin server = Javalin.create(config -> {
            config.addStaticFiles("/public", Location.CLASSPATH);
            config.registerPlugin(new RouteOverviewPlugin("/routes"));
        }).start(7000);

        server.get("", HomeController.homePage);




        server.get("/welcome", ctx -> ctx.html("<h1>Welcome to my website Demo</h1>"));



    }


}
