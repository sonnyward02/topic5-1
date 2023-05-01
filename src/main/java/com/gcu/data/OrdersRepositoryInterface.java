package com.gcu.data;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import com.gcu.model.OrderEntity;

@Component
public interface OrdersRepositoryInterface extends MongoRepository<OrderEntity, String> 
{
	@Query("{'productName':{'$regex':'?0','$options':'i'}}")
	List<OrderEntity> findOrderByName(String searchTerm);
}
