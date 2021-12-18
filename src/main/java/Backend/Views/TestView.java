package Backend.Views;

import static j2html.TagCreator.*;

public class TestView {

    public static String render(String string ){

        return html(
                head(),
                body(
                        form().withMethod("post").withAction("/test").withEnctype("multipart/form-data").with(
                            input().withValue(string)
                                    .withName("images")
                                    .withType("file")
                                    .isMultiple()
                                    .withAccept("image/*")
                                ,
                                input().withType("submit").withValue("Submit")
                        )
                )
        ).render();

    }

}
