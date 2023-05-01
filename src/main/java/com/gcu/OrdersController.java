package com.gcu;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.OrdersBusinessServiceInterface;
import com.gcu.model.OrderModel;
import com.gcu.model.SearchModel;

@Controller
@RequestMapping("/orders")
public class OrdersController {

	// dependency injection.
	OrdersBusinessServiceInterface service;

	@Autowired
	public OrdersController(OrdersBusinessServiceInterface service) 
	{
		super();
		this.service = service;	
	}
	
	@GetMapping("/")
	public String showAllOrders(Model model) 
	{
		// generate some orders
		// this version of the app will send a hard-coded list of orders
		List<OrderModel> orders = service.getOrders();

		model.addAttribute("title", "Here is what I want to do this summer");
		model.addAttribute("searchOrdersModel", new SearchModel());
		model.addAttribute("orders", orders);
		return "orders.html";
	}

	@GetMapping("/searchForm") 
	public String displaySearchForm(Model model)
	{
		// Display Login Form View
		model.addAttribute("title", "Search Orders");
		model.addAttribute("searchOrdersModel", new SearchModel());
		
		return "ordersSearchForm.html";
	}

	@PostMapping("/searchResults") 
	public String search(@Valid SearchModel searchModel, BindingResult bindingResult, Model model) 
	{ 
		String searchTerm = searchModel.getSearchTerm();
		List<OrderModel> orders = service.searchOrders(searchTerm); 
		
		model.addAttribute("title", "Search Results");
		model.addAttribute("searchOrdersModel", new SearchModel());
		model.addAttribute("orders", orders);
		return "orders.html";

	}	
	
	@GetMapping("/addNewForm")
	public String displayAddNewForm(Model model) 
	{
		// Display new order form
		model.addAttribute("title", "Add new order");
		model.addAttribute("orderModel", new OrderModel());
		
		return "ordersAddNewForm.html";
	}

	@PostMapping("/addNew")
	// process a request from the AddOrderForm. Add a new order to the database.
	// Show all orders.
	public String addOrder(@Valid OrderModel newOrder, BindingResult bindingResult, Model model) 
	{
		newOrder.setId(null);
		
		// add the new order
		service.addOne(newOrder);
		// get updated list of all the orders
		List<OrderModel> orders = service.getOrders();

		// display all orders
		model.addAttribute("title", "Added an item");
		model.addAttribute("searchOrdersModel", new SearchModel());
		model.addAttribute("orders", orders);
		return "orders.html";
	}

	@GetMapping("/admin") 
	public String showAdminPage( Model model) 
	{ 
		 
		// display all orders with delete and edit buttons
		List<OrderModel> orders = service.getOrders(); 
		
		model.addAttribute("title", "Edit or delete orders");
		model.addAttribute("orders", orders);
		
		// ordersAdmin page shows a table of orders including buttons for del and edit.
		return "ordersAdmin.html";
		
	}

	@PostMapping("/editForm")
	public String displayEditForm(OrderModel orderModel, Model model) 
	{
		// Display new order form
		model.addAttribute("title", "Edit order");
		model.addAttribute("orderModel", orderModel);
		return "ordersEditForm.html";
	}

	@PostMapping("/doUpdate") 
	// process a request from the AddOrderForm.  Add a new order to the database.  Show all orders.
	public String updateOrder(@Valid OrderModel order, BindingResult bindingResult, Model model) 
	{
		// add the new order
		service.updateOne(order.getId(), order);
		
		// get updated list of all the orders
		List<OrderModel> orders = service.getOrders(); 
		
		// display all orders
		model.addAttribute("orders", orders); 
		return "ordersAdmin.html";
	}
	
	@PostMapping("/delete/")
	public String deleteOrder(@Valid OrderModel order, BindingResult bindingResult, Model model)
	{
		service.deleteOne(order.getId());
		
		List<OrderModel> orders = service.getOrders();
		
		model.addAttribute("orders", orders);
		return "ordersAdmin.html";
	}
	
	@GetMapping("/spa")
	public String showSPApage(Model model)
	{
		return "ordersSPA.html";
	}
}
