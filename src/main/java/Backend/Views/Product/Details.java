package Backend.Views.Product;

import Backend.Models.Product;
import Backend.Util.Path;

import java.util.Map;

import static j2html.TagCreator.*;

public class Details {



    /**
     * User Home page
     *
     * @param model
     * @return The html content as a string
     */

    public static String render(Map<String, Object> model ) throws Exception {


        Product product =  (Product) model.get("product");
        int position = (Integer) model.get("position");

        return html(

              head(

              ),

              body(
                      div(
                          button(attrs(".move"),"Left"
                          ).withId("left"),
                              button(attrs(".move"),"Right"
                              ).withId("right")
                      ),

                      div(attrs(".product-image"),
                              img(attrs(".image")).withSrc("/" + product.getImageList().get(position).getFile().getName())
                      ).withId("image-src"),

                      div(
                              textarea().withText(product.getDescription())
                      )


                      //text(product.toString())
                   //   a("Edit product").withHref(Path.EDITPRODUCT+"/"+ id)
              ),
                scriptWithInlineFile_min("src/main/resources/Js/Index.js")

        ).render();
    }
}
