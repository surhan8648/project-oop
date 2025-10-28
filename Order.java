// File: Order.java
import java.util.ArrayList;

public class Order {
    private ArrayList<MenuItem> items;

    public Order() {
        this.items = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        this.items.add(item);
    }

    // *** เมธอดที่แก้ไข/เพิ่มเข้ามา ***
    public String getOrderAsString() {
        if (items.isEmpty()) {
            return ">> ออเดอร์ของคุณว่างเปล่า";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("--- ออเดอร์ของคุณ ---\n");
        for (MenuItem item : items) {
            sb.append(item.toString() + "\n");
        }
        sb.append("---------------------");
        return sb.toString();
    }

    // เมธอดนี้ยังใช้เหมือนเดิม
    public double calculateTotal() {
        double total = 0.0;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        return total;
    }
}