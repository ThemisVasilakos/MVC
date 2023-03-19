package gr.mindthecode.mvc.service;

import gr.mindthecode.mvc.dto.NewOrderDto;
import gr.mindthecode.mvc.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    NewOrderDto createOrder(NewOrderDto newOrderDto);
    List<ShoppingCart> findAll();
}
