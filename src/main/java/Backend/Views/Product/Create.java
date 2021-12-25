package Backend.Views.Product;

import static j2html.TagCreator.*;

import Backend.Models.Category;
import Backend.Models.Product;
import Backend.Util.Path;
import Backend.Util.Request;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Create {

    static List<String> categories = Stream.of(Category.values()).map(
            Category::name).collect(Collectors.toList());


    public static String render(Map<String, Object> model) {

        Product product = (Product) model.get("product");
        return html(
                head(
                        meta().withCharset("UTF-8"),
                        meta().withName("viewport").withContent("width=device-width, initial-scale=1"),
                        link().withRel("stylesheet").withHref("https://fonts.googleapis.com/icon?family=Material+Icons")
                ),
                body(

                        h1("CREATE YOUR PRODUCT"),

                        FormPartial.productForm(product),
                        br(),
                        a("Home").withHref(Path.HOME)


                )


        ).render();
    }
}
