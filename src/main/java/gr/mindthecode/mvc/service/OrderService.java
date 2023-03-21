package gr.mindthecode.mvc.service;

import gr.mindthecode.mvc.model.Orders;

import java.util.List;

public interface OrderService {
    List<Orders> findAll();
}
