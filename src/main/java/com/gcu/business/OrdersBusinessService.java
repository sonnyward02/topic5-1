package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.OrdersDataServiceForRepository;
import com.gcu.model.OrderModel;

public class OrdersBusinessService implements OrdersBusinessServiceInterface 
{
	@Autowired
	OrdersDataServiceForRepository service;
	
	@Override
	public void test() 
	{
		System.out.println("OrderBusinessService is working");
	}

	@Override
	public List<OrderModel> getOrders() 
	{
		List<OrderModel> orders = new ArrayList<OrderModel>(); 
		orders = service.getOrders(); 
		
		return orders;
	}

	@Override
	public void init() 
	{
		System.out.println("Init method of Orders Business Service");
	}

	@Override
	public void destroy() 
	{
		System.out.println("Destroy method of Orders Business Service");
	}
	
	@Override
	public OrderModel getById(String id) 
	{
		 
		OrderModel result = null;
		try
		{
			result = service.getById(id);
			
		}
		catch (Exception e)
		{
			System.out.println(e);
			 
		}
		
		return result;
	}

	@Override
	public List<OrderModel> searchOrders(String searchTerm) 
	{
		List<OrderModel> orders = new ArrayList<OrderModel>();
		orders = service.searchOrders(searchTerm);
		
		return orders;
	}

	@Override
	public String addOne(OrderModel newOrder) 
	{
		String result = "";
		result = service.addOne(newOrder);
		
		return result;
	}

	@Override
	public boolean deleteOne(String id) 
	{
		boolean result = false;
		try 
		{
			result = service.deleteOne(id);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return result;
	}

	@Override
	public OrderModel updateOne(String idToUpdate, OrderModel updateOrder) 
	{
		service.updateOne(idToUpdate, updateOrder);
		
		return updateOrder;
	}

}
