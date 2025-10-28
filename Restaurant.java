// File: Restaurant.java
import java.util.ArrayList;

public class Restaurant {
    private ArrayList<MenuItem> menu;

    public Restaurant() {
        this.menu = new ArrayList<>();
        initializeMenu();
    }

    private void initializeMenu() {
        // สร้างเมนูโดยใช้คลาสลูก (FoodItem / DrinkItem)
        this.menu.add(new FoodItem("ข้าวกะเพราหมูสับ", 60.0));
        this.menu.add(new FoodItem("ข้าวผัดกุ้ง", 70.0));
        this.menu.add(new FoodItem("คะน้าหูกรอบ", 65.0));
        this.menu.add(new FoodItem("ต้มยำกุ้ง", 120.0));
        this.menu.add(new DrinkItem("น้ำเปล่า", 10.0, "ขวด"));
        // เพิ่มเมนูเพิ่มเติม
        this.menu.add(new DrinkItem("โค้ก", 20.0, "M"));
        this.menu.add(new DrinkItem("ชามะนาว", 25.0, "L"));
        this.menu.add(new FoodItem("ผัดซีอิ๊วหมู", 65.0));
        this.menu.add(new FoodItem("ส้มตำไทย", 50.0));
        this.menu.add(new DrinkItem("ชาเย็น", 30.0, "M"));
    }

    // *** เมธอดที่แก้ไข/เพิ่มเข้ามา ***
    // จากเดิม: displayMenu() ที่เป็น void
    // เปลี่ยนเป็น: getMenuAsString() ที่ return String
    public String getMenuAsString() {
        StringBuilder sb = new StringBuilder();
        sb.append("========= เมนูอาหาร =========\n");
        for (int i = 0; i < menu.size(); i++) {
            sb.append(i + 1).append(". ").append(menu.get(i).toString()).append("\n");
        }
        sb.append("============================");
        return sb.toString();
    }
    
    // เก็บไว้เพื่อความเข้ากันได้กับโค้ดเวอร์ชันก่อนหน้า
    public void displayMenu() {
        System.out.println(getMenuAsString());
    }
    // เมธอดนี้ยังใช้เหมือนเดิม
    public MenuItem getMenuItem(int index) {
        if (index >= 0 && index < menu.size()) {
            return menu.get(index);
        }
        return null;
    }
}