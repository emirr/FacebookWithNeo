package com.starter.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.entity.Restaurant;
@Repository
public interface RestaurantRepository extends GraphRepository<Restaurant>{
	// void registerRestaurant(Restaurant restaurant);
	
}
