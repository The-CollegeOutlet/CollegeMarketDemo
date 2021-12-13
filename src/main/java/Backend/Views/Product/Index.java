package Backend.Views.Product;

import Backend.Models.Product;
import Backend.Models.User;
import Backend.Util.Path;

import static j2html.TagCreator.*;

public class Index {



    /**
     * User Home page
     *
     * @param product
     * @return The html content as a string
     */

    public static String render(Product product){
        int id = product.getId();

        return html(

              head(),

              body(
                      text(product.toString()),
                      a("Edit product").withHref(Path.EDITPRODUCT+"/id?"+ id)
              )

        ).render();
    }
}
