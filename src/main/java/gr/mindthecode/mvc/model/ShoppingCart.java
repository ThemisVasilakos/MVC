package gr.mindthecode.mvc.model;

import jakarta.persistence.*;
import org.springframework.context.annotation.Lazy;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shoppingCartId;

    @ManyToOne
    @Lazy(false)
    @MapsId("orders_id")
    private Orders order;

    @ManyToOne
    @Lazy(false)
    @MapsId("product_id")
    private Product products;

    private Integer quantity;

    //Constructor
    public ShoppingCart() {
    }

    //Getters and Setters

    public Integer getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Integer shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }

}
