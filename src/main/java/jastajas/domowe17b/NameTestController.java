package jastajas.domowe17b;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


@RequestMapping
@Controller
public class NameTestController {

    private ProductsDatabase productsDatabase;


    public NameTestController(ProductsDatabase productsDatabase) {
        this.productsDatabase = productsDatabase;
    }

    @RequestMapping("/list")
    public String showCategoryProducts(@RequestParam String category, Model model) {

        List<Product> filteredList = new ArrayList<>();
        double sum = 0;

        for (Product product : productsDatabase.getProducts()) {
            if (category.equals("")) {
                filteredList.add(product);
                sum += product.getPrice();
            } else if (category.equals(product.getCategory())){
                filteredList.add(product);
                sum += product.getPrice();
            }
        }

        BigDecimal newVal = new BigDecimal(sum);
        newVal = newVal.setScale(2,RoundingMode.HALF_EVEN);

        model.addAttribute("productList", filteredList);
        model.addAttribute("sumPrice", newVal);

        return "productsList";
    }

    @RequestMapping("/productDetails")
    public String showProductDetails(@RequestParam Integer id, Model model) {

        for (Product product : productsDatabase.getProducts()) {
            if (product.getId() == id){
               model.addAttribute("prodDetails", product);
            }
        }
        return "productDetails";
    }


    @RequestMapping("/addProductForm")
    public String addProduct(Model model){
        model.addAttribute("addProduct", new Product());
        return "Form";
    }

    @RequestMapping("/add")
    public String add(Product product) throws IOException {
        product.setId(productsDatabase.getProducts().size());
        productsDatabase.addProductTdDB(product);
        return "redirect:/list?category=";
    }



}
