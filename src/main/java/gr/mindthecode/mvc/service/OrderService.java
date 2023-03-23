package gr.mindthecode.mvc.service;

import gr.mindthecode.mvc.model.Orders;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {
    List<Orders> findAll();
    Page<Orders> getOrders(String address,
                              int page,
                              int size,
                              String sort);
}
