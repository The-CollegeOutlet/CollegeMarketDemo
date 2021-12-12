package Backend.Views.User;

import static j2html.TagCreator.*;

public class Login {


    /**
     * The user login page
     * @return The html content as a string
     */

    public static String render(){
        return html(
                head(
                        meta().withCharset("UTF-8"),
                        meta().withName("viewport").withContent("width=device-width, initial-scale=1"),
                        link().withRel("stylesheet").withHref("https://fonts.googleapis.com/icon?family=Material+Icons"),
                        link().withRel("stylesheet").withHref("src/main/resources/public/userformstyle.css"),
                        styleWithInlineFile_min("src/main/resources/css/userformstyle.css")
                ),
                body(
                        div(attrs(".login_box"),
                                h1("Login"),
                                form().withAction("")
                                        .withMethod("post")
                                        .with(

                                                label("Email")
                                                        .withFor("email"),


                                                input().withType("email")
                                                        .withId("email")
                                                        .withName("email")
                                                        .isRequired(),


                                                label("Password")
                                                        .withFor("password"),


                                                input().withType("password")
                                                        .withId("password")
                                                        .withName("password")
                                                        .isRequired(),

                                                input().withType("submit")
                                                        .withValue("Submit")

                                        )
                        ),

                                p("Don't have an account").attr(".redirect_account").with(a("Sign up Here").withHref("/create"))
                )

        ).render();
    }

}
