package Backend.Views.User;

import static j2html.TagCreator.*;

public class Create {

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
                        div(attrs(".signup_box"),
                                h1("Sign up"),
                                h2("It's free and only takes a minute"),


                                /**
                                 * We will be replaced with a partial(visual function)
                                 * so we can reuse it for edit
                                 */

                                form().withMethod("post").withAction("/create").with(


                                        label("First Name")
                                                .withFor("firstname"),

                                        input().withType("text")
                                                .withId("firstname")
                                                .withName("firstname")
                                                .isRequired(),


                                        label("Last Name")
                                                .withFor("lastname"),

                                        input().withType("text")
                                                .withId("lastname")
                                                .withName("lastname")
                                                .withValue("")
                                                .isRequired(),

                                        label("Email")
                                                .withFor("email"),


                                        input().withType("email")
                                                .withId("email")
                                                .withName("email")
                                                .withValue("")
                                                .isRequired(),


                                        label("Password")
                                                .withFor("password"),


                                        input().withType("password")
                                                .withId("password")
                                                .withName("password")
                                                .withValue("")
                                                .isRequired(),


                                        label("Confirm Password")
                                                .withFor("confirm-password"),

                                        input().withType("password")
                                                .withId("confirm-password")
                                                .withName("confirm-password")
                                                .withValue("")
                                                .isRequired(),

                                        input().withType("submit").withValue("Submit")

                                ),
                                p("By clicking the Sign up button, you are agree to our").with(
                                        br(),
                                        a("Terms and Conditions").withHref(""),
                                        text("and"),

                                        a("Policy And Privacy").withHref("")
                                )

                        ),
                        p("Already have an account?").attr(".redirect_account").with(a("login here").withHref("/login"))
                )
        ).render();

    }
}
