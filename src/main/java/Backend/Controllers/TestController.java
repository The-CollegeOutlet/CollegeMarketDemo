package Backend.Controllers;

import Backend.Models.DAL;
import Backend.Models.Product;
import Backend.Views.Partial;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.List;

public class TestController {

    static List<Product> products;




    public static Handler test = ctx ->{

       // ctx.("<h1> Test was successful</h1>");
        System.out.println("yes i am here");
       ctx.result("<section> Test was successful</section>");
    };
    public static Handler testAction = ctx ->{


       // System.out.println(products);

    };


}
