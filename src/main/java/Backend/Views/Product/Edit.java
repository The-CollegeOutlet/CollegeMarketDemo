package Backend.Views.Product;

import Backend.Models.Category;
import Backend.Models.Product;
import Backend.Util.Path;
import Backend.Util.Request;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static j2html.TagCreator.*;
import static j2html.TagCreator.a;

public class Edit {


    static List<String> categories = Stream.of(Category.values()).map(
            Category::name).collect(Collectors.toList());


    public static String render(Product product) {
        return html(
                head(
                        meta().withCharset("UTF-8"),
                        meta().withName("viewport").withContent("width=device-width, initial-scale=1"),
                        link().withRel("stylesheet").withHref("https://fonts.googleapis.com/icon?family=Material+Icons")
                ),
                body(

                        text("CREATE YOUR PRODUCT"),

                        form().withMethod("post").withAction(Path.CREATEPRODUCT).with(

                                input().withType("text").withName(Request.ID).withId(Request.ID)
                                        .withValue(String.valueOf(product.getId())).isHidden(),

                                label("Name")
                                        .withFor(Request.PRODUCTNAME),

                                input().withType("text")
                                        .withId(Request.PRODUCTNAME)
                                        .withName(Request.PRODUCTNAME)
                                        .withValue(product.getName())
                                        .isRequired(),


                                label("Category")
                                        .withFor(Request.PRODUCTCATEGORY),

                                select().withName(Request.PRODUCTCATEGORY)
                                        .withId(Request.PRODUCTCATEGORY)
                                        .with(
                                                each(categories, category ->
                                                        option(category)
                                                                .withCondSelected(category.equals
                                                                        (product.getCategory().toString()))
                                                )
                                        ),


                                label("Description")
                                        .withFor(Request.PRODUCTDESC),


                                textarea().withId(Request.PRODUCTDESC)
                                        .withName(Request.PRODUCTDESC)
                                        .withRows("10")
                                        .withCols("30")
                                        .withText(product.getDescription())
                                        .isRequired(),


                                label("Price")
                                        .withFor(Request.PRODUCTPRICE),


                                input().withType("text")
                                        .withId(Request.PRODUCTPRICE)
                                        .withName(Request.PRODUCTPRICE)
                                        .withValue(String.valueOf(product.getPrice()))
                                        .isRequired(),


                                input().withType("submit").withValue("Submit")

                        ),
                        br(),
                        a("Home").withHref(Path.HOME)


                )

        ).render();
    }
}
