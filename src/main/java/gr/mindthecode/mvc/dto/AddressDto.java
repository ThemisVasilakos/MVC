package gr.mindthecode.mvc.dto;

import java.io.Serializable;

public class AddressDto implements Serializable {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
