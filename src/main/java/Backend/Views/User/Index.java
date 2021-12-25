package Backend.Views.User;

import Backend.Models.Product;
import Backend.Models.User;
import Backend.Util.Path;
import Backend.Views.Partial;

import java.util.List;

import static j2html.TagCreator.*;

public class Index {


    /**
     * User Home page
     *
     * @param user The logged-in user
     * @return The html content as a string
     */

    public static String render(User user, boolean edit, boolean delete) throws Exception {
        List<Product> products = user.getProductList();

        return html(

                head(
                        meta().attr("http-equiv","Cache-control" ).withContent("content=no-cache, no-store, must-revalidate"),
                        meta().attr("http-equiv","Pragma" ).withContent("no-cache"),
                        meta().attr("http-equiv","Expires").withContent("0"),
                        styleWithInlineFile_min("src/main/resources/css/style.css")
                ),

                body(

                        div(user.getFirstName()),
                        div(user.getLastName()),
                        div(user.getEmail()),
                        div(user.getDateCreated().toString()),

                        Partial.productSection(products, edit, delete),



                        div(
                                a("Home Page").withHref(Path.HOME)
                        ),

                        div(
                                a("Edit Profile").withHref(Path.EDITUSER)
                        ),

                        div(
                                a("Add Product").withHref(Path.CREATEPRODUCT)
                        ),

                        a("Log out").withHref(Path.LOGOUT)
                )

        ).render();
    }
}
