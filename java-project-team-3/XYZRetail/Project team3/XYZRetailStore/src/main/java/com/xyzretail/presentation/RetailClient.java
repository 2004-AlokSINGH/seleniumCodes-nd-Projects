package com.xyzretail.presentation;

import com.xyzretail.exceptions.EmptyCartException;
import com.xyzretail.service.ShoppingService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RetailClient {
    private static String selectedCategory = null;
    private static String customerName;
    private static String contact;
    private static String email;
    private static String address;

    private enum Category {
        BOOKS("Books"),
        CDS("CDs"),
        COSMETICS("Cosmetics"),
        STATIONERY("Stationery");

        private final String displayName;

        Category(String displayName) {
            this.displayName = displayName;
        }

        public static String getDisplayName(int number) {
            if (number < 1 || number > values().length) {
                return null;
            }
            return values()[number - 1].displayName;
        }

        public static void displayCategories() {
            System.out.println("Available Categories:");
            int i = 1;
            for (Category category : values()) {
                System.out.println(i++ + ". " + category.displayName);
            }
        }
    }

    public void startShoppingExperience() throws EmptyCartException {
        ShoppingService shoppingService = new ShoppingService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Welcome to XYZ Retail Shopping System ===");
        boolean exit = false;

        while (!exit) {
            displayMenu();

            int choice = getValidIntegerInput(scanner, "Enter your choice: ");
            if (choice == -1) continue;

            switch (choice) {
                case 1:
                    handleCategorySelection(shoppingService, scanner);
                    break;
                case 2:
                    if (selectedCategory == null) {
                        System.out.println("Please view a category first to select items.");
                        break;
                    }
                    handleAddItemToCart(shoppingService, scanner);
                    break;
                case 3:
                    collectCustomerInfo(scanner);
                    shoppingService.storeCustomerAndOrderDetails(customerName, contact, email, address);
                    shoppingService.generateFinalBill();
                    selectedCategory = null;
                    break;
                case 4:
                    exit = true;
                    System.out.println("Thank you for shopping at XYZ Retail!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        shoppingService.closeService();
        scanner.close();
    }

    private void collectCustomerInfo(Scanner scanner) {
        System.out.println("\nPlease provide your details to proceed:");
        System.out.print("Name: ");
        customerName = scanner.nextLine();
        System.out.print("Contact Number: ");
        contact = scanner.nextLine();
        System.out.print("Email: ");
        email = scanner.nextLine();
        System.out.print("Address: ");
        address = scanner.nextLine();
    }

    private void displayMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1. View available items in a category");
        System.out.println("2. Add item to cart");
        System.out.println("3. Generate and view final bill");
        System.out.println("4. Exit");
    }

    private int getValidIntegerInput(Scanner scanner, String prompt) {
        int value = -1;
        try {
            System.out.print(prompt);
            value = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
        return value;
    }

    private void handleCategorySelection(ShoppingService shoppingService, Scanner scanner) {
        Category.displayCategories();
        int categoryNumber = getValidIntegerInput(scanner, "Enter category number: ");
        if (categoryNumber == -1) return;

        selectedCategory = Category.getDisplayName(categoryNumber);
        if (selectedCategory == null) {
            System.out.println("Invalid category number!");
            return;
        }
        shoppingService.displayAvailableItemsByCategory(selectedCategory);
    }

    private void handleAddItemToCart(ShoppingService shoppingService, Scanner scanner) {
        System.out.println("Adding item to cart for category: " + selectedCategory);
        int itemId = getValidIntegerInput(scanner, "Enter item ID you want to buy: ");
        if (itemId == -1) return;

        int quantity = getValidIntegerInput(scanner, "Enter quantity: ");
        if (quantity == -1) return;

        shoppingService.addItemToCart(selectedCategory, itemId, quantity);
    }
}
