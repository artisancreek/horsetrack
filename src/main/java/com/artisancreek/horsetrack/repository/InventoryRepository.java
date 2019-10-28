package com.artisancreek.horsetrack.repository;

import com.artisancreek.horsetrack.model.Inventory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InventoryRepository extends CrudRepository<Inventory,Integer> {
  List<Inventory> findAll();
  Inventory findByDenominationEquals(int denomination);
}
