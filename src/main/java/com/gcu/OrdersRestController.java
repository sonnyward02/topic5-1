package com.gcu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.business.OrdersBusinessServiceInterface;
import com.gcu.model.OrderModel;

@RestController
@RequestMapping("/api/v1/orders")
public class OrdersRestController 
{
	@Autowired
	private OrdersBusinessServiceInterface service;
	
	@Autowired
	public OrdersRestController(OrdersBusinessServiceInterface service)
	{
		super();
		this.service = service;
	}
	
	@GetMapping("/")
	public ResponseEntity<?> showAllOrders()
	{
		try 
		{
			List<OrderModel> orders = service.getOrders();
			if(orders != null)
			{
				return new ResponseEntity<>(orders, HttpStatus.OK);
			}
			else 
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable(name="id") String id)
	{
		try 
		{
			OrderModel order = service.getById(id);
			if(order != null)
			{
				return new ResponseEntity<>(order, HttpStatus.OK);
			}
			else 
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/search/{searchTerm}")
	public ResponseEntity<?> searchOrders(@PathVariable(name="searchTerm") String searchTerm)
	{
		List<OrderModel> results = null;
		
		try 
		{
			results = service.searchOrders(searchTerm);
			if(results != null)
			{
				return new ResponseEntity<>(results, HttpStatus.OK);
			}
			else 
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<?> addOrder(@RequestBody OrderModel model)
	{
		String results = null;
		
		try 
		{
			results = service.addOne(model);
			if(results != "")
			{
				return new ResponseEntity<>(results, HttpStatus.OK);
			}
			else 
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOne(@PathVariable(name="id") String id)
	{
		boolean results = false;
		
		try 
		{
			results = service.deleteOne(id);
			if(results)
			{
				return new ResponseEntity<>(results, HttpStatus.OK);
			}
			else 
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/")
	public ResponseEntity<?> update(@RequestBody OrderModel model)
	{
		OrderModel results = null;
		
		try 
		{
			results = service.updateOne(model.getId(), model);
			if(results != null)
			{
				return new ResponseEntity<>(results, HttpStatus.OK);
			}
			else 
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
