package br.com.hackathongdg.recycleplus;

import java.util.ArrayList;
import java.util.List;

import br.com.hackathongdg.recycleplus.model.Product;

public class ProductsRepo {

    private static ProductsRepo instance = null;

    protected ProductsRepo() {
        // Exists only to defeat instantiation.
    }

    public static ProductsRepo getInstance() {
        if (instance == null) {
            instance = new ProductsRepo();
        }
        return instance;
    }

    public List<Product> getProducts() {
        Product product1 = new Product();
        product1.setTitle("Lata de Coca-cola");
        product1.setCategory("Metal");
        product1.setPhoto("http://farm8.static.flickr.com/7277/7598643812_bec22e6676_m.jpg");
        product1.setRecycled(true);

        Product product2 = new Product();
        product2.setTitle("Garrafa Pet");
        product2.setCategory("Plástico");
        product2.setPhoto("http://www.purihome.com/wpimages/wp18d9231b_05_06.jpg");
        product2.setRecycled(false);

        Product product3 = new Product();
        product3.setTitle("Lote de baterias");
        product3.setCategory("Lixo eletrônico");
        product3.setPhoto("http://www.solarnavigator.net/world_solar_challenge/solar_challenge_images/battery_pack_varta_lithium_ion_accumulator.jpg");
        product3.setRecycled(false);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        return products;
    }
}
