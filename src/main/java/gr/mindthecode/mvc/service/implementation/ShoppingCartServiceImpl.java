package gr.mindthecode.mvc.service.implementation;

import gr.mindthecode.mvc.dto.NewOrderDto;
import gr.mindthecode.mvc.dto.ProductQuantity;
import gr.mindthecode.mvc.model.Orders;
import gr.mindthecode.mvc.model.Product;
import gr.mindthecode.mvc.model.ShoppingCart;
import gr.mindthecode.mvc.repository.OrdersRepository;
import gr.mindthecode.mvc.repository.ProductRepository;
import gr.mindthecode.mvc.repository.ShoppingCartRepository;
import gr.mindthecode.mvc.service.ShoppingCartService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ProductRepository productRepository;
    private OrdersRepository ordersRepository;
    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartServiceImpl(ProductRepository productRepository, OrdersRepository ordersRepository, ShoppingCartRepository shoppingCartRepository) {
        this.productRepository = productRepository;
        this.ordersRepository = ordersRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    @Transactional
    public NewOrderDto createOrder(NewOrderDto newOrderDto) {
        Orders order = new Orders();
        order.setAddress(newOrderDto.getAddress());

        Double totalCost = 0.0;

        ShoppingCart shoppingCart = new ShoppingCart();

        ArrayList<ProductQuantity> products = (ArrayList<ProductQuantity>) newOrderDto.getProducts();
        for(int i=0;i<products.size();i++){
            Integer tmp = products.get(i).getProductId();
            //Check if product exists
            Optional<Product> check = productRepository.findById(tmp);
            if(check.isEmpty()){
                throw new RuntimeException("Product doesnot exist");
            }
            Integer quantity = products.get(i).getQuantity();
            totalCost=totalCost + (check.get().getProductPrice() * quantity);

            shoppingCart.setOrder(order);
            shoppingCart.addProduct(check.get());
            shoppingCart.setQuantity(quantity);
            shoppingCartRepository.save(shoppingCart);


        }

        order.setTotalCost(totalCost);
        ordersRepository.save(order);

        return newOrderDto;
    }

    @Override
    public List<ShoppingCart> findAll() {
        return shoppingCartRepository.findAll();
    }
}
