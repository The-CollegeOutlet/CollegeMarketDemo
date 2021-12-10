package Backend.Controllers;

import Backend.Views.IndexView;
import Backend.Views.TestView;
import io.javalin.http.Handler;


public class IndexController {

    /**
     *
     *
     *
     *
     */


    public static Handler IndexPage =
            ctx -> ctx.html(IndexView.render());

    public static Handler test = ctx -> {
        ctx.html(TestView.render("Welcome to Test it would be awesome if this worked"));
    };


}
