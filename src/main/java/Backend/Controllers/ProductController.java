package Backend.Controllers;


import Backend.Models.Category;
import Backend.Models.DAL;
import Backend.Models.Product;
import Backend.Models.User;
import Backend.Util.Path;
import Backend.Views.Product.Create;
import Backend.Views.Product.Edit;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import static Backend.Util.Request.*;

public class ProductController {

    public static Handler create = ctx -> {

            ctx.html(Create.render());
    };


    public static Handler createAction = ctx -> {
        Product product = bindObject(ctx);
        if(product != null){

            if(product.dbSave() > 0){
                ctx.redirect(Path.PROFILE);
            }
        }

    };

    public static Handler edit = ctx -> {
        String temp = getRedirectId(ctx);
        int id = Integer.parseInt(temp);


       Product product = DAL.getProduct(id);
        System.out.println(product);
        if(product != null){
            ctx.html(Edit.render(product));
        }else{
            ctx.redirect(Path.PROFILE);
        }

    };

    public static Handler editAction = ctx -> {
        Product product = bindObject(ctx);

        if(product.dbSave() > 0){
            ctx.redirect(Path.PROFILE);
        }


    };





    private static Product bindObject(Context ctx){
        Product product = null;
        User user = ctx.sessionAttribute(MODEL);
        if(user != null) {
            String email = user.getEmail();
            float price = Float.parseFloat(getQueryPrice(ctx));

             product = new Product(getQueryName(ctx), email, Category.valueOf(getQueryCategory(ctx)), getQueryDesc(ctx),price);
             String temp = getQueryId(ctx);
             int id = Integer.parseInt(temp);
             product.setId(id);

            return product;
        }

        return product;
    }
}
