package Classes;



public class Order{
	/* Класс, описывающий заказ, в котором содержится определенное число товаров 
	 * 
	 *
	 */
	int numOfProducts; // количество товаров
	double sumOfProduct; // сумма товаров
	double sale; // скидка на товар в процентах
	
	public Order(int numOfProducts, double sumOfProduct, double sale) {
		this.numOfProducts = numOfProducts;
		this.sumOfProduct = sumOfProduct;
		this.sale = sale;
	}
	
	
	
	static void printOrder(Order order) {
		System.out.println("Сумма заказа:" + String.format("%.2f", order.numOfProducts*order.sumOfProduct));
		System.out.println("Сумма заказа со скидкой:" + String.format("%.2f", (order.numOfProducts*order.sumOfProduct)*(100 - order.sale)/100));
	}
	
}
