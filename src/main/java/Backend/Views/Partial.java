package Backend.Views;

import Backend.Models.Product;
import Backend.Util.Path;
import Backend.Util.Request;
import j2html.tags.Tag;

import java.util.List;

import static j2html.TagCreator.*;

public class Partial {


    /**
     *
     * @param products Product list
     * @param edit checks if the product should be allowed to be edited
     * @param delete checks if the product should be allowed to be deleted
     * @return Product section tag
     */

    public static Tag productSection(List<Product> products, boolean edit, boolean delete){

        return section(attrs(".products")).withId("products").with( products.size() != 0 ? productCard(products, edit, delete)
                : p("They are currently no products available")

        );
    }


    private static Tag productCard(List<Product> products, boolean edit, boolean delete) {

        return div(attrs(".product-container")).with(each(products, product -> {
            try {
                return a(div(attrs(".product-card"),

                        div(attrs(".product-image"),
                                img(attrs(".image")).withSrc("/" + product.getImageList().get(0).getFile().getName())
                        ),
                        div(attrs(".product-info"),
                                h2(attrs(".product-price"),
                                        String.valueOf(product.getPrice())),

                                p(attrs(".product-name"),
                                        product.getName()),

                                a(attrs(".product-email"),
                                        product.getEmail())

                        ),
                        div(attrs(".button-container")).condWith(edit && delete,

                                div(attrs(".edit"), form().withMethod("get").withAction(Path.EDITPRODUCT).with(
                                        input().withType("text").withName(Request.ID).withId(Request.ID)
                                                .withValue(String.valueOf(product.getId())).isHidden(),
                                        input().withType("submit").withValue("Edit")
                                )),

                                div(attrs(".delete"), form().withMethod("get").withAction(Path.EDITPRODUCT).with(
                                        input().withType("text").withName(Request.ID).withId(Request.ID)
                                                .withValue(String.valueOf(product.getId())).isHidden(),
                                        input().withType("submit").withValue("Delete")
                                )))
                )).withHref("/product/details/"+product.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }));
    }



    public static Tag displayImage(Product product, int location) throws Exception {
        return div(attrs(".product-image"),
                img(attrs(".image")).withSrc("/" + product.getImageList().get(location).getFile().getName())
        );
}}
