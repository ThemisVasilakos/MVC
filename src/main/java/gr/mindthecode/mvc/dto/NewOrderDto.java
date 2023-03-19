package gr.mindthecode.mvc.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class NewOrderDto implements Serializable {
    private String address;
    private Collection<ProductQuantity> products;

    public NewOrderDto() {
        this.products = new ArrayList<>();
    }

    //Getters and Setters

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Collection<ProductQuantity> getProducts() {
        return products;
    }

    public void setProducts(Collection<ProductQuantity> products) {
        this.products = products;
    }
}
