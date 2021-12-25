package Backend.Views.Home;

import Backend.Models.Category;
import Backend.Models.Product;
import Backend.Util.Path;
import Backend.Util.Request;
import Backend.Views.Partial;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static j2html.TagCreator.*;

public class Index {

    static List<String> categories = Stream.of(Category.values()).map(
            Category::name).collect(Collectors.toList());


    public static String render(List<Product> products, boolean edit, boolean delete) {

        return html(
                head(
                        meta().withCharset("UTF-8"),
                        meta().withName("viewport").withContent("width=device-width, initial-scale=1"),
                        link().withRel("stylesheet").withHref("https://fonts.googleapis.com/icon?family=Material+Icons"),
                        styleWithInlineFile_min("src/main/resources/css/style.css")

                ),
                body(

                        /*
                          Header starts here
                         */

                        header(attrs(".header"),
                                div(attrs(".site_header"),
                                        h1(attrs(".site_name"),
                                                "The CollegeOutlet"
                                        )
                                ),



                                //User Icons

                                div(attrs(".user_icon"),

                                        a().withHref(Path.CREATEUSER).with(
                                                i(attrs(".material-icons"),
                                                        "person_add")
                                        ),

                                        a().withHref(Path.LOGIN).with(
                                                i(attrs(".material-icons"),
                                                        "account_circle")
                                        )
                                )

                                //User Icons Ends

                        ),

                        // Header starts ends

                        //Search Bar Starts


                        div(attrs(".search-bar"),
                                div( form().withAction(Path.SEARCH).withMethod("post").with(
                                        input().withType("text")
                                                .withPlaceholder("Search")
                                                .withId("search")
                                                .withName("search"),
                                        // .withOnsearch("search()"),
                                        button().withType("submit")
                                                .with(
                                                        i(attrs(".material-icons"),
                                                                "search")
                                                )
                                )),

                                //Search Bar Ends


                                div(
                                     label("Filter"),
                                        select().withName(Request.PRODUCTCATEGORY)
                                                .withId("filter")
                                                .with(option("Select a Category"),
                                                        each(categories, category ->
                                                                option(category)
                                                        )
                                                )
                                )

                        ),






                        //  User Side Bar Starts

                        div(attrs(".user_sidebar"),
                                div(attrs(".sidebar_categories"),

                                        div(attrs(".sidebar_single_category"),
                                                a().withHref(Path.PROFILE).with(
                                                        i(attrs(".material-icons"),
                                                                "perm_identity"),
                                                        span("Profile")
                                                )

                                        ),

                                        div(attrs(".sidebar_single_category"),
                                                a().withHref(Path.EDITUSER).with(
                                                        i(attrs(".material-icons"),
                                                                "manage_accounts"),
                                                        span("Edit Profile")

                                                )


                                        ),

                                        div(attrs(".sidebar_single_category"),
                                                a().withHref(Path.CREATEPRODUCT).with(
                                                        i(attrs(".material-icons"),
                                                                "playlist_add"),
                                                        span("Add Product")
                                                )

                                        ),

                                        div(attrs(".sidebar_single_category"),
                                                a().withHref(Path.LOGOUT).with(
                                                        i(attrs(".material-icons"),
                                                                "logout"),
                                                        span("Logout")
                                                )
                                        )
                                )
                        ),

                                /*
                                   User Side Bar Ends
                                 */

                        /*
                          Main Body starts

                          The products are listed here
                         */

                        div(h2(" Products ")),

                        div(attrs("#mainbody"),

                                //Displays product card
                                Partial.productSection(products, edit, delete)
                        )
                ),
                scriptWithInlineFile_min("src/main/resources/Js/index.js")
        ).render();


    }

}
