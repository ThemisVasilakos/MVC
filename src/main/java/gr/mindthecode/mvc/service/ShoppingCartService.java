package gr.mindthecode.mvc.service;

import gr.mindthecode.mvc.dto.DisplayProductDto;
import gr.mindthecode.mvc.dto.NewOrderDto;
import gr.mindthecode.mvc.dto.ProductQuantity;
import gr.mindthecode.mvc.model.Product;
import gr.mindthecode.mvc.model.ShoppingCart;

import java.util.Collection;
import java.util.List;

public interface ShoppingCartService {
    NewOrderDto createOrder(NewOrderDto newOrderDto);
    List<ShoppingCart> findAll();
    void addProductQuantity(Integer id);
    void buildNewOrderDto(String address,Collection<ProductQuantity> productQuantity);
    NewOrderDto getNewOrderDto();
    Collection<ProductQuantity> getProductQuantity();
    void reset();
}
