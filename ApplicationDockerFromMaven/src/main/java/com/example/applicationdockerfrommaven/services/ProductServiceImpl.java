package com.example.applicationdockerfrommaven.services;


import com.example.applicationdockerfrommaven.converters.ProductFormToProduct;
import com.example.applicationdockerfrommaven.domain.Product;
import com.example.applicationdockerfrommaven.domain.ProductForm;
//import com.example.applicationdockerfrommaven.repositories.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

//    @Autowired
//    ProductRepository productRepository;

    @Autowired
    ProductFormToProduct productFormToProduct;

    @Override
    public List<Product> listAll() {
        List<Product> products = new ArrayList<>();
//        productRepository.findAll().forEach(products::add); //fun with Java 8
        return products;
    }

    @Override
    public Product getById(String id) {
        return new Product();
//                productRepository.findById(id).orElse(null);
    }

    @Override
    public Product saveOrUpdate(Product product) {
//        productRepository.save(product);
        return product;
    }

    @Override
    public void delete(String id) {
//        productRepository.deleteById(id);
    }

    @Override
    public Product saveOrUpdateProductForm(ProductForm productForm) {
        Product savedProduct = saveOrUpdate(productFormToProduct.convert(productForm));

        System.out.println("Saved Product Id: " + savedProduct.getId());
        return savedProduct;
    }
}
