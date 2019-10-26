package com.artisancreek.horsetrack.service;

import com.artisancreek.horsetrack.model.Horse;
import com.artisancreek.horsetrack.model.Inventory;
import com.artisancreek.horsetrack.repository.HorseRepository;
import com.artisancreek.horsetrack.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporterService {

  public ReporterService() {
  }

  @Autowired
  private HorseRepository horseRepository;

  @Autowired
  private InventoryRepository inventoryRepository;

  @Value("${currency.symbol}")
  private String currencySymbol;

  public void printHorses() {

    Iterable<Horse> horses = horseRepository.findAll();
    System.out.println("Horses:");
    horses.forEach((horse) -> {
      System.out.println(horse.getHorseNumber()
          +","+horse.getHorseName()
          +","+horse.getOdds()
          +","+horse.getRaceStatus().toString().toLowerCase());
    });
  }

  public void printInventory() {

    Iterable<Inventory> inventories = inventoryRepository.findAll();
    System.out.println("Inventory:");
    inventories.forEach((inventory) -> {
      System.out.println(currencySymbol
          +inventory.getDenomination()
          +","+inventory.getBillCount());
    });
  }

  public void startup() {
    printInventory();
    printHorses();
  }

}
