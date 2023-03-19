package gr.mindthecode.mvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.context.annotation.Lazy;

import java.io.Serializable;

@Entity
public class ShoppingCart {

    @EmbeddedId
    private ShoppingCartPK id;

    @ManyToOne
    @Lazy(false)
    @MapsId("orders_id")
    @JsonIgnore
    private Orders order;

    @ManyToOne
    @Lazy(false)
    @MapsId("product_id")
    @JsonIgnore
    private Product products;

    private Integer quantity;

    //Constructor
    public ShoppingCart() {
    }

    //Getters and Setters


    public ShoppingCartPK getId() {
        return id;
    }

    public void setId(ShoppingCartPK id) {
        this.id = id;
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
