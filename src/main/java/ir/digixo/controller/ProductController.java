package ir.digixo.controller;

import ir.digixo.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/")
public class ProductController {

    @GetMapping("products")
    public List<Product> getProducts() {
        return Arrays.asList(
                new Product(1l, "iphone1", "description1", new BigDecimal("1000")),
                new Product(2l, "iphone2", "description2", new BigDecimal("2000")),
                new Product(3l, "iphone3", "description3", new BigDecimal("3000"))
        );
    }
}
