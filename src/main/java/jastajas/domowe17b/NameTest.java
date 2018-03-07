package jastajas.domowe17b;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping
public class NameTest {

    private ProductsDatabase productsDatabase;


    public NameTest(ProductsDatabase productsDatabase) {
        this.productsDatabase = productsDatabase;
    }

    @RequestMapping
    @ResponseBody
    public String showOption(){

        String main = "<ul><li><a href=" + '"' + "http://localhost:8080/allProducts" + '"' + ">Pokaż wszystkie produkty</a></li>" +
        "<li><a href=" + '"' + "http://localhost:8080/list?category=Produkty%20spożywcze" + '"' + ">Pokaż artykuły spożywcze</a></li>" +
        "<li><a href=" + '"' + "http://localhost:8080/list?category=Produkty%20gospodarstwa%20domowego" + '"' + ">Pokaż artykuły gospodarstwa domowego</a></li>" +
        "<li><a href=" + '"' + "http://localhost:8080/list?category=Inne" + '"' + ">Pokaż inne produkty</a></li></ul>" +
                "<p>Całkowita cena produktów wynosi: " + productsDatabase.getSumPrices() + " PLN</p></br>" +
                "<a href=" + '"' + "http://localhost:8080/Form.html" + '"' + ">Dodaj produkt</a><br>";
        return main;
    }

    @RequestMapping("/allProducts")
    @ResponseBody
    public String showAllProducts() {

        String allProducts = "";

        for (Product product : productsDatabase.getProducts()) {
            allProducts += product.toString() + "<br/>";
        }

        return allProducts;
    }


    @RequestMapping("/list")
    @ResponseBody
    public String showCategoryProducts(@RequestParam String category) {

        String allCategoryProducts = "<ul>";

        for (Product product : productsDatabase.getProducts()) {
           if(product.getCategory().equals(category)){

               allCategoryProducts += "<li>" + product.toString() + "</li><br/>";
            }
        }
        allCategoryProducts += "</ul>";

        return allCategoryProducts;
    }

    @GetMapping("/add")
    public String addUsers(@RequestParam String name,@RequestParam String price,@RequestParam String category) throws IOException {
            productsDatabase.getProducts().add(new Product(name, Double.valueOf(price), category));
            productsDatabase.saveFile();
            return "/success.html";

    }

}
