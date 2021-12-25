package Backend.Views.User;

import Backend.Models.User;
import Backend.Util.Path;
import Backend.Util.Request;
import j2html.tags.Tag;

import static j2html.TagCreator.*;
import static j2html.TagCreator.input;

public class FormPartial {

    public static Tag userForm(User user){

        return form().withMethod("post").withAction(Path.CREATEUSER).with(

                input().withType("text").withName(Request.ID).withId(Request.ID)
                        .withValue(String.valueOf(user.getId())).isHidden(),


                label("First Name")
                        .withFor("firstname"),

                input().withType("text")
                        .withId("firstname")
                        .withName("firstname")
                        .withValue(user.getFirstName())
                        .isRequired(),


                label("Last Name")
                        .withFor("lastname"),

                input().withType("text")
                        .withId("lastname")
                        .withName("lastname")
                        .withValue(user.getLastName())
                        .isRequired(),

                label("Email")
                        .withFor("email"),


                input().withType("email")
                        .withId("email")
                        .withName("email")
                        .withValue(user.getEmail())
                        .isRequired()
                        .withCondContenteditable(user.getId() < 0),


                label("Password")
                        .withFor("password"),


                input().withType("password")
                        .withId("password")
                        .withName("password")
                       // .withValue("")
                        .isRequired(),


                label("Confirm Password")
                        .withFor("confirm-password"),

                input().withType("password")
                        .withId("confirm-password")
                        .withName("confirm-password")
                      //  .withValue("")
                        .isRequired(),

                input().withType("submit").withValue("Submit")

        );
    }
}
