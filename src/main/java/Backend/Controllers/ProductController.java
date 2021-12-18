package Backend.Controllers;


import Backend.Models.*;
import Backend.Util.Path;
import Backend.Util.ViewUtil;
import Backend.Views.Product.Create;
import Backend.Views.Product.Edit;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.UploadedFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

import static Backend.Util.Request.*;

public class ProductController {

    public static Handler create = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel();

        ctx.html(Create.render(model));
    };


    public static Handler createAction = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel();
        Product product = bindObject(ctx);
        model.replace("product", product);

        if(product != null){

            System.out.println(product);

            if(product.dbSave() > 0){
                ctx.redirect(Path.PROFILE);
            }else
            {
                ctx.html(Create.render(model));
            }


        }

    };

    public static Handler edit = ctx -> {

        Map<String, Object> model = ViewUtil.baseModel();
        String temp = getRedirectId(ctx);
        int id = Integer.parseInt(temp);

       Product product = DAL.getProduct(id);
        User user = (User) ctx.sessionAttribute(MODEL);
       //We could check if the product belongs to the user before rendering
        // we could also encrypt the product id
        System.out.println(product);
        if(product != null && user != null && product.getUser().equals(user)){
            model.replace("product", product);
            ctx.html(Edit.render(model));
        }else{
            ctx.redirect(Path.PROFILE);
        }

    };

    public static Handler editAction = ctx -> {

        Map<String, Object> model = ViewUtil.baseModel();
        Product product = bindObject(ctx);
        model.replace("product", product);

        if(product.dbSave() > 0){
            ctx.redirect(Path.PROFILE);
        }else{
            ctx.html(Edit.render(model));
        }


    };





    private static Product bindObject(Context ctx){
        Product product = new Product();
        User user = ctx.sessionAttribute(MODEL);
        if(user != null) {
            String email = user.getEmail();
            float price = Float.parseFloat(getQueryPrice(ctx));

            String temp = getQueryId(ctx);
            int id = Integer.parseInt(temp);

             product = new Product(id, getQueryName(ctx), email, Category.valueOf(getQueryCategory(ctx)), getQueryDesc(ctx),price);


            List<UploadedFile> imageList = getQueryImage(ctx);

            for(UploadedFile uploadedFile: imageList){

                InputStream stream = uploadedFile.getContent();
                File file = new File("src/main/resources/public/"+ uploadedFile.getFilename());
                try {
                    Files.copy(stream, file.toPath() , StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                product.addImage(new Image(file));

            }

            return product;
        }

        return product;
    }
}
