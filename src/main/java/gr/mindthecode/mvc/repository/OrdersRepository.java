package gr.mindthecode.mvc.repository;

import gr.mindthecode.mvc.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Integer> {
    Page<Orders> findByAddressContaining(String address, Pageable pageable);
    List<Orders> findAll();
}
