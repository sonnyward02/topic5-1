package com.gcu.business;

import java.util.List;
import com.gcu.model.OrderModel;

public interface OrdersBusinessServiceInterface 
{
	public void test();
	public void init();
	public void destroy();
	
	public List<OrderModel> getOrders();
	public OrderModel getById(String id);
	public List<OrderModel> searchOrders(String searchTerm);
	public String addOne(OrderModel newOrder);
	public boolean deleteOne(String id);
	public OrderModel updateOne(String idToUpdate, OrderModel updateOrder);
}
