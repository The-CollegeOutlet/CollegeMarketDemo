package Backend.Views.Product;

import static j2html.TagCreator.*;

import Backend.Models.Category;
import Backend.Util.Path;
import Backend.Util.Request;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Create {

    static List<String> categories = Stream.of(Category.values()).map(
            Category::name).collect(Collectors.toList());


    public static String render() {
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
                                        .withValue("-1").isHidden(),

                                label("Name")
                                        .withFor(Request.PRODUCTNAME),

                                input().withType("text")
                                        .withId(Request.PRODUCTNAME)
                                        .withName(Request.PRODUCTNAME)
                                        .isRequired(),


                                label("Category")
                                        .withFor(Request.PRODUCTCATEGORY),

                                select().withName(Request.PRODUCTCATEGORY)
                                        .withId(Request.PRODUCTCATEGORY)
                                        .with(
                                                each(categories, category ->
                                                        option(category)
                                                )
                                        ),


                                label("Description")
                                        .withFor(Request.PRODUCTDESC),


                                textarea().withId(Request.PRODUCTDESC)
                                        .withName(Request.PRODUCTDESC)
                                        .withRows("10")
                                        .withCols("30")
                                        .isRequired(),


                                label("Price")
                                        .withFor(Request.PRODUCTPRICE),


                                input().withType("text")
                                        .withId(Request.PRODUCTPRICE)
                                        .withName(Request.PRODUCTPRICE)
                                        .withValue("")
                                        .isRequired(),


                                input().withType("submit").withValue("Submit")

                        ),
                        br(),
                        a("Home").withHref(Path.HOME)


                )

        ).render();
    }
}
