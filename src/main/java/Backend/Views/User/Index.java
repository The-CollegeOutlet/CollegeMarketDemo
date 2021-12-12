package Backend.Views.User;

import Backend.Models.User;
import Backend.Util.Path;

import static j2html.TagCreator.*;

public class Index {



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
                      div(text(user.toString())
                      ),

                      br(),

                      div(
                        a("Home Page").withHref(Path.HOME)
                      ),

                      a("Log out").withHref(Path.LOGOUT)
              )

        ).render();
    }
}
