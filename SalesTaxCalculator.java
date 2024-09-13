import java.util.ArrayList;
import java.util.List;

class Item {
    private String name;
    private double price;
    private boolean isImported;
    private boolean isExempt;

    public Item(String name, double price, boolean isImported, boolean isExempt) {
        this.name = name;
        this.price = price;
        this.isImported = isImported;
        this.isExempt = isExempt;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isImported() {
        return isImported;
    }

    public boolean isExempt() {
        return isExempt;
    }
}

class Receipt {
    private List<Item> items;
    private double totalSalesTax;
    private double totalPrice;

    public Receipt() {
        items = new ArrayList<>();
        totalSalesTax = 0;
        totalPrice = 0;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void calculateTotals() {
        for (Item item : items) {
            double itemTax = calculateItemTax(item);
            double itemPriceWithTax = item.getPrice() + itemTax;
            totalSalesTax += itemTax;
            totalPrice += itemPriceWithTax;

            System.out.printf("%s: %.2f\n", item.getName(), roundPrice(itemPriceWithTax));
        }
    }

    private double calculateItemTax(Item item) {
        double salesTax = 0;

        if (!item.isExempt()) {
            salesTax += 0.10 * item.getPrice();
        }

        if (item.isImported()) {
            salesTax += 0.05 * item.getPrice();
        }

        return roundTax(salesTax);
    }

    private double roundTax(double tax) {
        return Math.ceil(tax * 20) / 20.0;
    }

    private double roundPrice(double price) {
        return Math.round(price * 100.0) / 100.0;
    }

    public void printSummary() {
        System.out.printf("Sales Taxes: %.2f\n", roundPrice(totalSalesTax));
        System.out.printf("Total: %.2f\n", roundPrice(totalPrice));
        
    }
}

public class SalesTaxCalculator {

    public static void main(String[] args) {
        
        System.out.println("\nOutput 1:");
        Receipt receipt1 = new Receipt();
        receipt1.addItem(new Item("1 book", 12.49, false, true));
        receipt1.addItem(new Item("1 music CD", 14.99, false, false));
        receipt1.addItem(new Item("1 chocolate bar", 0.85, false, true));
        receipt1.calculateTotals();
        receipt1.printSummary();

        
        System.out.println("\nOutput 2:");
        Receipt receipt2 = new Receipt();
        receipt2.addItem(new Item("1 imported box of chocolates", 10.00, true, true));
        receipt2.addItem(new Item("1 imported bottle of perfume", 47.50, true, false));
        receipt2.calculateTotals();
        receipt2.printSummary();

        
        System.out.println("\nOutput 3:");
        Receipt receipt3 = new Receipt();
        receipt3.addItem(new Item("1 imported bottle of perfume", 27.99, true, false));
        receipt3.addItem(new Item("1 bottle of perfume", 18.99, false, false));
        receipt3.addItem(new Item("1 packet of headache pills", 9.75, false, true));
        receipt3.addItem(new Item("1 box of imported chocolates", 11.25, true, true));
        receipt3.calculateTotals();
        receipt3.printSummary();
    }
}
