// File: MenuItem.java
// ทำให้เป็น abstract class (Class นามธรรม)
public abstract class MenuItem {
    protected String name; // เปลี่ยนเป็น protected เพื่อให้ Class ลูกเข้าถึงได้
    protected double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    
    // สร้าง abstract method ให้ Class ลูกไป implement
    public abstract String getType(); 

    // ปรับปรุงการแสดงผลเล็กน้อย
    @Override
    public String toString() {
        // (ประเภท: อาหาร) ข้าวกะเพรา - 60.0 บาท
        return "(ประเภท: " + getType() + ") " + this.name + " - " + this.price + " บาท";
    }
}