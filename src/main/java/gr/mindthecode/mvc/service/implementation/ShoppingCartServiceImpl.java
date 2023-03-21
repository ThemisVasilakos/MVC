package gr.mindthecode.mvc.service.implementation;

import gr.mindthecode.mvc.dto.NewOrderDto;
import gr.mindthecode.mvc.dto.ProductQuantity;
import gr.mindthecode.mvc.model.Orders;
import gr.mindthecode.mvc.model.Product;
import gr.mindthecode.mvc.model.ShoppingCart;
import gr.mindthecode.mvc.model.ShoppingCartPK;
import gr.mindthecode.mvc.repository.OrdersRepository;
import gr.mindthecode.mvc.repository.ProductRepository;
import gr.mindthecode.mvc.repository.ShoppingCartRepository;
import gr.mindthecode.mvc.service.ShoppingCartService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ProductRepository productRepository;
    private OrdersRepository ordersRepository;
    private ShoppingCartRepository shoppingCartRepository;

    private Collection<ProductQuantity> productQuantity;
    private NewOrderDto newOrderDto;

    public ShoppingCartServiceImpl(ProductRepository productRepository, OrdersRepository ordersRepository, ShoppingCartRepository shoppingCartRepository) {
        this.productRepository = productRepository;
        this.ordersRepository = ordersRepository;
        this.shoppingCartRepository = shoppingCartRepository;

        this.productQuantity = new ArrayList<>();
        this.newOrderDto = new NewOrderDto();
    }

    @Override
    @Transactional
    public NewOrderDto createOrder(NewOrderDto newOrderDto) {
        Orders order = new Orders();
        order.setAddress(newOrderDto.getAddress());
        ordersRepository.save(order);
        Double totalCost = 0.0;

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

            ShoppingCartPK shoppingCartPK = new ShoppingCartPK();
            shoppingCartPK.setOrdersId(order.getOrdersId());
            shoppingCartPK.setProductId(check.get().getProductId());

            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setId(shoppingCartPK);
            shoppingCart.setOrder(order);
            shoppingCart.setProducts(check.get());
            shoppingCart.setQuantity(quantity);
            shoppingCartRepository.save(shoppingCart);

        }

        order.setTotalCost(totalCost);
        ordersRepository.save(order);

        return newOrderDto;
    }

    @Override
    public void addProductQuantity(Integer id) {
        ProductQuantity tmp = new ProductQuantity();
        tmp.setProductId(id);
        tmp.setQuantity(1);

        this.productQuantity.add(tmp);
    }

    @Override
    public void buildNewOrderDto(String address, Collection<ProductQuantity> productQuantity) {
        newOrderDto.setAddress(address);
        newOrderDto.setProducts(productQuantity);
    }

    @Override
    public List<ShoppingCart> findAll() {
        return shoppingCartRepository.findAll();
    }

    @Override
    public NewOrderDto getNewOrderDto() {
        return newOrderDto;
    }

    @Override
    public Collection<ProductQuantity> getProductQuantity() {
        return this.productQuantity;
    }

    @Override
    public void reset() {
        this.productQuantity.clear();
    }
}
