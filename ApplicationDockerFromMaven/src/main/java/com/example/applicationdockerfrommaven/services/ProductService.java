package com.example.applicationdockerfrommaven.services;

import com.example.applicationdockerfrommaven.domain.Product;
import com.example.applicationdockerfrommaven.domain.ProductForm;
import java.util.List;

public interface ProductService {

    List<Product> listAll();

    Product getById(String id);

    Product saveOrUpdate(Product product);

    void delete(String id);

    Product saveOrUpdateProductForm(ProductForm productForm);
}
