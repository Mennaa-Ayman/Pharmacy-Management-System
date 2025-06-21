package com.mycompany.pharmacymanagementsystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class MainFX extends Application {
    private Branch branch;
    private Order currentOrder;
    private Inventory inventory;
    private HashMap<String, Medicine> medicineMap;
    private Stage primaryStage;
    private static int rcptNo = 50;
    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage) {
     //// login page ////
        // Left pane/

    Button checkOutButton = createNavigationButton("Check Out");
    setupCheckoutButton(checkOutButton);


    this.primaryStage = stage;
    
    // Initialize business objects
    inventory = new Inventory();
    currentOrder = new Order(inventory);
    medicineMap = new HashMap<>();
    branch = new Branch("New Cairo");
    
    
    // Create medicines and add to map
    Medicine strepsils = new Medicine("MP1", "Strepsils", "GSK", 25, 2, true, "tablet", 30);
    Medicine panadol = new Medicine("MP2", "Panadol Extra", "GSK", 8, 3, false, "tablet", 20);
    Medicine fucicort = new Medicine("MP3", "Fucicort", "GSK", 8, 1, true, "cream", 1);
    Medicine zithromax = new Medicine("MP4", "Zithromax", "GSK", 14, 2, true, "tablet", 15);
    
    medicineMap.put("Strepsils", strepsils);
    medicineMap.put("Panadol Extra", panadol);
    medicineMap.put("Fucicort", fucicort);
    medicineMap.put("Zithromax", zithromax);
    
    // Add to inventory
    inventory.addStock(strepsils, 10);
    inventory.addStock(panadol, 20);
    inventory.addStock(fucicort, 15);
    inventory.addStock(zithromax, 12);



    VBox leftPanel = new VBox(20);
    leftPanel.setPrefWidth(100); 
    leftPanel.setMaxWidth(100); 
    leftPanel.setMaxWidth(Double.MAX_VALUE);
    leftPanel.setAlignment(Pos.CENTER);
    leftPanel.setPadding(new Insets(10));
    leftPanel.setStyle("-fx-background-color: #b6ccd7");  
    Image Logo = new Image("file:/E:/ASU Junior Files/Spring/AdvancedProgramming/PharmacyManagementSystemMarina/PharmacyManagementSystem/src/images/Logo.png");
    ImageView LogoImageView = new ImageView(Logo);
    LogoImageView.setFitWidth(220);
    LogoImageView.setPreserveRatio(true);
    System.out.println("Image loaded: " + (Logo.isError() ? "No" : "Yes"));
    leftPanel.getChildren().addAll(LogoImageView);
    // Right panel
    VBox rightPanel = new VBox(15);
    rightPanel.setPrefWidth(350);
    rightPanel.setMaxWidth(Double.MAX_VALUE);
    rightPanel.setAlignment(Pos.CENTER_LEFT);
    rightPanel.setPadding(new Insets(100, 50, 100, 50)); 
    rightPanel.setStyle("-fx-background-color: white");

    Label emailLabel = new Label("Email");
    emailLabel.setFont(Font.font("Segoe UI", 18));
    TextField emailField = new TextField();
    emailField.setFont(Font.font("Segoe UI", 18));
    emailField.setPromptText("Enter your email");

    Label passwordLabel = new Label("Password");
    passwordLabel.setFont(Font.font("Segoe UI", 18));
    PasswordField passwordField = new PasswordField();
    passwordField.setFont(Font.font("Segoe UI", 18));
    passwordField.setPromptText("Enter your password");
    

    Button loginButton = new Button("Login");
    loginButton.setFont(Font.font("Segoe UI", 18));
    // loginButton.setStyle("-fx-background-color: yellow");

    rightPanel.getChildren().addAll( emailLabel, emailField, passwordLabel, passwordField, loginButton);

    // Layout
    HBox layout = new HBox();
    HBox.setHgrow(leftPanel, Priority.ALWAYS);
    HBox.setHgrow(rightPanel, Priority.ALWAYS);
    layout.getChildren().addAll(leftPanel, rightPanel);

    
    
        /// products page ///
   // Left Navigation Panel
        VBox leftNav = new VBox(10);
        leftNav.setPrefWidth(150); // Adjust width as needed
        leftNav.setStyle("-fx-background-color: #b6ccd7");
        leftNav.setAlignment(Pos.TOP_CENTER);
        leftNav.setPadding(new Insets(20));
        
        Image smallLogo = new Image("file:E:/ASU Junior Files/Spring/AdvancedProgramming/PharmacyManagementSystemMarina/PharmacyManagementSystem/src/images/Logo.png");
        ImageView logoImageView = new ImageView(smallLogo);
        logoImageView.setFitWidth(180);
        logoImageView.setPreserveRatio(true);
        Button homeButton = createNavigationButton("Home");
        Button cartButton = createNavigationButton("Cart");
        Button logoutButton = createNavigationButton("Logout");
        Button aboutUsButton = createNavigationButton("About Us");
        //Button checkOutButton2 = createNavigationButton("Check Out");

        leftNav.getChildren().addAll(logoImageView, homeButton, cartButton, logoutButton, aboutUsButton, checkOutButton); 
        leftNav.setMaxWidth(100); 
        leftNav.setMaxWidth(Double.MAX_VALUE);
        leftNav.setAlignment(Pos.CENTER);
        leftNav.setPadding(new Insets(10));
        leftNav.setStyle("-fx-background-color: #b6ccd7");
            
        GridPane shopGrid = createShopGrid();
        shopGrid.setMaxWidth(100); 
        shopGrid.setMaxWidth(Double.MAX_VALUE);
        shopGrid.setAlignment(Pos.CENTER);
        shopGrid.setPadding(new Insets(20)); 

        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(leftNav);
        mainLayout.setCenter(shopGrid);
        
        // scene and stage 
        var scene1 = new Scene(new StackPane(layout), 800, 600);
        stage.setScene(scene1);
        stage.show();
        var scene2 = new Scene(mainLayout, 800, 600);
        loginButton.setOnAction(e ->stage.setScene(scene2));
        logoutButton.setOnAction(e ->stage.setScene(scene1));
    }
    private Button createNavigationButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(Double.MAX_VALUE);
        return button;
    }

    private GridPane createShopGrid() {
        GridPane shopGrid = new GridPane();
        shopGrid.setHgap(20);
        shopGrid.setVgap(30);
        shopGrid.setAlignment(Pos.TOP_LEFT);

        // "Shop" Header
        Label shopHeader = new Label("Shop");
        shopHeader.setFont(Font.font("Arial", 24));
        shopHeader.setAlignment(Pos.CENTER); 
        shopGrid.add(shopHeader, 0, 0);
        
        shopGrid.add(createProductView("Strepsils", "$25", "file:E:\\ASU Junior Files\\Spring\\AdvancedProgramming\\PharmacyManagementSystemMarina\\PharmacyManagementSystem\\src\\images\\Strepsils.jpg"), 0, 1);
        shopGrid.add(createProductView("Panadol Extra", "$8", "file:/E:\\ASU Junior Files\\Spring\\AdvancedProgramming\\PharmacyManagementSystemMarina\\PharmacyManagementSystem\\src\\images\\Panadol.jpg"), 1, 1);
        shopGrid.add(createProductView("Fucicort", "$8", "file:E:\\ASU Junior Files\\Spring\\AdvancedProgramming\\PharmacyManagementSystemMarina\\PharmacyManagementSystem\\src\\images\\Fucicort.png"), 0, 2);
        shopGrid.add(createProductView("Zithromax", "$14", "file:E:\\ASU Junior Files\\Spring\\AdvancedProgramming\\PharmacyManagementSystemMarina\\PharmacyManagementSystem\\src\\images\\Zithromax.jpg"), 1, 2);
        
        return shopGrid;
}
    private GridPane createProductView(String name, String price, String imagePath) {
        GridPane productGrid = new GridPane();
        productGrid.setAlignment(Pos.CENTER);
        productGrid.setHgap(30);
        productGrid.setVgap(30);
        productGrid.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        productGrid.setMaxWidth(Double.MAX_VALUE);
        productGrid.setAlignment(Pos.CENTER);
        productGrid.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Image (0, 0) - Make it square
        Image productImage = new Image(imagePath);
        ImageView productImageView = new ImageView(productImage);
        productImageView.setFitWidth(80); 
        productImageView.setFitHeight(80);
        productImageView.setPreserveRatio(true);
        productGrid.add(productImageView, 0, 0, 2, 1); 
        
         // Quantity (0, 1)
        Label quantityLabel = new Label("Qty:");
        TextField quantityField = new TextField("1");
        quantityField.setPrefWidth(50);
        HBox quantityBox = new HBox(5, quantityLabel, quantityField);
        quantityBox.setAlignment(Pos.CENTER_LEFT);
        productGrid.add(quantityBox, 0, 1);

        // Text Info (Name and Price) (1, 0)
        Label nameLabel = new Label(name);
        nameLabel.setFont(Font.font("Arial", 14));
        Label priceLabel = new Label(price);
        priceLabel.setFont(Font.font("Arial", 12));
        VBox textInfoBox = new VBox(2, nameLabel, priceLabel);
        productGrid.add(textInfoBox, 1, 0);
        
        // Add Button (1, 1)
        Button addButton = new Button("ADD");
        addButton.setMaxWidth(Double.MAX_VALUE);
        productGrid.add(addButton, 1, 1);

        addButton.setOnAction(e -> {
        try {
            int quantity = Integer.parseInt(quantityField.getText());
            Medicine medicine = medicineMap.get(name);
            if (medicine != null) {
                currentOrder.addItem(medicine, quantity);
                showAlert("Success", quantity + " " + name + " added to cart");
            }
        } catch (NumberFormatException ex) {
            showAlert("Error", "Please enter a valid quantity");
        } catch (Exception ex) {
            showAlert("Error", ex.getMessage());
        }
    });

        return productGrid;
    }
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
    private void setupCheckoutButton(Button checkOutButton) {
    checkOutButton.setOnAction(e -> {
        if (currentOrder.getItems().isEmpty()) {
            showAlert("Error", "Cart is empty!");
            return;
        }

        // Create receipt window
        Stage receiptStage = new Stage();
        
        // Main layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: white;");
        
        Label pharmacyLabel = new Label(((Pharmacy)branch).getName());
        Label branchLabel = new Label(branch.getLocation());
        
        // Receipt data
        Label receiptLabel = new Label("RECEIPT");
        VBox receiptData = new VBox();
        receiptData.setPadding(new Insets(10,0,10,0));
        Label receiptNumLabel = new Label("Receipt #: RCPT-2025-"+rcptNo++); 
        Label dateLabel = new Label("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        receiptData.getChildren().addAll(receiptLabel, receiptNumLabel, dateLabel);
        
        // Items list
        VBox items = new VBox(5);
        items.setAlignment(Pos.CENTER_LEFT);
        Label itemsTitle = new Label("ITEMS PURCHASED");
        Separator itemsSeparator = new Separator();
        
        VBox headerRow = new VBox();
        headerRow.setPadding(new Insets(0,0,5,0));
        Label itemsheader = new Label(String.format("%-60s %10s","Item","Price"));
        headerRow.getChildren().add(itemsheader);
        
        items.getChildren().addAll(itemsTitle, itemsSeparator, headerRow);
        
        VBox itemsList = new VBox(5);
        for (Item item : currentOrder.getItems()) {
            Label itemLabel = new Label(String.format("%-60s %10.2f", 
                item.getName(), item.getPrice()));
            itemsList.getChildren().add(itemLabel);
        }
        Separator separator = new Separator();
        
        // Total and ending
        Separator totalSeparator = new Separator();
        double total = currentOrder.calculateOrderTotal();
        Label totalLabel = new Label(String.format("%-60s %10.2f","TOTAL", total));
        Label endLabel = new Label("Wishing you a speedy recovery!");
        
        // Payment options
        Label paymentLabel = new Label("Select Payment Method:");
        paymentLabel.setFont(Font.font("Arial", 14));
        
        Button cashButton = new Button("Cash");
        Button cardButton = new Button("Card");
        
        HBox paymentButtons = new HBox(10, cashButton, cardButton);
        paymentButtons.setAlignment(Pos.CENTER);
        
        layout.getChildren().addAll(pharmacyLabel, branchLabel, separator, receiptData, 
            items, itemsList, totalSeparator, totalLabel, endLabel, 
            new Separator(), paymentLabel, paymentButtons);

        // Payment handlers
        cashButton.setOnAction(cashEvent -> {
            TextField amountField = new TextField();
            amountField.setPromptText("Enter cash amount");
            Button submitCash = new Button("Pay");
            
            submitCash.setOnAction(submitEvent -> {
                try {
                    double cashAmount = Double.parseDouble(amountField.getText());
                    Cash cashPayment = new Cash(total);
                    
                    if (cashPayment.pay(cashAmount)) {
                        currentOrder.finalizeOrder(cashPayment, cashAmount);
                        receiptStage.close();
                        showAlert("Success", "Payment successful! Thank you for your purchase.");
                        currentOrder = new Order(inventory);
                    } else {
                        showAlert("Error", "Insufficient cash amount");
                    }
                } catch (NumberFormatException ex) {
                    showAlert("Error", "Please enter a valid amount");
                }
            });
            
            VBox cashBox = new VBox(10, new Label("Enter Cash Amount:"), amountField, submitCash);
            cashBox.setPadding(new Insets(10));
            layout.getChildren().add(cashBox);
        });

        cardButton.setOnAction(cardEvent -> {
            TextField cardBalanceField = new TextField();
            cardBalanceField.setPromptText("Enter card balance");
            Button submitCard = new Button("Process Payment");
            
            submitCard.setOnAction(submitEvent -> {
                try {
                    double cardBalance = Double.parseDouble(cardBalanceField.getText());
                    Card cardPayment = new Card(total, cardBalance);
                    
                    if (cardPayment.pay(total)) {
                        currentOrder.finalizeOrder(cardPayment, total);
                        receiptStage.close();
                        showAlert("Success", "Card payment successful! Thank you for your purchase.");
                        currentOrder = new Order(inventory);
                    } else {
                        showAlert("Error", "Insufficient card balance");
                    }
                } catch (NumberFormatException ex) {
                    showAlert("Error", "Please enter a valid amount");
                }
            });
            
            VBox cardBox = new VBox(10, new Label("Enter Card Balance:"), cardBalanceField, submitCard);
            cardBox.setPadding(new Insets(10));
            layout.getChildren().add(cardBox);
        });

        Scene receiptScene = new Scene(layout, 400, 600);
        receiptStage.setScene(receiptScene);
        receiptStage.show();
    });
}
    
}


