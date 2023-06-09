package hu.docker.services;

import hu.docker.domain.Product;
import hu.docker.domain.ProductForm;
import java.util.List;

public interface ProductService {

    List<Product> listAll();

    Product getById(String id);

    Product saveOrUpdate(Product product);

    void delete(String id);

    Product saveOrUpdateProductForm(ProductForm productForm);
}
