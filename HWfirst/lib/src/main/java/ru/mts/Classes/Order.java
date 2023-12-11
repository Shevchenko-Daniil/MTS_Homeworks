package ru.mts.Classes;


/* Класс, описывающий заказ, в котором содержится определенное число товаров 
 * 
 *
 */


public class Order{

	int numOfProducts; // количество товаров
	double sumOfProduct; // сумма товаров
	double sale; // скидка на товар в процентах
	
	public Order(int numOfProducts, double sumOfProduct, double sale) {
		this.numOfProducts = numOfProducts;
		this.sumOfProduct = sumOfProduct;
		this.sale = sale;
	}
	
	
	
	static void printOrder(Order order) {
		if(order == null) {
			System.out.println("Пустой объект заказа");
		}
		else if(order.numOfProducts < 0 || order.sumOfProduct < 0){
			System.out.println("Некорректные параметры заказа");
		}
		else {
			System.out.println("Сумма заказа:" + String.format("%.2f", order.numOfProducts*order.sumOfProduct));
			System.out.println("Сумма заказа со скидкой:" + String.format("%.2f", (order.numOfProducts*order.sumOfProduct)*(100 - order.sale)/100));
		}
	}
	
}
