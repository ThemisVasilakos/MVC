package gr.mindthecode.mvc.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shoppingCartId;

    @ManyToOne
    @Lazy(false)
    private Orders order;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    @JoinTable(
            name = "cart_product",
            joinColumns = @JoinColumn(name = "shopping_cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    @JsonManagedReference
    private List<Product> products;

    private Integer quantity;

    //Constructor
    public ShoppingCart() {
        this.products = new ArrayList<>();
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

    public void addProduct(Product product){
        this.products.add(product);
    }
}
