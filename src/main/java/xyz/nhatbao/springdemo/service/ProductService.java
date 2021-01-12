package xyz.nhatbao.springdemo.service;

import xyz.nhatbao.springdemo.model.Product;

import java.util.Collection;

public interface ProductService {
    public abstract Collection<Product> getProducts();
    public abstract void createProduct(Product product);
    public abstract void updateProduct(String id, Product product);
    public abstract void deleteProduct(String id);
}
