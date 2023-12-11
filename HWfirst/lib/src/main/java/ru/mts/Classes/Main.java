package ru.mts.Classes;

public class Main{
	public static void main(String[] args) {
		Order firstOrder = new Order(5, 320, 0.75);
		Order secondOrder = new Order(7, 180, 42.575);
		Order thirdOrder = new Order(12, 540, 59.1);

		System.out.println("Первый заказ");
		Order.printOrder(firstOrder);
		System.out.println();
		System.out.println("Второй заказ");
		Order.printOrder(secondOrder);
		System.out.println();
		System.out.println("Третий заказ");
		Order.printOrder(thirdOrder);
	}
	
}