package Entity;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class MakeOrder 
{
    private Customer customer;
    private Fooditem[] items;
    private String restaurant;
    private int itemCount;
    private double total;
    private String orderId;
    
    public MakeOrder(Customer customer, String restaurant)
    {
        this.customer = customer;
        this.restaurant = restaurant;
        this.items = new Fooditem[10];
        this.itemCount = 0;
        this.total = 0;
        this.orderId = "ORD" + System.currentTimeMillis();
    }
    
    public void addItem(Fooditem item) 
    {
        if (itemCount < items.length) 
        {
            items[itemCount] = item;
            itemCount++;
            calculateTotal();
        }
    }
    
    private void calculateTotal()
    {
        total = 0;
        for (int i = 0; i < itemCount; i++) 
        {
            total += items[i].getFinalPrice(); // Use discounted price
        }
        total += 60; // Delivery charge
    }
    
    public double getTotal() 
    {
        return total;
    }
    
    public int getItemCount() 
    {
        return itemCount;
    }
    
    public String getOrderId() 
    {
        return orderId;
    }
    
    public Customer getCustomer() 
    {
        return customer;
    }
    
    public String getOrderSummary() 
    {
        String summary = "";
        summary += "=== FAV PANDA ORDER ===\n";
        summary += "Order ID: " + orderId + "\n";
        summary += customer.toString() + "\n";
        summary += "Restaurant: " + restaurant + "\n\n";
        summary += "Items:\n";
        
        double itemsTotal = 0;
        for (int i = 0; i < itemCount; i++) 
        {
            summary += "• " + items[i].toString() + "\n";
            itemsTotal += items[i].getFinalPrice();
        }
        
        summary += "\nSubtotal: tk" + String.format("%.0f", itemsTotal) + "\n";
        summary += "Delivery: tk60\n";
        summary += "Total: tk" + String.format("%.0f", total) + "\n";
        summary += "==========================================";
        
        return summary;
    }
    
    public void saveOrder() 
    {
        try {
            File file = new File("./Data/orders.txt");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            
            FileWriter fwriter = new FileWriter(file, true);
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm a, dd/MM/yyyy");
            String timeDate = now.format(formatter);
            
            fwriter.write("Date and Time: " + timeDate + "\n");
            fwriter.write("Order ID: " + orderId + "\n");
            fwriter.write("========================================\n");
          
            fwriter.write("Customer: " + customer.getName() + "\n");
            fwriter.write("Address: " + customer.getAddress() + "\n");
            fwriter.write("Phone: " + customer.getPhone() + "\n");
            fwriter.write("Restaurant: " + restaurant + "\n");
            fwriter.write("Items:\n");
            
            double itemsTotal = 0;
            for (int i = 0; i < itemCount; i++) {
                fwriter.write(" - " + items[i].toString() + "\n");
                itemsTotal += items[i].getFinalPrice();
            }
            
            fwriter.write("Subtotal: tk" + String.format("%.0f", itemsTotal) + "\n");
            fwriter.write("Delivery: tk60\n");
            fwriter.write("Total: tk" + String.format("%.0f", total) + "\n");
            fwriter.write("========================================\n\n");
            fwriter.flush();
            fwriter.close();
            
        } catch (IOException e) 
        {
            JOptionPane.showMessageDialog(null, "Error saving order to file!");
        }
    }
}