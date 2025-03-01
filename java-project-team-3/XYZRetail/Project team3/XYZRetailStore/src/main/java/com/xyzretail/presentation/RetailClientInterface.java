package com.xyzretail.presentation;

import java.util.Scanner;

public interface RetailClientInterface {

    void startShoppingExperience();

    void collectCustomerInfo();

    void displayMenu();

    void handleCategorySelection();

    void handleAddItemToCart();

	void collectCustomerInfo(Scanner scanner);
}
