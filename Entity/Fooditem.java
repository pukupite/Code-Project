package Entity;

public abstract class Fooditem implements Discountable
{
    private String name;
    private double price;
    private String category;
    
    public Fooditem(String name, double price, String category) 
    {
        this.name = name;
        this.price = price;
        this.category = category;
    }
    
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setCategory(String category) { this.category = category; }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    
    // Implementing Discountable interface
    public double calculateDiscount() 
    {
        if (price > 500) {
            return price * 0.10; // 10% discount for items over tk500
        }
        return 0.0;
    }
    
    public boolean hasDiscount() 
    {
        return price > 500;
    }
    
    public double getFinalPrice() 
    {
        return price - calculateDiscount();
    }
    
    public String toString() 
    {
        if (hasDiscount()) {
            return name + " (tk" + getFinalPrice() + " - Was tk" + price + " - 10% OFF!)";
        }
        return name + " (tk" + price + ")";
    }
}
