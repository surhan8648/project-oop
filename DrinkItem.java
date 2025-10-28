// File: DrinkItem.java
public class DrinkItem extends MenuItem {
    
    private String size; // คุณสมบัติเฉพาะของเครื่องดื่ม

    public DrinkItem(String name, double price, String size) {
        super(name, price);
        this.size = size;
    }

    @Override
    public String getType() {
        return "เครื่องดื่ม";
    }

    // เราสามารถ Override เมธอด toString() เพื่อแสดงผลแบบใหม่
    @Override
    public String toString() {
        // (ประเภท: เครื่องดื่ม) โค้ก [M] - 20.0 บาท
        return "(ประเภท: " + getType() + ") " + this.name + " [" + this.size + "] - " + this.price + " บาท";
    }
}