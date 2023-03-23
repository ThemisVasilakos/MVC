package gr.mindthecode.mvc.service;

import gr.mindthecode.mvc.model.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    Product createOrUpdateProduct(Integer id,Product product) throws Exception;
    void deleteProduct(Integer id);
    public abstract Product getProductById(Integer id);
    Page<Product> getProducts(String description,
                              int page,
                              int size,
                              String sort);
}
