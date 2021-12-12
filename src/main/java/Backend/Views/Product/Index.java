package Backend.Views.Product;

import Backend.Models.Product;
import Backend.Models.User;

import static j2html.TagCreator.*;

public class Index {



    /**
     * User Home page
     *
     * @param product
     * @return The html content as a string
     */

    public static String render(Product product){

        return html(

              head(),

              body(
                      text(product.toString())
              )

        ).render();
    }
}
