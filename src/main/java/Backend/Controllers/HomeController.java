package Backend.Controllers;

import Backend.Models.Category;
import Backend.Models.DAL;
import Backend.Models.Product;
import Backend.Views.Home.Index;
import Backend.Views.Partial;
import io.javalin.http.Handler;
import java.util.ArrayList;
import java.util.List;



public class HomeController {

    static List<Product> products;


    public static Handler index = ctx ->{
        List<Product> productList = DAL.getAllProducts();
        products = productList;
        ctx.html(Index.render(productList, false, false));
    };


    public static Handler search = ctx -> {

        List<Product> searchProducts = new ArrayList<>();


        if(products == null){
            products = DAL.getAllProducts();
        }
        try{
            String searchText = ctx.body().toLowerCase();

            if(searchText.length() > 0 ) {

                for (Product product : products) {
                    if (product.getName().toLowerCase().contains(searchText) || product.getDescription().contains(searchText)) {
                        searchProducts.add(product);
                    }
                }
                // Displays the results of the search
                ctx.result(Partial.productSection(searchProducts, false, false).toString());
            }
            else{

                // Displays the Product List
                ctx.result(Partial.productSection(products, false, false).toString());
            }

        }catch(Exception ex){
            ex.printStackTrace();

        }
    };

    public static Handler filter = ctx -> {

        List<Product> filteredProducts = new ArrayList<>();

        if(products == null){
            products = DAL.getAllProducts();
        }
        try{
            String filteredText = ctx.body();

            if(filteredText.length() > 0  && !filteredText.equals("Select a Category")) {
                Category category = Category.valueOf(filteredText);
                for (Product product : products) {
                    if (product.getCategory().equals(category)) {
                        filteredProducts.add(product);
                    }
                }
                // Displays the results of the search
                ctx.result(Partial.productSection(filteredProducts, false, false).toString());
            }
            else{

                // Displays the Product List
                ctx.result(Partial.productSection(products, false, false).toString());
            }

        }catch(Exception ex){
            ex.printStackTrace();

        }


    };







}
