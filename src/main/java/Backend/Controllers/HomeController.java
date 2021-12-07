package Backend.Controllers;

import Backend.Models.DAL;
import Backend.Models.Home;
import Backend.Models.Product;
import Backend.Util.Path;
import io.javalin.http.Handler;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.File;
import java.io.StringWriter;
import java.net.URL;
import java.util.*;

public class HomeController {


    static Properties properties;
    static VelocityEngine velocityEngine;
    static String path = "C:/Users/Daniel/Desktop/CollegeMarketDemo/src/main/resources/templates/";
    static String keyPath = "file.resource.loader.path";


    /**
     *
     * Map data = new HashMap();
     * // Add data to your map here
     *
     * StringWriter writer = new StringWriter();
     * VelocityContext velocityContext = new VelocityContext(data);
     * velocityEngine.mergeTemplate(templateLocation, velocityContext, writer);
     * String html = writer.toString();
     *
     *
     *  VelocityEngine ve = new VelocityEngine();
     *         ve.init();
     *
     *         Template t = ve.getTemplate("datapage.vm");
     *
     *         VelocityContext vc = new VelocityContext();
     *             vc.put("username", "John");
     *
     *         StringWriter sw = new StringWriter();
     *         t.merge(vc, sw);
     *
     *         System.out.println(sw);
     *
     *         // ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
     *         //ve.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, );
     *        // ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
     *
     */




    public static Handler homePage = ctx -> {
        //Map<String, Object> model = new HashMap<>();
        //model.put("products", Home.getProductList());
        velocityEngine = new VelocityEngine();

       properties = new Properties();
       properties.put(keyPath, path);


         velocityEngine.init(properties);

        Template template = velocityEngine.getTemplate("HomeView.vm");
        VelocityContext context = new VelocityContext();

        StringWriter sw = new StringWriter();

        template.merge(context, sw);
        ctx.html(sw.toString());

    };

    public static void main(String[] args) {
        VelocityEngine ve = new VelocityEngine();

        Properties props = new Properties();
        props.put("file.resource.loader.path", "C:/Users/Daniel/Desktop/CollegeMarketDemo/src/main/resources/templates/");


        ve.init(props);

        Template template = ve.getTemplate("HomeView.vm");
        VelocityContext contex = new VelocityContext();
        StringWriter sw = new StringWriter();
        template.merge(contex, sw);
        System.out.println(sw);

    }
}
