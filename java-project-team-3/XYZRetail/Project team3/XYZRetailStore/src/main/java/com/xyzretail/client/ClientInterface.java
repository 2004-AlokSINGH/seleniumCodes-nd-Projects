package com.xyzretail.client;

import com.xyzretail.exceptions.EmptyCartException;
import com.xyzretail.presentation.RetailClient;

public class ClientInterface {
    public static void main(String[] args) {
        RetailClient client = new RetailClient();
        try {
			client.startShoppingExperience();
		} catch (EmptyCartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}