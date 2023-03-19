package gr.mindthecode.mvc.service.implementation;

import gr.mindthecode.mvc.model.Product;
import gr.mindthecode.mvc.repository.ProductRepository;
import gr.mindthecode.mvc.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createOrUpdateProduct(Integer id,Product product) throws Exception {
        if (id != null) {
            if (!id.equals(product.getProductId())) {
                throw new Exception("id in path does not patch id in body");
            }
        }
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        Product match = productRepository.findById(id).orElseThrow();
        productRepository.delete(match);
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public Page<Product> getProducts(Double productPrice, int page, int size, String sort) {
        PageRequest paging = PageRequest
                .of(page, size)
                .withSort(sort.equalsIgnoreCase("ASC") ?
                        Sort.by("productPrice").ascending() :
                        Sort.by("productPrice").descending());

        Page<Product> res;
        if (productPrice == null) {
            res = productRepository.findAll(paging);
        } else {
            res = productRepository.findByProductPrice(productPrice, paging);
        }

        return res;
    }


}
