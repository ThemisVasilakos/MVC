package gr.mindthecode.mvc.controller;

import gr.mindthecode.mvc.dto.NewOrderDto;
import gr.mindthecode.mvc.model.Product;
import gr.mindthecode.mvc.model.ShoppingCart;
import gr.mindthecode.mvc.repository.ShoppingCartRepository;
import gr.mindthecode.mvc.service.ProductService;
import gr.mindthecode.mvc.service.ShoppingCartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Test {

    private ProductService productService;
    private ShoppingCartService shoppingCartService;
    private ShoppingCartRepository cartRepository;

    public Test(ProductService productService, ShoppingCartService shoppingCartService) {
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/product")
    public Product createProduct(@RequestBody Product product) throws Exception {
        return productService.createOrUpdateProduct(null,product);
    }

    @PostMapping("/order")
    public NewOrderDto createOrder(@RequestBody NewOrderDto newOrderDto) throws Exception {
        return shoppingCartService.createOrder(newOrderDto);
    }

    @GetMapping("/order")
    public List<ShoppingCart> getOrders(){
        return shoppingCartService.findAll();
    }


}
