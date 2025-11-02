package Frame;

import Entity.*;
import java.lang.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Gui extends JFrame implements ActionListener
{
    private Font f1, f2;
    private Color c1, c2;
    private JPanel panel;
    private JLabel label1, label2, label3, label4, label5, label6, label7, menuLabel;
    private JTextField nameField, addressField, phoneField;
    private JButton confirmBtn, cancelBtn, menuBtn1, menuBtn2, menuBtn3;
    private JRadioButton pizzaRadio, burgerRadio, biryaniRadio, ramenRadio;
    private ButtonGroup categoryGroup;
    private JComboBox restaurantCombo;
    private JTextArea displayArea;
    private JScrollPane scrollPane;
    
    private String[] pizzaRestaurants = {"Pizza Palace", "Tony's Pizza", "Pizza Corner"};
    private String[] burgerRestaurants = {"Burger Barn", "King Burger", "Burger House"};
    private String[] biryaniRestaurants = {"Royal Biryani", "Hyderabad House", "Biryani Express"};
    private String[] ramenRestaurants = {"Ramen Corner", "Tokyo Ramen", "Noodle House"};
    
    private String[][] pizzaMenus = {
        {"Margherita Pizza", "Pepperoni Pizza", "Supreme Pizza"},
        {"Classic Pizza", "Veggie Pizza", "Meat Lovers"},
        {"Italian Special", "Hawaiian Pizza", "BBQ Chicken"}
    };
    private double[][] pizzaPrices = {
        {450.0, 520.0, 680.0},
        {420.0, 480.0, 720.0},
        {550.0, 600.0, 750.0}
    };
    
    private String[][] burgerMenus = {
        {"Classic Burger", "Cheese Burger", "Double Burger"},
        {"Royal Burger", "Chicken Burger", "Fish Burger"},
        {"House Special", "Bacon Burger", "Veggie Burger"}
    };
    private double[][] burgerPrices = {
        {300.0, 380.0, 520.0},
        {450.0, 420.0, 380.0},
        {580.0, 520.0, 350.0}
    };
    
    private String[][] biryaniMenus = {
        {"Chicken Biryani", "Mutton Biryani", "Veg Biryani"},
        {"Hyderabadi Special", "Dum Biryani", "Egg Biryani"},
        {"Express Special", "Prawn Biryani", "Paneer Biryani"}
    };
    private double[][] biryaniPrices = {
        {480.0, 650.0, 380.0},
        {720.0, 580.0, 420.0},
        {550.0, 680.0, 450.0}
    };
    
    private String[][] ramenMenus = {
        {"Chicken Ramen", "Beef Ramen", "Veggie Ramen"},
        {"Miso Ramen", "Shoyu Ramen", "Tonkotsu Ramen"},
        {"Spicy Ramen", "Seafood Ramen", "Classic Ramen"}
    };
    private double[][] ramenPrices = {
        {420.0, 480.0, 380.0},
        {520.0, 500.0, 580.0},
        {450.0, 620.0, 400.0}
    };
    
    private String selectedCategory = "";
    private int selectedRestaurantIndex = -1;
    private MakeOrder currentOrder;
    
    public Gui()
    {
        super("FAV PANDA - Food Delivery System");
        super.setBounds(100, 50, 1000, 750);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel = new JPanel();
        panel.setLayout(null);
        c2 = new Color(240, 248, 255);
        panel.setBackground(c2);
        
        f1 = new Font("Arial", Font.BOLD, 24);
        f2 = new Font("Arial", Font.BOLD, 14);
        c1 = new Color(255, 69, 0);
        
        label1 = new JLabel("FAV PANDA");
        label1.setBounds(400, 10, 200, 40);
        label1.setFont(f1);
        label1.setForeground(c1);
        panel.add(label1);
        
        label2 = new JLabel("Customer Information");
        label2.setBounds(50, 60, 200, 25);
        label2.setFont(f2);
        panel.add(label2);
        
        label3 = new JLabel("NAME:");
        label3.setBounds(50, 90, 80, 25);
        label3.setFont(f2);
        panel.add(label3);
        nameField = new JTextField();
        nameField.setBounds(130, 90, 150, 25);
        panel.add(nameField);
        
        label4 = new JLabel("ADDRESS:");
        label4.setBounds(50, 120, 80, 25);
        label4.setFont(f2);
        panel.add(label4);
        addressField = new JTextField();
        addressField.setBounds(130, 120, 150, 25);
        panel.add(addressField);
        
        label5 = new JLabel("PHONE:");
        label5.setBounds(50, 150, 80, 25);
        label5.setFont(f2);
        panel.add(label5);
        phoneField = new JTextField();
        phoneField.setBounds(130, 150, 150, 25);
        panel.add(phoneField);
        
        label6 = new JLabel("Select Your Favorite Category");
        label6.setBounds(50, 190, 250, 25);
        label6.setFont(f2);
        panel.add(label6);
        
        pizzaRadio = new JRadioButton("Pizza");
        pizzaRadio.setBounds(50, 220, 70, 25);
        pizzaRadio.setFont(f2);
        pizzaRadio.addActionListener(this);
        panel.add(pizzaRadio);
        
        burgerRadio = new JRadioButton("Burger");
        burgerRadio.setBounds(130, 220, 80, 25);
        burgerRadio.setFont(f2);
        burgerRadio.addActionListener(this);
        panel.add(burgerRadio);
        
        biryaniRadio = new JRadioButton("Biryani");
        biryaniRadio.setBounds(220, 220, 80, 25);
        biryaniRadio.setFont(f2);
        biryaniRadio.addActionListener(this);
        panel.add(biryaniRadio);
        
        ramenRadio = new JRadioButton("Ramen");
        ramenRadio.setBounds(310, 220, 80, 25);
        ramenRadio.setFont(f2);
        ramenRadio.addActionListener(this);
        panel.add(ramenRadio);
        
        categoryGroup = new ButtonGroup();
        categoryGroup.add(pizzaRadio);
        categoryGroup.add(burgerRadio);
        categoryGroup.add(biryaniRadio);
        categoryGroup.add(ramenRadio);
        
        label7 = new JLabel("Select Your Fav Restaurant");
        label7.setBounds(550, 90, 200, 25);
        label7.setFont(f2);
        panel.add(label7);
        
        restaurantCombo = new JComboBox();
        restaurantCombo.setBounds(550, 115, 200, 25);
        restaurantCombo.setFont(f2);
        restaurantCombo.addActionListener(this);
        panel.add(restaurantCombo);
        
        menuLabel = new JLabel("MENU");
        menuLabel.setBounds(630, 170, 100, 25);
        menuLabel.setFont(f2);
        panel.add(menuLabel);
        
        menuBtn1 = new JButton("Item 1");
        menuBtn1.setBounds(550, 200, 200, 40);
        menuBtn1.setFont(f2);
        menuBtn1.setBackground(Color.LIGHT_GRAY);
        menuBtn1.addActionListener(this);
        menuBtn1.setEnabled(false);
        panel.add(menuBtn1);
        
        menuBtn2 = new JButton("Item 2");
        menuBtn2.setBounds(550, 250, 200, 40);
        menuBtn2.setFont(f2);
        menuBtn2.setBackground(Color.LIGHT_GRAY);
        menuBtn2.addActionListener(this);
        menuBtn2.setEnabled(false);
        panel.add(menuBtn2);
        
        menuBtn3 = new JButton("Item 3");
        menuBtn3.setBounds(550, 300, 200, 40);
        menuBtn3.setFont(f2);
        menuBtn3.setBackground(Color.LIGHT_GRAY);
        menuBtn3.addActionListener(this);
        menuBtn3.setEnabled(false);
        panel.add(menuBtn3);
        
        displayArea = new JTextArea();
        scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(50, 280, 400, 200);
        displayArea.setFont(f2);
        displayArea.setEditable(false);
        panel.add(scrollPane);
        
        cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(150, 500, 100, 40);
        cancelBtn.setFont(f2);
        cancelBtn.setBackground(Color.RED);
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.addActionListener(this);
        panel.add(cancelBtn);
        
        confirmBtn = new JButton("Confirm");
        confirmBtn.setBounds(300, 500, 100, 40);
        confirmBtn.setFont(f2);
        confirmBtn.setBackground(Color.GREEN);
        confirmBtn.setForeground(Color.WHITE);
        confirmBtn.addActionListener(this);
        panel.add(confirmBtn);
        
        super.add(panel);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == pizzaRadio)
        {
            selectedCategory = "Pizza";
            updateRestaurantList(pizzaRestaurants);
        }
        else if(ae.getSource() == burgerRadio)
        {
            selectedCategory = "Burger";
            updateRestaurantList(burgerRestaurants);
        }
        else if(ae.getSource() == biryaniRadio)
        {
            selectedCategory = "Biryani";
            updateRestaurantList(biryaniRestaurants);
        }
        else if(ae.getSource() == ramenRadio)
        {
            selectedCategory = "Ramen";
            updateRestaurantList(ramenRestaurants);
        }
        
        if(ae.getSource() == restaurantCombo)
        {
            selectedRestaurantIndex = restaurantCombo.getSelectedIndex();
            updateMenuButtons();
        }
        
        if(ae.getSource() == menuBtn1)
        {
            addItemToOrder(0);
        }
        else if(ae.getSource() == menuBtn2)
        {
            addItemToOrder(1);
        }
        else if(ae.getSource() == menuBtn3)
        {
            addItemToOrder(2);
        }
        
        if(ae.getSource() == confirmBtn)
        {
            confirmOrder();
        }
        else if(ae.getSource() == cancelBtn)
        {
            cancelOrder();
        }
        
        updateDisplay();
    }
    
    private void updateRestaurantList(String[] restaurants)
    {
        restaurantCombo.removeAllItems();
        for(int i = 0; i < restaurants.length; i++)
        {
            restaurantCombo.addItem(restaurants[i]);
        }
        selectedRestaurantIndex = -1;
        menuBtn1.setEnabled(false);
        menuBtn2.setEnabled(false);
        menuBtn3.setEnabled(false);
    }
    
    private void updateMenuButtons()
    {
        if(selectedRestaurantIndex >= 0)
        {
            menuBtn1.setEnabled(true);
            menuBtn2.setEnabled(true);
            menuBtn3.setEnabled(true);
            
            String[] currentMenu = getCurrentMenu();
            double[] currentPrices = getCurrentPrices();
            
            if(currentMenu != null && currentPrices != null)
            {
                menuBtn1.setText("<html>" + currentMenu[0] + "<br>tk" + currentPrices[0] + "</html>");
                menuBtn2.setText("<html>" + currentMenu[1] + "<br>tk" + currentPrices[1] + "</html>");
                menuBtn3.setText("<html>" + currentMenu[2] + "<br>tk" + currentPrices[2] + "</html>");
            }
        }
    }
    
    private String[] getCurrentMenu()
    {
        if(selectedCategory.equals("Pizza") && selectedRestaurantIndex >= 0)
            return pizzaMenus[selectedRestaurantIndex];
        else if(selectedCategory.equals("Burger") && selectedRestaurantIndex >= 0)
            return burgerMenus[selectedRestaurantIndex];
        else if(selectedCategory.equals("Biryani") && selectedRestaurantIndex >= 0)
            return biryaniMenus[selectedRestaurantIndex];
        else if(selectedCategory.equals("Ramen") && selectedRestaurantIndex >= 0)
            return ramenMenus[selectedRestaurantIndex];
        return null;
    }
    
    private double[] getCurrentPrices()
    {
        if(selectedCategory.equals("Pizza") && selectedRestaurantIndex >= 0)
            return pizzaPrices[selectedRestaurantIndex];
        else if(selectedCategory.equals("Burger") && selectedRestaurantIndex >= 0)
            return burgerPrices[selectedRestaurantIndex];
        else if(selectedCategory.equals("Biryani") && selectedRestaurantIndex >= 0)
            return biryaniPrices[selectedRestaurantIndex];
        else if(selectedCategory.equals("Ramen") && selectedRestaurantIndex >= 0)
            return ramenPrices[selectedRestaurantIndex];
        return null;
    }
    
    private void addItemToOrder(int itemIndex)
    {
        try
        {
            String name = nameField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();
            
            if(name.isEmpty() || address.isEmpty() || phone.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Please fill all customer information!");
                return;
            }
            
            if(selectedCategory.isEmpty() || selectedRestaurantIndex < 0)
            {
                JOptionPane.showMessageDialog(this, "Please select category and restaurant!");
                return;
            }
            
            Customer customer = new Customer(name, address, phone);
            
            if(currentOrder == null)
            {
                currentOrder = new MakeOrder(customer, (String)restaurantCombo.getSelectedItem());
            }
            
            if(currentOrder.getItemCount() >= 3)
            {
                JOptionPane.showMessageDialog(this, "Maximum 3 items allowed per order!");
                return;
            }
            
            String[] currentMenu = getCurrentMenu();
            double[] currentPrices = getCurrentPrices();
            
            Fooditem item = null;
            if(selectedCategory.equals("Pizza"))
                item = new Pizza(currentMenu[itemIndex], currentPrices[itemIndex]);
            else if(selectedCategory.equals("Burger"))
                item = new Burger(currentMenu[itemIndex], currentPrices[itemIndex]);
            else if(selectedCategory.equals("Biryani"))
                item = new Biriyani(currentMenu[itemIndex], currentPrices[itemIndex]);
            else if(selectedCategory.equals("Ramen"))
                item = new Ramen(currentMenu[itemIndex], currentPrices[itemIndex]);
            
            currentOrder.addItem(item);
            
            JOptionPane.showMessageDialog(this, "Added: " + item.getName() + 
                (item.hasDiscount() ? " (10% discount applied!)" : ""));
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Error adding item: " + e.getMessage());
        }
    }
    
    private void confirmOrder()
    {
        if(currentOrder == null || currentOrder.getItemCount() == 0)
        {
            JOptionPane.showMessageDialog(this, "Please add items to your order!");
            return;
        }
        
        currentOrder.saveOrder();
        JOptionPane.showMessageDialog(this, "Order confirmed and saved!\n" + currentOrder.getOrderSummary());
        
        resetForm();
    }
    
    private void cancelOrder()
    {
        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel?");
        if(result == JOptionPane.YES_OPTION)
        {
            resetForm();
        }
    }
    
    private void resetForm()
    {
        nameField.setText("");
        addressField.setText("");
        phoneField.setText("");
        categoryGroup.clearSelection();
        restaurantCombo.removeAllItems();
        selectedCategory = "";
        selectedRestaurantIndex = -1;
        currentOrder = null;
        
        menuBtn1.setEnabled(false);
        menuBtn2.setEnabled(false);
        menuBtn3.setEnabled(false);
        menuBtn1.setText("Item 1");
        menuBtn2.setText("Item 2");
        menuBtn3.setText("Item 3");
        
        updateDisplay();
    }
    
    private void updateDisplay()
    {
        String display = "";
        display += "=== ORDER DETAILS ===\n";
        display += "Name: " + nameField.getText() + "\n";
        display += "Address: " + addressField.getText() + "\n";
        display += "Phone: " + phoneField.getText() + "\n\n";
        
        if(!selectedCategory.isEmpty())
        {
            display += "Category: " + selectedCategory + "\n";
        }
        
        if(selectedRestaurantIndex >= 0)
        {
            display += "Restaurant: " + restaurantCombo.getSelectedItem() + "\n\n";
        }
        
        if(currentOrder != null && currentOrder.getItemCount() > 0)
        {
            display += currentOrder.getOrderSummary();
        }
        
        displayArea.setText(display);
    }
}