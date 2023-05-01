package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gcu.model.OrderEntity;
import com.gcu.model.OrderModel;

@Repository
public class OrdersDataServiceForRepository implements OrdersDataAccessInterface<OrderModel>
{
	@Autowired
	OrdersRepositoryInterface ordersRepository;
	
	ModelMapper modelMapper = new ModelMapper();

	@Override
	public OrderModel getById(String id) 
	{
		// fetch an entity from the db.
		OrderEntity entity = ordersRepository.findById(id).orElse(null);
		
		// convert to model
		OrderModel model = modelMapper.map(entity, OrderModel.class);
		
		return model;
	}

	@Override
	public List<OrderModel> getOrders() 
	{
		Iterable<OrderEntity> entities = ordersRepository.findAll();
		List<OrderModel> orders = new ArrayList<OrderModel>();
		
		for (OrderEntity entity : entities) 
		{
			orders.add(modelMapper.map(entity, OrderModel.class));
		}
		return orders;
	}

	@Override
	public List<OrderModel> searchOrders(String searchTerm) 
	{
		Iterable<OrderEntity> entities = ordersRepository.findOrderByName(searchTerm);
		List<OrderModel> orders = new ArrayList<OrderModel>();
		
		for (OrderEntity entity : entities) 
		{
			orders.add(modelMapper.map(entity, OrderModel.class));
		}
		return orders;
	}

	@Override
	public String addOne(OrderModel newOrder) 
	{
		OrderEntity entity = modelMapper.map(newOrder, OrderEntity.class);
		OrderEntity result = ordersRepository.save(entity);
		if (result == null) {
			return null;
		}
		else {
			return result.getId();
		}
	}

	@Override
	public boolean deleteOne(String id) 
	{
		ordersRepository.deleteById(id);
		return true;
	}

	@Override
	public OrderModel updateOne(String idToUpdate, OrderModel updateOrder) 
	{
		OrderEntity entity = modelMapper.map(updateOrder, OrderEntity.class);
		OrderEntity result = ordersRepository.save(entity);
		OrderModel order = modelMapper.map(result, OrderModel.class);
		
		return order;
	}
}
