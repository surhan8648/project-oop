// File: FoodItem.java
// ใช้ extends เพื่อสืบทอดคุณสมบัติจาก MenuItem
public class FoodItem extends MenuItem {
    
    // สามารถเพิ่มคุณสมบัติเฉพาะของอาหารได้ เช่น ระดับความเผ็ด
    // private String spiceLevel; 

    public FoodItem(String name, double price) {
        // เรียก Constructor ของ Class แม่ (MenuItem)
        super(name, price); 
    }

    // ต้อง implement เมธอดที่ Class แม่บังคับไว้
    @Override
    public String getType() {
        return "อาหาร";
    }
}