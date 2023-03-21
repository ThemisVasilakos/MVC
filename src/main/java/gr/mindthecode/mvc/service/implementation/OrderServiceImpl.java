package gr.mindthecode.mvc.service.implementation;

import gr.mindthecode.mvc.model.Orders;
import gr.mindthecode.mvc.repository.OrdersRepository;
import gr.mindthecode.mvc.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrdersRepository ordersRepository;

    public OrderServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }
}
