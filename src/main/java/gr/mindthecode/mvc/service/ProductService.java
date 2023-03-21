package gr.mindthecode.mvc.service;

import gr.mindthecode.mvc.dto.DisplayProductDto;
import gr.mindthecode.mvc.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product createOrUpdateProduct(Integer id,Product product) throws Exception;
    void deleteProduct(Integer id);
    Product getProductById(Integer id);
    Page<Product> getProducts(Double productPrice,
                              int page,
                              int size,
                              String sort);
}
