import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Restaurant restaurant = new Restaurant();
		Order order = new Order();
		boolean isRunning = true;

		System.out.println("ยินดีต้อนรับสู่ระบบรับออเดอร์ร้านอาหาร!");

		while (isRunning) {
			printMainMenu();
			System.out.print("เลือก (0=ออก,1-4): ");
			String input = scanner.nextLine().trim();

			switch (input) {
				case "1":
					restaurant.displayMenu();
					break;
				case "2":
					restaurant.displayMenu();
					System.out.print("เลือกเมนูที่ต้องการ (ใส่หมายเลข): ");
					String sel = scanner.nextLine().trim();
					try {
						int itemNumber = Integer.parseInt(sel);
						MenuItem selectedItem = restaurant.getMenuItem(itemNumber - 1);
						if (selectedItem != null) {
							order.addItem(selectedItem);
							System.out.println(">> เพิ่มลงออเดอร์: " + selectedItem.getName());
						} else {
							System.out.println(">> เลือกเมนูไม่ถูกต้อง!");
						}
					} catch (NumberFormatException e) {
						System.out.println(">> กรุณาป้อนหมายเลขเท่านั้น");
					}
					break;
				case "3":
					System.out.println(order.getOrderAsString());
					break;
				case "4":
					doCheckout(scanner, order);
					// ถ้าชำระสำเร็จ ให้ออกจากโปรแกรม
					if (order.calculateTotal() > 0) {
						// doCheckout will end program only when payment successful; we still loop so allow explicit exit
					}
					break;
				case "0":
					System.out.println("ออกจากโปรแกรม... ขอบคุณครับ");
					isRunning = false;
					break;
				default:
					System.out.println(">> เลือกไม่ถูกต้อง กรุณาเลือก 0-4");
			}
		}

		scanner.close();
	}

	private static void printMainMenu() {
		System.out.println("\n--- กรุณาเลือกเมนู ---");
		System.out.println("1. แสดงเมนูอาหารทั้งหมด");
		System.out.println("2. สั่งอาหาร (เพิ่มลงในออเดอร์)");
		System.out.println("3. ดูออเดอร์ที่สั่งไป");
		System.out.println("4. เช็คบิล และ ชำระเงิน");
	}

	private static void doCheckout(Scanner scanner, Order order) {
		System.out.println("\n--- สรุปยอด ---");
		System.out.println(order.getOrderAsString());
		double total = order.calculateTotal();
		System.out.println(">> ราคารวมทั้งหมด: " + total + " บาท");

		if (total <= 0) {
			System.out.println(">> ยังไม่มีรายการสั่งซื้อ");
			return;
		}

		System.out.print("รับเงินมา (บาท): ");
		String money = scanner.nextLine().trim();
		try {
			double cashReceived = Double.parseDouble(money);
			if (cashReceived < total) {
				System.out.println(">> รับเงินมาไม่พอ! กรุณาชำระใหม่");
			} else {
				double change = cashReceived - total;
				System.out.println(">> เงินทอน: " + change + " บาท");
				System.out.println("ขอบคุณที่ใช้บริการครับ");
				// Clear the order after successful payment (optional)
				// Currently Order has no clear method; to keep it simple we won't modify order object here
			}
		} catch (NumberFormatException e) {
			System.out.println(">> กรุณาป้อนจำนวนเงินที่เป็นตัวเลข");
		}
	}
}
