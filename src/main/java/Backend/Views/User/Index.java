package Backend.Views.User;

import Backend.Models.Product;
import Backend.Models.User;
import Backend.Util.Path;
import Backend.Util.Request;

import java.sql.SQLException;
import java.util.List;

import static j2html.TagCreator.*;

public class Index {


    /**
     * User Home page
     *
     * @param user The logged-in user
     * @return The html content as a string
     */

    public static String render(User user) throws SQLException {
        List<Product> products = user.getProductList();

        return html(

                head(),

                body(

                        div(user.getFirstName()),
                        div(user.getLastName()),
                        div(user.getEmail()),
                        div(
                                p("Products"),
                                each(products, product ->
                                        div(
                                                label("Name")
                                                        .withFor(Request.PRODUCTNAME),
                                                p(product.getName()),
                                                br(),

                                                label("Category")
                                                        .withFor(Request.PRODUCTCATEGORY),

                                                p(product.getCategory().name()),
                                                br(),

                                                label("Description")
                                                        .withFor(Request.PRODUCTDESC),

                                                p(product.getDescription()),
                                                br(),


                                                label("Price")
                                                        .withFor(Request.PRODUCTPRICE),
                                                p(String.valueOf(product.getPrice())),
                                                br(),

                                                div(
                                                        /*
                                                        a("Edit product").withHref(Path.EDITPRODUCT+"/"+ product.getId()).with(
                                                               input().withType("text").withValue(String.valueOf(product.getId())).withName(Request.ID).withId(Request.ID).isHidden()
                                                        ),


                                                         */
                                                        form().withMethod("get").withAction(Path.EDITPRODUCT).with(
                                                                input().withType("text").withName(Request.ID).withId(Request.ID)
                                                                        .withValue(String.valueOf(product.getId())).isHidden(),
                                                                input().withType("submit").withValue("Edit Product")
                                                        )
                                                )


                                        ))
                        ),

                        div(user.getDateCreated().toString()),

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
