package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

import com.gcu.business.FiveValidLogins;
import com.gcu.business.OrdersBusinessService;
import com.gcu.business.OrdersBusinessServiceInterface;
import com.gcu.business.SecurityServiceInterface;
import com.gcu.data.OrdersDataAccessInterface;
import com.gcu.data.OrdersDataServiceForRepository;
import com.gcu.model.OrderModel;

@Configuration
public class SpringConfig 
{
	@Bean(name="ordersBusinessService", initMethod="init", destroyMethod="destroy")
	@RequestScope
	public OrdersBusinessServiceInterface getOrdersBusiness()
	{
		return new OrdersBusinessService();
	}
	
	@Bean(name="ordersDAO")
	@RequestScope
	public OrdersDataAccessInterface<OrderModel> getDataService()
	{
		//return new OrdersDataService();
		//return new OrdersFakeDAO();
		return new OrdersDataServiceForRepository();
	}
	
	@Bean(name ="securityService")
	public SecurityServiceInterface getSecurityService()
	{
		return new FiveValidLogins();
	}
}


