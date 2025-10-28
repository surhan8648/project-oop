// File: RestaurantGUI.java
import javax.swing.*; // Import Swing มาสร้างหน้าต่าง
import java.awt.*; // Import มาจัดหน้า
import java.awt.event.*; // Import มาดักจับการคลิก

// ให้ Class นี้ "สืบทอด" คุณสมบัติมาจาก JFrame (หน้าต่าง)
public class RestaurantGUI extends JFrame implements ActionListener {

    // 1. ประกาศ "สมอง" (Logic)
    private Restaurant restaurant;
    private Order order;

    // 2. ประกาศ "ส่วนประกอบ" หน้าจอ
    private JTextArea displayArea; // จอแสดงผล (แทน Console)
    private JButton menuButton, orderButton, viewOrderButton, checkoutButton;
    
    // นี่คือ Constructor (เมธอดที่จะรันเมื่อถูกสร้าง)
    public RestaurantGUI() {
        // 3. สร้าง Object ของสมอง
        restaurant = new Restaurant();
        order = new Order();

        // 4. ตั้งค่าหน้าต่างหลัก
        setTitle("ระบบรับออเดอร์ร้านอาหาร (GUI)");
        setSize(500, 400); // กว้าง 500, สูง 400
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // กด X ให้ออกจากโปรแกรม
        setLayout(new BorderLayout()); // จัดวางแบบง่ายๆ (เหนือ, ใต้, กลาง)

        // 5. สร้างจอแสดงผล
        displayArea = new JTextArea();
        displayArea.setEditable(false); // ทำให้พิมพ์ทับไม่ได้
        JScrollPane scrollPane = new JScrollPane(displayArea); // ทำให้มี Scrollbar
        add(scrollPane, BorderLayout.CENTER); // วางจอไว้ตรงกลาง

        // 6. สร้าง "แผง" สำหรับวางปุ่ม
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout()); // วางปุ่มเรียงกันไป

        // 7. สร้างปุ่ม (นี่คือเมนู 4 ข้อของเรา)
        menuButton = new JButton("1. แสดงเมนู");
        orderButton = new JButton("2. สั่งอาหาร");
        viewOrderButton = new JButton("3. ดูออเดอร์");
        checkoutButton = new JButton("4. เช็คบิล");

        // 8. "ลงทะเบียน" ว่าถ้าปุ่มถูกคลิก ให้มาเรียกเมธอดใน Class นี้
        menuButton.addActionListener(this);
        orderButton.addActionListener(this);
        viewOrderButton.addActionListener(this);
        checkoutButton.addActionListener(this);

        // 9. เอาปุ่มไปวางบนแผง
        buttonPanel.add(menuButton);
        buttonPanel.add(orderButton);
        buttonPanel.add(viewOrderButton);
        buttonPanel.add(checkoutButton);

        // 10. เอาแผงปุ่มไปวางไว้ "ข้างล่าง" หน้าต่าง
        add(buttonPanel, BorderLayout.SOUTH);

        // 11. แสดงหน้าต่าง
        setVisible(true);
        
        // 12. ข้อความต้อนรับ
        displayArea.setText("ยินดีต้อนรับสู่ระบบร้านอาหาร!\nกรุณาเลือกเมนูจากปุ่มด้านล่าง");
    }

    // *** นี่คือเมธอดหลักที่มาแทน switch-case ***
    // มันจะถูกเรียก "ทุกครั้ง" ที่มีการกดปุ่ม
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // ถ้าปุ่มที่ 1 (แสดงเมนู) ถูกกด
        if (e.getSource() == menuButton) {
            String menuText = restaurant.getMenuAsString();
            displayArea.setText(menuText); // เอาข้อความไปแสดงบนจอ
        }
        
        // ถ้าปุ่มที่ 2 (สั่งอาหาร) ถูกกด
        else if (e.getSource() == orderButton) {
            // ใช้ JOptionPane (หน้าต่าง Pop-up) เพื่อรับค่า (แทน Scanner)
            String input = JOptionPane.showInputDialog(this, 
                "ใส่หมายเลขเมนูที่ต้องการ (1-5):");
            
            try {
                int itemNumber = Integer.parseInt(input); // แปลงข้อความเป็นตัวเลข
                MenuItem selectedItem = restaurant.getMenuItem(itemNumber - 1); // -1 เพราะ Array เริ่มที่ 0

                if (selectedItem != null) {
                    order.addItem(selectedItem);
                    displayArea.setText(">> เพิ่ม '" + selectedItem.getName() + "' ลงในออเดอร์แล้ว");
                } else {
                    displayArea.setText(">> เลือกเมนูไม่ถูกต้อง!");
                }
            } catch (Exception ex) {
                displayArea.setText(">> ข้อผิดพลาด: กรุณาป้อนเฉพาะตัวเลข!");
            }
        }
        
        // ถ้าปุ่มที่ 3 (ดูออเดอร์) ถูกกด
        else if (e.getSource() == viewOrderButton) {
            String orderText = order.getOrderAsString();
            displayArea.setText(orderText);
        }
        
        // ถ้าปุ่มที่ 4 (เช็คบิล) ถูกกด
        else if (e.getSource() == checkoutButton) {
            double total = order.calculateTotal();
            String orderText = order.getOrderAsString();
            
            displayArea.setText(orderText + "\n\n" + ">> ราคารวมทั้งหมด: " + total + " บาท");
            
            // (เราสามารถเพิ่ม JOptionPane เพื่อรับเงินและคำนวณเงินทอนต่อได้)
        }
    }

    // *** นี่คือเมธอด main ตัวใหม่ ***
    // (ย้าย main จากไฟล์เดิมมาไว้ที่นี่)
    public static void main(String[] args) {
        // สั่งให้ Java สร้างหน้าต่าง GUI ของเรา
        new RestaurantGUI();
    }
}