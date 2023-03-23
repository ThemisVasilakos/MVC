package gr.mindthecode.mvc.repository;

import gr.mindthecode.mvc.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Page<Product> findByProductDescriptionContaining(String description, Pageable pageable);
    Product findByProductId(Integer id);
    List<Product> findAll();

}
