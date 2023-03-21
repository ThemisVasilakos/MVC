package gr.mindthecode.mvc.dto;

import java.io.Serializable;

public class ProductQuantity implements Serializable {
    private Integer productId;
    private Integer quantity=1;

    //Getters and Setters
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String toString(){//overriding the toString() method
        return "ProductID: "+ productId+ " Quantity: "+ quantity;
    }
}
