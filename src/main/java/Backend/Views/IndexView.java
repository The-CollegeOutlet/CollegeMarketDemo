package Backend.Views;

import static j2html.TagCreator.*;

public class IndexView {

    public static String render(){
        return html(
                head(
                        meta().withCharset("UTF-8"),
                        meta().withName("viewport").withContent("width=device-width, initial-scale=1"),
                        link().withRel("stylesheet").withHref("https://fonts.googleapis.com/icon?family=Material+Icons"),
                        link().withRel("stylesheet").withHref("src/main/resources/public/style.css"),
                        styleWithInlineFile_min("src/main/resources/css/style.css")
                        ),
                body(

                        /**
                         * Header starts here
                         */

                        header(attrs(".header"),
                                div(attrs(".site_header"),
                                        p(attrs(".site_name"),
                                                "The CollegeOutlet"
                                        )
                                ),

                                /*

                                  Search Bar Starts

                                 */


                                div(attrs(".search_bar"),
                                        form().withAction("").withMethod("post").with(
                                                input().withType("text")
                                                        .withPlaceholder("Search"),
                                                button().withType("submit")
                                                        .with(
                                                        i(attrs(".material-icons"),
                                                                "search")
                                                )

                                        )),

                                   /*

                                       Search Bar Ends

                                     */


                                /*
                                    User Icons
                                 */
                                div(attrs(".user_icon"),

                                        a().withHref("/create").with(
                                                i(attrs(".material-icons"),
                                                "person_add")
                                                ),

                                        a().withHref("/login").with(
                                                i(attrs(".material-icons"),
                                                "account_circle")
                                        )
                                )

                                 /*
                                    User Icons Ends
                                  */

                        ),



                        /**
                         * Header starts ends
                         */



                        /**
                         * Main Body starts
                         */

                        div(attrs(".mainbody"),

                                /*
                                  User Side Bar Starts

                                 */

                                div(attrs(".user_sidebar"),
                                        div(attrs(".sidebar_categories"),
                                                div(attrs(".sidebar_single_category"),
                                                        i(attrs(".material-icons"),
                                                                "perm_identity"),
                                                        span("Profile")

                                                ),
                                                div(attrs(".sidebar_single_category"),
                                                        i(attrs(".material-icons"),
                                                                "manage_accounts"),
                                                        span("Edit Profile")

                                                ),
                                                div(attrs(".sidebar_single_category"),
                                                        i(attrs(".material-icons"),
                                                                "playlist_add"),
                                                        span("Add Product")

                                                ),
                                                div(attrs(".sidebar_single_category"),
                                                        i(attrs(".material-icons"),
                                                                "logout"),
                                                        span("Logout")
                                                )
                                        )
                                ),

                                /*

                                   User Side Bar Ends

                                 */

                                /**
                                 *
                                 * Product list starts here
                                 *
                                 */


                                div(attrs(".products"),

                                        h1(" Products "),

                                        /*

                                           Individual Product start here

                                         */
                                        /**
                                         *
                                         * A partial will be made
                                         * A partial is a visual funcion that we can call again
                                         *
                                         * We will use a for loop to iterate through all the images in the product
                                         * list object
                                         *
                                         * method pseudo code
                                         * DivTag listProduct(List<Product>){
                                         *
                                         * }
                                         *
                                         */

                                        div(attrs(".product"),
                                                div(attrs(".product_image"),
                                                        img().withSrc("/blackdress.jpg").withAlt("The Image is currently" +
                                                                " unable to be to displayed")
                                                )
                                        )


                                        /*

                                           Individual Product ends here

                                         */
                                )
                        )
                )

        ).render();
    }

}
