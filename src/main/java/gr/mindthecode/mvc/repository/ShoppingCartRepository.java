package gr.mindthecode.mvc.repository;

import gr.mindthecode.mvc.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Integer> {
    List<ShoppingCart> findAll();
}
