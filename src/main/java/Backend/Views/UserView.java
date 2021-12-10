package Backend.Views;

import Backend.Models.User;

import static j2html.TagCreator.*;

public class UserView {



    /**
     * User Home page
     *
     * @param user The logged-in user
     * @return The html content as a string
     */

    public static String render(User user){

        return html(

              head(),

              body(
                      text(user.toString())
              )

        ).render();
    }
}
