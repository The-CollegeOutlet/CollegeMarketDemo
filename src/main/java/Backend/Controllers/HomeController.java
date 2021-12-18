package Backend.Controllers;

import Backend.Models.DAL;
import Backend.Models.Image;
import Backend.Models.Product;
import Backend.Views.Home.Index;
import Backend.Views.TestView;
import io.javalin.core.util.FileUtil;
import io.javalin.http.Handler;
import io.javalin.http.UploadedFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static Backend.Util.Request.*;


public class HomeController {

    /**
     *
     *
     *
     *
     */


    public static Handler index = ctx ->{
        List<Product> products = DAL.getAllProducts();
        ctx.html(Index.render(products, false, false));
    };

    public static Handler test = ctx -> {
        ctx.html(TestView.render("Upload images"));
    };

    public static Handler testAction = ctx -> {


        List<UploadedFile> imageList = getQueryImage(ctx);
        imageList.forEach(uploadedFile -> {

            InputStream stream = uploadedFile.getContent();
            File file = new File("src/main/resources/public/"+ uploadedFile.getFilename());
                    try {
                        Files.copy(stream, file.toPath() , StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }



                }



        );





        //ctx.html(TestView.render(""));
    };


}
