package xyz.nhatbao.springdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.nhatbao.springdemo.exception.ProductNotFoundException;
import xyz.nhatbao.springdemo.model.Product;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/products")
public class ProductServiceController {
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

    // method not found, GET is the default method
    @RequestMapping
    public ResponseEntity<Object> getAllProducts() {
        return new ResponseEntity(productRepository.values(), HttpStatus.OK);
    }

    //  add a product by POST method
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        return new ResponseEntity(productRepository.put(product.getId(), product), HttpStatus.OK);
    }

    //  update a product by PUT method
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
        //  if product not found throw ProductNotFound exception
        if (!productRepository.containsKey(id)) throw new ProductNotFoundException();
        return new ResponseEntity(productRepository.put(id, product), HttpStatus.OK);
    }

    //  delete a product by DELETE method
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") String id) {
        return new ResponseEntity(productRepository.remove(id), HttpStatus.OK);
    }
}
