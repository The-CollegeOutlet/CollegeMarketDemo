package Backend.Views.Product;

import Backend.Models.Category;
import Backend.Models.Product;
import Backend.Util.Path;
import Backend.Util.Request;
import j2html.tags.Tag;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static j2html.TagCreator.*;
import static j2html.TagCreator.input;

public class FormPartial {

    static List<String> categories = Stream.of(Category.values()).map(
            Category::name).collect(Collectors.toList());

    public static Tag productForm(Product product){


        return  form().withMethod("post").withAction(Path.CREATEPRODUCT)
                .withEnctype("multipart/form-data")
                .with(

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

                        label("Images").withFor(Request.IMAGE),


                        input().withValue("Upload Images")
                                .withName("images")
                                .withType("file")
                                .isMultiple()
                                .isRequired()
                                .withAccept("image/*")
                        ,



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

                );
    }
}
