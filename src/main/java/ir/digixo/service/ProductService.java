package ir.digixo.service;

import ir.digixo.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    List<Product> list;

    public void restCallToGetProducts() {

        System.out.println("restCallToGetProducts...");
        RestTemplate restTemplate = new RestTemplate();
        Product[] productArray = restTemplate.getForObject("http://localhost:8080/products", Product[].class);
        list = new ArrayList<>();
        list.addAll(Arrays.asList(productArray));
    }

    public Product getProduct() {

        System.out.println("getProduct...");
        if (list == null) {
            restCallToGetProducts();
        }
        if (list != null && !list.isEmpty()) {
            return list.removeFirst();
        }
        return null;
    }

}
