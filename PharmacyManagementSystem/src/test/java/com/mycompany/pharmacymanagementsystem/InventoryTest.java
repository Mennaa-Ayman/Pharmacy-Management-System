package com.mycompany.pharmacymanagementsystem;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Karim Maaty
 */
public class InventoryTest {
    
    /**
     * Test of isAvailable method, of class Inventory.
     */
    @Test
    public void nullPointerTotestIsAvailable() {
        System.out.println("isAvailable(1)");
        Item item = null;
        Inventory instance = new Inventory();
        assertThrows(NullPointerException.class, ()->{
            boolean result = instance.isAvailable(item);
        });
    }
    
    @Test
    public void trueTestIsAvailable(){
        System.out.println("isAvailable(2)");
        Item item = new Medicine("MP0", "Panadol", "PanadolCorporation", 
                                40, 3, false, "tablet", 20);
        Inventory inv = new Inventory();
        inv.addStock(item, 20);
        assertEquals(true, inv.isAvailable(item));
    }
    
    @Test
    public void falseTestIsAvailable(){
        System.out.println("isAvailable(3)");
        Item item = new Medicine("MP0", "Panadol", "PanadolCorporation", 
                                40, 3, false, "tablet", 20);
        Inventory inv = new Inventory();
        assertEquals(false, inv.isAvailable(item));
    }    
} 
    

