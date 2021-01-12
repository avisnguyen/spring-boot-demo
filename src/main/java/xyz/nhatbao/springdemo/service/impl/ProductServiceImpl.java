package xyz.nhatbao.springdemo.service.impl;

import org.springframework.stereotype.Service;
import xyz.nhatbao.springdemo.exception.ProductNotFoundException;
import xyz.nhatbao.springdemo.model.Product;
import xyz.nhatbao.springdemo.service.ProductService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    //    define product repository
    private static Map<String, Product> productRepository = new HashMap<String, Product>();

    // add 2 product to repository
    static {
        Product ipXS = new Product();
        ipXS.setId("1");
        ipXS.setName("IPhone XS - 256GB");
        productRepository.put(ipXS.getId(), ipXS);

        Product ip11 = new Product();
        ip11.setId("2");
        ip11.setName("IPhone 11 - 128GB");
        productRepository.put(ip11.getId(), ip11);

    }

    @Override
    public Collection<Product> getProducts() {
        return productRepository.values();
    }

    @Override
    public void createProduct(Product product) {
        productRepository.put(product.getId(), product);
    }

    @Override
    public void updateProduct(String id, Product product) {
        if (!productRepository.containsKey(id)) throw new ProductNotFoundException();
        else productRepository.put(id, product);
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.remove(id);
    }
}
