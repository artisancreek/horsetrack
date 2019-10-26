package com.artisancreek.horsetrack.service;

import com.artisancreek.horsetrack.model.Inventory;
import com.artisancreek.horsetrack.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

  @Value("${restock.amount}")
  private int restockAmount;

  @Autowired
  private InventoryRepository inventoryRepository;

  public void restock() {
    List<Inventory> inventories = inventoryRepository.findAll();

    inventories.stream()
      .forEach(inventory-> {
        inventory.setBillCount(restockAmount);
        inventoryRepository.save(inventory);
      });

  }

}
