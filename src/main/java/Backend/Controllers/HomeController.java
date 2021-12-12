package Backend.Controllers;

import Backend.Views.Home.IndexView;
import Backend.Views.TestView;
import io.javalin.http.Handler;


public class HomeController {

    /**
     *
     *
     *
     *
     */


    public static Handler index =
            ctx -> ctx.html(IndexView.render());

    public static Handler test = ctx -> {
        ctx.html(TestView.render("Welcome to Test it would be awesome if this worked"));
    };


}
