package com.mtit.assignment2.repository;

import org.springframework.data.repository.CrudRepository;
import com.mtit.assignment2.models.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {

}
