package com.mycompany.pharmacymanagementsystem;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //----------------Pharmacy-------------------------
        Branch branch = new Branch("New Cairo");
        branch.printPharmacyName();
        branch.printOwnerName();
        System.out.println(branch.getLocation() + "\n\n");
        //-------------------------------------------------
        //----------------Branch----------------------------
        System.out.println("\n===================\nLogin Validation Feature\n===================\n");

        Admin admin1 = new Admin("Karim", 30_000);
        Admin admin2 = new Admin("Marina", 30_000);
        Customer cs1 = new Customer("Karim");
        Customer cs2 = new Customer("Menna");
        admin1.getAdminInfo();

        branch.addAdmin(admin1);
        
        branch.addCustomer(cs1);
        branch.addCustomer(cs2);
        //-------------------------------------------------
        //=========================Creating Test Database using Copilot=========================================
        Medicine panadol = new Medicine("MP0", "Panadol", "PanadolCorporation", 40, 3, false, "tablet", 20);
        Medicine augmentin = new Medicine("MP1", "Strepsils", "GSK", 85.50, 2, true, "tablet", 30);
        Medicine ventolin = new Medicine("MP2", "Ventolin", "GSK", 120, 1, false, "inhaler", 2);

        Supplements omega3 = new Supplements("SP0", "Omega-3", "Nature's Way", 199.99, 1, false, "capsule", 1, "Fish Oil");
        Supplements vitaminD = new Supplements("SP1", "Vitamin D3", "NOW Foods", 149.99, 2, false, "tablet", 1, "Vitamin");
        Supplements vitaminB = new Supplements("SP2", "Vitamin B3", "NOW Foods", 149.99, 2, false, "tablet", 1, "Vitamin");

        BabyCare diapers = new BabyCare("BP0", "Premium Diapers", "Pampers", 129.99, 5, false, "Diaper");
        BabyCare babyLotion = new BabyCare("BP1", "Baby Lotion", "Johnson's", 45.99, 3, false, "Skincare");

        PersonalCare toothpaste = new PersonalCare("PP0", "Fresh Mint", "Colgate", 25.99, 2, false, "Oral Care");
        PersonalCare shampoo = new PersonalCare("PP1", "Bobana Shampoo", "Head & Shoulders", 59.99, 3, false, "Hair Care");

        Devices thermometer = new Devices("Digital Thermometer", "Omron", "Temperature Measurement", true);
        Devices bpMonitor = new Devices("BP Monitor", "Microlife", "Blood Pressure Measurement", true);
        
        // Create and setup inventory
        Inventory inventory = new Inventory();
        inventory.addStock(panadol, 10);
        inventory.addStock(augmentin, 5);
        inventory.addStock(ventolin, 3);
        inventory.addStock(omega3, 4);
        inventory.addStock(vitaminD, 6);
        inventory.addStock(diapers, 20);
        inventory.addStock(babyLotion, 7);
        inventory.addStock(toothpaste, 15);
        inventory.addStock(shampoo, 10);
        inventory.addDevice(thermometer, 2);
        //==================================================================
        branch.setInventory(inventory);

        //=========================Customer Making An Order=========================================
        System.out.println("\n--------------\nCustomer Making an Order\n--------------\n");
        Order dummyOrder = new Order(inventory); //Initializing order class with our current inventory.

        
        // Create initial "cart" of items.
        ArrayList<Item> o1 = new ArrayList<>();
        o1.add(panadol);
        o1.add(vitaminD);
        o1.add(diapers);

        //Make an order with initial item
        cs1.makeOrder(o1);
        System.out.println(cs1.getOrder().getItems());//Printing initial items in basket.

        //Add an extra item to order
        cs1.getOrder().addItem(shampoo, 2);
        System.out.println(cs1.getOrder().getItems());//Printing items to show item was added successfuly


        //Get order's total
        double cs1O1Total = cs1.getOrder().calculateOrderTotal();
        
        //New transaction created, of type cash, which needs required amount for order.
        Cash cs1Cash = new Cash(cs1O1Total); 

        //Sorting items by price, makes it look neater in receipt.
        cs1.getOrder().getItems().sort(null);

        //Finalizing transaction.
        cs1.getOrder().finalizeOrder(cs1Cash, 200); //Finalizing transaction with less than required amount to test Negative case.

        cs1.getOrder().finalizeOrder(cs1Cash, cs1O1Total); //Finalizing transaction with correct amount to test Positive case.

        System.out.println("\n--------------\nPrinting Medicine stock after order to show Stock Tracking Feature\n--------------\n");
        inventory.showMedStock();;


        System.out.println("\n--------------\nSecond Customer Making Order to show Inventory Deducting Feature\n--------------\n");
        ArrayList<Item> o2 = new ArrayList<>();
        o2.add(panadol);
        o2.add(vitaminD);
        o2.add(diapers);

        cs2.makeOrder(o2);
        System.out.println(cs2.getOrder().getItems());

        cs2.getOrder().addItem(shampoo, 10); //Items won't be added. Not enough shampoos in inventory.
        cs2.getOrder().addItem(vitaminB, 3); //Items won't be added. No vitaminB stock in inventory.

        System.out.println(cs2.getOrder().getItems()); //Printing items to show Inventory Tracking is successful.

        double cs2o2Total = cs2.getOrder().calculateOrderTotal(); // Total is $319.98
        Card cs2Card = new Card(cs2o2Total, 300); // Customer provides card with $300 balance

        cs2.getOrder().finalizeOrder(cs2Card, 300); //Customer tries to pay 330, even though his card has only 300.

        Card cs2Card2 = new Card(cs2o2Total, 3000);

        cs2.getOrder().finalizeOrder(cs2Card2, cs2o2Total);

        System.out.println("\n===============================\nFinancial Statements\n===============================\n");

        //Setting sales and expenses for yesterday and today. Still haven't linked with customer's orders.
        LocalDate may14 = LocalDate.of(2025, 5, 14);
        LocalDate may15 = LocalDate.of(2025, 5, 15);
        LocalDate may16 = LocalDate.of(2025, 5, 16);

        

        Sales may14Sales = new Sales(may14);
        Sales may15Sales = new Sales(may15);
        //Let's record order1 for may 14 sales and order2 for may15 sales
        may14Sales.addToTotalSalesToday(cs1O1Total);
        may15Sales.addToTotalSalesToday(cs2o2Total);


        //Constructor immediately sets the expenses for the day
        Expenses may14Expenses = new Expenses(may14, 100, 50);
        Expenses may15Expenses = new Expenses(may15, 1150, 50);

        
        //You can print out companies financials indvidiually
        Financials may14Financials = new Financials(may14);
        System.out.println("\n---------\nFinancials of pharmacy at start of may 14\n---------");
        System.out.println("Revenue: " + may14Financials.getRevenue());
        System.out.println("Expenses: " + may14Financials.getExpenses());
        System.out.println("Profit: " + may14Financials.getProfit());

        Financials may15Financials = new Financials(may15);
        System.out.println("\n---------\nFinancials of pharmacy at start of may 15\n---------");
        System.out.println("Revenue: " + may15Financials.getRevenue());
        System.out.println("Expenses: " + may15Financials.getExpenses());
        System.out.println("Profit: " + may15Financials.getProfit());


        //Or you can just use the toString method to get the same description
        Financials may16Financials = new Financials(may16);
        System.out.println("\n---------\nFinancials of pharmacy at start of may 16\n---------");
        System.out.println(may16Financials);
        

        //Now let's add these financials to the branch
        branch.addFinancials(may15Financials);
        branch.addFinancials(may16Financials);

        System.out.println("\n---------\nFull Financials List of the branch\n---------");
        System.out.println(branch.getFinancials());

    }
    
}
