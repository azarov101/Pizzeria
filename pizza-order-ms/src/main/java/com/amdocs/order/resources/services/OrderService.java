package com.amdocs.order.resources.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.amdocs.order.resources.services.interfaces.IOrderService;
import com.example.order.model.external.CreateOrderRequest;
import com.example.order.model.external.CreateOrderResponse;
import com.example.order.model.external.GetAllOrdersResponse;
import com.example.order.model.external.GetOrderDetailsResponse;
import com.example.order.model.external.OrderDetails;
import com.example.order.model.external.OrderDetailsExtra;
import com.example.order.model.external.PizzaDrinks;
import com.example.order.model.external.PizzaDrinksExtra;
import com.example.order.model.external.PizzaExtra;
import com.example.order.model.external.PizzaToppings;
import com.example.order.model.external.PizzaToppingsExtra;

@Service
public class OrderService implements IOrderService {

	protected MongoTemplate mongoTemplate;
	protected Random rand; // generate random order id
	
	public OrderService(MongoTemplate mongoTemplate) {
		super();
		this.mongoTemplate = mongoTemplate;
		this.rand = new Random();
	}
	
	@Override
	public GetAllOrdersResponse getAllOrders() {
		List<GetOrderDetailsResponse> orderList = mongoTemplate.findAll(GetOrderDetailsResponse.class, "pizza_order");
		
		return new GetAllOrdersResponse() {{
			setOrderList(orderList);
		}};
	}
	
	public GetOrderDetailsResponse getOrderDetails(Long orderId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("orderId").is(orderId));
		
		GetOrderDetailsResponse response = mongoTemplate.findOne(query, GetOrderDetailsResponse.class, "pizza_order");
		
		return response != null ? response : new GetOrderDetailsResponse();
	}
	
	public CreateOrderResponse createOrder(CreateOrderRequest request) {
		GetOrderDetailsResponse newOrder = mapOrder(request); // map order object
		int counter = 0;
		
		do {
			try {
				mongoTemplate.save(newOrder, "pizza_order"); // save to database
				break;
			} catch(Exception e) {
				try {
				    Thread.sleep(1000);
				} catch (InterruptedException ie) {
					System.out.println("\nInterruptedException - Thread exception\n");
				    Thread.currentThread().interrupt();
				}
				if (++counter == 3) {
					System.out.println("\nMongoTemplate failed to save. Retry #" + counter + "\n");
					return createOrderResponse(0L, "Failure"); // return status failure
				}
			}
		} while (counter < 3);

		
		return createOrderResponse(newOrder.getOrderId(), "Success"); // return status success
	}
	
	protected Long GenerateRandomNumber() {
		int random = rand.nextInt(Integer.MAX_VALUE);
		return Long.valueOf(random);
	}
	
	//  													Mapping Methods
	//-----------------------------------------------------------------------------------------------------------------------------
	private CreateOrderResponse createOrderResponse(Long id, String status) {
		return new CreateOrderResponse() {{
			setOrderId(id);
			setRequestStatus(status);
		}};	
	}
	
	/**
	 * This method map the request order before inserting it to database.
	 * @param request - the request order.
	 * @return the new mapped order.
	 */
	private GetOrderDetailsResponse mapOrder(CreateOrderRequest request) {
		GetOrderDetailsResponse order = new GetOrderDetailsResponse(); // create new list
		
		order.setName(request.getName());
		order.setTotalPrice(request.getTotalPrice());
		order.setLocation(request.getLocation());
		order.setNotes(request.getNotes());
		
		List<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
		
		for(OrderDetailsExtra requestItem: request.getItems()) {
			OrderDetails orderDetails = new OrderDetails(); // create new orderDetails
			
			orderDetails.setPizzaDescription(requestItem.getPizzaDescription());
			orderDetails.setNumberOfPizzas(requestItem.getNumberOfPizzas());
			orderDetails.setOrderPrice(requestItem.getOrderPrice());
			orderDetails.setToppings(mapToppings(requestItem.getToppings()));
			orderDetails.setDrinks(mapDrinks(requestItem.getDrinks()));
			
			orderDetailsList.add(orderDetails); // add object to list
		}
		
		order.setItems(orderDetailsList);
		
		Long orderId = GenerateRandomNumber();
		order.setOrderId(orderId);
		
		return order;
	}
	
	/**
	 * This method map the extended toppings list to simplified toppings list (remove extra data before saving to DB).
	 * PizzaToppingsExtra object may contain: int pizzaNumber, PizzaExtra toppings.
	 * @param otherList - toppings list.
	 * @return PizzaToppings list of toppings.
	 */
	private List<PizzaToppings> mapToppings(List<PizzaToppingsExtra> otherList) {
		List<PizzaToppings> toppingsList = new ArrayList<PizzaToppings>(); // create new list
		
		for(PizzaToppingsExtra other: otherList) {
			PizzaToppings toppings = new PizzaToppings();
			
			toppings.setPizzaNumber(other.getPizzaNumber());
			toppings.setToppings(mapExtra(other.getToppings()));
			
			toppingsList.add(toppings); // add object to list
		}
		
		return toppingsList;
	}
	
	/**
	 * This method map toppings from List of PizzaExtra to List of Strings.
	 * PizzaExtra Object may contain: String item, Double price, Double discountedPrice, String image.
	 * @param extraList - toppings list.
	 * @return toppings list of strings.
	 */
	private List<String> mapExtra(List<PizzaExtra> extraList) {
		List<String> list = new ArrayList<String>();
		
		for(PizzaExtra extra: extraList) {
			list.add(extra.getItem());
		}
		return list;
	}
	
	/**
	 * This method map drinks from List of PizzaDrinksExtra to List of PizzaDrinks.
	 * PizzaDrinksExtra Object may contain: String item, Double price, Double discountedPrice, String image, int amount.
	 * @param otherList - drinks list.
	 * @return drink list of PizzaDrinks object. Each object contains: String item, int amount.
	 */
	private List<PizzaDrinks> mapDrinks(List<PizzaDrinksExtra> otherList) {
		List<PizzaDrinks> list = new ArrayList<PizzaDrinks>();
		
		for(PizzaDrinksExtra other: otherList) {
			PizzaDrinks drink = new PizzaDrinks();
			
			drink.setItem(other.getItem());
			drink.setAmount(other.getAmount());
			
			list.add(drink);
		}
		return list;
	}
	//-----------------------------------------------------------------------------------------------------------------------------
}
