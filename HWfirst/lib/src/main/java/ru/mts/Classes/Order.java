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
		else if(order.numOfProducts <= 0 || order.sumOfProduct <= 0 || order.sale < 0 || order.sale > 100){
			System.out.println("Некорректные параметры заказа");
		}
		else {
			double sumOfOrder = order.numOfProducts*order.sumOfProduct; //сумма всего заказа без скидки
			System.out.println("Сумма заказа:" + String.format("%.2f", sumOfOrder));
			System.out.println("Сумма заказа со скидкой:" + String.format("%.2f", sumOfOrder*(100 - order.sale)/100));
		}
	}
	
}
