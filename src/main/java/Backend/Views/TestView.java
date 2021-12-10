package Backend.Views;

import static j2html.TagCreator.*;

public class TestView {

    public static String render(String string ){

        return html(
                head(),
                body(
                        input().withValue(string).withType("text")
                )
        ).render();

    }

}
