package jastajas.domowe17b;

import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductsDatabase {

    private List<Product> products;


    public ProductsDatabase() throws Exception {
        FileReader fr = new FileReader("src/main/resources/products.csv");
        BufferedReader bfr = new BufferedReader(fr);

        products = new ArrayList<>();

        String line = null;

        while ((line = bfr.readLine()) != null) {
            String[] temp = line.split(";");
            products.add(new Product(temp[1], Double.valueOf(temp[2]), temp[3], temp[4],Integer.valueOf(temp[0])));
        }
    }

    public List<Product> getProducts() {
        return products;
    }


    public void saveFile() throws IOException {
        FileWriter fw = new FileWriter("src/main/resources/products.csv", false);
        BufferedWriter bfw = new BufferedWriter(fw);

        for (int i = 0; i < products.size(); i++) {
            String newProduct = products.get(i).getId() + ";" +
                    products.get(i).getName() + ";" +
                    products.get(i).getPrice() + ";" +
                    products.get(i).getCategory() + ";" +
                    products.get(i).getPicture();
            bfw.write(newProduct);
            bfw.newLine();
        }

        bfw.close();
    }

    public void addProductTdDB(Product product) throws IOException {
        products.add(product);
        saveFile();
    }
}
