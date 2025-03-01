package com.xyzretail.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customers {
    private int customerId;
    private String name;
    private String contact;
    private String email;
    private String address;
	@Override
	public String toString() {
		return "Customers [customerId=" + customerId + ", name=" + name + ", contact=" + contact + ", email=" + email
				+ ", address=" + address + "]";
	}
    
    
}

